package com.hiepscar.baitaplon;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by hoanghiep on 11/9/16.
 */
public class ItemManager {
    private ArrayList<Item> itemList;
    private File file;

    public ItemManager() {
        itemList = new ArrayList<>();
        file = new File("D:/item.txt");
        initItemToList();
    }

    private void initItemToList() {
        try {
            RandomAccessFile reader = new RandomAccessFile(file, "rw");
            String line = reader.readLine();
            while (line != null) {
                String result[] = line.split("_");
                Item item = new Item(result[0], Integer.parseInt(result[1]), Integer.parseInt(result[2]));
                itemList.add(item);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Item> getItemList() {
        return itemList;
    }
}
