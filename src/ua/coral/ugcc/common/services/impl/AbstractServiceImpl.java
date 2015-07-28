package ua.coral.ugcc.common.services.impl;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ua.coral.ugcc.common.dao.ContactsDao;
import ua.coral.ugcc.common.dao.NewsDao;
import ua.coral.ugcc.common.dao.TokenDao;
import ua.coral.ugcc.common.dao.impl.ContactsDaoImpl;
import ua.coral.ugcc.common.dao.impl.NewsDaoImpl;
import ua.coral.ugcc.common.dao.impl.TokenDaoImpl;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.dto.impl.Token;
import ua.coral.ugcc.common.services.Service;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractServiceImpl extends RemoteServiceServlet implements RemoteService, Service {

    private final NewsDao newsDao = new NewsDaoImpl();
    private final TokenDao tokenDao = new TokenDaoImpl();
    private final ContactsDao contactsDao = new ContactsDaoImpl();

    @Override
    public void addNews(final News news) {
        newsDao.add(news);
    }

    @Override
    public void removeNews(final News news) {
        newsDao.remove(news);
    }

    @Override
    public void updateNews(final News news) {
        newsDao.update(news);
    }

    @Override
    public List<News> listNews() {
        return new LinkedList<>(newsDao.listNews());
    }

    @Override
    public List<News> listNews(Integer limit) {
        return new LinkedList<>(newsDao.listNews(limit));
    }

    @Override
    public int countNews() {
        return newsDao.count();
    }

    @Override
    public News getNewsById(final Long newsId) {
        return newsDao.getNewsById(newsId);
    }

    @Override
    public void addToken(final Token token) {
        tokenDao.add(token);
    }

    @Override
    public Token getToken() {
        return tokenDao.getLastToken();
    }

    @Override
    public void addContact(final Contact contacts) {
        contactsDao.add(contacts);
    }

    @Override
    public void removeContact(final Contact contacts) {
        contactsDao.remove(contacts);
    }

    @Override
    public void updateContact(final Contact contacts) {
        contactsDao.update(contacts);
    }

    @Override
    public List<Contact> listContacts() {
        return new LinkedList<>(contactsDao.listContacts());
    }

    @Override
    public Contact getContactById(final Long contactId) {
        return contactsDao.getContactById(contactId);
    }
}
