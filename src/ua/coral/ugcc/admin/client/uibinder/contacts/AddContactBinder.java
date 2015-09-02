package ua.coral.ugcc.admin.client.uibinder.contacts;

import ua.coral.ugcc.admin.client.presenter.AddContactPresenter;
import ua.coral.ugcc.common.client.Locale;
import ua.coral.ugcc.common.component.GrowlUtils;
import ua.coral.ugcc.common.dto.impl.Contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;

public class AddContactBinder extends Composite {
    interface AddContactBinderUiBinder extends UiBinder<HTMLPanel, AddContactBinder> {
    }

    private static AddContactBinderUiBinder ourUiBinder = GWT.create(AddContactBinderUiBinder.class);

    @UiField
    TextBox addressField;
    @UiField
    TextBox indexField;
    @UiField
    TextBox phoneField;
    @UiField
    TextBox emailField;
    @UiField
    TextArea descriptionField;
    @UiField
    Button saveBtn;

    private final AddContactPresenter presenter;
    private final Locale constants = GWT.create(Locale.class);

    public AddContactBinder(final AddContactPresenter presenter) {
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("saveBtn")
    public void onSaveContact(final ClickEvent event) {
        if (isValueInput()) {
            final Contact contact = new Contact();
            contact.setAddress(addressField.getValue());
            contact.setIndex(indexField.getValue());
            contact.setPhoneNumber(phoneField.getValue());
            contact.setEmail(emailField.getValue());
            contact.setDescription(descriptionField.getValue());

            presenter.addContact(contact, this);
        } else {
            GrowlUtils.showMessage(constants.newsRecordSavingFailed(), "Bad!", GrowlType.DANGER,
                    IconType.FLASH);
        }
    }

    public void saveSuccessful() {
        GrowlUtils.showMessage(constants.newsRecordSaving(), constants.newsRecordSavingSuccessful(), GrowlType.SUCCESS,
                IconType.SMILE_O);
    }

    public void saveFailure(final Throwable caught) {
        GrowlUtils.showMessage(constants.newsRecordSavingFailed(), caught.getMessage(), GrowlType.DANGER,
                IconType.FLASH);
    }

    private boolean isValueInput() {
        return !addressField.getValue().isEmpty() &&
                !indexField.getValue().isEmpty() &&
                !phoneField.getValue().isEmpty() &&
                !emailField.getValue().isEmpty();
    }
}