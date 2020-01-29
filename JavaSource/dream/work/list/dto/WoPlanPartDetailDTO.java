package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업게획목록 - 투입부품 상세 DTO
 * @author  kim21017
 * @version $Id: WoPlanPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WoPlanPartDetailDTO extends BaseDTO
{
	/** 작업계획목록 투입부품 id */
	private String woPartId		= "";
	/** 투입부품id*/
	private String partId			= "";
	/** 투입부품No*/
	private String partNo			= "";
	/** 투입부품명 */
	private String partDesc		= "";
	/** 사용수량 */
	private String useQty 			= "";
	/** 사용창고id */
	private String wcodeId 		= "";
	/** 사용창고명 */
	private String wcodeDesc		= "";
	/** 비고 */
	private String remark 			= "";
	/** 부품등급코드 */
	private String partGrade 		= "";
	/** 부품등급명 */
	private String partGradeDesc 	= "";
	/** 현재고 */
	private String stockQty		= "";
	/** Serial Part */
	private String isSerialPart	= "";
	
	/** 부위 ID */
	private String eqAsmbId		= "";
	/** 부위 DESC */
	private String eqAsmbDesc		= "";
	
	/** 설비 ID */
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