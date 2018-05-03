
import java.util.Random;

public class HistogramProgram {
    public static void main(String[] args) {
        byte[] arr = new byte[10];
        Random rand = new Random();
        for(int i = 0; i < 100; i++){
            int randNum = rand.nextInt(10)+0;
            arr[randNum] += 1;
        }
        for(int j = 0; j < arr.length;j++){
            System.out.print(j+" | ");
            for(int k = 0; k <arr[j]; k++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
