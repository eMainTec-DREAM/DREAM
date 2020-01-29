package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 부품창고 보관위치 - Detail DTO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartListBinDetailDTO extends BaseDTO
{
	/** 창고 ID */
	private String wcodeId			= "";
    /** 부품 저장위치 ID */
    private String ptBinListId		= "";
    /** 부품 저장위치 ID */
    private String ptwhBinNoId		= "";
    /** 창고명 */
    private String wname			= "";
    /** 구역 */
    private String binNo 			= "";
    /** 열 */
    private String binNoTxt     	= "";
    /** 비고 */
    private String remark 			= "";
    /** part ID */
    private String partId			= "";
    /** 재고등급 */
    private String partGrade		= "";
    /** 재고등급명 */
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
