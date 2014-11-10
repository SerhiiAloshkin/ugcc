package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.View;

import java.util.List;

import com.google.gwt.place.shared.Place;

public interface ListNewsView extends View<ListNewsView.Presenter> {

    void eventListContactsFailed();

    void eventListRetrievedFromService(List<News> result);

    void eventAddContactFailed(Throwable caught);

    void eventAddContactSuccessful();

    void eventRemoveContactFailed();

    void eventRemoveContactSuccessful();

    void eventUpdateContactFailed();

    void eventUpdateSuccessful();

    public interface Presenter {
        void goTo(Place place);

        void listNews();

        void addNews(News news);

        void removeNews(News news);

        void updateNews(final News news);
    }
}
