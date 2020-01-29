package dream.doc.ctg.dto;

import common.bean.BaseDTO;

/**
 * �����з�ü�� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class DocCtgDetailDTO extends BaseDTO
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
	
	private String plantDesc	= "";
	/** ���������з�ü��ID */
	private String pdocctgId = "0";
	
	private String pdocctgDesc	= "";
	/** �����з�ü��ID */
	private String docctgId = "";
	/** ȸ���ڵ� */
	private String compNo = "";

	/** full desc */
	private String fullDesc			= "";
	
	
	public String getPlantDesc() {
		return plantDesc;
	}
	public String getFullDesc() {
		return fullDesc;
	}
	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
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
	public String getPdocctgId() {
		return pdocctgId;
	}
	public void setPdocctgId(String pdocctgId) {
		this.pdocctgId = pdocctgId;
	}
	public String getPdocctgDesc() {
		return pdocctgDesc;
	}
	public void setPdocctgDesc(String pdocctgDesc) {
		this.pdocctgDesc = pdocctgDesc;
	}
	
	
}
