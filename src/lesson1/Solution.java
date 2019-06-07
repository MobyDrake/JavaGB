package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution<T> {
    private T[] mass;

    public Solution(T[] mass) {
        this.mass = mass;
    }


    //метод для первого пункта задания
    public  void changePosition(int positionFirst, int positionTwo) {

        T sol = mass[positionFirst];
        mass[positionFirst] = mass[positionTwo];
        mass[positionTwo] = sol;
    }

    //метода для второго пункта задания
    public ArrayList<T> changeArr() {
        return new ArrayList<>(Arrays.asList(mass));
    }


    public static void main(String[] args) {
//        Solution<String> sol = new Solution<>(new String[] {"1", "2", "3", "4", "5"});
//        sol.changePosition(1, 3);
//        ArrayList<String> arr = sol.changeArr();
//
//        for(String s : sol.mass) {
//            System.out.println(s);
//        }

        Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());

        Box<Apple> appleBox1 = new Box<>();
        appleBox.toPour(appleBox1);

        System.out.println(appleBox.getWeight());
        System.out.println(appleBox1.getWeight());
        System.out.println(orangeBox.getWeight());
        System.out.println(appleBox1.compare(orangeBox));


    }

}

