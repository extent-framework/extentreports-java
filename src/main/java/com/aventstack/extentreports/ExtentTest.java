package com.aventstack.extentreports;

import java.io.Serializable;
import java.util.Arrays;

import com.aventstack.extentreports.gherkin.entity.IGherkinFormatterModel;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.ExceptionInfo;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.RunResult;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.service.ExceptionInfoService;
import com.aventstack.extentreports.model.service.LogService;
import com.aventstack.extentreports.model.service.TestService;

import lombok.Getter;

/**
 * Defines a test. You can add logs, snapshots, assign author and categories to
 * a test and its children.
 * 
 * <p>
 * The below log types will all be logged with <code>Status.PASS</code>:
 * </p>
 * 
 * <pre>
 * test.log(Status.PASS, "details");
 * test.pass("details");
 * test.pass(MarkupHelper.createCodeBlock(code));
 * </pre>
 * 
 * <p>
 * A few notes:
 * </p>
 * 
 * <ul>
 * <li>Tests started with the <code>createTest</code> method are parent-level,
 * always level 0</li>
 * <li>Tests started with the <code>createNode</code> method are children,
 * always level 1 and greater</li>
 * </ul>
 */
@Getter
public class ExtentTest implements RunResult, Serializable {
    private static final long serialVersionUID = 5846031786305901993L;

    /**
     * An instance of {@link ExtentReports} to which this {@link ExtentTest}
     * belongs
     */
    private transient ExtentReports extent;

    /**
     * Internal model
     */
    private Test model;

    /**
     * Creates a BDD style parent test representing one of the
     * {@link IGherkinFormatterModel} classes. This method would ideally be used
     * for creating the parent, ie {@link Feature).
     * 
     * <ul>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * extent.createTest(Feature.class, "Feature Name", "Description");
     * </pre>
     * 
     * @param extent
     *            An {@link ExtentReports} object
     * @param type
     *            A {@link IGherkinFormatterModel} type
     * @param name
     *            Test name
     * @param description
     *            Test description
     */
    ExtentTest(ExtentReports extent, Class<? extends IGherkinFormatterModel> type, String name, String description) {
        model = TestService.createTest(type, name, description);
        this.extent = extent;
    }

    /**
     * Create a test with description
     * 
     * @param extent
     *            An {@link ExtentReports} object
     * @param testName
     *            Test name
     * @param description
     *            Test description
     */
    ExtentTest(ExtentReports extent, String testName, String description) {
        this(extent, null, testName, description);
    }

    /**
     * Creates a BDD-style node with description representing one of the
     * {@link IGherkinFormatterModel} classes:
     * 
     * <ul>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.createNode(Scenario.class, "bddNode", "description");
     * </pre>
     * 
     * @param type
     *            A {@link IGherkinFormatterModel} type
     * @param name
     *            Name of node
     * @param description
     *            A short description
     * 
     * @return {@link ExtentTest} object
     */
    public synchronized ExtentTest createNode(Class<? extends IGherkinFormatterModel> type, String name,
            String description) {
        ExtentTest t = new ExtentTest(extent, type, name, description);
        TestService.addNode(t.getModel(), model);
        extent.onNodeCreated(t.getModel(), model);
        return t;
    }

    /**
     * Creates a node with description
     * 
     * @param name
     *            Name of node
     * @param description
     *            A short description
     * 
     * @return {@link ExtentTest} object
     */
    public synchronized ExtentTest createNode(String name, String description) {
        ExtentTest t = new ExtentTest(extent, name, description);
        TestService.addNode(t.getModel(), model);
        extent.onNodeCreated(t.getModel(), model);
        return t;
    }

