package logic;

import models.CloudResource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CloudManager implements Serializable {
    private Map<String, List<CloudResource>> projects = new HashMap<>();
    private transient List<ResourcePolicy<? extends CloudResource>> policies = new ArrayList<>();

    public CloudManager(Map<String, List<CloudResource>> projects, List<ResourcePolicy<? extends CloudResource>> policies) {
        this.projects = projects;
        this.policies = policies;
    }

    public CloudManager() {

    }

    public void addResources(String projectName, CloudResource resource){
        if (projectName == null){
            System.out.println("Введите название проекта");
            return;
        }

        if (projects.containsKey(projectName)){
            projects.get(projectName).add(resource);

        } else {
            List<CloudResource> cloudResourceList = new ArrayList<>();
            cloudResourceList.add(resource);
            projects.put(projectName, cloudResourceList);
        }
    }

    public void addPolicy(ResourcePolicy<?> policy){
        policies.add(policy);
    }

    public CloudResource getResourceById(String id){

        for (List<CloudResource> cloudResourceList:projects.values()){
            for (CloudResource cloudResource:cloudResourceList){
                if (id.equals(cloudResource.getId())){
                    return cloudResource;
                }
            }
        }return null;
    }

    public void applyAllPolicies(){
        for (List<CloudResource> cloudResourceList:projects.values()){
            for (CloudResource cloudResource:cloudResourceList){
                for (ResourcePolicy resourcePolicy:policies){
                    try {
                        resourcePolicy.apply(cloudResource);
                    }catch (ClassCastException ignored){}
                }
            }
        }
    }

    public Stream<CloudResource> getAnalyticsStream(){
        return projects.values().stream().flatMap(List::stream);
    }

    public void loadToFile(String fileName){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(projects);
        } catch (IOException e) {
            System.err.println("Не удалось загрузить данные в файл");
        }
    }

    public static CloudManager takeFromFile(String fileName){
        File file = new File(fileName);
        if (file.exists()){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){

                Map<String, List<CloudResource>> fileInfo = (Map<String, List<CloudResource>>) ois.readObject();
                CloudManager cloudManager = new CloudManager();
                cloudManager.setProjects(fileInfo);
                System.out.println("Данные успешно загружены из файла");
                return cloudManager;

            }catch (IOException | ClassNotFoundException e){
                System.out.println("Не удалось загрузить данные из файла");
            }
        }else{
            System.out.println("файл не найден");
        }
        return new CloudManager();
    }

    public void shawProjectByName(String name){
        for (CloudResource cloudResource:projects.get(name)){
            System.out.println(cloudResource.getDetails());
        }
    }


    public Map<String, List<CloudResource>> getProjects() {
        return projects;
    }

    public void setProjects(Map<String, List<CloudResource>> projects) {
        this.projects = projects;
    }

    public List<ResourcePolicy<? extends CloudResource>> getPolicies() {
        return policies;
    }

    public void setPolicies(List<ResourcePolicy<? extends CloudResource>> policies) {
        this.policies = policies;
    }
}
