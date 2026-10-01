package models;

public class HydraulicPress extends FactoryEquipment{
    private double pressure;

    public HydraulicPress(String id, String name, double pressure) {
        super(id, name);
        setPressure(pressure);
    }



    @Override
    public String getDetails() {
        return " id: " +  id  + " name: " + name  + " status: " + status + " temperature: " + temperature + " pressure: " + pressure;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        if (pressure < 0){
            throw new IllegalArgumentException();
        }else {
            this.pressure = pressure;
        }
    }
}
