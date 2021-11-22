package Problem96Permutations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListContainer {
    private List<LinkedList<Integer>> container;

    public ListContainer(){
        container = new LinkedList<LinkedList<Integer>>();
    }


    public void print(){
        for(LinkedList<Integer> list : container){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    public void addList(LinkedList<Integer> list){
        // System.out.println("Added list: "+Arrays.toString(list.toArray()));
        container.add(list);
    }

    public LinkedList<Integer> get(int index){
        return container.get(index);
    }


}
