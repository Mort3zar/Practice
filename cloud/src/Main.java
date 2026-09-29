import logic.CloudManager;
import logic.ResourcePolicy;
import models.CloudDatabase;
import models.CloudResource;
import models.LoadBalancer;
import models.VirtualMachine;

import java.util.Scanner;

public class Main {
    private static CloudManager cloudManager = new CloudManager();
    private static Scanner scanner = new Scanner(System.in);
    private static final String fileName = "cloud_state.dat";

    public static void main(String[] args) {
        boolean flag = true;
        cloudManager = CloudManager.takeFromFile(fileName);
        while (flag){
            System.out.println("""
                    1. Добавить ресурс  в указанный проект
                    2. Изменить статус по id
                    3. Применить базовое правило
                    4. Применить индивидуальное правило
                    5. Применить все правила
                    6. Выполнить аналитический запрос
                    7. Показать проект по названию
                    8. Выход
                    """);

            String input = scanner.nextLine();
            switch (input) {
                    case "1":
                        addResource();
                        break;
                    case "2":
                        changeStatus();
                        break;
                    case "3":
                        basePolicy();
                        break;
                    case "4":
                        individualPolicy();
                        break;
                    case "5":
                        executeAllPolicies();
                        break;
                    case "6":
                        analytics();
                        break;
                    case "7":
                        showByName();
                        break;
                    case "8":
                        cloudManager.loadToFile(fileName);
                        flag = false;
                        break;
            }
        }
    }

    public static void addResource(){
        System.out.println("Введите id проекта");
        String id = scanner.nextLine();

        if (cloudManager.getResourceById(id) != null){
            System.out.println("такой id уже существует");
            return;
        }

        System.out.println("Введите имя проекта");
        String name = scanner.nextLine();

        System.out.println("Введите название проекта");
        String projectName = scanner.nextLine();

        System.out.println("Введите тип ресурса: 1-CloudDatabase, 2-LoadBalancer, 3-VirtualMachine");
        String type = scanner.nextLine();
        switch (type){
            case "1":
                System.out.println("Введите сколько места на диске занято");
                double storageUsed = Double.parseDouble(scanner.nextLine());
                CloudResource cloudDatabase = new CloudDatabase(id, name, false, storageUsed);
                cloudManager.addResources(projectName, cloudDatabase);
                System.out.println("Ресурс был успешно добавлен");
                break;
            case "2":
                System.out.println("Введите сколько активных сетевых соединений");
                int activeConnections = Integer.parseInt(scanner.nextLine());
                CloudResource loadBalancer = new LoadBalancer(id, name, false, activeConnections);
                cloudManager.addResources(projectName, loadBalancer);
                System.out.println("Ресурс был успешно добавлен");
                break;
            case "3":
                System.out.println("Введите объем оперативной памяти");
                int ramSize = Integer.parseInt(scanner.nextLine());
                CloudResource virtualMachine = new VirtualMachine(id, name, false, ramSize);
                cloudManager.addResources(projectName, virtualMachine);
                System.out.println("Ресурс был успешно добавлен");
                break;
            default:
                System.out.println("Введите число от 1 до 3");
                break;
        }
    }

    public static void changeStatus(){
        System.out.println("Введите id устройства, статус которого вы хотите поменять");
        String id = scanner.nextLine();

        if (id == null){
            System.out.println("Введите id");
            return;
        }

        if (cloudManager.getResourceById(id) == null){
            System.out.println("Ресурса с таким id не существует");
            return;
        }

        if (cloudManager.getResourceById(id).isActive()){
            cloudManager.getResourceById(id).stop();
        }else {
            cloudManager.getResourceById(id).start();
        }
    }

    public static void basePolicy(){
        ResourcePolicy<CloudResource> base = new ResourcePolicy<>("Сброс фантомной нагрузки",
                c-> !c.isActive() && c.getLoadPercentage() > 0.0,
                c-> c.setLoadPercentage(0.0));
        cloudManager.addPolicy(base);
    }

    public static void individualPolicy(){
        ResourcePolicy<LoadBalancer> individual = new ResourcePolicy<>("Масштабирование трафика",
                c-> c.isActive() && c.getActiveConnection() > 10000,
                c-> c.setLoadPercentage(100.0));
        cloudManager.addPolicy(individual);
    }

    public static void executeAllPolicies(){
        cloudManager.applyAllPolicies();
    }

    public static void analytics(){
        System.out.println(cloudManager.getProjects().keySet().stream().filter(name-> cloudManager.getProjects().get(name).stream().anyMatch(c-> !c.isActive())).toList());
    }

    public static void showByName(){
        System.out.println("Введите имя проекта, который хотите увидеть");
        String name = scanner.nextLine();

        if (name == null) {
            System.out.println("Проект с таким именем не найден.");
            return;
        }

        cloudManager.shawProjectByName(name);
    }


}
