package models;

public class RoboticArm extends FactoryEquipment{
    private double currentPayLoad;

    public RoboticArm(String id, String name, double currentPayLoad) {
        super(id, name);
        setCurrentPayLoad(currentPayLoad);
    }

    @Override
    public String getDetails() {
        return " id: " +  id  + " name: " + name  + " status: " + status + " temperature: " + temperature + " currentPayLoad: " + currentPayLoad;
    }

    public double getCurrentPayLoad() {
        return currentPayLoad;
    }

    public void setCurrentPayLoad(double currentPayLoad) {
        if (currentPayLoad < 0){
            throw new IllegalArgumentException();
        }else {
            this.currentPayLoad = currentPayLoad;
        }
    }
}
