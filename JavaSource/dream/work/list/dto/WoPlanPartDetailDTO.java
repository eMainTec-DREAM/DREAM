package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���ȹ��� - ���Ժ�ǰ �� DTO
 * @author  kim21017
 * @version $Id: WoPlanPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WoPlanPartDetailDTO extends BaseDTO
{
	/** �۾���ȹ��� ���Ժ�ǰ id */
	private String woPartId		= "";
	/** ���Ժ�ǰid*/
	private String partId			= "";
	/** ���Ժ�ǰNo*/
	private String partNo			= "";
	/** ���Ժ�ǰ�� */
	private String partDesc		= "";
	/** ������ */
	private String useQty 			= "";
	/** ���â��id */
	private String wcodeId 		= "";
	/** ���â��� */
	private String wcodeDesc		= "";
	/** ��� */
	private String remark 			= "";
	/** ��ǰ����ڵ� */
	private String partGrade 		= "";
	/** ��ǰ��޸� */
	private String partGradeDesc 	= "";
	/** ����� */
	private String stockQty		= "";
	/** Serial Part */
	private String isSerialPart	= "";
	
	/** ���� ID */
	private String eqAsmbId		= "";
	/** ���� DESC */
	private String eqAsmbDesc		= "";
	
	/** ���� ID */
	private String equipId		    = "";
	
	
	public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEqAsmbId()
    {
        return eqAsmbId;
    }
    public void setEqAsmbId(String eqAsmbId)
    {
        this.eqAsmbId = eqAsmbId;
    }
    public String getEqAsmbDesc()
    {
        return eqAsmbDesc;
    }
    public void setEqAsmbDesc(String eqAsmbDesc)
    {
        this.eqAsmbDesc = eqAsmbDesc;
    }
    public String getIsSerialPart() {
		return isSerialPart;
	}
	public void setIsSerialPart(String isSerialPart) {
		this.isSerialPart = isSerialPart;
	}
	public String getStockQty() {
		return stockQty;
	}
	public void setStockQty(String stockQty) {
		this.stockQty = CommonUtil.convertMoney(stockQty);
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
	public String getWoPartId() {
		return woPartId;
	}
	public void setWoPartId(String woPartId) {
		this.woPartId = woPartId;
		super.setAuditKey(woPartId);
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
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = CommonUtil.convertMoney(useQty);
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getWcodeDesc() {
		return wcodeDesc;
	}
	public void setWcodeDesc(String wcodeDesc) {
		this.wcodeDesc = wcodeDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}