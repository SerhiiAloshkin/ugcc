package ua.coral.ugcc.common.client;

public enum UGCCAuth {

    GOOGLE_AUTH_URL("https://accounts.google.com/o/oauth2/auth"),
    CLIENT_ID("318390621392-obe6svtjgf95l8vu9m80f5t8hgp3gb2e.apps.googleusercontent.com"),
    //CLIENT_ID("318390621392-7mgptu5bgce5a2vh3a0lfjabsr8b9nkl.apps.googleusercontent.com"),
    CLIENT_SECRET("vgHUSYD3qu_69WLXjPid7u02"),
    //CLIENT_SECRET("tShiIh1Tdb-tkB8OCzsWktti"),
    PLUS_SCOPE("https://www.googleapis.com/auth/plus.login"),
    PICASA_SCOPE("https://picasaweb.google.com/data/");

    private final String value;

    UGCCAuth(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UGCCAuth{value='" + value + "'}";
    }
}
