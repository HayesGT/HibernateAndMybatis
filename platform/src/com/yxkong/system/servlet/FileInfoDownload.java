package com.yxkong.system.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxkong.common.utils.PropertiesUtils;
import com.yxkong.common.utils.ServletUtils;

public class FileInfoDownload extends HttpServlet {
	private static final long serialVersionUID = 4842605803382482794L;

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filename=request.getParameter("filename");
        String name=request.getParameter("name");
//      path=new String(path.getBytes("ISO-8859-1"),"utf-8");
        if(checkFileExists(name)){
        	download(name,filename,request,response);
        }else{
			String msg=request.getParameter("filename")+" 下载文件已不存在！";
			msg+="<a onclick=\"javascript:history.go(-1);\" href=\"javascript:void(0);\" style=\"text-decoration: none;\">返回</a>";
			response.setHeader("Content-type","text/html;charset=UTF-8");
		    OutputStream stream = response.getOutputStream();
		    stream.write(msg.getBytes("UTF-8"));
        }
    }
	public boolean checkFileExists(String path){
        String downloadPath = PropertiesUtils.parseStr("uploadPath");
        File file = new File(downloadPath+path);
        if(file.exists()){
        	return true;
        }else{
        	return false;
        }
	}
	
    public HttpServletResponse download(String path,String realFileName,HttpServletRequest request, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
//            String downloadPath = this.getServletConfig().getServletContext().getRealPath("");
            String downloadPath = PropertiesUtils.parseStr("uploadPath");
            File file = new File(downloadPath+path);
            // 取得文件的后缀名。
            //String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + new String(realFileName.getBytes("utf-8"),"ISO-8859-1"));
            ServletUtils.setFileDownloadHeader(response,request, realFileName);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}