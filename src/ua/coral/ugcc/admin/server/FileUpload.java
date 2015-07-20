package ua.coral.ugcc.admin.server;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.MediaByteArraySource;
import com.google.gdata.data.photos.PhotoEntry;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ua.coral.ugcc.common.dao.TokenDao;
import ua.coral.ugcc.common.dao.impl.TokenDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileUpload extends HttpServlet {

    private static final String CLIENT_ID = "318390621392-obe6svtjgf95l8vu9m80f5t8hgp3gb2e.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "vgHUSYD3qu_69WLXjPid7u02";
    private final TokenDao tokenDao = new TokenDaoImpl();

    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        ServletFileUpload upload = new ServletFileUpload();

        try {
            FileItemIterator iter = upload.getItemIterator(request);

            while (iter.hasNext()) {
                FileItemStream item = iter.next();

                InputStream stream = item.openStream();

                // Process the input stream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[8192];
                while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, len);
                }

                final PicasawebService service = new PicasawebService("UgccSite");
                service.setOAuth2Credentials(authorize());
                service.setConnectTimeout(0);
                service.setReadTimeout(0);
                final MediaByteArraySource fileSource = new MediaByteArraySource(out.toByteArray(), "image/jpeg");
                final URL url = new URL("https://picasaweb.google.com/data/feed/api/user/106984661305245773041/albumid/6136237850628250209");
                PhotoEntry returnedPhoto = service.insert(url, PhotoEntry.class, fileSource);
                String href = returnedPhoto.getHtmlLink().getHref();

                if (returnedPhoto.getMediaContents().size() > 0) {
                    href = returnedPhoto.getMediaContents().get(0).getUrl();
                }
                response.setContentType("text/plain");
                response.getWriter().print(href);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Authorizes the installed application to access user's protected data.
     */
    private Credential authorize() throws Exception {
        final HttpTransport httpTransport = new NetHttpTransport();
        final JsonFactory jsonFactory = new JacksonFactory();

        GoogleCredential credential = new GoogleCredential.Builder()
                .setClientSecrets(CLIENT_ID,
                        CLIENT_SECRET)
                .setJsonFactory(jsonFactory)
                .setTransport(httpTransport)
                .build()
                .setAccessToken(tokenDao.getLastToken().getAccessToken());

        return credential;
    }
}