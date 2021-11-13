package Problem303Clock;

import Exceptions.NegativeNumberException;
import Exceptions.OutOfRangeException;

public class Clock {

    public int solution(int clockHandHours, int clockHandMinutes){
        if(clockHandHours < 0 || clockHandMinutes < 0)
            throw new NegativeNumberException("Error: clock hands cannot be negative.");
        if(clockHandHours > 12 || clockHandMinutes > 60)
            throw new OutOfRangeException("Error: value out of demanded range.");
        int hourAngle = clockHandHours*30;
        int minutes = clockHandMinutes/12;
        hourAngle += minutes;

        int minuteAngle = (int)6*clockHandMinutes;

        int angle = (int)(Math.abs(hourAngle-minuteAngle));
        return Math.min(angle, 360-angle);
    }
}
