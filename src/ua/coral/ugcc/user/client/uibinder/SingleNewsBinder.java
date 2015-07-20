package ua.coral.ugcc.user.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.client.ui.Button;
import ua.coral.ugcc.common.client.UGCCConstants;
import ua.coral.ugcc.common.dto.impl.News;
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
    private final UGCCConstants constants = GWT.create(UGCCConstants.class);

    public SingleNewsBinder(final News news, final ListNewsView.Presenter presenter) {
        this.news = news;
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
        linkedTitle.setText(news.getTitle());
        content.setHTML(news.getContent());
    }
}