package ua.coral.ugcc.user.client.uibinder.contacts;

import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.user.client.view.ListContactsView;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class RollContactsBinder extends Composite {
    interface RollContactsBinderUiBinder extends UiBinder<HTMLPanel, RollContactsBinder> {
    }

    private static RollContactsBinderUiBinder ourUiBinder = GWT.create(RollContactsBinderUiBinder.class);

    @UiField
    HTMLPanel contactsList;

    public RollContactsBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setContacts(final List<Contact> contactsList, final ListContactsView.Presenter presenter) {
        for (final Contact contact : contactsList) {
            this.contactsList.add(new SingleContactBinder(contact, presenter));
        }
    }
}