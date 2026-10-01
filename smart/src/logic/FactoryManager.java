package logic;

import models.FactoryEquipment;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FactoryManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, List<FactoryEquipment>> workshops = new HashMap<>();
    private transient List<SafetyProtocol<? extends FactoryEquipment>> protocols = new ArrayList<>();

    public FactoryManager(Map<String, List<FactoryEquipment>> workshops, List<SafetyProtocol<? extends FactoryEquipment>> protocols) {
        this.workshops = workshops;
        this.protocols = protocols;
    }

    public FactoryManager() {

    }

    public void addEquipment(String workshopName, FactoryEquipment equipment){
        if (workshopName.isEmpty()){
            System.out.println("Название цеха не может быть пустым");
            return;
        }

        if (getEquipmentById(equipment.getId()) != null){
            System.out.println("оборудывание с таким id уже существует");
            return;
        }

        if (workshops.containsKey(workshopName)){
            workshops.get(workshopName).add(equipment);
        }else {
            List<FactoryEquipment> equipmentList = new ArrayList<>();
            equipmentList.add(equipment);
            workshops.put(workshopName, equipmentList);
        }
    }

    public void addProtocol(SafetyProtocol<?> protocol){
        protocols.add(protocol);
    }

    public FactoryEquipment getEquipmentById(String id){
        for (List<FactoryEquipment> equipmentList: workshops.values()){
            for (FactoryEquipment factoryEquipment:equipmentList){
                if (factoryEquipment.getId().equals(id)){
                    return factoryEquipment;
                }
            }
        }return null;
    }

    public void applyAllProtocols() {
        if (protocols.isEmpty()){
            System.out.println("Пока протоколов не обнаружено, необходимо его добавить");
            return;
        }

        for (List<FactoryEquipment> equipmentList : workshops.values()) {
            for (FactoryEquipment factoryEquipment : equipmentList) {
                for (SafetyProtocol safetyProtocol : protocols) {
                    try {
                        safetyProtocol.apply(factoryEquipment);
                    }catch (ClassCastException ignored){}
                }
            }
        }
    }

    public Stream<FactoryEquipment> getAnalyticsStream(){
        return workshops.values().stream().flatMap(List::stream);
    }

    public void loadToFile(String fileName){
        try(ObjectOutputStream oos = new ObjectOutputStream(new  FileOutputStream(fileName))){
            oos.writeObject(workshops);
            System.out.println("Данные успешно записаны");
        } catch (IOException e) {
            System.err.println("Не удалось записать данные в файл");
        }
    }

    public static FactoryManager takeFromFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Map<String, List<FactoryEquipment>> output = (Map<String, List<FactoryEquipment>>) ois.readObject();
                FactoryManager factoryManager = new FactoryManager();
                factoryManager.setWorkshops(output);
                System.out.println("Данные успешно выгружены из файла");
                return factoryManager;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Не удалось записать данные из файла");
            }
        }else {
            System.out.println("Файл не найден, для начала запишите в него данные");
        }
        return new FactoryManager();
    }




    public Map<String, List<FactoryEquipment>> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(Map<String, List<FactoryEquipment>> workshops) {
        this.workshops = workshops;
    }

    public List<SafetyProtocol<? extends FactoryEquipment>> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<SafetyProtocol<? extends FactoryEquipment>> protocols) {
        this.protocols = protocols;
    }
}
