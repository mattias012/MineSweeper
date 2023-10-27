public class Mine {

    private int row;
    private int col;

    private char symbol;

    public Mine(int row, int col){
        this.row = row;
        this.col = col;
        this.symbol = 'M';
    }

    public char getSymbol(){
        return this.symbol;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
