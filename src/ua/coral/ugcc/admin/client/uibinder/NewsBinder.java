package ua.coral.ugcc.admin.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

public class NewsBinder extends Composite {
    interface NewsBinderUiBinder extends UiBinder<HTMLPanel, NewsBinder> {
    }

    private static NewsBinderUiBinder ourUiBinder = GWT.create(NewsBinderUiBinder.class);

    @UiField
    RollNewsBinder roll;

    public NewsBinder(final List<News> newsList, final ListNewsView.Presenter presenter) {
        initWidget(ourUiBinder.createAndBindUi(this));
        roll.setNews(newsList, presenter);
    }
}