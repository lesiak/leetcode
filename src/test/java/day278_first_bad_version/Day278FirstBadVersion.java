package day278_first_bad_version;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public class Day278FirstBadVersion {

    @Test
    void testDetectBadVersion() {
        var detectedBad = firstBadVersion(5, badVersion(4));
        Assertions.assertThat(detectedBad).isEqualTo(4);
    }

    Predicate<Integer> badVersion(int firstBad) {
        return x -> x >= firstBad;
    }

    public int firstBadVersion(int n, Predicate<Integer> isBadVersion) {
        var left = 1;
        var right = n;

        while (left < right) {
            var mid = left + (right - left) / 2;
            if (isBadVersion.test(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
