package dream.comm.revision.service.spring;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.azure.storage.StorageException;

import common.bean.MwareConfig;
import common.bean.User;
import common.file.FileUploadUtil;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.asset.list.dao.MaEqMstrDetailDAO;
import dream.asset.list.dao.MaEqMstrListDAO;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.service.MaEqMstrAsmbDetailService;
import dream.asset.list.service.MaEqMstrAsmbListService;
import dream.asset.list.service.MaEqMstrDetailService;
import dream.comm.revision.dao.CommRevDAO;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.comm.revision.service.CommRevService;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.pm.list.dao.MaPmMstrDetailDAO;
import dream.work.pm.list.dao.MaPmMstrListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.std.dao.MaStdPointHdrListDAO;

/**
 * 상세 serviceimpl 
 * @author  jung7126
 * @version $Id: CommRevServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="commRevServiceTarget"
 * @spring.txbn id="commRevService"
 * @spring.property name="commRevDAO" ref="commRevDAO"
 * @spring.property name="maPmMstrListDAO" ref="maPmMstrListDAO"
 * @spring.property name="maPmMstrDetailDAO" ref="maPmMstrDetailDAO"
 * @spring.property name="maEqMstrListDAO" ref="maEqMstrListDAO"
 * @spring.property name="maStdPointHdrListDAO" ref="maStdPointHdrListDAO"
 * @spring.property name="maEqMstrDetailDAO" ref="maEqMstrDetailDAO"
 * 
 */
public class CommRevServiceImpl implements CommRevService
{
    private CommRevDAO commRevDAO = null;
    private MaPmMstrListDAO maPmMstrListDAO = null;
    private MaPmMstrDetailDAO maPmMstrDetailDAO = null;
    private MaEqMstrListDAO maEqMstrListDAO = null;
    private MaStdPointHdrListDAO maStdPointHdrListDAO = null;
    private MaEqMstrDetailDAO maEqMstrDetailDAO = null;
    
	public MaEqMstrDetailDAO getMaEqMstrDetailDAO()
    {
        return maEqMstrDetailDAO;
    }
    public void setMaEqMstrDetailDAO(MaEqMstrDetailDAO maEqMstrDetailDAO)
    {
        this.maEqMstrDetailDAO = maEqMstrDetailDAO;
    }
    public MaStdPointHdrListDAO getMaStdPointHdrListDAO() {
		return maStdPointHdrListDAO;
	}
	public void setMaStdPointHdrListDAO(MaStdPointHdrListDAO maStdPointHdrListDAO) {
		this.maStdPointHdrListDAO = maStdPointHdrListDAO;
	}
    public MaEqMstrListDAO getMaEqMstrListDAO() {
		return maEqMstrListDAO;
	}
	public void setMaEqMstrListDAO(MaEqMstrListDAO maEqMstrListDAO) {
		this.maEqMstrListDAO = maEqMstrListDAO;
	}
	public MaPmMstrDetailDAO getMaPmMstrDetailDAO() {
		return maPmMstrDetailDAO;
	}
	public void setMaPmMstrDetailDAO(MaPmMstrDetailDAO maPmMstrDetailDAO) {
		this.maPmMstrDetailDAO = maPmMstrDetailDAO;
	}
	public MaPmMstrListDAO getMaPmMstrListDAO() {
		return maPmMstrListDAO;
	}
	public void setMaPmMstrListDAO(MaPmMstrListDAO maPmMstrListDAO) {
		this.maPmMstrListDAO = maPmMstrListDAO;
	}
	public CommRevDAO getCommRevDAO() {
		return commRevDAO;
	}
	public void setCommRevDAO(CommRevDAO commRevDAO) {
		this.commRevDAO = commRevDAO;
	}

	public CommRevCommonDTO findDetail(CommRevCommonDTO commRevCommonDTO, User user) throws Exception{
		return commRevDAO.findDetail(commRevCommonDTO, user);
	}
	
