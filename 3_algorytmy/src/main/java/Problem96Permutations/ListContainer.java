package Problem96Permutations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListContainer {
    private int size;
    private List<LinkedList<Integer>> container;

    public ListContainer(int n){
        container = new LinkedList<LinkedList<Integer>>();
    }


    public void print(){
        for(LinkedList<Integer> list : container){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    public void addList(LinkedList<Integer> list){
        container.add(list);
    }

}
