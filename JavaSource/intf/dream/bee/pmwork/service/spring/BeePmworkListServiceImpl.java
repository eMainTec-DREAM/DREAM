package intf.dream.bee.pmwork.service.spring;

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
import dream.doc.file.dao.MaDocLibDetailDAO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;
import dream.work.list.dao.WoPlanCraftDetailDAO;
import dream.work.list.dao.WoPlanDetailDAO;
import dream.work.list.dao.WoPlanPartDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;
import dream.work.list.service.MaWoResultMstrDetailService;
import intf.dream.bee.inspection.dao.BeeInspectionListDAO;
import intf.dream.bee.pmi.day.dao.BeePmiDayListDAO;
import intf.dream.bee.pmi.patrol.dao.BeePmiPatrolListDAO;
import intf.dream.bee.pmi.routine.dao.BeePmiRoutineListDAO;
import intf.dream.bee.pmwork.dao.BeePmworkListDAO;
import intf.dream.bee.pmwork.dto.BeePmworkCommonDTO;
import intf.dream.bee.pmwork.service.BeePmworkListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beePmworkListServiceTarget"
 * @spring.txbn id="beePmworkListService"
 * @spring.property name="beePmworkListDAO" ref="beePmworkListDAO"
 * @spring.property name="maDocLibDetailDAO" ref="maDocLibDetailDAO"
 * @spring.property name="beePmiDayListDAO" ref="beePmiDayListDAO"
 * @spring.property name="beePmiRoutineListDAO" ref="beePmiRoutineListDAO"
 * @spring.property name="beePmiPatrolListDAO" ref="beePmiPatrolListDAO"
 * @spring.property name="beeInspectionListDAO" ref="beeInspectionListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="woPlanDetailDAO" ref="woPlanDetailDAO"
 * @spring.property name="woPlanCraftDetailDAO" ref="woPlanCraftDetailDAO"
 * @spring.property name="woPlanPartDetailDAO" ref="woPlanPartDetailDAO"
 */
public class BeePmworkListServiceImpl implements BeePmworkListService
{
    private BeePmworkListDAO beePmworkListDAO = null;
    private MaDocLibDetailDAO maDocLibDetailDAO = null;
    
    private BeePmiDayListDAO beePmiDayListDAO = null;
    
    private BeePmiRoutineListDAO beePmiRoutineListDAO = null;
    
    private BeePmiPatrolListDAO beePmiPatrolListDAO = null;

    private BeeInspectionListDAO beeInspectionListDAO = null;
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;

    private WoPlanDetailDAO woPlanDetailDAO = null;
    private WoPlanCraftDetailDAO woPlanCraftDetailDAO = null;
    private WoPlanPartDetailDAO woPlanPartDetailDAO = null;

    
    public WoPlanDetailDAO getWoPlanDetailDAO() {
		return woPlanDetailDAO;
	}

	public void setWoPlanDetailDAO(WoPlanDetailDAO woPlanDetailDAO) {
		this.woPlanDetailDAO = woPlanDetailDAO;
	}

	public WoPlanCraftDetailDAO getWoPlanCraftDetailDAO() {
		return woPlanCraftDetailDAO;
	}

	public void setWoPlanCraftDetailDAO(WoPlanCraftDetailDAO woPlanCraftDetailDAO) {
		this.woPlanCraftDetailDAO = woPlanCraftDetailDAO;
	}

	public WoPlanPartDetailDAO getWoPlanPartDetailDAO() {
		return woPlanPartDetailDAO;
	}

	public void setWoPlanPartDetailDAO(WoPlanPartDetailDAO woPlanPartDetailDAO) {
		this.woPlanPartDetailDAO = woPlanPartDetailDAO;
	}

	public MaWoResultMstrDetailService getMaWoResultMstrDetailService()
    {
        return maWoResultMstrDetailService;
    }

    public void setMaWoResultMstrDetailService(
            MaWoResultMstrDetailService maWoResultMstrDetailService)
    {
        this.maWoResultMstrDetailService = maWoResultMstrDetailService;
    }

