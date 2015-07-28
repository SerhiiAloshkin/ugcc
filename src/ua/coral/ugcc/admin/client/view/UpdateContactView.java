package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.admin.client.uibinder.contacts.EditContactBinder;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.presenter.DefaultPresenter;
import ua.coral.ugcc.common.view.View;

public interface UpdateContactView extends View<UpdateContactView.Presenter> {

    void setContact(Contact contact);

    interface Presenter extends DefaultPresenter {
        void updateContact(Contact contact, EditContactBinder editView);

        void listContacts();
    }
}
