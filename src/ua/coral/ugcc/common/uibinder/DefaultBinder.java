package ua.coral.ugcc.common.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.Label;

import java.util.Date;

public class DefaultBinder extends Composite {
    interface DefaultBinderUiBinder extends UiBinder<HTMLPanel, DefaultBinder> {
    }

    private static DefaultBinderUiBinder ourUiBinder = GWT.create(DefaultBinderUiBinder.class);

    @UiField
    Label copyright;

    @UiField
    Container content;

    public DefaultBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
        copyright.setHTML("&copy; 2014-" + DateTimeFormat.getFormat("yyyy").format(new Date()));
    }

    public void setChild(final Widget child) {
        content.add(child);
    }
}