    /**
     * Creates a BDD-style node representing one of the
     * {@link IGherkinFormatterModel} classes such as:
     * 
     * <ul>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.createNode(Scenario.class, "bddNode");
     * </pre>
     * 
     * @param type
     *            A {@link IGherkinFormatterModel} type
     * @param name
     *            Name of node
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest createNode(Class<? extends IGherkinFormatterModel> type, String name) {
        return createNode(type, name, null);
    }

    /**
     * Creates a BDD-style node with description using name of the Gherkin model
     * such as:
     * 
     * <ul>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.createNode(new GherkinKeyword("Scenario"), "bddTest", "description");
     * </pre>
     * 
     * @param gherkinKeyword
     *            Name of the {@link GherkinKeyword}
     * @param name
     *            Name of node
     * @param description
     *            A short description
     * 
     * @return {@link ExtentTest}
     */
    public ExtentTest createNode(GherkinKeyword gherkinKeyword, String name, String description) {
        return createNode(gherkinKeyword.getKeyword().getClass(), name, description);
    }

    /**
     * Creates a BDD-style node using name of the Gherkin model such as:
     * 
     * <ul>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
     * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.createNode(new GherkinKeyword("Scenario"), "bddTest");
     * </pre>
     * 
     * @param gherkinKeyword
     *            Name of the {@link GherkinKeyword}
     * @param name
     *            Name of node
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest createNode(GherkinKeyword gherkinKeyword, String name) {
        return createNode(gherkinKeyword, name, null);
    }

    /**
     * Creates a node
     * 
     * @param name
     *            Name of node
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest createNode(String name) {
        return createNode(name, null);
    }

    /**
     * Logs an event with {@link Status}, details and a media object:
     * {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.log(Status.FAIL, "details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param status
     *            {@link Status}
     * @param details
     *            Details
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest log(Status status, String details, Media media) {
        Log log = LogService.createLog(model, status, details);
        LogService.addMedia(log, media);
        TestService.computeTestStatus(model);
        extent.onLogCreated(log, model);
        return this;
    }

    /**
     * Logs an event with {@link Status} and details
     * 
     * @param status
     *            {@link Status}
     * @param details
     *            Details
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest log(Status status, String details) {
        return log(status, details, null);
    }

    /**
     * Logs an event with {@link Status} and custom {@link Markup} such as:
     * 
     * <ul>
     * <li>Code block</li>
     * <li>Label</li>
     * <li>Table</li>
     * </ul>
     * 
     * @param status
     *            {@link Status}
     * @param markup
     *            {@link Markup}
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest log(Status status, Markup markup) {
        String details = markup.getMarkup();
        return log(status, details);
    }

    /**
     * Logs an event with {@link Status}, an exception and a media object:
     * {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
     * test.log(Status.FAIL, exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param status
     *            {@link Status}
     * @param t
     *            {@link Throwable}
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest log(Status status, Throwable t, Media media) {
        ExceptionInfo exceptionInfo = ExceptionInfoService.createExceptionInfo(t);
        Log log = LogService.createLog(model, status, exceptionInfo);
        LogService.addMedia(log, media);
        return this;
    }

    /**
     * Logs an event with {@link Status} and exception
     * 
     * @param status
     *            {@link Status}
     * @param t
     *            {@link Throwable}
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest log(Status status, Throwable t) {
        return log(status, t, null);
    }

    /**
     * Logs an <code>Status.INFO</code> event with details and a media object:
     * {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.info("details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param details
     *            Details
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest info(String details, Media media) {
        log(Status.INFO, details, media);
        return this;
    }

    /**
     * Logs an event with <code>Status.INFO</code> with details
     * 
     * @param details
     *            Details
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest info(String details) {
        return info(details, null);
    }

    /**
     * Logs an <code>Status.INFO</code> event with an exception and a media
     * object: {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
     * test.info(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t
     *            {@link Throwable}
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest info(Throwable t, Media media) {
        log(Status.INFO, t, media);
        return this;
    }

    /**
     * Logs an event with <code>Status.INFO</code> and exception
     * 
     * @param t
     *            {@link Throwable}
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest info(Throwable t) {
        return info(t, null);
    }

    /**
     * Logs an event with <code>Status.INFO</code> and custom {@link Markup}
     * such as:
     * 
     * <ul>
     * <li>Code block</li>
     * <li>Label</li>
     * <li>Table</li>
     * </ul>
     * 
     * @param m
     *            {@link Markup}
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest info(Markup m) {
        log(Status.INFO, m);
        return this;
    }

    /**
     * Logs an <code>Status.PASS</code> event with details and a media object:
     * {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param details
     *            Details
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest pass(String details, Media media) {
        log(Status.PASS, details, media);
        return this;
    }

    /**
     * Logs an event <code>Status.PASS</code> with details
     * 
     * @param details
     *            Details
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest pass(String details) {
        return pass(details, null);
    }

    /**
     * Logs an <code>Status.PASS</code> event with an exception and a media
     * object: {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
     * test.pass(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t
     *            {@link Throwable}
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest pass(Throwable t, Media media) {
        log(Status.PASS, t, media);
        return this;
    }

    /**
     * Logs an event with <code>Status.PASS</code> and exception
     * 
     * @param t
     *            {@link Throwable}
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest pass(Throwable t) {
        return pass(t, null);
    }

    /**
     * Logs an event with <code>Status.PASS</code> and custom {@link Markup}
     * such as:
     * 
     * <ul>
     * <li>Code block</li>
     * <li>Label</li>
     * <li>Table</li>
     * </ul>
     * 
     * @param m
     *            {@link Markup}
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest pass(Markup m) {
        log(Status.PASS, m);
        return this;
    }

    /**
     * Logs an <code>Status.FAIL</code> event with details and a media object:
     * {@link ScreenCapture}
     * 
     * @param details
     *            Details
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest fail(String details, Media media) {
        log(Status.FAIL, details, media);
        return this;
    }

    /**
     * Logs an event <code>Status.FAIL</code> with details
     * 
     * @param details
     *            Details
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest fail(String details) {
        return fail(details, null);
    }

    /**
     * Logs an <code>Status.FAIL</code> event with an exception and a media
     * object: {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
     * test.fail(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t
     *            {@link Throwable}
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest fail(Throwable t, Media media) {
        log(Status.FAIL, t, media);
        return this;
    }

    /**
     * Logs an event with <code>Status.FAIL</code> and exception
     * 
     * @param t
     *            {@link Throwable}
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest fail(Throwable t) {
        return fail(t, null);
    }

    /**
     * Logs an event with <code>Status.FAIL</code> and custom {@link Markup}
     * such as:
     * 
     * <ul>
     * <li>Code block</li>
     * <li>Label</li>
     * <li>Table</li>
     * </ul>
     * 
     * @param m
     *            {@link Markup}
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest fail(Markup m) {
        log(Status.FAIL, m);
        return this;
    }

    /**
     * Logs an <code>Status.WARNING</code> event with an exception and a media
     * object: {@link ScreenCapture}
     * 
     * @param details
     *            Details
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest warning(String details, Media media) {
        log(Status.WARNING, details, media);
        return this;
    }

    /**
     * Logs an event <code>Status.WARNING</code> with details
     * 
     * @param details
     *            Details
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest warning(String details) {
        return warning(details, null);
    }

    /**
     * Logs an <code>Status.WARNING</code> event with an exception and a media
     * object: {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
     * test.warning(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t
     *            {@link Throwable}
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest warning(Throwable t, Media media) {
        log(Status.WARNING, t, media);
        return this;
    }

    /**
     * Logs an event with <code>Status.WARNING</code> and exception
     * 
     * @param t
     *            {@link Throwable}
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest warning(Throwable t) {
        return warning(t, null);
    }

    /**
     * Logs an event with <code>Status.WARNING</code> and custom {@link Markup}
     * such as:
     * 
     * <ul>
     * <li>Code block</li>
     * <li>Label</li>
     * <li>Table</li>
     * </ul>
     * 
     * @param m
     *            {@link Markup}
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest warning(Markup m) {
        log(Status.WARNING, m);
        return this;
    }

    /**
     * 
     * @param details
     *            Details
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest skip(String details, Media media) {
        log(Status.SKIP, details, media);
        return this;
    }

    /**
     * Logs an event <code>Status.SKIP</code> with details
     * 
     * @param details
     *            Details
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest skip(String details) {
        return skip(details, null);
    }

    /**
     * Logs an <code>Status.SKIP</code> event with an exception and a media
     * object: {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
     * test.skip(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t
     *            {@link Throwable}
     * @param provider
     *            A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link ExtentTest} object
     */
    public ExtentTest skip(Throwable t, Media media) {
        log(Status.SKIP, t, media);
        return this;
    }

