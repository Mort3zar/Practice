package models;

public class LoadBalancer extends CloudResource{
    private int activeConnection;

    public LoadBalancer(String id, String name, boolean status, int activeConnection) {
        super(id, name, status);
        this.activeConnection = activeConnection;
    }

    @Override
    public String getDetails() {
        return this.id + " " + this.name + " " +  this.status + " " +  this.loadPercentage + " " +  this.activeConnection;

    }

    public int getActiveConnection() {
        return activeConnection;
    }

    public void setActiveConnection(int activeConnection) {
        this.activeConnection = activeConnection;
    }
}
