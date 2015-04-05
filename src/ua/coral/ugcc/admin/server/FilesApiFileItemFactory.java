package ua.coral.ugcc.admin.server;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemHeaders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.util.HashMap;

import static gwtupload.shared.UConsts.MULTI_SUFFIX;

/**
 * Upload factory based in the GAE File API.
 *
 * @author Vyacheslav Sokolov
 * @author Manolo Carrasco Moñino
 */
public class FilesApiFileItemFactory implements FileItemFactory, Serializable {

    private static final long serialVersionUID = 3683112300714613746L;

    public static class FilesAPIOutputStream extends OutputStream {
        private FileWriteChannel channel;
        private OutputStream stream;

        public FilesAPIOutputStream(FileService service, AppEngineFile file)
                throws IOException {
            channel = service.openWriteChannel(file, true);
            stream = Channels.newOutputStream(channel);
        }

        public void close() throws IOException {
            stream.close();
            channel.closeFinally();
        }

        public void flush() throws IOException {
            stream.flush();
        }

        public void write(byte[] b, int off, int len) throws IOException {
            stream.write(b, off, len);
        }

        public void write(byte[] b) throws IOException {
            stream.write(b);
        }

        public void write(int b) throws IOException {
            stream.write(b);
        }
    }

    public static class FilesAPIFileItem implements FileItem, HasBlobKey {
        private static final long serialVersionUID = 3683112300714613746L;
        private String field;
        private String type;
        private boolean formField;
        private String name;

        static private FileService fileService = FileServiceFactory.getFileService();
        static private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

        public static BlobstoreService getBlobstoreService() {
            return blobstoreService;
        }

        private AppEngineFile file = null;

        public FilesAPIFileItem(String fieldName, String contentType,
                                boolean isFormField, String fileName) throws IOException {
            field = fieldName;
            type = contentType;
            formField = isFormField;
            name = fileName;
            file = fileService.createNewBlobFile(contentType, fileName);
        }

        public void delete() {
            BlobKey key = getKey();
            if (key != null) {
                blobstoreService.delete(key);
                file = null;
            }
        }

        public byte[] get() {
            BlobKey key = getKey();
            if (key == null)
                return null;
            return blobstoreService.fetchData(key, 0, getSize() - 1);
        }

        public String getContentType() {
            return type;
        }

        public String getFieldName() {
            return field;
        }

        public InputStream getInputStream() throws IOException {
            if (file == null)
                return null;
            FileReadChannel channel = fileService.openReadChannel(file, false);
            return Channels.newInputStream(channel);
        }

        public String getName() {
            return name;
        }

        public OutputStream getOutputStream() throws IOException {
            if (file == null)
                return null;
            return new FilesAPIOutputStream(fileService, file);
        }

        public long getSize() {
            BlobKey key = getKey();
            if (key == null)
                return 0;
            BlobInfo info = new BlobInfoFactory().loadBlobInfo(key);
            if (info == null)
                return 0;
            return info.getSize();
        }

        public String getString() {
            return get().toString();
        }

        public String getString(String encoding)
                throws UnsupportedEncodingException {
            return new String(get(), encoding);
        }

        public boolean isFormField() {
            return formField;
        }

        public boolean isInMemory() {
            return false;
        }

        public void setFieldName(String arg0) {
            field = arg0;
        }

        public void setFormField(boolean arg0) {
            formField = arg0;
        }

        public void write(File arg0) throws Exception {
            throw new UnsupportedOperationException("Writing to file is not allowed");
        }

        public BlobKey getKey() {
            if (file == null)
                return null;
            return fileService.getBlobKey(file);
        }

        public String getKeyString() {
            BlobKey k = getKey();
            return k == null ? null : k.getKeyString();
        }

        public FileItemHeaders getHeaders() {
            return null;
        }

        public void setHeaders(FileItemHeaders headers) {
        }
    }

    private HashMap<String, Integer> map = new HashMap<String, Integer>();

    public FileItem createItem(String fieldName, String contentType,
                               boolean isFormField, String fileName) {

        if (fieldName.contains(MULTI_SUFFIX)) {
            Integer cont = map.get(fieldName) != null ? (map.get(fieldName) + 1) : 0;
            map.put(fieldName, cont);
            fieldName = fieldName.replace(MULTI_SUFFIX, "") + "-" + cont;
        }
        try {
            return new FilesAPIFileItem(fieldName, contentType, isFormField, fileName);
        } catch (IOException x) {
            x.printStackTrace();
            return null;
        }
    }
}