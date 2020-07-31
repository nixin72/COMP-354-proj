import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTests {

  @Test
  public void any_log_of_1() {
    assertEquals(0, Calculator.log_e_x(1));
  }

  @Test
  public void minus_infinity() {
    assertEquals(Double.NEGATIVE_INFINITY, Calculator.log_e_x(-0.0000000001));
    assertEquals(Double.NEGATIVE_INFINITY, Calculator.log_e_x(0));
    assertEquals(Double.NEGATIVE_INFINITY, Calculator.log_e_x(-1.371));
    assertEquals(Double.NEGATIVE_INFINITY, Calculator.log_e_x(-4.2));
    assertEquals(Double.NEGATIVE_INFINITY, Calculator.log_e_x(-33.00013));
    assertEquals(Double.NEGATIVE_INFINITY, Calculator.log_e_x(-1000.23));
    assertEquals(Double.NEGATIVE_INFINITY, Calculator.log_e_x(-1000000.9991));
  }

  @Test
  public void precision_check() {
    assertEquals(1.38629436111989061, Calculator.log_e_x(4), TestCalculator.epsilon);
    assertEquals(2.30258509299404568, Calculator.log_e_x(10), TestCalculator.epsilon);
    assertEquals(3.09104245335831585, Calculator.log_e_x(22), TestCalculator.epsilon);
    assertEquals(4.209160236650682, Calculator.log_e_x(67.3), TestCalculator.epsilon);
    assertEquals(4.572461026578386, Calculator.log_e_x(96.782), TestCalculator.epsilon);
  }
}
