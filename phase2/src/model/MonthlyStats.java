package model;

import java.io.Serializable;
import java.util.HashMap;

/** This class is used to */
public class MonthlyStats implements SystemStats, Serializable {

    private HashMap<Integer, Double> monthlyStats = new HashMap<>();

    MonthlyStats(){
        for (int i = 1; i < 13; i++){
            monthlyStats.put(i, 0.0);
        }
    }

    public HashMap<Integer, Double> getMonthlyStats(){
        return monthlyStats;
    }

    void updateMonthlyStats(int month, double fare){
        monthlyStats.replace(month, monthlyStats.get(month) + fare);
    }

}
