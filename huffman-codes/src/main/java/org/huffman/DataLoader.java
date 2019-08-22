package org.huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * DataLoader
 */
public class DataLoader {

    static ArrayList<Integer> load(File f) throws NumberFormatException, IOException {
        BufferedReader fReader = new BufferedReader(new FileReader(f));
        int size = Integer.parseInt(fReader.readLine());
        String line;
        ArrayList<Integer> weights = new ArrayList<Integer>();
        weights.ensureCapacity(size);

        for (int i = 0; i < size; i++) {
            line = fReader.readLine();
            weights.add(Integer.parseInt(line));
        }

        fReader.close();

        return weights;
    }
}