package fujfu.com.firstdemo.util;

import java.io.File;
import java.util.HashMap;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;



public class EncodeTest {
    public static void main(String[] args) throws Exception {
        int width = 300;
        int height = 300;
// int width = 105;
// int height = 50;
// 条形码的输入是13位的数字
// String text = "6923450657713";
// 二维码的输入是字符串
        String text = "testtesttest生成条形码图片";
        String format = "png";
        HashMap<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
// 条形码的格式是 BarcodeFormat.EAN_13
// 二维码的格式是BarcodeFormat.QR_CODE
        BitMatrix bm = new MultiFormatWriter().encode(text,
                BarcodeFormat.QR_CODE, width, height, hints);
        File out = new File("new.png");
// 生成条形码图片
// File out = new File("ean3.png");
        WriteBitMatricToFile.writeBitMatricToFile(bm, format, out);
    }

}