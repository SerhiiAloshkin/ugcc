package ua.coral.ugcc.admin.client.panel;

import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.client.UGCCConstants;
import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Label;
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

    private final News news;
    private final ListNewsView.Presenter presenter;
    private final Button editButton = new Button(constants.edit());
    private final Button removeButton = new Button(constants.remove());
    private final HTMLFlow flow = new HTMLFlow();

    public SingleNewsPanel(final News news, final ListNewsView parent) {
        super();
        this.news = news;
        this.presenter = parent.getPresenter();

        init();
    }

    private void init() {
        setWidth100();
        setHeight100();
        setStyleName("seperated");
        setPadding(15);

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

        flow.setWidth100();
        flow.setContents(news.getContent());
        addMouseOverHandler(new NewsMouseOverHandler());
        addMouseOutHandler(new NewsMouseOutHandler());

        editButton.addClickHandler(new NewsEditClickHandler());
        removeButton.addClickHandler(new NewsRemoveClickHandler());

        addMember(hLayout);
        addMember(flow);
        addMember(getBottomPanel());
    }

    private Widget getBottomPanel() {
        final HLayout hLayout = new HLayout();
        hLayout.setWidth100();
        hLayout.setHeight(NEWS_TOP_HEIGHT);
        hLayout.setAlign(Alignment.RIGHT);
        hLayout.setMembersMargin(MEMBERS_MARGIN);

        final DateTimeFormat format = DateTimeFormat.getFormat("dd-MM-yyyy HH:mm");
        final Label createdDate = new Label("Created: " + format.format(news.getCreateDate()));
        createdDate.setStyleName("createdDate");
        createdDate.setWrap(false);

        hLayout.addMember(createdDate);

        return hLayout;
    }

    private void setVisibleButtons(final boolean isVisible) {
        editButton.setVisible(isVisible);
        removeButton.setVisible(isVisible);
    }

    private class NewsMouseOverHandler implements MouseOverHandler {
        @Override
        public void onMouseOver(final MouseOverEvent mouseOverEvent) {
            setVisibleButtons(true);
            flow.setBackgroundColor("#BDE5B7");
        }
    }

    private class NewsMouseOutHandler implements MouseOutHandler {
        @Override
        public void onMouseOut(final MouseOutEvent mouseOutEvent) {
            setVisibleButtons(false);
            flow.setBackgroundColor("#FFFFFF");
        }
    }

    private class NewsRemoveClickHandler implements ClickHandler {
        @Override
        public void onClick(final ClickEvent clickEvent) {
            SC.ask("Removing news", "Are you sure??", new BooleanCallback() {
                @Override
                public void execute(final Boolean value) {
                    if (value != null && value) {
                        presenter.removeNews(news);
                        setVisible(false);
                        clear();
                    }
                }
            });
        }
    }

    private class NewsEditClickHandler implements ClickHandler {
        @Override
        public void onClick(final ClickEvent event) {
            presenter.updateNews(news);
        }
    }
}
