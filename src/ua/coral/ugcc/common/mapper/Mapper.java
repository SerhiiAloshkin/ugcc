package ua.coral.ugcc.common.mapper;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;
import ua.coral.ugcc.common.annotation.LongString;
import ua.coral.ugcc.common.dto.Dto;
import ua.coral.ugcc.common.exception.UGCCRuntimeException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Map;

public class Mapper {

    private Mapper() {
        super();
    }

    public static <E extends Dto> Entity getEntity(@NotNull final E object) {
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

    public static <E extends Dto> E getObject(final Class<E> target, @NotNull final Entity entity) {
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
}
