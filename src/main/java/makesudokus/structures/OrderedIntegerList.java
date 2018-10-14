package makesudokus.structures;

public class OrderedIntegerList {
    private final int DEFAULT_SIZE = 10;
    private int[] contents;
    private int size;

    public OrderedIntegerList() {
        this.contents = new int[DEFAULT_SIZE];
        this.size = 0;
    }

    /**
     * Adds an integer to the list and moves all integers after it up a spot.
     * @param newNumber new number to be added.
     */
    public void add(int newNumber) {
        if (this.size == this.contents.length) {
            this.increaseCapacity();
        }
        if (this.size == 0) {
            this.contents[0] = newNumber;
            this.size++;
        } else if (!this.contains(newNumber)) {
            this.contents[this.size] = newNumber;
            for (int i = this.size-1; i >=0; i--) {
                if (this.contents[i] > this.contents[i+1]) {
                    int temp = this.contents[i+1];
                    this.contents[i+1] = this.contents[i];
                    this.contents[i] = temp;
                } else {
                    this.size++;
                    break;
                }
            }
        }
    }


    /**
     * Increases the capacity of the array under the hood
     */
    private void increaseCapacity() {
        int[] newContents = new int[this.contents.length * 2];
        for (int i = 0; i < this.size; i++) {
            newContents[i] = this.contents[i];
        }
        this.contents = newContents;
    }

    /**
     * Finds any contained numbers with a binary search.
     * @param number Number to search for
     * @return Returns true if the list contains searched number
     */
    public boolean contains(int number) {
        int start = 0;
        int end = this.size-1;
        int pointer = 0;
        while (start != end) {
            pointer = (end - start) / 2 + start;
            if (this.contents[pointer] == number) {
                return true;
            }
            if (number > this.contents[pointer]) {
                if(start==pointer) {
                    start = pointer + 1;
                    pointer++;
                }
                else
                    start = pointer;
            } else {
                end = pointer;
            }
        }
        if (this.contents[pointer] == number) {
            return true;
        }
        return false;
    }

    public int get(int index){
        if(index >= this.size) {
            System.out.println("Illegal index get request");
            return -666;
        }
        return this.contents[index];
    }

    public int size() {
        return this.size;
    }
}
