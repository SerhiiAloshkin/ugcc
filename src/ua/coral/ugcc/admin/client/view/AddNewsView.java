package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.presenter.DefaultPresenter;
import ua.coral.ugcc.common.view.View;

public interface AddNewsView extends View<AddNewsView.Presenter> {

    public interface Presenter extends DefaultPresenter {
        void addNews(News news);

        void listNews();
    }
}
