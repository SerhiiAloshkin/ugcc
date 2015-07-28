package ua.coral.ugcc.user.client.uibinder;

import ua.coral.ugcc.user.client.presenter.ContactsPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ContactsBinder extends Composite {
    interface ContactsBinderUiBinder extends UiBinder<HTMLPanel, ContactsBinder> {
    }

    private static ContactsBinderUiBinder ourUiBinder = GWT.create(ContactsBinderUiBinder.class);

    private final ContactsPresenter presenter;

    public ContactsBinder(final ContactsPresenter presenter) {
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
    }
}