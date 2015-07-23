package ua.coral.ugcc.user.client.event;

import com.google.gwt.event.shared.GwtEvent;
import ua.coral.ugcc.user.client.event.handler.OpenedNewsEventHandler;

public class OpenedNewsEvent extends GwtEvent<OpenedNewsEventHandler> {

    public static Type<OpenedNewsEventHandler> TYPE = new Type<>();

    private Long newsId;

    public OpenedNewsEvent(final Long newsId) {
        this.newsId = newsId;
    }

    public Long getNewsId() {
        return newsId;
    }

    @Override
    public Type<OpenedNewsEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final OpenedNewsEventHandler handler) {
        handler.onOpenedNews(this);
    }
}
