package intf.dream.android.offline.madoclib.service.spring;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import common.file.MwareFile;
import common.finder.valid.dao.ValidationDAO;
import common.util.CommonUtil;
import dream.doc.file.dao.MaDocLibDetailDAO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;
import intf.dream.android.offline.madoclib.dao.AnIfDocLibListDAO;
import intf.dream.android.offline.madoclib.service.AnIfDocLibListService;
import intf.dream.android.offline.mainspection.dao.AnIfInspectionListDAO;
import intf.dream.android.offline.pmi.day.dao.AnIfPmiDayListDAO;
import intf.dream.android.offline.pmi.patrol.dao.AnIfPmiPatrolListDAO;
import intf.dream.android.offline.pmi.routine.dao.AnIfPmiRoutineListDAO;


/**
 * 첨부파일 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: AnIfDocLibListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="anIfDocLibListServiceTarget"
 * @spring.txbn id="anIfDocLibListService"
 * @spring.property name="anIfDocLibListDAO" ref="anIfDocLibListDAO"
 * @spring.property name="maDocLibDetailDAO" ref="maDocLibDetailDAO"
 * @spring.property name="anIfPmiDayListDAO" ref="anIfPmiDayListDAO"
 * @spring.property name="anIfPmiRoutineListDAO" ref="anIfPmiRoutineListDAO"
 * @spring.property name="anIfPmiPatrolListDAO" ref="anIfPmiPatrolListDAO"
 * @spring.property name="anIfInspectionListDAO" ref="anIfInspectionListDAO"
 * @spring.property name="validationDAO" ref="validationDAO"
 */
public class AnIfDocLibListServiceImpl implements AnIfDocLibListService
{
    private AnIfDocLibListDAO anIfDocLibListDAO = null;

    private MaDocLibDetailDAO maDocLibDetailDAO = null;
    
    private AnIfPmiDayListDAO anIfPmiDayListDAO = null;
    
    private AnIfPmiRoutineListDAO anIfPmiRoutineListDAO = null;
    
    private AnIfPmiPatrolListDAO anIfPmiPatrolListDAO = null;
    private AnIfInspectionListDAO anIfInspectionListDAO = null;
    
    private ValidationDAO validationDAO = null;
    
    
	public AnIfInspectionListDAO getAnIfInspectionListDAO() {
		return anIfInspectionListDAO;
	}

	public void setAnIfInspectionListDAO(AnIfInspectionListDAO anIfInspectionListDAO) {
		this.anIfInspectionListDAO = anIfInspectionListDAO;
	}

	public AnIfPmiRoutineListDAO getAnIfPmiRoutineListDAO() {
		return anIfPmiRoutineListDAO;
	}

	public void setAnIfPmiRoutineListDAO(AnIfPmiRoutineListDAO anIfPmiRoutineListDAO) {
		this.anIfPmiRoutineListDAO = anIfPmiRoutineListDAO;
	}

	public AnIfPmiPatrolListDAO getAnIfPmiPatrolListDAO() {
		return anIfPmiPatrolListDAO;
	}

	public void setAnIfPmiPatrolListDAO(AnIfPmiPatrolListDAO anIfPmiPatrolListDAO) {
		this.anIfPmiPatrolListDAO = anIfPmiPatrolListDAO;
	}

	public AnIfPmiDayListDAO getAnIfPmiDayListDAO() {
		return anIfPmiDayListDAO;
	}

	public void setAnIfPmiDayListDAO(AnIfPmiDayListDAO anIfPmiDayListDAO) {
		this.anIfPmiDayListDAO = anIfPmiDayListDAO;
	}

	public AnIfDocLibListDAO getAnIfDocLibListDAO() {
		return anIfDocLibListDAO;
	}

	public void setAnIfDocLibListDAO(AnIfDocLibListDAO anIfDocLibListDAO) {
		this.anIfDocLibListDAO = anIfDocLibListDAO;
	}

	public MaDocLibDetailDAO getMaDocLibDetailDAO() {
		return maDocLibDetailDAO;
	}

	public void setMaDocLibDetailDAO(MaDocLibDetailDAO maDocLibDetailDAO) {
		this.maDocLibDetailDAO = maDocLibDetailDAO;
	}

	public ValidationDAO getValidationDAO() {
		return validationDAO;
	}

	public void setValidationDAO(ValidationDAO validationDAO) {
		this.validationDAO = validationDAO;
	}


