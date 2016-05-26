package com.arpaul.utilitieslib;

import java.util.Collections;
import java.util.List;

/**
 * Created by Aritra on 5/20/2016.
 */
public class SortUtils {

    public static void swapPosition(List<?> swapList, int i, int j){
        Collections.swap(swapList, i, j);
    }

    public static void sortReversely(List<?> swapList){
        Collections.sort(swapList, Collections.reverseOrder());
    }
}
