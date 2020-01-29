package intf.dream.bee.woreq.service.spring;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import common.bean.MwareConfig;
import common.bean.User;
import common.file.FileUploadUtil;
import common.file.MwareFile;
import common.util.CommonUtil;
import common.util.MailUtil;
import dream.doc.file.dao.MaDocLibDetailDAO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;
import dream.req.work.dto.ReqWorkDetailDTO;
import intf.dream.bee.woreq.dao.BeeWoReqListDAO;
import intf.dream.bee.woreq.dto.BeeWoReqCommonDTO;
import intf.dream.bee.woreq.service.BeeWoReqListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeWoReqListServiceTarget"
 * @spring.txbn id="beeWoReqListService"
 * @spring.property name="beeWoReqListDAO" ref="beeWoReqListDAO"
 * @spring.property name="maDocLibDetailDAO" ref="maDocLibDetailDAO"
 */
public class BeeWoReqListServiceImpl implements BeeWoReqListService
{
    private BeeWoReqListDAO beeWoReqListDAO = null;
    private MaDocLibDetailDAO maDocLibDetailDAO = null;

    public MaDocLibDetailDAO getMaDocLibDetailDAO() {
		return maDocLibDetailDAO;
	}

	public void setMaDocLibDetailDAO(MaDocLibDetailDAO maDocLibDetailDAO) {
		this.maDocLibDetailDAO = maDocLibDetailDAO;
	}
	public BeeWoReqListDAO getBeeWoReqListDAO() {
		return beeWoReqListDAO;
	}
	public void setBeeWoReqListDAO(BeeWoReqListDAO beeWoReqListDAO) {
		this.beeWoReqListDAO = beeWoReqListDAO;
	}
	
