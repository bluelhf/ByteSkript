package mx.kenzie.skript.compiler.structure;

import mx.kenzie.skript.api.SyntaxElement;
import mx.kenzie.skript.compiler.Context;

import java.util.Collection;

public class TriggerTree extends ProgrammaticSplitTree {
    
    private final SectionMeta owner;
    private final MultiLabel end;
    private final Collection<PreVariable> variables;
    
    public TriggerTree(SectionMeta owner, Collection<PreVariable> variables) {
        this.owner = owner;
        this.end = new MultiLabel();
        this.variables = variables;
    }
    
    public Collection<PreVariable> getVariables() {
        return variables;
    }
    
    @Override
    public SectionMeta owner() {
        return owner;
    }
    
    @Override
    public MultiLabel getEnd() {
        return end;
    }
    
    @Override
    public void start(Context context) {
    
    }
    
    @Override
    public void branch(Context context) {
    
    }
    
    @Override
    public void close(Context context) {
        if (end.uses.size() > 0)
            context.getMethod().writeCode(end.instruction());
    }
    
    @Override
    public boolean permit(SyntaxElement element) {
        return false;
    }
    
    @Override
    public boolean isOpen() {
        return false;
    }
}
