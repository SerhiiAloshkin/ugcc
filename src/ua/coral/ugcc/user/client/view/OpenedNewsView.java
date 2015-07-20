package ua.coral.ugcc.user.client.view;

import ua.coral.ugcc.common.presenter.DefaultPresenter;
import ua.coral.ugcc.common.view.View;

public interface OpenedNewsView extends View<OpenedNewsView.Presenter> {

    interface Presenter extends DefaultPresenter {

        void openNews(Long newsId);
    }
}
