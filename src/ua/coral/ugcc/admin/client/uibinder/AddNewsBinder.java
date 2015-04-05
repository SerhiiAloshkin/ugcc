package ua.coral.ugcc.admin.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import gwtupload.client.IUploadStatus;
import gwtupload.client.IUploader;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Panel;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlPosition;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;
import org.gwtbootstrap3.extras.summernote.client.event.SummernoteOnImageUploadEvent;
import org.gwtbootstrap3.extras.summernote.client.event.SummernoteOnImageUploadHandler;
import org.gwtbootstrap3.extras.summernote.client.ui.Summernote;
import ua.coral.ugcc.admin.client.presenter.AddNewsPresenter;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.Date;

/**
 * Created by Noname on 21.03.2015.
 */
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
    MultiUploader defaultUploader;
    @UiField
    FlowPanel panelImages;
    @UiField
    Label lUrl;

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

        defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
    }

    // Load the image in the document and in the case of success attach it to the viewer
    private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
        public void onFinish(IUploader uploader) {
            if (uploader.getStatus() == IUploadStatus.Status.SUCCESS) {

                final String url = uploader.getServletPath() + "?blob-key=" + uploader.getServerInfo().message;
                lUrl.setText(url);

//                new PreloadedImage(uploader.fileUrl(), showImage);
//
//
//
//                // The server sends useful information to the client by default
//                IUploader.UploadedInfo info = uploader.getServerInfo();
//                System.out.println("File name " + info.name);
//                System.out.println("File content-type " + info.ctype);
//                System.out.println("File size " + info.size);
//
//                // You can send any customized message and parse it
//                System.out.println("Server message " + info.message);
            }
        }
    };

    // Attach an image to the pictures viewer
    private PreloadedImage.OnLoadPreloadedImageHandler showImage = new PreloadedImage.OnLoadPreloadedImageHandler() {
        public void onLoad(PreloadedImage image) {
            image.setWidth("75px");
            panelImages.add(image);
        }
    };

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
        GrowlOptions go = new GrowlOptions();
        go.setType(GrowlType.SUCCESS);
        go.setTemplate("<div data-growl=\"container\" class=\"alert\" role=\"alert\">" +
                "<button type=\"button\" class=\"close\" data-growl=\"dismiss\">" +
                "<span aria-hidden=\"true\">×</span>" +
                "<span class=\"sr-only\">Close</span>" +
                "</button>" +
                "<b><span data-growl=\"title\"></span></b><br/>" +
                "<span data-growl=\"icon\"></span>" +
                "<span data-growl=\"message\"></span>" +
                "<a href=\"#\" data-growl=\"url\"></a>" +
                "</div>");
        go.makeDefault();
        go.setPosition(GrowlPosition.TOP_CENTER);
        Growl.growl("\tЗбереження запису\t", "\tНовина була успішно збережена\t", IconType.SMILE_O, go);
    }

    public void saveFailure(final Throwable caught) {
        GrowlOptions go = new GrowlOptions();
        go.setType(GrowlType.DANGER);
        go.setTemplate("<div data-growl=\"container\" class=\"alert\" role=\"alert\">" +
                "<button type=\"button\" class=\"close\" data-growl=\"dismiss\">" +
                "<span aria-hidden=\"true\">×</span>" +
                "<span class=\"sr-only\">Close</span>" +
                "</button>" +
                "<b><span data-growl=\"title\"></span></b><br/>" +
                "<span data-growl=\"icon\"></span>" +
                "<span data-growl=\"message\"></span>" +
                "<a href=\"#\" data-growl=\"url\"></a>" +
                "</div>");
        go.makeDefault();
        go.setPosition(GrowlPosition.TOP_CENTER);
        Growl.growl("\tПомилка при збереженні запису\t", "\t" + caught.getMessage() + "\t", IconType.FLASH, go);
    }
}