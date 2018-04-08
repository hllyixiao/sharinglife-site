package cn.com.sharinglife.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用的方法
 * Created by 贺淋亮 on 2017/8/24.
 */
public class CommonUtil {

	private static final String DISK_PATH = "E:";

	/**
	 * 获得物理ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//			//这个地方会有5s延迟
//			if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
//				//根据网卡取本机配置的IP
//				InetAddress inet=null;
//				try {
//					inet = InetAddress.getLocalHost();
//				} catch (UnknownHostException e) {
//					e.printStackTrace();
//				}
//				ip= inet.getHostAddress();
//			}
			ip = "127.0.0.1";
		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ip!=null && ip.length()>15){
			if(ip.indexOf(",")>0){
				ip = ip.substring(0,ip.indexOf(","));
			}
		}
		return ip;
	}

    /**
     * 返回文件的原始名称、后缀名、新的全路径（为每一个用户配置一个文件路径）
     * @param file
     * @param fileType  true : 表示图片文件  false : 表示视频文件
     * @param id
     * @return
     */
	public static Map<String,String> getUserFilePath(MultipartFile file, boolean fileType,Integer id){
	    Map<String,String> resMap = new HashMap();
	    StringBuffer userPath = new StringBuffer("//SLFile//");
		// 获取文件原始名称（包括后缀）
		String originalName = file.getOriginalFilename();
		// 获取文件的后缀名（格式）
		String suffixName = originalName.substring(originalName.lastIndexOf("."));
		//添加一级路径
        userPath.append(fileType ? "image//" : "video//");

        //根据用户id设置二级路径
        if(id == null){
            userPath.append("unknow//");
        }else{
            userPath
                    .append(String.valueOf((id / 1000 + 1)))
                    .append("//")
                    .append(id)
                    .append("//");
        }
        String diskPath = DISK_PATH + userPath;
        resMap.put("originalName",originalName);
        resMap.put("suffixName",suffixName);
        resMap.put("diskPath",diskPath);
		resMap.put("userPath",userPath.toString());
        return resMap;
    }

	/**
	 * BASE64加密
	 *
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(final String plainText) {
		byte[] plainByte = plainText.getBytes();
		final Base64 base64 = new Base64();
		plainByte = base64.encode(plainByte);
		return new String(plainByte);
	}

	/**
	 * BASE64解密
	 *
	 * @param encodeStr
	 * @return
	 */
	public static String decryptBASE64(final String encodeStr) {
		byte[] encodeByte = encodeStr.getBytes();
		final Base64 base64 = new Base64();
		encodeByte = base64.decode(encodeByte);
		return new String(encodeByte);
	}

	/**
	 * 生成二维码图片
	 * @param width
	 * @param height
	 * @param content
	 * @param storagePath
	 */
    public static void createQrCode (int width,int height,String content,String storagePath) {
        String format = "png";
        //定义二维码的参数
        HashMap map = new HashMap();
        //设置编码
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //设置纠错等级
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        map.put(EncodeHintType.MARGIN, 2);
        try {
            //生成二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
            Path file = new File(storagePath).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/**
 * 读取二维码
 */
    public static void readQrCode (String filePath) {
        try {
            MultiFormatReader multiFormatReader = new MultiFormatReader();
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            //定义二维码参数
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET,"utf-8");

            //获取读取二维码结果
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = multiFormatReader.decode(binaryBitmap, hints);

            System.out.println("读取二维码： " + result.toString());
            System.out.println("二维码格式： " + result.getBarcodeFormat());
            System.out.println("二维码内容： " + result.getText());
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * 获取文章第一张图片
	 *
	 * @return
	 */
	public static String showFirstImgUrl(String content) {
		if (content.contains("<img")) {
			String img = "";
			String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
			Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
			Matcher m_image = p_image.matcher(content);
			if (m_image.find()) {
				img = img + "," + m_image.group();
				// //匹配src
				Matcher m = Pattern.compile("src\\s*=\\s*\'?\"?(.*?)(\'|\"|>|\\s+)").matcher(img);
				if (m.find()) {
					return m.group(1);
				}
			}
		}
		return "";
	}
}