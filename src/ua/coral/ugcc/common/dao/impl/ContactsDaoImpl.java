package ua.coral.ugcc.common.dao.impl;

import ua.coral.ugcc.common.dao.ContactsDao;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.mapper.Mapper;

import java.util.LinkedList;
import java.util.List;

import com.google.appengine.api.datastore.Entity;

public class ContactsDaoImpl extends DaoImpl implements ContactsDao {

    @Override
    protected Class<Contact> getEntityClass() {
        return Contact.class;
    }

    @Override
    protected String getId() {
        return "id";
    }

    @Override
    public List<Contact> listContacts() {
        final List<Entity> entities = getEntities(0);
        final List<Contact> contactsList = new LinkedList<>();

        for (final Entity entity : entities) {
            contactsList.add(Mapper.getObject(Contact.class, entity));
        }

        return contactsList;
    }

    @Override
    public Contact getContactById(final Long contactId) {
        return Mapper.getObject(Contact.class, getEntityById(contactId));
    }
}
