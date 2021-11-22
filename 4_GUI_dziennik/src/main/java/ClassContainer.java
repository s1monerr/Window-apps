import java.util.*;

public class ClassContainer {
    private Map<String, ClassGroup> classMap;

    public ClassContainer() {
        this.classMap = new LinkedHashMap<>();
    }

    public boolean addClass(String key, ClassGroup Cl){
        classMap.put(key, Cl);
        return true;
    }

    public boolean removeClass(String key){
        if(classMap.containsKey(key)) {
            classMap.remove(key);
            System.out.println("Successfully removed class with key "+key);
            return true;
        }

        System.err.println("Error: no class with such key in container.");
        return false;
    }

    public List<ClassGroup> findEmpty(){
        List<ClassGroup> toReturnList = new LinkedList<>();
        for(Map.Entry<String, ClassGroup> c : classMap.entrySet()){
            if(c.getValue().getStudentList().isEmpty()){
                toReturnList.add(c.getValue());}
        }
        return toReturnList;
    }

    public void summary(){
        for(Map.Entry<String, ClassGroup> c : classMap.entrySet()){

            System.out.println("Group name: "+c.getValue().getName()+", capacity: "+c.getValue().capacity()+"%");
        }
    }

    public LinkedList<Student> allStudentList(){
        LinkedList<Student> toReturn = new LinkedList<>();
        for(Map.Entry<String, ClassGroup> c : classMap.entrySet()){
            for(Student s  : c.getValue().getStudentList()){
                toReturn.add(s);
            }
        }
        return toReturn;
    }

    public List<ClassGroup> allGroupsList(){
        List<ClassGroup> toReturn = new LinkedList<>();
        for(Map.Entry<String, ClassGroup> c : classMap.entrySet()){
            toReturn.add(c.getValue());
        }
        return toReturn;
    }


    public Map<String, ClassGroup> getClassMap() {
        return classMap;
    }

    public void deleteStudent(Student toDelete) {
            for(Map.Entry<String, ClassGroup> c : classMap.entrySet()){
                for(Student s  : c.getValue().getStudentList()){
                    if(s == toDelete)
                        c.getValue().deleteStudent(toDelete);
                }
            }
    }

    public void addStudent(Student student, int groupNumber){
        List<String> keys = new ArrayList<>(classMap.keySet());
        try {
            String groupKey = keys.get(--groupNumber);
            classMap.get(groupKey).addStudent(student);
        }catch (Exception e){
            //
        }
    }

    public ClassGroup getClassByIndex(int groupNumber){
        ClassGroup group;
        List<String> keys = new ArrayList<>(classMap.keySet());
        try {
            group = classMap.get(keys.get(groupNumber));
        }catch (Exception e){
            group = null;
        }
        return group;
    }


}
