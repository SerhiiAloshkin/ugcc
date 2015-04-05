package ua.coral.ugcc.admin.server;

import com.google.appengine.api.blobstore.BlobKey;
import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static gwtupload.shared.UConsts.PARAM_BLOBKEY;

/**
 * Upload servlet which uses the FilesApiFileItemFactory using the GAE File API.
 * <p/>
 * To use this servlet you need:
 * <p/>
 * Add these lines to your web.xml
 * <p/>
 * <pre>
 * &lt;servlet&gt;
 * &lt;servlet-class&gt;gwtupload.server.gae.FilesApiUploadAction&lt;/servlet-class&gt;
 * &lt;/servlet&gt;
 * &lt;servlet-mapping&gt;
 * &lt;servlet-name&gt;uploadServlet&lt;/servlet-name&gt;
 * &lt;url-pattern&gt;gupld&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * &lt;servlet-mapping&gt;
 * &lt;servlet-name&gt;uploadServlet&lt;/servlet-name&gt;
 * &lt;url-pattern&gt;upload&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * </pre>
 * <p/>
 * Enable Session in your appengine-web.xml
 * <p/>
 * <pre>
 * &lt;sessions-enabled&gt;true&lt;/sessions-enabled&gt;
 * </pre>
 * <p/>
 * You can get the blob key in server client side using this code
 * <p/>
 * <pre>
 * uploader.addOnFinishUploadHandler(new OnFinishUploaderHandler() {
 * public void onFinish(IUploader uploader) {
 * if (uploader.getStatus() == Status.SUCCESS) {
 * String url = uploader.getServletPath() + "?blob-key=" + uploader.getServerInfo().message;
 * }
 * }
 * });
 * </pre>
 *
 * @author Vyacheslav Sokolov
 * @author Manolo Carrasco Moñino
 */
public class FilesApiUploadAction extends UploadAction {
    private static final long serialVersionUID = 3683112300714613746L;


    @Override
    public boolean isAppEngine() {
        return true;
    }

    @Override
    public String executeAction(HttpServletRequest request,
                                List<FileItem> sessionFiles) throws UploadActionException {
        String ret = "";
        for (FileItem i : sessionFiles) {
            if (!i.isFormField()) {
                ret += (ret.isEmpty() ? "" : " ") + ((FilesApiFileItemFactory.FilesAPIFileItem) i).getKey().getKeyString();
                logger.info("Received new file, stored in blobstore with the key: " + ret);
            }
        }
        return ret;
    }

    @Override
    protected FileItemFactory getFileItemFactory(long requestSize) {
        return new FilesApiFileItemFactory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String bkey = request.getParameter(PARAM_BLOBKEY);
        logger.info("Files doGet " + bkey);
        if (bkey != null) {
            logger.info("Serving a blobstore file with the key:" + bkey);
            FilesApiFileItemFactory.FilesAPIFileItem.getBlobstoreService().serve(new BlobKey(bkey), response);
        } else {
            super.doGet(request, response);
        }
    }
}