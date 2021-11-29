package Controller;

import Exceptions.OutOfRangeException;

import java.util.*;

public class ClassContainer {
    private Map<String, Course> classMap;

    public ClassContainer() {
        this.classMap = new LinkedHashMap<>();
    }

    public boolean addClass(Course course){
        classMap.put(course.getName(), course);
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

    public List<Course> findEmpty(){
        List<Course> toReturnList = new LinkedList<>();
        for(Map.Entry<String, Course> c : classMap.entrySet()){
            if(c.getValue().getStudentList().isEmpty()){
                toReturnList.add(c.getValue());}
        }
        return toReturnList;
    }

//    public void summary(){
//        for(Map.Entry<String, Model.ClassGroup> c : classMap.entrySet()){
//
//            System.out.println("Group name: "+c.getValue().getName()+", capacity: "+c.getValue().capacity()+"%");
//        }
//    }

    public LinkedList<Student> allStudentList(){
        LinkedList<Student> toReturn = new LinkedList<>();
        for(Map.Entry<String, Course> c : classMap.entrySet()){
            for(Student s  : c.getValue().getStudentList()){
                toReturn.add(s);
            }
        }
        return toReturn;
    }

    public List<Course> allCoursesList(){
        List<Course> toReturn = new LinkedList<>();
        for(Map.Entry<String, Course> c : classMap.entrySet()){
            toReturn.add(c.getValue());
        }
        return toReturn;
    }


    public Map<String, Course> getClassMap() {
        return classMap;
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
            list.get(studentIndex).setIndex(index);
            list.get(studentIndex).setGroup(group);
        }catch (Exception e){
            //
        }
    }

    public Course getClassByIndex(int groupNumber){
        Course group;
        List<String> keys = new ArrayList<>(classMap.keySet());
        try {
            group = classMap.get(keys.get(groupNumber));
        }catch (Exception e){
            group = null;
        }
        return group;
    }

    public Course getClassByName(String name){
        Course group;
        try {
            group = classMap.get(name);
        }catch (Exception e){
            group = null;
        }
        return group;
    }

    private LinkedList<Student> sortBySurname(LinkedList<Student> list){
        List<Student> toReturnList = new LinkedList<>();
        toReturnList = list;
        Course temp = new Course("temp",0);
        Collections.sort(list, temp.getComparatorSurname());
        System.out.println("Sorting by surname...");
        return list;
    }


    public LinkedList<Student> getGroupList(int index){
        LinkedList<Student> toReturn = new LinkedList<>();
        List<String> keys = new ArrayList<>(classMap.keySet());
        toReturn = classMap.get(keys.get(index)).getStudentList();

        return toReturn;
    }

    public void modifyClass(int index, String gName, int sLimit) {
        try {
            Course group;
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
