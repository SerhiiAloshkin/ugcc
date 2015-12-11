package ua.coral.ugcc.admin.server;

import ua.coral.ugcc.admin.client.AdminModeService;
import ua.coral.ugcc.admin.client.LoginInfo;
import ua.coral.ugcc.common.services.impl.AbstractServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AdminModeServiceImpl extends AbstractServiceImpl implements AdminModeService {
    @Override
    public String greetServer(String input) throws IllegalArgumentException {

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        // Escape data from the client to avoid cross-site script vulnerabilities.
        input = escapeHtml(input);
        userAgent = escapeHtml(userAgent);

        return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
                + userAgent;
    }

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    // TODO #11: implement login helper methods in service implementation

    @Override
    public String getUserEmail(final String token) {
        final UserService userService = UserServiceFactory.getUserService();
        final User user = userService.getCurrentUser();
        if (null != user) {
            return user.getEmail();
        } else {
            return "noreply@sample.com";
        }
    }

    @Override
    public LoginInfo login(final String requestUri) {
        final UserService userService = UserServiceFactory.getUserService();
        final User user = userService.getCurrentUser();
        final LoginInfo loginInfo = new LoginInfo();
        if (user != null && "sem.aleshkin@gmail.com".equals(user.getEmail())) {
            loginInfo.setLoggedIn(true);
            loginInfo.setName(user.getEmail());
            loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
        } else {
            loginInfo.setLoggedIn(false);
            loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
        }
        return loginInfo;
    }

    @Override
    public LoginInfo loginDetails(final String token) {
        String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + token;

        final StringBuffer r = new StringBuffer();
        try {
            final URL u = new URL(url);
            final URLConnection uc = u.openConnection();
            final int end = 1000;
            InputStreamReader isr = null;
            BufferedReader br = null;
            try {
                isr = new InputStreamReader(uc.getInputStream());
                br = new BufferedReader(isr);
                final int chk = 0;
                while ((url = br.readLine()) != null) {
                    if ((chk >= 0) && ((chk < end))) {
                        r.append(url).append('\n');
                    }
                }
            } catch (final java.net.ConnectException cex) {
                r.append(cex.getMessage());
            } catch (final Exception ex) {
                System.err.println(ex.getMessage());
            } finally {
                try {
                    br.close();
                } catch (final Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
        }

        final LoginInfo loginInfo = new LoginInfo();
        try {
            final JsonFactory f = new JsonFactory();
            JsonParser jp;
            jp = f.createJsonParser(r.toString());
            jp.nextToken();
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                final String fieldname = jp.getCurrentName();
                jp.nextToken();
                if ("picture".equals(fieldname)) {
                    loginInfo.setPictureUrl(jp.getText());
                } else if ("name".equals(fieldname)) {
                    loginInfo.setName(jp.getText());
                } else if ("email".equals(fieldname)) {
                    loginInfo.setEmailAddress(jp.getText());
                }
            }
        } catch (final JsonParseException ex) {
            System.err.println(ex.getMessage());
        } catch (final IOException ex) {
            System.err.println(ex.getMessage());
        }
        return loginInfo;
    }
}