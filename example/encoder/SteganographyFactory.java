package example.encoder;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.awt.image.BufferedImage;

public class SteganographyFactory {

    private static void compile(String classSource) {

        File sourceFile = new File(classSource);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());
    }

    public static void getSteganography(String classSource, String originImage) throws IOException {
        String className = classSource.substring(0, classSource.indexOf(".")).replace("/", ".");
        SteganographyFactory.compile(classSource);
        BufferedImage image = ImageIO.read(new File(originImage));
        SteganographyEncoder steganographyEncoder = new SteganographyEncoder(image);

        BufferedImage encodedImage = steganographyEncoder.encodeFile(new File(classSource.replace("java", "class")));
        ImageIO.write(encodedImage, "png", new File(className + ".png"));

    }

    public static void main(String[] args) throws IOException {

        SteganographyFactory.getSteganography("example/BubbleSorter.java", "example/resources/bubble.jpeg");

        // 将选择排序编码进sky.jpg图片中
        SteganographyFactory.getSteganography("example/SelectSort.java", "example/resources/sky.jpg");

        // 将快速排序编码进leaf.jpg图片中
        SteganographyFactory.getSteganography("example/QuickSort.java", "example/resources/leaf.jpg");

    }

}
