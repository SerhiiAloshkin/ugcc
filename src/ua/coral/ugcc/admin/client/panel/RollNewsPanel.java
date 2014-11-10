package ua.coral.ugcc.admin.client.panel;

import ua.coral.ugcc.admin.client.view.ListNewsView;

import com.smartgwt.client.widgets.layout.VLayout;

public class RollNewsPanel extends VLayout {

    private static final int MEMBERS_MARGIN = 20;

    private ListNewsView.Presenter presenter;

    public RollNewsPanel(final ListNewsView.Presenter presenter) {
        super();
        this.presenter = presenter;

        init();
    }

    private void init() {
        setWidth100();
        setHeight100();
        setMembersMargin(MEMBERS_MARGIN);

        presenter.listNews();
    }
}
