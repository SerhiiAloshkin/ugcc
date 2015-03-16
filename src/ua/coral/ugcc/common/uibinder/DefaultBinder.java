package ua.coral.ugcc.common.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.PanelFooter;

import java.util.Date;

public class DefaultBinder extends Composite {
    interface DefaultBinderUiBinder extends UiBinder<HTMLPanel, DefaultBinder> {
    }

    private static DefaultBinderUiBinder ourUiBinder = GWT.create(DefaultBinderUiBinder.class);

    @UiField
    PanelFooter copyright;

    @UiField
    HTMLPanel content;

    public DefaultBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
        copyright.setTitle("&copy; 2014-" + DateTimeFormat.getFormat("yyyy").format(new Date()));
    }

    public void setChild(final Widget child) {
        content.add(child);
    }
}