	public int insertRegislate(CommRevCommonDTO commRevCommonDTO,User user) throws Exception
    {   
		int result=0;
		
		String revhistId = commRevDAO.getNextSequence("SQAREVISIONHIST_ID");
		commRevCommonDTO.setRevisionhistId(revhistId);
		// 신규 Revision 생성
		this.insertRevHist(commRevCommonDTO, user);
		
		
		if("PMSTD".equals(commRevCommonDTO.getRevisionObjType()))
    	{
			result += commRevDAO.insertPm(commRevCommonDTO, user);
    	}
		else if("ASSET".equals(commRevCommonDTO.getRevisionObjType()))
		{
			if("MD".equals(commRevCommonDTO.getEqCtgTypeId())){
				commRevDAO.insertMold(commRevCommonDTO);
			}else if("TL".equals(commRevCommonDTO.getEqCtgTypeId())){
				commRevDAO.insertTool(commRevCommonDTO);
			}else if("BD".equals(commRevCommonDTO.getEqCtgTypeId())){
				commRevDAO.insertBuilding(commRevCommonDTO);
			}else if("PT".equals(commRevCommonDTO.getEqCtgTypeId())){
				commRevDAO.insertDevice(commRevCommonDTO);
			}else if("IT".equals(commRevCommonDTO.getEqCtgTypeId())){
				commRevDAO.insertEqItDetail(commRevCommonDTO);
			}
			
			result += commRevDAO.insertAsset(commRevCommonDTO,user);
			
			// TAEQASSLIST default 데이터 생성
			MaEqMstrDetailDAO maEqMstrDetailDAO = getMaEqMstrDetailDAO();
			maEqMstrDetailDAO.insertAssDetail(user, commRevCommonDTO.getObjectId());
		}
		else if("STWRK".equals(commRevCommonDTO.getRevisionObjType()))
		{
			commRevDAO.insertStwrk(commRevCommonDTO);
		}
		return result;
    }
	
