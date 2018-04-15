package me.gavincook.commons.util;

/**
 * @author Hinsteny
 * @date 2018-04-15
 * @since 1.0.0
 */
public class ByteUtils {

    /**
     * translate bytes arrays to hex-string
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        String temp;
        for (byte i : bytes) {
            temp = Integer.toHexString(i & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            hex.append(temp);
        }
        return hex.toString().toUpperCase();
    }

    /**
     * translate hex-bytes to bytes
     * @param data
     * @return
     */
    public static byte[] hexToyte(byte[] data) {
        if (null == data || data.length % 2 != 0) {
            throw new IllegalArgumentException("Illegal parameters");
        }
        byte[] bytes = new byte[data.length / 2];
        String temp;
        for (int n = 0; n < data.length; n+=2) {
            temp = new String(data, n, 2);
            bytes[n/2] = (byte) Integer.parseInt(temp, 16);
        }

        return bytes;
    }


}
