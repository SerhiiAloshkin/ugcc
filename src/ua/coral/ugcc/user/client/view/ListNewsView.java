package ua.coral.ugcc.user.client.view;

import ua.coral.ugcc.common.presenter.DefaultPresenter;
import ua.coral.ugcc.common.view.View;

public interface ListNewsView extends View<ListNewsView.Presenter> {

    interface Presenter extends DefaultPresenter {

        void listNews();

        void countNews();

        void openNews(Long newsId);
    }
}
