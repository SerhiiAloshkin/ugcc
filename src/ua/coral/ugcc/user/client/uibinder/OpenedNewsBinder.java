package ua.coral.ugcc.user.client.uibinder;

import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.gwtbootstrap3.client.ui.Heading;

public class OpenedNewsBinder extends Composite {
    interface EditNewsBinderUiBinder extends UiBinder<HTMLPanel, OpenedNewsBinder> {
    }

    private static EditNewsBinderUiBinder ourUiBinder = GWT.create(EditNewsBinderUiBinder.class);

    @UiField
    HTML body;
    @UiField
    Heading title;

    public OpenedNewsBinder(final News news) {
        initWidget(ourUiBinder.createAndBindUi(this));

        title.setText(news.getTitle());
        body.setHTML(news.getContent());
    }
}