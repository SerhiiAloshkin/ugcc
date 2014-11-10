package ua.coral.ugcc.common.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface View<P> extends IsWidget {

    void init();

    void setPresenter(P presenter);
}
