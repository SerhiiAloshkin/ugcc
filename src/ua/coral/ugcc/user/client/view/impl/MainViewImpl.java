package ua.coral.ugcc.user.client.view.impl;

import ua.coral.ugcc.common.view.MainView;
import ua.coral.ugcc.user.client.ui.HelloWorld;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

public class MainViewImpl extends AbstractView implements MainView {

    private Element element = DOM.createSpan();

    public MainViewImpl() {
        super();
    }

    @Override
    protected IsWidget getContent() {
        final DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.EM);

        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setName("World");
        panel.addNorth(HTMLPanel.wrap(helloWorld.getElement()), 10);

        panel.setStyleName("bordered");
        return panel;
    }

    @Override
    public void setContent(String content) {
        element.setInnerText(content);
    }
}
