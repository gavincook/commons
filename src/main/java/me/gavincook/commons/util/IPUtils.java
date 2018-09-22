package me.gavincook.commons.util;

import java.net.InetAddress;

/**
 * IPUtils
 *
 * @author gavincook
 * @version $ID: IPUtils.java, v0.1 2018-01-09 16:35 gavincook Exp $$
 */
public class IPUtils {

    /**
     * 虚拟机IP
     */
    public static final String JVM_SERVER_IP = "server.ip";

    /**
     * 容器IP
     */
    public static final String ENV_SERVER_IP = "container.ip";

    private static String VM_IP;

    /**
     * 获取JVM的IP
     */
    public static String getVMIp() {
        if (StringUtils.isNullOrEmpty(VM_IP)) {
            try {
                // 如果配置了vm参数，已vm参数为准
                String vmIp = System.getProperty(JVM_SERVER_IP);
                if (!StringUtils.isNullOrEmpty(vmIp)) {
                    VM_IP = vmIp;
                    return VM_IP;
                }

                // 主要针对docker容器，网络是nat模式，需要将映射IP放入环境变量
                String envIp = System.getenv(ENV_SERVER_IP);
                if (!StringUtils.isNullOrEmpty(envIp)) {
                    VM_IP = envIp;
                    return VM_IP;
                }

                if (StringUtils.isNullOrEmpty(VM_IP)) {
                    VM_IP = InetAddress.getLocalHost().getHostAddress();
                }
            } catch (Exception e) {
                VM_IP = "127.0.0.1";
            }
        }
        return VM_IP;
    }

}
