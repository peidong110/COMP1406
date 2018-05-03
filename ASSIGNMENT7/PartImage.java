import javafx.geometry.Point2D;

public class PartImage {
    private boolean[][]     pixels;
    private boolean[][]     visited;
    private int             rows;
    private int             cols;


    public PartImage(int r, int c) {
        rows = r;
        cols = c;
        visited = new boolean[r][c];
        pixels = new boolean[r][c];
        }

    public PartImage(int rw, int cl, byte[][] data) {
        this(rw,cl);
        for (int r=0; r<10; r++) {
            for (int c=0; c<10; c++) {
                if(data[r][c] == 1) {
                    pixels[r][c] = true;
                    }
                else
                    pixels[r][c]= false;

            }
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public boolean getPixel(int r, int c) { return pixels[r][c]; }

    // You will re-write the 5 methods below
    public void print() {
        for (boolean[] u : pixels){
            for (boolean z : u){
                if(z){
                    System.out.print("*");
                }else
                    System.out.print("-");
            }
            System.out.print("\n");
        }



    }
    public Point2D findStart() {
        int x = 0;
        int y = 0;
        Point2D point2D;

        boolean check = false;
        for (int i =0; i < 10;i++){
            for (int j =0; j < 10; j++){
                if (pixels[j][i]&& !check){
                    x = i;
                    y = j;
                    check = true;
                    break;
                    /*
                    if (r < 0 || r> 9 || c < 0 || c > 9) {
                        return;
                    }*/
                }
            }
        }
        point2D = new Point2D(x,y);
        return point2D;
    }
    public int partSize() {
        int size = 0;
        for (boolean[] u : pixels){
            for (boolean z : u){
                if (z){
                    size++;
                }
            }
        }
        return size;
    }
    private void expandFrom(int r, int c) {
        if (!(r < 0 || r> 9 || c < 0 || c > 9)) {
            boolean bool = pixels[r][c];
            if (bool) {
                pixels[r][c] = false;
                visited[r][c] = true;
                expandFrom(r + 1, c);
                expandFrom(r - 1, c);
                expandFrom(r, c + 1);
                expandFrom(r, c - 1);
            }
        }
            }





    private int perimeterOf(int r, int c) {
        int count;
        if (c >= cols||c<0||r >= rows||r<0)
            return 0;
        if (visited[r][c])
            return 0;
        if (!(pixels[r][c]))
            return 0;
        else
            count = val(r,c);
        visited[r][c] = true;
        return (count + perimeterOf(r+1,c)+perimeterOf(r-1,c)+perimeterOf(r,c+1)+perimeterOf(r,c-1));

        }
        private int val(int r, int c){
            int totalTiles =0;
            if(r==0)
                totalTiles++;
            if(c==0)
                totalTiles++;
            if(r==rows-1)
                totalTiles++;
            if(c==cols-1)
                totalTiles++;
            if(r>0 && !(pixels[r-1][c]))
                totalTiles++;
            if(c>0 && !(pixels[r][c-1]))
                totalTiles++;
            if(r<rows-1 && !(pixels[r+1][c]))
                totalTiles++;
            if(c<cols-1 && !(pixels[r][c+1]))
                totalTiles++;
            return totalTiles;
        }


    public boolean isBroken(){
        Point2D p = findStart();
        expandFrom((int)p.getX(), (int)p.getY());
        return (partSize() != 0);
    }

    public int perimeter() {
        Point2D p = findStart();
        return perimeterOf((int)p.getX(), (int)p.getY());
    }

    public static PartImage exampleA() {
        byte[][] pix = {{0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,0,0,0},
                {0,1,1,1,1,1,1,0,0,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,0,0,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,0,0,0},
                {0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}};
        return new PartImage(10,10, pix);
    }
    public static PartImage exampleB() {
        byte[][] pix = {{1,0,1,0,1,0,1,0,0,0},
                {1,0,1,0,1,0,1,1,1,1},
                {1,0,1,0,1,0,1,0,0,0},
                {1,0,1,0,1,0,1,1,1,1},
                {1,0,1,0,1,0,1,0,0,0},
                {1,0,1,0,1,0,1,1,1,1},
                {1,1,1,1,1,1,1,0,0,0},
                {0,1,0,1,0,0,1,1,1,1},
                {0,1,0,1,0,0,1,0,0,0},
                {0,1,0,1,0,0,1,0,0,0}};
        return new PartImage(10,10, pix);
    }
    public static PartImage exampleC() {
        byte[][] pix = {{1,1,1,0,0,0,1,0,0,0},
                {1,1,1,1,0,0,1,1,1,0},
                {1,1,1,1,1,1,1,1,1,1},
                {0,1,1,1,0,0,1,0,0,0},
                {0,0,1,0,0,0,0,0,0,0},
                {1,0,0,0,1,1,0,1,1,1},
                {1,1,0,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
                {0,0,1,1,0,1,1,1,1,1},
                {0,0,1,0,0,0,1,1,0,0}};
        return new PartImage(10,10, pix);
    }
    public static PartImage exampleD() {
        byte[][] pix = {{1,0,1,0,1,0,1,1,0,0},
                {1,0,1,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0,0,1,1},
                {1,0,1,1,1,1,1,1,1,0},
                {1,0,0,1,0,0,1,0,0,0},
                {1,1,0,0,0,1,1,0,0,1},
                {0,1,0,0,0,0,0,0,1,1},
                {0,1,0,1,0,0,0,0,0,0},
                {0,0,0,1,1,1,0,0,0,0},
                {0,0,0,0,0,1,1,0,0,0}};
        return new PartImage(10,10, pix);
    }
}
