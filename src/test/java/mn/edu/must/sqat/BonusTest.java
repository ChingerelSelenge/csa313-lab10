
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BonusTest {

    @Test
    @DisplayName("AllDefs: score zero, streak reset, threshold not met")
    void testNormal() {
        int[] attendance = { 1, 0, 1, 1, 1 };

        assertEquals(0, DataFlowTargets.bonus(attendance, 6));
    }

    @Test
    @DisplayName("AllDefs: score five and bonus three")
    void testBest5() {
        int[] attendance = { 1, 1, 1, 1, 1 };

        assertEquals(8, DataFlowTargets.bonus(attendance, 5));
    }

    @Test
    @DisplayName("DU: empty attendance, loop zero iterations")
    void testEmpty() {
        int[] attendance = {};
        assertEquals(5, DataFlowTargets.bonus(attendance, 0));
    }

    @Test
    @DisplayName("DU: all absent, streak reset and repeated")
    void testAllAbsent() {
        int[] attendance = { 0, 0, 0 };
        assertEquals(0, DataFlowTargets.bonus(attendance, 1));
    }

    @Test
    @DisplayName("DU: streak reset then new streak reaches five")
    void testStreakResetThenFive() {
        int[] attendance = { 1, 1, 0, 1, 1, 1, 1, 1 };
        assertEquals(8, DataFlowTargets.bonus(attendance, 5));
    }

    @Test
    @DisplayName("DU: streak reaches six and best is updated repeatedly")
    void testStreakSix() {
        int[] attendance = { 1, 1, 1, 1, 1, 1 };
        assertEquals(8, DataFlowTargets.bonus(attendance, 6));
    }

    @Test
    @DisplayName("DU: threshold met but best remains below five")
    void testThresholdOnly() {
        int[] attendance = { 1, 1, 0, 1, 1 };
        assertEquals(5, DataFlowTargets.bonus(attendance, 4));
    }

    @Test
    @DisplayName("Invalid input: null attendance")
    void testInvalidNullAttendance() {
        assertThrows(
                IllegalArgumentException.class,
                () -> DataFlowTargets.bonus(null, 1));
    }

    @Test
    @DisplayName("Invalid input: negative threshold")
    void testInvalidNegativeThreshold() {
        assertThrows(
                IllegalArgumentException.class,
                () -> DataFlowTargets.bonus(new int[] { 1, 1 }, -1));
    }

    @Test
    @DisplayName("M2 kill: present reaches five while streak is only four")
    void testKillM2WrongBestVariable() {
        int[] attendance = { 1, 0, 1, 1, 1, 1 };

        assertEquals(5, DataFlowTargets.bonus(attendance, 5));
    }

    @Test
    @DisplayName("DU: score definition at 49 reaches use at 54")
    void testScore49To54() {
        int[] attendance = { 1, 1, 1, 1, 1 };

        assertEquals(3, DataFlowTargets.bonus(attendance, 6));
    }
}
