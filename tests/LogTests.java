import eternity.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTests {

    @Test
    public void any_log_of_1() {
        assertEquals(Calculator.log_e_x(1), 0);
    }

    @Test
    public void minus_infinity() {
        assertEquals(Calculator.log_e_x(-0.0000000001), Double.NEGATIVE_INFINITY);
        assertEquals(Calculator.log_e_x(0), Double.NEGATIVE_INFINITY);
        assertEquals(Calculator.log_e_x(-1.371), Double.NEGATIVE_INFINITY);
        assertEquals(Calculator.log_e_x(-4.2), Double.NEGATIVE_INFINITY);
        assertEquals(Calculator.log_e_x(-33.00013), Double.NEGATIVE_INFINITY);
        assertEquals(Calculator.log_e_x(-1000.23), Double.NEGATIVE_INFINITY);
        assertEquals(Calculator.log_e_x(-1000000.9991), Double.NEGATIVE_INFINITY);
    }

    @Test
    public void precision_check() {
        assertEquals(Calculator.log_e_x(4), 1.38629436111989061);
        assertEquals(Calculator.log_e_x(10), 2.30258509299404568);
        assertEquals(Calculator.log_e_x(22), 3.09104245335831585);
        assertEquals(Calculator.log_e_x(67.3), 4.209160236650682);
        assertEquals(Calculator.log_e_x(96.782), 4.572461026578386);
    }

}
