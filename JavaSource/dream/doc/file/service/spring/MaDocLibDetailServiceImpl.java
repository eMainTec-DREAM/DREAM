package dream.doc.file.service.spring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.upload.FormFile;

import common.bean.MwareConfig;
import common.bean.User;
import common.file.FileUploadUtil;
import common.file.MwareFile;
import dream.doc.file.dao.MaDocLibDetailDAO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;
import dream.doc.file.service.MaDocLibDetailService;

/**
 * 메뉴 - 상세 serviceimpl 
 * @author  jung7126
 * @version $Id: MaDocLibDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maDocLibDetailServiceTarget"
 * @spring.txbn id="maDocLibDetailService"
 * @spring.property name="maDocLibDetailDAO" ref="maDocLibDetailDAO"
 */
public class MaDocLibDetailServiceImpl implements MaDocLibDetailService
{
    private MaDocLibDetailDAO maDocLibDetailDAO = null;
    
    public MaDocLibDetailDAO getMaDocLibDetailDAO() {
		return maDocLibDetailDAO;
	}

	public void setMaDocLibDetailDAO(MaDocLibDetailDAO maDocLibDetailDAO) {
		this.maDocLibDetailDAO = maDocLibDetailDAO;
	}

	public MaDocLibDetailDTO findDetail(String docId, User user)throws Exception
    {
        return maDocLibDetailDAO.findDetail(docId, user);
    }
    
	public int insertDetail(MaDocLibDetailDTO maDocLibDetailDTO, MaDocLibCommonDTO maDocLibCommonDTO, FormFile[] formFiles) throws Exception
    {        
	    uploadFiles(maDocLibDetailDTO, formFiles);

	    if(maDocLibCommonDTO.getObjectId() != "")  //링크를 줘야 한다.
	    {
	        maDocLibDetailDAO.insertObjDoc(maDocLibCommonDTO, maDocLibDetailDTO);
	    }
	    return maDocLibDetailDAO.insertDetail(maDocLibCommonDTO, maDocLibDetailDTO);
    }
	
	public int updateDetail(MaDocLibDetailDTO maDocLibDetailDTO, FormFile[] formFiles,  String[] deleteRows) throws Exception
    {        
	    uploadFiles(maDocLibDetailDTO, formFiles);

	    if(deleteRows != null)
	    {
	        for(String docdataId : deleteRows)
	        {
	            String nfPath = maDocLibDetailDAO.getFileInfo(docdataId);
	            maDocLibDetailDAO.deleteFileInfo(docdataId);
	            
	            FileUploadUtil.deleteFile(nfPath, docdataId);
	        }
	    }
	    return maDocLibDetailDAO.updateDetail(maDocLibDetailDTO);
    }
	
	public int uploadFiles(MaDocLibDetailDTO maDocLibDetailDTO, FormFile[] fileList) throws FileNotFoundException, IOException
	{
	    int result = 0;
        
        if (fileList == null) return 0;
        //월별로 폴더를 만들어 저장한다.
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        
        //저장 경로 저장
        String nfFilePath ="";
        if(MwareConfig.osName.indexOf("LINUX") >=0){
        	nfFilePath = maDocLibDetailDTO.getCompNo() + "/DOCUMENT/" + ft.format(date);   //D:\mware_data\file\100\201601\IMAGE\xxxxx 
        }else{
        	nfFilePath = maDocLibDetailDTO.getCompNo() + "\\DOCUMENT\\" + ft.format(date);   //D:\mware_data\file\100\201601\IMAGE\xxxxx 
        }
        maDocLibDetailDTO.setNfFilePath(nfFilePath);
        
        for (int i=0; i < fileList.length; i++)
        {
            if (fileList[i] == null || "".equals(fileList[i].getFileName()))
            {
                continue;
            }
            
            //System.out.println("!!!!"+fileList[i].getFileName()+"    "+fileList[i].getFileSize());
           
            //================================================================================
            // 첨부파일을 upload 한다.
//            String docDataId = maDocLibDetailDAO.getNextSequence("SQADOCDATA_NO")+"";   //저장된 파일 이름
//            maDocLibDetailDTO.setDocDataId(docDataId);
//            MwareFile mwareFile = FileUploadUtil.doFileUpload(fileList[i], 
//                    maDocLibDetailDTO.getDocDataId(), nfFilePath); //file, physicalName(저장될 이름), 저장될 폴더  
            //================================================================================

            // 저장된 파일정보를 DB 에 저장한다.
           // result += maDocLibDetailDAO.insertFileInfo(mwareFile, maDocLibDetailDTO);
        }
        
        return result;
	}


