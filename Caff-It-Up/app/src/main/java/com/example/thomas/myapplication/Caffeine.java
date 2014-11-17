package com.example.thomas.myapplication;

/**
 * Created by Thomas on 11/9/2014.
 */
public class Caffeine {
    public static double[] limit = new double{45, 62.5, 85, 240, 400};
    private double multiplier;
    private double holdLimit;

    public Caffeine() {
        multiplier = 1;
        holdLimit = limit[3];
    }

    public Caffeine(int weight, int height, int age, boolean smoke, boolean birthControl, boolean pregnant) {
//        double lowerBound;
//        if(weight <= 300)
//            lowerBound = (weight - 90) / 210.;
//        else
//            lowerBound = 1;
//        multiplier = 75 + lowerBound*100;
        if(pregnant)
            holdLimit = limit[3];
        else if(age < 4)
            holdLimit = 0;
        else if(age < 6)
            holdLimit = limit[0];
        else if(age < 9)
            holdLimit = limit[1];
        else if(age < 12)
            holdLimit = limit[2];
        else
            holdLimit = limit[4];

        //rate of metabolism doesnt actually match rate of caffeine metabolism.. but im gonna try it anyway
        double multiplier;
        if(smoke)
            multiplier = .7;
        else {
            multiplier = 1;
        }
        if(birthControl)
            multiplier *= 2;
        this.multiplier = (2495 / (13.397 * height + 4.799 * weight - 5.677 * age + 66.5) * multiplier);
    }   //ask about caffeine metabolism rates vs actual gen

    private double getLimit() {
        return holdLimit;
    }

    private double timeToDose(double caffeineInBody, double caffeineStart) {
        //return hours?
        return multiplier * Math.log(((caffeineInBody * (11.76 - .162)) / (11.76 * caffeineStart)) / (Math.log(Math.pow(Math.E, - .162) - Math.pow(Math.E, -11.76))));
    }
}
