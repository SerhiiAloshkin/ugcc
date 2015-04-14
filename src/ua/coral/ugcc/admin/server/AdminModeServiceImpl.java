package ua.coral.ugcc.admin.server;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.photos.PhotoEntry;
import ua.coral.ugcc.admin.client.AdminModeService;
import ua.coral.ugcc.common.services.impl.AbstractServiceImpl;

import java.io.File;
import java.net.URL;

public class AdminModeServiceImpl extends AbstractServiceImpl implements AdminModeService {

    private String token;

    @Override
    public String sendFile(final String fileName) {
        final PicasawebService service = new PicasawebService("ugcc-site-1");
        try {
            service.setUserCredentials("sem.aleshkin@gmail.com", "hpocvqxahlyslceu");
            final URL fileUrl = new URL(fileName);
            final MediaFileSource fileSource = new MediaFileSource(new File(fileUrl.toURI()), "image/jpeg");

//            final MediaByteArraySource fileSource = new MediaByteArraySource(IOUtils.toByteArray(file.openStream()),
//                    "image/jpeg");
            final URL url = new URL("https://picasaweb.google.com/data/feed/api/user/106984661305245773041/albumid/6136237850628250209");
            PhotoEntry returnedPhoto = service.insert(url, PhotoEntry.class, fileSource);
            return returnedPhoto.getHtmlLink().getHref();
//            final URL url = new URL("https://picasaweb.google.com/data/feed/api/user/106984661305245773041?kind=album");
//            AlbumEntry myAlbum = new AlbumEntry();
//
//            myAlbum.setTitle(new PlainTextConstruct("Trip to France"));
//            myAlbum.setDescription(new PlainTextConstruct("My recent trip to France was delightful!"));
//
//            AlbumEntry insertedEntry = service.insert(url, myAlbum);

//            final UserFeed myUserFeed = service.getFeed(url, UserFeed.class);
//
////            final PhotoEntry photo = service.insert(url, PhotoEntry.class, fileSource);
////            return photo.getHtmlLink().getHref();
//            for (final GphotoEntry entry : myUserFeed.getEntries()) {
//                if (entry.getGphotoId().equals("6136237850628250209")) {
//                    return entry.getFeedLink().getHref();
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setAuthToken(final String token) {
        this.token = token;
    }
}