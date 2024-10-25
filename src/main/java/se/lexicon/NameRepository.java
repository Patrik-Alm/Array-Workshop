package se.lexicon;

import java.util.Arrays;

/**
 * The NameRepository class provides methods to manage a list of names.
 * It offers functionalities such as adding, removing, finding, and updating names.
 */
public class NameRepository {

    private static String[] names = new String[0];

    /**
     * Retrieves the current size of the names array.
     *
     * @return The number of elements in the names array.
     */
    public static int getSize() {
        return names.length;
    }

    /**
     * Sets the names array to the provided array of names.
     *
     * @param names The array of names to set.
     */
    public static void setNames(String[] names) {
        NameRepository.names = Arrays.copyOf(names, names.length);
    }

    /**
     * Clears the names array by creating a new empty array.
     */
    public static void clear() {
        names = new String[0];
    }

    /**
     * Retrieves a copy of the names array.
     *
     * @return A new array containing all elements from the names array.
     */
    public static String[] findAll() {
        return Arrays.copyOf(names, names.length);
    }

    /**
     * Finds a name that matches the given fullName case-insensitively.
     *
     * @param fullName The full name to search for.
     * @return The matching name if found; otherwise, null.
     */
    public static String find(String fullName) {
        for (String name : names) {
            if (name.equalsIgnoreCase(fullName)) {
                return name;
            }
        }
        return null;
    }

    /**
     * Adds a new fullName to the names array if it doesn't already exist.
     *
     * @param fullName The full name to add.
     * @return True if the fullName is added successfully; false if it already exists.
     */
    public static boolean add(String fullName) {
        if (find(fullName) != null) {
            return false;
        }

        names = Arrays.copyOf(names, names.length + 1);
        names[names.length - 1] = fullName;
        return true;
    }

    /**
     * Find all names that match the given firstName.
     *
     * @param firstName The first name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByFirstName(String firstName) {
        String[] result = new String[0];
        for (String element : names) {
            String[] fullNameArray = element.split(" ");
            String fn = fullNameArray[0];

            if (fn.equalsIgnoreCase(firstName)) {
                result = addToArray(result, element);
            }
        }
        return result;
    }

    /**
     * Find all names that match the given lastName.
     *
     * @param lastName The last name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByLastName(String lastName) {
        String[] result = new String[0];
        for (String element : names) {
            String[] fullNameArray = element.split(" ");
            String ln = fullNameArray[1];

            if (ln.equalsIgnoreCase(lastName)) {
                result = addToArray(result, element);
            }
        }
        return result;
    }

    /**
     * Add a new name to the source array and return a new array.
     *
     * @param source  The source array.
     * @param newName The new name to add to the array.
     * @return A new array with the new name added at the end.
     */
    private static String[] addToArray(String[] source, String newName) {
        String[] tmp = Arrays.copyOf(source, source.length + 1);
        tmp[tmp.length - 1] = newName;
        return tmp;
    }

    /**
     * Updates a name in the names array from the original name to the updated name.
     *
     * @param original    The original name to update.
     * @param updatedName The updated name to set.
     * @return True if the name is updated successfully; false if the updated name already exists or the original name is not found.
     */
    public static boolean update(String original, String updatedName) {
        int originalIndex = indexOf(names, original);
        if (originalIndex == -1 || find(updatedName) != null) {
            return false;
        }

        names[originalIndex] = updatedName;
        return true;
    }

    /**
     * Removes a name from the names array, case-insensitively.
     *
     * @param fullName The full name to remove.
     * @return True if the name is removed successfully; false if the name is not found in the array.
     */
    public static boolean remove(String fullName) {
        fullName = fullName.toLowerCase();
        int findIndex = -1;
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().equals(fullName)) {
                findIndex = i;
                break;
            }
        }

        if (findIndex == -1) {
            return false;
        }

        String[] anotherArray = new String[names.length - 1];
        int sequencer = 0;
        for (int i = 0; i < names.length; i++) {
            if (i == findIndex) {
                continue;
            }
            anotherArray[sequencer++] = names[i];
        }
        names = anotherArray;
        return true;
    }

    /**
     * Removes a name from the names array, case-insensitively.
     *
     * @param fullName The full name to remove.
     * @return True if the name is removed successfully; false if the name is not found in the array.
     */
    public static boolean removeNew(String fullName) {
        int index = indexOf(names, fullName);
        if (index == -1) {
            return false;
        }

        String[] newArray = new String[names.length - 1];

        System.arraycopy(names, 0, newArray, 0, index);
        System.arraycopy(names, index + 1, newArray, index, names.length - index - 1);
        names = newArray;
        return true;
    }

    /**
     * Finds all names that match the given attribute (first or last name).
     *
     * @param attribute The attribute to search for (first or last name).
     * @param index     The index in the full name where the attribute is located (0 for first name, 1 for last name).
     * @return An array containing all matching names.
     */
    private static String[] findByAttribute(String attribute, int index) {
        String[] result = new String[0];
        for (String name : names) {
            String[] fullNameArray = name.split(" ");
            if (fullNameArray.length > index && fullNameArray[index].equalsIgnoreCase(attribute)) {
                result = addToArray(result, name);
            }
        }
        return result;
    }

    /**
     * Gets the index of a string in the given array, ignoring case sensitivity.
     *
     * @param array  The array to search in.
     * @param element The string to search for.
     * @return The index of the string if found; otherwise, -1.
     */
    private static int indexOf(String[] array, String element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(element)) {
                return i;
            }
        }
        return -1;
    }


}