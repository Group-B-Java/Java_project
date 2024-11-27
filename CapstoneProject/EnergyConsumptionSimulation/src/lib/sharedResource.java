package lib;

public class sharedResource {
    private int sharedValue;
    //private boolean updated = false;

    public sharedResource(int initialValue) {
        this.sharedValue = initialValue;
    }

    public synchronized int getSharedValue() {
        return sharedValue;
    }

    public synchronized void setSharedValue(int newValue) {
        this.sharedValue = newValue;
    }
}
