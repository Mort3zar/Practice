package models;

public class VirtualMachine extends CloudResource{
    private int ramSize;

    public VirtualMachine(String id, String name, boolean status, int ramSize) {
        super(id, name, status);
        this.ramSize = ramSize;
    }

    @Override
    public String getDetails() {
        return this.id + " " + this.name + " " +  this.status + " " +  this.loadPercentage + " " +  this.ramSize;
    }

    public int getRamSize() {
        return ramSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }
}
