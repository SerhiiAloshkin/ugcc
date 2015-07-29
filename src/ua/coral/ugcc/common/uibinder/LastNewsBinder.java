package ua.coral.ugcc.common.uibinder;

import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.gwtbootstrap3.client.ui.Row;

public class LastNewsBinder extends Composite {

    private static final int COLUMN_COUNT = 3;

    interface LastNewsBinderUiBinder extends UiBinder<HTMLPanel, LastNewsBinder> {
    }

    private static LastNewsBinderUiBinder ourUiBinder = GWT.create(LastNewsBinderUiBinder.class);

    @UiField
    HTMLPanel lastNewsRows;

    public LastNewsBinder(final List<News> newsList) {
        initWidget(ourUiBinder.createAndBindUi(this));

        int index = 1;
        Row row = null;
        for (final News news : newsList) {
            int remainder = index % COLUMN_COUNT;
            if (index == 1 || remainder == 0) {
                row = new Row();
                lastNewsRows.add(row);
            }
            row.add(new OneLastNewsBinder(news));
            index++;
        }
    }
}