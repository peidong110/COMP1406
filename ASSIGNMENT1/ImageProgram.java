//This code is created by Payton Pei Student# 101065299
import java.util.Random;
public class ImageProgram {
    public static void main(String[] args) {
        int[] indexNumber = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        char[][] arr = new char[10][10];
        char[][] NewArray = new char[10][10];
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boolean trueOrWrong = rand.nextBoolean();
                if (trueOrWrong == true) {
                    arr[i][j] = 'o';
                } else {
                    arr[i][j] = '.';
                }
                NewArray[i][j] = arr[i][j];
            }
        }
        for (int z : indexNumber) {
            for (int m : indexNumber) {
                System.out.print(NewArray[z][m]);
            }
            System.out.print("\n");
        }
        int maxVal = 0;
        for (int row = 0; row < 10; row++) {
            int counter = 0;
            for (int col = 0; col < 10; col++) {
                if (NewArray[row][col] == 'o') {
                    counter += 1;
                } else {
                    if (maxVal < counter) {
                        maxVal = counter;
                    }
                    counter = 0;
                }
            }
        }
        System.out.println("The Longest Horizontal Sequence is: "+maxVal);
        int maxVer = 0;
        for (int VCol = 0; VCol < 10; VCol++) {
            int counter = 0;
            for (int Hor = 0; Hor < 10; Hor++) {
                if (NewArray[Hor][VCol] == 'o') {
                    counter += 1;
                } else {
                    if (maxVer < counter) {
                        maxVer = counter;
                    }
                    counter = 0;
                }
            }
        }
        System.out.println("The Longest Vertical Sequence is: "+maxVer);
    }

}


