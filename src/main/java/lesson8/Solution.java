package lesson8;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new Object());
        }
        System.out.println("Сколько объектов на самом деле: " + list.size());

        System.out.println("Сколько мы насчитали первым способом: " + sizeAbstractList1(list));

        System.out.println("Сколько мы насчитали вторым способом: " + sizeAbstractList2(list));

    }

    private static int sizeAbstractList1(ArrayList<Object> list) {
        OurObject1 ourObj = new OurObject1();
        ArrayList<Object> outList = new ArrayList<>(list);
        outList.add(ourObj);
        int result = 0;
        for(Object b : outList) {
            if (b.equals(ourObj) && ourObj.isOutObj()) {
                break;
            }
            result++;
        }

        return result;
    }

    private static int sizeAbstractList2(ArrayList<Object> list) {
        OurObject2 ourObj = new OurObject2();
        ArrayList<Object> outList = new ArrayList<>(list);
        outList.add(ourObj);
        for(Object b : outList) {
            if (b == ourObj) {
                break;
            }
            ourObj.indUp();
        }

        return ourObj.getIndex();
    }

    //вариант просто с булей
    private static class OurObject1 {

        private boolean isOutObj() {
            return true;
        }
    }

    //вариант с внутренним счётчиком, вы говорили с хэш-кодом, но я немного не сообразил зачем :C
    private static class OurObject2 {

        private int index = 0;

        private void indUp() {
            index++;
        }

        private int getIndex() {
            return index;
        }
    }
}
