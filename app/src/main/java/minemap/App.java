/*
 * This source file was generated by the Gradle 'init' task
 */
package minemap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import dev.dewy.nbt.Nbt;
import dev.dewy.nbt.io.CompressionType;
import dev.dewy.nbt.tags.array.ByteArrayTag;
import dev.dewy.nbt.tags.array.IntArrayTag;
import dev.dewy.nbt.tags.array.LongArrayTag;
import dev.dewy.nbt.tags.collection.CompoundTag;
import dev.dewy.nbt.tags.collection.ListTag;
import dev.dewy.nbt.tags.primitive.*;

public class App {
    private static final Nbt NBT = new Nbt();

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        double zmax = -1000000.0;
        double zmin = 10000000.0;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("SWISSALTI3D_0.5_XYZ_CHLV95_LN02_2586_1230.xyz"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String arr[] = line.split(" ");
                try {
                    double x = Double.parseDouble(arr[0]);
                    double y = Double.parseDouble(arr[1]);
                    double z = Double.parseDouble(arr[2]);
                    if (z > zmax) {
                        zmax = z;
                    }
                    if (z < zmin) {
                        zmin = z;
                    }
                } catch (Exception e) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done");
        System.out.println("Min: " + zmin + " Max: " + zmax);

        // https://minecraft.wiki/w/Schematic_file_format
        CompoundTag sch = new CompoundTag("Schematic");
        sch.put(new ShortTag("Width", 10)); // X
        sch.put(new ShortTag("Height", 10)); // Y
        sch.put(new ShortTag("Length", 10)); // Z
        sch.put("Materials", new StringTag("Classic"));
        sch.put(new ByteArrayTag("Blocks", new byte[] { 0, -124, 13, -6, Byte.MAX_VALUE }));
        try {
            NBT.toFile(sch, new File("test.nbt"));
        } catch (IOException e) {

        }
    }
}