	public BeePmiDayListDAO getBeePmiDayListDAO() {
		return beePmiDayListDAO;
	}

	public void setBeePmiDayListDAO(BeePmiDayListDAO beePmiDayListDAO) {
		this.beePmiDayListDAO = beePmiDayListDAO;
	}


	public BeePmiRoutineListDAO getBeePmiRoutineListDAO() {
		return beePmiRoutineListDAO;
	}

	public void setBeePmiRoutineListDAO(BeePmiRoutineListDAO beePmiRoutineListDAO) {
		this.beePmiRoutineListDAO = beePmiRoutineListDAO;
	}

	public BeePmiPatrolListDAO getBeePmiPatrolListDAO() {
		return beePmiPatrolListDAO;
	}

	public void setBeePmiPatrolListDAO(BeePmiPatrolListDAO beePmiPatrolListDAO) {
		this.beePmiPatrolListDAO = beePmiPatrolListDAO;
	}

	public BeeInspectionListDAO getBeeInspectionListDAO() {
		return beeInspectionListDAO;
	}

	public void setBeeInspectionListDAO(BeeInspectionListDAO beeInspectionListDAO) {
		this.beeInspectionListDAO = beeInspectionListDAO;
	}

	public MaDocLibDetailDAO getMaDocLibDetailDAO() {
		return maDocLibDetailDAO;
	}

	public void setMaDocLibDetailDAO(MaDocLibDetailDAO maDocLibDetailDAO) {
		this.maDocLibDetailDAO = maDocLibDetailDAO;
	}
	public BeePmworkListDAO getBeePmworkListDAO() {
		return beePmworkListDAO;
	}
	public void setBeePmworkListDAO(BeePmworkListDAO beePmworkListDAO) {
		this.beePmworkListDAO = beePmworkListDAO;
	}
	
