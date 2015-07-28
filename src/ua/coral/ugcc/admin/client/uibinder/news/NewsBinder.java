package ua.coral.ugcc.admin.client.uibinder.news;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.client.ui.Button;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

public class NewsBinder extends Composite {
    interface NewsBinderUiBinder extends UiBinder<HTMLPanel, NewsBinder> {
    }

    private static NewsBinderUiBinder ourUiBinder = GWT.create(NewsBinderUiBinder.class);

    @UiField
    RollNewsBinder roll;
    @UiField
    Button addBtn;

    private final ListNewsView.Presenter presenter;

    public NewsBinder(final List<News> newsList, final ListNewsView.Presenter presenter) {
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
        roll.setNews(newsList, presenter);
    }

    @UiHandler("addBtn")
    public void onAddNews(final ClickEvent event) {
        presenter.addNews();
    }
}