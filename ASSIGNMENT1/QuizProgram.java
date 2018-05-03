//The assignment is edited by Payton Pei, Student# 101065299
import java.util.Random;
import java.util.Scanner;
public class QuizProgram {
    public static void main(String[] args) {
        char trueOrWrong[] = {'-','+'};
        int counter = 0;

        for(int tenTimes = 0; tenTimes < 10; tenTimes++){
            Random rand = new Random();
            int randomPlusOrMinus = rand.nextInt(2)+0;
            int n1 = rand.nextInt(100)+0;
            //n2 is equal to 0 to n1 inclusively
            int n2 = rand.nextInt(n1+1)+0;
            int answerAddition = n1 + n2;
            int answerSubtraction = n1 - n2;
            Scanner usrInput = new Scanner(System.in);
            System.out.println("What is the answer of : "+n1+trueOrWrong[randomPlusOrMinus]+n2);
            int ans = usrInput.nextInt();

            if(trueOrWrong[randomPlusOrMinus] == '+'){
                if(ans == answerAddition){

                    System.out.println("Yes, you are correct");
                    counter += 1;
                }
                else{
                    System.out.println("no,the answer is "+answerAddition);
                }
            }
            else{
                if(ans == answerSubtraction){
                    System.out.println("Yes, the answer is correct");
                    counter += 1;

                }
                else{
                    System.out.println("no the answer is: "+ answerSubtraction);
                }
            }
        }

        System.out.println("Your final mark is "+counter*10+" %");

    }
}
