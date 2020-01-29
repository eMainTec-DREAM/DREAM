package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사용량 항목 - Detail DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkPmPointUInsDetailDTO extends BaseDTO
{
    /** 점검항목ID */
    private String pmPointId				= "";
	/** 점검항목 */
	private String checkPoint 				= "";
	/** 사용여부 */
	private String isActive                 = "";
    /** 누적값 여부 */
	private String isRunValue				= "";
	/** 배율 */
	private String fitRate					= "";
	/** 점검순서 */
	private String stepNum					= "";
	/** 단위 */
	private String uom						= "";
	/** 비고 */
	private String remark					= "";
	/** 점검ID */
	private String pmId						= "";
	/** 측정분류항목 ID */
	private String stdCheckPointId			= "";
	/** 측정분류항목명 */
	private String stdCheckPointDesc		= "";
	
	public String getStdCheckPointDesc() {
		return stdCheckPointDesc;
	}

	public void setStdCheckPointDesc(String stdCheckPointDesc) {
		this.stdCheckPointDesc = stdCheckPointDesc;
	}

	public String getStdCheckPointId() {
		return stdCheckPointId;
	}

	public void setStdCheckPointId(String stdCheckPointId) {
		this.stdCheckPointId = stdCheckPointId;
	}

	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}

	public String getStepNum() {
		return stepNum;
	}

	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCheckPoint() {
		return checkPoint;
	}

	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsRunValue() {
		return isRunValue;
	}

	public void setIsRunValue(String isRunValue) {
		this.isRunValue = isRunValue;
	}

	public String getFitRate() {
		return fitRate;
	}

	public void setFitRate(String fitRate) {
		this.fitRate = CommonUtil.convertMoney(fitRate);
	}

	public String getPmPointId() {
		return pmPointId;
	}

	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
	}
	
}
