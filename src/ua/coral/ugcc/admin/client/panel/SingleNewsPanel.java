package ua.coral.ugcc.admin.client.panel;

import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.client.UGCCConstants;
import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SingleNewsPanel extends VLayout {

    private static final int MEMBERS_MARGIN = 20;
    private static final int NEWS_TOP_HEIGHT = 23;

    private UGCCConstants constants = GWT.create(UGCCConstants.class);

    private News news;
    private ListNewsView.Presenter presenter;

    private final Button editButton = new Button(constants.edit());
    private final Button removeButton = new Button(constants.remove());

    public SingleNewsPanel(final News news, final ListNewsView.Presenter presenter) {
        super();
        this.news = news;
        this.presenter = presenter;

        init();
    }

    private void init() {
        setWidth100();
        setHeight100();

        final HLayout hLayout = new HLayout();
        hLayout.setWidth100();
        hLayout.setHeight(NEWS_TOP_HEIGHT);
        hLayout.setAlign(Alignment.RIGHT);
        hLayout.setMembersMargin(MEMBERS_MARGIN);

        editButton.setVisible(false);
        removeButton.setVisible(false);

        editButton.setIcon("edit-icon.png");
        removeButton.setIcon("Gnome-Edit-Delete-64.png");

        hLayout.addMember(editButton);
        hLayout.addMember(removeButton);

        final HTMLFlow flow = new HTMLFlow();
        flow.setWidth100();
        flow.setContents(news.getContent());
        addMouseOverHandler(new NewsMouseOverHandler());
        addMouseOutHandler(new NewsMouseOutHandler());

        removeButton.addClickHandler(new NewsRemoveClickHandler());

        addMember(hLayout);
        addMember(flow);
    }

    private void setVisibleButtons(final boolean isVisible) {
        editButton.setVisible(isVisible);
        removeButton.setVisible(isVisible);
    }

    private class NewsMouseOverHandler implements MouseOverHandler {
        @Override
        public void onMouseOver(final MouseOverEvent mouseOverEvent) {
            setVisibleButtons(true);
        }
    }

    private class NewsMouseOutHandler implements MouseOutHandler {
        @Override
        public void onMouseOut(final MouseOutEvent mouseOutEvent) {
            setVisibleButtons(false);
        }
    }

    private class NewsRemoveClickHandler implements ClickHandler {
        @Override
        public void onClick(final ClickEvent clickEvent) {
            presenter.removeNews(news);
            setVisible(false);
            clear();
        }
    }
}
