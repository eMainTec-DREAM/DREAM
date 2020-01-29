package dream.part.pur.buy.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매신청 item - 상세  DTO
 * @author  kim21017
 * @version $Id: MaPtBuyReqDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPtBuyReqDetailDTO extends BaseDTO
{
	/** 구매신청ID */
	private String ptPrListId 	= "";
	/** 구매신청 itemID */
	private String ptPrItemId 	= "";
	/** 자재Id */
	private String partId 		= "";
	/** 자재No */
	private String partNo 		= "";
	/** 품명 */
	private String partDesc 	= "";
	/** 규격 */	
	private String ptSize 		= "";
	/** 모델 */	
	private String model 		= "";
	/** 자재등급 */	
	private String partGrade	= "";
	/** 자재등급명 */
	private String partGradeDesc   = "";
	/** 수량 */
	private String recQty 		= "";
	/** 비고 */
	private String remark 		= "";
	/** 타이틀 */
	private String partDescSize	= "";
	/** 단가 */
	private String unitPrice	= "";
	/** 가격 */
	private String totalPrice	= "";
	/** 화폐 */
	private String curr			= "";
	/** 화폐명 */
	private String currDesc		= "";
	/** 요청자 ID */
	private String appReqById	= "";
	/** 요청자명 */
	private String appReqByDesc	= "";
	/** 현장구매청구 Id */
	private String ptPnListId	= "";
	/** 현장구매청구 Id */
	private String ptPnListIds	= "";
	/** 발주작성수량 */
	private String poOnQty		= "";
	/** 발주수량 */
	private String poQty		= "";
	/** 입고작성수량 */
	private String grOnQty		= "";
	/** 입고수량 */
	private String grQty		= "";
	/** 계정 ID */
	private String accntId		= "";
	/** 계정명 */
	private String accntDesc	= "";
	/** 코스트센터 ID */
	private String ctCtrId		= "";
	/** 코스트센터명 */
	private String ctCtrDesc	= "";
	/** ERP 품목 */
	private String erpPartNo	= "";
	/** ERP PR번호 */
	private String erpPrNo	    = "";
	/** ERP PR순번 */
	private String erpPrSeq	    = "";
	/** 구매그룹 */
    private String purGroup     = "";
    /** 구매그룹 desc */
    private String purGroupDesc = "";
    /** G/L계정범주 */
    private String accntType    = "";
    /** G/L계정범주 desc */
    private String accntTypeDesc= "";
    /** 단위 */
    private String uom          = "";
    /** 단위명 */
    private String uomDesc      = "";
    /** 요청일자 **/
    private String reqComDate   = "";
    
	public String getPtPnListIds()
    {
        return ptPnListIds;
    }
    public void setPtPnListIds(String ptPnListIds)
    {
        this.ptPnListIds = ptPnListIds;
    }
    public String getPtPrListId()
    {
        return ptPrListId;
    }
    public void setPtPrListId(String ptPrListId)
    {
        this.ptPrListId = ptPrListId;
    }
    public String getPoOnQty()
    {
        return poOnQty;
    }
    public void setPoOnQty(String poOnQty)
    {
        this.poOnQty = poOnQty;
    }
    public String getGrOnQty()
    {
        return grOnQty;
    }
    public void setGrOnQty(String grOnQty)
    {
        this.grOnQty = grOnQty;
    }
    public String getPoQty()
    {
        return poQty;
    }
    public void setPoQty(String poQty)
    {
        this.poQty = poQty;
    }
    public String getGrQty()
    {
        return grQty;
    }
    public void setGrQty(String grQty)
    {
        this.grQty = grQty;
    }
    public String getReqComDate()
    {
        return reqComDate;
    }
    public void setReqComDate(String reqComDate)
    {
        this.reqComDate = CommonUtil.convertDate(reqComDate);
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public String getUomDesc()
    {
        return uomDesc;
    }
    public void setUomDesc(String uomDesc)
    {
        this.uomDesc = uomDesc;
    }
    public String getUom()
    {
        return uom;
    }
    public void setUom(String uom)
    {
        this.uom = uom;
    }
    public String getPurGroup()
    {
        return purGroup;
    }
    public void setPurGroup(String purGroup)
    {
        this.purGroup = purGroup;
    }
    public String getPurGroupDesc()
    {
        return purGroupDesc;
    }
    public void setPurGroupDesc(String purGroupDesc)
    {
        this.purGroupDesc = purGroupDesc;
    }
    public String getAccntType()
    {
        return accntType;
    }
    public void setAccntType(String accntType)
    {
        this.accntType = accntType;
    }
    public String getAccntTypeDesc()
    {
        return accntTypeDesc;
    }
    public void setAccntTypeDesc(String accntTypeDesc)
    {
        this.accntTypeDesc = accntTypeDesc;
    }
    public String getErpPartNo()
    {
        return erpPartNo;
    }
    public void setErpPartNo(String erpPartNo)
    {
        this.erpPartNo = erpPartNo;
    }
    public String getAccntId()
    {
        return accntId;
    }
    public void setAccntId(String accntId)
    {
        this.accntId = accntId;
    }
    public String getAccntDesc()
    {
        return accntDesc;
    }
    public void setAccntDesc(String accntDesc)
    {
        this.accntDesc = accntDesc;
    }
    public String getCtCtrId()
    {
        return ctCtrId;
    }
    public void setCtCtrId(String ctCtrId)
    {
        this.ctCtrId = ctCtrId;
    }
    public String getCtCtrDesc()
    {
        return ctCtrDesc;
    }
    public void setCtCtrDesc(String ctCtrDesc)
    {
        this.ctCtrDesc = ctCtrDesc;
    }
    public String getErpPrNo()
    {
        return erpPrNo;
    }
    public void setErpPrNo(String erpPrNo)
    {
        this.erpPrNo = erpPrNo;
    }
    public String getErpPrSeq()
    {
        return erpPrSeq;
    }
    public void setErpPrSeq(String erpPrSeq)
    {
        this.erpPrSeq = erpPrSeq;
    }
	public String getPtPnListId() {
		return ptPnListId;
	}
	public void setPtPnListId(String ptPnListId) {
		this.ptPnListId = ptPnListId;
	}
	public String getAppReqById() {
		return appReqById;
	}
	public void setAppReqById(String appReqById) {
		this.appReqById = appReqById;
	}
	public String getAppReqByDesc() {
		return appReqByDesc;
	}
	public void setAppReqByDesc(String appReqByDesc) {
		this.appReqByDesc = appReqByDesc;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = CommonUtil.convertMoney(totalPrice);
	}
	public String getPartGrade() {
		return partGrade;
	}
	public void setPartGrade(String partGrade) {
		this.partGrade = partGrade;
	}
	public String getCurr() {
		return curr;
	}
	public void setCurr(String curr) {
		this.curr = curr;
	}
	public String getCurrDesc() {
		return currDesc;
	}
	public void setCurrDesc(String currDesc) {
		this.currDesc = currDesc;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = CommonUtil.convertMoney(unitPrice);
	}
	public String getPartDescSize() {
		return partDescSize;
	}
	public void setPartDescSize(String partDescSize) {
		this.partDescSize = partDescSize;
	}
	public String getPtPrItemId() {
		return ptPrItemId;
	}
	public void setPtPrItemId(String ptPrItemId) {
		this.ptPrItemId = ptPrItemId;
		super.setAuditKey(ptPrItemId);
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
	public String getRecQty() {
		return recQty;
	}
	public void setRecQty(String recQty) {
		this.recQty = CommonUtil.convertMoney(recQty);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPartGradeDesc() {
		return partGradeDesc;
	}
	public void setPartGradeDesc(String partGradeDesc) {
		this.partGradeDesc = partGradeDesc;
	}
	
	
}