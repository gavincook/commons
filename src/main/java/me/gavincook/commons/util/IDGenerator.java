package me.gavincook.commons.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ID生成器
 *
 * @author gavincook
 * @version $ID: IDGenerator.java, v0.1 2018-01-09 16:31 gavincook Exp $$
 */
public class IDGenerator {

    /**
     * ip
     */
    private static final String ip;

    /**
     * 16进制ip
     */
    private static final String hexIP;

    /**
     * 计数器
     */
    private static AtomicInteger counter = new AtomicInteger(0);

    /**
     * 字母，用于生成随机数
     */
    private static char letters[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 随机数对象
     */
    private static Random random = new Random();

    static {
        ip = IPUtils.getVMIp();
        hexIP = ipToHex(ip);
    }

    /**
     * 时间戳16进制
     */
    private static String format(long longval) {
        String formatted = Long.toHexString(longval);
        StringBuffer buf = new StringBuffer("00000000000");
        buf.replace(11 - formatted.length(), 11, formatted);
        return buf.toString();
    }

    /**
     * 获取计数器
     */
    private static String getCount() {
        return String.valueOf((counter.getAndIncrement() & 0x7fffffff) % 9000 + 1000);
    }

    /**
     * ip 16进制
     */
    private static String ipToHex(String ip) {
        byte[] ipData = new byte[4];
        String[] ipStrArray = ip.split("\\.");
        for (int i = 0; i < 4; i++) {
            ipData[i] = (byte) (Integer.parseInt(ipStrArray[i]));
        }
        StringBuilder hexStr = new StringBuilder(8);
        for (int i = 0; i < 4; i++) {
            hexStr.append(Integer.toHexString(ipData[i] >> 4 & 0x0F));
            hexStr.append(Integer.toHexString(ipData[i] & 0x0F));
        }
        return hexStr.toString();
    }

    /**
     * 获取指定位数的随机数
     */
    private static String random(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(letters[random.nextInt(25)]);
        }
        return sb.toString();
    }

    /**
     * 生成ID
     */
    public static String generateID() {
        return format(System.currentTimeMillis()) + hexIP + getCount() + random(5);
    }
}
