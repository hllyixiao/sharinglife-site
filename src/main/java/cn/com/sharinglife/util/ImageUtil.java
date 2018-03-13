package cn.com.sharinglife.util;

import sun.misc.BASE64Encoder;

import java.util.Objects;

public class ImageUtil {

    private static final String BASE64_IMAGE_HEADER = "data:image/png;base64,";

    private static final BASE64Encoder encoder = new BASE64Encoder();

    public static String verifyCodeImageToBase64(byte[] bytes){
        String base64Image = imageToBase64(bytes);
        return BASE64_IMAGE_HEADER + base64Image;
    }

    public static String imageToBase64(byte[] bytes){
        if(Objects.nonNull(bytes)){
            return encoder.encodeBuffer(bytes).trim();
        }
        return "";
    }
}
