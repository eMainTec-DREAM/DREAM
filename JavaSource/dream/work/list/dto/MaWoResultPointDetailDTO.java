package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���� �˻��׸� �� DTO
 * @author  kim21017
 * @version $Id: MaWoResultPointDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultPointDetailDTO extends BaseDTO
{
	/** �۾���� �˻��׸� id */
	private String woPointId				= "";
	/** �����׸�ID */
	private String pmPointId				= "";
	/** PM ID */
	private String pmId						= "";
	/** ���˼��� */
	private String stepNum 					= "";
	/** ���˺��� ID */
	private String eqAsmbId 				= "";
	/** ���˺��� */
	private String eqAsmbDesc 				= "";
	/** �����׸� */
	private String checkPoint 				= "";
	/** ���˹�� */
	private String checkMethod 				= "";
	/** �������� */
	private String fitBasis 				= "";
	/** ��ġ/���� ���� */
	private String checkTypeDesc 			= "";
	/** ������/���� */
	private String checkValUom 				= "";
	/** ��� */
	private String remark 					= "";
	/** �˻���code */
	private String pmPointRsltStatus 		= "";
	/** �˻���desc */
	private String pmPointRsltStatusDesc 	= "";
	/** �˻簪 */
	private String resultValue 				= "";
	/** �˻缼�γ��� */
	private String resultRemark 			= "";
	/** ��ġ/���� ���� ID */
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