	public List findWoReqList(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception
	{      
		return beeWoReqListDAO.findWoReqList(beeWoReqCommonDTO, map);
	}
	
	public List findWoReqCount(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception
	{      
		return beeWoReqListDAO.findWoReqCount(beeWoReqCommonDTO, map);
	}
	
	public int deleteWoReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeWoReqListDAO.deleteWoReq(map);
		}
		return resultQty;
	}
	public int insertWoReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeWoReqListDAO.insertWoReq(map);
		}
		return resultQty;
	}
	public int updateWoReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			
			if("Y".equals(CommonUtil.convertString(map.get("isReq")))){
			    map.put("woReqStatus", "REQ");
				beeWoReqListDAO.updateWoReqStatus(map);
				
				ReqWorkDetailDTO reqWorkDetailDTO = new ReqWorkDetailDTO();
				reqWorkDetailDTO.setWoReqId(CommonUtil.convertString(map.get("woReqId")));
				
				User user = new User();
				user.setCompNo(CommonUtil.convertString(map.get("compNo")));
				user.setLangId("".equals(CommonUtil.convertString(map.get("lang")))?"ko":CommonUtil.convertString(map.get("lang")));
				Locale locale = new Locale(user.getLangId());
				user.setLocale(locale);
				
				MailUtil.sendMail("REQ10", reqWorkDetailDTO.getWoReqId(), user);
			}else{
				beeWoReqListDAO.updateWoReq(map);
			}
		}
		return resultQty;
	}

	
	public List findWoResList(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception
	{      
		return beeWoReqListDAO.findWoResList(beeWoReqCommonDTO, map);
	}
	public List findWoResCount(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception
	{      
		return beeWoReqListDAO.findWoResCount(beeWoReqCommonDTO, map);
	}
	public int updateWoRes(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			
			beeWoReqListDAO.updateWoRes(map);
		}
		return resultQty;
	}
	public int deleteWoRes(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeWoReqListDAO.deleteWoRes(map);
		}
		return resultQty;
	}

	public List findWoReqResList(Map map) throws Exception
	{      
		return beeWoReqListDAO.findWoReqResList(map);
	}
	public int insertWoReqRes(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			map.put("woReqStatus", "WRK");
			beeWoReqListDAO.insertWoReqRes(map);
			beeWoReqListDAO.updateWoReqStatus(map);
		}
		return resultQty;
	}
	public int updateWoReqRes(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeWoReqListDAO.updateWoReqRes(map);
		}
		return resultQty;
	}
	public int deleteWoReqRes(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeWoReqListDAO.deleteWoReqRes(map);
		}
		return resultQty;
	}
	

	public List findPhotoList(Map map) throws Exception
	{      
		List list = beeWoReqListDAO.findPhotoList(map);
		for(int i=0; i<list.size(); i++){
			Map listMap = (Map)list.get(i);
			
			String fileNo = String.valueOf(listMap.get("ID"));	//docdata_id
			String fileName = String.valueOf(listMap.get("FILE_NAME"));	//file_name
			String filePath = String.valueOf(listMap.get("FILE_PATH"));	//file_path
			
			String originFile = "";
			String targetFile = "";
			
			if(MwareConfig.osName.indexOf("LINUX") >=0){
				originFile = MwareConfig.getFileDir() + filePath + "/" + fileNo;
				targetFile = MwareConfig.getWebAppPath() + "dream/android/" + fileNo;
			}else{
				originFile = MwareConfig.getFileDir() + filePath + "\\" + fileNo;
				targetFile = MwareConfig.getWebAppPath() + "dream\\android\\" + fileNo;
			}
			
			try
	           {
					File f = new File(targetFile);
					if (!f.isFile()) {
						if(MwareConfig.osName.indexOf("LINUX") >=0){
							FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"dream/android/");
						}else{
							FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"dream\\android\\");
						}
					}
	           }catch (IOException e){}
			
		}
		
		return list;
	}
	
	public int deleteTempPhoto(List list) throws Exception
	{      
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			List tempList = beeWoReqListDAO.findPhotoList(map);
			
			String fileNo = String.valueOf(map.get("ID"));	//docdata_id
			String fileName = String.valueOf(map.get("FILE_NAME"));	//file_name
			String filePath = String.valueOf(map.get("FILE_PATH"));	//file_path
			
			String originFile = "";
			String targetFile = "";
			
			File dirFile = null;
			
			if(MwareConfig.osName.indexOf("LINUX") >=0){
				originFile = MwareConfig.getFileDir() + filePath + "/" + fileNo;
				targetFile = MwareConfig.getWebAppPath() + "dream/android/" + fileNo;
				
				dirFile = new File(MwareConfig.getWebAppPath() + "dream/android/");
			}else{
				originFile = MwareConfig.getFileDir() + filePath + "\\" + fileNo;
				targetFile = MwareConfig.getWebAppPath() + "dream\\android\\" + fileNo;
				
				dirFile = new File(MwareConfig.getWebAppPath() + "dream\\android\\");
			}
			
	        File [] files = dirFile.listFiles();
	        
	        if(files == null) return 0;
	        
	        for (int i=0; i<files.length; i++)
	        {
	        	try
	            {
		        	files[i].delete();
	            }
	            catch(Exception ex)
	            {
	                ex.printStackTrace();
	            }
	        }
	        
		}
		return resultQty;
	}
	
	public int deleteTempPhotoOne(Map map) throws Exception
	{      
		int resultQty = 0;
		List tempList = beeWoReqListDAO.findPhotoList(map);
		
		String fileNo = String.valueOf(map.get("ID"));	//docdata_id
		String fileName = String.valueOf(map.get("FILE_NAME"));	//file_name
		String filePath = String.valueOf(map.get("FILE_PATH"));	//file_path
		
		String originFile = "";
		String targetFile = "";
		
		File dirFile = null;
		if(MwareConfig.osName.indexOf("LINUX") >=0){
			originFile = MwareConfig.getFileDir() + filePath + "/" + fileNo;
			targetFile = MwareConfig.getWebAppPath() + "dream/android/" + fileNo;
			dirFile = new File(MwareConfig.getWebAppPath() + "dream/android/");
		}else{
			originFile = MwareConfig.getFileDir() + filePath + "\\" + fileNo;
			targetFile = MwareConfig.getWebAppPath() + "dream\\android\\" + fileNo;
			dirFile = new File(MwareConfig.getWebAppPath() + "dream\\android\\");
		}
		
        File [] files = dirFile.listFiles();
        
        if(files == null) return 0;
        
        for (int i=0; i<files.length; i++)
        {
        	try
            {
	        	files[i].delete();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
		return resultQty;
	}
	
	public int insertPhoto(List list, FormFile[] fileList)  throws Exception
	{
		int result = 0;
		if (fileList == null||fileList[1]==null) return 0;
		MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
        MaDocLibDetailDTO maDocLibDetailDTO = new MaDocLibDetailDTO();
		for(Object obj : list){
			Map map = (Map)obj;
			String docId = beeWoReqListDAO.findDoc(map);
			
			Date date = new Date();
	        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
	        maDocLibDetailDTO.setCompNo(String.valueOf(map.get("compNo")));

			if("0".equals(docId)&&!"".equals(docId)){
				//새 헤더 생성
				//월별로 폴더를 만들어 저장한다.
		        String newDocId = String.valueOf(map.get("docId"));
		        
		        maDocLibDetailDTO.setDocId(newDocId);
		        maDocLibDetailDTO.setRegId(String.valueOf(map.get("regId")));
		        maDocLibDetailDTO.setDeptId(String.valueOf(map.get("deptId")));
		        maDocLibDetailDTO.setSecurGrade("3");
		        maDocLibDetailDTO.setDocCateg("DOC");
		        maDocLibCommonDTO.setObjectType(String.valueOf(map.get("objectType")));
		        maDocLibDetailDTO.setDescription(String.valueOf(map.get("woDesc")));
		        
		        maDocLibDetailDTO.setDocNo(newDocId);
		        maDocLibCommonDTO.setObjectId(String.valueOf(map.get("objectId")));

		      //Header 생성
		        maDocLibDetailDAO.insertDetail(maDocLibCommonDTO, maDocLibDetailDTO);
		        
		        maDocLibDetailDAO.insertObjDoc(maDocLibCommonDTO, maDocLibDetailDTO);
		      
			}else if(!"0".equals(docId)&&!"".equals(docId)){
				maDocLibDetailDTO.setDocId(docId);
			}
			
			//저장 경로 저장
	        String nfFilePath =  "";
	        if(MwareConfig.osName.indexOf("LINUX") >=0){
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + "/DOCUMENT/" + ft.format(date);
	        }else{
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + "\\DOCUMENT\\" + ft.format(date);
	        }
	        maDocLibDetailDTO.setNfFilePath(nfFilePath);
	        
	        for (int i=0; i < fileList.length; i++)
	        {
	            if (fileList[i] == null || "".equals(fileList[i].getFileName()))
	            {
	                continue;
	            }

	            //================================================================================
	            // 첨부파일을 upload 한다.
	            String docDataId = String.valueOf(map.get("docDataId"));   //저장된 파일 이름
	            maDocLibDetailDTO.setDocDataId(docDataId);
	            MwareFile mwareFile = FileUploadUtil.doFileUpload(fileList[i], 
	                    maDocLibDetailDTO.getDocDataId(), nfFilePath); //file, physicalName(저장될 이름), 저장될 폴더  
	            //================================================================================

	            result += maDocLibDetailDAO.insertFileInfo(mwareFile, maDocLibDetailDTO);
	        }
			
		}
		return result;
	}
	public int updatePhoto(List list, FormFile[] fileList)  throws Exception
	{
		int result = 0;
		if (fileList == null||fileList[1]==null) return 0;
		
		MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
        MaDocLibDetailDTO maDocLibDetailDTO = new MaDocLibDetailDTO();
        
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        
		for(Object obj : list){
			Map map = (Map)obj;

			String docDataId = String.valueOf(map.get("docDataId"));
			maDocLibDetailDAO.deleteFileInfo(docDataId);
			deleteTempPhotoOne(map);
			
			String docId = String.valueOf(map.get("docId"));
			
	        maDocLibDetailDTO.setCompNo(String.valueOf(map.get("compNo")));
	        
	        maDocLibDetailDTO.setDocId(docId);
			
			//저장 경로 저장
	        String nfFilePath =  "";
	        if(MwareConfig.osName.indexOf("LINUX") >=0){
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + "/DOCUMENT/" + ft.format(date);
	        }else{
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + "\\DOCUMENT\\" + ft.format(date);
	        }
	        maDocLibDetailDTO.setNfFilePath(nfFilePath);
	        
	        for (int i=0; i < fileList.length; i++)
	        {
	            if (fileList[i] == null || "".equals(fileList[i].getFileName()))
	            {
	                continue;
	            }

	            //================================================================================
	            // 첨부파일을 upload 한다.
	            maDocLibDetailDTO.setDocDataId(docDataId);
	            MwareFile mwareFile = FileUploadUtil.doFileUpload(fileList[i], 
	                    maDocLibDetailDTO.getDocDataId(), nfFilePath); //file, physicalName(저장될 이름), 저장될 폴더  
	            //================================================================================

	            result += maDocLibDetailDAO.insertFileInfo(mwareFile, maDocLibDetailDTO);
	        }
			
		}
		return result;
	}
	public int deletePhoto(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			String docDataId = String.valueOf(map.get("docDataId"));
			maDocLibDetailDAO.deleteFileInfo(docDataId);
			deleteTempPhotoOne(map);
		}
		return resultQty;
	}
}

