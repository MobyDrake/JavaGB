package lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.LinkedList;

public class Solution {


    public static void start(Class testClass) {
        Method[] methods = testClass.getDeclaredMethods();
        LinkedList<Method> list = new LinkedList<>();

        for(Method m : methods) {
            if(m.isAnnotationPresent(Test.class)) {
                int priority = m.getAnnotation(Test.class).priority();
                if (priority < 1 || priority > 10) {
                    throw new RuntimeException();
                }
                list.add(m);
            }
        }

        list.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
            }
        });

        for(Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (list.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException();
                }
                list.set(0, m);
            }

            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (list.get(list.size()-1).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException();
                }
                list.add(m);
            }
        }

        for(Method m : list) {
            try {
                m.invoke(testClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
