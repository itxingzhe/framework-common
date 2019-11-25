package com.jiebai.framework.common.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * MachineUtil
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class MachineUtil {


    private static final Logger LOGGER = Logger.getLogger("MachineUtil");

    private static final int MACHINE_IDENTIFIER = createMachineIdentifier();

    public static int getMachineIdentifier() {
        return MACHINE_IDENTIFIER;
    }

    /**
     * 获取机器 Mac地址
     *
     * @return int
     */
    private static int createMachineIdentifier() {

        int machinePiece;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            if (null != e) {
                while (e.hasMoreElements()) {
                    NetworkInterface ni = e.nextElement();
                    sb.append(ni.toString());
                    byte[] mac = ni.getHardwareAddress();
                    if (null != mac) {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(mac);
                        try {
                            sb.append(byteBuffer.getChar());
                            sb.append(byteBuffer.getChar());
                            sb.append(byteBuffer.getChar());
                        } catch (BufferUnderflowException shortHardwareAddressException) {

                        }
                    }
                }

            }
            machinePiece = sb.toString().hashCode();
        } catch (SocketException e) {
            machinePiece = (new SecureRandom().nextInt());
            LOGGER.info("获取机器号失败");
            e.printStackTrace();
        }
        return machinePiece;
    }

}
