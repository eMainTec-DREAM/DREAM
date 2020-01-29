package dream.note.daily.dto;

import common.bean.BaseDTO;

/**
 * ���� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaWoDailyActDetailDTO extends BaseDTO
{
    /** �����۾� Main Act ID */
    private String woDayActId       = "";
	/** �۾���id*/
	private String empId			= "";
	/** �۾���No*/
	private String empNo			= "";
	/** �۾��ڸ� */
	private String empDesc			= "";
	/** equip name */
	private String equipDesc		= "";
	/** action of contents */
	private String actContents		= "";
	/** action */
	private String action			= "";
	/** ���ļ��� */
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
