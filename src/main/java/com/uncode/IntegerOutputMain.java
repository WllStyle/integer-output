package main.java.com.uncode;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by wang on 2017/9/1.
 */
public class IntegerOutputMain {
    public static void main(String[] args) throws Exception{
//        readFile();
        readReader();
    }

    private static void readFile() {
//        //默认定位到项目的根目录，此处是获取文件的相对路径
        String filePath = "src/main/resource/nums.txt";
        for (Integer x : new IntegerOutput(filePath)) {
            System.out.println(x);
        }
    }

    private static void readReader() throws Exception {
        String filePath = "src/main/resource/nums.txt";
        File f = new File(filePath);    // 声明File对象
        Reader input = new FileReader(f);
        for(Integer x:new IntegerOutput(input)){
            System.out.println(x);
        }
    }


}
