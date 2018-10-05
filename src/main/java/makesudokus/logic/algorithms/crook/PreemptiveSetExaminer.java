package makesudokus.logic.algorithms.crook;

import makesudokus.structures.OrderedIntegerList;
import makesudokus.structures.UnorderedList;

public class PreemptiveSetExaminer {
    /**
     * Really cool method that checks the given set for preemptive sets and does crossouts based on the findings.
     *
     * We know that a set is a preemptive set by Crook's definition if it has m cells that only contain m different
     * digits. To find these preemptive sets we will do a breadth-first search.
     *
     * 1. We take the first cell that isn't complete. It is assumed that complete cells have already been
     * crossed out from the markups. The span of the markup of that cell is m and it contains the markup of only one
     * cell, so p=1.
     * 2. We take a second cell, which is any other non-finished cell in the set. We now add its markup to the first
     * cell's markup. Note that duplicates are thrown away. If the second cell had digits in its markup that the first
     * didn't, m goes up by the amount of new digits added. p is incremented by one.
     * 3. Repeat step 2 going deeper and deeper in the search tree. If at any point it is discovered that m=p, then we
     * have a preemptive set. If we go deep enough to hit the last cell, then we know p=m, because the amount of digits
     * we are still looking for must be the amount of empty cells.
     * 4. Go back to step 1 and start over with the next cell.
     * 5. Check the found preemptive sets. If there are sets that are subsets of other sets (or straight up duplicates,
     * then the larger one will be thrown away.
     */
    public static void examine(Cell[] set) {
        //We only want to deal with unknown cells
        UnorderedList unknownCells = new UnorderedList();
        for(int i = 0; i < set.length; i++) {
            if(!set[i].isKnown()) {
                unknownCells.add(set[i]);
            }
        }
        //We use every cell as the starting cell once
        for(int i = 0; i < unknownCells.size(); i++) {

            int depth = 0;
            int[] subset = new int[unknownCells.size()];
            subset[depth] = i;

            for(int e = 0; e < unknownCells.size(); e++) {
                if(intArrayContains(subset, e)) {
                    continue;
                } else {

                }
            }
        }
    }

    private static boolean intArrayContains(int[] set, int number) {
        for(int i = 0; i < set.length; i++) {
            if(set.length == number)
                return true;
        }
        return false;
    }


}
