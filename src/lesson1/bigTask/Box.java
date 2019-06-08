package lesson1.bigTask;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> box;

    public Box() {
        this.box = new ArrayList<>();
    }

    public void setBox(ArrayList<T> box) {
        this.box = box;
    }

    public void addFruit(T fruit) {
        box.add(fruit);
    }

    public float getWeight() {
        return box.size() != 0 ? box.size() * box.get(0).getWeight() : 0;
    }

    public boolean compare(Box box) {
        return Box.this.getWeight() == box.getWeight();

    }

    public void toPour(Box<T> otherBox) {
        otherBox.box.addAll(this.box);
        box.clear();
    }
}
