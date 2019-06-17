package lesson3.bigTask;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TestR {
    private final static long MEGABYTE = 1024L * 1024L;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private File file;
    private int page = 1;
    private HashMap<Integer, String> map = new HashMap<>();


    public TestR() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Ведите путь к файлу:");
                while(true) {
                    try {
                        String str = reader.readLine();
                        if (openFile(str)) {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                while(true) {
                    readPage();
                    while (true) {
                        try {
                            String str = reader.readLine();
                            if (str.equals("next")) {
                                nextPage();
                                break;
                            }
                            if (str.equals("pre")) {
                                previousPage();
                                break;
                            }
                            if (str.equals("help")) {
                                helpPage();
                            }
                            if (str.startsWith("open")) {
                                String[] token = str.split(" ");
                                if(openFile(token[1])) {
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }

    private void readPage() {
//        char[] page = new char[0];
//        try(BufferedReader in = new BufferedReader(new FileReader(file))) {
//            int ch = 0;
//            for (int i = 0; i < 1800 && (ch = in.read()) != -1; i++) {
//                System.out.print((char) ch);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        for(Map.Entry<Integer, String> tp : map.entrySet()) {
            if (tp.getKey() == page) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("Страница:" + page);
                System.out.println("------------------------------------------------------------------");
                System.out.print(tp.getValue());
                System.out.println("\n" + "------------------------------------------------------------------");
            }
        }

    }

    private void readFile(File file) {
//        try(BufferedReader in = new BufferedReader(new FileReader(file))) {
//            int line;
//            char[] buff = new char[1800];
//            int page = 1;
//            while((line = in.read(buff)) != -1) {
//                map.put(page, buff);
//                page++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try(FileInputStream in = new FileInputStream(file)) {
            byte[] arr = new byte[3600];
            int x;
            int page = 1;
            while((x = in.read(arr)) > 0) {
                map.put(page, new String(arr, 0, x, StandardCharsets.UTF_8));
                page++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean openFile(String str) {
        file = new File(str);
        if (file.exists()) {
            readFile(file);
            return true;
        }
        return false;
    }

    private void nextPage() {
        page++;
    }

    private void previousPage() {
        if (page > 1) page--;
    }

    private void helpPage() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("Рабочие команды:\n" +
                "next - отображает следующию страницу\n" +
                "prev - отображает предыдущую страницу\n" +
                "open 'path file' - открывает новый файл\n");
        System.out.println("------------------------------------------------------------------");
    }

}

