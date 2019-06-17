package lesson3.bigTask;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReaderProgram {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private File file;
    private int page = 0;
    private final static long MEGABYTE = 1024L * 1024L;
    private final int PAGE_SIZE = 1800;


    public ReaderProgram() {
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
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Страница:" + (page + 1));
                    System.out.println("------------------------------------------------------------------");
                    readPage();
                    System.out.println("\n" + "------------------------------------------------------------------");

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
//                            if (str.equals("help")) {
//                                helpPage();
//                            }
//                            if (str.startsWith("open")) {
//                                String[] token = str.split(" ");
//                                if(openFile(token[1])) {
//                                    break;
//                                }
//                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }




            }
        }).start();
    }


    private boolean openFile(String path) {
        file = new File(path);
        return file.exists() && (file.length()) > 10 * MEGABYTE;
    }

    private void readPage() {
        try (RandomAccessFile ranF = new RandomAccessFile(file, "r")) {
            byte[] buff = new byte[1800];
            int x;
            ranF.seek(PAGE_SIZE * page);

//            while((x = ranF.read(buff)) > 0) {
//                System.out.print(new String(buff, 0 , x, StandardCharsets.UTF_8));
//            }
            ranF.read(buff);
            System.out.print(new String(buff, 0 , PAGE_SIZE, StandardCharsets.UTF_8));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextPage() {
        page++;
    }

    private void previousPage() {
        if (page != 0) {
            page--;
        }
    }
}
