package makesudokus.logic.algorithms.crook;

import makesudokus.structures.OrderedIntegerList;
import makesudokus.structures.UnorderedList;

public class PreemptiveSetExaminer {
    /**
     * Really cool method that checks the given set for preemptive sets and does crossouts based on the findings.
     *
     * We know that a set is a preemptive set by Crook's definition if it has p cells that contain m different
     * digits and m=p. To find these preemptive sets I have applied a version of Dijkstra's algorithm.
     *
     * 1. We take the first cell that isn't complete. It is assumed that complete cells have already been
     * crossed out from the markups. The span of the markup of that cell is m and it contains the markup of only one
     * cell, so p=1.
     * 2. We start a loop, that goes on until m=p or we run out of cells.
     * 3. We then look at the markups of every other cell and calculate their "distance" from our current preemptive set.
     * The distance from the set is the amount of digits the new cell has in their markup that aren't already in the
     * markup of the preemptive set. The distances are stored in a separate distance table.
     * 4. The "closest" cell is then added to the preemptive set. M is incremented by the distance to the newest cell
     * and p is incremented by 1.
     *
     * Since all unknown cells of a set always compose a preemptive set, we are only interested in cases where p is
     * smaller than the amount of unknown cells in the set.
     */
    public static void examine(Cell[] set) {
        //We only want to deal with unknown cells
        UnorderedList originalList = parse(set);



        //We use every cell as the starting cell once
        for(int i = 0; i < originalList.size(); i++) {
            UnorderedList list = originalList.copy();
            //Add the starting cell to the potential preemptive set and remove it from the list of available cells.
            //This is essentially the same as marking it "done".
            PreemptiveSet potentialSet = new PreemptiveSet();
            potentialSet.addCell((Cell)list.get(i));
            list.remove(i);

            //starting values
            int m = potentialSet.getSpan();
            int p = 1;

            //We keep going until we find a preemptive set (preemptive set is when m=p)
            while(m > p && !list.empty()) {
                //Create a distance table
                int[] distance = distanceTable(potentialSet, list);
                //get the index of the first cell with the smallest distance
                int index = nearestIndex(distance);
                //add that cell to the set
                m += distance[index];
                potentialSet.addCell((Cell)list.get(index));
                list.remove(index);
                p++;
            }
            //System.out.println("p:" + p + "/" + originalList.size() + " " + potentialSet);
            if (p < originalList.size()) {
                //System.out.println("We found a preemptive set! Remaining cells " + list.size());
                crossouts(list, potentialSet);
            }

        }
    }

    /**
     * Crosses out the markups found in preemptive sets from the other cells
     * @param cells An unordered list of cells.
     * @param pSet A known preemptive set.
     */
    private static void crossouts(UnorderedList cells, PreemptiveSet pSet) {
        //These are the ones we want to cross out
        OrderedIntegerList markups = pSet.getMarkup();
        //And we cross them out of each cell
        for(int i = 0; i < cells.size(); i++) {
            for(int e = 0; e < markups.size(); e++) {
                ((Cell)cells.get(i)).crossout(markups.get(e));
            }
        }
    }

    /**
     * Counts the distance of each cell and returns them as a table. The distance is determined by how many
     * new digits the inclusion of the cell would introduce to the potential set.
     * @param pSet The current markup to compare to is found in here.
     * @param list An unordered list of cells.
     * @return Returns a table with the distances of each cell to the current markup set.
     */
    private static int[] distanceTable(PreemptiveSet pSet, UnorderedList list) {
        int[] distances = new int[list.size()];
        //for each cell
        for(int i = 0; i < list.size(); i++) {
            distances[i] = distance(pSet, (Cell)list.get(i));
        }

        return distances;
    }

    /**
     * Simple helper method that returns the index of the smallest entry.
     * @param distanceTable the table where we search for the smallest entry.
     * @return Returns the index of the smallest entry.
     */
    private static int nearestIndex(int[] distanceTable) {
        int smallestIndex = 0;
        int smallestNumber = Integer.MAX_VALUE;
        for(int i = 0; i < distanceTable.length; i++) {
            if (distanceTable[i] < smallestNumber) {
                smallestNumber = distanceTable[i];
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    /**
     * Counts the distance of a single Cell.
     * @param potentialSet The current markup to compare to is found in here
     * @param c Cell to be examined.
     * @return Returns the distance of the cell to the potential set.
     */
    private static int distance(PreemptiveSet potentialSet, Cell c) {
        OrderedIntegerList m = potentialSet.getMarkup();
        int delta = 0;
        for (int i = 0; i < c.countMarkups();i++) {
            if (!m.contains(c.getMarkupDigits()[i]))
                delta++;
        }
        return delta;
    }


    /**
     * Removes known cells and returns the rest in list format.
     * @param set A set of cells, originating either from a row, column or 3x3 box.
     * @return Returns an unordered list with the cells that are currently still empty.
     */
    private static UnorderedList parse(Cell[] set) {
        UnorderedList list = new UnorderedList();
        for(int i = 0; i < set.length; i++) {
            if(!set[i].isKnown()) {
                list.add(set[i]);
            }
        }
        return list;
    }
}
