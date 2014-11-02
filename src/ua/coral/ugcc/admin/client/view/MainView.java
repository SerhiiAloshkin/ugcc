package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainView extends IsWidget {

    void init();

    void setPresenter(Presenter presenter);

    void setContent(String content);

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
