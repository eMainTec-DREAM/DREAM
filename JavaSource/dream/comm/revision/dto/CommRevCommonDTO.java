package dream.comm.revision.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  jung7126
 * @version $Id: CommRevPmDTO.java,v 1.0 2015/12/02 08:44:16 jung7126 Exp $
 * @since   1.0
 *
 */
public class CommRevCommonDTO extends BaseDTO
{
    /** 예방작업ID */
    private String objectId = "";
    /** 예방작업번호 */
    private String objectNo = "";
    /** RevisionHist ID */
    private String revisionhistId = "";
    /** 예방작업명 */
    private String description = "";
    /** 제정/개정구분 ID */
    private String revisionType = "";
    /** 제정/개정구분 DESC */
    private String revisionTypeDesc = "";
    /** Revision 업무유형 ID */
    private String revisionObjType = "";
    /** Revision 업무유형 DESC */
    private String revisionObjTypeDesc = "";
    /** 진행상태 ID */
    private String revisionStatusId = "";
    /** 진행상태 DESC */
    private String revisionStatusDesc = "";
    /** 제정 담당부서 ID */
    private String wrkDeptId = "";
    /** 제정 담당부서 DESC */
    private String wrkDeptDesc = "";
    /** 제정 담당자 ID */
    private String wrkEmpId = "";
    /** 제정 담당자 DESC */
    private String wrkEmpDesc = "";
    /** 문서번호 */
    private String docNo = "";
    /** Revision 번호 */
    private String revNo = "";
    /** 제정일자 */
    private String wrkDate = "";
    /** 제정사항 */
    private String revDesc = "";
    
    /** 연결 PageId */
    private String param = "";
    
    /** 이전 예방작업ID */
    private String oldObjectId = "";
    /** 이전 RevisionHist ID */
    private String oldRevisionhistId = "";
    /** 이전 Revision 번호 */
    private String oldRevNo = "";
    
    /** 작업종류 */
    private String selectedWoType = "";
    /** 작업형태 */
    private String selectedPmType = "";
    
    private String eqCtgTypeId 	= ""; 
    
    private String eqCtgId 		= ""; 
    
    private String eqCtgDesc 	= ""; 
    
    private String eqCtgIsLowestLvl 	= ""; 
    
