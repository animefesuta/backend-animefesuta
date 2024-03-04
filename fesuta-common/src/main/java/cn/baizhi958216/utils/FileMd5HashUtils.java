package cn.baizhi958216.utils;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileMd5HashUtils {
    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    /**
     * 计算文件的MD5
     * 
     * @param fileName 文件的绝对路径
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String getFileMD5(InputStream filInputStream) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buf = new byte[8192];
            int len;
            while ((len = filInputStream.read(buf)) > 0) {
                digest.update(buf, 0, len);
            }
            return toHexString(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String toHexString(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    /**
     * 计算文件的Hash256值
     * 
     * @param fileName 文件的绝对路径
     * @return
     */

    public String getFileHash256(InputStream fileInputStream) {
        try {
            byte[] data = fileInputStream.readAllBytes(); // 读取输入流到字节数组
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(data); // 计算哈希值
            return byte2hexLower(digest); // 转换为十六进制字符串
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String byte2hexLower(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0XFF);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString();
    }
}
