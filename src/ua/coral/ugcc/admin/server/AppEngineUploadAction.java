package ua.coral.ugcc.admin.server;

import gwtupload.server.AbstractUploadListener;
import gwtupload.server.UploadAction;
import org.apache.commons.fileupload.FileItemFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Upload servlet for the GwtUpload library's deployed in Google App-engine.
 * </p>
 * <p/>
 * <b>Limitations in Google Application Engine:</b>
 * <ul>
 * <li>It doesn't support writing to file-system, so this servlet stores
 * fileItems in memcache using CacheableFileItem</li>
 * <li>The request size is limited to 512 KB, so this servlet hardcodes the
 * maxSize to 512</li>
 * <li>The limit for session and cache objects is 1024 KB</li>
 * <li>The time spent to process a request is limited, so this servlet limits
 * the sleep delay to a maximum of 50ms</li>
 * </ul>
 *
 * @author Manolo Carrasco Moñino
 */
public class AppEngineUploadAction extends UploadAction {

    private static final long serialVersionUID = -2569300604226532811L;

    @Override
    public void checkRequest(HttpServletRequest request) {
        super.checkRequest(request);
        if (request.getContentLength() > MemCacheFileItemFactory.DEFAULT_REQUEST_SIZE + 1024) {
            throw new RuntimeException(
                    "Google appengine doesn't allow requests with a size greater than "
                            + MemCacheFileItemFactory.DEFAULT_REQUEST_SIZE + " Bytes");
        }
    }


    @Override
    public boolean isAppEngine() {
        return true;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        useBlobstore = false;
        if (uploadDelay > 0) {
            uploadDelay = Math.max(50, uploadDelay);
        }
        if (maxSize > MemCacheFileItemFactory.DEFAULT_REQUEST_SIZE) {
            maxSize = MemCacheFileItemFactory.DEFAULT_REQUEST_SIZE;
            logger.info("GAEE-UPLOAD-SERVLET init: maxSize=" + maxSize
                    + ", slowUploads=" + uploadDelay + ", isAppEngine=" + isAppEngine()
                    + ", useBlobstore=" + useBlobstore);
        }
    }

    @Override
    protected final AbstractUploadListener createNewListener(
            HttpServletRequest request) {
        return new MemCacheUploadListener(uploadDelay, request.getContentLength());
    }

    @Override
    protected final AbstractUploadListener getCurrentListener(
            HttpServletRequest request) {
        return MemCacheUploadListener.current(request.getSession().getId());
    }

    @Override
    protected final FileItemFactory getFileItemFactory(long requestSize) {
        return new MemCacheFileItemFactory();
    }

}
