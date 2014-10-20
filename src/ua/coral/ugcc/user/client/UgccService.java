package ua.coral.ugcc.user.client;

import ua.coral.ugcc.common.services.Service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ugccService")
public interface UgccService extends RemoteService, Service {

    /**
     * Utility/Convenience class.
     * Use UgccService.App.getInstance() to access static instance of ugccServiceAsync
     */
    public static class App {
        private static final UgccServiceAsync INSTANCE = GWT.create(UgccService.class);

        private App() {
            super();
        }

        public static synchronized UgccServiceAsync getInstance() {
            return INSTANCE;
        }
    }
}
