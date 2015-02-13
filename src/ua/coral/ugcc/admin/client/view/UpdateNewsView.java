package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.View;

public interface UpdateNewsView extends View<UpdateNewsView.Presenter> {

    void setNews(News news);

    public interface Presenter {
        void updateNews(News news);

        void listNews();
    }
}
