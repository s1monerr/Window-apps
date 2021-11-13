package Problem282Pythogorean;

import Exceptions.EmptyArrayException;

import java.lang.*;

public class Pythogorean {
    private int[] array;

//    public Pythogorean(int[] array){
//            try{
//                this.array = array;
//            }catch(ArrayIndexOutOfBoundsException e) {
//                System.out.println("Error - an array is too small.");
//            }
//    }

    public boolean solution(int[] array){
        if(array.length == 0)
            throw new EmptyArrayException("Error: array cannot be empty.");
        int[] squares = squareArray(array);
        for(int i : squares){
            System.out.println(i);
        }

        for(int i = 0; i < squares.length; i++){
            for(int j = 1; j < squares.length; j++){
                if(i != j){
                    int tempSum = squares[i] + squares[j];
                    if(contains(squares, tempSum)){
                        try {
                            System.out.println("Found pythagorean set: a = " + array[i] + " b = " + array[j]+" c = "+Math.sqrt(tempSum));
                        }catch (ArrayIndexOutOfBoundsException e){
                            System.out.println("Array out of index.");
                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }

    int[] squareArray(int[] array){
        int length = array.length;
        int[] arr = new int[length];
        for(int i = 0; i < array.length; i++)
            arr[i] = array[i] * array[i];
        return arr;
    }

    boolean contains(int[] array, int value){
        for(int i : array){
            if(i == value)
                return true;
        }
        return false;
    }


}
