import Exceptions.OutOfRangeException;

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

//    public void summary(){
//        for(Map.Entry<String, ClassGroup> c : classMap.entrySet()){
//
//            System.out.println("Group name: "+c.getValue().getName()+", capacity: "+c.getValue().capacity()+"%");
//        }
//    }

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

    public int getClassNumber(){
        return classMap.size();
    }

    public void addStudent(Student student, int groupNumber){
        List<String> keys = new ArrayList<>(classMap.keySet());
        if(groupNumber > classMap.size())
            throw new OutOfRangeException("Error - out of class range!");
        try {
            student.setGroup(groupNumber);
            String groupKey = keys.get(--groupNumber);
            classMap.get(groupKey).addStudent(student);
        }catch (Exception e){
            //
        }
    }

    public void changeStudentData(int studentIndex, String name, String surname, int yearOfBirth, double score, int index, int group){
        if(group > classMap.size())
            throw new OutOfRangeException("Out of range!");
        try {
            List<Student> list = allStudentList();
            list.get(studentIndex).setName(name);
            list.get(studentIndex).setSurname(surname);
            list.get(studentIndex).setYearOfBirth(yearOfBirth);
            list.get(studentIndex).setScore(score);
            list.get(studentIndex).setIndex(index);
            list.get(studentIndex).setGroup(group);
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

    private LinkedList<Student> sortBySurname(LinkedList<Student> list){
        List<Student> toReturnList = new LinkedList<>();
        toReturnList = list;
        ClassGroup temp = new ClassGroup("temp",0);
        Collections.sort(list, temp.getComparatorSurname());
        System.out.println("Sorting by surname...");
        return list;
    }

    private LinkedList<Student> sortByScore(LinkedList<Student> list){
        List<Student> toReturnList = new LinkedList<>();
        toReturnList = list;
        ClassGroup temp = new ClassGroup("temp",0);
        Collections.sort(list, temp.getComparatorScore());
        System.out.println("Sorting by score...");
        return list;
    }

    public LinkedList<Student> getSortedStudentList(int i){
        LinkedList<Student> toReturn = new LinkedList<>();
        for(Map.Entry<String, ClassGroup> c : classMap.entrySet()){
            for(Student s  : c.getValue().getStudentList()){
                toReturn.add(s);
            }
        }
        if(i == 0)
            toReturn = sortBySurname(toReturn);
        else if(i == 1)
            toReturn = sortByScore(toReturn);
        else
            System.err.println("Error - wrong sorting type");
        return toReturn;
    }

    public LinkedList<Student> getGroupList(int index){
        LinkedList<Student> toReturn = new LinkedList<>();
        List<String> keys = new ArrayList<>(classMap.keySet());
        toReturn = classMap.get(keys.get(index)).getStudentList();

        return toReturn;
    }

    public void modifyClass(int index, String gName, int sLimit) {
        try {
            ClassGroup group;
            List<String> keys = new ArrayList<>(classMap.keySet());
            try {
                group = classMap.get(keys.get(index));
            } catch (Exception e) {
                group = null;
            }
            group.setName(gName);
            group.studentsLimit = sLimit;
        }catch (Exception e){
            //
        }
    }
}
