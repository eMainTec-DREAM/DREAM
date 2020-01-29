package dream.tool.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재재고 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPttStckDetailDTO extends BaseDTO
{
	/** 창고ID */
	private String wcodeId         = "";
	/** 창고명 */
	private String wname           = "";
	/** 자재Id */
	private String partId          = "";
	/** 자재번호 */
	private String partNo          = "";
	/** 자재 품명/규격 */
	private String partNameSize    = "";
	/** 자재상태 */
	private String partGrade       = "";
	
	/** 자재상태명 */
	private String partGradeDesc   = "";
	/** 재고수량 */
	private String bstockQty        = "";
	/** 안전재고 Max*/
	private String maxSaftyQty      = "";
	/** 안전재고 Min*/
	private String minSaftyQty      = "";
	/** 보관위치 */
	private String binNo            = "";
	
	
    public String getPartGradeDesc()
    {
        return partGradeDesc;
    }
    public void setPartGradeDesc(String partGradeDesc)
    {
        this.partGradeDesc = partGradeDesc;
    }
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getWname()
    {
        return wname;
    }
    public void setWname(String wname)
    {
        this.wname = wname;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
        super.setAuditKey(partId);
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getPartNameSize()
    {
        return partNameSize;
    }
    public void setPartNameSize(String partNameSize)
    {
        this.partNameSize = partNameSize;
    }
    public String getPartGrade()
    {
        return partGrade;
    }
    public void setPartGrade(String partGrade)
    {
        this.partGrade = partGrade;
    }
    public String getBinNo()
    {
        return binNo;
    }
    public void setBinNo(String binNo)
    {
        this.binNo = binNo;
    }
	public String getBstockQty() {
		return bstockQty;
	}
	public void setBstockQty(String bstockQty) {
		this.bstockQty = CommonUtil.convertMoney(bstockQty);
	}
	public String getMaxSaftyQty() {
		return maxSaftyQty;
	}
	public void setMaxSaftyQty(String maxSaftyQty) {
		this.maxSaftyQty = CommonUtil.convertMoney(maxSaftyQty);
	}
	public String getMinSaftyQty() {
		return minSaftyQty;
	}
	public void setMinSaftyQty(String minSaftyQty) {
		this.minSaftyQty = CommonUtil.convertMoney(minSaftyQty);
	}
}
