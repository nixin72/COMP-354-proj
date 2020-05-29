import eternity.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTests {

    @Test
    public void any_log_of_1(){
        assertEquals(Calculator.log_e_x(1), 1);
    }

}
