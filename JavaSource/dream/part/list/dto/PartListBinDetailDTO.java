package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * ��ǰâ�� ������ġ - Detail DTO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartListBinDetailDTO extends BaseDTO
{
	/** â�� ID */
	private String wcodeId			= "";
    /** ��ǰ ������ġ ID */
    private String ptBinListId		= "";
    /** ��ǰ ������ġ ID */
    private String ptwhBinNoId		= "";
    /** â��� */
    private String wname			= "";
    /** ���� */
    private String binNo 			= "";
    /** �� */
    private String binNoTxt     	= "";
    /** ��� */
    private String remark 			= "";
    /** part ID */
    private String partId			= "";
    /** ����� */
    private String partGrade		= "";
    /** ����޸� */
    private String partGradeDesc	= "";
    
    
    
	public String getPartGrade() {
		return partGrade;
	}
	public void setPartGrade(String partGrade) {
		this.partGrade = partGrade;
	}
	public String getPartGradeDesc() {
		return partGradeDesc;
	}
	public void setPartGradeDesc(String partGradeDesc) {
		this.partGradeDesc = partGradeDesc;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPtwhBinNoId() {
		return ptwhBinNoId;
	}
	public void setPtwhBinNoId(String ptwhBinNoId) {
		this.ptwhBinNoId = ptwhBinNoId;
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getPtBinListId() {
		return ptBinListId;
	}
	public void setPtBinListId(String ptBinListId) {
		this.ptBinListId = ptBinListId;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getBinNo() {
		return binNo;
	}
	public void setBinNo(String binNo) {
		this.binNo = binNo;
	}
	public String getBinNoTxt() {
		return binNoTxt;
	}
	public void setBinNoTxt(String binNoTxt) {
		this.binNoTxt = binNoTxt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	
	
}
