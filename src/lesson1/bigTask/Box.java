package lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> list;
    private float weight = 0;

    public Box() {
        this.list = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(fruit);
        weight += fruit.getWeight();
    }

    public float getWeight() {
        return weight;
    }

    public boolean compare(Box box) {
        if (box != null) {
            return Box.this.getWeight() == box.getWeight();
        }
        return false;
    }

    public void toPour(Box<T> box) {
        if (weight != 0) {
            for (T tt : list) {
                box.addFruit(tt);
            }
            list = null;
            weight = 0;
        }
    }
}
