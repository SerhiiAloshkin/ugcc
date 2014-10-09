package ua.coral.ugcc.user.client.view.impl;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import ua.coral.ugcc.common.view.NewsView;

public class NewsViewImpl extends AbstractView implements NewsView {

    private Element element = DOM.createSpan();

    public NewsViewImpl() {
        super();
    }

    @Override
    protected IsWidget getContent() {
        return new Label("News");
    }

    @Override
    public void setContent(final String content) {
        element.setInnerText(content);
    }
}
