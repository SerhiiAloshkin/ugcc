package ua.coral.ugcc.admin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.coral.ugcc.common.services.Service;

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

    String sendFile(String fileName);
}