	public int insertRevision(CommRevCommonDTO commRevCommonDTO, User loginUser) throws Exception
    {   
		int result=0;
		
		String revhistId = commRevDAO.getNextSequence("SQAREVISIONHIST_ID");
		commRevCommonDTO.setRevisionhistId(revhistId);
		
		String compNo = loginUser.getCompNo();
		String newSeq = commRevCommonDTO.getObjectId();
		String id = commRevCommonDTO.getOldObjectId();
		String revStat = commRevCommonDTO.getRevisionStatusId(); 
		
		// 신규 Revision 생성
		this.insertRevHist(commRevCommonDTO, loginUser);
		

		
		if("PMSTD".equals(commRevCommonDTO.getRevisionObjType()))
	    {
			//기존 PM revision 상태 변경
			commRevDAO.updatePm(id, compNo, "Y", "P");
			//pm master COPY
			result += maPmMstrListDAO.insertCopyDetail(newSeq, id, revStat, revhistId, loginUser, false);
			//pm point COPY 
			result += maPmMstrListDAO.insertCopyPoint(newSeq, id, loginUser);
			//pm part COPY
			result += maPmMstrListDAO.insertCopyPart(newSeq, id, loginUser);
			//pm part COPY
			result += maPmMstrListDAO.insertCopyMsTime(newSeq, id, loginUser);
						
			//select old pmequip_id
			String[] oldPmEquip = maPmMstrListDAO.selectPmEquipMaps(id, loginUser);
			Map pmEquipMap = new HashMap<String, String>();
			for(String oldId:oldPmEquip){
			    pmEquipMap.put(oldId, maPmMstrListDAO.getNextSequence("SQAPMEQUIP_ID"));
			    //pm equip COPY
			    result += maPmMstrListDAO.insertCopyOneEquip(newSeq, pmEquipMap.get(oldId).toString(), oldId, loginUser);
			    maPmMstrListDAO.updatePmEquipId(newSeq, pmEquipMap.get(oldId).toString(), oldId, loginUser);
			}
			//old PMSTD의 Audit Trail을 복사해서 가져온다. 
			String[] tracelogIdArr = commRevDAO.getPMTracelogId(id, compNo);
			String newTraceLogId;
			for(String tracelogId : tracelogIdArr)
			{			    
			    newTraceLogId = commRevDAO.getNextSequence("sqatracelog_id");
			    commRevDAO.copyAuditTrailObjectId(newSeq, id, compNo, newTraceLogId, tracelogId);
			    commRevDAO.copyAuditTrailDtlObjectId(newSeq, id, compNo, newTraceLogId, tracelogId);
			}
	    }
		else if("ASSET".equals(commRevCommonDTO.getRevisionObjType()))
	    {
			//기존 설비 revision 상태 변경
			commRevDAO.updateAsset(id, compNo, "Y", "P");
			
			result += maEqMstrListDAO.insertCopyDetail(newSeq, id, revhistId, "P", loginUser, "N");
			
			MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", loginUser);
			MaEqMstrAsmbDetailService maEqMstrAsmbDetailService = (MaEqMstrAsmbDetailService) CommonUtil.getBean("maEqMstrAsmbDetailService", loginUser);
			MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
			maEqMstrCommonDTO.setEquipId(id);
			List<MaEqMstrAsmbDetailDTO> maEqMstrAsmbDetailDTOList = new ArrayList<MaEqMstrAsmbDetailDTO>();
			List<Map> eqAsmbList = maEqMstrAsmbListService.findAsmbList(maEqMstrCommonDTO, new MaEqMstrAsmbListDTO(), loginUser);
			maEqMstrCommonDTO.setEquipId(newSeq);
			Map<String,String> oldNewIdMap = new HashMap<String,String>();
			for(Map eqAsmbMap:eqAsmbList){
			    MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = (MaEqMstrAsmbDetailDTO) CommonUtil.makeDTO(eqAsmbMap, MaEqMstrAsmbDetailDTO.class);
			    String newEqAsmbId = maEqMstrListDAO.getNextSequence("SQAEQASMB_ID");
			    String oldEqAsmbId = maEqMstrAsmbDetailDTO.getEqAsmbId();
			    oldNewIdMap.put(oldEqAsmbId, newEqAsmbId);
			    maEqMstrAsmbDetailDTO.setEqAsmbId(newEqAsmbId);
			    maEqMstrAsmbDetailDTO.setEquipId(newSeq);
			    maEqMstrAsmbDetailDTOList.add(maEqMstrAsmbDetailDTO);
			    maEqMstrAsmbDetailService.insertDetail(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, loginUser);
			    
			    fileImgCopy(newEqAsmbId, oldEqAsmbId, "EQASMB", loginUser);
			}
			for(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO:maEqMstrAsmbDetailDTOList) {
			    maEqMstrAsmbDetailDTO.setPeqAsmbId(StringUtil.valueOf(oldNewIdMap.get(maEqMstrAsmbDetailDTO.getPeqAsmbId())));
			    maEqMstrAsmbDetailService.updateDetail(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, loginUser);
			}
			
			result += maEqMstrListDAO.insertCopyEqMold(newSeq, id, loginUser);
			result += maEqMstrListDAO.insertCopyEqTool(newSeq, id, loginUser);
			result += maEqMstrListDAO.insertCopyEqBuilding(newSeq, id, loginUser);
			result += maEqMstrListDAO.insertCopyEqSpec(newSeq, id, loginUser);
			result += maEqMstrListDAO.insertCopyEqPart(newSeq, id, loginUser);
			result += maEqMstrListDAO.insertCopyEqAsset(newSeq, id, loginUser);
			result += maEqMstrListDAO.insertCopyEqItems(newSeq, id, loginUser);
			result += maEqMstrListDAO.insertCopyEqVendor(newSeq, id, loginUser);
			result += maEqMstrListDAO.insertCopyEqDevice(newSeq, id, loginUser);
			result += maEqMstrListDAO.insertCopyEqHist(newSeq, id, loginUser);

			fileImgCopy(newSeq, id, "EQMSTR", loginUser);
			fileDocCopy(newSeq, id, "EQMSTR", loginUser);
	        
	        maEqMstrListDAO.SP_IF_UPD_TXEQUIPMENT(newSeq,loginUser);
	    }
		else if("STWRK".equals(commRevCommonDTO.getRevisionObjType()))
	    {
			//기존 설비 revision 상태 변경
			commRevDAO.updateStwrk(id, compNo, "Y", "P");
			
			result += maStdPointHdrListDAO.insertCopyDetail(newSeq, id, revhistId, "P", loginUser, "N");
			result += maStdPointHdrListDAO.insertCopyPoint(newSeq, id, loginUser, "N");
			result += maStdPointHdrListDAO.insertCopyWoType(newSeq, id, loginUser);
			result += maStdPointHdrListDAO.insertCopyWork(newSeq, id, loginUser);
			result += maStdPointHdrListDAO.insertCopyPart(newSeq, id, loginUser);
	    }
		return result;
    }
	
