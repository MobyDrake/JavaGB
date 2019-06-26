package lesson5;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {

    private ArrayList<Stage> stages;
    private static boolean win = true;

    public ArrayList<Stage> getStages() { return stages; }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public synchronized static void checkWin(Car car) {
        if (win) {
            System.out.println(car.getName() + " - WIN");
        }
        win = false;
    }


}
