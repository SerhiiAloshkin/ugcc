package ua.coral.ugcc.admin.client.view.impl;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import ua.coral.ugcc.admin.client.AdminModeServiceDelegate;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.MainView;

import java.util.List;

public class MainViewImpl extends AbstractView implements MainView {

    private Element element = DOM.createSpan();

    private RichTextEditor richTextEditor;

    private Button button;

    private News currentNews;
    private AdminModeServiceDelegate serviceDelegate;

    private VLayout newsLayout;
    private HLayout mainLayout;

    public MainViewImpl() {
        super();
        getServiceDelegate().setGui(this);

        wireGUIEvents();
    }

    private AdminModeServiceDelegate getServiceDelegate() {
        if (serviceDelegate == null) {
            serviceDelegate = new AdminModeServiceDelegate();
        }
        return serviceDelegate;
    }

    private void wireGUIEvents() {
        button.addClickHandler(new ClickHandler(){
            @Override
            public void onClick(final ClickEvent event) {
                eventAddNewButtonClicked();
            }});
    }

    @Override
    protected HLayout getContent() {
        mainLayout = new HLayout();
        mainLayout.setWidth100();
        mainLayout.setHeight100();
        mainLayout.setMembersMargin(20);

        final VLayout vLayout = new VLayout();
        vLayout.setWidth(1000);
        vLayout.setHeight100();
        vLayout.setMembersMargin(20);
        vLayout.setAlign(Alignment.CENTER);

        vLayout.addMember(getTopPanel());
        vLayout.addMember(getCenterPanel());
        vLayout.addMember(getBottomPanel());

        mainLayout.addMember(getEmptyPanel());
        mainLayout.addMember(vLayout);
        mainLayout.addMember(getEmptyPanel());

        return mainLayout;
    }

    private Widget getEmptyPanel() {
        final Layout layout = new Layout();
        layout.setWidth("*");
        return layout;
    }

    private Widget getTopPanel() {
        final HLayout layout = new HLayout();
        layout.setShowEdges(true);
        layout.setHeight(150);
        return layout;
    }

    private Widget getCenterPanel() {
        final VLayout vLayout = new VLayout();
        vLayout.setWidth100();
        vLayout.setHeight100();
        vLayout.setMembersMargin(20);

        final HLayout hLayout = new HLayout();
        hLayout.setMembersMargin(20);

        hLayout.addMember(getMenuPanel());
        hLayout.addMember(getContentPanel());

        vLayout.addMember(hLayout);

        return vLayout;
    }

    private Widget getMenuPanel() {
        final VLayout layout = new VLayout();
        layout.setHeight100();
        layout.setWidth(250);
        return layout;
    }

    private Widget getContentPanel() {
        final VLayout layout = new VLayout();
        layout.setWidth("*");
        layout.setMembersMargin(20);

        layout.addMember(getNewsPanel());
        layout.addMember(getAddNewsPanel());

        return layout;
    }

    private Widget getNewsPanel() {
        if (newsLayout == null) {
            newsLayout = new VLayout();
        }
        newsLayout.setWidth100();
        newsLayout.setHeight100();
        newsLayout.setMembersMargin(20);

        getServiceDelegate().listNews();

        return newsLayout;
    }

    private Widget getNews(final News news) {
        final VLayout layout = new VLayout();
        layout.setWidth100();
        layout.setHeight100();

        final HLayout hLayout = new HLayout();
        hLayout.setWidth100();
        hLayout.setHeight(23);
        hLayout.setAlign(Alignment.RIGHT);
        hLayout.setMembersMargin(10);

        final Button editButton = new Button("Edit");
        final Button removeButton = new Button("Remove");

        editButton.setVisible(false);
        removeButton.setVisible(false);

        hLayout.addMember(editButton);
        hLayout.addMember(removeButton);

        final HTMLFlow flow = new HTMLFlow();
        flow.setWidth100();
        flow.setContents(news.getContent());
        layout.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(final MouseOverEvent event) {
                editButton.setVisible(true);
                removeButton.setVisible(true);
            }
        });
        layout.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(final MouseOutEvent event) {
                editButton.setVisible(false);
                removeButton.setVisible(false);
            }
        });

        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                getServiceDelegate().removeNews(news);
                layout.setVisible(false);
                layout.clear();
            }
        });

        layout.addMember(hLayout);
        layout.addMember(flow);

        return layout;
    }

    private Widget getAddNewsPanel() {
        final VLayout layout = new VLayout();
        layout.setHeight(155);
        layout.setWidth100();
        layout.setMembersMargin(20);

        richTextEditor = new RichTextEditor();
        richTextEditor.setHeight(155);
        richTextEditor.setOverflow(Overflow.HIDDEN);
        richTextEditor.setCanDragResize(true);
        richTextEditor.setShowEdges(true);

        final HLayout hLayout = new HLayout();
        hLayout.setAlign(Alignment.RIGHT);

        button = new Button("Add News");
        hLayout.addMember(button);

        layout.addMember(richTextEditor);
        layout.addMember(hLayout);

        return layout;
    }

    private Widget getBottomPanel() {
        final HLayout layout = new HLayout();
        layout.setBackgroundColor("#808080");
        layout.setHeight(75);
        return layout;
    }

    @Override
    public void setContent(String content) {
        element.setInnerText(content);
    }

    public void eventAddContactSuccessful() {
        newsLayout.addMember(getNews(currentNews));
    }

    public void eventUpdateSuccessful() {

    }

    public void eventRemoveContactSuccessful() {

    }

    public void eventUpdateContactFailed() {
        messageBox("Update contact failed");
    }

    public void eventAddContactFailed(Throwable caught) {
        messageBox(caught.getMessage());
    }

    public void eventRemoveContactFailed() {
        messageBox("Remove contact failed");
    }

    public void eventListContactsFailed() {
        messageBox("Unable to get contact list");
    }

    public void eventListRetrievedFromService(final List<News> listNews) {
        if (newsLayout == null) {
            newsLayout = new VLayout();
        }

        for (final News news : listNews) {
            newsLayout.addMember(getNews(news));
        }
    }

    public void eventAddNewButtonClicked() {
        final News news = new News();
        news.setId(System.currentTimeMillis());
        news.setContent(richTextEditor.getValue());
        currentNews = news;
        getServiceDelegate().addNews(currentNews);
        richTextEditor.setValue("");
    }

    private void messageBox(final String message) {
        final Window winModal = new Window();
        winModal.setWidth(360);
        winModal.setHeight(115);
        winModal.setTitle("Modal Window");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.centerInPage();
        winModal.addItem(new Label(message));
        winModal.show();
    }
}
