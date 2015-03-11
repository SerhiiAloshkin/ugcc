package ua.coral.ugcc.common.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MainBinder extends Composite {

    interface MainBinderUiBinder extends UiBinder<Widget, MainBinder> {
    }

    private static MainBinderUiBinder uiBinder = GWT.create(MainBinderUiBinder.class);

    public MainBinder() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