	public int completeRegislate(CommRevCommonDTO commRevCommonDTO, User user) throws Exception
    {
		int result = 0;
		String revisionhistId = commRevCommonDTO.getRevisionhistId();
		String compNo = user.getCompNo();
		String objectId = commRevCommonDTO.getObjectId();
		
		// Revision History Update. 제개정완료(=C), 결재완료(=CMP)
		commRevCommonDTO.setRevisionStatusId("C");
        commRevCommonDTO.setRevisionStepStatusId("CMP");
        this.updateRevHist(commRevCommonDTO, user);
		
		if("PMSTD".equals(commRevCommonDTO.getRevisionObjType()))
		{
			// PM Update
			result += commRevDAO.updatePm(objectId, compNo, "Y", "C");
			// 기존 PM 일정 생성
			MaPmMstrDetailDTO maPmMstrDetailDTO = setPmDetailDTO(commRevCommonDTO, user);
			maPmMstrDetailDAO.updateLastSchedDate(maPmMstrDetailDTO, user);
			maPmMstrDetailDAO.createPmSchedule(user.getCompNo(), maPmMstrDetailDTO.getPmId(), user.getEmpId());
			maPmMstrDetailDAO.createWorkOrder(user.getCompNo(), maPmMstrDetailDTO.getPmId());	
		}
		else if("ASSET".equals(commRevCommonDTO.getRevisionObjType()))
		{
			// PM Update
			result += commRevDAO.updateAsset(objectId, compNo, "Y", "C");
			// 기존 PM 일정 생성
			MaPmMstrDetailDTO maPmMstrDetailDTO = setPmDetailDTO(commRevCommonDTO, user);
			maPmMstrDetailDAO.updateLastSchedDate(maPmMstrDetailDTO, user);
			maPmMstrDetailDAO.createPmSchedule(user.getCompNo(), maPmMstrDetailDTO.getPmId(), user.getEmpId());
			maPmMstrDetailDAO.createWorkOrder(user.getCompNo(), maPmMstrDetailDTO.getPmId());	
		}
		else if("STWRK".equals(commRevCommonDTO.getRevisionObjType()))
		{
			commRevDAO.updateStwrk(objectId, compNo, "Y", "C");
		}
		
		return result;
    }
	
