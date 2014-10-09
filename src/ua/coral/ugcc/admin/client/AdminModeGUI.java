package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AdminModeGUI {

    private static final int TITLE_COLUMN = 0;
    private static final int CONTENT_COLUMN = 1;
    private static final int EDIT_COLUMN = 2;
    private static final int REMOVE_COLUMN = 3;

    protected Button addButton;
    protected Button updateButton;
    protected Button addNewButton;

    private TextBox title;
    private TextBox content;

    private Label status;

    protected Grid newsGrid;
    private Grid formGrid;

    private List<News> listNews;
    private News currentNews;
    protected AdminModeServiceDelegate serviceDelegate;

    public void init() {
        addButton = new Button("Add news");
        addNewButton = new Button("Add news1");
        updateButton = new Button("Update news");

        title = new TextBox();
        content = new TextBox();

        status = new Label();

        newsGrid = new Grid(3, 4);

        buildForm();
        placeWidgets();
    }

    public void service_eventListRetrievedFromService(final List<News> listNews) {
        status.setText("Retrieved News list");

        this.listNews = listNews;
        newsGrid.clear();
        newsGrid.resizeRows(listNews.size());
        int row = 0;

        for (final News news : listNews) {
            newsGrid.setWidget(row, TITLE_COLUMN, new Label(news.getTitle()));
            newsGrid.setWidget(row, CONTENT_COLUMN, new Label(news.getContent()));
            newsGrid.setWidget(row, EDIT_COLUMN, new Hyperlink("Edit", null));
            newsGrid.setWidget(row, REMOVE_COLUMN, new Hyperlink("Remove", null));
            row++;
        }
    }

    public void gui_eventNewsGridClicked(final HTMLTable.Cell cellClicked) {
        int row = cellClicked.getRowIndex();
        int col = cellClicked.getCellIndex();

        final News news = listNews.get(row);

        if (col == EDIT_COLUMN) {
            addNewButton.setVisible(true);
            updateButton.setVisible(true);
            addButton.setVisible(false);
            loadForm(news);
        } else if (col == REMOVE_COLUMN) {
            serviceDelegate.removeNews(news);
        }
    }

    public void gui_eventUpdateButtonClicked() {
        addNewButton.setVisible(true);
        formGrid.setVisible(false);
        copyFieldDateToContact();
        serviceDelegate.updateNews(currentNews);
    }

    public void gui_eventAddButtonClicked() {
        addNewButton.setVisible(true);
        formGrid.setVisible(false);
        copyFieldDateToContact();
        serviceDelegate.addNews(currentNews);
    }

    public void gui_eventAddNewButtonClicked() {
        this.addNewButton.setVisible(false);
        this.updateButton.setVisible(false);
        this.addButton.setVisible(true);

        final News news = new News();
        news.setId(System.currentTimeMillis());

        loadForm(news);
    }

    public void service_eventAddContactSuccessful() {
        status.setText("Contact was successfully added");
        serviceDelegate.listNews();
    }

    public void service_eventUpdateSuccessful() {
        status.setText("Contact was successfully updated");
        serviceDelegate.listNews();
    }

    public void service_eventRemoveContactSuccessful() {
        status.setText("Contact was removed");
        serviceDelegate.listNews();
    }

    public void service_eventUpdateContactFailed(Throwable caught) {
        status.setText("Update contact failed");
    }

    public void service_eventAddContactFailed(Throwable caught) {
        status.setText("Unable to update contact");
    }

    public void service_eventRemoveContactFailed(Throwable caught) {
        status.setText("Remove contact failed");
    }

    public void service_eventListContactsFailed(Throwable caught) {
        status.setText("Unable to get contact list");
    }

    private void buildForm() {
        formGrid = new Grid(3, 3);
        formGrid.setVisible(false);

        formGrid.setWidget(0, 0, new Label("Title"));
        formGrid.setWidget(0, 1, title);

        formGrid.setWidget(1, 0, new Label("Content"));
        formGrid.setWidget(1, 1, content);

        formGrid.setWidget(2, 0, updateButton);
        formGrid.setWidget(2, 1, addButton);
    }


    private void copyFieldDateToContact() {
        currentNews.setTitle(title.getText());
        currentNews.setContent(content.getText());
    }

    private void loadForm(final News news) {
        formGrid.setVisible(true);
        currentNews = news;
        title.setText(news.getTitle());
        content.setText(news.getContent());
    }

    private void placeWidgets() {
        RootPanel.get("newsListing").add(newsGrid);
        RootPanel.get("newsForm").add(formGrid);
        RootPanel.get("newsStatus").add(status);
        RootPanel.get("newsToolBar").add(addNewButton);
    }
}
