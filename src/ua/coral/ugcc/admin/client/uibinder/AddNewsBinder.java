package ua.coral.ugcc.admin.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Panel;
import org.gwtbootstrap3.client.ui.SubmitButton;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;
import org.gwtbootstrap3.extras.summernote.client.event.SummernoteOnImageUploadEvent;
import org.gwtbootstrap3.extras.summernote.client.event.SummernoteOnImageUploadHandler;
import org.gwtbootstrap3.extras.summernote.client.ui.Summernote;
import ua.coral.ugcc.admin.client.presenter.AddNewsPresenter;
import ua.coral.ugcc.common.component.GrowlUtils;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.Date;

public class AddNewsBinder extends Composite {
    interface AddNewsBinderUiBinder extends UiBinder<HTMLPanel, AddNewsBinder> {
    }

    private static AddNewsBinderUiBinder ourUiBinder = GWT.create(AddNewsBinderUiBinder.class);

    @UiField
    Summernote summernote;
    @UiField
    Button edit;
    @UiField
    Button save;
    @UiField
    Button preview;
    @UiField
    Panel panel;
    @UiField
    Panel editPanel;
    @UiField
    HTML body;
    @UiField
    TextBox editTitle;
    @UiField
    Heading title;
    @UiField
    FileUpload fileUploader;
    @UiField
    SubmitButton upload;
    @UiField
    FormPanel form;

    private final AddNewsPresenter presenter;

    public AddNewsBinder(final AddNewsPresenter presenter) {
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));

        summernote.setHeight(500);
        summernote.addImageUploadHandler(new SummernoteOnImageUploadHandler() {
            @Override
            public void onImageUpload(final SummernoteOnImageUploadEvent event) {
                event.getSource();
            }
        });

        panel.setVisible(false);
        edit.setVisible(false);
        upload.setEnabled(false);

        edit.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                editedPanel();

            }
        });
        save.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                savedPanel();
            }
        });
        preview.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                previewPanel();
            }
        });
        upload.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                uploadFile();
            }
        });

        form.setAction(GWT.getModuleBaseURL() + "fileupload");
        form.setMethod(FormPanel.METHOD_POST);
        form.setEncoding(FormPanel.ENCODING_MULTIPART);

        fileUploader.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(final ChangeEvent event) {
                upload.setEnabled(true);
            }
        });

        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            @Override
            public void onSubmitComplete(final FormPanel.SubmitCompleteEvent event) {
                uploaded(event.getResults());
                fileUploader.getElement().setPropertyString("value", "");
                upload.setEnabled(false);
            }
        });
    }

    private void uploadFile() {
        form.submit();
        GrowlUtils.showMessage("Завантаження зображення", "Початок завантаження зображення на сервері",
                GrowlType.SUCCESS, IconType.SMILE_O);
    }

    public void uploaded(final String result) {
        int b = result.indexOf(">") + 1;
        int e = result.lastIndexOf("</");
        final String url = result.substring(b, e);

        if (url.contains("http")) {
            final String imageBlock = "<p><br></p><img src=\"" + url + "\"><p><br></p>";
            final String code = summernote.getCode() + imageBlock;
            summernote.setText(code);
            GrowlUtils.showMessage("Завантаження зображення", "Зображення успішно завантажено на сервері",
                    GrowlType.SUCCESS, IconType.SMILE_O);
        } else {
            GrowlUtils.showMessage("Завантаження зображення", "Помилка під час збереження зображення на сервері",
                    GrowlType.DANGER, IconType.FLASH);
        }
    }

    private void editedPanel() {
        panel.setVisible(false);
        summernote.setFocus(true);
        editPanel.setVisible(true);
        edit.setVisible(false);
        preview.setVisible(true);
    }

    private void savedPanel() {
        final News news = new News();
        news.setTitle(editTitle.getText());
        news.setContent(summernote.getCode());
        news.setCreateDate(new Date());

        presenter.addNews(news, this);
    }

    private void previewPanel() {
        final String code = summernote.getCode();
        final String title = editTitle.getText();

        body.setHTML(code);
        this.title.setText(title);

        editPanel.setVisible(false);
        panel.setVisible(true);
        edit.setVisible(true);
        preview.setVisible(false);
    }

    public void saveSuccessful() {
        GrowlUtils.showMessage("Збереження запису", "Новина була успішно збережена", GrowlType.SUCCESS,
                IconType.SMILE_O);
    }

    public void saveFailure(final Throwable caught) {
        GrowlUtils.showMessage("Помилка при збереженні запису", caught.getMessage(), GrowlType.DANGER,
                IconType.FLASH);
    }
}