package com.drug.platform.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Yaochao on 2015/7/14.
 */
public class BytesHelper {

    /**
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     *
     * @param hexStr
     * @return
     */
    public static byte[] hexToBytes(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static byte[] readFromInputStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while ((n = inputStream.read(buf)) > 0) {
            out.write(buf, 0, n);
        }
        out.flush();
        out.close();
        return out.toByteArray();
    }
}
