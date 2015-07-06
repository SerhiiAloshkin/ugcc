package ua.coral.ugcc.admin.server;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.MediaByteArraySource;
import com.google.gdata.data.photos.PhotoEntry;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class FileUpload extends HttpServlet {

    /**
     * Directory to store user credentials.
     */
    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"), ".store/plus_sample");
    private static final String CLIENT_ID = "318390621392-obe6svtjgf95l8vu9m80f5t8hgp3gb2e.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "vgHUSYD3qu_69WLXjPid7u02";
    private static final String REDIRECT_URI = "https://developers.google.com/oauthplayground";

    private static AppEngineDataStoreFactory dataStoreFactory;

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport httpTransport;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private String token;

    public void setAuthToken(final String token) {
        this.token = token;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletFileUpload upload = new ServletFileUpload();

        try {
            FileItemIterator iter = upload.getItemIterator(request);

            while (iter.hasNext()) {
                FileItemStream item = iter.next();

                String name = item.getFieldName();
                InputStream stream = item.openStream();

                // Process the input stream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[8192];
                while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, len);
                }

                final PicasawebService service = new PicasawebService("UgccSite");
                service.setOAuth2Credentials(authorize());
                service.setConnectTimeout(0);
                service.setReadTimeout(0);
                final MediaByteArraySource fileSource = new MediaByteArraySource(out.toByteArray(), "image/jpeg");
                final URL url = new URL("https://picasaweb.google.com/data/feed/api/user/106984661305245773041/albumid/6136237850628250209");
                PhotoEntry returnedPhoto = service.insert(url, PhotoEntry.class, fileSource);
                String href = returnedPhoto.getHtmlLink().getHref();

                if (returnedPhoto.getMediaContents().size() > 0) {
                    href = returnedPhoto.getMediaContents().get(0).getUrl();
                }
                response.setContentType("text/plain");
                response.getWriter().print(href);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Authorizes the installed application to access user's protected data.
     */
    private Credential authorize() throws Exception {

//        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        dataStoreFactory = AppEngineDataStoreFactory.getDefaultInstance();
//
//        // load client secrets
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
//                new InputStreamReader(FileUpload.class.getResourceAsStream("/client_secrets.json")));
//        if (clientSecrets.getDetails().getClientId().startsWith("Enter")
//                || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
//            System.out.println(
//                    "Enter Client ID and Secret from https://code.google.com/apis/console/?api=plus "
//                            + "into plus-cmdline-sample/src/main/resources/client_secrets.json");
//        }
//        // set up authorization code flow
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                httpTransport, JSON_FACTORY, clientSecrets, Collections.singleton(PlusScopes.PLUS_ME))
//                .setCredentialDataStore(StoredCredential.getDefaultDataStore(dataStoreFactory)).build();
//        // authorize
//        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        TokenResponse tokenResponse = new TokenResponse();

        AuthorizationCodeFlow.Builder codeFlowBuilder =
                new GoogleAuthorizationCodeFlow.Builder(httpTransport,
                        jsonFactory,
                        CLIENT_ID,
                        CLIENT_SECRET,
                        Arrays.asList("https://picasaweb.google.com/data/"));

        AuthorizationCodeFlow codeFlow = codeFlowBuilder.build();
        AuthorizationCodeRequestUrl authorizationUrl = codeFlow.newAuthorizationUrl();
        authorizationUrl.setRedirectUri(REDIRECT_URI);

        //URLConnection connection = HttpURLConnection.
        System.out.println("Go to the following address:\n" + authorizationUrl);
        System.out.println("What is the 'code' url parameter?");
        String code = new Scanner(System.in).nextLine();


        //Use the code returned by the Google Authentication Server to generate an Access Code
        AuthorizationCodeTokenRequest tokenRequest = codeFlow.newTokenRequest(code);
        tokenRequest.setRedirectUri(REDIRECT_URI);
        tokenResponse = tokenRequest.execute();

        String GOOGLE_REFRESH_TOKEN = "";
        String GOOGLE_ACCESS_TOKEN = "";

        GOOGLE_REFRESH_TOKEN = tokenResponse.getRefreshToken();
        GOOGLE_ACCESS_TOKEN = tokenResponse.getAccessToken();

        GoogleCredential credential = new GoogleCredential.Builder()
                .setClientSecrets(CLIENT_ID,
                        CLIENT_SECRET)
                .setJsonFactory(jsonFactory)
                .setTransport(httpTransport)
                .build()
                .setAccessToken(GOOGLE_ACCESS_TOKEN)
                .setRefreshToken(GOOGLE_REFRESH_TOKEN);

        return credential;
    }

//    public String upload(String fullpathToImage)
//    {
//        //The url of the image
//        String resultingURL = new String();
//
//        try
//        {
//
//            // These are not needed
//            String GOOGLE_APP_NAME = "TralhasVariasScanPoster";
//
//            String GOOGLE_REFRESH_TOKEN = "";
//            String GOOGLE_ACCESS_TOKEN = "";
//
//            HttpTransport httpTransport = new NetHttpTransport();
//            JsonFactory jsonFactory = new JacksonFactory();
//            TokenResponse tokenResponse = new TokenResponse();
//
//            //Check of we have a previous Refresh Token cached
//            if (Config.GOOGLE_OAUTH_REFRESH_TOKEN.length() == 0)
//            {
//                //No Google OAuth2 Key has been previously cached
//
//                //Request the user to grant access to the Picasa Resource (uses the Google Authentication servers)
//                AuthorizationCodeFlow.Builder codeFlowBuilder =
//                        new GoogleAuthorizationCodeFlow.Builder(httpTransport,
//                                jsonFactory,
//                                Config.GOOGLE_CLIENT_ID,
//                                Config.GOOGLE_CLIENT_SECRET,
//                                Arrays.asList(Config.GOOGLE_SCOPE_PICASA));
//
//                AuthorizationCodeFlow codeFlow = codeFlowBuilder.build();
//                AuthorizationCodeRequestUrl authorizationUrl = codeFlow.newAuthorizationUrl();
//                authorizationUrl.setRedirectUri(Config.GOOGLE_REDIRECT_URI);
//
//                System.out.println("Go to the following address:\n" + authorizationUrl);
//                System.out.println("What is the 'code' url parameter?");
//                String code = new Scanner(System.in).nextLine();
//
//                //Use the code returned by the Google Authentication Server to generate an Access Code
//                AuthorizationCodeTokenRequest tokenRequest = codeFlow.newTokenRequest(code);
//                tokenRequest.setRedirectUri(Config.GOOGLE_REDIRECT_URI);
//                tokenResponse = tokenRequest.execute();
//
//                GOOGLE_REFRESH_TOKEN = tokenResponse.getRefreshToken();
//                GOOGLE_ACCESS_TOKEN = tokenResponse.getAccessToken();
//
//                //Store the Refresh Token for later usage (this avoid having to request the user to
//                //Grant access to the application via the webbrowser again
//                saveTextFile(Config.GOOGLE_OAUTH_REFRESH_TOKEN_FILE, GOOGLE_REFRESH_TOKEN);
//            }
//            else
//            {
//                //There is a Google OAuth2 Key cached previously.
//                //Use the refresh token to get a new Access Token
//
//                //Get the cached Refresh Token
//                GOOGLE_REFRESH_TOKEN = new String(Config.GOOGLE_OAUTH_REFRESH_TOKEN);
//
//                //Now we need to get a new Access Token using our previously cached Refresh Token
//                RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(httpTransport,
//                        jsonFactory,
//                        new GenericUrl(Config.GOOGLE_TOKEN_SERVER_URL),
//                        GOOGLE_REFRESH_TOKEN);
//
//                refreshTokenRequest.setClientAuthentication(new BasicAuthentication(Config.GOOGLE_CLIENT_ID, Config.GOOGLE_CLIENT_SECRET));
//                refreshTokenRequest.setScopes(Arrays.asList(Config.GOOGLE_SCOPE_PICASA));
//
//                tokenResponse = refreshTokenRequest.execute();
//
//                //Get and set the Refresn the Access Tokens
//                GOOGLE_ACCESS_TOKEN = new String(tokenResponse.getAccessToken());
//            }
//
//            //At this point we have a valid Google Access Token
//            //Let us access Picasa then!
//            GoogleCredential credential = new GoogleCredential.Builder()
//                    .setClientSecrets(Config.GOOGLE_CLIENT_ID, Config.GOOGLE_CLIENT_SECRET)
//                    .setJsonFactory(jsonFactory)
//                    .setTransport(httpTransport)
//                    .build()
//                    .setAccessToken(GOOGLE_ACCESS_TOKEN)
//                    .setRefreshToken(GOOGLE_REFRESH_TOKEN);
//
//            PicasawebService picasaWebSvc = new PicasawebService("GOOGLE_APP_NAME");
//            picasaWebSvc.setOAuth2Credentials(credential);
//
//            URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/" + Config.PICASAWEB_LOGIN + "/albumid/" + Config.PICASAWEB_ALBUM_ID);
//
//            MediaFileSource myMedia = new MediaFileSource(new File(fullpathToImage), "image/jpeg");
//            PhotoEntry returnedPhoto = picasaWebSvc.insert(feedUrl, PhotoEntry.class, myMedia);
//
//            resultingURL = returnedPhoto.getMediaContents().get(0).getUrl();
//
//            if (resultingURL.toLowerCase().contains("please check your firewall") || resultingURL.toLowerCase().contains("error"))
//            {
//                throw new Exception("The Windows Firewall seems to be blocking the upload...");
//            }
//
//            System.out.println("...DONE!");
//            System.out.println("Cover page URL: " + resultingURL);
//        }
//        catch (Exception e)
//        {
//            System.err.println("[BloggerImageUploader.java]: There was an error: " + e.getMessage());
//            return "";
//        }
//
//        //The result
//        return resultingURL;
//    }
}