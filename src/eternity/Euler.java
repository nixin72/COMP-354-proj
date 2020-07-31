public class Euler {

  private static final double TWO_54 = 0x40000000000000L,
      P1 = 0.16666666666666602,
      P2 = -2.7777777777015593e-3,
      P3 = 6.613756321437934e-5,
      P4 = -1.6533902205465252e-6,
      P5 = 4.1381367970572385e-8,
      TWO_28 = 0x10000000,
      EXP_LIMIT_H = 709.782712893384,
      EXP_LIMIT_L = -745.1332191019411,
      LN2 = 0.6931471805599453,
      LN2_H = 0.6931471803691238,
      LN2_L = 1.9082149292705877e-10,
      INV_LN2 = 1.4426950408889634,
      INV_LN2_H = 1.4426950216293335,
      INV_LN2_L = 1.9259629911266175e-8;

  public static double abs(double a) {
    return (a <= 0) ? 0 - a : a;
  }

  public static double exp(double a) {
    if (a != a) return a;
    if (a > EXP_LIMIT_H) return Double.POSITIVE_INFINITY;
    if (a < EXP_LIMIT_L) return 0;

    double d1, d2;
    int i;
    double t = abs(a);

    if (t > 0.5 * LN2) {
      if (t < 1.5 * LN2) {
        d1 = t - LN2_H;
        d2 = LN2_L;
        i = 1;
      } else {
        i = (int) (INV_LN2 * t + 0.5);
        d1 = t - i * LN2_H;
        d2 = i * LN2_L;
      }
      if (a < 0) {
        d1 = -d1;
        d2 = -d2;
        i = -i;
      }
      a = d1 - d2;
    } else if (t < 1 / TWO_28) return 1;
    else d2 = d1 = i = 0;

    t = a * a;
    double c = a - t * (P1 + t * (P2 + t * (P3 + t * (P4 + t * P5))));
    if (i == 0) return 1 - (a * c / (c - 2) - a);
    double y = 1 - (d2 - a * c / (2 - c) - d1);
    return scale(y, i);
  }

  private static double scale(double x, int n) {
    //        if (Configuration.DEBUG && abs(n) >= 2048)
    //            throw new InternalError("Assertion failure");
    if (x == 0 || x == Double.NEGATIVE_INFINITY || !(x < Double.POSITIVE_INFINITY) || n == 0)
      return x;
    long bits = Double.doubleToLongBits(x);
    int exp = (int) (bits >> 52) & 0x7ff;
    if (exp == 0) // Subnormal x.
    {
      x *= TWO_54;
      exp = ((int) (Double.doubleToLongBits(x) >> 52) & 0x7ff) - 54;
    }
    exp += n;
    if (exp > 0x7fe) // Overflow.
    return Double.POSITIVE_INFINITY * x;
    if (exp > 0) // Normal.
    return Double.longBitsToDouble((bits & 0x800fffffffffffffL) | ((long) exp << 52));
    if (exp <= -54) return 0 * x; // Underflow.
    exp += 54; // Subnormal result.
    x = Double.longBitsToDouble((bits & 0x800fffffffffffffL) | ((long) exp << 52));
    return x * (1 / TWO_54);
  }

  // public static void main(String[] args) {
  //     eternity.Euler test = new eternity.Euler();
  //     System.out.println(test.exp(3));
  // }
}
