package com.yxkong.system.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yxkong.common.utils.DateUtil;
import com.yxkong.common.utils.GsonUtil;
import com.yxkong.common.utils.PropertiesUtils;
import com.yxkong.system.model.FileInfo;
import com.yxkong.system.service.IFileInfoService;
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class FileInfoUpload extends HttpServlet {
	private static final long serialVersionUID = 7104635179812319259L;

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String savePath = PropertiesUtils.parseStr("uploadPath");
        
        String prePath= "/uploads/";
        
        File f1 = new File(savePath);
        if (!f1.exists()) {
            f1.mkdirs();
        }
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		
		IFileInfoService fileInfoService  = (IFileInfoService) context.getBean("fileInfoServiceImpl");
		
        List fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            return;
        }
        Iterator<FileItem> it = fileList.iterator();
        String name = "";
        String extName = "";
        
        String  dataId="";
        String  filename="";
        String  autoname="";
        String  url="";
      
        while (it.hasNext()) {
         FileItem item = it.next();
    	 if (item.isFormField()) {
    		 String filedName = item.getFieldName();  
    		 if (filedName.equals("dataId")) {  
    			 dataId = item.getString("UTF-8");  
    		 }
    	 }
        }       
        it = fileList.iterator();
        Map<String,String> m=null;
        while (it.hasNext()) {
            FileItem item = it.next();
        	FileInfo  fileInfo=new  FileInfo();
            if (!item.isFormField()) {
                name = item.getName();
                filename=name;
                long size = item.getSize();
                String type = item.getContentType();
                if (name == null || name.trim().equals("")) {
                    continue;
                }
                //扩展名格式：  
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }
                File file = null;
                do {
                    name = UUID.randomUUID().toString();
                    url=prePath + name + extName;
                    autoname=name + extName;
                    file = new File(savePath + name + extName);
                } while (file.exists());
                File saveFile = new File(savePath + name + extName);
                try {
                    item.write(saveFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
             	fileInfo.setFileName(filename);
            	fileInfo.setName(autoname);
            	fileInfo.setUrl(url);//url  需要修订
            	fileInfo.setDataId(dataId);
        		try {
        			fileInfoService.save(fileInfo);
        			m=new HashMap<String, String>();
        			m.put("id", fileInfo.getId());
        			m.put("name", fileInfo.getName());
        			m.put("created", DateUtil.getCurrentDate());
        			m.put("url", fileInfo.getUrl());
        			m.put("fileName", fileInfo.getFileName());
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
            }
        }
        response.getWriter().print(GsonUtil.toJson(m));
    }
}