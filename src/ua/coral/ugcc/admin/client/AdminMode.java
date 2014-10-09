package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.common.activity.AppActivityMapper;
import ua.coral.ugcc.common.place.AppPlaceHistoryMapper;
import ua.coral.ugcc.common.place.MainPlace;
import ua.coral.ugcc.common.view.ClientFactory;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class AdminMode implements EntryPoint {

    private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

    // This app's personal client ID assigned by the Google APIs Console
    // (http://code.google.com/apis/console).
    private static final String GOOGLE_CLIENT_ID = "318390621392-7mgptu5bgce5a2vh3a0lfjabsr8b9nkl.apps.googleusercontent.com";

    // The auth scope being requested. This scope will allow the application to
    // identify who the authenticated user is.
    private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/plus.login";

//    // Use the implementation of Auth intended to be used in the GWT client app.
//    private static final Auth AUTH = Auth.get();
//
    private Place defaultPlace = new MainPlace("Main");
    private SimplePanel simplePanel = new SimplePanel();

    @Override
    public void onModuleLoad() {
//        addGoogleAuth();
//
//        // Export the JS method that can be called in pure JS
//        Auth.export();
        final ClientFactory clientFactory = GWT.create(ClientFactory.class);
        final EventBus eventBus = clientFactory.getEventBus();
        final PlaceController placeController = clientFactory.getPlaceController();

        final ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        final ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(simplePanel);

        final AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        RootPanel.get().add(simplePanel);
        historyHandler.handleCurrentHistory();
    }

    // Adds a button to the page that asks for authentication from Google.
//    private void addGoogleAuth() {
//        // Since the auth flow requires opening a popup window, it must be started
//        // as a direct result of a user action, such as clicking a button or link.
//        // Otherwise, a browser's popup blocker may block the popup.
//        final HTML button = new HTML("<button id='login'/>");
//        button.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID)
//                        .withScopes(PLUS_ME_SCOPE);
//
//                // Calling login() will display a popup to the user the first time it is
//                // called. Once the user has granted access to the application,
//                // subsequent calls to login() will not display the popup, and will
//                // immediately result in the callback being given the token to use.
//                AUTH.login(req, new Callback<String, Throwable>() {
//                    @Override
//                    public void onSuccess(String token) {
//                        //loadPage();
//                        test();
//                        button.setVisible(false);
//                        Window.alert("Got an OAuth token:\n" + token + "\n"
//                                + "Token expires in " + AUTH.expiresIn(req) + " ms\n");
//                    }
//
//                    @Override
//                    public void onFailure(Throwable caught) {
//                        Window.alert("Error:\n" + caught.getMessage());
//                    }
//                });
//            }
//        });
//        RootPanel.get().add(button);
//    }
//
//    private void test() {
////        Document.get().getBody().appendChild(helloWorld.getElement());
//        //  Document.get().getBody().appendChild(onInitialize().getElement());
//        RootPanel.get("content").add(onInitialize());
//    }
//
//    public Widget onInitialize() {
//        // Create the text area and toolbar
//        RichTextArea area = new RichTextArea();
//        area.setSize("100%", "14em");
//        RichTextToolbar toolbar = new RichTextToolbar(area);
//        toolbar.setWidth("100%");
//
//        // Add the components to a panel
//        Grid grid = new Grid(2, 1);
//        grid.setWidget(0, 0, toolbar);
//        grid.setWidget(1, 0, area);
//        grid.setWidth("100%");
//        return grid;
//    }
//
    private void loadPage() {
//        gui = new MainViewImpl();
//        serviceDelegate = new AdminModeServiceDelegate();
//
//        gui.serviceDelegate = serviceDelegate;
//        serviceDelegate.gui = gui;
//
//        gui.init();
//        serviceDelegate.listNews();
//        wireGUIEvents();
    }

//    private void wireGUIEvents() {
//        gui.newsGrid.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                HTMLTable.Cell cellForEvent = gui.newsGrid.getCellForEvent(event);
//                gui.gui_eventNewsGridClicked(cellForEvent);
//            }
//        });
//        gui.addButton.addClickHandler(new ClickHandler(){
//            public void onClick(ClickEvent event) {
//                gui.gui_eventAddButtonClicked();
//            }});
//        gui.updateButton.addClickHandler(new ClickHandler(){
//            public void onClick(ClickEvent event) {
//                gui.gui_eventUpdateButtonClicked();
//            }});
//        gui.addNewButton.addClickHandler(new ClickHandler(){
//            public void onClick(ClickEvent event) {
//                gui.gui_eventAddNewButtonClicked();
//            }});
//    }
}
