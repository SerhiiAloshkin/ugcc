package ua.coral.ugcc.common.component;

import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlPosition;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;

public class GrowlUtils {

    private GrowlUtils() {
        super();
    }

    public static void showMessage(final String title, final String message, final GrowlType type,
                                   final IconType iconType) {
        GrowlOptions go = new GrowlOptions();
        go.setType(type);
        go.setTemplate("<div data-growl=\"container\" class=\"alert\" role=\"alert\">" +
                "<button type=\"button\" class=\"close\" data-growl=\"dismiss\">" +
                "<span aria-hidden=\"true\">×</span>" +
                "<span class=\"sr-only\">Close</span>" +
                "</button>" +
                "<b><span data-growl=\"title\"></span></b><br/>" +
                "<span data-growl=\"icon\"></span>" +
                "<span data-growl=\"message\"></span>" +
                "<a href=\"#\" data-growl=\"url\"></a>" +
                "</div>");
        go.makeDefault();
        go.setPosition(GrowlPosition.TOP_CENTER);
        Growl.growl("\t" + title + "\t", "\t" + message + "\t", iconType, go);
    }
}
