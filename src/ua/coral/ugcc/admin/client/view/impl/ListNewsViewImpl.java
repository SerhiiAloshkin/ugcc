package ua.coral.ugcc.admin.client.view.impl;

import ua.coral.ugcc.admin.client.panel.PagingPanel;
import ua.coral.ugcc.admin.client.panel.RollNewsPanel;
import ua.coral.ugcc.admin.client.panel.SingleNewsPanel;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.client.UGCCConstants;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.impl.AbstractViewImpl;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ListNewsViewImpl extends AbstractViewImpl<ListNewsView.Presenter> implements ListNewsView {

    private static final int ADD_NEWS_HEIGHT = 155;
    private static final int MEMBERS_MARGIN = 20;
    private static final int MESSAGE_BOX_HEIGHT = 115;
    private static final int MESSAGE_BOX_WIDTH = 360;

    private RichTextEditor richTextEditor;

    private Button button;

    private News currentNews;

    private RollNewsPanel rollNewsPanel;

    private PagingPanel pagingPanel;

    private UGCCConstants constants = GWT.create(UGCCConstants.class);

    public ListNewsViewImpl() {
        super();
    }

    @Override
    protected void postInit() {
        wireGUIEvents();
    }

    private void wireGUIEvents() {
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventAddNewButtonClicked();
            }
        });
    }

    @Override
    protected Widget getContentPanel() {
        final VLayout layout = new VLayout();
        layout.setWidth("*");
        layout.setMembersMargin(MEMBERS_MARGIN);

        rollNewsPanel = new RollNewsPanel(getPresenter());
        pagingPanel = new PagingPanel(getPresenter());

        layout.addMember(rollNewsPanel);
        layout.addMember(pagingPanel);
        layout.addMember(getAddNewsPanel());

        return layout;
    }

    private Widget getAddNewsPanel() {
        final VLayout layout = new VLayout();
        layout.setHeight(ADD_NEWS_HEIGHT);
        layout.setWidth100();
        layout.setMembersMargin(MEMBERS_MARGIN);

        richTextEditor = new RichTextEditor();
        richTextEditor.setHeight(ADD_NEWS_HEIGHT);
        richTextEditor.setOverflow(Overflow.HIDDEN);
        richTextEditor.setCanDragResize(true);
        richTextEditor.setShowEdges(true);

        final HLayout hLayout = new HLayout();
        hLayout.setAlign(Alignment.RIGHT);

        button = new Button(constants.add());
        hLayout.addMember(button);

        layout.addMember(richTextEditor);
        layout.addMember(hLayout);

        return layout;
    }

    @Override
    public void eventAddNewsSuccessful() {
        rollNewsPanel.addMember(new SingleNewsPanel(currentNews, getPresenter()));
    }

    @Override
    public void eventUpdateNewsSuccessful() {
        messageBox("Update contact successful");
    }

    @Override
    public void eventRemoveNewsSuccessful() {
//        messageBox("Remove contact successful");
        getPresenter().countNews();
    }

    @Override
    public void eventUpdateNewsFailed() {
        messageBox("Update contact failed");
    }

    @Override
    public void eventAddNewsFailed(Throwable caught) {
        messageBox(caught.getMessage());
    }

    @Override
    public void eventRemoveNewsFailed() {
        messageBox("Remove contact failed");
    }

    @Override
    public void eventListNewsFailed() {
        messageBox("Unable to get contact list");
    }

    @Override
    public void eventListRetrievedFromService(final List<News> listNews) {
        for (final News news : listNews) {
            rollNewsPanel.addMember(new SingleNewsPanel(news, getPresenter()));
        }
    }

    @Override
    public void eventCountNewsFailed() {
        messageBox("Load count news failed");
    }

    @Override
    public void eventCountNewsSuccessful(final int count) {
        pagingPanel.setItemsCount(count);
        pagingPanel.buildPanel();
    }

    public void eventAddNewButtonClicked() {
        final News news = new News();
        news.setId(System.currentTimeMillis());
        news.setContent(richTextEditor.getValue());
        currentNews = news;
        getPresenter().addNews(currentNews);
        richTextEditor.setValue("");

        getPresenter().countNews();
    }

    private void messageBox(final String message) {
        final Window winModal = new Window();
        winModal.setWidth(MESSAGE_BOX_WIDTH);
        winModal.setHeight(MESSAGE_BOX_HEIGHT);
        winModal.setTitle("Modal Window");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.centerInPage();
        winModal.addItem(new Label(message));
        winModal.show();
    }
}
