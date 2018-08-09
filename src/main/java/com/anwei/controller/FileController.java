package com.anwei.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.anwei.common.util.UrlUtil;
import com.anwei.entity.file.FileMeta;

/**
 * 文件上传
 * @author Anwei
 * @date 2018年1月9日
 */
@Controller
@RequestMapping("file")
public class FileController {
	
	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
    FileMeta fileMeta = null;
    
    /***************************************************
     * URL: /rest/controller/upload  
     * upload(): receives files
     * @param request : MultipartHttpServletRequest auto passed
     * @param response : HttpServletResponse auto passed
     * @return LinkedList<FileMeta> as json format
     ****************************************************/
    @RequestMapping(value="upload", method = RequestMethod.POST)
    public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
    	long st = System.currentTimeMillis();
        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
             System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
 
             //2.2 if files > 10 remove the first from the list
             if(files.size() >= 10)
                 files.pop();
 
             //2.3 create new fileMeta
             fileMeta = new FileMeta();
             fileMeta.setFileName(mpf.getOriginalFilename());
             fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
             fileMeta.setFileType(mpf.getContentType());
 
             try {
                fileMeta.setBytes(mpf.getBytes());
 
                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)            
                 FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("C:/tmp/files/"+mpf.getOriginalFilename()));
 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             files.add(fileMeta);
         }
         
         System.out.println("上传耗时：" + (System.currentTimeMillis()-st));
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
    }
    
    /***************************************************
     * URL: /rest/controller/get/{value}
     * get(): get file as an attachment
     * @param response : passed by the server
     * @param value : value from the URL
     * @return void
     ****************************************************/
    @RequestMapping(value = "get/{value}", method = RequestMethod.GET)
     public void get(HttpServletResponse response,@PathVariable String value){
         FileMeta getFile = files.get(Integer.parseInt(value));
         try {      
                response.setContentType(getFile.getFileType());
                response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
                FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
         }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
         }
     }
    
    @RequestMapping( value="page", method=RequestMethod.GET )
	public ModelAndView page() {
		ModelAndView mav = new ModelAndView("upload");
		return mav;
	}
    
    
    @RequestMapping( value="page2", method=RequestMethod.GET )
    public ModelAndView page2() {
    	ModelAndView mav = new ModelAndView("/views/upload");
    	return mav;
    }
    
    @RequestMapping( value="download", method=RequestMethod.GET )
    public void download(HttpServletResponse response) {  
        try {
        	
        	String filePath = "/tmp/files/TIM截图20170707112410.png";
        	
            // path是指欲下载的文件的路径。  
            File file = new File(filePath);
            String filename = file.getName(), 
            	ext = filename.substring(filename.lastIndexOf("."));
            System.out.println(ext);
            
            filename = UrlUtil.encoder(filename); // 支持中文文件名
            
            // 以流的形式下载文件。  
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();
            
            // 清空response  
            response.reset();
            // 设置response的Header  
            response.addHeader("Content-Disposition", "attachment;filename=" + filename );  
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream;charset=utf-8"); // .* 不知文件类型
//          response.setContentType("application/vnd.ms-excel;charset=utf-8");  
            
            toClient.write(buffer);
            toClient.flush();  
            toClient.close();  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }
}
