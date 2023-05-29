package io.github.mizinchik.dsl;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;
import groovy.lang.GroovyShell;
import groovy.lang.MetaProperty;
import groovy.util.DelegatingScript;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import lombok.SneakyThrows;
import org.apache.groovy.groovysh.Main;
import org.codehaus.groovy.control.CompilerConfiguration;

/**
 * Extending this class makes your class be applicable
 * to the DSL for this lab.
 * Handles all the required parsing.
 */
public class Config extends GroovyObjectSupport {
    protected URI scriptPath;
    protected List<String> essentials = List.of(new String[]{"tasks", "allStudents"});

    /**
     * Runs the groovy script.
     *
     * @param uri location of groovy script
     */
    @SneakyThrows
    public void runFrom(URI uri) {
        this.scriptPath = uri;
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName());
        GroovyShell sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc);
        DelegatingScript script = (DelegatingScript) sh.parse(uri);
        script.setDelegate(this);
        script.run();
    }

    /**
     * This method is called when groovy failed to
     * find an appropriate one to execute a closure
     * given for one of the object attributes in a script.
     * Processes closures in an appropriate way,
     * handles lists.
     *
     * @param name of the method
     * @param args provided
     */
    @SneakyThrows
    @SuppressWarnings("unused")
    public void methodMissing(String name, Object args) {
        MetaProperty metaProperty = getMetaClass().getMetaProperty(name);
        if (metaProperty != null) {
            Closure<?> closure = (Closure<?>) ((Object[]) args)[0];
            Class<?> type = metaProperty.getType();
            Constructor<?> constructor = type.getConstructor();
            Object value = getProperty(name) == null
                    ? constructor.newInstance() :
                    getProperty(name);
            closure.setDelegate(value);
            closure.setResolveStrategy(Closure.DELEGATE_FIRST);
            closure.call();
            setProperty(name, value);
        } else {
            throw new IllegalArgumentException("No such field: " + name);
        }
    }

    /**
     * Treats lists respectively.
     * Essentials go first in a natural order of the list.
     */
    public void postProcess() {
        for (String propName : essentials) {
            postProcessSpecific(propName);
        }
        for (MetaProperty metaProperty : getMetaClass().getProperties()) {
            postProcessSpecific(metaProperty.getName(), metaProperty);
        }
    }

    /**
     * Processes a specific metaproperty.
     *
     * @param propName metaproperty name
     */
    public void postProcessSpecific(String propName) {
        MetaProperty metaProperty = getMetaClass().getMetaProperty(propName);
        if (metaProperty == null) {
            return;
        }
        postProcessSpecific(propName, metaProperty);
    }

    /**
     * Processes a specific metaproperty.
     *
     * @param propName metaproperty name
     * @param metaProperty metaproperty
     */
    @SneakyThrows
    public void postProcessSpecific(String propName, MetaProperty metaProperty) {
        Object value = getProperty(propName);
        if (Collection.class.isAssignableFrom(metaProperty.getType())
                && value instanceof Collection) {
            ParameterizedType collectionType = (ParameterizedType) getClass()
                    .getDeclaredField(metaProperty.getName()).getGenericType();
            Class<?> itemClass = (Class<?>) collectionType.getActualTypeArguments()[0];
            if (Config.class.isAssignableFrom(itemClass)) {
                Collection<?> collection = (Collection<?>) value;
                @SuppressWarnings("unchecked") Collection<Object> newValue = collection
                        .getClass().getDeclaredConstructor().newInstance();
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
