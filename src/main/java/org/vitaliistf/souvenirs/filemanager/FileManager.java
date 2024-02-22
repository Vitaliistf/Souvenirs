package org.vitaliistf.souvenirs.filemanager;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for managing file operations (saving and loading sets of objects).
 *
 * @param <T> The type of objects to be saved and loaded.
 */
public class FileManager<T> {

    private final File file;

    /**
     * Constructs a FileManager instance with the specified property.
     *
     * @param filepath The file path.
     */
    public FileManager(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Saves a set of objects to the file.
     *
     * @param set The set of objects to save.
     */
    public void saveToFile(Set<T> set) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(set);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a set of objects from the file.
     *
     * @return A set of objects loaded from the file, or an empty set if the file does not exist or
     * an error occurs during loading.
     */
    @SuppressWarnings("unchecked")
    public Set<T> loadFromFile() {
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (Set<T>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new HashSet<>();
    }

}

