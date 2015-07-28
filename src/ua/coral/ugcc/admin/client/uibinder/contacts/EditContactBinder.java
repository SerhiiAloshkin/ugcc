package ua.coral.ugcc.admin.client.uibinder.contacts;

import ua.coral.ugcc.admin.client.presenter.UpdateContactPresenter;
import ua.coral.ugcc.admin.client.presenter.UpdateNewsPresenter;
import ua.coral.ugcc.common.client.UGCCConstants;
import ua.coral.ugcc.common.component.GrowlUtils;
import ua.coral.ugcc.common.dto.impl.Contact;
import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Panel;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;
import org.gwtbootstrap3.extras.summernote.client.ui.Summernote;

public class EditContactBinder extends Composite {
    interface EditContactBinderUiBinder extends UiBinder<HTMLPanel, EditContactBinder> {
    }

    private static EditContactBinderUiBinder ourUiBinder = GWT.create(EditContactBinderUiBinder.class);

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

    private final Contact contact;
    private final UpdateContactPresenter presenter;
    private final UGCCConstants constants = GWT.create(UGCCConstants.class);

    public EditContactBinder(final Contact contact, final UpdateContactPresenter presenter) {
        this.contact = contact;
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));

        addressField.setValue(contact.getAddress());
        indexField.setValue(contact.getIndex());
        phoneField.setValue(contact.getPhoneNumber());
        emailField.setValue(contact.getEmail());
        descriptionField.setValue(contact.getDescription());
    }

    @UiHandler("saveBtn")
    public void onSaveContact(final ClickEvent event) {
        if (isValueInput()) {
            contact.setAddress(addressField.getValue());
            contact.setIndex(indexField.getValue());
            contact.setPhoneNumber(phoneField.getValue());
            contact.setEmail(emailField.getValue());
            contact.setDescription(descriptionField.getValue());

            presenter.updateContact(contact, this);
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