package ua.coral.ugcc.common.uibinder;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.maps.gwt.client.*;

public class MapBinder extends Composite {
    interface MapBinderUiBinder extends UiBinder<HTMLPanel, MapBinder> {
    }

    private static MapBinderUiBinder ourUiBinder = GWT.create(MapBinderUiBinder.class);

    @UiField
    HTMLPanel mapPanel;
    @UiField
    HTMLPanel directionPanel;

    public MapBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));

        mapPanel.add(getMapPanel());
        directionPanel.add(getDirectionPanel());
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

        final DirectionsRenderer renderer = DirectionsRenderer.create();
        final GoogleMap map = GoogleMap.create(widg.getElement(), mapOptions);

        renderer.setMap(map);

        MarkerOptions markerOpts = MarkerOptions.create();
        markerOpts.setPosition(mapCenter);
        markerOpts.setMap(map);
        final Marker marker = Marker.create(markerOpts);

        InfoWindowOptions infoWindowOpts = InfoWindowOptions.create();
        infoWindowOpts.setContent("<b>Mykolaiv</b>");

        final InfoWindow infoWindow = InfoWindow.create();
        infoWindow.open(map);

        marker.addClickListener(new Marker.ClickHandler() {
            public void handle(MouseEvent event) {
                infoWindow.open(map, marker);
            }
        });

        return widg;
    }

    private SimplePanel getDirectionPanel() {
        final LatLng mapCenter = LatLng.create(46.9686762, 31.9867591);

        MapOptions mapOptions = MapOptions.create();
        mapOptions.setScaleControl(true);
        mapOptions.setCenter(mapCenter);
        mapOptions.setZoom(17);
        mapOptions.setMapTypeId(MapTypeId.ROADMAP);

        SimplePanel widg = new SimplePanel();
        widg.setSize("100%", "80%");

        final DirectionsRenderer renderer = DirectionsRenderer.create();
        final GoogleMap map = GoogleMap.create(widg.getElement(), mapOptions);

        renderer.setMap(map);

        MarkerOptions markerOpts = MarkerOptions.create();
        markerOpts.setPosition(mapCenter);
        markerOpts.setMap(map);
        final Marker marker = Marker.create(markerOpts);

        InfoWindowOptions infoWindowOpts = InfoWindowOptions.create();
        infoWindowOpts.setContent("<b>Mykolaiv</b>");

        final InfoWindow infoWindow = InfoWindow.create();
        infoWindow.open(map);

        marker.addClickListener(new Marker.ClickHandler() {
            public void handle(MouseEvent event) {
                infoWindow.open(map, marker);
            }
        });

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
}