import java.util.HashMap;

public class Board {
    private final int rows;
    private final int columns;
    private final Cell[][] board;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.board = new Cell[rows][columns];

        initBoard();
    }

    private void initBoard() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                board[row][column] = new Cell();
            }
        }
    }

    private int getAliveNeighbours(int cellRow, int cellColumn) {
        int aliveNeighbours = 0;

        for (int row = cellRow - 1; row <= cellRow + 1; row++) {
            // Loop through the row above, the current row, and the row below
            for (int column = cellColumn - 1; column <= cellColumn + 1; column++) {
                // Loop through the column to the left, the current column, and the column to the right
                if (row == cellRow && column == cellColumn) continue; // Skip counting the current cell
                if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) continue; // Make sure the neighbour is in the grid

                if (board[row][column].isAlive())
                    aliveNeighbours++;
            }
        }

        return aliveNeighbours;
    }

    public void advanceGeneration() {
        HashMap<Cell, Boolean> cellsToUpdate = new HashMap<Cell, Boolean>();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell currentCell = board[row][column];
                int aliveNeighbours = getAliveNeighbours(row, column);

                if (currentCell.isAlive()) {
                    if (aliveNeighbours < 2) // Rule 1 - underpopulation
                        cellsToUpdate.put(currentCell, false);
                    else if (aliveNeighbours > 3) // Rule 3 - overpopulation
                        cellsToUpdate.put(currentCell, false);
                } else if (aliveNeighbours == 3) // Rule 4 - reproduction
                    cellsToUpdate.put(currentCell, true);
            }
        }

        for (Cell cell : cellsToUpdate.keySet())
            cell.setAlive(cellsToUpdate.get(cell));
    }

    public void setCellAlive(int row, int column) {
        board[row][column].setAlive(true);
    }

    public void draw() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++)
                System.out.print(board[row][column].isAlive() ? "X " : "# ");

            System.out.println();
        }

        System.out.println();
    }
}