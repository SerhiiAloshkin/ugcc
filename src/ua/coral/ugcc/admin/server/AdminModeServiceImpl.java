package ua.coral.ugcc.admin.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.coral.ugcc.admin.client.AdminModeService;
import ua.coral.ugcc.common.dao.NewsDao;
import ua.coral.ugcc.common.dao.impl.NewsDaoImpl;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.LinkedList;
import java.util.List;

public class AdminModeServiceImpl extends RemoteServiceServlet implements AdminModeService {

    private NewsDao dao = new NewsDaoImpl();

    @Override
    public String getMessage(final String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    @Override
    public void addNews(final News news) {
        dao.add(news);
    }

    @Override
    public void removeNews(final News news) {
        dao.remove(news);
    }

    @Override
    public void updateNews(final News news) {
        dao.update(news);
    }

    @Override
    public List<News> listNews() {
        return new LinkedList<>(dao.listNews());
    }
}