package ua.coral.ugcc.common.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.dto.impl.Token;

import java.util.List;

public interface ServiceAsync {

    void addNews(News news, AsyncCallback<Void> callback);

    void removeNews(News news, AsyncCallback<Void> callback);

    void updateNews(News news, AsyncCallback<Void> callback);

    void listNews(AsyncCallback<List<News>> callback);

    void listNews(Integer limit, AsyncCallback<List<News>> callback);

    void countNews(AsyncCallback<Integer> callback);

    void getNewsById(Long newsId, AsyncCallback<News> callback);

    void addToken(Token token, AsyncCallback<Void> callback);

    void getToken(AsyncCallback<Token> callback);

    void addContact(Contact contacts, AsyncCallback<Void> callback);

    void removeContact(Contact contacts, AsyncCallback<Void> callback);

    void updateContact(Contact contacts, AsyncCallback<Void> callback);

    void listContacts(AsyncCallback<List<Contact>> callback);

    void getContactById(Long contactId, AsyncCallback<Contact> callback);
}
