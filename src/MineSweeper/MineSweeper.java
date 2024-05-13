package MineSweeper;
import java.util.Scanner;
import java.util.Random;

public class MineSweeper { // Değerlendirme formu 5
    public  void Run() {
        Scanner inp = new Scanner(System.in);
        Random rand = new Random();
        final char DEFAULT_SYMBOL = '-';
        final char MINE = '*';

        // ------------------------------------ GET MATRİX VALUES FROM USER ----------------------------------------

        int row, column;
        do { // Değerlendirme formu 7
            System.out.println("Enter the number of rows (minimum 2): ");
            row = inp.nextInt();
            System.out.println("Enter the number of columns (minimum 2): ");
            column = inp.nextInt();
        } while (row < 2 || column < 2);

        char[][] matrix = new char[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                matrix[i][j] = DEFAULT_SYMBOL;
        }
        System.out.println("The matrix that you created : ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }

        // -----------------------CALCULATİNG THE NUMBER OF MİNES AND PLACİNG THEM -------------------------------

        // Değerlendirme formu 8
        int AreaofBoard = row * column;
        int NumberofMines = row * column/ 4;

        for (int i = 0; i < NumberofMines; i++) {
            int randRow = rand.nextInt(row);
            int randColumn = rand.nextInt(column);

            while (matrix[randRow][randColumn] == MINE) {
                randRow = rand.nextInt(row);
                randColumn = rand.nextInt(column);
            }
            matrix[randRow][randColumn] = MINE;
        }

        System.out.println("The matrix filled with mines : ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        // ------------------------------------- GET VALUE FROM USER --------------------------------------------

        boolean gameOver = false;
        while (!gameOver) {

            int selectedRow, selectedColumn;

            do { // Değerlendirme formu 9
                System.out.println("Enter a row (0 to " + (row - 1) + "): ");
                selectedRow = inp.nextInt();
                System.out.println("Enter a column (0 to " + (column - 1) + "): ");
                selectedColumn = inp.nextInt();
                boolean isSelected = true;
                // Değerlendirme formu 11
                char cell = matrix[selectedRow][selectedColumn];  //when selected a cell.


                if (cell == MINE) { //check the mine, print all matrix and game over. // Değerlendirme formu 13
                    System.out.println("Game Over! You stepped on a mine.");
                    gameOver = true;
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++)
                            System.out.print(matrix[i][j] + " ");
                        System.out.println();
                    }

                } else {
                    isSelected = true; //if a cell is selected, just show the default symbols and hints.
                    int countOfCell = checkNearbyMines(matrix, selectedRow, selectedColumn);
                    cell = (char) (countOfCell + '0'); // int cell is turning to char.
                    if (isSelected && matrix[selectedRow][selectedColumn] != MINE) {
                        matrix[selectedRow][selectedColumn] = cell;
                    }

                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            if (isSelected && matrix[i][j] == MINE) {
                                System.out.print(DEFAULT_SYMBOL + " ");
                            } else {
                                System.out.print(matrix[i][j] + " ");
                            }
                        }
                        System.out.println();
                    }
                }

                if (isGameWon(matrix)) { //winning condition. // Değerlendirme formu 14
                    System.out.println("You won the game!"); // Değerlendirme formu 15
                    gameOver = true;
                }

            } while (!isValidInput(selectedRow, selectedColumn, row, column)); // Değerlendirme formu 10

        }
    }
    // -----------------------------------------  METHODS  ---------------------------------------------------------
    // Değerlendirme formu 6
    private static boolean isGameWon(char[][] matrix) {
        char DEFAULT_SYMBOL = '-';
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == DEFAULT_SYMBOL) {
                    return false;
                }
            }
        }
        return true;
    }
    private static int checkNearbyMines(char[][] matrix, int row, int column) { // Değerlendirme formu 12
        int count = 0;
        final char MINE = '*';

        // Sağ
        if (column + 1 < matrix[0].length && matrix[row][column + 1] == MINE) {
            count++;
        }
        // Sol
        if (column - 1 >= 0 && matrix[row][column - 1] == MINE) {
            count++;
        }
        // Üst
        if (row - 1 >= 0 && matrix[row - 1][column] == MINE) {
            count++;
        }
        // Alt
        if (row + 1 < matrix.length && matrix[row + 1][column] == MINE) {
            count++;
        }
        // Üst Sol Capraz
        if (row - 1 >= 0 && column - 1 >= 0 && matrix[row - 1][column - 1] == MINE) {
            count++;
        }
        // Üst Sağ Capraz
        if (row - 1 >= 0 && column + 1 < matrix[0].length && matrix[row - 1][column + 1] == MINE) {
            count++;
        }
        // Alt Sol Capraz
        if (row + 1 < matrix.length && column - 1 >= 0 && matrix[row + 1][column - 1] == MINE) {
            count++;
        }
        // Alt Sağ Capraz
        if (row + 1 < matrix.length && column + 1 < matrix[0].length && matrix[row + 1][column + 1] == MINE) {
            count++;
        }

        return count;
    }

    private static boolean isValidInput(int selectedRow, int selectedColumn, int row, int column) {
        return selectedRow >= 0 && selectedRow < row && selectedColumn >= 0 && selectedColumn < column;
    }
    // ----------------------------------------   RUN THE CODE -----------------------------------------------------
    public static void main(String[] args) {
        MineSweeper ms = new MineSweeper();
        ms.Run();
    }
}




