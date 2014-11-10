package ua.coral.ugcc.admin.client.panel;

import ua.coral.ugcc.admin.client.view.ListNewsView;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class PagingPanel extends HLayout {

    private static final int PAGE_SIZE = 3;

    private ListNewsView.Presenter presenter;

    private int count;

    private int size = PAGE_SIZE;

    private int page = 0;

    public PagingPanel(final ListNewsView.Presenter presenter) {
        super();
        this.presenter = presenter;

        init();
    }

    private void init() {
        setWidth100();
        setAlign(Alignment.CENTER);

        presenter.countNews();
    }

    public void setItemsCount(final int count) {
        this.count = count;
    }

    public void setPageSize(final int size) {
        this.size = size;
    }

    public void setCurrentPage(final int page) {
        this.page = page;
    }

    public void buildPanel() {
        if (count > size) {
            addMember(new Label("1 | 2 | 3"));
        }
    }
}
