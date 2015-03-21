package ua.coral.ugcc.admin.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

public class SingleNewsBinder extends Composite {
    interface SingleNewsBinderUiBinder extends UiBinder<HTMLPanel, SingleNewsBinder> {
    }

    private static SingleNewsBinderUiBinder ourUiBinder = GWT.create(SingleNewsBinderUiBinder.class);

    @UiField
    Heading title;
    @UiField
    HTML content;
    @UiField
    Button editBtn;
    @UiField
    Button removeBtn;

    private final News news;
    private final ListNewsView.Presenter presenter;

    public SingleNewsBinder(final News news, final ListNewsView.Presenter presenter) {
        this.news = news;
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
        title.setText(news.getTitle());
        content.setHTML(news.getContent());
    }

    @UiHandler("editBtn")
    public void onEditNews(final ClickEvent event) {
        presenter.updateNews(news.getId());
    }

    @UiHandler("removeBtn")
    public void onRemoveNews(final ClickEvent event) {
        final String msg = "Ви дійсно бажаєте видалити новину? Після видалення, запис повернути буде неможливо.";
        Bootbox.confirm(msg, new ConfirmCallback() {
            @Override
            public void callback(final boolean result) {
                presenter.removeNews(news);
            }
        });
    }
}