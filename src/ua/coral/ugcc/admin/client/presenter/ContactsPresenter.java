package ua.coral.ugcc.admin.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.AddContactEvent;
import ua.coral.ugcc.admin.client.event.AddNewsEvent;
import ua.coral.ugcc.admin.client.event.UpdateContactEvent;
import ua.coral.ugcc.admin.client.uibinder.contacts.SingleContactBinder;
import ua.coral.ugcc.admin.client.view.ListContactsView;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.admin.client.uibinder.contacts.ContactsBinder;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

import java.util.List;

public class ContactsPresenter extends DefaultPresenterImpl implements Presenter, ListContactsView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public ContactsPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;

        listContacts();
        //view.setChild(new ContactsBinder(this));
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    @Override
    public void addContact() {
        eventBus.fireEvent(new AddContactEvent());
    }

    @Override
    public void listContacts() {
        rpcService.listContacts(new AsyncCallback<List<Contact>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<Contact> contactsList) {
                onContacts(contactsList);
            }
        });
    }

    @Override
    public void updateContact(final Long contactId) {
        eventBus.fireEvent(new UpdateContactEvent(contactId));
    }

    @Override
    public void removeContact(final Contact contact, final SingleContactBinder view) {
        rpcService.removeContact(contact, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                view.removeFailure(caught);
            }

            @Override
            public void onSuccess(Void result) {
                view.removeSuccessful();
                view.removeFromParent();
            }
        });
    }

    private void onContacts(final List<Contact> contactsList) {
        view.setChild(new ContactsBinder(contactsList, this));
    }
}
