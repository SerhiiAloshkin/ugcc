package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.common.services.Service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

@RemoteServiceRelativePath("AdminModeService")
public interface AdminModeService extends RemoteService, Service {

    // Sample interface method of remote interface
    String getMessage(String msg);



    /**
     * Utility/Convenience class.
     * Use AdminModeService.App.getInstance() to access static instance of AdminModeServiceAsync
     */
    public static class App {
        private static final AdminModeServiceAsync ourInstance = (AdminModeServiceAsync) GWT.create(AdminModeService.class);

        public static AdminModeServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
