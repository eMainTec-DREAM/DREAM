package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��뷮 �׸� - Detail DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkPmPointUInsDetailDTO extends BaseDTO
{
    /** �����׸�ID */
    private String pmPointId				= "";
	/** �����׸� */
	private String checkPoint 				= "";
	/** ��뿩�� */
	private String isActive                 = "";
    /** ������ ���� */
	private String isRunValue				= "";
	/** ���� */
	private String fitRate					= "";
	/** ���˼��� */
	private String stepNum					= "";
	/** ���� */
	private String uom						= "";
	/** ��� */
	private String remark					= "";
	/** ����ID */
	private String pmId						= "";
	/** �����з��׸� ID */
	private String stdCheckPointId			= "";
	/** �����з��׸�� */
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
