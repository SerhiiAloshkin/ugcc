package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.View;

import java.util.List;

public interface ListNewsView extends View<ListNewsView.Presenter> {

    void loadNews(List<News> newsList);

    public interface Presenter {
        void listNews();
        void countNews();

        void addNews();
        void removeNews(News news);
        void updateNews(final News news);
    }
}
