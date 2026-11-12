import java.util.Random;

public class Person {
    private int x, y;
    public String image = "\uD83E\uDDD9\u200D";
    private int live = 3;

    public Person(int size){
        this.y = size;
        int n = new Random().nextInt(size) +1;
        this.x = n;
    }

    public boolean isMoveCorrect(int newX, int newY){
        return (this.x == newX && Math.abs(this.y - newY) == 1) ||
               (this.y == newY && Math.abs(this.x - newX) == 1);
    }
    public void move(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }

    public void downLive(){
        if (live > 0) live--;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getLive() {return live;}
    public String getImage() {return image;}
}