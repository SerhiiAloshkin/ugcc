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

    private final Label label = new Label();

    public PagingPanel(final ListNewsView.Presenter presenter) {
        super();
        this.presenter = presenter;

        init();
    }

    private void init() {
        setWidth100();
        setAlign(Alignment.CENTER);

        addMember(label);

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
        final StringBuilder builder = new StringBuilder("1");
        float pages = (float) count / (float) size;
        for (int i = 1; i < pages; ) {
            builder.append(" | ");
            builder.append(++i);
        }
        label.setContents(builder.toString());
    }
}
