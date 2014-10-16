package ua.coral.ugcc.user.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.user.client.UgccService;

import java.util.Collections;
import java.util.List;

public class UgccServiceImpl extends RemoteServiceServlet implements UgccService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    @Override
    public void addNews(final News news) {
    }

    @Override
    public void removeNews(final News news) {
    }

    @Override
    public void updateNews(final News news) {
    }

    @Override
    public List<News> listNews() {
        return Collections.emptyList();
    }
}