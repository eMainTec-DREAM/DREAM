
package dream.part.adj.stktake.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��ǰ�ǻ� item - ��  DTO
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class PartAdjStkTakeItemDetailDTO extends BaseDTO
{
	/** ���Ž�û itemID */
	private String ptStkTakeItemId 	= "";
	/** ����Id */
	private String partId 		= "";
	/** ����No */
	private String partNo 		= "";
	/** ǰ�� */
	private String partDesc 	= "";
	/** �԰� */	
	private String ptSize 		= "";
	/** �� */	
	private String model 		= "";
	/** �������� */
	private String realQty 		= "";
	/** ��������� */
	private String sstockQty 	= "";
	/** ���̼��� */
	private String gapQty 		= "";
	/** ��� */
	private String remark 		= "";
	/** ���ۻ� */
	private String maker		= "";
	/** ���� */
	private String ptStkTakeStatus 		= "";
	private String partGrade	= "";
	private String partGradeDesc	= "";

	public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public String getPtStkTakeStatus() {
		return ptStkTakeStatus;
	}
	public void setPtStkTakeStatus(String ptStkTakeStatus) {
		this.ptStkTakeStatus = ptStkTakeStatus;
	}
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
	public String getRealQty() {
		return realQty;
	}
	public void setRealQty(String realQty) {
		this.realQty =  CommonUtil.convertMoney(realQty);
	}
	public String getSstockQty() {
		return sstockQty;
	}
	public void setSstockQty(String sstockQty) {
		this.sstockQty = sstockQty;
	}
	public String getGapQty() {
		return gapQty;
	}
	public void setGapQty(String gapQty) {
		this.gapQty = gapQty;
	}

	public String getPtStkTakeItemId() {
		return ptStkTakeItemId;
	}
	public void setPtStkTakeItemId(String ptStkTakeItemId) {
		this.ptStkTakeItemId = ptStkTakeItemId;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getPtSize() {
		return ptSize;
	}
	public void setPtSize(String ptSize) {
		this.ptSize = ptSize;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
}