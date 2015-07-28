package ua.coral.ugcc.admin.client.uibinder.contacts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ua.coral.ugcc.admin.client.presenter.ContactsPresenter;
import ua.coral.ugcc.admin.client.uibinder.news.RollNewsBinder;
import ua.coral.ugcc.common.dto.impl.Contact;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;

public class ContactsBinder extends Composite {
    interface ContactsBinderUiBinder extends UiBinder<HTMLPanel, ContactsBinder> {
    }

    private static ContactsBinderUiBinder ourUiBinder = GWT.create(ContactsBinderUiBinder.class);

    @UiField
    RollContactsBinder roll;
    @UiField
    Button addBtn;

    private final ContactsPresenter presenter;

    public ContactsBinder(final List<Contact> contactsList, final ContactsPresenter presenter) {
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
        roll.setContacts(contactsList, presenter);
    }

    @UiHandler("addBtn")
    public void onAddNews(final ClickEvent event) {
        presenter.addContact();
    }
}