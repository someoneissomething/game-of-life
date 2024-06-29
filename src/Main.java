public class Main {
    public static void main(String[] args) {
        // Conway's Game of Life:
        // 1. Any live cell with fewer than two live neighbours dies - underpopulation
        // 2. Any live cell with two or three live neighbours lives on to the next generation
        // 3. Any live cell with more than three live neighbours dies - overpopulation
        // 4. Any dead cell with exactly three live neighbours becomes a live cell - reproduction

        // 2D array - the grid
        // Alive cell - X
        // Dead cell - #

        Board board = new Board(10, 10);

        board.setCellAlive(5, 5);
        board.setCellAlive(6, 5);
        board.setCellAlive(7, 5);
        board.setCellAlive(6, 4);
        board.setCellAlive(7, 6);

        board.draw();
        board.advanceGeneration();
        board.advanceGeneration();
        board.advanceGeneration();
        board.draw();
    }
}