package ua.coral.ugcc.common.dao.impl;

import ua.coral.ugcc.common.dao.NewsDao;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.mapper.Mapper;

import java.util.LinkedList;
import java.util.List;

import com.google.appengine.api.datastore.Entity;

public class NewsDaoImpl extends DaoImpl implements NewsDao {

    @Override
    protected Class<News> getEntityClass() {
        return News.class;
    }

    @Override
    protected String getId() {
        return "id";
    }

    @Override
    public List<News> listNews() {
        final List<Entity> entities = getEntities();
        final List<News> newsList = new LinkedList<>();

        for (final Entity entity : entities) {
            newsList.add(Mapper.getObject(News.class, entity));
        }

        return newsList;
    }

    @Override
    public News getNewsById(final Long newsId) {
        return Mapper.getObject(News.class, getEntityById(newsId));
    }
}
