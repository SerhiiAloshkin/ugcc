package ua.coral.ugcc.admin.client.uibinder.media;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;


public class MediaBinder extends Composite {
    interface MediaBinderUiBinder extends UiBinder<HTMLPanel, MediaBinder> {
    }

    private static MediaBinderUiBinder ourUiBinder = GWT.create(MediaBinderUiBinder.class);

    public MediaBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}