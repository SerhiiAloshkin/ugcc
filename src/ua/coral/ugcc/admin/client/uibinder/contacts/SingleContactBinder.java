package ua.coral.ugcc.admin.client.uibinder.contacts;

import ua.coral.ugcc.admin.client.view.ListContactsView;
import ua.coral.ugcc.common.client.UGCCConstants;
import ua.coral.ugcc.common.component.GrowlUtils;
import ua.coral.ugcc.common.dto.impl.Contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormControlStatic;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;

public class SingleContactBinder extends Composite {
    interface SingleContactsBinderUiBinder extends UiBinder<HTMLPanel, SingleContactBinder> {
    }

    private static SingleContactsBinderUiBinder ourUiBinder = GWT.create(SingleContactsBinderUiBinder.class);

    @UiField
    FormControlStatic addressField;
    @UiField
    FormControlStatic indexField;
    @UiField
    FormControlStatic phoneField;
    @UiField
    FormControlStatic emailField;
    @UiField
    FormControlStatic descriptionField;
    @UiField
    Button editBtn;
    @UiField
    Button removeBtn;

    private final Contact contact;
    private final ListContactsView.Presenter presenter;
    private final UGCCConstants constants = GWT.create(UGCCConstants.class);

    public SingleContactBinder(final Contact contact, final ListContactsView.Presenter presenter) {
        this.contact = contact;
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));

        addressField.setText(contact.getAddress());
        indexField.setText(contact.getIndex());
        phoneField.setText(contact.getPhoneNumber());
        emailField.setText(contact.getEmail());
        descriptionField.setText(contact.getDescription());
    }

    @UiHandler("editBtn")
    public void onEditContact(final ClickEvent event) {
        presenter.updateContact(contact.getId());
    }

    @UiHandler("removeBtn")
    public void onRemoveContact(final ClickEvent event) {
        final String msg = constants.newsRecordRemovingMessage();
        Bootbox.confirm(msg, new ConfirmCallback() {
            @Override
            public void callback(final boolean result) {
                removeContact(result);
            }
        });
    }

    private void removeContact(final boolean result) {
        if (result) {
            presenter.removeContact(contact, this);
        }
    }

    public void removeSuccessful() {
        GrowlUtils.showMessage(constants.newsRecordRemoving(), constants.newsRecordRemovingSuccessful(),
                GrowlType.SUCCESS, IconType.SMILE_O);
    }

    public void removeFailure(final Throwable caught) {
        GrowlUtils.showMessage(constants.newsRecordRemovingFailed(), caught.getMessage(), GrowlType.DANGER,
                IconType.FLASH);
    }
}