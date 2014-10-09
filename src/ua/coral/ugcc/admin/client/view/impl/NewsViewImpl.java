package ua.coral.ugcc.admin.client.view.impl;

import ua.coral.ugcc.common.view.NewsView;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.smartgwt.client.widgets.layout.HLayout;

public class NewsViewImpl extends AbstractView implements NewsView {

    private Element element = DOM.createSpan();

    public NewsViewImpl() {
        super();
    }

    @Override
    protected HLayout getContent() {
        return new HLayout();
    }

    @Override
    public void setContent(final String content) {
        element.setInnerText(content);
    }
}
