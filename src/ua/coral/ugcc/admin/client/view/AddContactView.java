package ua.coral.ugcc.admin.client.view;

import ua.coral.ugcc.admin.client.uibinder.contacts.AddContactBinder;
import ua.coral.ugcc.admin.client.uibinder.news.AddNewsBinder;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.presenter.DefaultPresenter;
import ua.coral.ugcc.common.view.View;

public interface AddContactView extends View<AddContactView.Presenter> {

    interface Presenter extends DefaultPresenter {
        void addContact(Contact contact, AddContactBinder addView);

        void listContacts();
    }
}
