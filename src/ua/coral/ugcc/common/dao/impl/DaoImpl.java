package ua.coral.ugcc.common.dao.impl;

import ua.coral.ugcc.common.dao.Dao;
import ua.coral.ugcc.common.dto.Dto;
import ua.coral.ugcc.common.mapper.Mapper;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

public abstract class DaoImpl implements Dao {

    private final DatastoreService dsService = DatastoreServiceFactory.getDatastoreService();

    public Entity getEntityById(final Long id) {
        final Query.Filter filter = new Query.FilterPredicate(getId(), Query.FilterOperator.EQUAL, id);
        final Query query = new Query(getEntityClass().getSimpleName()).setFilter(filter);
        return dsService.prepare(query).asSingleEntity();
    }

    public List<Entity> getEntities() {
        final Query query = new Query(getEntityClass().getSimpleName());
        return dsService.prepare(query).asList(FetchOptions.Builder.withDefaults());
    }

    @Override
    public void add(final Dto object) {
        dsService.put(Mapper.getEntity(object));
    }

    @Override
    public void remove(final Dto object) {
        final Entity entity = getEntityById(object.getId());
        dsService.delete(entity.getKey());
    }

    @Override
    public void update(final Dto object) {
        final Entity entity = getEntityById(object.getId());
        entity.setPropertiesFrom(Mapper.getEntity(object));
        dsService.put(entity);
    }

    protected abstract <E extends Dto> Class<E> getEntityClass();

    protected abstract String getId();
}
