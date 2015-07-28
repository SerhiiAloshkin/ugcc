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
        return abbreviate(new HTML(news.getContent()).getText(), 100);
    }

    private String abbreviate(final String str, final int maxWidth) {
        if (str == null) {
            return null;
        }
        return abbreviate(str, 0, maxWidth, 0);
    }

    private String abbreviate(final String str, final int index, final int maxWidth, int counter) {
        int foundIndex = getFoundIndex(str, index);
        counter++;
        if (counter < maxWidth) {
            return abbreviate(str, foundIndex, maxWidth, counter);
        }
        return str.substring(0, foundIndex);
    }

    private int getFoundIndex(final String str, final int index) {
        int foundIndex = str.indexOf(" ", index);
        if (str.charAt(foundIndex + 1) == ' ') {
            return getFoundIndex(str, foundIndex + 1);
        }
        return foundIndex + 1;
    }
}