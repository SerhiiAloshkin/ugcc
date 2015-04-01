package ua.coral.ugcc.common.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import ua.coral.ugcc.common.dto.impl.News;

public class OneLastNewsBinder extends Composite {
    interface OneLastNewsBinderUiBinder extends UiBinder<HTMLPanel, OneLastNewsBinder> {
    }

    private static OneLastNewsBinderUiBinder ourUiBinder = GWT.create(OneLastNewsBinderUiBinder.class);

    @UiField
    Label dateNews;
    @UiField
    Button linkNews;

    public OneLastNewsBinder(final News news) {
        initWidget(ourUiBinder.createAndBindUi(this));

        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd-MMM-yyyy");

        dateNews.setText(dateTimeFormat.format(news.getCreateDate()));
        linkNews.setText(news.getTitle());
    }
}