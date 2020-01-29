package dream.part.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재재고 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtStckDetailDTO extends BaseDTO
{
	/** 창고ID */
	private String wcodeId         = "";
	/** 창고명 */
	private String wname           = "";
	/** 자재Id */
	private String partId          = "";
	/** 자재번호 */
	private String partNo          = "";
	/** 부품명 */
	private String partDesc        = "";
	/** 규격 */
	private String ptSize          = "";
	/** 모델 */
	private String model           = "";
	/** 자재 품명/규격 */
	private String partNameSize    = "";
	/** 자재상태 */
	private String partGrade       = "";
	
	/** 자재상태명 */
	private String partGradeDesc   = "";
	/** a재고수량 */
	private String astockQty        = "";
	/** b재고수량 */
	private String bstockQty        = "";
	/** 안전재고 Max*/
	private String maxSaftyQty      = "";
	/** 안전재고 Min*/
	private String minSaftyQty      = "";
	/** a보관위치 */
	private String abinNo            = "";
	/** b보관위치 */
	private String bbinNo            = "";
	/** 창고분류  */
	private String whType           = "";
	/** 시리얼  */
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
