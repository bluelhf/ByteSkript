package mx.kenzie.skript.api;

public interface Instruction<Type> {
    
    void run() throws Throwable;
    
    default Type get() {
        return null;
    }
    
    default void runSafely() {
        try {
            this.run();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    
}
