package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.admin.client.activity.AppActivityMapper;
import ua.coral.ugcc.admin.client.view.ClientFactory;
import ua.coral.ugcc.common.client.AbstractEntryPoint;
import ua.coral.ugcc.common.place.AppPlaceHistoryMapper;
import ua.coral.ugcc.common.place.MainPlace;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class AdminMode extends AbstractEntryPoint {

    private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

    // This app's personal client ID assigned by the Google APIs Console
    // (http://code.google.com/apis/console).
    private static final String GOOGLE_CLIENT_ID = "318390621392-7mgptu5bgce5a2vh3a0lfjabsr8b9nkl.apps.googleusercontent.com";

    // The auth scope being requested. This scope will allow the application to
    // identify who the authenticated user is.
    private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/plus.login";

    // Use the implementation of Auth intended to be used in the GWT client app.
    private static final Auth AUTH = Auth.get();

    private final HTML button = new HTML("<button id='login'/>");

    private final SimplePanel simplePanel = new SimplePanel();

    @Override
    public void onModuleLoad() {
        loadPage();
        //addGoogleAuth();

        // Export the JS method that can be called in pure JS
        //Auth.export();
    }

    // Adds a button to the page that asks for authentication from Google.
    private void addGoogleAuth() {
        // Since the auth flow requires opening a popup window, it must be started
        // as a direct result of a user action, such as clicking a button or link.
        // Otherwise, a browser's popup blocker may block the popup.
        button.addClickHandler(new AdminClickHandler());
        RootPanel.get().add(button);
    }

    private void onClick() {
        final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID)
                .withScopes(PLUS_ME_SCOPE);

        // Calling login() will display a popup to the user the first time it is
        // called. Once the user has granted access to the application,
        // subsequent calls to login() will not display the popup, and will
        // immediately result in the callback being given the token to use.
        AUTH.login(req, new Callback<String, Throwable>() {
            @Override
            public void onSuccess(String token) {
                loadPage();
                button.setVisible(false);
                Window.alert("Got an OAuth token:\n" + token + "\n"
                        + "Token expires in " + AUTH.expiresIn(req) + " ms\n");
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error:\n" + caught.getMessage());
            }
        });
    }

    private void loadPage() {
        final ClientFactory clientFactory = GWT.create(ClientFactory.class);
        final EventBus eventBus = clientFactory.getEventBus();
        final PlaceController placeController = clientFactory.getPlaceController();

        final ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        final ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(simplePanel);

        final AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, getPlace());

        RootPanel.get().add(simplePanel);
        historyHandler.handleCurrentHistory();
    }

    @Override
    protected Place getPlace() {
        return new MainPlace("Main");
    }

    private class AdminClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            AdminMode.this.onClick();
        }
    }
}
