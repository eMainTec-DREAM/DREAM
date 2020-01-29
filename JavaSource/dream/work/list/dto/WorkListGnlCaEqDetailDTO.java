package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업상세  - 검교정 - 표준기 상세 DTO
 * @author  kim21017
 * @version $Id: WorkListCavalDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListGnlCaEqDetailDTO extends BaseDTO
{
	/** 차기교정일 */
	private String nextPlanDate = "";
	/** 일련번호(Serial #) */
	private String serialNo = "";
	/** 표준기명(설비명) */
	private String description = "";
	/** 표준기# */
	private String equipId = "";
	/** 교정성적서# */
	private String woNo = "";
	/** 표준기 최근 WO ID */
	private String calibWkorId = "";
	/** 작업ID */
	private String wkOrId = "";
	/** 검교정작업표준교정기ID */
	private String wocalibstdeqId = "";
	/** 회사코드 */
	private String compNo = "";
	
	private String itemNo	= "";
	/** SOP No */
	private String calibSopdocNo		= "";
	private String calibSopdocNoDesc	= "";
	
	public String getCalibSopdocNo() {
		return calibSopdocNo;
	}
	public void setCalibSopdocNo(String calibSopdocNo) {
		this.calibSopdocNo = calibSopdocNo;
	}
	public String getCalibSopdocNoDesc() {
		return calibSopdocNoDesc;
	}
	public void setCalibSopdocNoDesc(String calibSopdocNoDesc) {
		this.calibSopdocNoDesc = calibSopdocNoDesc;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getNextPlanDate() {
		return nextPlanDate;
	}
	public void setNextPlanDate(String nextPlanDate) {
		this.nextPlanDate = CommonUtil.convertDate(nextPlanDate);
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getWoNo() {
		return woNo;
	}
	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	public String getCalibWkorId() {
		return calibWkorId;
	}
	public void setCalibWkorId(String calibWkorId) {
		this.calibWkorId = calibWkorId;
	}
	
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
	public String getWocalibstdeqId() {
		return wocalibstdeqId;
	}
	public void setWocalibstdeqId(String wocalibstdeqId) {
		this.wocalibstdeqId = wocalibstdeqId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	
	
}