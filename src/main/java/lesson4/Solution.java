package lesson4;

public class Solution {
    private char currentLetter = 'A';

    public static void main(String[] args) {
        Solution s = new Solution();

        new Thread(new Runnable() {
            @Override
            public void run() {
                s.printA();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                s.printB();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                s.printC();
            }
        }).start();

    }

    public synchronized void printA() {
        for (int i = 0; i < 5; i++) {
            while(currentLetter != 'A') {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print('A');
            currentLetter = 'B';
            notifyAll();
        }
    }

    public synchronized void printB() {
        for (int i = 0; i < 5; i++) {
            while(currentLetter != 'B') {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print('B');
            currentLetter = 'C';
            notifyAll();
        }
    }

    public synchronized void printC() {
        for (int i = 0; i < 5; i++) {
            while(currentLetter != 'C') {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print('C');
            currentLetter = 'A';
            notifyAll();
        }
    }
}
