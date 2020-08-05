package com.aventstack.extentreports.reporter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.aventstack.extentreports.AttribStatusDist;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.ExceptionInfo;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.MetaDataStorable;
import com.aventstack.extentreports.model.NamedAttribute;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.model.ReportStats;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.SystemEnvInfo;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.context.NamedAttributeContext;
import com.aventstack.extentreports.model.context.NamedAttributeContextManager;
import com.aventstack.extentreports.observer.EntityObserver;
import com.aventstack.extentreports.observer.entity.AttributeEntity;
import com.aventstack.extentreports.observer.entity.LogEntity;
import com.aventstack.extentreports.observer.entity.MediaEntity;
import com.aventstack.extentreports.observer.entity.ObservedEntity;
import com.aventstack.extentreports.observer.entity.ReportEntity;
import com.aventstack.extentreports.observer.entity.TestEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * ExtentKlovReporter is a NoSQL database reporter (MongoDB), which updates
 * information in the database which is then used by the ExtentX server to
 * display in-depth analysis.
 * 
 * ExtentKlovReporter is a port from ExtentReports version 4.
 */
public class ExtentKlovReporter extends AbstractReporter
        implements
            EntityObserver<ObservedEntity> {
    public static final String ID_KEY = "KLOV_ID";
    public static final String REPORT_ID_KEY = "KLOV_REPORT_ID";
    public static final String LOG_ID_KEY = "KLOV_LOG_ID";
    public static final String TEST_ID_KEY = "KLOV_TEST_ID";

    private static final String DEFAULT_PROJECT_NAME_PROP = "klov.project.name";
    private static final String DEFAULT_REPORT_NAME_PROP = "klov.report.name";
    private static final String DEFAULT_MONGODB_HOST_PROP = "mongodb.host";
    private static final String DEFAULT_MONGODB_PORT_PROP = "mongodb.port";
    private static final String DEFAULT_MONGODB_URI_PROP = "mongodb.uri";
    private static final String DEFAULT_KLOV_HOST_PROP = "klov.host";
    private static final String DEFAULT_KLOV_PORT_PROP = "klov.port";
    private static final String DB_NAME = "klov";
    private static final String DEFAULT_PROJECT_NAME = "Default";

    private final AtomicBoolean initiated = new AtomicBoolean();

    private String url;
    private Boolean appendExisting = false;

    private NamedAttributeContextManager<Category> categoryContext;
    private NamedAttributeContextManager<Author> authorContext;
    private NamedAttributeContextManager<Device> deviceContext;
    private Map<String, ObjectId> categoryNameObjectIdCollection = new HashMap<>();
    private Map<String, ObjectId> authorNameObjectIdCollection = new HashMap<>();
    private Map<String, ObjectId> deviceNameObjectIdCollection = new HashMap<>();
    private Map<String, ObjectId> exceptionNameObjectIdCollection = new HashMap<>();
    private KlovMediaStorageHandler mediaStorageHandler;

    private ObjectId reportId;
    private String reportName;
    private long reportSeq;
    private ObjectId projectId;
    private String projectName;

    private MongoClient mongoClient;

    private MongoCollection<Document> projectCollection;
    private MongoCollection<Document> reportCollection;
    private MongoCollection<Document> testCollection;
    private MongoCollection<Document> logCollection;
    private MongoCollection<Document> exceptionCollection;
    private MongoCollection<Document> mediaCollection;
    private MongoCollection<Document> categoryCollection;
    private MongoCollection<Document> authorCollection;
    private MongoCollection<Document> deviceCollection;
    private MongoCollection<Document> environmentCollection;

    static {
        /* use mongodb reporting for only critical/severe events */
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
    }

    /**
     * Initializes the KlovReporter
     */
    public ExtentKlovReporter() {
    }

    /**
     * Initializes the KlovReporter with project and report names
     * 
     * @param projectName
     *            Name of the project
     * @param reportName
     *            Name of the report
     */
    public ExtentKlovReporter(String projectName, String reportName) {
        this();
        this.projectName = projectName;
        this.reportName = reportName;
    }

    public ExtentKlovReporter(String projectName) {
        this(projectName, null);
    }

    /**
     * Sets the project name
     * 
     * @param projectName
     *            Name of the project
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Sets the report name
     * 
     * @param reportName
     *            Name of the report
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * Initialize Mongo DB connection with host and default port: 27017
     * 
     * @param host
     *            host name
     * @return a {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initMongoDbConnection(String host) {
        mongoClient = new MongoClient(host, 27017);
        return this;
    }

    /**
     * Initialize Mongo DB connection with host and {@link MongoClientOptions}
     * 
     * @param host
     *            host name
     * @param options
     *            {@link MongoClientOptions} options
     * @return a {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initMongoDbConnection(String host, MongoClientOptions options) {
        mongoClient = new MongoClient(host, options);
        return this;
    }

    /**
     * Initialize Mongo DB connection with host and post
     * 
     * @param host
     *            host name
     * @param port
     *            port number
     * @return a {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initMongoDbConnection(String host, int port) {
        mongoClient = new MongoClient(host, port);
        return this;
    }

    /**
     * Initialize Mongo DB connection with a {@link MongoClientURI}
     * 
     * @param uri
     *            {@link MongoClientURI} uri
     * @return a {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initMongoDbConnection(MongoClientURI uri) {
        mongoClient = new MongoClient(uri);
        return this;
    }

    /**
     * Initializes the Mongo DB connection with {@link ServerAddress}
     * 
     * @param addr
     *            {@link ServerAddress} server address
     * @return a {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initMongoDbConnection(ServerAddress addr) {
        mongoClient = new MongoClient(addr);
        return this;
    }

    /**
     * Initializes the Mongo DB connection with a list of {@link ServerAddress}
     * addresses
     * 
     * @param seeds
     *            A list of {@link ServerAddress} server addresses
     * @return a {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initMongoDbConnection(List<ServerAddress> seeds) {
        mongoClient = new MongoClient(seeds);
        return this;
    }

    /**
     * Initializes the Mongo DB connection with a list of {@link ServerAddress}
     * and {@link MongoClientOptions}
     * 
     * @param seeds
     *            A list of {@link ServerAddress} server addresses
     * @param options
     *            {@link MongoClientOptions} options
     * @return a {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initMongoDbConnection(List<ServerAddress> seeds, MongoClientOptions options) {
        mongoClient = new MongoClient(seeds, options);
        return this;
    }

    /**
     * Initializes the Mongo DB connection with a {@link ServerAddress} and
     * {@link MongoClientOptions}
     * 
     * @param addr
     *            A list of {@link ServerAddress} server addresses
     * @param options
     *            {@link MongoClientOptions} options
     * @return a {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initMongoDbConnection(ServerAddress addr, MongoClientOptions options) {
        mongoClient = new MongoClient(addr, options);
        return this;
    }

    /**
     * Initializes the Mongo DB connection with a connection url
     * 
     * @param url
     *            Url string
     * @return a {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initKlovServerConnection(String url) {
        this.url = url;
        return this;
    }

    /**
     * Initializes KlovReporter with default Klov and MongoDB settings. This
     * default the Klov server and MongoDB to LOCALHOST and also uses default
     * ports 80 and 27017 respectively.
     * 
     * @return A {@link ExtentKlovReporter} object
     */
    public ExtentKlovReporter initWithDefaultSettings() {
        return initMongoDbConnection("localhost", 27017)
                .initKlovServerConnection("http://localhost");
    }

    public void loadInitializationParams(InputStream is) {
        try {
            Properties props = loadProperties(is);
            loadInitializationParams(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Properties loadProperties(InputStream is) throws IOException {
        Properties properties = new Properties();
        properties.load(is);
        return properties;
    }

    private void loadInitializationParams(Properties props) {
        String mongoUri = props.getProperty(DEFAULT_MONGODB_URI_PROP);
        String mongoHost = props.getProperty(DEFAULT_MONGODB_HOST_PROP);
        String mongoPort = props.getProperty(DEFAULT_MONGODB_PORT_PROP);
        int mongoPortInt = IntUtil.tryParseInt(mongoPort) == true ? Integer.valueOf(mongoPort) : -1;
        if (mongoHost != null && mongoPortInt != -1) {
            initMongoDbConnection(mongoHost, mongoPortInt);
        } else if (mongoHost != null) {
            initMongoDbConnection(mongoHost);
        } else if (mongoUri != null) {
            initMongoDbConnection(new MongoClientURI(mongoUri));
        } else {
            throw new IllegalStateException("Mongo connection params have not been supplied.");
        }

        String projectName = props.getProperty(DEFAULT_PROJECT_NAME_PROP);
        this.projectName = projectName == null || projectName.isEmpty() ? this.projectName : projectName;

        String reportName = props.getProperty(DEFAULT_REPORT_NAME_PROP);
        reportName = reportName == null || reportName.isEmpty() ? this.reportName : reportName;
        this.reportName = reportName;

        String klovHost = props.getProperty(DEFAULT_KLOV_HOST_PROP);
        String klovPort = props.getProperty(DEFAULT_KLOV_PORT_PROP);
        if (klovHost != null && klovPort != null) {
            String uri = klovHost + ":" + klovPort;
            initKlovServerConnection(uri);
        } else if (klovHost != null) {
            initKlovServerConnection(klovHost);
        }
    }

    private void initCollections(MongoDatabase db) {
        projectCollection = db.getCollection("project");
        reportCollection = db.getCollection("report");
        testCollection = db.getCollection("test");
        logCollection = db.getCollection("log");
        exceptionCollection = db.getCollection("exception");
        mediaCollection = db.getCollection("media");
        categoryCollection = db.getCollection("category");
        authorCollection = db.getCollection("author");
        deviceCollection = db.getCollection("device");
        environmentCollection = db.getCollection("environment");
    }

    private void setupProject() {
        String projectName = this.projectName == null || this.projectName.isEmpty()
                ? DEFAULT_PROJECT_NAME
                : this.projectName;
        Document doc = new Document("name", projectName);
        Document project = projectCollection.find(doc).first();
        if (project != null) {
            projectId = project.getObjectId("_id");
        } else {
            doc.append("createdAt", Calendar.getInstance().getTime());
            projectCollection.insertOne(doc);
            projectId = MongoUtil.getId(doc);
        }
        setupReport(projectName);
    }

    private void setupReport(String projectName) {
        String reportName = this.reportName == null || this.reportName.isEmpty()
                ? "Build " + Calendar.getInstance().getTimeInMillis()
                : this.reportName;
        this.reportName = reportName;
        Document doc = new Document("name", reportName)
                .append("project", projectId)
                .append("projectName", projectName);
        if (appendExisting) {
            FindIterable<Document> iterable = reportCollection.find(doc);
            Document report = iterable.first();
            if (report != null) {
                reportId = report.getObjectId("_id");
                return;
            }
        }
        reportSeq = reportCollection.count(new Document("project", projectId)) + 1;
        doc.append("startTime", Calendar.getInstance().getTime())
                .append("seq", reportSeq);
        reportCollection.insertOne(doc);
        reportId = MongoUtil.getId(doc);
    }

    public synchronized void flush(ReportEntity entity) {
        Report report = entity.getReport();
        List<Test> testList = report.getTestList();

        if (testList == null || testList.isEmpty())
            return;

        ReportStats stats = report.getStats();

        Document doc = new Document("endTime", report.getEndTime())
                .append("duration", report.timeTaken())
                .append("status", report.getStatus().toLower())
                .append("parentLength", stats.sumStat(stats.getParent()))
                .append("passParentLength", stats.getParent().get(Status.PASS))
                .append("failParentLength", stats.getParent().get(Status.FAIL))
                .append("warningParentLength", stats.getParent().get(Status.WARNING))
                .append("skipParentLength", stats.getParent().get(Status.SKIP))
                .append("childLength", stats.sumStat(stats.getChild()))
                .append("passChildLength", stats.getChild().get(Status.PASS))
                .append("failChildLength", stats.getChild().get(Status.FAIL))
                .append("warningChildLength", stats.getChild().get(Status.WARNING))
                .append("skipChildLength", stats.getChild().get(Status.SKIP))
                .append("grandChildLength", stats.sumStat(stats.getGrandchild()))
                .append("passGrandChildLength", stats.getGrandchild().get(Status.PASS))
                .append("failGrandChildLength", stats.getGrandchild().get(Status.FAIL))
                .append("warningGrandChildLength", stats.getGrandchild().get(Status.WARNING))
                .append("skipGrandChildLength", stats.getGrandchild().get(Status.SKIP))
                .append("analysisStrategy", stats.getAnalysisStrategy().toString())
                .append("bdd", testList.get(0).isBDD());

        this.authorContext = report.getAuthorCtx();
        this.categoryContext = report.getCategoryCtx();
        this.deviceContext = report.getDeviceCtx();
        Set<String> authorNameList = getCollectionKeys(authorNameObjectIdCollection);
        Set<String> categoryNameList = getCollectionKeys(categoryNameObjectIdCollection);
        Set<String> deviceNameList = getCollectionKeys(deviceNameObjectIdCollection);
        Set<String> exceptionNameList = getCollectionKeys(exceptionNameObjectIdCollection);

        if (authorNameList != null) {
            // maintain for backward compatibility
            doc.append("authorNameList", authorNameList);
            Set<AttribStatusDist<Author>> set = report.getAuthorCtx().getSet()
                    .stream()
                    .map(x -> new AttribStatusDist<Author>(x))
                    .collect(Collectors.toSet());
            doc.append("authorInfo", set);
        }
        if (categoryNameList != null) {
            // maintain for backward compatibility
            doc.append("categoryNameList", categoryNameList);
            Set<AttribStatusDist<Category>> set = report.getCategoryCtx().getSet()
                    .stream()
                    .map(x -> new AttribStatusDist<Category>(x))
                    .collect(Collectors.toSet());
            doc.append("categoryInfo", set);
        }
        if (deviceNameList != null && !deviceNameList.isEmpty()) {
            // maintain for backward compatibility
            doc.append("deviceNameList", deviceNameList);
            Set<AttribStatusDist<Device>> set = report.getDeviceCtx().getSet()
                    .stream()
                    .map(x -> new AttribStatusDist<Device>(x))
                    .collect(Collectors.toSet());
            doc.append("deviceInfo", set);
        }
        if (exceptionNameList != null)
            doc.append("exceptions", exceptionNameList);

        reportCollection.updateOne(new Document("_id", reportId), new Document("$set", doc));
        insertUpdateSystemAttribute(report);
    }

    private void insertUpdateSystemAttribute(Report report) {
        List<SystemEnvInfo> sysEnvInfoList = report.getSystemEnvInfo();
        Document doc;
        for (SystemEnvInfo attr : sysEnvInfoList) {
            doc = new Document("project", projectId).append("report", reportId).append("name", attr.getName());
            Document envSingle = environmentCollection.find(doc).first();
            if (envSingle == null) {
                doc.append("value", attr.getValue());
                environmentCollection.insertOne(doc);
            } else {
                ObjectId id = envSingle.getObjectId("_id");
                doc = new Document("_id", id).append("value", attr.getValue());
                environmentCollection.updateOne(new Document("_id", id), new Document("$set", doc));
            }
        }
    }

    private Set<String> getCollectionKeys(Map<String, ObjectId> collection) {
        if (collection == null || collection.isEmpty())
            return null;
        return collection.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    public Set<ObjectId> getCollectionValues(Map<String, ObjectId> collection) {
        if (collection == null || collection.isEmpty())
            return null;
        return collection.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }

    private void updateTestDesc(Test test) {
        Document doc = new Document("description", test.getDescription());
        testCollection.updateOne(new Document("_id", test.getInfoMap().get(ID_KEY)), new Document("$set", doc));
    }

    private void updateTestChildrenCount(Test test) {
        Document doc = new Document("childNodesLength", test.getChildren().size());
        testCollection.updateOne(new Document("_id", test.getInfoMap().get(ID_KEY)), new Document("$set", doc));
    }

    public Observer<AttributeEntity> getAttributesObserver() {
        return new Observer<AttributeEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(AttributeEntity value) {
                if (value.getAuthor() != null)
                    assignAttribute(value.getTest(), value.getAuthor(), authorNameObjectIdCollection,
                            authorCollection,
                            authorContext);
                if (value.getCategory() != null)
                    assignAttribute(value.getTest(), value.getCategory(), categoryNameObjectIdCollection,
                            categoryCollection,
                            categoryContext);
                if (value.getDevice() != null)
                    assignAttribute(value.getTest(), value.getDevice(), deviceNameObjectIdCollection,
                            deviceCollection,
                            deviceContext);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    public <T extends NamedAttribute> void assignAttribute(Test test, NamedAttribute attribute,
            Map<String, ObjectId> attrObjectIdCollection, MongoCollection<Document> mongoCollection,
            NamedAttributeContextManager<T> attributeContext) {
        Document doc;
        if (!attrObjectIdCollection.containsKey(attribute.getName())) {
            doc = new Document("report", reportId).append("project", projectId).append("name", attribute.getName());

            FindIterable<Document> iterable = mongoCollection.find(doc);
            Document docAttribute = iterable.first();

            if (docAttribute != null) {
                attrObjectIdCollection.put(attribute.getName(), docAttribute.getObjectId("_id"));
            } else {
                doc = new Document("testIdList", Arrays.asList(test.getInfoMap().get(ID_KEY)))
                        .append("testNameList", Arrays.asList(test.getName()))
                        .append("testLength", 1)
                        .append("project", projectId).append("report", reportId)
                        .append("name", attribute.getName())
                        .append("timeTaken", Long.valueOf(0));
                mongoCollection.insertOne(doc);
                ObjectId categoryId = MongoUtil.getId(doc);
                attrObjectIdCollection.put(attribute.getName(), categoryId);
            }
        } else {
            ObjectId id = attrObjectIdCollection.get(attribute.getName());
            // default length
            int testLength = 1;
            if (attributeContext != null) {
                Optional<NamedAttributeContext<T>> context = attributeContext.getSet()
                        .stream().filter(x -> x.getAttr().getName().equals(attribute.getName())).findFirst();
                if (context.isPresent())
                    testLength = context.get().size() + 1;
            }
            mongoCollection.updateOne(new Document("_id", id), new Document("$push",
                    new Document("testIdList", test.getInfoMap().get(ID_KEY))
                            .append("testNameList", test.getName())));
            mongoCollection.updateOne(new Document("_id", id),
                    new Document("$set", new Document("testLength", testLength)));
        }
    }

    public Observer<LogEntity> getLogObserver() {
        return new Observer<LogEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(LogEntity value) {
                onLogAdded(value.getTest(), value.getLog());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    public synchronized void onLogAdded(Test test, Log log) {
        Document doc = new Document("test", test.getInfoMap().get(ID_KEY))
                .append("project", projectId).append("report", reportId)
                .append("testName", test.getName())
                .append("sequence", log.getSeq())
                .append("status", log.getStatus().toLower())
                .append("timestamp", log.getTimestamp())
                .append("mediaCount", log.hasMedia() ? 1 : 0)
                .append("details", log.getDetails());

        if (log.hasException())
            doc.append("exception", log.getException().getName())
                    .append("stacktrace", log.getException().getStackTrace());

        if (log.hasMedia() && ((ScreenCapture) log.getMedia()).getBase64() != null)
            doc.append("details", log.getDetails() + ((ScreenCapture) log.getMedia()).getBase64());

        logCollection.insertOne(doc);
        ObjectId logId = MongoUtil.getId(doc);
        log.getInfoMap().put(ID_KEY, logId);

        // check for exceptions..
        for (ExceptionInfo ex : test.getExceptions()) {
            ObjectId exceptionId;
            doc = new Document("report", reportId)
                    .append("project", projectId)
                    .append("name", ex.getName());
            FindIterable<Document> iterable = exceptionCollection.find(doc);
            Document docException = iterable.first();

            // check if a matching exception name is available in 'Exception'
            // collection
            // (MongoDB)
            // if a matching exception name is found, associate with this
            // exception's
            // ObjectId
            if (!exceptionNameObjectIdCollection.containsKey(ex.getName())) {
                if (docException != null) {
                    exceptionNameObjectIdCollection.put(ex.getName(), docException.getObjectId("_id"));
                } else {
                    doc = new Document("project", projectId)
                            .append("report", reportId)
                            .append("name", ex.getName())
                            .append("stacktrace", ex.getStackTrace())
                            .append("testCount", 0);
                    exceptionCollection.insertOne(doc);
                    exceptionId = MongoUtil.getId(doc);
                    docException = exceptionCollection.find(new Document("_id", exceptionId)).first();
                    exceptionNameObjectIdCollection.put(ex.getName(), exceptionId);
                }
            }
            Integer testCount = ((Integer) (docException.get("testCount"))) + 1;
            doc = new Document("testCount", testCount);
            exceptionCollection.updateOne(new Document("_id", docException.getObjectId("_id")),
                    new Document("$set", doc));
            doc = new Document("exception", exceptionNameObjectIdCollection.get(ex.getName()))
            		.append("exceptionName", ex.getName());
            testCollection.updateOne(new Document("_id", test.getInfoMap().get(ID_KEY)), new Document("$set", doc));
            updateTestDesc(test);
        }
        endTestRecursive(test);
    }

    private void endTestRecursive(Test test) {
        Document doc = new Document("status", test.getStatus().toLower())
                .append("endTime", test.getEndTime())
                .append("duration", test.timeTaken())
                .append("leaf", test.isLeaf())
                .append("mediaCount", !test.getMedia().isEmpty() ? test.getMedia().size() : 0)
                .append("childNodesLength", test.hasChildren() ? test.getChildren().size() : 0)
                .append("logCount", test.hasAnyLog() ? test.getLogs().size() + test.getGeneratedLog().size() : 0)
                .append("categorized", test.hasAttributes())
                .append("description", test.getDescription());

        if (test.hasCategory()) {
            List<String> categoryNameList = test.getCategorySet().stream().map(NamedAttribute::getName)
                    .collect(Collectors.toList());
            doc.append("categoryNameList", categoryNameList);
        }
        if (test.hasDevice()) {
            List<String> deviceNameList = test.getDeviceSet().stream().map(NamedAttribute::getName)
                    .collect(Collectors.toList());
            doc.append("deviceNameList", deviceNameList);
        }
        if (test.hasAuthor()) {
            List<String> authorNameList = test.getAuthorSet().stream().map(NamedAttribute::getName)
                    .collect(Collectors.toList());
            doc.append("authorNameList", authorNameList);
        }

        testCollection.updateOne(new Document("_id", test.getInfoMap().get(ID_KEY)), new Document("$set", doc));
        if (test.getLevel() > 0)
            endTestRecursive(test.getParent());
    }

    public Observer<MediaEntity> getMediaObserver() {
        return new Observer<MediaEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(MediaEntity value) {
                try {
                    if (value.getTest() != null)
                        onScreenCaptureAdded(value.getTest(), (ScreenCapture) value.getMedia());
                    if (value.getLog() != null)
                        onScreenCaptureAdded(value.getLog(), (ScreenCapture) value.getMedia());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    public void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException {
        screenCapture.getInfoMap().put(TEST_ID_KEY, test.getInfoMap().get(ID_KEY));
        saveScreenCapture(test, screenCapture);
    }

    public void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException {
        screenCapture.getInfoMap().put(LOG_ID_KEY, log.getInfoMap().get(ID_KEY));
        saveScreenCapture(log, screenCapture);
    }

    private void saveScreenCapture(MetaDataStorable store, ScreenCapture screenCapture) throws IOException {
        if (mediaStorageHandler == null) {
            KlovMedia klovMedia = new KlovMedia(projectId, reportId, mediaCollection);
            mediaStorageHandler = new KlovMediaStorageHandler(url, klovMedia);
        }
        mediaStorageHandler.saveScreenCapture(store, screenCapture);
    }

    public Observer<ReportEntity> getReportObserver() {
        return new Observer<ReportEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ReportEntity value) {
                flush(value);
            }

            // TODO: replace with DoFinally
            @Override
            public void onError(Throwable e) {
                mongoClient.close();
            }

            @Override
            public void onComplete() {
                mongoClient.close();
            }
        };
    }

    public Observer<TestEntity> getTestObserver() {
        return new Observer<TestEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(TestEntity value) {
                if (!initiated.get()) {
                    start();
                    initiated.compareAndSet(false, true);
                }
                if (!value.getRemoved())
                    onTestStarted(value.getTest());
                else
                    onTestRemoved(value.getTest());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    private final void start() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoDatabase db = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
        initCollections(db);
        setupProject();
    }

    private void onTestStarted(Test test) {
        Document doc = new Document("project", projectId)
                .append("report", reportId)
                .append("reportName", reportName)
                .append("reportSeq", reportSeq)
                .append("level", test.getLevel())
                .append("name", test.getName())
                .append("status", test.getStatus().toLower())
                .append("description", test.getDescription())
                .append("startTime", test.getStartTime())
                .append("endTime", test.getEndTime())
                .append("bdd", test.isBDD())
                .append("leaf", test.isLeaf())
                .append("childNodesLength", test.getChildren().size())
                .append("mediaCount", 0)
                .append("childNodesLength", 0)
                .append("logCount", 0);

        if (test.isBDD())
            doc.append("bddType", test.getBddType().getSimpleName());

        if (test.getParent() != null) {
            doc.append("parent", test.getParent().getInfoMap().get(ID_KEY))
                    .append("parentName", test.getParent().getName());
            updateTestChildrenCount(test.getParent());
            updateTestDesc(test.getParent());
        }

        testCollection.insertOne(doc);
        ObjectId testId = MongoUtil.getId(doc);
        test.getInfoMap().put(ID_KEY, testId);
    }

    private void onTestRemoved(Test test) {
        Document doc = new Document("_id", test.getInfoMap().get(ID_KEY));
        testCollection.deleteOne(doc);
        test.getLogs().forEach(this::removeLog);
        if (test.hasAnyLog()) {
            doc = new Document("test", test.getInfoMap().get(ID_KEY));
            logCollection.deleteMany(doc);
        }
        if (test.hasAttributes())
            removeFromAttributes(test);
        if (test.hasChildren())
            test.getChildren().forEach(this::onTestRemoved);
    }

    private void removeLog(Log log) {
        Document doc = new Document("_id", log.getInfoMap().get(ID_KEY));
        logCollection.deleteOne(doc);
        if (log.hasMedia())
            removeMedia(log.getMedia());
    }

    private void removeMedia(Media m) {
        Document doc = new Document("_id", m.getInfoMap().get(ID_KEY));
        mediaCollection.deleteOne(doc);
    }

    private void removeFromAttributes(Test t) {
        Document match, update;
        for (Author x : t.getAuthorSet()) {
            match = new Document("_id", authorNameObjectIdCollection.get(x.getName()));
            update = new Document("testIdList", t.getInfoMap().get(ID_KEY));
            authorCollection.updateOne(match, new BasicDBObject("$pull", update));
        }
        for (Category x : t.getCategorySet()) {
            match = new Document("_id", categoryNameObjectIdCollection.get(x.getName()));
            update = new Document("testIdList", t.getInfoMap().get(ID_KEY));
            categoryCollection.updateOne(match, new BasicDBObject("$pull", update));
        }
        for (Device x : t.getDeviceSet()) {
            match = new Document("_id", deviceNameObjectIdCollection.get(x.getName()));
            update = new Document("testIdList", t.getInfoMap().get(ID_KEY));
            deviceCollection.updateOne(match, new BasicDBObject("$pull", update));
        }
    }
}
