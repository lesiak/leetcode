package day468_validate_IP_address;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class Day468ValidateIPAddress {
    private static final String IPv4 = "IPv4";
    private static final String IPv6 = "IPv6";
    private static final String Neither = "Neither";

    public static final Set<Character> ipV6Chars = Set.of(
            'a', 'b', 'c', 'd', 'e', 'f',
            'A', 'B', 'C', 'D', 'E', 'F',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    @Test
    void testValidIPv4() {
        var queryIP = "172.16.254.1";
        var validationResult = validIPAddress(queryIP);
        assertThat(validationResult).isEqualTo(IPv4);
    }

    @Test
    void testInvalidIPv4WithLeadingZeros() {
        var queryIP = "172.16.04.1";
        var validationResult = validIPAddress(queryIP);
        assertThat(validationResult).isEqualTo(Neither);
    }

    @Test
    void testInvalidIPv4MissingPart() {
        var queryIP = "172.16..1";
        var validationResult = validIPAddress(queryIP);
        assertThat(validationResult).isEqualTo(Neither);
    }

    @Test
    void testInvalidIPv4WithExtraTrailingPArt() {
        var queryIP = "172.16.4.1.";
        var validationResult = validIPAddress(queryIP);
        assertThat(validationResult).isEqualTo(Neither);
    }

    @Test
    void testInvalidIPv4TooBigNumber() {
        var queryIP = "256.257.111.1";
        var validationResult = validIPAddress(queryIP);
        assertThat(validationResult).isEqualTo(Neither);
    }

    @Test
    void testValidIPv6() {
        var queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        var validationResult = validIPAddress(queryIP);
        assertThat(validationResult).isEqualTo(IPv6);
    }

    @Test
    void testInvalidIPv6ExtraEmptyPart() {
        var queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        var validationResult = validIPAddress(queryIP);
        assertThat(validationResult).isEqualTo(Neither);
    }



    public String validIPAddress(String queryIP) {
        if (isValidIPv4(queryIP)) {
            return IPv4;
        } else if (isValidIPv6(queryIP)) {
            return IPv6;
        } else {
            return Neither;
        }

    }



    public boolean isValidIPv4(String queryIP) {
        String[] v4Parts = queryIP.split("\\.", -1);
        if (v4Parts.length != 4) {
            return false;
        }
        for (var part : v4Parts) {
            if (!isValidIPv4part(part)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidIPv4part(String part) {
        if (part.length() == 0 || part.length() > 3) {
            return false;
        }
        int num = 0;
        for (int i = 0; i < part.length(); i++) {
            char cur = part.charAt(i);
            if (cur < '0' || cur > '9') {
                return false;
            }
            num = num * 10 + part.charAt(i) - '0';
        }
        return (part.charAt(0) != '0' || part.length() == 1) && num <= 255;
    }

    public boolean isValidIPv6(String queryIP) {
        String[] v6Parts = queryIP.split(":", -1);
        if (v6Parts.length != 8) {
            return false;
        }
        for (var part : v6Parts) {
            if (!isValidIPv6part(part)) {
                return false;
            }
        }
        return true;
    }


    public boolean isValidIPv6part(String part) {
        if (part.isEmpty()) {
            return false;
        }
        else if (part.length() > 4) {
            return false;
        } else {
            for (int i =0 ; i < part.length(); ++i ) {
                if (!ipV6Chars.contains(part.charAt(i))) {
                    return false;
                }
                //
                // var cur = part.charAt(i);
                // if ((cur < '0' || cur > '9') && (cur < 'a' || cur > 'f') && (cur < 'A' || cur > 'F')) {
                //    return false;
                //}

            }
        }
        return true;
    }
}