    /**
     * Logs an event with <code>Status.SKIP</code> and exception
     * 
     * @param t
     *            {@link Throwable}
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest skip(Throwable t) {
        return skip(t, null);
    }

    /**
     * Logs an event with <code>Status.SKIP</code> and custom {@link Markup}
     * such as:
     * 
     * <ul>
     * <li>Code block</li>
     * <li>Label</li>
     * <li>Table</li>
     * </ul>
     * 
     * @param m
     *            {@link Markup}
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest skip(Markup m) {
        log(Status.SKIP, m);
        return this;
    }

    /**
     * Assigns a category or group
     * 
     * @param category
     *            Category name
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest assignCategory(String... category) {
        if (category == null || category.length == 0)
            return this;
        Arrays.stream(category)
                .forEach(x -> {
                    Category c = new Category(x.replaceAll("\\s+", ""));
                    model.getCategorySet().add(c);
                    extent.onCategoryAdded(c, model);
                });
        return this;
    }

    /**
     * Assigns an author
     * 
     * @param author
     *            Author name
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest assignAuthor(String... author) {
        if (author == null || author.length == 0)
            return this;
        Arrays.stream(author)
                .forEach(x -> {
                    Author a = new Author(x.replaceAll("\\s+", ""));
                    model.getAuthorSet().add(a);
                    extent.onAuthorAdded(a, model);
                });
        return this;
    }

    /**
     * Assign a device
     * 
     * @param device
     *            Device name
     * 
     * @return {@link ExtentTest} object
     */
    public ExtentTest assignDevice(String... device) {
        if (device == null || device.length == 0)
            return this;
        Arrays.stream(device)
                .forEach(x -> {
                    Device d = new Device(x.replaceAll("\\s+", ""));
                    model.getDeviceSet().add(d);
                    extent.onDeviceAdded(d, model);
                });
        return this;
    }

