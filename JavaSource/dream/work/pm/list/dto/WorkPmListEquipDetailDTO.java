package dream.work.pm.list.dto;

import java.util.List;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방설비 상세 DTO
 * @author  jung7126
 * @version $Id: WorkPmListEquipDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class WorkPmListEquipDetailDTO extends BaseDTO
{
	/** 예방작업설비ID */
	private String pmEquipId			= "";
	/** 설비id*/
	private String equipId				= "";
	/** 설비명*/
	private String equipDesc			= "";
	/** 부위id*/
    private String eqAsmbId              = "";
    /** 부위명*/
    private String eqAsmbDesc            = "";
	/** 설비위치명 */
	private String eqLocDesc			= "";
	/** 작업부위 */
	private String workPart				= "";
	/** 최초작업예정일 */
	private String initWrkDate			= "";
	/** 순서 */
	private String stepNum            = "";
	/** Desc */
	private String description         = "";
	/** 사용여부 */
	private String isActive             = "";
	/** 비고 */
	private String remark               = "";
	/** 비고 */
	private String lastSchDate               = "";
	/** 비고 */
	private String nextSchDate               = "";
	/** 비고 */
	private String lastCpltDate               = "";
	/** 비고 */
	private String nextPlanDate               = "";
	/** 비고 */
	private String oldInitWrkDate               = "";
	
	/** 설비종류 */
    private String eqctgType       	= "";
	
    /** 설비번호 */
    private String itemNo	        	= "";
    /** 상위설비번호 */
    private String pitemNo	        	= "";
    /** 상위설비 Desc */
    private String pequipDesc       	= "";
    /** 상위설비사용부서 Desc */
    private String pequipUsaDeptDesc	= "";
    
    private List slideFileList      = null;
    
	public String getEqctgType() {
		return eqctgType;
	}
	public List getSlideFileList() {
		return slideFileList;
	}
	public void setSlideFileList(List slideFileList) {
		this.slideFileList = slideFileList;
	}
	public String getPitemNo() {
		return pitemNo;
	}
	public void setPitemNo(String pitemNo) {
		this.pitemNo = pitemNo;
	}
	public String getPequipDesc() {
		return pequipDesc;
	}
	public void setPequipDesc(String pequipDesc) {
		this.pequipDesc = pequipDesc;
	}
	public String getPequipUsaDeptDesc() {
		return pequipUsaDeptDesc;
	}
	public void setPequipUsaDeptDesc(String pequipUsaDeptDesc) {
		this.pequipUsaDeptDesc = pequipUsaDeptDesc;
	}
	public void setEqctgType(String eqctgType) {
		this.eqctgType = eqctgType;
	}
	public String getEqAsmbId()
    {
        return eqAsmbId;
    }
    public void setEqAsmbId(String eqAsmbId)
    {
        this.eqAsmbId = eqAsmbId;
    }
    public String getEqAsmbDesc()
    {
        return eqAsmbDesc;
    }
    public void setEqAsmbDesc(String eqAsmbDesc)
    {
        this.eqAsmbDesc = eqAsmbDesc;
    }
    public String getLastSchDate() {
		return lastSchDate;
	}
	public void setLastSchDate(String lastSchDate) {
		this.lastSchDate = lastSchDate;
	}
	public String getNextSchDate() {
		return nextSchDate;
	}
	public void setNextSchDate(String nextSchDate) {
		this.nextSchDate = nextSchDate;
	}
	public String getLastCpltDate() {
		return lastCpltDate;
	}
	public void setLastCpltDate(String lastCpltDate) {
		this.lastCpltDate = lastCpltDate;
	}
	public String getNextPlanDate() {
		return nextPlanDate;
	}
	public void setNextPlanDate(String nextPlanDate) {
		this.nextPlanDate = nextPlanDate;
	}
	public String getOldInitWrkDate() {
		return oldInitWrkDate;
	}
	public void setOldInitWrkDate(String oldInitWrkDate) {
		this.oldInitWrkDate = oldInitWrkDate;
	}
	public String getStepNum()
    {
        return stepNum;
    }
    public void setStepNum(String stepNum)
    {
        this.stepNum = stepNum;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getIsActive()
    {
        return isActive;
    }
    public void setIsActive(String isActive)
    {
        this.isActive = isActive;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getInitWrkDate() {
		return initWrkDate;
	}
	public void setInitWrkDate(String initWrkDate) {
		this.initWrkDate = CommonUtil.convertDate(initWrkDate);
	}
	public String getWorkPart() {
		return workPart;
	}
	public void setWorkPart(String workPart) {
		this.workPart = workPart;
	}
	public String getPmEquipId() {
		return pmEquipId;
	}
	public void setPmEquipId(String pmEquipId) {
		this.pmEquipId = pmEquipId;
		super.setAuditKey(pmEquipId);
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
}