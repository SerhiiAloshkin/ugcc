package ua.coral.ugcc.user.client.uibinder.contacts;

import ua.coral.ugcc.common.client.Locale;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.user.client.view.ListContactsView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.gwtbootstrap3.client.ui.FormControlStatic;


public class SingleContactBinder extends Composite {
    interface SingleContactsBinderUiBinder extends UiBinder<HTMLPanel, SingleContactBinder> {
    }

    private static SingleContactsBinderUiBinder ourUiBinder = GWT.create(SingleContactsBinderUiBinder.class);

    @UiField
    FormControlStatic addressField;
    @UiField
    FormControlStatic indexField;
    @UiField
    FormControlStatic phoneField;
    @UiField
    FormControlStatic emailField;
    @UiField
    FormControlStatic descriptionField;

    private final Contact contact;
    private final ListContactsView.Presenter presenter;
    private final Locale constants = GWT.create(Locale.class);

    public SingleContactBinder(final Contact contact, final ListContactsView.Presenter presenter) {
        this.contact = contact;
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));

        addressField.setText(contact.getAddress());
        indexField.setText(contact.getIndex());
        phoneField.setText(contact.getPhoneNumber());
        emailField.setText(contact.getEmail());
        descriptionField.setText(contact.getDescription());
    }
}