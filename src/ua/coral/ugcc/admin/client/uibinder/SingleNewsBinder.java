package ua.coral.ugcc.admin.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

public class SingleNewsBinder extends Composite {
    interface SingleNewsBinderUiBinder extends UiBinder<HTMLPanel, SingleNewsBinder> {
    }

    private static SingleNewsBinderUiBinder ourUiBinder = GWT.create(SingleNewsBinderUiBinder.class);

    @UiField
    DivElement newsBody;
    @UiField
    Button editBtn;

    private final News news;
    private final ListNewsView.Presenter presenter;

    public SingleNewsBinder(final News news, final ListNewsView.Presenter presenter) {
        this.news = news;
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
        this.newsBody.setInnerHTML(news.getContent());
    }

    @UiHandler("editBtn")
    public void onEditNews(final ClickEvent event) {
        presenter.updateNews(news.getId());
    }
}