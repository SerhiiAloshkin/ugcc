package ua.coral.ugcc.common.services.impl;

import ua.coral.ugcc.common.dao.NewsDao;
import ua.coral.ugcc.common.dao.impl.NewsDaoImpl;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.services.Service;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public abstract class AbstractServiceImpl extends RemoteServiceServlet implements RemoteService, Service {

    private NewsDao dao = new NewsDaoImpl();

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

    @Override
    public int countNews() {
        return dao.count();
    }
}
