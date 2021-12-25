/*
 * Copyright (c) 2021 ByteSkript org (Moderocky)
 * View the full licence information and permissions:
 * https://github.com/Moderocky/ByteSkript/blob/master/LICENSE
 */

package org.byteskript.skript.runtime.type;

import org.byteskript.skript.error.ScriptRuntimeError;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicVariable extends AtomicReference<Object> {
    
    public static AtomicVariable wrap(final Object object) {
        if (object == null) return new AtomicVariable();
        if (object instanceof AtomicVariable variable) return variable;
        final AtomicVariable variable = new AtomicVariable();
        variable.set(object);
        return variable;
    }
    
    public static Object unwrap(final Object object) {
        if (object == null) return null;
        if (object instanceof AtomicVariable variable) return variable.getAcquire();
        return object;
    }
    
    public static void set(Object value, Object ref) {
        if (ref instanceof AtomicVariable variable) variable.set(value);
        else throw new ScriptRuntimeError(ref + " is not an atomic variable.");
    }
    
    public static Object get(Object ref) {
        if (ref instanceof AtomicVariable variable) return variable.getAcquire();
        else return ref;
    }
    
    public static void delete(Object ref) {
        if (ref instanceof AtomicVariable variable) variable.set(null);
        else throw new ScriptRuntimeError(ref + " is not an atomic variable.");
    }
    
}
