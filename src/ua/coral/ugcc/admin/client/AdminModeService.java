package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.common.services.Service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AdminModeService")
public interface AdminModeService extends RemoteService, Service {

    /**
     * Utility/Convenience class.
     * Use AdminModeService.App.getInstance() to access static instance of AdminModeServiceAsync
     */
    public static class App {
        private static final AdminModeServiceAsync OUR_INSTANCE = (AdminModeServiceAsync) GWT.create(AdminModeService.class);

        private App() {
            super();
        }

        public static AdminModeServiceAsync getInstance() {
            return OUR_INSTANCE;
        }
    }

    String greetServer(String name) throws IllegalArgumentException;

    String getUserEmail(String token);

    LoginInfo login(String requestUri);

    void logout();

    LoginInfo loginDetails(String token);
}
