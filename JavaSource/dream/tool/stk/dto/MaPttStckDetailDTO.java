package dream.tool.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPttStckDetailDTO extends BaseDTO
{
	/** â��ID */
	private String wcodeId         = "";
	/** â��� */
	private String wname           = "";
	/** ����Id */
	private String partId          = "";
	/** �����ȣ */
	private String partNo          = "";
	/** ���� ǰ��/�԰� */
	private String partNameSize    = "";
	/** ������� */
	private String partGrade       = "";
	
	/** ������¸� */
	private String partGradeDesc   = "";
	/** ������ */
	private String bstockQty        = "";
	/** ������� Max*/
	private String maxSaftyQty      = "";
	/** ������� Min*/
	private String minSaftyQty      = "";
	/** ������ġ */
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
