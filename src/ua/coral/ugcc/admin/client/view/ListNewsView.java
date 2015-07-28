package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.admin.client.uibinder.news.SingleNewsBinder;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.presenter.DefaultPresenter;
import ua.coral.ugcc.common.view.View;

import java.util.List;

public interface ListNewsView extends View<ListNewsView.Presenter> {

    void loadNews(List<News> newsList);

    interface Presenter extends DefaultPresenter {
        void listNews();
        void countNews();

        void addNews();

        void removeNews(News news, SingleNewsBinder view);

        void updateNews(Long newsId);
    }
}
