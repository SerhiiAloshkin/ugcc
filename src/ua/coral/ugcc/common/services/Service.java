package ua.coral.ugcc.common.services;

import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

public interface Service {

    void addNews(News news);

    void removeNews(News news);

    void updateNews(News news);

    List<News> listNews();

    int countNews();

    News getNewsById(Long newsId);
}
