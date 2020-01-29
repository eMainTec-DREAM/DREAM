package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���� �۾��ʼ��˻��׸� �� DTO
 * @author  kim21017
 * @version $Id: MaWoResultStPointDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultStPointDetailDTO extends BaseDTO
{
	/** �۾���� �۾��ʼ��˻��׸� id */
	private String woStPointId				= "";
	/** ���˼��� */
	private String stepNum 					= "";
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
	private String stPointRsltStatus 		= "";
	/** �˻���desc */
	private String stPointRsltStatusDesc 	= "";
	/** �˻簪 */
	private String resultValue 				= "";
	/** �˻缼�γ��� */
	private String resultRemark 			= "";
	
	public String getWoStPointId() {
		return woStPointId;
	}
	public void setWoStPointId(String woStPointId) {
		this.woStPointId = woStPointId;
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
	public String getStPointRsltStatus() {
		return stPointRsltStatus;
	}
	public void setStPointRsltStatus(String stPointRsltStatus) {
		this.stPointRsltStatus = stPointRsltStatus;
	}
	public String getStPointRsltStatusDesc() {
		return stPointRsltStatusDesc;
	}
	public void setStPointRsltStatusDesc(String stPointRsltStatusDesc) {
		this.stPointRsltStatusDesc = stPointRsltStatusDesc;
	}
	
}