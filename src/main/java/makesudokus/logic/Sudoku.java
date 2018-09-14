package makesudokus.logic;

public class Sudoku {
    private int[][] content;

    public Sudoku() {
        //Eventually might be replaced by code to fetch random content from a list,
        //generate one or start an empty one.
        content = new int[9][9];
    }

    public int[][] getContent() {
        return content;
    }

    public void setContent(int[][] newcontent) {
        content = newcontent;
    }

    public boolean setNumber(int x, int y, int newNumber) {
        if (newNumber >= 1 && newNumber <= 9 &&
        x >= 0 && x <= 8 &&
        y >= 0 && y <= 8) {
            content[y][x] = newNumber;
            return true;
        }
        else {
            return false;
        }
    }

    public int getNumber(int x, int y) {
        return content[y][x];
    }

    @Override
    public String toString(){
        String s = "";
        for (int y = 0; y < this.content.length; y++) {
            for (int x = 0; x < this.content[y].length; x++) {
                s += Integer.toString(content[y][x]);
            }
            s+= "\n";
        }
        return s;
    }
}
