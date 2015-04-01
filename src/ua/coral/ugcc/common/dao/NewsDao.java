package ua.coral.ugcc.common.dao;

import ua.coral.ugcc.common.dto.impl.News;

import java.io.Serializable;
import java.util.List;

public interface NewsDao extends Dao, Serializable {

    List<News> listNews();

    List<News> listNews(int limit);

    News getNewsById(Long newsId);
}
