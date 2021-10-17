public class Robot {

    private int x;
    private int y;
    private Direction facing;
    private Table table;
    private boolean isOnTable = false;

    public Robot(){
        this.x = 0;
        this.y = 0;
        this.facing = null;
        this.table = null;
    }

    public void place(Table table, int x, int y, Direction d){
        if (table.isValidPosition(x, y) && d != null) {
            this.x = x;
            this.y = y;
            this.facing = d;
            this.table = table;
            this.isOnTable = true;
        }
    }

    public void move() {
        if (isOnTable) {
            int newX = x + facing.getX();
            int newY = y + facing.getY();
            if (table.isValidPosition(newX, newY)){
                x = newX;
                y = newY;
            }
        }
    }

    public void leftTurn() {
        if (isOnTable) {
            facing = facing.left();
        }
    }

    public void rightTurn() {
        if (isOnTable) {
            facing = facing.right();
        }
    }

    public String report() {
        return String.format("%d,%d,%s", getX(), getY(), getFacing());
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Direction getFacing(){
        return facing;
    }

    public Table getTable(){
        return table;
    }
}
