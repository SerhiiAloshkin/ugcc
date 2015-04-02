package ua.coral.ugcc.common.client;

import com.google.gwt.i18n.client.Constants;

public interface UGCCConstants extends Constants {

    @DefaultStringValue("Edit")
    @Key("btn.edit")
    String edit();

    @DefaultStringValue("Remove")
    @Key("btn.remove")
    String remove();

    @DefaultStringValue("Add news")
    @Key("btn.add")
    String add();

    @DefaultStringValue("Cancel")
    @Key("btn.cancel")
    String cancel();

    @DefaultStringValue("Removing")
    @Key("messageRemoveTitle")
    String messageRemoveTitle();

    @DefaultStringValue("Are you sure?")
    @Key("messageRemoveBody")
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

    @DefaultStringValue("Title")
    @Key("page.default.title")
    String pageDefaultTitle();
}
