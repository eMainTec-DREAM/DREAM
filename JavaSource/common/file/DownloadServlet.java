package common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import common.bean.MwareConfig;
import common.file.service.FileUploadService;
import common.struts.BaseAction;
import common.util.FileUtil;

/**
 * File Download Servlet
 * Web.xml에 등록되어 있다.
 * @author  javaworker
 * @version $Id: DownloadServlet.java,v 1.2 2014/01/06 06:20:34 javaworker Exp $
 * @since   1.0
 *
 */
public class DownloadServlet extends HttpServlet 
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(DownloadServlet.class);

	protected void performTask(
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response)
		throws Exception 
    {   
        logger.debug("==============================");
        logger.debug("====== Download Servlet ======");
        logger.debug("==============================");

        String realName = request.getParameter("physicalFileName"); // 현재 업로드 되어있는 파일
        String fileType = request.getParameter("fileType");

        //===========================================================================
        // DB에서 파일이름을 조회한다.
        FileUploadService fileUploadService = (FileUploadService)BaseAction.ctx.getBean("fileUploadService");

        // 파일 정보 조회
        MwareFile mwareFile = fileUploadService.getFileInfo(realName, fileType);

        String fileName = mwareFile.getFileName();
        String fileFolder = mwareFile.getDocType();

        //============================================================================
		try 
        {         
		    
		    // File 저장 경로
            String fileDir = MwareConfig.getFileDir();

            if("AZURE".equals(fileDir))
            {
                  BufferedOutputStream bos = null;
           
                  CloudBlobClient serviceClient = FileUtil.getCloudBlobClient();
                  CloudBlobContainer container = serviceClient.getContainerReference("dream");
    
                  CloudBlockBlob blob = container.getBlockBlobReference(fileFolder + "/" + mwareFile.getPhysicalFileName());
                  
                  if (!blob.exists()) 
                  {
                      response.setContentType("text/html; charset=UTF-8");
                      PrintWriter out = response.getWriter();
                      out.println("<script>alert('File Not Found');</script>");
                      
                      return;
                  }
                  else
                  {
                      try {
                          bos= new BufferedOutputStream(response.getOutputStream());
                          setHeader(response, fileName, (int)blob.getProperties().getLength(), blob.getProperties().getContentType());
                          blob.download(bos);
                          
                          bos.flush();
                          
                          return;
                      }
                      finally 
                      {
                          bos.flush();
                      }
                  }
                
            }
            else
            {
                 /*
                 * Windows
                 */
//			String filePath =
//                MwareConfig.getFileDir() + fileFolder + "\\" + realName;
                /*
                 * Linux 
                 */
                String filePath = "";
                
                if(MwareConfig.osName.indexOf("LINUX") >=0){
                    filePath =
                            MwareConfig.getFileDir() + fileFolder + "/" + mwareFile.getPhysicalFileName();
                }else{
                    filePath =
                            MwareConfig.getFileDir() + fileFolder + "\\" + mwareFile.getPhysicalFileName();
                }
                
                java.io.File tempFile = new java.io.File(filePath);
                
                logger.debug("================================");
                logger.debug("File Path [" + filePath + "]");
                logger.debug("File Name [" + fileName + "]");
                logger.debug("================================");	
                
                //String agentType = request.getHeader("Accept-Encoding");
                
                try 
                {
                    if (!tempFile.exists() || !tempFile.canRead()) 
                    {
                        PrintWriter out = response.getWriter();
                        //out.println("<script>alert('File Not Found');history.back();</script>");
                        out.println("<script>alert('File Not Found');</script>");
                        return;
                    }
                } 
                catch (Exception e) 
                {
                    PrintWriter out = response.getWriter();
                    //out.println("<script>alert('File Not Found');history.back();</script>");
                    out.println("<script>alert('File Not Found');</script>");
                    return;
                }
                setHeader(response, fileName, (int)tempFile.length(), getServletContext().getMimeType(tempFile.toString()));
                transport(new FileInputStream(tempFile), response.getOutputStream());
                
                return;
            }
            
            
		} 
        catch (Exception e) 
        {
            PrintWriter out = null;
            try
            {
                out = response.getWriter();
                out.println(
                "<script>alert('File Not Found');history.back();</script>");
            }
            catch(Exception ex)
            {
                
            }
			return;
		}
	}

    /**
     * Method setHeader.
     * @param response
     * @param file
     */
    private void setHeader(HttpServletResponse response, String fileName, int fileLength, String mime) 
    {
        if (mime == null) 
        {
            mime = "application/octet-stream";
        }
        response.setContentType(mime + ";charset=ISO-8859-1");
      
        response.setHeader("Content-Disposition", 
              "attachment; filename=" + K2E(fileName) + ";");
        //response.setHeader("Content-Length", ""+file.length() );
        
        
        //???
        response.setContentLength(fileLength);
    }
 
    private void transport(InputStream in, OutputStream out)
    throws IOException 
    {
            
        BufferedInputStream bin = null;
        BufferedOutputStream bos = null;
        
        try 
        {
            bin = new BufferedInputStream( in );
            bos = new BufferedOutputStream( out );
        
            byte[] buf = new byte[2048]; //buffer size 2K.
            int read = 0;
//            while ((read = bin.read(buf)) != -1) 
//            {
//                bos.write(buf,0,read);
//            }

            while ((read = bin.read()) != -1) {
                bos.write(read);
            }
            
            bos.flush();
            bin.close();
            
        }
        finally 
        {
            bos.flush();
            bin.close();
        }        
    }

    public static final String KOR_CHARSET = "MS949"; //EUC-KR
   public static final String ENG_CHARSET = "ISO-8859-1";
   private String K2E(String korean) 
   {
        String english = null;
        
        if (korean == null ) {
            return null;
        }
        
        try { 
            english = new String(korean.getBytes(KOR_CHARSET), ENG_CHARSET);
        } catch (UnsupportedEncodingException e) {
            english = new String(korean);
        }

        return english;
    }

    
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException 
    {
		doPost(req, resp);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException 
    {
		try 
        {
			performTask(req, resp);
		} 
        catch (Exception e) 
        {   
			throw new ServletException(e.getMessage());
		}
	}

}