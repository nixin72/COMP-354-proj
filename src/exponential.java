import java.util.*;

class exponential {

    Scanner scan = new Scanner (System.in);

    static double power(double p1, double p2){
        double answer;

        if (p2==0)
            return 1.0;
        else if (p2==1)
            return p1;
        else if (p2 % 2 ==0)
            return power(p1, p2/2) * power(p1, p2/2);
        else
            return p1 * power(p1, p2/2) * power(p1, p2/2);

    }

    public static void main(String []args){
        exponential test = new exponential();
        System.out.println(test.power(5, 2));
    }
}
