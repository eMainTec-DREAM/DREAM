package dream.doc.img.service.spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts.upload.FormFile;

import common.bean.MwareConfig;
import common.bean.User;
import common.file.FileUploadUtil;
import common.file.MwareFile;
import common.util.DateUtil;
import dream.doc.img.dao.MaDocImgDetailDAO;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.doc.img.dto.MaDocImgDetailDTO;
import dream.doc.img.service.MaDocImgDetailService;

/**
 * 메뉴 - 상세 serviceimpl 
 * @author  jung7126
 * @version $Id: MaDocImgDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maDocImgDetailServiceTarget"
 * @spring.txbn id="maDocImgDetailService"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 */
public class MaDocImgDetailServiceImpl implements MaDocImgDetailService
{
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
    public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}

	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}

	public MaDocImgDetailDTO findDetail(MaDocImgCommonDTO maDocImgCommonDTO, User user)throws Exception
    {	    
        return maDocImgDetailDAO.findDetail(maDocImgCommonDTO.getImageId(), user);
    }
    
	public int insertDetail(MaDocImgDetailDTO maDocImgDetailDTO, MaDocImgCommonDTO maDocImgCommonDTO, FormFile[] formFiles) throws Exception
    {        
	    uploadFiles(maDocImgDetailDTO, formFiles);

//	    if(maDocImgCommonDTO.getObjectId() != "")  //링크를 줘야 한다.
//	    {
//	        maDocImgDetailDAO.insertObjDoc(maDocImgCommonDTO, maDocImgDetailDTO);
//	    }
        return maDocImgDetailDAO.insertDetail(maDocImgCommonDTO, maDocImgDetailDTO);
    }
	
	public int updateDetail(MaDocImgDetailDTO maDocImgDetailDTO, FormFile[] formFiles,  String[] deleteRows) throws Exception
    {        
	    uploadFiles(maDocImgDetailDTO, formFiles);

	    if(deleteRows != null)
	    {
	        for(String docdataId : deleteRows)
	        {
	            String nfPath = maDocImgDetailDAO.getFileInfo(docdataId);
	            maDocImgDetailDAO.deleteFileInfo(docdataId);
	            
	            FileUploadUtil.deleteFile(nfPath, docdataId);
	        }
	        
	    }
	    
        return maDocImgDetailDAO.updateDetail(maDocImgDetailDTO);
    }
	
	public int uploadFiles(MaDocImgDetailDTO maDocImgDetailDTO, FormFile[] fileList) throws FileNotFoundException, IOException
	{
	    int result = 0;
        
        if (fileList == null) return 0;
        //월별로 폴더를 만들어 저장한다.
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        
        //저장 경로 저장
        String nfFilePath ="";
        if(MwareConfig.osName.indexOf("LINUX") >=0){
        	nfFilePath = maDocImgDetailDTO.getCompNo() + "/IMAGE/" + ft.format(date);
        }else{
        	nfFilePath = maDocImgDetailDTO.getCompNo() + "\\IMAGE\\" + ft.format(date);
        }
        maDocImgDetailDTO.setNfFilePath(nfFilePath);
        
        for (int i=0; i < fileList.length; i++)
        {
            if (fileList[i] == null || "".equals(fileList[i].getFileName()))
            {
                continue;
            }
           
            //================================================================================
            // 첨부파일을 upload 한다.
            String imgDataId = maDocImgDetailDAO.getNextSequence("SQAIMGDATA_ID")+"";   //저장된 파일 이름
            maDocImgDetailDTO.setImgDataId(imgDataId);
            MwareFile mwareFile = FileUploadUtil.doFileUpload(fileList[i], 
                    maDocImgDetailDTO.getImgDataId(), nfFilePath); //file, physicalName(저장될 이름), 저장될 폴더  
            //================================================================================

            // 저장된 파일정보를 DB 에 저장한다.
            result += maDocImgDetailDAO.insertFileInfo(mwareFile, maDocImgDetailDTO);
        }
        
        return result;
	}

    @Override
    public String findObjectTypeDesc(String objectType, User user)
    {
        // TODO Auto-generated method stub
        return maDocImgDetailDAO.findOjbectTypeDesc(objectType, user);
    }

    public void deleteAutoFiles(MaDocImgDetailDTO maDocImgDetailDTO, String[] deleteRows)
    {
        if(deleteRows != null)
        {
            for(String docdataId : deleteRows)
            {
                String nfPath = maDocImgDetailDAO.getFileInfo(docdataId);
                maDocImgDetailDAO.deleteFileInfo(docdataId);
                
                FileUploadUtil.deleteFile(nfPath, docdataId);
            }
            
        }
    }

    public Map<String,String> uploadAutoFiles(MaDocImgDetailDTO maDocImgDetailDTO, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
        int result = 0;
        
        //월별로 폴더를 만들어 저장한다.
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");

        //저장 경로 저장
        String nfFilePath = "";
        if(MwareConfig.osName.indexOf("LINUX") >=0){
        	nfFilePath = maDocImgDetailDTO.getCompNo() + "/IMAGE/" + ft.format(date);
        }else{
        	nfFilePath = maDocImgDetailDTO.getCompNo() + "\\IMAGE\\" + ft.format(date);
        }
        maDocImgDetailDTO.setNfFilePath(nfFilePath);

        String mode = request.getParameter("mode");
        String action = "";

        ServletFileUpload uploader = null;
        List<FileItem> items = null;
        
        if (mode == null || (mode != null && !mode.equals("conf") && !mode.equals("sl"))) {
            uploader = new ServletFileUpload(new DiskFileItemFactory());
            items = (List<FileItem>) request.getAttribute("items");
           // items = uploader.parseRequest(request);
        }

        Map<String,String> rstMap = FileUploadUtil.checkFile(items, "IMAGE");
        if(rstMap.containsValue("ERROR"))
        {
        	return rstMap;
        }

        if (mode == null) {
            mode = "";

            for (FileItem item : items) {
                if (item != null) {
                    if (item.getFieldName().equals("mode")) {
                        mode = getStringFromStream(item.getInputStream());
                    }
                    if (item.getFieldName().equals("action")) {
                        action = getStringFromStream(item.getInputStream());
                    }
                }
            }
        }

        if (mode.equals("conf")) {
//            int maxPostSize = 2000000;
            
            response.setHeader("Content-type", "text/json");
//            response.getWriter().write("{ \"maxFileSize\":" + maxPostSize + " }");
        }

        if (mode.equals("html4") || mode.equals("flash") || mode.equals("html5")) {
            if (action.equals("cancel")) {
                response.setHeader("Content-type", "text/json");
                response.getWriter().write("{\"state\":\"cancelled\"}");
            } else {
                String filename = "";
                Integer filesize = 0;
                
                for (FileItem item : items) 
                {
                    if (!item.isFormField()) 
                    {
                        String imgDataId = maDocImgDetailDAO.getNextSequence("SQAIMGDATA_ID")+"";   //저장된 파일 이름
                        maDocImgDetailDTO.setImgDataId(imgDataId);
                        MwareFile mwareFile = FileUploadUtil.doFileUpload(item, 
                                maDocImgDetailDTO.getImgDataId(), nfFilePath, maDocImgDetailDTO.getCompNo()); //file, physicalName(저장될 이름), 저장될 폴더  
                        //================================================================================
                        String match = "[!@#$%&~+`;'\\s]";
                        mwareFile.setFileName(mwareFile.getFileName().replaceAll(match, ""));
                        // 저장된 파일정보를 DB 에 저장한다.
                        result += maDocImgDetailDAO.insertFileInfo(mwareFile, maDocImgDetailDTO);
                    }
                }
                
                response.setHeader("Content-type", "text/html");
                response.getWriter().write("{\"state\":true,\"name\":\"" + filename.replace("\"","\\\"") + "\",\"size\":" + filesize + ",\"extra\":{\"info\":\"just a way to send some extra data\",\"param\":\"some value here\"}}");
            }
        }
        
        return rstMap;

    }
    
    public String getStringFromStream(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
    
    @Override
    public Map<String,String> uploadAutoFiles(MaDocImgDetailDTO maDocImgDetailDTO, String url) throws Exception
    {
        int result = 0;
        Map<String,String> rstMap = new HashMap<String, String>();
        rstMap.put("state","OK");
        rstMap.put("info","");
        String sysImgFileExt = MwareConfig.getFileType()+MwareConfig.getImgFileType();
        
        //월별로 폴더를 만들어 저장한다.
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");

        //저장 경로 저장
        String nfFilePath = "";
        if(MwareConfig.osName.indexOf("LINUX") >=0){
            nfFilePath = maDocImgDetailDTO.getCompNo() + "/IMAGE/" + ft.format(date);
        }else{
            nfFilePath = maDocImgDetailDTO.getCompNo() + "\\IMAGE\\" + ft.format(date);
        }
        maDocImgDetailDTO.setNfFilePath(nfFilePath);
        
        try
        {
            String fileName = url.substring(url.lastIndexOf("/")+1, url.length());
            if((sysImgFileExt.toUpperCase()).indexOf(FilenameUtils.getExtension(fileName).toUpperCase()) == -1)
            {
                rstMap.put("state", "ERROR");
                rstMap.put("info", FilenameUtils.getExtension(fileName)+", File Extension is not allowed.");
            }
            String imgDataId = maDocImgDetailDAO.getNextSequence("SQAIMGDATA_ID")+"";   //저장된 파일 이름
            maDocImgDetailDTO.setImgDataId(imgDataId);
            MwareFile mwareFile = FileUploadUtil.doUrlFileUpload(new URL(url), fileName, maDocImgDetailDTO.getImgDataId(), nfFilePath);
            String match = "[!@#$%&~+`;'\\s]";
            mwareFile.setFileName(mwareFile.getFileName().replaceAll(match, ""));
            // 저장된 파일정보를 DB 에 저장한다.
            result += maDocImgDetailDAO.insertFileInfo(mwareFile, maDocImgDetailDTO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            rstMap.put("state", "ERROR");
            rstMap.put("info", e.getMessage());
        }
        
        return rstMap;
    }
}
