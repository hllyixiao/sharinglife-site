package cn.com.sharinglife.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 常用的方法
 * Created by 贺淋亮 on 2017/8/24.
 */
@Component
public class Functions {

	//获得物理ip
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
		if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
			if(ip.indexOf(",")>0){
				ip = ip.substring(0,ip.indexOf(","));
			}
		}
		return ip;
	}


	//将字符串转为MD5
	public static String strToMd5(String str) {
		String md5Str = null;
		if (str != null && str.length() != 0) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				byte b[] = md.digest();

				int i;
				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				//32位
				md5Str = buf.toString();
				//16位
				//  md5Str = buf.toString().substring(8, 24);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return md5Str;
	}

	//根据email获取gravatar头像
	public static String getGravatar(String email) {
		String emailMd5 = strToMd5(email);
		//设置图片大小32px
		String avatar = "http://cn.gravatar.com/avatar/"+emailMd5+"?s=128&d=identicon&r=PG";
		return avatar;
	}

}