    @Override
    public Status getStatus() {
        return TestService.getTestStatus(model);
    }
    /*
     * public ExtentTest addScreenCaptureFromPath(String imagePath, String
     * title) throws IOException { if (imagePath == null || imagePath.isEmpty())
     * throw new IllegalArgumentException("imagePath cannot be null or empty");
     * ScreenCapture screenCapture = new ScreenCapture();
     * screenCapture.setPath(imagePath); if (title != null) {
     * screenCapture.setName(title); } extent.addScreenCapture(test,
     * screenCapture); return addScreenCapture(screenCapture); }
     * 
     * private ExtentTest addScreenCapture(ScreenCapture screenCapture) {
     * model.getScreenCaptureContext().add(screenCapture); return this; }
     * 
     * public ExtentTest addScreenCaptureFromPath(String imagePath) throws
     * IOException { return addScreenCaptureFromPath(imagePath, null); }
     * 
     * public ExtentTest addScreenCaptureFromBase64String(String s, String
     * title) { ScreenCapture screenCapture = new ScreenCapture();
     * screenCapture.setBase64String(s); screenCapture.setName(title); try {
     * extent.addScreenCapture(test, screenCapture); } catch (IOException e) { }
     * 
     * return addScreenCapture(screenCapture); }
     * 
     * @Override public ExtentTest addScreenCaptureFromBase64String(String s) {
     * return addScreenCaptureFromBase64String(s, null); }
     * 
     * void setUseManualConfiguration(Boolean b) {
     * getModel().setUsesManualConfiguration(b); }
     */

}