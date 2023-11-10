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
                if(maze.getDirections()==4) {
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
                }else if(maze.getDirections()==8) {
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
                if(!done && !path.equals("DL")) {
                	path = "UR";
                	done = resolverLaberinto(startRow, startColumn + 1); //UR
                }
                if(!done && !path.equals("DR")) {
                	path = "UL";
                	done = resolverLaberinto(startRow - 1, startColumn - 1); //UL
                }
                if(!done && !path.equals("UR")) {
                	path = "DL";
                	done = resolverLaberinto(startRow + 1, startColumn - 1); //UL
                }
                if(!done && !path.equals("UL")) {
                	path = "DR";
                	done = resolverLaberinto(startRow + 1, startColumn + 1); //UL
                }
                }
                
                if (done) {
                    maze.markPath(startRow, startColumn, path);
                }
            }
        }
        return done;
    }
}
