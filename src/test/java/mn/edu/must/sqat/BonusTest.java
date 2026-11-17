
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusTest {

    @Test
    @DisplayName("AllDefs: score zero, streak reset, threshold not met")
    void testNormal() {
        int[] attendance = {1, 0, 1, 1, 1};

        assertEquals(0, DataFlowTargets.bonus(attendance, 6));
    }

    @Test
    @DisplayName("AllDefs: score five and bonus three")
    void testBest5() {
        int[] attendance = {1, 1, 1, 1, 1};

        assertEquals(8, DataFlowTargets.bonus(attendance, 5));
    }
}
