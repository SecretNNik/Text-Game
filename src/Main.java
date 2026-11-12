import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ты готов начать игру. я знаю что ты готов просто ответь да");
        String answer = scanner.nextLine();

        switch (answer.toLowerCase()){
            case "да":
                System.out.println("startuem igru");
                break;
            case "нет":
                System.out.println("obidno");
                System.exit(404);
            default:
                System.out.println(answer + "? НЕПОНЯТНО. НО МЫ ВСЁ РАВНО НАЧНЕМ");
        }
        System.out.println("Выберите уровень сложности(1 - очень легко, 100 - близко к невозможному)");
        short difficulty = scanner.nextShort();
        if(difficulty > 100){
            System.out.println("Можно только до 100 сложности я же сказал");
            System.exit(404);
        }
         else if(difficulty >= 95){
            System.out.println("Эта сложность не рекомендуется для прохождения!");
        }

        System.out.println("Вы выбрали " + difficulty + " уровень сложности");

        System.out.println("Выберите размер одной стороны поля до 20, кратное 5(5 или 10 или 15 или 20)");
        int size = scanner.nextInt();
        if (size >20) {
            System.out.println("Слишком большое поле, опасность переполнения памяти");
            System.exit(404);
        }
        else if (size % 5 != 0) {
            System.out.println("ПОЛЕ НЕ ДЕЛИТСЯ НА 5 ЕРРОР ЕРРОР ВСЕ УМЕРЛО");
            System.exit(404);
        }
            System.out.println("Вы выбрали " + size + " размера одной стороны");

        String [][] board = new String[size][size];
        int step = 1;

        Person person = new Person(size);
        Random r = new Random();
        int castleX = r.nextInt(size) + 1;
        int castleY = 1;
        String castleImage = "🏰";

        int monsterCount = (size * size) / 5;
        Monster[] monsters = new Monster[monsterCount];
        for (int i = 0; i < monsterCount; i++){
            int type = r.nextInt(3);
            if (type==0) monsters[i] = new Monster(size);
            else if (type==1) monsters[i] = new BigMonster(size);
            else monsters[i] = new SmallMonster(size);
        }
        boolean game = true;

        while (game) {
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    board[y][x] = "  ";
                }
            }

            for (Monster m : monsters){
                if (m != null && m.getX() > 0 && m.getY() >0){
                    board[m.getY()-1][m.getX()-1] = m.getImage();
                }
            }

            board[castleY -1][castleX-1] = castleImage;
            board[person.getY() - 1][person.getX() - 1] = person.getImage();

            System.out.println("\nХод: " + step + " | Жизни: " +person.getLive());
            String wall = "+-----".repeat(size) + "+";
            for (int i = 0; i < size; i++){
                System.out.println(wall);
                for (int j = 0; j < size; j++){
                    System.out.print("| " + board [i][j] + "  ");
                }
                System.out.println("|");
            }
            System.out.println(wall);

            if (person.getLive() <=0){
                System.out.println("ТЫ УМЕРЛО");
                System.exit(404);
            }

            System.out.println("Ты на Х: " +person.getX() + ", Y: " + person.getY());
            System.out.println("КУДА ТЫ ХОЧЕШЬ ПОЙТИ ЧЕРЕЗ ПРОБЕЛ?");

            int nextX = scanner.nextInt();
            int nextY = scanner.nextInt();
            if (nextX < 1 || nextX > size || nextY < 1 || nextY > size){
                System.out.println("КУДА СБЕГАЕШЬ ДЕЗЕРТИР?");
                continue;
            }

            if (person.isMoveCorrect(nextX, nextY)){
                String nextCell = board[nextY - 1][nextX-1];

                if (nextCell.equals(castleImage)){
                    System.out.println("ТЫ ПОБЕЖДАЕШЬ В ИГРЕ ЗА " + step +" ХОДОВ, МОЖЕШЬ НАЧАТЬ ЗАНОВО. СОЗДАНО SECRETNNIK");
                    System.exit(777);
                } else if (!nextCell.equals("  ")){
                    for (int i = 0; i <monsters.length; i++){
                        if (monsters[i] != null && monsters[i].getX() == nextX && monsters[i].getY() == nextY){
                            boolean success = monsters[i].taskMonster(difficulty);
                            if (success){
                                monsters[i] = null;
                                person.move(nextX, nextY);
                            } else {
                                person.downLive();
                            }
                            break;
                        }
                    }
                } else {
                    person.move(nextX, nextY);
                }
                step++;
            } else {
                System.out.println("ТЫ СЛИШКОМ БЫСТРО");
            }
        }
    }
}