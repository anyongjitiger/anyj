package com.emep.zaixian.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@WebServlet("/upload")
@Controller
@RequestMapping("/upload")
@MultipartConfig
public class UploadImgServlet extends HttpServlet {

	private static final long serialVersionUID = -2712624630388027149L;

	@Autowired
	ServletContext context;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Do nothing...
	}

	@RequestMapping("/")
	@RequiresPermissions("user:create")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("textml;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Part part = request.getPart("myFileName");
		String[] allowedType = { "image/bmp", "image/gif", "image/jpeg",
				"image/png" };
		boolean allowed = Arrays.asList(allowedType).contains(
				part.getContentType());
		if (!allowed) {
			response.getWriter().write("error|wrong image type");
			return;
		}
		if (part.getSize() > 5 * 1024 * 1024) {
			response.getWriter().write("error|image bigger than 5M");
			return;
		}
		String fi = part.getHeader("content-disposition");
		String fileNameExtension = fi.substring(fi.indexOf("."),
				fi.length() - 1);
		String realName = UUID.randomUUID().toString() + fileNameExtension;
		//读取配置文件
		String filename = "/imgPath.properties";
		Properties props = new Properties();
		props.load(UploadImgServlet.class.getClassLoader().getResourceAsStream(
				filename));
		String path = props.getProperty("imgRealPath") + File.separator + realName;
		part.write(path);
		response.getWriter().write(props.getProperty("imgPath") + File.separator + realName);
		/*
		 * String realPath = context.getRealPath("") + File.separator +
		 * UPLOAD_DIRECTORY+ File.separator + realName; part.write(realPath);
		 * response.getWriter().write(request.getContextPath()+ File.separator +
		 * UPLOAD_DIRECTORY+ File.separator +realName);
		 */
	}

}