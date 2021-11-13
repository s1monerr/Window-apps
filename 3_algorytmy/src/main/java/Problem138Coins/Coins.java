package Problem138Coins;

import Exceptions.NegativeNumberException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Coins {
    private List<Integer> values;

    public Coins(){
     values = new ArrayList<>();
     values.add(25);
        values.add(10);
        values.add(5);
        values.add(1);
    }

    public LinkedList<Integer> solution(int sum){
        if(sum < 0)
            throw new NegativeNumberException("Error: negative sum");
        LinkedList<Integer> toReturn = new LinkedList<>();

        while(sum != 0){
            for(int i : values){
                if(sum >= i){
                    toReturn.add(i);
                    sum -= i;
                    break;
                }
            }
        }
        return toReturn;
    }
}
