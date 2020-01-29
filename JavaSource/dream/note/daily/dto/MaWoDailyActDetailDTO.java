package dream.note.daily.dto;

import common.bean.BaseDTO;

/**
 * 공통 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaWoDailyActDetailDTO extends BaseDTO
{
    /** 일일작업 Main Act ID */
    private String woDayActId       = "";
	/** 작업자id*/
	private String empId			= "";
	/** 작업자No*/
	private String empNo			= "";
	/** 작업자명 */
	private String empDesc			= "";
	/** equip name */
	private String equipDesc		= "";
	/** action of contents */
	private String actContents		= "";
	/** action */
	private String action			= "";
	/** 정렬순서 */
	private String ordNo			= "";
	
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getWoDayActId() {
		return woDayActId;
	}
	public void setWoDayActId(String woDayActId) {
		this.woDayActId = woDayActId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getActContents() {
		return actContents;
	}
	public void setActContents(String actContents) {
		this.actContents = actContents;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