    public int uploadFiles(Map map, FormFile[] fileList) throws FileNotFoundException, IOException
    {
        int result = 0;
        
        if (fileList == null||fileList[1] == null) return 0;
        //월별로 폴더를 만들어 저장한다.
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        
        
        String docId = maDocLibDetailDAO.getNextSequence("SQADOC_ID");
        //objectType (IMG, DOC) 에 따라 두가지 타입으로 저장 필요.
        MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
        MaDocLibDetailDTO maDocLibDetailDTO = new MaDocLibDetailDTO();
        maDocLibDetailDTO.setCompNo(String.valueOf(map.get("compNo")));
        maDocLibDetailDTO.setDocId(docId);
        maDocLibDetailDTO.setRegId(String.valueOf(map.get("regId")));
        maDocLibDetailDTO.setDeptId(String.valueOf(map.get("deptId")));
        maDocLibDetailDTO.setSecurGrade("3");
        maDocLibDetailDTO.setDocCateg("DOC");
        maDocLibCommonDTO.setObjectType(String.valueOf(map.get("ifType")));
        maDocLibDetailDTO.setDescription(String.valueOf(map.get("woDesc")));
        
        //새로 생성된 Work Order의 첨부파일은 wkorId 조회후 입력
//        if("Y".equals(String.valueOf(map.get("mobinsYn"))))
//        {
//            String[] objectIDArr = maDocLibDetailDAO.getWkorId(String.valueOf(map.get("objectId")));
//            map.put("objectId", objectIDArr[0]);
//            maDocLibDetailDTO.setDescription(String.valueOf(map.get("woDesc")));
//        }
//        else //제목 조회
//        {
//            String[] objectIDArr = maDocLibDetailDAO.getWkorDesc(String.valueOf(map.get("objectId")));
//            maDocLibDetailDTO.setDescription(objectIDArr[1]);
//        }
        
        //일상점검 or 파트체인지 점검
        if("PM_DAY_POINT".equals(CommonUtil.convertString(map.get("ifType")))||"PM_PART_POINT".equals(CommonUtil.convertString(map.get("ifType")))){
        	String objectId = anIfPmiDayListDAO.getInsdpoint(map);
        	if("".equals(objectId)){
        		objectId = anIfPmiDayListDAO.getNextSequence("SQAPMINSDPOINT_ID");
        	}
        	map.put("objectId",objectId);
        }else if("PM_ROUTINE_POINT".equals(CommonUtil.convertString(map.get("ifType")))){
        	String objectId = anIfPmiRoutineListDAO.getInspoint(map);
        	if("".equals(objectId)){
        		objectId = anIfPmiRoutineListDAO.getNextSequence("SQAPMINSPOINT_ID");
        	}
        	map.put("objectId",objectId);
        }else if("PM_PATROL_POINT".equals(CommonUtil.convertString(map.get("ifType")))){
        	String objectId = anIfPmiPatrolListDAO.getInsppoint(map);
        	if("".equals(objectId)){
        		objectId = anIfPmiPatrolListDAO.getNextSequence("SQAPMPTRLRSLTPOINT_ID");
        	}
        	map.put("objectId",objectId);
        }else if("PM_POINT".equals(CommonUtil.convertString(map.get("ifType")))){
        	String objectId = anIfInspectionListDAO.getWopoint(map);
        	if("".equals(objectId)){
        		objectId = anIfInspectionListDAO.getNextSequence("SQAWOPOINT_ID");
        	}
        	map.put("objectId",objectId);
        }
        
        maDocLibDetailDTO.setDocNo(docId);
        maDocLibCommonDTO.setObjectId(String.valueOf(map.get("objectId")));
        
        //Header 생성
        maDocLibDetailDAO.insertDetail(maDocLibCommonDTO, maDocLibDetailDTO);
        
        maDocLibDetailDAO.insertObjDoc(maDocLibCommonDTO, maDocLibDetailDTO);
        
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
            String docDataId = anIfDocLibListDAO.getNextSequence("SQADOCDATA_NO")+"";   //저장된 파일 이름
            maDocLibDetailDTO.setDocDataId(docDataId);
            MwareFile mwareFile = FileUploadUtil.doFileUpload(fileList[i], 
                    maDocLibDetailDTO.getDocDataId(), nfFilePath); //file, physicalName(저장될 이름), 저장될 폴더  
            //================================================================================

            result += maDocLibDetailDAO.insertFileInfo(mwareFile, maDocLibDetailDTO);
        }
        
        
        return result;
    }
}

