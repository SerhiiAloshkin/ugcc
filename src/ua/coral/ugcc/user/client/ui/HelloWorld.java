package ua.coral.ugcc.user.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

/**
 * Created with IntelliJ IDEA.
 * User: Noname
 * Date: 30.06.14
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {
    interface HelloWorldUiBinder extends UiBinder<DivElement, HelloWorld> {
    }

    private static HelloWorldUiBinder ourUiBinder = GWT.create(HelloWorldUiBinder.class);

    @UiField
    public SpanElement nameSpan;

    private DivElement root;

    public HelloWorld() {
        root = ourUiBinder.createAndBindUi(this);
    }

    public Element getElement() {
        return root;
    }

    public void setName(final String name) {
        nameSpan.setInnerText(name);
    }
}