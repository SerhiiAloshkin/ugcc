package ua.coral.ugcc.admin.server;

import com.google.gdata.client.Query;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.ILink;
import com.google.gdata.data.photos.AlbumFeed;
import ua.coral.ugcc.admin.client.AdminModeService;
import ua.coral.ugcc.common.services.impl.AbstractServiceImpl;

import java.net.URL;

public class AdminModeServiceImpl extends AbstractServiceImpl implements AdminModeService {

    private String token;

    @Override
    public String sendFile(final String fileName) {
        final PicasawebService service = new PicasawebService("ugcc-site-1");
        try {
            service.setUserCredentials("sem.aleshkin@gmail.com", "hpocvqxahlyslceu");

            URL feedUrl = new
                    URL("https://picasaweb.google.com/data/feed/api/user/106984661305245773041/albumid/6136237850628250209/photoid/" + fileName);

            Query query = new Query(feedUrl);

            AlbumFeed searchResultsFeed = service.query(query, AlbumFeed.class);
            if (!searchResultsFeed.getPhotoEntries().isEmpty()) {
                return searchResultsFeed.getPhotoEntries().get(0).getLink(FileUpload.REL, ILink.Type.HTML).getHref();
            }

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