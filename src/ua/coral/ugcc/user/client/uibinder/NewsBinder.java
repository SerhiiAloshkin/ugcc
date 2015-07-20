package ua.coral.ugcc.user.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.user.client.view.ListNewsView;

import java.util.List;

public class NewsBinder extends Composite {
    interface NewsBinderUiBinder extends UiBinder<HTMLPanel, NewsBinder> {
    }

    private static NewsBinderUiBinder ourUiBinder = GWT.create(NewsBinderUiBinder.class);

    @UiField
    RollNewsBinder roll;

    private final ListNewsView.Presenter presenter;

    public NewsBinder(final List<News> newsList, final ListNewsView.Presenter presenter) {
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
        roll.setNews(newsList, presenter);
    }
}