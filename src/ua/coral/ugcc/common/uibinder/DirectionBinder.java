package ua.coral.ugcc.common.uibinder;

import ua.coral.ugcc.common.presenter.impl.DirectionPresenter;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.maps.gwt.client.DirectionsRenderer;
import com.google.maps.gwt.client.DirectionsRequest;
import com.google.maps.gwt.client.DirectionsResult;
import com.google.maps.gwt.client.DirectionsService;
import com.google.maps.gwt.client.DirectionsStatus;
import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.MapTypeId;
import com.google.maps.gwt.client.TravelMode;

import org.gwtbootstrap3.client.ui.Button;

public class DirectionBinder extends Composite {
    interface DirectionBinderUiBinder extends UiBinder<HTMLPanel, DirectionBinder> {
    }

    private static DirectionBinderUiBinder ourUiBinder = GWT.create(DirectionBinderUiBinder.class);

    @UiField
    HTMLPanel directionPanel;
    @UiField
    Button toMap;

    private DirectionPresenter presenter;

    public DirectionBinder(final DirectionPresenter presenter) {
        this.presenter = presenter;

        initWidget(ourUiBinder.createAndBindUi(this));

        directionPanel.add(getDirectionPanel());
    }

    private SimplePanel getDirectionPanel() {
        final LatLng mapCenter = LatLng.create(46.9686762, 31.9867591);

        MapOptions mapOptions = MapOptions.create();
        mapOptions.setScaleControl(true);
        mapOptions.setMapTypeId(MapTypeId.ROADMAP);

        SimplePanel widg = new SimplePanel();
        widg.setSize("100%", "80%");

        final DirectionsRenderer renderer = DirectionsRenderer.create();
        final GoogleMap map = GoogleMap.create(widg.getElement(), mapOptions);

        map.addIdleListenerOnce(new GoogleMap.IdleHandler() {
            @Override
            public void handle() {
                map.triggerResize();
                renderer.setMap(map);
            }
        });

        renderer.setMap(map);

        final DirectionsService service = DirectionsService.create();
        final DirectionsRequest request = DirectionsRequest.create();

        if (Geolocation.isSupported()) {
            Geolocation.getIfSupported().getCurrentPosition(
                    new Callback<Position, PositionError>() {

                        @Override
                        public void onSuccess(Position result) {
                            Position.Coordinates coords = result.getCoordinates();
                            LatLng initialLocation = LatLng.create(coords.getLatitude(),
                                    coords.getLongitude());
                            request.setOrigin(initialLocation);
                            request.setDestination(mapCenter);
                            request.setTravelMode(TravelMode.DRIVING);

                            service.route(request, new DirectionsService.Callback() {
                                @Override
                                public void handle(final DirectionsResult a, final DirectionsStatus b) {
                                    if (b == DirectionsStatus.OK) {
                                        renderer.setDirections(a);
                                    }
                                }
                            });
                        }

                        @Override
                        public void onFailure(PositionError reason) {

                        }
                    });
        }

        return widg;
    }

    @UiHandler("toMap")
    public void goToMap(final ClickEvent event) {
        presenter.goToMap();
    }
}