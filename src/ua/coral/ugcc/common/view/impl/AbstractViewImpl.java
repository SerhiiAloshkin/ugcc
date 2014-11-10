package ua.coral.ugcc.common.view.impl;

import ua.coral.ugcc.common.view.View;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class AbstractViewImpl<P> extends Composite implements View<P> {

    private static final int BOTTOM_PANEL_HEIGHT = 75;
    private static final int CONTENT_WIDTH = 1000;
    private static final int MEMBERS_MARGIN = 20;
    private static final int MENU_WIDTH = 250;
    private static final int TOP_PANEL_HEIGHT = 150;

    private P presenter;
    private HLayout mainLayout;

    @Override
    public void init() {
        getContent().draw();
        postInit();
    }

    @Override
    public void setPresenter(final P presenter) {
        this.presenter = presenter;
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract void postInit();

    protected abstract Widget getContentPanel();

    private HLayout getContent() {
        mainLayout = new HLayout();
        mainLayout.setWidth100();
        mainLayout.setHeight100();
        mainLayout.setMembersMargin(MEMBERS_MARGIN);

        final VLayout vLayout = new VLayout();
        vLayout.setWidth(CONTENT_WIDTH);
        vLayout.setHeight100();
        vLayout.setMembersMargin(MEMBERS_MARGIN);
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
        layout.setHeight(TOP_PANEL_HEIGHT);
        return layout;
    }

    private Widget getCenterPanel() {
        final VLayout vLayout = new VLayout();
        vLayout.setWidth100();
        vLayout.setHeight100();
        vLayout.setMembersMargin(MEMBERS_MARGIN);

        final HLayout hLayout = new HLayout();
        hLayout.setMembersMargin(MEMBERS_MARGIN);

        hLayout.addMember(getMenuPanel());
        hLayout.addMember(getContentPanel());

        vLayout.addMember(hLayout);

        return vLayout;
    }

    private Widget getMenuPanel() {
        final VLayout layout = new VLayout();
        layout.setHeight100();
        layout.setWidth(MENU_WIDTH);
        return layout;
    }

    private Widget getBottomPanel() {
        final HLayout layout = new HLayout();
        layout.setBackgroundColor("#808080");
        layout.setHeight(BOTTOM_PANEL_HEIGHT);
        return layout;
    }
}
