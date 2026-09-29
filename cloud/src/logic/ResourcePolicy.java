package logic;

import models.CloudResource;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ResourcePolicy<T extends CloudResource> {
    private String policyName;
    private Predicate<T> condition;
    private Consumer<T> action;

    public ResourcePolicy(String policyName, Predicate<T> condition, Consumer<T> action) {
        this.policyName = policyName;
        this.condition = condition;
        this.action = action;
    }

    public void apply(T resource){
        if (condition.test(resource)) { action.accept(resource);}
    }


    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
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
