package ua.coral.ugcc.admin.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;

public class AddNews extends Composite {
    interface AddNewsUiBinder extends UiBinder<HTMLPanel, AddNews> {
    }

    private static AddNewsUiBinder uiBinder = GWT.create(AddNewsUiBinder.class);

    @UiField(provided = true)
    public FlexTable flexTable;

    private CwConstants constants;

    public AddNews() {
        setupTable();
        initWidget(uiBinder.createAndBindUi(this));
    }

    private void setupTable() {
        flexTable = new FlexTable();
        flexTable.setCellSpacing(6);
        final FlexTable.FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();

        // Add a title to the form
        flexTable.setHTML(0, 0, constants.cwDecoratorPanelFormTitle());
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(
                0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        // Add some standard form options
        flexTable.setHTML(1, 0, constants.cwDecoratorPanelFormName());
        flexTable.setWidget(1, 1, new TextBox());
        flexTable.setHTML(2, 0, constants.cwDecoratorPanelFormDescription());
        flexTable.setWidget(2, 1, new TextBox());
    }

    public static interface CwConstants extends Constants {
        @DefaultStringValue("Add rounded corners to any Widget using the Decorator Panel")
        String cwDecoratorPanelDescription();

        @DefaultStringValue("Description:")
        String cwDecoratorPanelFormDescription();

        @DefaultStringValue("Name:")
        String cwDecoratorPanelFormName();

        @DefaultStringValue("Enter Search Criteria")
        String cwDecoratorPanelFormTitle();

        @DefaultStringValue("Decorator Panel")
        String cwDecoratorPanelName();
    }
}