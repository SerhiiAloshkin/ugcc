package ua.coral.ugcc.admin.client.panel;

import com.smartgwt.client.widgets.layout.VLayout;

public class RollNewsPanel extends VLayout {

    private static final int MEMBERS_MARGIN = 20;

    public RollNewsPanel() {
        super();

        init();
    }

    private void init() {
        setWidth100();
        setHeight100();
        setMembersMargin(MEMBERS_MARGIN);
    }
}
