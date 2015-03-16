package ua.coral.ugcc.common.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.MapTypeId;
import com.google.maps.gwt.client.Marker;
import com.google.maps.gwt.client.MarkerOptions;
import org.gwtbootstrap3.client.ui.Button;
import ua.coral.ugcc.common.presenter.impl.MapPresenter;

public class MapBinder extends Composite {
    interface MapBinderUiBinder extends UiBinder<HTMLPanel, MapBinder> {
    }

    private static MapBinderUiBinder ourUiBinder = GWT.create(MapBinderUiBinder.class);

    @UiField
    HTMLPanel mapPanel;
    @UiField
    Button toDirection;

    private MapPresenter presenter;

    public MapBinder(final MapPresenter presenter) {
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));

        mapPanel.add(getMapPanel());
    }

    private SimplePanel getMapPanel() {
        final LatLng mapCenter = LatLng.create(46.9686762, 31.9867591);

        MapOptions mapOptions = MapOptions.create();
        mapOptions.setScaleControl(true);
        mapOptions.setCenter(mapCenter);
        mapOptions.setZoom(17);
        mapOptions.setMapTypeId(MapTypeId.ROADMAP);

        SimplePanel widg = new SimplePanel();
        widg.setSize("100%", "80%");

        final GoogleMap map = GoogleMap.create(widg.getElement(), mapOptions);

        map.addIdleListenerOnce(new GoogleMap.IdleHandler() {
            @Override
            public void handle() {
                map.triggerResize();
                map.setCenter(mapCenter);
            }
        });

        MarkerOptions markerOpts = MarkerOptions.create();
        markerOpts.setPosition(mapCenter);
        markerOpts.setMap(map);
        Marker.create(markerOpts);

        return widg;
    }

    @UiHandler("toDirection")
    public void goToDirection(final ClickEvent event) {
        presenter.goToDirection();
    }
}