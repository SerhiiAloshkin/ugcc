package ua.coral.ugcc.admin.client.presenter;

import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.ListContactsEvent;
import ua.coral.ugcc.admin.client.uibinder.contacts.EditContactBinder;
import ua.coral.ugcc.admin.client.view.UpdateContactView;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class UpdateContactPresenter extends DefaultPresenterImpl implements Presenter, UpdateContactView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public UpdateContactPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus,
                                  final Long contactId) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;

        loadContactById(contactId);
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view);
    }

    private void loadContactById(final Long newsId) {
        rpcService.getContactById(newsId, new AsyncCallback<Contact>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final Contact contact) {
                loadEditView(contact);
            }
        });
    }

    private void loadEditView(final Contact contact) {
        view.setChild(new EditContactBinder(contact, this));
    }

    @Override
    public void updateContact(final Contact contact, final EditContactBinder editView) {
        rpcService.updateContact(contact, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                editView.saveFailure(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                editView.saveSuccessful();
                eventBus.fireEvent(new ListContactsEvent());
            }
        });
    }

    @Override
    public void listContacts() {
        eventBus.fireEvent(new ListContactsEvent());
    }
}
