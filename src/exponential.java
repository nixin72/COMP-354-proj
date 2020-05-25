import java.util.*;

class exponential {

    static double power(double p1, double p2){

        if (p1==0 && p2==0)
            return 0; //Supposed to print "Error"
        else if (p2==0)
            return 1.0;
        else if (p2==1)
            return p1;
        //        else if (p2<0){
//            p2=-1*(p2);
//            //System.out.println(p2);
//            if (p2 % 2 ==0)
//                return 1/(power(p1, p2/2) * power(p1, p2/2));
//            else
//                return 1/(p1 * power(p1, p2/2) * power(p1, p2/2));
//        }

        else if (p2 % 2 ==0)
            return power(p1, p2/2) * power(p1, p2/2);
        else
            return p1 * power(p1, p2/2) * power(p1, p2/2);

    }

    public static void main(String []args){
        exponential test = new exponential();
        System.out.println(test.power(1, 2));
    }
}
