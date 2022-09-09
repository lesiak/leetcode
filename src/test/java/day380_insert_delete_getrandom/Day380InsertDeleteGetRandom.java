package day380_insert_delete_getrandom;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Implement the RandomizedSet class:
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 */
class RandomizedSet {

    List<Integer> valsList = new ArrayList();
    Map<Integer, Integer> valToPos = new HashMap<Integer, Integer>();
    Random ran = new Random();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if(valToPos.containsKey(val)) {
            return false;
        } else {
            valsList.add(val);
            valToPos.put(val, valsList.size() - 1);
            return true;
        }

    }

    public boolean remove(int val) {
        Integer valIndex = valToPos.get(val);
        if (valIndex == null) {
            return false;
        } else if (valIndex == valsList.size() - 1) {
            valToPos.remove(val);
            valsList.remove(valIndex.intValue());
            return true;
        } else {
            valToPos.remove(val);
            int lastElement = valsList.get(valsList.size() - 1);
            valToPos.put(lastElement, valIndex);
            valsList.set(valIndex, lastElement);
            valsList.remove(valsList.size() - 1);
            return true;
        }
    }

    public int getRandom() {
        int nextIndex = ran.nextInt(valsList.size());
        return valsList.get(nextIndex);
    }
}


public class Day380InsertDeleteGetRandom {
    @Test
    void testRandomOps() {
        RandomizedSet randomizedSet = new RandomizedSet();
        assertThat(randomizedSet.insert(1)).isTrue();
        assertThat(randomizedSet.remove(2)).isFalse();
        assertThat(randomizedSet.insert(2)).isTrue();
        assertThat(randomizedSet.getRandom()).isIn(1, 2);
        assertThat(randomizedSet.remove(1)).isTrue();
        assertThat(randomizedSet.insert(2)).isFalse();
        assertThat(randomizedSet.getRandom()).isIn(2);
    }


}
