package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������ ��������-�����׸�  �� DTO
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmInsPointDetailDTO extends BaseDTO
{
	/** ����ID */
	private String equipId				= "";
	/** �����۾�ID */
	private String pmId					= "";
	/** �����۾��׸�ID */
	private String pmPointId			= "";
	/** ���˼��� */
	private String stepNum				= "";
	/** ����ID */
	private String eqAsmbId				= "";
	/** ������ */
	private String eqAsmbDesc			= "";
	/** �����׸� */
	private String checkPoint			= "";
	/** �������� */
	private String fitBasis				= "";
	/** ���˹�� */
	private String checkMethod			= "";
	/** ��ġ/���� ID */
	private String checkTypeId			= "";
	/** ��ġ/���� �� */
	private String checkTypeDesc		= "";
	/** ���ذ� */
	private String checkBasisVal		= "";
	/** ���Ѱ� */
	private String checkMin				= "";
	/** ���Ѱ� */
	private String checkMax				= "";
	/** ���� */
	private String uom					= "";
	/** ���࿩��ID */
	private String isActiveId			= "";
	/** ���࿩�θ�  */
	private String isActiveDesc			= "";
	/** ��� */
	private String remark				= "";
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
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
	public String getEqAsmbId() {
		return eqAsmbId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
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
	public String getFitBasis() {
		return fitBasis;
	}
	public void setFitBasis(String fitBasis) {
		this.fitBasis = fitBasis;
	}
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
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
	public String getCheckBasisVal() {
		return checkBasisVal;
	}
	public void setCheckBasisVal(String checkBasisVal) {
		this.checkBasisVal = CommonUtil.convertMoney(checkBasisVal);
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
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getIsActiveId() {
		return isActiveId;
	}
	public void setIsActiveId(String isActiveId) {
		this.isActiveId = isActiveId;
	}
	public String getIsActiveDesc() {
		return isActiveDesc;
	}
	public void setIsActiveDesc(String isActiveDesc) {
		this.isActiveDesc = isActiveDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}