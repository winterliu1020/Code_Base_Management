package cn.edu.nchu.software.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @ClassName FileUtil
 * @Description TODO
 * @Auther 范孝发
 * @Date 2019/1/10 11:02
 * @Version 1.0
 */
public class FileUtil {
	/**
	 * 方法实现说明
	 * 
	 * @Author 范孝发
	 * @Description 处理文件上传，返回文件路径
	 * @Date 15:39 2019/1/14
	 * @Param [pathName, replyAppendix, request]
	 * @exception @return java.lang.String
	 **/
	public static String upload(String path, MultipartFile file, HttpServletRequest request) {

		// 文件夹存不存在就新建一个
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 判断附件是否为空，为空就附件字段为null
		if (file != null && !file.isEmpty()) {
			String fileName = file.getOriginalFilename();
	        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
	        String newFileName = new Date().getTime() + "." + suffix;
			// 5,构建文件对象
			File file1 = new File(path,newFileName);
			// 6,执行上传操作
			try {
				file.transferTo(file1);
				return file1.getName(); 
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * 方法实现说明
	 * 
	 * @Author 范孝发
	 * @Description 处理文件下载
	 * @Date 15:40 2019/1/14
	 * @Param [pathName, fileName, request, response]
	 * @exception @return int
	 **/
	public static int download(String filePath,String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 得到要下载的文件
			File file = new File(filePath);
			// 如果文件不存在
			if (!file.exists()) {
				return 0;
			}
			// 设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
			response.setCharacterEncoding("UTF-8");
			// 读取要下载的文件，保存到文件输入流
			FileInputStream in = new FileInputStream(file);
			// 创建输出流
			OutputStream out = response.getOutputStream();
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
			// 关闭文件输入流
			in.close();
			// 关闭输出流
			out.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String uploadCode(String code,String suffix,String path) {

		// 文件夹存不存在就新建一个
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 判断字符串是否为空
		if (code != null && !code.equals("")) {
	        String newFileName = new Date().getTime() + "." + suffix;
			File file1 = new File(path,newFileName);
			try {
				FileOutputStream fos = new FileOutputStream(file1);
				fos.write(code.getBytes());
				return file1.getName(); 
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
