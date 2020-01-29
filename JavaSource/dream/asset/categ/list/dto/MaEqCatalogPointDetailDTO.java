package dream.asset.categ.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���������� �����׸� ���� Detail DTO
 * @author  euna0207
 * @version $Id: MaEqCatalogPointDetailDTO.java,v 1.1 2015/12/04 09:10:45 euna0207 Exp $
 * @since   1.0
 */
public class MaEqCatalogPointDetailDTO extends BaseDTO
{
	/** ���������� �����׸��ڵ� (key) */
	private String eqCtgPmPointId 		= "";
	/** ���������ڵ� ID*/
	private String eqCtgId 				= "";
    /** �������-�Ⱓ */
	private String cycle 				= "";
	/** �������-��,��,��, �� */
	private String periodTypeId			= "";
	/** �������Desc-��,��,��, �� */
	private String periodTypeDesc 		= "";
	/** ���˺��� ID */
	private String eqasmbId 			= "";
	/** ���˺��� Desc */
	private String eqasmbDesc 			= "";
	/** ��뿩�� ID */
	private String isUse				= "";
	/** �����׸� */
	private String checkPoint 			= "";
	/** ���˹�� */
	private String checkMethod 			= "";
	/** �������� */
	private String fitBasis 			= "";
	/** ��ġ/�������� ID */
	private String checkTypeId 			= "";
	/** ��ġ/�������� Desc */
	private String checkTypeDesc 		= "";
	/** ���� */
	private String checkMin				 = "";
	/** ���� */
	private String checkBasisVal 		 = "";
	/** ���� */
	private String checkMax 			 = "";
	/** ���� */
	private String uom 					 = "";
	/** ����ҿ�ð� */
	private String predTime 			 = "";
	/** ���İ� */
	private String ordNo 				 = "";
	/** ��� */
	private String remark 				 = "";

	/** ���˼��� */
	private String stepNum 				 = "";
	/** (������)���������׸񿩺� */
    private String isCommCtgPoint	     = "";
    /** ������� Ȯ�ι�� ID */
    private String scheduleTypeId		 = "";
    /** ������� Ȯ�ι�� */
    private String scheduleTypeDesc		 = "";
    /** �������-��뷮,���귮,�����ð� */
    private String usage	 			 = "";
	
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = CommonUtil.convertMoney(stepNum);
	}
	public String getIsCommCtgPoint() {
		return isCommCtgPoint;
	}
	public void setIsCommCtgPoint(String isCommCtgPoint) {
		this.isCommCtgPoint = isCommCtgPoint;
	}
	public String getScheduleTypeId() {
		return scheduleTypeId;
	}
	public void setScheduleTypeId(String scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}
	public String getScheduleTypeDesc() {
		return scheduleTypeDesc;
	}
	public void setScheduleTypeDesc(String scheduleTypeDesc) {
		this.scheduleTypeDesc = scheduleTypeDesc;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = CommonUtil.convertMoney(usage);
	}
	public String getEqCtgPmPointId() {
		return eqCtgPmPointId;
	}
	public void setEqCtgPmPointId(String eqCtgPmPointId) {
		this.eqCtgPmPointId = eqCtgPmPointId;
		super.setAuditKey(eqCtgPmPointId);
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getPeriodTypeId() {
		return periodTypeId;
	}
	public void setPeriodTypeId(String periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getEqasmbId() {
		return eqasmbId;
	}
	public void setEqasmbId(String eqasmbId) {
		this.eqasmbId = eqasmbId;
	}
	public String getEqasmbDesc() {
		return eqasmbDesc;
	}
	public void setEqasmbDesc(String eqasmbDesc) {
		this.eqasmbDesc = eqasmbDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
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
	public String getCheckTypeId() {
		return checkTypeId;
	}
	public void setCheckTypeId(String checkTypeId) {
		this.checkTypeId = checkTypeId;
	}
	public String getCheckTypeDesc() {
		return checkTypeDesc;
	}
	public void setCheckTypeDesc(String checkTypeDesc) {
		this.checkTypeDesc = checkTypeDesc;
	}
	public String getCheckMin() {
		return checkMin;
	}
	public void setCheckMin(String checkMin) {
		this.checkMin = checkMin;
	}
	public String getCheckBasisVal() {
		return checkBasisVal;
	}
	public void setCheckBasisVal(String checkBasisVal) {
		this.checkBasisVal = checkBasisVal;
	}
	public String getCheckMax() {
		return checkMax;
	}
	public void setCheckMax(String checkMax) {
		this.checkMax = checkMax;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getPredTime() {
		return predTime;
	}
	public void setPredTime(String predTime) {
		this.predTime = predTime;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}