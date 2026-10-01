package models;

public class ConveyorBelt extends FactoryEquipment{
    private double speed;

    public ConveyorBelt(String id, String name, double speed) {
        super(id, name);
        setSpeed(speed);
    }

    @Override
    public String getDetails() {
        return " id: " +  id  + " name: " + name  + " status: " + status + " temperature: " + temperature + " speed: " + speed;

    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if (speed < 0){
            throw new IllegalArgumentException();
        }
        this.speed = speed;
    }
}
