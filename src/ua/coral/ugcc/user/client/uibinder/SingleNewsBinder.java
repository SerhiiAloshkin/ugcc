package ua.coral.ugcc.user.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.client.ui.Button;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.utils.StringUtils;
import ua.coral.ugcc.user.client.view.ListNewsView;

public class SingleNewsBinder extends Composite {
    interface SingleNewsBinderUiBinder extends UiBinder<HTMLPanel, SingleNewsBinder> {
    }

    private static SingleNewsBinderUiBinder ourUiBinder = GWT.create(SingleNewsBinderUiBinder.class);

    @UiField
    Button linkedTitle;
    @UiField
    HTML content;

    private final News news;
    private final ListNewsView.Presenter presenter;

    public SingleNewsBinder(final News news, final ListNewsView.Presenter presenter) {
        this.news = news;
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
        linkedTitle.setText(news.getTitle());
        content.setHTML(getShortContent());

    }

    @UiHandler("linkedTitle")
    public void onSelectedNews(final ClickEvent event) {
        presenter.openNews(news.getId());
    }

    private String getShortContent() {
        return StringUtils.abbreviate(new HTML(news.getContent()).getText(), 100);
    }


}