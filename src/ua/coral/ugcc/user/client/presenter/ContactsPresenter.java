package ua.coral.ugcc.user.client.presenter;

import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;
import ua.coral.ugcc.user.client.UgccServiceAsync;
import ua.coral.ugcc.user.client.uibinder.contacts.ContactsBinder;
import ua.coral.ugcc.user.client.view.ListContactsView;

import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class ContactsPresenter extends DefaultPresenterImpl implements Presenter, ListContactsView.Presenter {

    private final UgccServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public ContactsPresenter(final UgccServiceAsync rpcService, final HandlerManager eventBus) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;

        listContacts();
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
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

    private void onContacts(final List<Contact> contactsList) {
        view.setChild(new ContactsBinder(contactsList, this));
    }
}
