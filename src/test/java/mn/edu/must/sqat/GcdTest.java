package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GcdTest {

    @Test
    @DisplayName("D13(x,y) -> U20(x), P15(y=false): no loop")
    void testGcd7And0() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("D13(x,y) -> loop uses; D16(tmp) -> U18(tmp)")
    void testGcd0And5() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }

    @Test
    @DisplayName("D17(x), D18(y) -> next iteration uses; loop exit")
    void testGcd12And8() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("Additional multi-iteration GCD case")
    void testGcd18And12() {
        assertEquals(6, DataFlowTargets.gcd(18, 12));
    }
}
