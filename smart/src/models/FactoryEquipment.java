package models;

import interfaces.Operatable;

import java.io.Serializable;

public abstract class FactoryEquipment implements Operatable, Serializable {
    private static final long serialVersionUID = 1L;
    protected String id;
    protected String name;
    protected boolean status = false;
    protected double temperature = 0.0;

    public FactoryEquipment(String id, String name) {
        setId(id);
        setName(name);
        setTemperature(temperature);
    }

    public abstract String getDetails();


    @Override
    public void startWork() {
        this.status = true;
    }

    @Override
    public void stopWork() {
        this.status = false;
    }

    @Override
    public boolean isWorking() {
        return this.status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.isEmpty()){
            throw new IllegalArgumentException();
        }else {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        }else {
            this.name = name;
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        if (temperature < 0) {
            throw new IllegalArgumentException();
        }else {
            this.temperature = temperature;
        }
    }
}
