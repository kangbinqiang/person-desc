package fujfu.com.firstdemo.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.DataInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Description:数据处理类
 * Author:kbq
 * Date: 2019-09-12 14:07
 */
public class EncryptionUtils {

    private static final String public_key_path = "keys/platform_public_key.pem";
    private static final String private_key_path = "keys/app_private_key.pem";

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * 字段加密
     * @param bizContent
     * @return
     * @throws Exception
     */
    public static HashMap encryptionParam(HashMap<String,Object> bizContent) throws Exception {
        HashMap<String,Object> documentMap = new HashMap<>();
        //head中的固定参数
        HashMap<String,Object> headMap = new HashMap<>();
        headMap.put("HeadTranCode","交易代码");
        headMap.put("HeadBusinessId","请求流水号");
        headMap.put("HeadReqDate","交易日期");
        headMap.put("HeadReqTime","交易时间");
        headMap.put("HeadSndOrgan","发起机构");
        headMap.put("HeadMsgVersion","报文版本号");
        headMap.put("HeadRevOrgan","接受机构");
        //body中含有各个接口的字段
        HashMap<String,Object> bodyMap = new HashMap<>();
        bodyMap.putAll(bizContent);
        bodyMap.put("Signature",sign(createLinkString(headMap,bodyMap)));//SHA1withRSA数据签名算法
        bodyMap.put("koalB64Cert",bytesToHexStr(getPemPublicKey("RSA").getEncoded()));//公钥

        documentMap.put("head",headMap);
        documentMap.put("body",bodyMap);
        return documentMap;
    }


    /**
     *
     * @param response
     * @return
     * @throws Exception
     */
    public static HashMap deencryParam(HashMap<String,Object> response) throws Exception {
        HashMap<String,Object> documentMap = new HashMap<>();
//        byte[] result =
        return documentMap;
    }

    /**
     * 用RSA私钥对业务参数进行签名
     * @param param
     * @return
     */
    public static String sign(String param) {
        try {
            //获得私钥
            PrivateKey privateKey = getPemPrivateKey("RSA");
            //用私钥给入参加签
            Signature sign = Signature.getInstance(SIGN_ALGORITHMS);
            sign.initSign(privateKey);
            sign.update(param.getBytes());
            byte[] signature = sign.sign();
            //将签名入参转为16进制的字符串
            return bytesToHexStr(signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用RSA公钥验证签名
     * @param param
     * @param signature
     * @return
     */
    public static boolean verifyRes(String param,String signature){
        try {
            PublicKey publicKey = getPemPublicKey("RSA");

            Signature sign = Signature.getInstance(SIGN_ALGORITHMS);
            sign.initVerify(publicKey);
            sign.update(param.getBytes());

            byte[] hexByte = hexStrToBytes(signature);
            return sign.verify(hexByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将字节数组转为16进制的字符串
     * @param bytes
     * @return
     */
    private static String bytesToHexStr(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < bytes.length; ++i) {
            stringBuffer.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1).toUpperCase());
        }
        return stringBuffer.toString();
    }

    /**
     * 将16进制的字符串转为字节数组
     * @param hexStr
     * @return
     */
    private static byte[] hexStrToBytes(String hexStr) {
        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hexStr.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    /**
     * 按照ASCII码升序写成key=value,再以&符号连接生成签名源串
     * @param first
     * @param second
     * @return
     */
    public static String createLinkString(HashMap<String, Object> first,HashMap<String, Object> second) {
        HashMap<String,Object> params = new HashMap<>();
        params.putAll(first);
        params.putAll(second);
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String result = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = params.get(key);
            if (!"document".equals(key) && !"body".equals(key) &&
                    !"head".equals(key) && !"Signature".equals(key) &&
                    !"koalB64Cert".equals(key) && value != null) {
                if (i == keys.size() - 1) {
                    result = result + key + "=" + value;
                } else {
                    result = result + key + "=" + value + "&";
                }
            }

        }
        if (result.endsWith("&")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 获得RSA公钥
     *
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static PublicKey getPemPublicKey(String algorithm) throws Exception {
        InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(public_key_path);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) fis.available()];
        dis.readFully(keyBytes);
        dis.close();

        String temp = new String(keyBytes);

        String publicKeyPEM = temp.replace("-----BEGIN PUBLIC KEY-----\r\n", "");
        publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");


        Base64 b64 = new Base64();
        byte[] decoded = b64.decode(publicKeyPEM);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePublic(spec);
    }

    /**
     * 获得RSA私钥
     *
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static PrivateKey getPemPrivateKey(String algorithm) throws Exception {
        InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(private_key_path);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) fis.available()];
        dis.readFully(keyBytes);
        dis.close();

        String temp = new String(keyBytes);
        String privKeyPEM = temp.replace("-----BEGIN PRIVATE KEY-----\r\n", "");
        privKeyPEM = privKeyPEM.replace("-----END PRIVATE KEY-----", "");

        Base64 b64 = new Base64();
        byte[] decoded = b64.decode(privKeyPEM);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePrivate(spec);
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("kang", "fu");
        hashMap.put("bin", 12);
        hashMap.put("qiang", "li");
        System.out.println("参数：" + encryptionParam(hashMap).toString());
    }
}
