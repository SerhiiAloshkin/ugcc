package ua.coral.ugcc.admin.client.uibinder.news;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

public class RollNewsBinder extends Composite {
    interface RollNewsBinderUiBinder extends UiBinder<HTMLPanel, RollNewsBinder> {
    }

    private static RollNewsBinderUiBinder ourUiBinder = GWT.create(RollNewsBinderUiBinder.class);

    @UiField
    HTMLPanel newsList;

    public RollNewsBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setNews(final List<News> newsList, final ListNewsView.Presenter presenter) {
        for (final News news : newsList) {
            this.newsList.add(new SingleNewsBinder(news, presenter));
        }
    }
}