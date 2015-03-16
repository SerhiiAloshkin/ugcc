package ua.coral.ugcc.admin.client.view.impl;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import ua.coral.ugcc.admin.client.view.UpdateNewsView;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.impl.AbstractViewImpl;

public class UpdateNewsViewImpl extends AbstractViewImpl<UpdateNewsView.Presenter> implements UpdateNewsView {

    private static final int MEMBERS_MARGIN = 20;

    private RichTextEditor richTextEditor = new RichTextEditor();
    private Button updateNewsButton;
    private Button cancelButton;
    private News news;

    @Override
    public void setNews(final News news) {
        this.news = news;
        richTextEditor.setValue(news.getContent());
    }

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
        richTextEditor.setID("richTextEditor");

        final HLayout hLayout = new HLayout();
        hLayout.setAlign(Alignment.RIGHT);
        hLayout.setMembersMargin(MEMBERS_MARGIN);

        updateNewsButton = new Button(constants.edit());
        updateNewsButton.addClickHandler(new UpdateNewsClickHandler());

        cancelButton = new Button(constants.cancel());
        cancelButton.addClickHandler(new CancelUpdateClickHandler());

        hLayout.addMember(updateNewsButton);
        hLayout.addMember(cancelButton);

        layout.addMember(richTextEditor);
        layout.addMember(hLayout);

        return layout;
    }

    private void doUpdateNews() {
        news.setContent(richTextEditor.getValue());
    }

    private void doCancelUpdate() {
        getPresenter().listNews();
    }

    private class UpdateNewsClickHandler implements ClickHandler {
        @Override
        public void onClick(final ClickEvent event) {
            doUpdateNews();
        }
    }

    private class CancelUpdateClickHandler implements ClickHandler {
        @Override
        public void onClick(final ClickEvent event) {
            doCancelUpdate();
        }
    }
}
