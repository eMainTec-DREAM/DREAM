package dream.doc.ctg.dto;

import common.bean.BaseDTO;

/**
 * �����з�ü�� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class DocCtgCommonDTO extends BaseDTO
{
	/** ��뿩�� */
	private String isUse = "";
	/** ���İ� */
	private String ordNo = "";
	/** Ư����� */
	private String remark = "";
	/** �����з�ü��� */
	private String description = "";
	/** �����з�ü��NO */
	private String docctgNo = "";
	/** ���屸�� */
	private String plant = "";
	/** ���������з�ü��ID */
	private String pDocctgId = "";
	
	private String pDocctgDesc	= "";
	/** �����з�ü��ID */
	private String docctgId = "";
	
	private String docctgDesc	= "";
	/** ȸ���ڵ� */
	private String compNo = "";
	
	
	public String getpDocctgDesc() {
		return pDocctgDesc;
	}
	public void setpDocctgDesc(String pDocctgDesc) {
		this.pDocctgDesc = pDocctgDesc;
	}
	public String getDocctgDesc() {
		return docctgDesc;
	}
	public void setDocctgDesc(String docctgDesc) {
		this.docctgDesc = docctgDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDocctgNo() {
		return docctgNo;
	}
	public void setDocctgNo(String docctgNo) {
		this.docctgNo = docctgNo;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getpDocctgId() {
		return pDocctgId;
	}
	public void setpDocctgId(String pDocctgId) {
		this.pDocctgId = pDocctgId;
	}
	public String getDocctgId() {
		return docctgId;
	}
	public void setDocctgId(String docctgId) {
		this.docctgId = docctgId;
		super.setAuditKey(docctgId);
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
}
