package chapter3.ThreadLocalRandomLearn;

import java.util.Random;

public class RandomLearn {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0;i<10;i++){
            System.out.println(random.nextInt(5));
        }
    }
}