	public List findPmworkList(BeePmworkCommonDTO beePmworkCommonDTO, Map map) throws Exception
	{      
		return beePmworkListDAO.findPmworkList(beePmworkCommonDTO, map);
	}
	public List findPmworkCount(BeePmworkCommonDTO beePmworkCommonDTO, Map map) throws Exception
	{      
		return beePmworkListDAO.findPmworkCount(beePmworkCommonDTO, map);
	}
	public int deletePmwork(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beePmworkListDAO.deleteWoCraft(map);
			beePmworkListDAO.deleteWoParts(map);
			beePmworkListDAO.deleteWoequip(map);
			beePmworkListDAO.deletePmwork(map);
		}
		return resultQty;
	}
	public int insertPmwork(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beePmworkListDAO.insertPmwork(map);
			beePmworkListDAO.insertWoequip(map);
			
			//TAWOPLAN INSERT 
        	User loginUser = new User();
        	loginUser.setPlant(CommonUtil.convertString(map.get("plant")));
        	WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
        	woPlanDetailDTO.setCompNo(CommonUtil.convertString(map.get("compNo")));
        	woPlanDetailDTO.setWkOrId(CommonUtil.convertString(map.get("wkorId")));
        	woPlanDetailDTO.setWoPlanStatusId("PPC");
        	woPlanDetailDTO.setWkOrDesc(CommonUtil.convertString(map.get("woDesc")));
        	woPlanDetailDTO.setWoTypeId(CommonUtil.convertString(map.get("woType")));
        	woPlanDetailDTO.setDeptId(CommonUtil.convertString(map.get("deptId")));
        	woPlanDetailDTO.setPmTypeId(CommonUtil.convertString(map.get("pmType")));
        	woPlanDetailDTO.setEmpId(CommonUtil.convertString(map.get("empId")));
        	woPlanDetailDTO.setPerform(CommonUtil.convertString(map.get("perform")));
        	woPlanDetailDTO.setWoNo(CommonUtil.convertString(map.get("wkorId")));
        	woPlanDetailDTO.setShiftTypeId(CommonUtil.convertString(map.get("shiftType")));
        	woPlanDetailDTO.setWkorDate(CommonUtil.convertDate(CommonUtil.convertString(map.get("wkorDate"))));
        	woPlanDetailDTO.setWkCtrId(CommonUtil.convertString(map.get("wkCtrId")));
			
        	woPlanDetailDAO.insertDetail(woPlanDetailDTO, loginUser);
		}
		return resultQty;
	}
	public int updatePmwork(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beePmworkListDAO.updatePmwork(map);
			completedUnplanWorkOrder(map);
		}
		return resultQty;
	}

	
	public List findWoCraftList(Map map) throws Exception
	{      
		return beePmworkListDAO.findWoCraftList(map);
	}
	public int insertWoCraft(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beePmworkListDAO.insertWoCraft(map);
			
			WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    		woPlanCommonDTO.setCompNo(CommonUtil.convertString(map.get("compNo")));
    		woPlanCommonDTO.setWkOrId(CommonUtil.convertString(map.get("wkorId")));

    		WoPlanCraftDetailDTO woPlanCraftDetailDTO = new WoPlanCraftDetailDTO();
    		woPlanCraftDetailDTO.setWoCraftId(CommonUtil.convertString(map.get("woCraftId")));
    		woPlanCraftDetailDTO.setEmpId(CommonUtil.convertString(map.get("empId")));
    		woPlanCraftDetailDTO.setStartDate(CommonUtil.convertDate(CommonUtil.convertString(map.get("startDate"))));
    		woPlanCraftDetailDTO.setStartTime(CommonUtil.convertTime(CommonUtil.convertString(map.get("startTime"))));
    		woPlanCraftDetailDTO.setEndDate(CommonUtil.convertDate(CommonUtil.convertString(map.get("endDate"))));
    		woPlanCraftDetailDTO.setEndTime(CommonUtil.convertTime(CommonUtil.convertString(map.get("endTime"))));
    		woPlanCraftDetailDTO.setWorkTime(CommonUtil.convertString(map.get("workTime")));
    		woPlanCraftDetailDTO.setRemark(CommonUtil.convertString(map.get("remark")));
    		
    		woPlanCraftDetailDAO.insertDetail(woPlanCraftDetailDTO, woPlanCommonDTO);
		}
		return resultQty;
	}
	public int updateWoCraft(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beePmworkListDAO.updateWoCraft(map);
		}
		return resultQty;
	}
	public int deleteWoCraft(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beePmworkListDAO.deleteWoCraft(map);
		}
		return resultQty;
	}

	public List findWoPartsList(Map map) throws Exception
	{      
		return beePmworkListDAO.findWoPartsList(map);
	}
	public int insertWoParts(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beePmworkListDAO.insertWoParts(map);
			
			WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    		woPlanCommonDTO.setCompNo(CommonUtil.convertString(map.get("compNo")));
    		woPlanCommonDTO.setWkOrId(CommonUtil.convertString(map.get("wkorId")));

    		WoPlanPartDetailDTO woPlanPartDetailDTO = new WoPlanPartDetailDTO();
    		woPlanPartDetailDTO.setWoPartId(CommonUtil.convertString(map.get("woPartId")));
    		woPlanPartDetailDTO.setPartId(CommonUtil.convertString(map.get("partId")));
    		woPlanPartDetailDTO.setWcodeId(CommonUtil.convertString(map.get("wcodeId")));
    		woPlanPartDetailDTO.setUseQty(CommonUtil.convertString(map.get("useQty")));
    		woPlanPartDetailDTO.setPartGrade(CommonUtil.convertString(map.get("partGrade")));
    		woPlanPartDetailDTO.setRemark(CommonUtil.convertString(map.get("remark")));
    		
    		woPlanPartDetailDAO.insertDetail(woPlanPartDetailDTO, woPlanCommonDTO);
		}
		return resultQty;
	}
	public int updateWoParts(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beePmworkListDAO.updateWoParts(map);
		}
		return resultQty;
	}
	public int deleteWoParts(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beePmworkListDAO.deleteWoParts(map);
		}
		return resultQty;
	}
	public List findStockQty(Map map) throws Exception
	{      
		return beePmworkListDAO.findStockQty(map);
	}
	

	public List findPhotoList(Map map) throws Exception
	{      
		
		map.put("COMP_NO", CommonUtil.convertString(map.get("compNo")));
		//일상점검 or 파트체인지 점검
        if("PM_DAY_POINT".equals(CommonUtil.convertString(map.get("objectType")))||"PM_PART_POINT".equals(CommonUtil.convertString(map.get("objectType")))){
        	String objectId = beePmiDayListDAO.getInsdpoint(map);
        	if("".equals(objectId)){
        		objectId = beePmworkListDAO.getNextSequence("SQAPMINSDPOINT_ID");
        	}
        	map.put("objectId",objectId);
        }else if("PM_ROUTINE_POINT".equals(CommonUtil.convertString(map.get("objectType")))){
        	String objectId = beePmiRoutineListDAO.getInspoint(map);
        	if("".equals(objectId)){
        		objectId = beePmworkListDAO.getNextSequence("SQAPMINSPOINT_ID");
        	}
        	map.put("objectId",objectId);
        }else if("PM_PATROL_POINT".equals(CommonUtil.convertString(map.get("objectType")))){
        	String objectId = beePmiPatrolListDAO.getInsppoint(map);
        	if("".equals(objectId)){
        		objectId = beePmworkListDAO.getNextSequence("SQAPMPTRLRSLTPOINT_ID");
        	}
        	map.put("objectId",objectId);
        }else if("PM_POINT".equals(CommonUtil.convertString(map.get("objectType")))){
        	String objectId = beeInspectionListDAO.getWopoint(map);
        	if("".equals(objectId)){
        		objectId = beePmworkListDAO.getNextSequence("SQAWOPOINT_ID");
        	}
        	map.put("objectId",objectId);
        }
		
		
		List list = beePmworkListDAO.findPhotoList(map);
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
			List tempList = beePmworkListDAO.findPhotoList(map);
			
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
		List tempList = beePmworkListDAO.findPhotoList(map);
		
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
		if (fileList == null||fileList[1] == null) return 0; // 안드로이드에서 1번에 담아서 보냄
		MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
        MaDocLibDetailDTO maDocLibDetailDTO = new MaDocLibDetailDTO();
		for(Object obj : list){
			Map map = (Map)obj;
			String docId = beePmworkListDAO.findDoc(map);
			
			Date date = new Date();
	        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
	        maDocLibDetailDTO.setCompNo(CommonUtil.convertString(String.valueOf(map.get("compNo"))));
	        map.put("COMP_NO", CommonUtil.convertString(String.valueOf(map.get("compNo"))));

			if("0".equals(docId)&&!"".equals(docId)){
				//새 헤더 생성
				//월별로 폴더를 만들어 저장한다.
		        String newDocId = String.valueOf(map.get("docId"));
		        
		        maDocLibDetailDTO.setDocId(newDocId);
		        maDocLibDetailDTO.setRegId(CommonUtil.convertString(String.valueOf(map.get("regId"))));
		        maDocLibDetailDTO.setDeptId(CommonUtil.convertString(String.valueOf(map.get("deptId"))));
		        maDocLibDetailDTO.setDocctgId(CommonUtil.convertString(String.valueOf(map.get("docCtg"))));
		        maDocLibDetailDTO.setSecurGrade("".equals(CommonUtil.convertString(String.valueOf(map.get("securGrade"))))?"3":CommonUtil.convertString(String.valueOf(map.get("securGrade"))));
		        maDocLibDetailDTO.setDocCateg("".equals(CommonUtil.convertString(String.valueOf(map.get("docCateg"))))?"DOC":CommonUtil.convertString(String.valueOf(map.get("docCateg"))));
		        maDocLibCommonDTO.setObjectType(CommonUtil.convertString(String.valueOf(map.get("objectType"))));
		        maDocLibDetailDTO.setDescription(CommonUtil.convertString(String.valueOf(map.get("woDesc"))));
		        maDocLibDetailDTO.setRemark(CommonUtil.convertString(String.valueOf(map.get("remark"))));
		        
		        maDocLibDetailDTO.setDocNo(newDocId);
		        
		        //일상점검 or 파트체인지 점검
		        if("PM_DAY_POINT".equals(CommonUtil.convertString(map.get("objectType")))||"PM_PART_POINT".equals(CommonUtil.convertString(map.get("objectType")))){
		        	String objectId = beePmiDayListDAO.getInsdpoint(map);
		        	if("".equals(objectId)){
		        		objectId = beePmworkListDAO.getNextSequence("SQAPMINSDPOINT_ID");
		        	}
		        	map.put("objectId",objectId);
		        }else if("PM_ROUTINE_POINT".equals(CommonUtil.convertString(map.get("objectType")))){
		        	String objectId = beePmiRoutineListDAO.getInspoint(map);
		        	if("".equals(objectId)){
		        		objectId = beePmworkListDAO.getNextSequence("SQAPMINSPOINT_ID");
		        	}
		        	map.put("objectId",objectId);
		        }else if("PM_PATROL_POINT".equals(CommonUtil.convertString(map.get("objectType")))){
		        	String objectId = beePmiPatrolListDAO.getInsppoint(map);
		        	if("".equals(objectId)){
		        		objectId = beePmworkListDAO.getNextSequence("SQAPMPTRLRSLTPOINT_ID");
		        	}
		        	map.put("objectId",objectId);
		        }else if("PM_POINT".equals(CommonUtil.convertString(map.get("objectType")))){
		        	String objectId = beeInspectionListDAO.getWopoint(map);
		        	if("".equals(objectId)){
		        		objectId = beePmworkListDAO.getNextSequence("SQAWOPOINT_ID");
		        	}
		        	map.put("objectId",objectId);
		        }
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
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + File.separator + "DOCUMENT" + File.separator + ft.format(date);
	        }else{
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + File.separator + "DOCUMENT" + File.separator + ft.format(date);
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
		if (fileList == null||fileList[1]==null) return 0; // 안드로이드에서 1번에 담아서 보냄
		
		MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
        MaDocLibDetailDTO maDocLibDetailDTO = new MaDocLibDetailDTO();
        
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        
		for(Object obj : list){
			Map map = (Map)obj;

			String docDataId = CommonUtil.convertString(String.valueOf(map.get("docDataId")));
			maDocLibDetailDAO.deleteFileInfo(docDataId);
			deleteTempPhotoOne(map);
			
			String docId = String.valueOf(map.get("docId"));
			
	        maDocLibDetailDTO.setCompNo(CommonUtil.convertString(String.valueOf(map.get("compNo"))));
	        maDocLibDetailDTO.setDocId(docId);
			
			//저장 경로 저장
	        String nfFilePath =  "";
	        if(MwareConfig.osName.indexOf("LINUX") >=0){
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + File.separator + "DOCUMENT" + File.separator + ft.format(date);
	        }else{
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + File.separator + "DOCUMENT" + File.separator + ft.format(date);
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
	//설비 첨부파일 올릴 때 document 정보도 변경한다.
	
	public int updateDocument(List list, FormFile[] fileList)  throws Exception
	{
		int result = 0;
		
		boolean fileConfirm = true;
		if (fileList == null||fileList[1]==null) fileConfirm = false; // 안드로이드에서 1번에 담아서 보냄
		
		MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
        MaDocLibDetailDTO maDocLibDetailDTO = new MaDocLibDetailDTO();
        
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        
		for(Object obj : list){
			Map map = (Map)obj;

			String docDataId = String.valueOf(map.get("docDataId"));
			deleteTempPhotoOne(map);
			
			String docId = String.valueOf(map.get("docId"));
			
	        maDocLibDetailDTO.setCompNo(CommonUtil.convertString(String.valueOf(map.get("compNo"))));
	        maDocLibDetailDTO.setDocId(docId);

	        maDocLibDetailDTO.setDescription(CommonUtil.convertString(String.valueOf(map.get("woDesc"))));
	        maDocLibDetailDTO.setDocNo(CommonUtil.convertString(String.valueOf(map.get("docNo"))));
	        maDocLibDetailDTO.setDocCateg(CommonUtil.convertString(String.valueOf(map.get("docCateg"))));
	        maDocLibDetailDTO.setSecurGrade(CommonUtil.convertString(String.valueOf(map.get("securGrade"))));
	        maDocLibDetailDTO.setDocctgId(CommonUtil.convertString(String.valueOf(map.get("docCtg"))));
	        maDocLibDetailDTO.setDeptId(CommonUtil.convertString(String.valueOf(map.get("deptId"))));
	        maDocLibDetailDTO.setRegId(CommonUtil.convertString(String.valueOf(map.get("regId"))));
	        maDocLibDetailDTO.setRegDate(CommonUtil.convertString(String.valueOf(map.get("regDate"))));
	        maDocLibDetailDTO.setRemark(CommonUtil.convertString(String.valueOf(map.get("remark"))));
			
	        //저장 경로 저장
	        String nfFilePath =  "";
	        if(MwareConfig.osName.indexOf("LINUX") >=0){
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + File.separator + "DOCUMENT" + File.separator + ft.format(date);
	        }else{
	        	nfFilePath =  maDocLibDetailDTO.getCompNo() + File.separator + "DOCUMENT" + File.separator + ft.format(date);
	        }
	        maDocLibDetailDTO.setNfFilePath(nfFilePath);
	        
	        if (fileConfirm) {
	        	maDocLibDetailDAO.deleteFileInfo(docDataId);
	        	
	        	maDocLibDetailDAO.updateDetail( maDocLibDetailDTO);
	        	
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
	        } else{
	        	maDocLibDetailDAO.updateDetail( maDocLibDetailDTO);
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
	private void completedUnplanWorkOrder(Map map) throws Exception{
		String wkorId = CommonUtil.convertString(map.get("wkorId"));
		String isConfirm = CommonUtil.convertString(map.get("isConfirm"));
        String compNo = CommonUtil.convertString(map.get("compNo"));
        User user = new User();
        user.setCompNo(compNo);
        user.setUserId(CommonUtil.convertString(map.get("userId")));
        String lang = "".equals(CommonUtil.convertString(map.get("lang")))?"ko":CommonUtil.convertString(map.get("lang"));
        user.setLangId(lang);
        Locale locale = new Locale(lang);
        user.setLocale(locale);
        
        maWoResultMstrDetailService = (MaWoResultMstrDetailService) CommonUtil.getBean("maWoResultMstrDetailService", user);
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
        if(!"".equals(wkorId) && "Y".equals(isConfirm))
        {
            maWoResultMstrCommonDTO.setWkOrId(wkorId);
            maWoResultMstrCommonDTO.setCompNo(compNo);
            MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
            MaWoResultPmCalDTO maWoResultPmCalDTO = new MaWoResultPmCalDTO();
            
            maWoResultMstrDetailDTO.setLoginUser(user);
            maWoResultMstrDetailDTO.setCompNo(compNo);
            maWoResultMstrDetailDTO.setEnterBy(CommonUtil.convertString(map.get("userId")));

            maWoResultMstrDetailDTO.setEndDate(maWoResultMstrDetailDTO.getEndDate());
            maWoResultMstrDetailDTO.setEndTime(maWoResultMstrDetailDTO.getEndTime());
            maWoResultMstrDetailDTO.setUpdDate(maWoResultMstrDetailDTO.getUpdDate());
            maWoResultMstrDetailDTO.setStartDate(maWoResultMstrDetailDTO.getStartDate());
            maWoResultMstrDetailDTO.setStartTime(maWoResultMstrDetailDTO.getStartTime());
            maWoResultMstrDetailDTO.setWkorDate(maWoResultMstrDetailDTO.getWkorDate());
            
            //완료가 아니면 완료 시킴
            if(!"C".equals(maWoResultMstrDetailDTO.getWoStatusId())&&maWoResultMstrDetailDTO.getWkOrId()!=null&&!"".equals(maWoResultMstrDetailDTO.getWkOrId())){
                maWoResultMstrDetailService.completeDetail(maWoResultMstrDetailDTO,maWoResultPmCalDTO);
            }
        }
	}
}

