package dream.part.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtStckDetailDTO extends BaseDTO
{
	/** â��ID */
	private String wcodeId         = "";
	/** â��� */
	private String wname           = "";
	/** ����Id */
	private String partId          = "";
	/** �����ȣ */
	private String partNo          = "";
	/** ��ǰ�� */
	private String partDesc        = "";
	/** �԰� */
	private String ptSize          = "";
	/** �� */
	private String model           = "";
	/** ���� ǰ��/�԰� */
	private String partNameSize    = "";
	/** ������� */
	private String partGrade       = "";
	
	/** ������¸� */
	private String partGradeDesc   = "";
	/** a������ */
	private String astockQty        = "";
	/** b������ */
	private String bstockQty        = "";
	/** ������� Max*/
	private String maxSaftyQty      = "";
	/** ������� Min*/
	private String minSaftyQty      = "";
	/** a������ġ */
	private String abinNo            = "";
	/** b������ġ */
	private String bbinNo            = "";
	/** â��з�  */
	private String whType           = "";
	/** �ø���  */
	private String isSerial           = "";
	
	
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public String getPtSize()
    {
        return ptSize;
    }
    public void setPtSize(String ptSize)
    {
        this.ptSize = ptSize;
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public String getIsSerial() {
		return isSerial;
	}
	public void setIsSerial(String isSerial) {
		this.isSerial = isSerial;
	}
	public String getWhType() {
		return whType;
	}
	public void setWhType(String whType) {
		this.whType = whType;
	}
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
    
	public String getAbinNo() {
		return abinNo;
	}
	public void setAbinNo(String abinNo) {
		this.abinNo = abinNo;
	}
	public String getBbinNo() {
		return bbinNo;
	}
	public void setBbinNo(String bbinNo) {
		this.bbinNo = bbinNo;
	}
	public String getAstockQty() {
		return astockQty;
	}
	public void setAstockQty(String astockQty) {
		this.astockQty = CommonUtil.convertMoney(astockQty);
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
