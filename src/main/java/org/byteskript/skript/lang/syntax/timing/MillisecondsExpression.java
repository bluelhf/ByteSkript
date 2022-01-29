/*
 * Copyright (c) 2021 ByteSkript org (Moderocky)
 * View the full licence information and permissions:
 * https://github.com/Moderocky/ByteSkript/blob/master/LICENSE
 */

package org.byteskript.skript.lang.syntax.timing;

import mx.kenzie.foundation.MethodBuilder;
import mx.kenzie.foundation.Type;
import org.byteskript.skript.api.note.Documentation;
import org.byteskript.skript.api.note.ForceExtract;
import org.byteskript.skript.api.syntax.SimpleExpression;
import org.byteskript.skript.compiler.*;
import org.byteskript.skript.error.ScriptRuntimeError;
import org.byteskript.skript.lang.element.StandardElements;
import org.byteskript.skript.lang.handler.StandardHandlers;

import java.lang.reflect.Method;
import java.time.Duration;

@Documentation(
    name = "Milliseconds",
    description = """
        A timespan in milliseconds.
        """,
    examples = {
        """
            set {var} to 5 milliseconds
            wait 5 ms
                """
    }
)
public class MillisecondsExpression extends SimpleExpression {
    
    public MillisecondsExpression() {
        super(SkriptLangSpec.LIBRARY, StandardElements.EXPRESSION, "%Number% (ms|millis|milli[ ]second[s])");
        try {
            handlers.put(StandardHandlers.FIND, this.getClass().getMethod("find", Object.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    
    @ForceExtract
    public static Object find(Object object) {
        if (!(object instanceof final Number number)) {
            throw new ScriptRuntimeError("Timespan expression requires number.");
        } else {
            return Duration.ofMillis(number.longValue());
        }
    }
    
    @Override
    public Pattern.Match match(String thing, Context context) {
        return super.match(thing, context);
    }
    
    @Override
    public boolean allowAsInputFor(Type type) {
        return CommonTypes.DURATION.equals(type) || CommonTypes.OBJECT.equals(type);
    }
    
    @Override
    public void compile(Context context, Pattern.Match match) {
        final MethodBuilder method = context.getMethod();
        assert method != null;
        final Method target = handlers.get(StandardHandlers.FIND);
        assert target != null;
        this.writeCall(method, target, context);
        context.setState(CompileState.STATEMENT);
    }
    
}
