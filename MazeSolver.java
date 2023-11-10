public class MazeSolver {
    private Maze maze;
    private String path = "";

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public boolean resolverLaberinto(int startRow, int startColumn) {
        boolean done = false;
        int[][] grid = maze.getGrid();

        if (maze.solved(startRow, startColumn)) {
            done = true;
        } else {
            if (maze.validPosition(startRow, startColumn)) {
                maze.tryPosition(startRow, startColumn);
                if (!done && !path.equals("D")) {
                    path = "U";
                    done = resolverLaberinto(startRow - 1, startColumn); // up
                }
                if (!done && !path.equals("R")) {
                    path = "L";
                    done = resolverLaberinto(startRow, startColumn - 1); // left
                }
                if (!done && !path.equals("U")) {
                    path = "D";
                    done = resolverLaberinto(startRow + 1, startColumn); // down
                }
                if (!done && !path.equals("L")) {
                    path = "R";
                    done = resolverLaberinto(startRow, startColumn + 1); // right
                }
                if (done) {
                    maze.markPath(startRow, startColumn, path);
                }
            }
        }
        return done;
    }
}
