package io.github.mizinchik.dsl;

import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;
import groovy.lang.MetaProperty;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public class Config extends GroovyObjectSupport {
    public void methodMissing(String name, Object args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        MetaProperty metaProperty = getMetaClass().getMetaProperty(name);
        if (metaProperty != null) {
            Closure<?> closure = (Closure<?>) ((Object[]) args)[0];
            Class<?> type = metaProperty.getType();
            Constructor<?> constructor = type.getConstructor();
            Object value = getProperty(name) == null ?
                    constructor.newInstance() :
                    getProperty(name);
            closure.setDelegate(value);
            closure.setResolveStrategy(Closure.DELEGATE_FIRST);
            closure.call();
            setProperty(name, value);
        } else {
            throw new IllegalArgumentException("No such field: " + name);
        }
    }

    public void postProcess() throws NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        for (MetaProperty metaProperty : getMetaClass().getProperties()) {
            Object value = getProperty(metaProperty.getName());
            if (Collection.class.isAssignableFrom(metaProperty.getType()) &&
                    value instanceof Collection) {
                ParameterizedType collectionType = (ParameterizedType) getClass().
                        getDeclaredField(metaProperty.getName()).getGenericType();
                Class itemClass = (Class)collectionType.getActualTypeArguments()[0];
                if (Config.class.isAssignableFrom(itemClass)) {
                    Collection collection = (Collection) value;
                    Collection newValue = collection.getClass().newInstance();
                    for (Object o : collection) {
                        if (o instanceof Closure) {
                            Object item = itemClass.getConstructor().newInstance();
                            ((Config) item).setProperty("scriptPath", "src/main/resources/config.groovy");
                            ((Closure) o).setDelegate(item);
                            ((Closure) o).setResolveStrategy(Closure.DELEGATE_FIRST);
                            ((Closure) o).call();
                            ((Config) item).postProcess();
                            newValue.add(item);
                        } else {
                            newValue.add(o);
                        }
                    }
                    setProperty(metaProperty.getName(), newValue);
                }
            }
        }
    }
}
