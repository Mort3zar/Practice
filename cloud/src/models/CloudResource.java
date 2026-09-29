package models;

import interfaces.Manageable;

import java.io.Serializable;

public abstract class CloudResource implements Manageable, Serializable {
    protected String id;
    protected String name;
    protected boolean status;
    protected double loadPercentage = 0.0;

    public CloudResource(String id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public  abstract String getDetails();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getLoadPercentage() {
        return loadPercentage;
    }

    public void setLoadPercentage(double loadPercentage) {
        if (loadPercentage >= 0.0 && loadPercentage <= 100.0){
            this.loadPercentage = loadPercentage;
        }else {
            throw new IllegalArgumentException("Введите от 0 до 100");
        }
    }

    @Override
    public void start() {
        this.status = true;
    }

    @Override
    public void stop() {
        this.status = false;
    }

    @Override
    public boolean isActive() {
        return this.status;
    }
}
