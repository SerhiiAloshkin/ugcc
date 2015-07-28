package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.admin.client.uibinder.news.EditNewsBinder;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.presenter.DefaultPresenter;
import ua.coral.ugcc.common.view.View;

public interface UpdateNewsView extends View<UpdateNewsView.Presenter> {

    void setNews(News news);

    interface Presenter extends DefaultPresenter {
        void updateNews(News news, EditNewsBinder editView);

        void listNews();
    }
}
