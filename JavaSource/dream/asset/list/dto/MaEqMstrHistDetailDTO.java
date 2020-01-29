package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���񺯰��̷� - �� DTO
 * @author  kim21017
 * @version $Id: MaEqMstrHistDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqMstrHistDetailDTO extends BaseDTO
{
	private String eqalthistId			= "";
	/** ����ID */
	private String equipId 				= "";
	/** �����ȣ */
	private String itemNo 				= "";
	/** ����� */
	private String equipDesc 			= "";
	/** ��ġ�� */
	private String eqLocDesc 			= "";
	/** ������ */
	private String eqCtgDesc 			= "";
	/** ������¸� */
	private String eqStatusDesc 		= "";
	/** ��ġ���� */
	private String setupDate 			= "";
	/** ���ɷ� */
	private String capacity 			= "";
	/** ���Utility */
	private String utilCapa 			= "";
	/** ��/���� �� */
	private String plfTypeDesc 			= "";
	/** ���űݾ� */
	private String buyAmt 				= "";
	/** �μ��� */
	private String deptDesc 			= "";
	/** ������(��) �� */
	private String mainMngName 			= "";
	/** ������(��) �� */
	private String subMngName 			= "";
	/** �������񿩺� */
	private String isLawEq 				= "";
	
	
	public String getEqalthistId() {
		return eqalthistId;
	}
	public void setEqalthistId(String eqalthistId) {
		this.eqalthistId = eqalthistId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
	}
	public String getEqStatusDesc() {
		return eqStatusDesc;
	}
	public void setEqStatusDesc(String eqStatusDesc) {
		this.eqStatusDesc = eqStatusDesc;
	}
	public String getSetupDate() {
		return setupDate;
	}
	public void setSetupDate(String setupDate) {
		this.setupDate = CommonUtil.convertDate(setupDate);
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getUtilCapa() {
		return utilCapa;
	}
	public void setUtilCapa(String utilCapa) {
		this.utilCapa = utilCapa;
	}
	public String getPlfTypeDesc() {
		return plfTypeDesc;
	}
	public void setPlfTypeDesc(String plfTypeDesc) {
		this.plfTypeDesc = plfTypeDesc;
	}
	public String getBuyAmt() {
		return buyAmt;
	}
	public void setBuyAmt(String buyAmt) {
		this.buyAmt = CommonUtil.convertMoney(buyAmt);
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getMainMngName() {
		return mainMngName;
	}
	public void setMainMngName(String mainMngName) {
		this.mainMngName = mainMngName;
	}
	public String getSubMngName() {
		return subMngName;
	}
	public void setSubMngName(String subMngName) {
		this.subMngName = subMngName;
	}
	public String getIsLawEq() {
		return isLawEq;
	}
	public void setIsLawEq(String isLawEq) {
		this.isLawEq = isLawEq;
	}
}
