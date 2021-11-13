package Problem165Right;

import java.util.LinkedList;

public class RightSmaller {
    public LinkedList<Integer> solution(LinkedList<Integer> list){
        LinkedList<Integer> toReturn = new LinkedList<>();
        for(int i : list){
            int counter = 0;
            for(int j = list.indexOf(i); j < list.size(); j++){ // start from iterated index
                if(i > list.get(j))
                    ++counter;
            }
            toReturn.add(counter);
        }

        return  toReturn;
    }
}
