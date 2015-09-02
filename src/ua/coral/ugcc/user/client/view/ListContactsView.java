package ua.coral.ugcc.user.client.view;

import ua.coral.ugcc.common.view.View;

public interface ListContactsView extends View<ListContactsView.Presenter> {

    interface Presenter {

        void listContacts();
    }
}
