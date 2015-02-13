package ua.coral.ugcc.common.client;

import com.google.gwt.i18n.client.Constants;

public interface UGCCConstants extends Constants {

    @DefaultStringValue("Edit")
    String edit();

    @DefaultStringValue("Remove")
    String remove();

    @DefaultStringValue("Add news")
    String add();

    @DefaultStringValue("Cancel")
    String cancel();

    @DefaultStringValue("Removing")
    String messageRemoveTitle();

    @DefaultStringValue("Are you sure?")
    String messageRemoveBody();
}
