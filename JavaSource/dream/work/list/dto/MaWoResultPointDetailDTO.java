package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업결과 검사항목 상세 DTO
 * @author  kim21017
 * @version $Id: MaWoResultPointDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultPointDetailDTO extends BaseDTO
{
	/** 작업결과 검사항목 id */
	private String woPointId				= "";
	/** 점검항목ID */
	private String pmPointId				= "";
	/** PM ID */
	private String pmId						= "";
	/** 점검순서 */
	private String stepNum 					= "";
	/** 점검부위 ID */
	private String eqAsmbId 				= "";
	/** 점검부위 */
	private String eqAsmbDesc 				= "";
	/** 점검항목 */
	private String checkPoint 				= "";
	/** 점검방법 */
	private String checkMethod 				= "";
	/** 적정기준 */
	private String fitBasis 				= "";
	/** 수치/판정 구분 */
	private String checkTypeDesc 			= "";
	/** 설정값/단위 */
	private String checkValUom 				= "";
	/** 비고 */
	private String remark 					= "";
	/** 검사결과code */
	private String pmPointRsltStatus 		= "";
	/** 검사결과desc */
	private String pmPointRsltStatusDesc 	= "";
	/** 검사값 */
	private String resultValue 				= "";
	/** 검사세부내용 */
	private String resultRemark 			= "";
	/** 수치/판정 구분 ID */
	private String checkTypeId	 			= "";
	
	
	public String getCheckTypeId() {
		return checkTypeId;
	}
	public void setCheckTypeId(String checkTypeId) {
		this.checkTypeId = checkTypeId;
	}
	public String getPmPointId() {
		return pmPointId;
	}
	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
	}
	public String getWoPointId() {
		return woPointId;
	}
	public void setWoPointId(String woPointId) {
		this.woPointId = woPointId;
		super.setAuditKey(woPointId);
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
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
	public String getCheckValUom() {
		return checkValUom;
	}
	public void setCheckValUom(String checkValUom) {
		this.checkValUom = checkValUom;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPmPointRsltStatus() {
		return pmPointRsltStatus;
	}
	public void setPmPointRsltStatus(String pmPointRsltStatus) {
		this.pmPointRsltStatus = pmPointRsltStatus;
	}
	public String getPmPointRsltStatusDesc() {
		return pmPointRsltStatusDesc;
	}
	public void setPmPointRsltStatusDesc(String pmPointRsltStatusDesc) {
		this.pmPointRsltStatusDesc = pmPointRsltStatusDesc;
	}
	public String getResultValue() {
		return resultValue;
	}
	public void setResultValue(String resultValue) {
		this.resultValue = CommonUtil.convertMoney(resultValue);
	}
	public String getResultRemark() {
		return resultRemark;
	}
	public void setResultRemark(String resultRemark) {
		this.resultRemark = resultRemark;
	}
	public String getEqAsmbId() {
		return eqAsmbId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	
	
}