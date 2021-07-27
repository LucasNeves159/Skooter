
package Controler;

public class SingletonThread {
    // The field must be declared volatile so that double check lock would work
    // correctly.
    private static volatile SingletonThread instance;

    public SaveLoadFunction value;

    private SingletonThread(SaveLoadFunction value) {
        this.value = value;
    }
    
    public SaveLoadFunction getValue(){
        return value;
    }

    public static SingletonThread getInstance(SaveLoadFunction value) {

        SingletonThread result = instance;
        if (result != null) {
            return result;
        }
        synchronized(SingletonThread.class) {
            if (instance == null) {
                instance = new SingletonThread(value);
            }
            return instance;
        }
    }

}
