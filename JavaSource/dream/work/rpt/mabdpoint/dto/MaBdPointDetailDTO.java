package dream.work.rpt.mabdpoint.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 이상점검조치 - 상세 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaBdPointDetailDTO extends BaseDTO
{
	/** ID */
	private String woNgPointId			= "";
	/** 조치방법 */
	private String pmRepMethodType		= "";
	/** 점검일자 */
	private String inspectDate			= "";
	/** 담당부서 */
	private String deptDesc				= "";
	/** 설비위치 ID */
	private String eqLocId				= "";
	/** 설비위치 */
	private String eqLocDesc			= "";
	/** 작업자 */
	private String woCraft				= "";
	/** 설비 */
	private String equipDesc			= "";
	/** 설비코드 */
	private String equipNo			    = "";
	/** 점검부위 */
	private String asmbDesc				= "";
	/** 점검항목 */
	private String checkPoint			= "";
	/** 점검방법 */
	private String checkMethod			= "";
	/** 적정기준 */
	private String fitBasis				= "";
	/** 수치/판정구분 */
	private String checkType			= "";
	/** 설정값(상한/하한/기준)/단위 */
	private String checkUom				= "";
	/** 점검값 */
	private String resultValue			= "";
	/** 점검결과 */
	private String pmPointRsltStatusDesc= "";
	/** 조치WoNo */
	private String inspectWoNo			= "";
	/** 점검세부내용 */
	private String inspectRemark		= "";
	/** 조치결과 */
	private String pmPointRepStatus		= "";
	/** 조치결과명 */
	private String pmPointRepStatusDesc	= "";
	/** 조치내용 */
	private String repairDesc			= "";
	/** 조치일자 */
	private String repairDate			= "";
	/** 조치자 */
	private String repairBy				= "";
	/** 조치부서명 */
	private String repairDept			= "";
	/** 조치자명 */
	private String repairName			= "";
	
	/** pmType */
    private String pmType          = "";
    /** woType */
    private String woType          = "";
    /** pmiType */
    private String pmiType          = "";
    /** equipId */
    private String equipId          = "";
    /** pmWkorId */
    private String pmWkorId          = "";
    
    /** param1 */
    private String param1          = "";
    
    /** 작업요청서 ID */
    private String woreqId        = "";
    /** 작업계획서 ID */
    private String woplanId       = "";
    /** woequip ID */
    private String woequipId      = "";
    /** 첨부파일 ID */
    private String attachId		  = "";
    
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getEquipNo()
    {
        return equipNo;
    }
    public void setEquipNo(String equipNo)
    {
        this.equipNo = equipNo;
    }
    public String getRepairDept() {
		return repairDept;
	}
	public String getWoequipId() {
		return woequipId;
	}
	public void setWoequipId(String woequipId) {
		this.woequipId = woequipId;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getWoreqId() {
		return woreqId;
	}
	public void setWoreqId(String woreqId) {
		this.woreqId = woreqId;
	}
	public String getWoplanId() {
		return woplanId;
	}
	public void setWoplanId(String woplanId) {
		this.woplanId = woplanId;
	}
	public void setRepairDept(String repairDept) {
		this.repairDept = repairDept;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getPmWkorId()
    {
        return pmWkorId;
    }
    public void setPmWkorId(String pmWkorId)
    {
        this.pmWkorId = pmWkorId;
    }
    public String getPmiType()
    {
        return pmiType;
    }
    public void setPmiType(String pmiType)
    {
        this.pmiType = pmiType;
    }
    public String getPmType()
    {
        return pmType;
    }
    public void setPmType(String pmType)
    {
        this.pmType = pmType;
    }
    public String getWoType()
    {
        return woType;
    }
    public void setWoType(String woType)
    {
        this.woType = woType;
    }
    public String getWoNgPointId() {
		return woNgPointId;
	}
	public void setWoNgPointId(String woNgPointId) {
		this.woNgPointId = woNgPointId;
		super.setAuditKey(woNgPointId);
	}
	public String getPmRepMethodType() {
		return pmRepMethodType;
	}
	public void setPmRepMethodType(String pmRepMethodType) {
		this.pmRepMethodType = pmRepMethodType;
	}
	public String getInspectDate() {
		return inspectDate;
	}
	public void setInspectDate(String inspectDate) {
		this.inspectDate = CommonUtil.convertDate(inspectDate);
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getWoCraft() {
		return woCraft;
	}
	public void setWoCraft(String woCraft) {
		this.woCraft = woCraft;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getAsmbDesc() {
		return asmbDesc;
	}
	public void setAsmbDesc(String asmbDesc) {
		this.asmbDesc = asmbDesc;
	}
	public String getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	public String getFitBasis() {
		return fitBasis;
	}
	public void setFitBasis(String fitBasis) {
		this.fitBasis = fitBasis;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getCheckUom() {
		return checkUom;
	}
	public void setCheckUom(String checkUom) {
		this.checkUom = checkUom;
	}
	public String getResultValue() {
		return resultValue;
	}
	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}
	public String getPmPointRsltStatusDesc() {
		return pmPointRsltStatusDesc;
	}
	public void setPmPointRsltStatusDesc(String pmPointRsltStatusDesc) {
		this.pmPointRsltStatusDesc = pmPointRsltStatusDesc;
	}
	public String getInspectWoNo() {
		return inspectWoNo;
	}
	public void setInspectWoNo(String inspectWoNo) {
		this.inspectWoNo = inspectWoNo;
	}
	public String getInspectRemark() {
		return inspectRemark;
	}
	public void setInspectRemark(String inspectRemark) {
		this.inspectRemark = inspectRemark;
	}
	public String getPmPointRepStatus() {
		return pmPointRepStatus;
	}
	public void setPmPointRepStatus(String pmPointRepStatus) {
		this.pmPointRepStatus = pmPointRepStatus;
	}
	public String getPmPointRepStatusDesc() {
		return pmPointRepStatusDesc;
	}
	public void setPmPointRepStatusDesc(String pmPointRepStatusDesc) {
		this.pmPointRepStatusDesc = pmPointRepStatusDesc;
	}
	public String getRepairDesc() {
		return repairDesc;
	}
	public void setRepairDesc(String repairDesc) {
		this.repairDesc = repairDesc;
	}
	public String getRepairDate() {
		return repairDate;
	}
	public void setRepairDate(String repairDate) {
		this.repairDate = CommonUtil.convertDate(repairDate);
	}
	public String getRepairBy() {
		return repairBy;
	}
	public void setRepairBy(String repairBy) {
		this.repairBy = repairBy;
	}
	public String getRepairName() {
		return repairName;
	}
	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
}
