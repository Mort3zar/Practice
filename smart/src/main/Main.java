package main;

import logic.FactoryManager;
import logic.SafetyProtocol;
import models.ConveyorBelt;
import models.FactoryEquipment;
import models.HydraulicPress;
import models.RoboticArm;

import java.util.Scanner;

public class Main {
    private static FactoryManager factoryManager = new FactoryManager();
    private static Scanner scanner = new Scanner(System.in);
    private static String fileName = "factory_state.dat";

    public static void main(String[] args) {
        boolean flag = true;

        while (flag){
            try {
                System.out.println("""
                        1. Добавть оборудывание в указанный цех
                        2. Изменить статус по id
                        3. Добавить базовый протокол
                        4. Добавить индивидуальный протокол
                        5. Выполнить аналитический запрос
                        6. Применить все правила
                        7. Сериалиация данных в файл
                        8. Десереализация данных из файла
                        9. Выход
                        """);
                String input = scanner.nextLine();
                switch (input) {
                    case "1":
                        addEquipment();
                        break;
                    case "2":
                        changeStatus();
                        break;
                    case "3":
                        addBaseProtocol();
                        break;
                    case "4":
                        fourteenProtocol();
                        break;
                    case "5":
                        fourteenStream();
                        break;
                    case "6":
                        applyAllProtocols();
                        break;
                    case "7":
                        factoryManager.loadToFile(fileName);
                        break;
                    case "8":
                        factoryManager = FactoryManager.takeFromFile(fileName);
                        break;
                    case "9":
                        flag = false;
                        break;
                    default:
                        System.out.println("Введите число от 1 до 9");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректное значение");
            }
        }
    }

    public static void addEquipment(){
        System.out.println("Введите тип транспорта: " +
                "1 - ConveyorBelt" +
                "2 - HydraulicPress" +
                "3 - RoboticArm");
        String type = scanner.nextLine();
        System.out.println("Введите id");
        String id = scanner.nextLine();

        System.out.println("Ввдедите имя");
        String name = scanner.nextLine();

        System.out.println("Введите название цеха");
        String workshopName = scanner.nextLine();

        switch (type){
            case "1":
                System.out.println("Введите скорость");
                double speed = Double.parseDouble(scanner.nextLine());
                FactoryEquipment conveyorBelt = new ConveyorBelt(id, name, speed);
                factoryManager.addEquipment(workshopName, conveyorBelt);
                System.out.println("Оборудывание " +  conveyorBelt.getDetails() + " успешно добавлено в цех " + workshopName);
                break;
            case "2":
                System.out.println("Введите уровень давления");
                double pressure = Double.parseDouble(scanner.nextLine());
                FactoryEquipment hydraulicPress = new HydraulicPress(id, name, pressure);
                factoryManager.addEquipment(workshopName, hydraulicPress);
                System.out.println("Оборудывание " +  hydraulicPress.getDetails() + " успешно добавлено в цех " + workshopName);
                break;
            case "3":
                System.out.println("Введите текущую загрузку");
                double currentPayload = Double.parseDouble(scanner.nextLine());
                FactoryEquipment roboticArm = new RoboticArm(id, name, currentPayload);
                factoryManager.addEquipment(workshopName, roboticArm);
                System.out.println("Оборудывание " +  roboticArm.getDetails() + " успешно добавлено в цех " + workshopName);
                break;

            default:
                System.out.println("Введите число от 1 до 3");}
    }

    public static void changeStatus(){
        System.out.println("Введите id оборудывания, у которого хотите изменить статус");
        String id = scanner.nextLine();

        FactoryEquipment equipment = factoryManager.getEquipmentById(id);

        if (equipment == null){
            System.out.println("Устройство с таким id не найдено");
            return;
        }

        if (equipment.isWorking()){
            equipment.stopWork();
        }else {
            equipment.startWork();
        }
        System.out.println("Статус оборудывания был успешно изменен + "  + equipment.getDetails());
    }

    public static void addBaseProtocol(){
        SafetyProtocol<FactoryEquipment> baseProtocol = new SafetyProtocol<>("Сброс фантомного нагрева",
                f-> !f.isWorking() && f.getTemperature() > 20.0,
                f-> f.setTemperature(20.0));
        factoryManager.addProtocol(baseProtocol);
        System.out.println("протокол был успешно применен ко всему оборудыванию");
    }

    public static void fourteenProtocol(){
        SafetyProtocol<RoboticArm> individualProtocol = new SafetyProtocol<>("Очистка захвата",
                r -> !r.isWorking() && r.getCurrentPayLoad() > 0.0,
                r-> r.setCurrentPayLoad(0.0));
        factoryManager.addProtocol(individualProtocol);
        System.out.println("протокол был успешно прменен к классу RoboticArm");
    }

    public static void applyAllProtocols(){
        factoryManager.applyAllProtocols();
        System.out.println("Все правила были успешно применены");
    }

    public static void fourteenStream(){
        System.out.println(factoryManager.getAnalyticsStream().filter(FactoryEquipment::isWorking).mapToDouble(FactoryEquipment::getTemperature).sum());
    }

}

