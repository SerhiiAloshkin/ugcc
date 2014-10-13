package ua.coral.ugcc.common.mapper;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;
import ua.coral.ugcc.common.annotation.LongString;
import ua.coral.ugcc.common.dto.Dto;
import ua.coral.ugcc.common.exception.UGCCRuntimeException;

import java.lang.reflect.Field;
import java.util.Map;

public class Mapper {

    private Mapper() {
        super();
    }

    public static <E extends Dto> Entity getEntity(final E object) {
        if (object != null) {
            try {
                final Field[] fields = object.getClass().getDeclaredFields();
                final Entity entity = new Entity(object.getClass().getSimpleName(), object.getId());
                for (final Field field : fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(LongString.class)) {
                        entity.setProperty(field.getName(), new Text(field.get(object).toString()));
                    } else {
                        entity.setProperty(field.getName(), field.get(object));
                    }

                }
                return entity;
            } catch (IllegalAccessException ex) {
                throw new UGCCRuntimeException(ex);
            }
        }
        throw new IllegalArgumentException("Mapped object should be not null");
    }

    public static <E extends Dto> E getObject(final Class<E> target, final Entity entity) {
        if (entity != null) {
            try {
                final E object  = target.newInstance();
                final Map<String, Object> map = entity.getProperties();
                for (final Map.Entry<String, Object> entry : map.entrySet()) {
                    final Field field = object.getClass().getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(LongString.class)) {
                        field.set(object, ((Text) entry.getValue()).getValue());
                    } else {
                        field.set(object, entry.getValue());
                    }
                }
                return object;
            } catch (InstantiationException | IllegalAccessException | NoSuchFieldException ex) {
                throw new UGCCRuntimeException(ex);
            }
        }
        throw new IllegalArgumentException("Mapped object should be not null");
    }
}
