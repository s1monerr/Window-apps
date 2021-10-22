import java.util.HashMap;
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
}
