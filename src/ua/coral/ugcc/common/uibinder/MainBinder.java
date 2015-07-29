package ua.coral.ugcc.common.uibinder;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import org.gwtbootstrap3.client.ui.Label;

public class MainBinder extends Composite {

    interface MainBinderUiBinder extends UiBinder<Widget, MainBinder> {
    }

    private static MainBinderUiBinder uiBinder = GWT.create(MainBinderUiBinder.class);

    @UiField
    Label copyright;

    public MainBinder() {
        initWidget(uiBinder.createAndBindUi(this));
        copyright.setHTML("&copy; 2014-" + DateTimeFormat.getFormat("yyyy").format(new Date()));
    }
}
