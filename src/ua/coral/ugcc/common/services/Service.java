package ua.coral.ugcc.common.services;

import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.dto.impl.Token;

import java.util.List;

public interface Service {

    void addNews(News news);

    void removeNews(News news);

    void updateNews(News news);

    List<News> listNews();

    List<News> listNews(Integer limit);

    int countNews();

    News getNewsById(Long newsId);

    void addToken(Token token);

    Token getToken();

    void addContact(Contact contacts);

    void removeContact(Contact contacts);

    void updateContact(Contact contacts);

    List<Contact> listContacts();

    Contact getContactById(Long contactId);
}
