package ua.coral.ugcc.admin.client.uibinder.news;

import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.client.Locale;
import ua.coral.ugcc.common.component.GrowlUtils;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.utils.StringUtils;

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
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;

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
    private final Locale constants = GWT.create(Locale.class);

    public SingleNewsBinder(final News news, final ListNewsView.Presenter presenter) {
        this.news = news;
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
        title.setText(news.getTitle());
        content.setHTML(getShortContent());
    }

    @UiHandler("editBtn")
    public void onEditNews(final ClickEvent event) {
        presenter.updateNews(news.getId());
    }

    @UiHandler("removeBtn")
    public void onRemoveNews(final ClickEvent event) {
        final String msg = constants.newsRecordRemovingMessage();
        Bootbox.confirm(msg, new ConfirmCallback() {
            @Override
            public void callback(final boolean result) {
                removeNews(result);
            }
        });
    }

    private void removeNews(final boolean result) {
        if (result) {
            presenter.removeNews(news, this);
        }
    }

    public void removeSuccessful() {
        GrowlUtils.showMessage(constants.newsRecordRemoving(), constants.newsRecordRemovingSuccessful(),
                GrowlType.SUCCESS, IconType.SMILE_O);
    }

    public void removeFailure(final Throwable caught) {
        GrowlUtils.showMessage(constants.newsRecordRemovingFailed(), caught.getMessage(), GrowlType.DANGER,
                IconType.FLASH);
    }

    private String getShortContent() {
        return StringUtils.abbreviate(new HTML(news.getContent()).getText(), 100);
    }
}