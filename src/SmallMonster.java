import java.util.Random;
import java.util.Scanner;

public class SmallMonster extends Monster{

    public SmallMonster(int size) {
        super(size);
        this.image = "\uD83D\uDC79";
    }

    @Override
    public boolean taskMonster(int difficulty){
        Random r = new Random();
        short a = (short) r.nextInt(50);
        short b = (short) r.nextInt(a+1);

        System.out.println("Маленький Монстр задает вопрос:" + a + " - "+ b + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();

        if (a - b == ans){
            System.out.println("ТЫ ПОБЕДИЛО МЕЛКОГО МОНСТРА МОЖЕШЬ ИДТИ ДАЛЬШЕ");
            return true;
        } else {
            System.out.println("ТЫ ПРОИГРАЛО КАКИМ-ТО ОБРАЗОМ ДЕРЖИ МИНУС 1(1000) ХП");
            return false;
        }
    }
}