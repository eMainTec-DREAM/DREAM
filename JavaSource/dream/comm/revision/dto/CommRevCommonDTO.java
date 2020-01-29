package dream.comm.revision.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  jung7126
 * @version $Id: CommRevPmDTO.java,v 1.0 2015/12/02 08:44:16 jung7126 Exp $
 * @since   1.0
 *
 */
public class CommRevCommonDTO extends BaseDTO
{
    /** �����۾�ID */
    private String objectId = "";
    /** �����۾���ȣ */
    private String objectNo = "";
    /** RevisionHist ID */
    private String revisionhistId = "";
    /** �����۾��� */
    private String description = "";
    /** ����/�������� ID */
    private String revisionType = "";
    /** ����/�������� DESC */
    private String revisionTypeDesc = "";
    /** Revision �������� ID */
    private String revisionObjType = "";
    /** Revision �������� DESC */
    private String revisionObjTypeDesc = "";
    /** ������� ID */
    private String revisionStatusId = "";
    /** ������� DESC */
    private String revisionStatusDesc = "";
    /** ���� ���μ� ID */
    private String wrkDeptId = "";
    /** ���� ���μ� DESC */
    private String wrkDeptDesc = "";
    /** ���� ����� ID */
    private String wrkEmpId = "";
    /** ���� ����� DESC */
    private String wrkEmpDesc = "";
    /** ������ȣ */
    private String docNo = "";
    /** Revision ��ȣ */
    private String revNo = "";
    /** �������� */
    private String wrkDate = "";
    /** �������� */
    private String revDesc = "";
    
    /** ���� PageId */
    private String param = "";
    
    /** ���� �����۾�ID */
    private String oldObjectId = "";
    /** ���� RevisionHist ID */
    private String oldRevisionhistId = "";
    /** ���� Revision ��ȣ */
    private String oldRevNo = "";
    
    /** �۾����� */
    private String selectedWoType = "";
    /** �۾����� */
    private String selectedPmType = "";
    
    private String eqCtgTypeId 	= ""; 
    
    private String eqCtgId 		= ""; 
    
    private String eqCtgDesc 	= ""; 
    
    private String eqCtgIsLowestLvl 	= ""; 
    
    /** ������ �θ𼳺� id  */
    private String parentEquipId 		= "";
    /** ������ �θ𼳺��  */
    private String parentEquipDesc		= "";
    /** ������ �θ𼳺� ��ġ id  */
    private String parentEqLocId 		= "";
    /** �ۼ����� ID */
    private String revisionStepStatusId = "";
    /** �ۼ����� DESC */
    private String revisionStepStatusDesc = "";
    /** �����ڵ� */
    private String plantId              = "";
    /** ������ ���ϸ� */
    private String fileName             = "";
    /** ������ ������ ���� */
    private String guageType            = "";
    /** ����� ���ID */
    private String usageEmpId           = "";
    /** ����� ����� */
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
