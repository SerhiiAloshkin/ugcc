package ua.coral.ugcc.admin.client.uibinder;

import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.extras.summernote.client.event.SummernoteOnImageUploadEvent;
import org.gwtbootstrap3.extras.summernote.client.event.SummernoteOnImageUploadHandler;
import org.gwtbootstrap3.extras.summernote.client.ui.Summernote;

public class EditNewsBinder extends Composite {
    interface EditNewsBinderUiBinder extends UiBinder<HTMLPanel, EditNewsBinder> {
    }

    private static EditNewsBinderUiBinder ourUiBinder = GWT.create(EditNewsBinderUiBinder.class);

    @UiField
    Summernote summernote;

    private final News news;

    public EditNewsBinder(final News news) {
        this.news = news;

        initWidget(ourUiBinder.createAndBindUi(this));
        summernote.setCode(news.getContent());

        summernote.addImageUploadHandler(new SummernoteOnImageUploadHandler() {
            @Override
            public void onImageUpload(final SummernoteOnImageUploadEvent event) {
                Window.alert("Fired!");
            }
        });
    }
}