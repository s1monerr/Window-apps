import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClassContainer {
    private Map<String, Class> classMap;

    public ClassContainer() {
        this.classMap = new HashMap<>();
    }

    public boolean addClass(String key, Class Cl){
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

    public List<Class> findEmpty(){
        List<Class> toReturnList = new LinkedList<>();
        for(Map.Entry<String, Class> c : classMap.entrySet()){
            if(c.getValue().getStudentList().isEmpty()){
                toReturnList.add(c.getValue());}
        }
        return toReturnList;
    }

    public void summary(){
        for(Map.Entry<String, Class> c : classMap.entrySet()){

            System.out.println("Group name: "+c.getValue().getName()+", capacity: "+c.getValue().capacity()+"%");
        }
    }

}
