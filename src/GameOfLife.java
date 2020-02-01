/**
 * Created by bimsum on 1/29/20.
 */
public class GameOfLife {

    public static void main(String[] args) {

        // Initializing the grid

        int[][] lifeBoard = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        printLifeState(lifeBoard);
        // Method to generate next gen
        for (int x = 1; x <= 35; x++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lifeBoard = nextGeneration(lifeBoard);
        }

    }

    private static void printLifeState(int grid[][]) {
        // Displaying the grid
        int X = grid[0].length;
        int Y = grid.length;
        System.out.println("The size of grid: " + grid.length + " by " + grid[0].length + "\n");

        ;
        for (int i = 0; i < X; i++) {
            StringBuilder string = new StringBuilder();
            string.append("" +
                    "| ");
            for (int j = 0; j < Y; j++) {
                if (grid[i][j] == 0) {
                    string.append(" . ");
                } else {
                    string.append(" * ");
                }
            }
            string.append(" | ");
            System.out.println(string);
        }
        System.out.println("\n");
    }
    private static int[][] nextGeneration(int grid[][]) {
        int X = grid[0].length;
        int Y = grid.length;
        int[][] futureGen = new int[X][Y];

        System.out.println("Number of live neighbour based on position");
        for (int k = 1; k < X-1; k++) {
            for (int l = 1; l < Y-1; l++) {

                int liveNeighbour = 0;
                for (int m = -1; m <= 1; m++) {

                    for (int n = -1; n <= 1; n++) {
                        int a = k+m;
                        int b = l+n;
                        liveNeighbour = liveNeighbour + grid[a][b];
                    }
                }
                liveNeighbour = liveNeighbour - grid[k][l];
             //   System.out.println("Grid[" + k + "][" + l + "] = " + liveNeighbour);

                if (liveNeighbour < 2 && grid[k][l] == 1){
                    futureGen[k][l] = 0;
                }

                if (grid[k][l] == 1 && (liveNeighbour == 2 || liveNeighbour == 3)){
                    futureGen[k][l] = 1;
                }

                if (grid[k][l] == 1 && (liveNeighbour > 3)){
                    futureGen[k][l] = 0;
                }

                if (grid[k][l] == 0 && liveNeighbour == 3){
                    futureGen[k][l] = 1;
                }
            }
        }

        printLifeState(futureGen);
        return futureGen;
    }
}



