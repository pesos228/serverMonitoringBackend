package org.coursework.servermonitoing.models;

import java.util.Objects;
import java.util.Random;

public class IpAddress {
    private final String value;

    private static final Random RANDOM_GENERATOR = new Random();


    public IpAddress(String ipValue) {
        if (!isValid(ipValue)) {
            throw new IllegalArgumentException("Invalid IPv4 address format: " + ipValue);
        }

        this.value = ipValue;
    }


    public String getValue() {
        return value;
    }

    public static boolean isValid(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }

        String[] octets = ip.split("\\.");
        if (octets.length != 4) {
            return false;
        }

        for (String octet : octets) {
            if (octet.isEmpty()) {
                return false;
            }
            if (octet.length() > 3) {
                return false;
            }
            if (octet.length() > 1 && octet.startsWith("0")) {
                return false;
            }
            try {
                int num = Integer.parseInt(octet);
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static IpAddress random() {
        String randomIpString = String.format("%d.%d.%d.%d",
                RANDOM_GENERATOR.nextInt(256),
                RANDOM_GENERATOR.nextInt(256),
                RANDOM_GENERATOR.nextInt(256),
                RANDOM_GENERATOR.nextInt(256)
        );
        return new IpAddress(randomIpString);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpAddress ipAddress = (IpAddress) o;
        return value.equals(ipAddress.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
