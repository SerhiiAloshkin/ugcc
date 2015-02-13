package ua.coral.ugcc.admin.client.view.impl;

import ua.coral.ugcc.admin.client.view.AddNewsView;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.impl.AbstractViewImpl;

import java.util.Date;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class AddNewsViewImpl extends AbstractViewImpl<AddNewsView.Presenter> implements AddNewsView {

    private static final int MEMBERS_MARGIN = 20;

    private RichTextEditor richTextEditor = new RichTextEditor();
    private Button addNewsbutton;
    private Button cancelButton;

    @Override
    protected void postInit() {

    }

    @Override
    protected Widget getContentPanel() {
        final VLayout layout = new VLayout();
        layout.setHeight100();
        layout.setWidth100();
        layout.setMembersMargin(MEMBERS_MARGIN);

        richTextEditor.setHeight100();
        richTextEditor.setOverflow(Overflow.HIDDEN);
        richTextEditor.setCanDragResize(true);
        richTextEditor.setShowEdges(true);

        final HLayout hLayout = new HLayout();
        hLayout.setAlign(Alignment.RIGHT);
        hLayout.setMembersMargin(MEMBERS_MARGIN);

        addNewsbutton = new Button(constants.add());
        addNewsbutton.addClickHandler(new AddNewsClickHandler());

        cancelButton = new Button(constants.cancel());
        cancelButton.addClickHandler(new CancelAddClickHandler());

        hLayout.addMember(addNewsbutton);
        hLayout.addMember(cancelButton);

        layout.addMember(richTextEditor);
        layout.addMember(hLayout);

        return layout;
    }

    private void doAddNews() {
        final News news = new News();
        news.setId(System.currentTimeMillis());
        news.setContent(richTextEditor.getValue());
        news.setCreateDate(new Date());

        getPresenter().addNews(news);
    }

    private void doCancelAdd() {
        getPresenter().listNews();
    }

    private class AddNewsClickHandler implements ClickHandler {
        @Override
        public void onClick(final ClickEvent event) {
            doAddNews();
        }
    }

    private class CancelAddClickHandler implements ClickHandler {
        @Override
        public void onClick(final ClickEvent event) {
            doCancelAdd();
        }
    }
}
