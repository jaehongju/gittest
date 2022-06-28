package com.youhost.webcore;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


public class DownloadView extends AbstractView {

	public void Download(){
		//setContentType("application/download; utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileInputStream fis = null;
		OutputStream out = null;
		try{
			String contentType = (String) model.get("contentType");
			setContentType(contentType);
			
			Object obj = model.get("downloadFile");
			if(obj instanceof File){
				File file = (File) model.get("downloadFile");
				String fileName = "";
				
				response.setContentType(getContentType());
				response.setContentLength((int)file.length());
				
				String userAgent = request.getHeader("User-Agent");
				boolean isIe = userAgent.indexOf("MSIE") != -1;
				
				if(isIe){
					fileName = URLEncoder.encode(file.getName(), "utf-8");
				}else{
					fileName = new String(file.getName().getBytes("utf-8"));
				}
				out = response.getOutputStream();
				response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";");
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setHeader("Cache-Control", "private,no-cache,no-store");
				
				fis = new FileInputStream(file);
				FileCopyUtils.copy(fis, out);
			}else if(obj instanceof Workbook){
				Workbook wb = (Workbook) model.get("downloadFile");
				
				String fileName = (String) model.get("downloadFileName");
				response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";");
				
				out = response.getOutputStream();
				wb.write(out);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try{fis.close();}catch(Exception ignore){}
			}
		}
		if(out!=null) out.flush();
	}
}