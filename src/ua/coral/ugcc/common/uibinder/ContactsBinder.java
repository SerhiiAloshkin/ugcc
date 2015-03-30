package ua.coral.ugcc.common.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ua.coral.ugcc.common.presenter.impl.ContactsPresenter;

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