import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repository<T> {
    private final HashMap<String, T> items = new HashMap<>();

    public synchronized T save(T entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null.");
        String id = readId(entity);
        items.put(id, entity);
        return entity;
    }

    @SafeVarargs
    public final synchronized void saveAll(T... entities) {
        if (entities == null) throw new IllegalArgumentException("Items cannot be null.");
        for (T entity : entities) save(entity);
    }

    public synchronized T findById(String id) { return items.get(id); }
    public synchronized ArrayList<T> findAll() { return new ArrayList<>(items.values()); }
    public synchronized boolean delete(String id) { return items.remove(id) != null; }
    public synchronized int size() { return items.size(); }

    private String readId(T entity) {
        Class<?> type = entity.getClass();
        while (type != null) {
            for (Field field : type.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        if (value == null || value.toString().isBlank())
                            throw new BankException("@Id field is empty: " + field.getName());
                        return value.toString();
                    } catch (ReflectiveOperationException | SecurityException e) {
                        throw new BankException("Cannot read @Id field.", e);
                    }
                }
            }
            type = type.getSuperclass();
        }
        throw new BankException("No field marked @Id found in " + entity.getClass().getSimpleName());
    }
}
