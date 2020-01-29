package dream.work.list.energy.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 에너지 값 측정항목 상세 DTO
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointDetailDTO.java,v 1.1 2015/12/04 09:10:45 sy.yang Exp $
 * @since   1.0
 */
public class WorkListEnergyPointDetailDTO extends BaseDTO
{
	/** 점검작업 점검 id */
	private String pmInsPointId				= "";
	/** 점검항목ID */
	private String pmPointId				= "";
	
	/** 측정순서 */
	private String stepNum 					= "";
	/** 측정분류항목 ID */
    private String stdCheckPointId     		= "";    
    /** 측정분류항목 DESC */
    private String stdCheckPointDesc   		= "";   
	/** 측정항목 */
	private String checkPoint 				= "";
	/** 단위 */
	private String uom 						= "";
	/** 측정값 */
	private String resultValue 				= "";
	/** 누적값여부 */
	private String isRunValue 				= "";
	/** 이전측정값 */
	private String preResultValue 			= "";
	/** 사용값 */
	private String calValue 				= "";
	/** 비고 */
	private String remark 					= "";
	
	
    public String getPmInsPointId() {
		return pmInsPointId;
	}
	public void setPmInsPointId(String pmInsPointId) {
		this.pmInsPointId = pmInsPointId;
		super.setAuditKey(pmInsPointId);
	}
	public String getPmPointId() {
		return pmPointId;
	}
	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}
	public String getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResultValue() {
		return resultValue;
	}
	public void setResultValue(String resultValue) {
		this.resultValue = CommonUtil.convertMoney(resultValue);
	}
	public String getStdCheckPointId() {
		return stdCheckPointId;
	}
	public void setStdCheckPointId(String stdCheckPointId) {
		this.stdCheckPointId = stdCheckPointId;
	}
	public String getStdCheckPointDesc() {
		return stdCheckPointDesc;
	}
	public void setStdCheckPointDesc(String stdCheckPointDesc) {
		this.stdCheckPointDesc = stdCheckPointDesc;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getIsRunValue() {
		return isRunValue;
	}
	public void setIsRunValue(String isRunValue) {
		this.isRunValue = isRunValue;
	}
	public String getPreResultValue() {
		return preResultValue;
	}
	public void setPreResultValue(String preResultValue) {
		this.preResultValue = preResultValue;
	}
	public String getCalValue() {
		return calValue;
	}
	public void setCalValue(String calValue) {
		this.calValue = calValue;
	}
	
}