package ua.coral.ugcc.common.dao.impl;

import com.google.appengine.api.datastore.Entity;
import ua.coral.ugcc.common.dao.NewsDao;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.mapper.Mapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
        return listNews(0);
    }

    @Override
    public List<News> listNews(final int limit) {
        if (limit < 0) {
            return Collections.emptyList();
        }
        final List<Entity> entities = getEntities(limit);
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
