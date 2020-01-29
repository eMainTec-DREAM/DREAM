package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * �����׸� �˾� DTO
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 */
public class LovWorkPmDInsListDTO extends BaseDTO
{
	/** PM ID */
	private String pmId					= "";
	/** ���������� �����׸��ڵ� */
	private String eqCtgPmPointId 		= "";
	/** ���������ڵ� ID*/
	private String eqCtgId 				= "";
	/** �ֱ�-�Ⱓ */
	private String cycle 				= "";
	/** �ֱ� ID - ��, ��, ��, �� */
	private String periodTypeId			= "";
	/** �ֱ� Desc - ��, ��, ��, �� */
	private String periodTypeDesc 		= "";
	/** ���˺��� ID */
	private String eqasmbId 			= "";
	/** ���˺��� Desc */
	private String eqasmbDesc 			= "";
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
	/** ��� */
	private String remark 				 = "";
	/** ������ġ */
	private String binNo 				 = "";
	
	
	
	public String getBinNo() {
		return binNo;
	}
	public void setBinNo(String binNo) {
		this.binNo = binNo;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getEqCtgPmPointId() {
		return eqCtgPmPointId;
	}
	public void setEqCtgPmPointId(String eqCtgPmPointId) {
		this.eqCtgPmPointId = eqCtgPmPointId;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
