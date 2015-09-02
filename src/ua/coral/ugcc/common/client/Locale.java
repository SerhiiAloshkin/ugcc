package ua.coral.ugcc.common.client;

import com.google.gwt.i18n.client.Constants;

public interface Locale extends Constants {

    @DefaultStringValue("Edit")
    @Key("btn.edit")
    String edit();

    @DefaultStringValue("Remove")
    @Key("btn.remove")
    String remove();

    @DefaultStringValue("Add")
    @Key("btn.add")
    String add();

    @DefaultStringValue("Cancel")
    @Key("btn.cancel")
    String cancel();

    @DefaultStringValue("Review")
    @Key("btn.review")
    String review();

    @DefaultStringValue("Save")
    @Key("btn.save")
    String save();

    @DefaultStringValue("Upload")
    @Key("btn.upload")
    String upload();

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

    @DefaultStringValue("Description")
    @Key("page.default.news.description")
    String pageDefaultNewsDescription();

    @DefaultStringValue("Description")
    @Key("page.default.parish.description")
    String pageDefaultParishDescription();

    @DefaultStringValue("Description")
    @Key("page.default.schedule.description")
    String pageDefaultScheduleDescription();

    @DefaultStringValue("Description")
    @Key("page.default.contacts.description")
    String pageDefaultContactsDescription();

    @DefaultStringValue("Description")
    @Key("page.default.map.description")
    String pageDefaultMapDescription();

    @DefaultStringValue("Title")
    @Key("page.news.title")
    String pageNewsTitle();

    @DefaultStringValue("Upload Image")
    @Key("upload.image.title")
    String uploadImageTitle();

    @DefaultStringValue("*Upload maximum 6mb")
    @Key("upload.image.maxsize")
    String uploadImageMaxSize();

    @DefaultStringValue("Upload is started")
    @Key("upload.image.begin")
    String uploadImageBegin();

    @DefaultStringValue("Upload is successful")
    @Key("upload.image.successful")
    String uploadImageSuccessful();

    @DefaultStringValue("Upload failed")
    @Key("upload.image.failed")
    String uploadImageFailed();

    @DefaultStringValue("Saving news")
    @Key("news.record.saving")
    String newsRecordSaving();

    @DefaultStringValue("Saving news failed")
    @Key("news.record.saving.failed")
    String newsRecordSavingFailed();

    @DefaultStringValue("News saved successful")
    @Key("news.record.saving.successful")
    String newsRecordSavingSuccessful();

    @DefaultStringValue("Removing news")
    @Key("news.record.removing")
    String newsRecordRemoving();

    @DefaultStringValue("Removing news failed")
    @Key("news.record.removing.failed")
    String newsRecordRemovingFailed();

    @DefaultStringValue("News removed successful")
    @Key("news.record.removing.successful")
    String newsRecordRemovingSuccessful();

    @DefaultStringValue("Are you sure to remove news?")
    @Key("news.record.removing.message")
    String newsRecordRemovingMessage();

    @DefaultStringValue("Target")
    @Key("map.direction.target")
    String mapDirectionTarget();

    @DefaultStringValue("Path to target")
    @Key("map.direction.path")
    String mapDirectionPath();

    @DefaultStringValue("How to find")
    @Key("map.direction.hint")
    String mapDirectionHint();

    @DefaultStringValue("Create contact")
    @Key("contact.create.title")
    String contactCreateTitle();
}
