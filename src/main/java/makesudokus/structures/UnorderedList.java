package makesudokus.structures;

public class UnorderedList {
    private final int DEFAULT_SIZE = 10;
    private Object[] contents;
    private int size;

    /**
     * Constructor for the UnorderedList type.
     */
    public UnorderedList() {
        this.contents = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    /**
     * Add a new object to the list.
     * @param o The object to be added.
     */
    public void add(Object o) {
        if (this.contents[this.contents.length-1] != null) {
            increaseCapacity();
        }
        for (int i = 0; i < this.contents.length; i++) {
            if (this.contents[i] == null) {
                this.contents[i] = o;
                this.size++;
                break;
            }
        }
    }

    /**
     * Add an entry at a specific index
     * @param index
     */
    public boolean set(int index, Object o) {
        if (index >= this.size) {
            System.out.println("Index out of list range");
            return false;
        }
        this.contents[index] = o;
        return true;
    }

    /**
     * Remove an element from the list and move all entries behind the removed index up one spot
     * @param index Index of the entry to be removed.
     */
    public void remove(int index) {
        if(!this.set(index, null)) {
            return;
        }
        for (int i = index; this.contents[i+1] != null;i++) {
            this.contents[i] = this.contents[i+1];
        }
        this.contents[this.size-1] = null;
        this.size--;
    }

    /**
     * @return Returns the amount of entries in the list
     */
    public int size() {
        return this.size;
    }

    /**
     * If the list is full, we need to increase the size
     */
    private void increaseCapacity() {
        //Double the previous size
        Object[] newContents = new Object[2*this.contents.length];
        for (int i = 0; i < this.contents.length; i++) {
            newContents[i] = this.contents[i];
        }
        this.contents = newContents;
    }

    /**
     * Create and return a semi-deep copy of the current list. Semi-deep means, that the list will be a new one,
     * but it will still contain the same object references as the old one.
     * @return Returns a copy of the list.
     */
    public UnorderedList copy() {
        UnorderedList list = new UnorderedList();
        for (int i = 0; i < size; i++) {
            list.add(this.contents[i]);
        }
        return list;
    }

    /**
     * Fetches an item from the list.
     * @param index Index of the item searched for.
     * @return Returns the item at the given index.
     */
    public Object get(int index) {
        return this.contents[index];
    }

    /**
     * Getter method for emptiness of list.
     * @return Returns true if the list is empty.
     */
    public boolean empty() {
        return this.size == 0;
    }
}
