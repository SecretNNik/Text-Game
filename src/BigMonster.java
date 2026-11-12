import java.util.Random;
import java.util.Scanner;

public class BigMonster extends Monster{

    public BigMonster(int size) {
        super(size);
        this.image = "\uD83E\uDDCC";
    }

    @Override
    public boolean taskMonster(int difficulty){
        if (difficulty == 1){
            return super.taskMonster(difficulty);
        }

        Random r = new Random();
        int max = difficulty * 10;
        int a = r.nextInt(max);
        int b = r.nextInt(max);
        int c = r.nextInt(max);

        System.out.println("Большой Монстр задает вопрос:" + a + " + "+ b + " - "+ c + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();

        if (a + b - c == ans){
            System.out.println("ТЫ ПОБЕДИЛО БОЛЬШОГО МОНСТРА ПОЛУЧИ 1 ХП НЕ ВЫШЕ ЛИМИТА И МОЖЕШЬ ИДТИ ДАЛЬШЕ");
            return true;
        } else {
            System.out.println("ТЫ ПРОИГРАЛО БОЛЬШОМУ МОНСТРУ ДЕРЖИ МИНУС 1 ХП");
            return false;
        }
    }
}