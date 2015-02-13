package ua.coral.ugcc.admin.client.view.impl;

import ua.coral.ugcc.admin.client.panel.PagingPanel;
import ua.coral.ugcc.admin.client.panel.RollNewsPanel;
import ua.coral.ugcc.admin.client.panel.SingleNewsPanel;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.view.impl.AbstractViewImpl;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ListNewsViewImpl extends AbstractViewImpl<ListNewsView.Presenter> implements ListNewsView {

    private static final int MEMBERS_MARGIN = 20;

    private RollNewsPanel rollNewsPanel;
    private PagingPanel pagingPanel;
    private Button button;

    public ListNewsViewImpl() {
        super();
    }

    @Override
    protected void postInit() {
        getPresenter().listNews();
    }

    @Override
    protected Widget getContentPanel() {
        final VLayout layout = new VLayout();
        layout.setWidth("*");
        layout.setMembersMargin(MEMBERS_MARGIN);

        rollNewsPanel = new RollNewsPanel();
        pagingPanel = new PagingPanel(getPresenter());

        layout.addMember(rollNewsPanel);
        layout.addMember(pagingPanel);
        layout.addMember(getAddNewsPanel());

        return layout;
    }

    private Widget getAddNewsPanel() {
        final HLayout hLayout = new HLayout();
        hLayout.setAlign(Alignment.RIGHT);

        button = new Button(constants.add());
        button.addClickHandler(new AddNewsClickHandler());
        hLayout.addMember(button);

        return hLayout;
    }

    @Override
    public void loadNews(final List<News> newsList) {
        for (final News news : newsList) {
            rollNewsPanel.addMember(new SingleNewsPanel(news, this));
        }
    }

    private class AddNewsClickHandler implements ClickHandler {
        @Override
        public void onClick(final ClickEvent event) {
            getPresenter().addNews();
        }
    }
}
