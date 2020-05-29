import java.util.*;

public class exponential {
    /**
     * Exponential Function
     * Author: Eunsuk Choi - 40002230 - Eunsuk_c@hotmail.com
     * Referred https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
     *
     * In progress, I need to get rid of Math.abs()
     */

    public static double power(double p1, double p2){

        //If the second argument is positive or negative zero, then the result is 1.0.
        if (p2==0.0 || p2==-0.0)
            return 1.0;
        //If the second argument is 1.0, then the result is the same as the first argument.
        if (p2==1.0)
            return p1;

        //If the second argument is NaN, then the result is NaN.
        if (Double.isNaN(p2))
            return Double.NaN;

        //If the first argument is NaN and the second argument is nonzero, then the result is NaN.
        if (Double.isNaN(p1) && p2!=0.0)
            return Double.NaN;

        //If the absolute value of the first argument is greater than 1 and the second argument is positive infinity, or the absolute value of the first argument is less than 1 and the second argument is negative infinity, then the result is positive infinity.
        if ((Math.abs(p1)>1.0 && p2==Double.POSITIVE_INFINITY) || (Math.abs(p1)<1.0 && p2==Double.NEGATIVE_INFINITY))
            return Double.POSITIVE_INFINITY;

        //If the absolute value of the first argument is greater than 1 and the second argument is negative infinity, or the absolute value of the first argument is less than 1 and the second argument is positive infinity, then the result is positive zero.
        if ((Math.abs(p1)>1.0 && p2==Double.NEGATIVE_INFINITY) || (Math.abs(p1)<1.0 && p2 == Double.POSITIVE_INFINITY))
            return +0.0;

        //If the absolute value of the first argument equals 1 and the second argument is infinite, then the result is NaN.
        if (Math.abs(p1)==1.0 && Double.isFinite(p2))
            return Double.NaN;

        //If the first argument is positive zero and the second argument is greater than zero, or the first argument is positive infinity and the second argument is less than zero, then the result is positive zero.
        if ((p1==0.0 && p2>0.0) || (p1==Double.POSITIVE_INFINITY && p2 <0.0))
            return 0.0;

        //If the first argument is positive zero and the second argument is less than zero, or the first argument is positive infinity and the second argument is greater than zero, then the result is positive infinity.
        if ((p1==0.0 && p2<0.0) || (p1==Double.POSITIVE_INFINITY && p2 >0.0))
            return Double.POSITIVE_INFINITY;

        //If the first argument is negative zero and the second argument is greater than zero but not a finite odd integer,
        // or the first argument is negative infinity and the second argument is less than zero but not a finite odd integer, then the result is positive zero.
        if ((p1==-0.0 && p2>0.0 )|| (p1==Double.NEGATIVE_INFINITY && p2<0.0))
            return +0.0;

        //If the first argument is negative zero and the second argument is a positive finite odd integer,
        // or  the first argument is negative infinity and the second argument is a negative finite odd integer,  then the result is negative zero.
        if ((p1 ==-0.0 && p2>0.0) || (p1==Double.NEGATIVE_INFINITY && p2<0.0))
            return -0.0;

        //If the first argument is negative zero and the second argument is less than zero but not a finite odd integer,
        // or the first argument is negative infinity and the second argument is greater than zero but not a finite odd integer, then the result is positive infinity.
        if ((p1==-0.0 && p2<0.0) || (p1==Double.NEGATIVE_INFINITY && p2>0.0))
            return Double.POSITIVE_INFINITY;

        //If the first argument is negative zero and the second argument is a negative finite odd integer,
        // or the first argument is negative infinity and the second argument is a positive finite odd integer, then the result is negative infinity.
        if ((p1==-0.0 && p2<0.0) || (p1==Double.NEGATIVE_INFINITY && p2>0.0))
            return Double.NEGATIVE_INFINITY;
        /*
          If the first argument is finite and less than zero
          if the second argument is a finite even integer, the result is equal to the result of raising the absolute value of the first argument to the power of the second argument
          if the second argument is a finite odd integer, the result is equal to the negative of the result of raising the absolute value of the first argument to the power of the second argument
          if the second argument is finite and not an integer, then the result is NaN.
        */
        if (Double.isFinite(p1) && p1<0.0)
            return Double.NaN;
        else if (Double.isFinite(p2) && p2%2==0)
            return 0; //to be fixed
        else if (Double.isFinite(p2) && p2%2==1)
            return 0; //to be fixed
        else if (Double.isFinite(p2))
            return Double.NaN;

        //If both arguments are integers, then the result is exactly equal to the mathematical result of raising the first argument to the power of the second argument if that result can in fact be represented exactly as a double value.
        if (p2 % 2 ==0.0)
            return power(p1, p2/2) * power(p1, p2/2);
        else
            return p1 * power(p1, p2/2) * power(p1, p2/2);
    }
// Testing
//    public static void main(String []args){
//        exponential test = new exponential();
//        System.out.println(test.power(1, 2));
//    }
}
