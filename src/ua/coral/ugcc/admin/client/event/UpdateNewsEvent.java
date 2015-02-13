package ua.coral.ugcc.admin.client.event;

import ua.coral.ugcc.admin.client.event.handler.UpdateNewsEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateNewsEvent extends GwtEvent<UpdateNewsEventHandler> {

    public static Type<UpdateNewsEventHandler> TYPE = new Type<>();

    private final Long newsId;

    public UpdateNewsEvent(final Long newsId) {
        this.newsId = newsId;
    }

    public Long getNewsId() {
        return newsId;
    }

    @Override
    public Type<UpdateNewsEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final UpdateNewsEventHandler handler) {
        handler.onUpdateNews(this);
    }
}
