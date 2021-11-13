package Problem96Permutations;

import Exceptions.NegativeBoundException;
import Exceptions.NegativeNumberException;

import java.util.Arrays;
import java.util.List;

public class Permutations{
    private final int length = 3;
    private ListContainer container;

    public Permutations(){
        container = new ListContainer(length);
    }

    public ListContainer solution(List<Integer> list, int l, int r){
        if(l < 0 || r < 0)
            throw new NegativeNumberException("Error: negative bound value.");
        else if(l > r)
            throw new NegativeBoundException("Error: left border is bigger than right border.");
        if(l == r)
            System.out.println(Arrays.toString(list.toArray()));
        else {
            for(int i = l; i < r; i++){
                swap(list, l, i);
                solution(list, l+1, r);
                swap(list, l, i);
            }
        }
        return new ListContainer(2);
    }

    public List<Integer> swap(List<Integer> list, int i, int j){
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        return list;
    }
}