    /** 계측기 부모설비 id  */
    private String parentEquipId 		= "";
    /** 계측기 부모설비명  */
    private String parentEquipDesc		= "";
    /** 계측기 부모설비 위치 id  */
    private String parentEqLocId 		= "";
    /** 작성상태 ID */
    private String revisionStepStatusId = "";
    /** 작성상태 DESC */
    private String revisionStepStatusDesc = "";
    /** 공장코드 */
    private String plantId              = "";
    /** 페이지 파일명 */
    private String fileName             = "";
    /** 계측기 게이지 종류 */
    private String guageType            = "";
    /** 사용자 사원ID */
    private String usageEmpId           = "";
    /** 사용자 사원명 */
    private String usageEmpName         = "";
    
    
    public String getUsageEmpId()
    {
        return usageEmpId;
    }
    public void setUsageEmpId(String usageEmpId)
    {
        this.usageEmpId = usageEmpId;
    }
    public String getUsageEmpName()
    {
        return usageEmpName;
    }
    public void setUsageEmpName(String usageEmpName)
    {
        this.usageEmpName = usageEmpName;
    }
    public String getGuageType()
    {
        return guageType;
    }
    public void setGuageType(String guageType)
    {
        this.guageType = guageType;
    }
    public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getRevisionStepStatusId() {
		return revisionStepStatusId;
	}
	public void setRevisionStepStatusId(String revisionStepStatusId) {
		this.revisionStepStatusId = revisionStepStatusId;
	}
	public String getRevisionStepStatusDesc() {
		return revisionStepStatusDesc;
	}
	public void setRevisionStepStatusDesc(String revisionStepStatusDesc) {
		this.revisionStepStatusDesc = revisionStepStatusDesc;
	}
	public String getParentEqLocId() {
		return parentEqLocId;
	}
	public void setParentEqLocId(String parentEqLocId) {
		this.parentEqLocId = parentEqLocId;
	}
	public String getParentEquipDesc() {
		return parentEquipDesc;
	}
	public void setParentEquipDesc(String parentEquipDesc) {
		this.parentEquipDesc = parentEquipDesc;
	}
	public String getParentEquipId() {
		return parentEquipId;
	}
	public void setParentEquipId(String parentEquipId) {
		this.parentEquipId = parentEquipId;
	}
	public String getEqCtgIsLowestLvl() {
		return eqCtgIsLowestLvl;
	}
	public void setEqCtgIsLowestLvl(String eqCtgIsLowestLvl) {
		this.eqCtgIsLowestLvl = eqCtgIsLowestLvl;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
    public String getEqCtgTypeId() {
		return eqCtgTypeId;
	}
	public void setEqCtgTypeId(String eqCtgTypeId) {
		this.eqCtgTypeId = eqCtgTypeId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	public String getOldObjectId() {
		return oldObjectId;
	}
	public void setOldObjectId(String oldObjectId) {
		this.oldObjectId = oldObjectId;
	}
	public String getOldRevNo() {
		return oldRevNo;
	}
	public void setOldRevNo(String oldRevNo) {
		this.oldRevNo = oldRevNo;
	}
	public String getOldRevisionhistId() {
		return oldRevisionhistId;
	}
	public void setOldRevisionhistId(String oldRevisionhistId) {
		this.oldRevisionhistId = oldRevisionhistId;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getSelectedWoType() {
		return selectedWoType;
	}
	public void setSelectedWoType(String selectedWoType) {
		this.selectedWoType = selectedWoType;
	}
	public String getSelectedPmType() {
		return selectedPmType;
	}
	public void setSelectedPmType(String selectedPmType) {
		this.selectedPmType = selectedPmType;
	}
	public String getRevisionhistId() {
		return revisionhistId;
	}
	public void setRevisionhistId(String revisionhistId) {
		this.revisionhistId = revisionhistId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRevisionType() {
		return revisionType;
	}
	public void setRevisionType(String revisionType) {
		this.revisionType = revisionType;
	}
	public String getRevisionTypeDesc() {
		return revisionTypeDesc;
	}
	public void setRevisionTypeDesc(String revisionTypeDesc) {
		this.revisionTypeDesc = revisionTypeDesc;
	}
	public String getRevisionObjType() {
		return revisionObjType;
	}
	public void setRevisionObjType(String revisionObjType) {
		this.revisionObjType = revisionObjType;
	}
	public String getRevisionObjTypeDesc() {
		return revisionObjTypeDesc;
	}
	public void setRevisionObjTypeDesc(String revisionObjTypeDesc) {
		this.revisionObjTypeDesc = revisionObjTypeDesc;
	}
	public String getRevisionStatusId() {
		return revisionStatusId;
	}
	public void setRevisionStatusId(String revisionStatusId) {
		this.revisionStatusId = revisionStatusId;
	}
	public String getRevisionStatusDesc() {
		return revisionStatusDesc;
	}
	public void setRevisionStatusDesc(String revisionStatusDesc) {
		this.revisionStatusDesc = revisionStatusDesc;
	}
	public String getWrkDeptId() {
		return wrkDeptId;
	}
	public void setWrkDeptId(String wrkDeptId) {
		this.wrkDeptId = wrkDeptId;
	}
	public String getWrkDeptDesc() {
		return wrkDeptDesc;
	}
	public void setWrkDeptDesc(String wrkDeptDesc) {
		this.wrkDeptDesc = wrkDeptDesc;
	}
	public String getWrkEmpId() {
		return wrkEmpId;
	}
	public void setWrkEmpId(String wrkEmpId) {
		this.wrkEmpId = wrkEmpId;
	}
	public String getWrkEmpDesc() {
		return wrkEmpDesc;
	}
	public void setWrkEmpDesc(String wrkEmpDesc) {
		this.wrkEmpDesc = wrkEmpDesc;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getRevNo() {
		return revNo;
	}
	public void setRevNo(String revNo) {
		this.revNo = CommonUtil.convertMoney(revNo);
	}
	public String getWrkDate() {
		return wrkDate;
	}
	public void setWrkDate(String wrkDate) {
		this.wrkDate = CommonUtil.convertDate(wrkDate);
	}
	public String getRevDesc() {
		return revDesc;
	}
	public void setRevDesc(String revDesc) {
		this.revDesc = revDesc;
	}
    
    
}
