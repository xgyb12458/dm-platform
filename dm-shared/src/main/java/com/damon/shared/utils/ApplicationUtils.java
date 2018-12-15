package com.damon.shared.utils;

import org.springframework.util.ObjectUtils;

import java.net.*;
import java.util.Enumeration;

/**
 * @author Damon S.
 */
public final class ApplicationUtils {

    /**
     * 获取本地MAC地址
     **/
    public static String findLocalMac() throws SocketException, UnknownHostException {
        //获取网卡，获取地址
        InetAddress address = findInetAddress();
        byte[] mac = NetworkInterface.getByInetAddress(address).getHardwareAddress();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                builder.append("-");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            builder.append(s.length() == 1 ? 0 + s : s);
        }
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        return builder.toString().toUpperCase();
    }

    /**
     * 获取本地 IP 地址
     **/
    public static String findLocalIP() throws SocketException, UnknownHostException {
        String ipAddress = null;

        InetAddress address = findInetAddress();
        if (!ObjectUtils.isEmpty(address)) {
            ipAddress = address.getHostAddress();
        }
        return ipAddress;
    }

    private static InetAddress findInetAddress() throws SocketException, UnknownHostException {
        InetAddress address = null;

        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();

            while (addresses.hasMoreElements()) {
                InetAddress candidateAddress = (InetAddress) addresses.nextElement();
                if (!ObjectUtils.isEmpty(address)
                        && address instanceof Inet4Address
                        && !address.isLoopbackAddress()
                        && address.isSiteLocalAddress()) {
                    address = candidateAddress;
                    break;
                }
            }
        }
        // 如果没有发现 non-loopback 地址.只能用最次选的方案
        if (ObjectUtils.isEmpty(address)) {
            address = InetAddress.getLocalHost();
            if (ObjectUtils.isEmpty(address)) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
        }
        return address;
    }
}
