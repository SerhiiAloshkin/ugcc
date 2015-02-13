package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.View;

public interface AddNewsView extends View<AddNewsView.Presenter> {

    public interface Presenter {
        void addNews(News news);

        void listNews();
    }
}
