package example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;

import example.classloader.SteganographyClassLoader;

public class Scene {

    public static void main(String[] args) throws Exception {

        Line line = new Line(7);
        line.put(Gourd.ONE, 6);
        line.put(Gourd.TWO, 3);
        line.put(Gourd.THREE, 1);
        line.put(Gourd.FOUR, 5);
        line.put(Gourd.FIVE, 2);
        line.put(Gourd.SIX, 4);
        line.put(Gourd.SEVEN, 0);

        Geezer theGeezer = Geezer.getTheGeezer();

        SteganographyClassLoader loader;
        Class c;

        // 载入测试用图片
        // loader = new SteganographyClassLoader(new
        // URL("file:example.BubbleSorter.png"));
        // c = loader.loadClass("example.BubbleSorter");

        // 载入编码了快速排序的图片
        // loader = new SteganographyClassLoader(new URL("file:example.QuickSort.png"));
        // c = loader.loadClass("example.QuickSort");

        // 载入编码了选择排序的图片
        loader = new SteganographyClassLoader(new URL("file:example.SelectSort.png"));
        c = loader.loadClass("example.SelectSort");

        // 载入其他同学生成的图片
        // loader = new SteganographyClassLoader(new
        // URL("file:example.ShellSorter.png"));
        // c = loader.loadClass("example.ShellSorter");

        Sorter sorter = (Sorter) c.newInstance();

        theGeezer.setSorter(sorter);

        String log = theGeezer.lineUp(line);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }

}
