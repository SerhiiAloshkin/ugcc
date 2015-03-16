package ua.coral.ugcc.admin.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Panel;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlPosition;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;
import org.gwtbootstrap3.extras.summernote.client.ui.Summernote;
import ua.coral.ugcc.admin.client.presenter.UpdateNewsPresenter;
import ua.coral.ugcc.common.dto.impl.News;

public class EditNewsBinder extends Composite {
    interface EditNewsBinderUiBinder extends UiBinder<HTMLPanel, EditNewsBinder> {
    }

    private static EditNewsBinderUiBinder ourUiBinder = GWT.create(EditNewsBinderUiBinder.class);

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

    private final News news;
    private final UpdateNewsPresenter presenter;

    public EditNewsBinder(final News news, final UpdateNewsPresenter presenter) {
        this.news = news;
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));

        editTitle.setText(news.getTitle());
        summernote.setCode(news.getContent());
        summernote.setHeight(500);

        panel.setVisible(false);
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
    }

    private void editedPanel() {
        panel.setVisible(false);
        summernote.setFocus(true);
        editPanel.setVisible(true);
    }

    private void savedPanel() {
        news.setTitle(title.getText());
        news.setContent(summernote.getCode());

        presenter.updateNews(news, this);
    }

    private void previewPanel() {
        final String code = summernote.getCode();
        final String title = editTitle.getText();

        body.setHTML(code);
        this.title.setText(title);

        editPanel.setVisible(false);
        panel.setVisible(true);
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