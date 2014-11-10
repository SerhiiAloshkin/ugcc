package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.View;

import java.util.List;

import com.google.gwt.place.shared.Place;

public interface ListNewsView extends View<ListNewsView.Presenter> {

    void eventListNewsFailed();

    void eventListRetrievedFromService(List<News> result);

    void eventAddNewsFailed(Throwable caught);

    void eventAddNewsSuccessful();

    void eventRemoveNewsFailed();

    void eventRemoveNewsSuccessful();

    void eventUpdateNewsFailed();

    void eventUpdateNewsSuccessful();

    public interface Presenter {
        void goTo(Place place);

        void listNews();

        void addNews(News news);

        void removeNews(News news);

        void updateNews(final News news);
    }
}
