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

    public static final int SIZE = 10;
    public static final String MENU_BUTTON = "menuButton";
    public static final String MODULE_MENU_BUTTON = "moduleMenuButton";
    private Presenter presenter;

    public AbstractView() {
        final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Style.Unit.EM);
        dockLayoutPanel.addNorth(getMenuPanel(), SIZE);
        dockLayoutPanel.addNorth(getLogoPanel(), SIZE);
        dockLayoutPanel.addNorth(getMainContent(), SIZE);
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
        news.setStyleName(MENU_BUTTON);

        final Anchor press = new Anchor("Пресс-служба");
        press.setStyleName(MENU_BUTTON);

        final Anchor contacts = new Anchor("Контакты");
        contacts.setStyleName(MENU_BUTTON);

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

        dockLayoutPanel.addWest(getLeftMenu(), SIZE);
        dockLayoutPanel.addEast(getContent(), SIZE);

        return  dockLayoutPanel;
    }

    private DockLayoutPanel getLeftMenu() {
        final DockLayoutPanel layoutPanel = new DockLayoutPanel(Style.Unit.EM);
        final VerticalPanel panel = new VerticalPanel();

        final Anchor hyperlink = getLink("Головна", MODULE_MENU_BUTTON);
        hyperlink.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                presenter.goTo(new NewsPlace("News"));
            }
        });
        final Anchor hyperlink2 = getLink("Новини", MODULE_MENU_BUTTON);
        hyperlink2.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                presenter.goTo(new MainPlace("Main"));
            }
        });
        panel.add(hyperlink);
        panel.add(hyperlink2);
        panel.add(getLink("Про церкву", MODULE_MENU_BUTTON));
        panel.add(getLink("Екзархат", MODULE_MENU_BUTTON));
        panel.add(getLink("Для преси", MODULE_MENU_BUTTON));
        panel.add(getLink("Запитання і відповіді", MODULE_MENU_BUTTON));
        panel.add(getLink("Контакти", MODULE_MENU_BUTTON));
        layoutPanel.addNorth(panel, SIZE);
        layoutPanel.setStyleName("moduleMenu");

        return layoutPanel;
    }

    private Anchor getLink(final String label, final String style) {
        final Anchor hyperlink = new Anchor(label);
        hyperlink.setStyleName(style);
        return hyperlink;
    }
}