	public String completeRevision(CommRevCommonDTO commRevCommonDTO, User user) throws Exception
    {
		int result = 0;
		
		String compNo = user.getCompNo();
		String oldObjectId = "";
		String newObjectId = commRevCommonDTO.getObjectId();
		
		// Revision History Update
		commRevCommonDTO.setRevisionStatusId("C");
		commRevCommonDTO.setRevisionStepStatusId("CMP");
		this.updateRevHist(commRevCommonDTO, user);
		
		if("PMSTD".equals(commRevCommonDTO.getRevisionObjType()))
		{
			MaPmMstrDetailDTO maPmMstrDetailDTO = setPmDetailDTO(commRevCommonDTO, user);
			
			List<Map<String, Object>> listMap = commRevDAO.findOldPm(commRevCommonDTO.getObjectNo(), compNo);
			
			oldObjectId =  listMap.get(0).get("OBJID").toString();
			String pmType =  listMap.get(0).get("PMTYPE").toString();
			
			// PM Update
			result += commRevDAO.updatePm(oldObjectId, compNo, "N", "C");
			result += commRevDAO.updatePm(newObjectId, compNo, "Y", "C");
			
			result += maPmMstrDetailDAO.deletePmScheduleAll(compNo, oldObjectId);
			// 기존 PM 일정 생성
			maPmMstrDetailDAO.updateLastSchedDate(maPmMstrDetailDTO, user);
			maPmMstrDetailDAO.createPmSchedule(user.getCompNo(), maPmMstrDetailDTO.getPmId(), user.getEmpId());
			maPmMstrDetailDAO.createWorkOrder(user.getCompNo(), maPmMstrDetailDTO.getPmId());	
		}
		else if("ASSET".equals(commRevCommonDTO.getRevisionObjType()))
		{
			oldObjectId = commRevDAO.findOldAsset(commRevCommonDTO.getObjectNo(), compNo);
			
			if("".equals(oldObjectId))
			{
				result += commRevDAO.updateAsset(oldObjectId, compNo, "N", "C");
				result += commRevDAO.updateAsset(newObjectId, compNo, "Y", "C");
			}
			else{
				String oldNewObjectId = commRevDAO.getNextSequence("SQAEQUIP_ID");
				//개정전 최신 설비
				// Revision History Update
				commRevDAO.updateEqIdRevHist(oldObjectId, oldNewObjectId, commRevCommonDTO.getObjectNo(), compNo);
				//개정전 최신 설비 설비번호를 새로운 설비번호로 변경
				result += commRevDAO.updateOldAsset(oldObjectId, oldNewObjectId, compNo, "N", "C");
				//개정전 최신 설비 탭페이지 설비번호 변경 (탭: 부위, 제원, 부품, 자산, 첨부문서)
				commRevDAO.updateEqIdAsmb(oldObjectId, oldNewObjectId, user);
				commRevDAO.updateEqIdSpec(oldObjectId, oldNewObjectId, user);
				commRevDAO.updateEqIdPart(oldObjectId, oldNewObjectId, user);
				commRevDAO.updateEqIdAsset(oldObjectId, oldNewObjectId, user);
				
                commRevDAO.updateEqIdTool(oldObjectId, oldNewObjectId, user);
                
				this.fileDocMove(oldObjectId, oldNewObjectId, "EQMSTR", user);
				//설비이력 설비번호 변경
				commRevDAO.updateEqIdEqHist(oldObjectId, oldNewObjectId, user);
				//이미지 복사
				this.fileImgMove(oldObjectId, oldNewObjectId, "EQMSTR", user);
				//fileImgCopy(oldNewObjectId, oldObjectId, user);
				maEqMstrListDAO.SP_IF_UPD_TXEQUIPMENT(oldNewObjectId, user);
				//결재정보 object_id(설비번호) 변경
				commRevDAO.updateApprObjectId(oldObjectId, oldNewObjectId, "EQUIPREV", compNo);
				
				//개정하는 설비
				// Revision History Update
				commRevDAO.updateEqIdRevHist(newObjectId, oldObjectId, commRevCommonDTO.getObjectNo(), compNo);
				//개정하는 설비 설비번호를 개정전 최신 설비번호로 변경
				result += commRevDAO.updateNewAsset(newObjectId, oldObjectId, compNo, "Y", "C");
				//개정하는 설비 탭페이지 설비번호 이번 최신 설비번호로 변경 (탭: 부위, 제원, 부품, 자산, 첨부문서)
				commRevDAO.updateEqIdAsmb(newObjectId, oldObjectId, user);
				commRevDAO.updateEqIdSpec(newObjectId, oldObjectId, user);
				commRevDAO.updateEqIdPart(newObjectId, oldObjectId, user);
				commRevDAO.updateEqIdAsset(newObjectId, oldObjectId, user);
				
				commRevDAO.updateEqIdTool(newObjectId, oldObjectId, user);
				
				this.fileDocMove(newObjectId, oldObjectId, "EQMSTR", user);
				//개정후 추가한 예방작업이 있다면 이동
				commRevDAO.updateEqIdPmEquip(newObjectId, oldObjectId, user);
				//설비이력 설비번호 변경
				commRevDAO.updateEqIdEqHist(newObjectId, oldObjectId, user);
				//이미지 복사
				//fileImgCopy(oldObjectId, newObjectId, user);
				this.fileImgMove(newObjectId, oldObjectId, "EQMSTR", user);
				maEqMstrListDAO.SP_IF_UPD_TXEQUIPMENT(newObjectId, user);
				//결재정보 object_id(설비번호) 변경
				commRevDAO.updateApprObjectId(newObjectId, oldObjectId, "EQUIPREV", compNo);
				//Audit Trail object_id(설비번호) 변경
				commRevDAO.updateAuditTrailObjectId(newObjectId, oldObjectId, compNo);
				
				
				// 개정중 상태 변경시 스케줄이 삭제되어야 함.
				// 개정완료시 점검스케줄 삭제 로직
				MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService)CommonUtil.getBean("maEqMstrDetailService", user);
				MaEqMstrCommonDTO  maEqmstrCommonDTO = new MaEqMstrCommonDTO();
				maEqmstrCommonDTO.setEquipId(oldObjectId); // 이게 새번호인가???
				
				MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailService.findDetail(maEqmstrCommonDTO, user);
				maEqMstrDetailDTO.setCompNo(user.getCompNo());
				
				//설비상태에 따라 스케줄 삭제 (해당 계측기도 설비상태에 따라 상태 변화 및 스케줄 삭제)
                maEqMstrDetailService.delPmSchByStatus(maEqMstrDetailDTO, user);
			}
		}
		else if("STWRK".equals(commRevCommonDTO.getRevisionObjType()))
		{
			oldObjectId = commRevDAO.findOldStwrk(commRevCommonDTO.getObjectNo(), compNo);
			
			commRevDAO.updateStwrk(oldObjectId, compNo, "N", "C");
			commRevDAO.updateStwrk(newObjectId, compNo, "Y", "C");
		}
		
