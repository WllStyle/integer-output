package main.java.com.uncode;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wang on 2017/9/1.
 * implement Iterable
 */

public class IntegerOutput implements Iterable<Integer> {

    private Integer[] nums;
    private static String fileName;
    private List<Integer> list;
    private Reader mReader;

    //constructor with file path
    public IntegerOutput(String filePath) {
        fileName = filePath;
        stringToReader();

    }

    //constructor whit Reader
    public IntegerOutput(Reader reader) {
        mReader = reader;
        readBuff();
    }

    private void stringToReader() {

        try {
            mReader = new FileReader(fileName);
            readBuff();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //clear the list
    private void setList() {
        if (list != null && list.size() > 0) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }
    }

    //read character from file
    public void readBuff() {
        setList();
        try {
            FileReader fr = (FileReader) mReader;
            BufferedReader reader = new BufferedReader(fr);
            String s = null;
            while ((s = reader.readLine()) != null) {
                if (verify(s)) {
                    list.add(Integer.parseInt(s.trim()));
                }
            }
            if (reader != null) {
                reader.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //change the list to array
        nums = new Integer[list.size()];
        list.toArray(nums);
    }


    //filter the invalid num (e.g ++100,five)
    private boolean verify(String s) {
        if (s != null && isInteger(s.trim())) {
            if (isValidInt(s.trim())) {
                return true;
            }
            return false;
        }
        return false;
    }

    //judge the num
    public boolean isInteger(String input) {
        Matcher mer = Pattern.compile("^[+-]?\\d+$").matcher(input);
        if (mer.matches()) {
            return true;
        }
        return false;
    }

    //judge if larger than the int MAX_VALUE(or less than the MIN_VALUE)
    private boolean isValidInt(String value) {
        try {
            Integer.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public Iterator<Integer> iterator() {


        Iterator<Integer> it = new Iterator<Integer>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                if (nums != null) {
                    return index != nums.length;
                } else {
                    return false;
                }

            }

            @Override
            public Integer next() {
                if (nums != null) {
                    return nums[index++];
                } else {
                    return 0;
                }

            }
        };
        return it;
    }


}
