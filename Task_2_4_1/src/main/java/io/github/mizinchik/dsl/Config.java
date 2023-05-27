package io.github.mizinchik.dsl;

import groovy.lang.*;
import groovy.util.DelegatingScript;
import lombok.SneakyThrows;
import org.apache.groovy.groovysh.Main;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.Collection;

public class Config extends GroovyObjectSupport {
    protected URI scriptPath;

    @SneakyThrows
    public void runFrom(URI uri) {
        this.scriptPath = uri;
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName());
        GroovyShell sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc);
        DelegatingScript script = (DelegatingScript)sh.parse(uri);
        script.setDelegate(this);
        script.run();
    }

    @SneakyThrows
    @SuppressWarnings("unused")
    public void methodMissing(String name, Object args) {
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
            this.postProcess();
        } else {
            throw new IllegalArgumentException("No such field: " + name);
        }
    }

    @SneakyThrows
    public void postProcess() {
        for (MetaProperty metaProperty : getMetaClass().getProperties()) {
            Object value = getProperty(metaProperty.getName());
            if (Collection.class.isAssignableFrom(metaProperty.getType()) &&
                    value instanceof Collection) {
                ParameterizedType collectionType = (ParameterizedType) getClass().
                        getDeclaredField(metaProperty.getName()).getGenericType();
                Class<?> itemClass = (Class<?>)collectionType.getActualTypeArguments()[0];
                if (Config.class.isAssignableFrom(itemClass)) {
                    Collection<?> collection = (Collection<?>) value;
                    @SuppressWarnings("unchecked") Collection<Object> newValue = collection.
                            getClass().getDeclaredConstructor().newInstance();
                    for (Object o : collection) {
                        if (o instanceof Closure<?>) {
                            Object item = itemClass.getDeclaredConstructor().newInstance();
                            ((Config) item).setProperty("scriptPath", scriptPath);
                            ((Closure<?>) o).setDelegate(item);
                            ((Closure<?>) o).setResolveStrategy(Closure.DELEGATE_FIRST);
                            ((Closure<?>) o).call();
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