		return oldObjectId;
    }
	
	@Override
	public int insertRevHist(CommRevCommonDTO commRevCommonDTO, User user) throws Exception
	{
	    return commRevDAO.insertRevHist(commRevCommonDTO, user);
	}
	
	@Override
	public int updateRevHist(CommRevCommonDTO commRevCommonDTO, User user) throws Exception
	{
	    return commRevDAO.updateRevHist(commRevCommonDTO, user);
	}
	
	@Override
	public String maxRevNo(CommRevCommonDTO commRevCommonDTO, User user)
	{
		return commRevDAO.maxRevNo(commRevCommonDTO, user);
	}
	
	public String validRevNo(CommRevCommonDTO commRevCommonDTO, User user){
		return commRevDAO.validRevNo(commRevCommonDTO, user);
	}
	
	public String validObjectNo(CommRevCommonDTO commRevCommonDTO, User user) throws Exception
	{
		String rtnValue = "";
		
		if("PMSTD".equals(commRevCommonDTO.getRevisionObjType()))
		{
		    rtnValue = commRevDAO.validPmNo(commRevCommonDTO, user);
		}
		else if("ASSET".equals(commRevCommonDTO.getRevisionObjType()))
		{
			MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService) CommonUtil.getBean("maEqMstrDetailService", user);
            MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();
            maEqMstrDetailDTO.setItemNo(commRevCommonDTO.getObjectNo());
            rtnValue = maEqMstrDetailService.validItemNo(maEqMstrDetailDTO, user);
		}
		else if("STWRK".equals(commRevCommonDTO.getRevisionObjType()))
		{
			rtnValue = commRevDAO.validStwrkNo(commRevCommonDTO, user);
		}
		
		return rtnValue;
	}
	
	protected MaPmMstrDetailDTO setPmDetailDTO(CommRevCommonDTO commRevCommonDTO, User user)
	{
		MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
		
		maPmMstrDetailDTO.setCompNo(user.getCompNo());
		maPmMstrDetailDTO.setPmId(commRevCommonDTO.getObjectId());
		maPmMstrDetailDTO.setEnterBy(commRevCommonDTO.getEnterBy());
		
		return maPmMstrDetailDTO;
	}
	public String selectCustomObjectNo(CommRevCommonDTO commRevCommonDTO, User user)
	{
		return commRevCommonDTO.getObjectId();
	}
	
	public void fileImgCopy(String newObjectId, String oldObjectId, String objectType, User user) throws InvalidKeyException, URISyntaxException, StorageException
    {
       List imgList = maEqMstrListDAO.findImgList(oldObjectId, objectType, user);
       
       for(int i = 0; imgList.size() > i ; i++)
       {
           Map imageMap = (Map)imgList.get(i);
           maEqMstrListDAO.insertImage(newObjectId,imageMap, user); //헤더 insert
           
           String oldImageId = String.valueOf(imageMap.get("IMAGE_ID"));
           String newImageId = String.valueOf(imageMap.get("NEW_IMAGE_ID"));
           List imgFileList = maEqMstrListDAO.findImgFileList(oldImageId, user);
           
           for(int j = 0; imgFileList.size() > j ; j++)
           {
        	   Map imageFileMap = (Map)imgFileList.get(j);
        	   
        	   maEqMstrListDAO.insertImageFile(newImageId, imageFileMap, user);//detail insert
        	   
        	   String oldFileNo = String.valueOf(imageFileMap.get("IMGDATA_ID"));
               String newFileNo = String.valueOf(imageFileMap.get("NEW_IMGDATA_ID"));
               String filePath = String.valueOf(imageFileMap.get("NF_FILE_PATH"));

               String originFile = MwareConfig.getFileDir() + filePath + File.separator + oldFileNo;
               String targetFile = MwareConfig.getFileDir() + filePath + File.separator + newFileNo;

               try
               { // file copy
                   FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getFileDir() + filePath + File.separator);
               }catch (IOException e){}
        	   
           }
           
       }
    }
	public void fileImgMove(String oldObjectId, String newObjectId, String objectType, User user){
		commRevDAO.fileImgMove(oldObjectId, newObjectId, objectType, user);
	}
	
	public void fileDocCopy(String newObjectId, String oldObjectId, String objectType, User user) throws InvalidKeyException, URISyntaxException, StorageException
	{
		List docList = maEqMstrListDAO.findDocList(oldObjectId, objectType, user);
		
		for(int i = 0; docList.size() > i ; i++)
		{
			Map docMap = (Map)docList.get(i);
			maEqMstrListDAO.insertDoc(newObjectId, docMap, user); //헤더 insert
			
			String oldObjDocId = String.valueOf(docMap.get("OBJDOC_ID"));
			String newObjDocId = String.valueOf(docMap.get("NEW_OBJDOC_ID"));
			List docFileList = maEqMstrListDAO.findDocFileList(oldObjDocId, user);
			
			for(int j = 0; docFileList.size() > j ; j++)
			{
				Map docFileMap = (Map)docFileList.get(j);
				
				maEqMstrListDAO.insertDocFile(newObjDocId, docFileMap, user);//detail insert
				
				String oldFileNo = String.valueOf(docFileMap.get("DOCDATA_ID"));
				String newFileNo = String.valueOf(docFileMap.get("NEW_DOCDATA_ID"));
				String filePath = String.valueOf(docFileMap.get("NF_FILE_PATH"));


				String originFile = MwareConfig.getFileDir() + filePath + File.separator + oldFileNo;
				String targetFile = MwareConfig.getFileDir() + filePath + File.separator + newFileNo;
				
				try
				{ // file copy
					FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getFileDir() + filePath + File.separator);
				}catch (IOException e){}
				
			}
			
		}
	}
	public void fileDocMove(String oldObjectId, String newObjectId, String objectType, User user){
		commRevDAO.fileDocMove(oldObjectId, newObjectId, objectType, user);
	}

    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user, String revStatus, String objectType)
    {
		CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
		commRevCommonDTO.setObjectId(appReqDetailDTO.getObjectId());
		commRevCommonDTO.setRevisionObjType(objectType);
		
        if("PMSTD".equals(objectType))
		{
        	MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
        	maPmMstrCommonDTO.setPmId(appReqDetailDTO.getObjectId());
	        MaPmMstrDetailDTO maPmMstrDetailDTO = maPmMstrDetailDAO.findDetail(maPmMstrCommonDTO, user);
	        commRevCommonDTO.setObjectNo(maPmMstrDetailDTO.getPmNo());
	        commRevCommonDTO.setOldObjectId(maPmMstrDetailDTO.getPmId());
	        commRevCommonDTO.setRevisionhistId(maPmMstrDetailDTO.getRevisionhistId());
		}
        else if("ASSET".equals(objectType))
		{
        	MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailDAO.findDetail(appReqDetailDTO.getObjectId(), user);
        	commRevCommonDTO.setObjectNo(maEqMstrDetailDTO.getItemNo());
        	commRevCommonDTO.setOldObjectId(maEqMstrDetailDTO.getEquipId());
        	commRevCommonDTO.setRevisionhistId(maEqMstrDetailDTO.getRevisionhistId());
        	commRevCommonDTO.setEqCtgTypeId(maEqMstrDetailDTO.getEqCtgTypeId());
		}
        
		commRevCommonDTO.setRevisionObjType(objectType);

		String revStat = commRevDAO.findRevStatus(commRevCommonDTO, user); 
        if("C".equals(revStatus))
        {
            try {
            	if("W".equals(revStat))			//W    제정중
            	{
            		this.completeRegislate(commRevCommonDTO, user);
            	}
            	else if("P".equals(revStat))	//P    개정중
            	{
            		this.completeRevision(commRevCommonDTO, user);
            	}
			} catch (Exception e) { }
        	
        }
        else{
        	if("PMSTD".equals(commRevCommonDTO.getRevisionObjType()))
    		{
        		commRevDAO.updatePm(commRevCommonDTO.getOldObjectId(), user.getCompNo(), "N", revStatus);
    		}
    		else if("ASSET".equals(commRevCommonDTO.getRevisionObjType()))
    		{
    			commRevDAO.updateAsset(commRevCommonDTO.getOldObjectId(), user.getCompNo(), "N", revStatus);
    		}
        }
        
    }
    @Override
    public String findEqDesc(CommRevCommonDTO commRevCommonDTO, User user)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