    public Map<String,String> uploadAutoFiles(MaDocLibDetailDTO maDocLibDetailDTO, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if("".equals(maDocLibDetailDTO.getDocId()))
        {
            Enumeration params = request.getParameterNames();
            while (params.hasMoreElements()) 
            {
                String name = (String)params.nextElement();
                String []values = request.getParameterValues(name);
                if(name.equals("maDocLibCommonDTO.docId"))
                {
                    if(values != null)
                    {
                        maDocLibDetailDTO.setDocId(values[0]);
                    }
                }
                
            }
        }
        int result = 0;
        
        //월별로 폴더를 만들어 저장한다.
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        
        //저장 경로 저장
        String nfFilePath ="";
        if(MwareConfig.osName.indexOf("LINUX") >=0){
        	nfFilePath = maDocLibDetailDTO.getCompNo() + "/DOCUMENT/" + ft.format(date);
        }else{
        	nfFilePath = maDocLibDetailDTO.getCompNo() + "\\DOCUMENT\\" + ft.format(date);
        }

        maDocLibDetailDTO.setNfFilePath(nfFilePath);
        
       //String uploadDir = "D:/";

        String mode = request.getParameter("mode");
        String action = "";

        ServletFileUpload uploader = null;
        List<FileItem> items = null;
        
        if (mode == null || (mode != null && !mode.equals("conf") && !mode.equals("sl"))) {
            uploader = new ServletFileUpload(new DiskFileItemFactory());
            items = (List<FileItem>) request.getAttribute("items");
           // items = uploader.parseRequest(request);
        }
  
        Map<String,String> rstMap = FileUploadUtil.checkFile(items, "DOCUMENT");
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
            
            int maxPostSize = Integer.parseInt(MwareConfig.getMaxFileSize())*1000000;
            //MwareConfig.get
            response.setHeader("Content-type", "text/json");
            response.getWriter().write("{ \"maxFileSize\":" + maxPostSize + " }");
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
                        String docDataId = maDocLibDetailDAO.getNextSequence("SQADOCDATA_NO")+"";   //저장된 파일 이름
                        maDocLibDetailDTO.setDocDataId(docDataId);
                        MwareFile mwareFile = FileUploadUtil.doFileUpload(item, 
                                maDocLibDetailDTO.getDocDataId(), nfFilePath, maDocLibDetailDTO.getCompNo()); //file, physicalName(저장될 이름), 저장될 폴더  
                        //================================================================================
                        String match = "[!@#$%&~+`;'\\s]";
                        mwareFile.setFileName(mwareFile.getFileName().replaceAll(match, ""));
                        // 저장된 파일정보를 DB 에 저장한다.
                        result += maDocLibDetailDAO.insertFileInfo(mwareFile, maDocLibDetailDTO);
                    }
                    /*
                    if (!item.isFormField()) {
                        // Process form file field (input type="file").
                        String fieldname = item.getFieldName();
                        filename = FilenameUtils.getName(item.getName());
                        InputStream filecontent = item.getInputStream();

                        // Write to file
                         File f=new File(uploadDir + filename);
                        FileOutputStream fout=new FileOutputStream(f);
                        byte buf[]=new byte[1024];
                        int len;
                        while((len=filecontent.read(buf))>0) {
                            fout.write(buf,0,len);
                            filesize+=len;
                        }
                        fout.close(); 
                    }*/
                }
                // Manual filesize value only for demo!
                //filesize = 28428;

                response.setHeader("Content-type", "text/html");
                response.getWriter().write("{\"state\":true,\"name\":\"" + filename.replace("\"","\\\"") + "\",\"size\":" + filesize + ",\"extra\":{\"info\":\"just a way to send some extra data\",\"param\":\"some value here\"}}");
            }
        }
        
        /*for (int i=0; i < fileList.length; i++)
        {
            if (fileList[i] == null || "".equals(fileList[i].getFileName()))
            {
                continue;
            }
           
            //================================================================================
            // 첨부파일을 upload 한다.
            String docDataId = maDocLibDetailDAO.getNextSequence("SQADOCDATA_NO")+"";   //저장된 파일 이름
            maDocLibDetailDTO.setDocDataId(docDataId);
            MwareFile mwareFile = FileUploadUtil.doFileUpload(fileList[i], 
                    maDocLibDetailDTO.getDocDataId(), nfFilePath); //file, physicalName(저장될 이름), 저장될 폴더  
            //================================================================================

            // 저장된 파일정보를 DB 에 저장한다.
            result += maDocLibDetailDAO.insertFileInfo(mwareFile, maDocLibDetailDTO);
        }*/
        
//        return result;
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
    public void deleteAutoFiles(MaDocLibDetailDTO maDocLibDetailDTO, String[] deleteRows)
    {
        if(deleteRows != null)
        {
            for(String docdataId : deleteRows)
            {
                String nfPath = maDocLibDetailDAO.getFileInfo(docdataId);
                maDocLibDetailDAO.deleteFileInfo(docdataId);
                
                FileUploadUtil.deleteFile(nfPath, docdataId);
            }
            
        }
    }
    
}
