package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.admin.client.uibinder.contacts.SingleContactBinder;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.view.View;

public interface ListContactsView extends View<ListContactsView.Presenter> {

    interface Presenter {

        void listContacts();

        void addContact();

        void updateContact(Long contactId);

        void removeContact(Contact contact, SingleContactBinder view);
    }
}
