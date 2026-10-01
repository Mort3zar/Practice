package logic;

import models.FactoryEquipment;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class SafetyProtocol<T extends FactoryEquipment> {
    private String protocolName;
    private Predicate<T> condition;
    private Consumer<T> action;

    public SafetyProtocol(String protocolName, Predicate<T> condition, Consumer<T> action) {
        this.protocolName = protocolName;
        this.condition = condition;
        this.action = action;
    }
    public void apply(T equipment){
        if (condition.test(equipment)){
            action.accept(equipment);
        }
    }



    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public Predicate<T> getCondition() {
        return condition;
    }

    public void setCondition(Predicate<T> condition) {
        this.condition = condition;
    }

    public Consumer<T> getAction() {
        return action;
    }

    public void setAction(Consumer<T> action) {
        this.action = action;
    }
}
