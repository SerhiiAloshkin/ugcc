package ua.coral.ugcc.user.client.view.impl;

import ua.coral.ugcc.common.view.View;
import ua.coral.ugcc.common.place.MainPlace;
import ua.coral.ugcc.common.place.NewsPlace;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class AbstractView extends Composite implements View {

    private Presenter presenter;

    public AbstractView() {
        final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Style.Unit.EM);
        dockLayoutPanel.addNorth(getMenuPanel(), 2);
        dockLayoutPanel.addNorth(getLogoPanel(), 16);
        dockLayoutPanel.addNorth(getMainContent(), 10);
        dockLayoutPanel.setStyleName("mainDockPanel");

        initWidget(dockLayoutPanel);
    }

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    protected abstract IsWidget getContent();

    private HorizontalPanel getMenuPanel() {
        final HorizontalPanel panel = new HorizontalPanel();
        panel.setStyleName("menu");

        final Anchor news = new Anchor("Новости");
        news.setStyleName("menuButton");

        final Anchor press = new Anchor("Пресс-служба");
        press.setStyleName("menuButton");

        final Anchor contacts = new Anchor("Контакты");
        contacts.setStyleName("menuButton");

        panel.add(news);
        panel.add(press);
        panel.add(contacts);
        return panel;
    }

    private Image getLogoPanel() {
        final Image image = new Image();
        image.setUrl("/src/main/web/images/logo2.png");

        return image;
    }

    private DockLayoutPanel getMainContent() {
        final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Style.Unit.PCT);

        dockLayoutPanel.addWest(getLeftMenu(), 25);
        dockLayoutPanel.addEast(getContent(), 75);

        return  dockLayoutPanel;
    }

    private DockLayoutPanel getLeftMenu() {
        final DockLayoutPanel layoutPanel = new DockLayoutPanel(Style.Unit.EM);
        final VerticalPanel panel = new VerticalPanel();

        final Anchor hyperlink = getLink("Головна", "moduleMenuButton");
        hyperlink.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                presenter.goTo(new NewsPlace("News"));
            }
        });
        final Anchor hyperlink2 = getLink("Новини", "moduleMenuButton");
        hyperlink2.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                presenter.goTo(new MainPlace("Main"));
            }
        });
        panel.add(hyperlink);
        panel.add(hyperlink2);
        panel.add(getLink("Про церкву", "moduleMenuButton"));
        panel.add(getLink("Екзархат", "moduleMenuButton"));
        panel.add(getLink("Для преси", "moduleMenuButton"));
        panel.add(getLink("Запитання і відповіді", "moduleMenuButton"));
        panel.add(getLink("Контакти", "moduleMenuButton"));
        layoutPanel.addNorth(panel, 20);
        layoutPanel.setStyleName("moduleMenu");

        return layoutPanel;
    }

    private Anchor getLink(final String label, final String style) {
        final Anchor hyperlink = new Anchor(label);
        hyperlink.setStyleName(style);
        return hyperlink;
    }
}
