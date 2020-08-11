package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.ScreenCapture;

public class HttpMediaManagerImplKlov
        implements
            MediaStorage {

    private static final Logger logger = Logger.getLogger(HttpMediaManagerImplKlov.class.getName());
    private static final String ROUTE = "api/files";

    private String host;

    @Override
    public void init(String host) throws IOException {
        this.host = host;
        if (host.lastIndexOf('/') != host.length() - 1) {
            this.host = host + "/";
        }
    }

    @Override
    public void storeMedia(Media m) throws IOException {
        if (m.getPath() == null)
            return;
        if (m instanceof ScreenCapture && ((ScreenCapture) m).getBase64() != null) {
            return;
        }
        File f = new File(m.getPath());
        if (m.getResolvedPath() != null)
            f = new File(m.getResolvedPath());
        if (!f.exists()) {
            throw new IOException("The system cannot find the file specified " + m.getPath());
        }

        HttpPost post = new HttpPost(host + ROUTE);
        post.addHeader("Connection", "keep-alive");
        post.addHeader("User-Agent", "Mozilla/5.0");
        post.addHeader("Accept", "application/json");

        String ext = FileUtil.getExtension(m.getPath());

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart("name",
                new StringBody(Calendar.getInstance().getTime().getTime() + "." + ext, ContentType.TEXT_PLAIN));
        builder.addPart("id",
                new StringBody(m.getInfoMap().get(ExtentKlovReporter.ID_KEY).toString(), ContentType.TEXT_PLAIN));
        builder.addPart("reportId",
                new StringBody(m.getInfoMap().get(ExtentKlovReporter.REPORT_ID_KEY).toString(),
                        ContentType.TEXT_PLAIN));
        builder.addPart("testId",
                new StringBody(m.getInfoMap().get(ExtentKlovReporter.TEST_ID_KEY).toString(), ContentType.TEXT_PLAIN));
        builder.addPart("f", new FileBody(f));
        post.setEntity(builder.build());

        Object logId = m.getInfoMap().get(ExtentKlovReporter.LOG_ID_KEY);
        if (logId != null)
            builder.addPart("logId", new StringBody(logId.toString(), ContentType.TEXT_PLAIN));

        HttpClient client = null;
        if (host.toLowerCase().startsWith("https")) {
            SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
            try {
                sslContextBuilder.loadTrustMaterial(new TrustSelfSignedStrategy());
                @SuppressWarnings("deprecation")
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                        sslContextBuilder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                client = HttpClients.custom().setSSLSocketFactory(
                        sslsf).build();
            } catch (Exception e) {

            }
        } else {
            client = HttpClientBuilder.create().build();
        }

        HttpResponse response = client.execute(post);
        int responseCode = response.getStatusLine().getStatusCode();
        boolean isValid = isResponseValid(responseCode);

        if (!isValid)
            logger.warning("Unable to upload file to server " + m.getPath());
    }

    private boolean isResponseValid(int responseCode) {
        return 200 <= responseCode && responseCode <= 399;
    }

}
