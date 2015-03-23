package ua.coral.ugcc.common.presenter.impl;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.uibinder.ContactsBinder;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

public class ContactsPresenter extends DefaultPresenterImpl implements Presenter {

    private final DefaultBinder view;

    public ContactsPresenter(final HandlerManager eventBus, final DefaultBinder view) {
        super(eventBus);
        this.view = view;

        view.setChild(new ContactsBinder(this));
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }
}
