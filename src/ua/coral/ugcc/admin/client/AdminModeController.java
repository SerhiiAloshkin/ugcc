package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.admin.client.event.AddContactEvent;
import ua.coral.ugcc.admin.client.event.AddNewsEvent;
import ua.coral.ugcc.admin.client.event.ListNewsEvent;
import ua.coral.ugcc.admin.client.event.UpdateContactEvent;
import ua.coral.ugcc.admin.client.event.UpdateNewsEvent;
import ua.coral.ugcc.admin.client.event.handler.AddContactEventHandler;
import ua.coral.ugcc.admin.client.event.handler.AddNewsEventHandler;
import ua.coral.ugcc.admin.client.event.handler.ListNewsEventHandler;
import ua.coral.ugcc.admin.client.event.handler.UpdateContactEventHandler;
import ua.coral.ugcc.admin.client.event.handler.UpdateNewsEventHandler;
import ua.coral.ugcc.admin.client.presenter.AddContactPresenter;
import ua.coral.ugcc.admin.client.presenter.AddNewsPresenter;
import ua.coral.ugcc.admin.client.presenter.ContactsPresenter;
import ua.coral.ugcc.admin.client.presenter.ListNewsPresenter;
import ua.coral.ugcc.admin.client.presenter.MediaPresenter;
import ua.coral.ugcc.admin.client.presenter.UpdateContactPresenter;
import ua.coral.ugcc.admin.client.presenter.UpdateNewsPresenter;
import ua.coral.ugcc.common.client.DefaultAppController;
import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.presenter.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;

public class AdminModeController extends DefaultAppController {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;

    public AdminModeController(final AdminModeServiceAsync rpcService, final HandlerManager eventBus) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(ListNewsEvent.TYPE, new ListNewsEventHandler() {
            @Override
            public void onListNews(final ListNewsEvent event) {
                doListNews();
            }
        });
        eventBus.addHandler(AddNewsEvent.TYPE, new AddNewsEventHandler() {
            @Override
            public void onAddNews(final AddNewsEvent event) {
                doAddNews();
            }
        });
        eventBus.addHandler(UpdateNewsEvent.TYPE, new UpdateNewsEventHandler() {
            @Override
            public void onUpdateNews(final UpdateNewsEvent event) {
                doUpdateNews(event.getNewsId());
            }
        });
        eventBus.addHandler(AddContactEvent.TYPE, new AddContactEventHandler() {
            @Override
            public void onAddContact(final AddContactEvent event) {
                doAddContact();
            }
        });
        eventBus.addHandler(UpdateContactEvent.TYPE, new UpdateContactEventHandler() {
            @Override
            public void onUpdateContact(UpdateContactEvent event) {
                doUpdateContact(event.getContactId());
            }
        });

    }

    private void doListNews() {
        History.newItem(HistoryToken.TO_NEWS.getToken());
    }

    private void doAddNews() {
        History.newItem(HistoryToken.ADD_NEWS.getToken());
    }

    private void doAddContact() {
        History.newItem(HistoryToken.ADD_CONTACT.getToken());
    }

    private void doUpdateNews(final Long newsId) {
        History.newItem(HistoryToken.UPDATE_NEWS.getToken(), false);
        final Presenter presenter = new UpdateNewsPresenter(rpcService, eventBus, newsId);
        presenter.go(getContainer());
    }

    private void doUpdateContact(final Long contactId) {
        History.newItem(HistoryToken.UPDATE_CONTACT.getToken(), false);
        final Presenter presenter = new UpdateContactPresenter(rpcService, eventBus, contactId);
        presenter.go(getContainer());
    }

    @Override
    public void onValueChange(final ValueChangeEvent<String> event) {
        super.onValueChange(event);

        final String token = event.getValue();

        if (token == null) {
            return;
        }

        if (HistoryToken.TO_NEWS.getToken().equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    new ListNewsPresenter(rpcService, eventBus).go(getContainer());
                }
            });
        } else if (HistoryToken.ADD_NEWS.getToken().equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    new AddNewsPresenter(rpcService, eventBus).go(getContainer());
                }
            });
        } else if (HistoryToken.ADD_CONTACT.getToken().equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    new AddContactPresenter(rpcService, eventBus).go(getContainer());
                }
            });
        }
    }

    @Override
    protected void loadContactsPresenter() {
        new ContactsPresenter(rpcService, eventBus).go(getContainer());
    }

    @Override
    protected void loadMediaPresenter() {
        new MediaPresenter(rpcService, eventBus).go(getContainer());
    }
}
