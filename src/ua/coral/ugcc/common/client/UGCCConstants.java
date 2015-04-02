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

    @DefaultStringValue("Home")
    @Key("page.default.home")
    String pageDefaultHome();

    @DefaultStringValue("Parish")
    @Key("page.default.parish")
    String pageDefaultParish();

    @DefaultStringValue("Contacts")
    @Key("page.default.contacts")
    String pageDefaultContacts();

    @DefaultStringValue("News")
    @Key("page.default.news")
    String pageDefaultNews();

    @DefaultStringValue("Schedule")
    @Key("page.default.schedule")
    String pageDefaultSchedule();

    @DefaultStringValue("Map")
    @Key("page.default.map")
    String pageDefaultMap();
}
