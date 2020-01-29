package dream.work.pm.std.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 표준항목 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaStdPointDetailDTO extends BaseDTO
{
	/** 점검Id */
	private String stWrkPointId				= "";
	/** 점검순서 */
	private String stepNum 					= "";
	/** 점검항목 */
	private String checkPoint 				= "";
	/** 점검방법 */
	private String checkMethod 				= "";
	/** 적정기준 */
	private String fitBasis 				= "";
	/** 수치판정구분명 */
	private String checkTypeDesc            = "";
	/** 수치판정구분 */
	private String checkType                = "";
	/** 비고 */
	private String remark 					= "";
	/** 단위 */
	private String uom                      = "";
	/** 사용여부 */
	private String isActive                 = "";
	/** PM ID */
	private String pmId                     = "";
	
	private String checkMin                 = "";
	
	private String checkMax                 = "";
	
	private String checkBasisVal            = "";

	private String cycle            		= "";
	
	private String periodTypeDesc           = "";
	
	private String periodType            	= "";
	
	private String usage            		= "";
	
	private String scheduleTypeDesc         = "";
	
	private String scheduleType            	= "";
	/** 예상소요시간(분) */
	private String predTime            		= "";
	/** 정렬값 */
	private String ordNo            		= "";

	public String getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public String getPredTime() {
		return predTime;
	}

	public void setPredTime(String predTime) {
		this.predTime = CommonUtil.convertMoney(predTime);
	}

	public String getScheduleTypeDesc() {
		return scheduleTypeDesc;
	}

	public void setScheduleTypeDesc(String scheduleTypeDesc) {
		this.scheduleTypeDesc = scheduleTypeDesc;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}

	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = CommonUtil.convertMoney(usage);
	}

	public String getStWrkPointId() {
		return stWrkPointId;
	}

	public void setStWrkPointId(String stWrkPointId) {
		this.stWrkPointId = stWrkPointId;
		super.setAuditKey(stWrkPointId);
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

	public String getCheckTypeDesc() {
		return checkTypeDesc;
	}

	public void setCheckTypeDesc(String checkTypeDesc) {
		this.checkTypeDesc = checkTypeDesc;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}

	public String getCheckMin() {
		return checkMin;
	}

	public void setCheckMin(String checkMin) {
		this.checkMin = CommonUtil.convertMoney(checkMin);
	}

	public String getCheckMax() {
		return checkMax;
	}

	public void setCheckMax(String checkMax) {
		this.checkMax = CommonUtil.convertMoney(checkMax);
	}

	public String getCheckBasisVal() {
		return checkBasisVal;
	}

	public void setCheckBasisVal(String checkBasisVal) {
		this.checkBasisVal = CommonUtil.convertMoney(checkBasisVal);
	}
	
}
