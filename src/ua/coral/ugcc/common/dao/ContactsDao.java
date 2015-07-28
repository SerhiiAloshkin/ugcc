package ua.coral.ugcc.common.dao;

import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.dto.impl.News;

import java.io.Serializable;
import java.util.List;

public interface ContactsDao extends Dao, Serializable {

    List<Contact> listContacts();

    Contact getContactById(Long contactId);
}
