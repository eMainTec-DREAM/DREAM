
package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * �������� - �� DTO
 * @author  kim21017
 * @version $Id: MaEqCatalogDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqCatalogDetailDTO extends BaseDTO
{
	/** ��������ID */
	private String eqCtgId 			= "";
	/** �ڵ� */
	private String eqCtgCode 		= "";
	/** �ڵ�� */
	private String eqCtgDesc 		= "";
	/** ��������ID */
	private String peqCtgId 		= "0";
	/** ���������� */
	private String peqCtgDesc 		= "";
	/** ���񱸺�ID */
	private String eqTypeId 		= "";
	/** ���񱸺и� */
	private String eqTypeDesc 		= "";
	/** ���� */
	private String ordNo 			= "";
	/** ���� */
	private String remark 			= "";
	/** ��뿩�� */
	private String isUse 			= "";
	/** �����ڵ� */
	private String mngCd 			= "";
	/** ���������� */
	private String eqCnt			= "";
	/** full desc */
	private String fullDesc			= "";
	
	
	
	public String getFullDesc()
    {
        return fullDesc;
    }
    public void setFullDesc(String fullDesc)
    {
        this.fullDesc = fullDesc;
    }
    public String getEqCnt() {
		return eqCnt;
	}
	public void setEqCnt(String eqCnt) {
		this.eqCnt = eqCnt;
	}
	public String getMngCd() {
		return mngCd;
	}
	public void setMngCd(String mngCd) {
		this.mngCd = mngCd;
	}
	public String getEqTypeId() {
		return eqTypeId;
	}
	public void setEqTypeId(String eqTypeId) {
		this.eqTypeId = eqTypeId;
	}
	public String getEqTypeDesc() {
		return eqTypeDesc;
	}
	public void setEqTypeDesc(String eqTypeDesc) {
		this.eqTypeDesc = eqTypeDesc;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
		super.setAuditKey(eqCtgId);
	}
	public String getEqCtgCode() {
		return eqCtgCode;
	}
	public void setEqCtgCode(String eqCtgCode) {
		this.eqCtgCode = eqCtgCode;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
	}
	public String getPeqCtgId() {
		return peqCtgId;
	}
	public void setPeqCtgId(String peqCtgId) {
		this.peqCtgId = peqCtgId==""?"0":peqCtgId;
	}
	public String getPeqCtgDesc() {
		return peqCtgDesc;
	}
	public void setPeqCtgDesc(String peqCtgDesc) {
		this.peqCtgDesc = peqCtgDesc;
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
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
}
