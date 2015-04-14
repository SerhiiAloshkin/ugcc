package ua.coral.ugcc.admin.server;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.MediaByteArraySource;
import com.google.gdata.data.photos.PhotoEntry;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileUpload extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletFileUpload upload = new ServletFileUpload();

        try {
            FileItemIterator iter = upload.getItemIterator(request);

            while (iter.hasNext()) {
                FileItemStream item = iter.next();

                String name = item.getFieldName();
                InputStream stream = item.openStream();


                // Process the input stream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[8192];
                while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, len);
                }

                final PicasawebService service = new PicasawebService("ugcc-site-1");
                service.setUserCredentials("sem.aleshkin@gmail.com", "hpocvqxahlyslceu");
                final MediaByteArraySource fileSource = new MediaByteArraySource(out.toByteArray(), "image/jpeg");
                final URL url = new URL("https://picasaweb.google.com/data/feed/api/user/106984661305245773041/albumid/6136237850628250209");
                PhotoEntry returnedPhoto = service.insert(url, PhotoEntry.class, fileSource);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}