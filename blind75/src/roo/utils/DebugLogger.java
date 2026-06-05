package roo.utils;

/**
 * Class to debug issue instead of
 * repeated sysouts
 */
public class DebugLogger {
    private final boolean enabled;

    public DebugLogger(boolean enabled){
        this.enabled = enabled;
    }
    // Note if the Object is a custom class override toString
    public void log(String scope, String label, Object value){
        if(enabled){
            System.out.println("["+scope+"] : "+label+ " : "+value );
        }
    }
}
