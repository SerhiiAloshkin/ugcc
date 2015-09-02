package ua.coral.ugcc.user.client.uibinder.contacts;

import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.user.client.presenter.ContactsPresenter;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ContactsBinder extends Composite {
    interface ContactsBinderUiBinder extends UiBinder<HTMLPanel, ContactsBinder> {
    }

    private static ContactsBinderUiBinder ourUiBinder = GWT.create(ContactsBinderUiBinder.class);

    @UiField
    RollContactsBinder roll;

    private final ContactsPresenter presenter;

    public ContactsBinder(final List<Contact> contactsList, final ContactsPresenter presenter) {
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
        roll.setContacts(contactsList, presenter);
    }
}