package models;

public class CloudDatabase extends CloudResource{
    private double storageUsed;

    public CloudDatabase(String id, String name, boolean status, double storageUsed) {
        super(id, name, status);
        this.storageUsed = storageUsed;
    }

    public double getStorageUsed() {
        return storageUsed;
    }

    public void setStorageUsed(double storageUsed) {
        this.storageUsed = storageUsed;
    }

    @Override
    public String getDetails() {
        return this.id + " " + this.name + " " +  this.status + " " +  this.loadPercentage + " " +  this.storageUsed;

    }
}
