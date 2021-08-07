package mx.kenzie.skript.lang.syntax.comparison;

import mx.kenzie.foundation.Type;
import mx.kenzie.skript.api.syntax.RelationalExpression;
import mx.kenzie.skript.compiler.CommonTypes;
import mx.kenzie.skript.compiler.SkriptLangSpec;
import mx.kenzie.skript.lang.element.StandardElements;
import mx.kenzie.skript.lang.handler.StandardHandlers;
import mx.kenzie.skript.runtime.internal.OperatorHandler;

public class GT extends RelationalExpression {
    
    public GT() {
        super(SkriptLangSpec.LIBRARY, StandardElements.EXPRESSION, "%Object% (is greater than|>) ?%Object%");
        try {
            handlers.put(StandardHandlers.FIND, OperatorHandler.class.getMethod("gt", Object.class, Object.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Type getReturnType() {
        return CommonTypes.BOOLEAN;
    }
    
}