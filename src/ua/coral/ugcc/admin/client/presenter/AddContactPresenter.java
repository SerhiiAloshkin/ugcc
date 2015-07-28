package ua.coral.ugcc.admin.client.presenter;

import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.ListContactsEvent;
import ua.coral.ugcc.admin.client.event.ListNewsEvent;
import ua.coral.ugcc.admin.client.uibinder.contacts.AddContactBinder;
import ua.coral.ugcc.admin.client.view.AddContactView;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AddContactPresenter extends DefaultPresenterImpl implements Presenter, AddContactView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public AddContactPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;

        view.setChild(new AddContactBinder(this));
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    @Override
    public void addContact(final Contact contact, final AddContactBinder addView) {
        rpcService.addContact(contact, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                addView.saveFailure(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                addView.saveSuccessful();
                eventBus.fireEvent(new ListContactsEvent());
            }
        });
    }

    @Override
    public void listContacts() {
        eventBus.fireEvent(new ListContactsEvent());
    }
}
