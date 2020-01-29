package dream.part.pur.buy.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���Ž�û item - ��  DTO
 * @author  kim21017
 * @version $Id: MaPtBuyReqDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPtBuyReqDetailDTO extends BaseDTO
{
	/** ���Ž�ûID */
	private String ptPrListId 	= "";
	/** ���Ž�û itemID */
	private String ptPrItemId 	= "";
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
	/** ������ */	
	private String partGrade	= "";
	/** �����޸� */
	private String partGradeDesc   = "";
	/** ���� */
	private String recQty 		= "";
	/** ��� */
	private String remark 		= "";
	/** Ÿ��Ʋ */
	private String partDescSize	= "";
	/** �ܰ� */
	private String unitPrice	= "";
	/** ���� */
	private String totalPrice	= "";
	/** ȭ�� */
	private String curr			= "";
	/** ȭ��� */
	private String currDesc		= "";
	/** ��û�� ID */
	private String appReqById	= "";
	/** ��û�ڸ� */
	private String appReqByDesc	= "";
	/** ���屸��û�� Id */
	private String ptPnListId	= "";
	/** ���屸��û�� Id */
	private String ptPnListIds	= "";
	/** �����ۼ����� */
	private String poOnQty		= "";
	/** ���ּ��� */
	private String poQty		= "";
	/** �԰��ۼ����� */
	private String grOnQty		= "";
	/** �԰���� */
	private String grQty		= "";
	/** ���� ID */
	private String accntId		= "";
	/** ������ */
	private String accntDesc	= "";
	/** �ڽ�Ʈ���� ID */
	private String ctCtrId		= "";
	/** �ڽ�Ʈ���͸� */
	private String ctCtrDesc	= "";
	/** ERP ǰ�� */
	private String erpPartNo	= "";
	/** ERP PR��ȣ */
	private String erpPrNo	    = "";
	/** ERP PR���� */
	private String erpPrSeq	    = "";
	/** ���ű׷� */
    private String purGroup     = "";
    /** ���ű׷� desc */
    private String purGroupDesc = "";
    /** G/L�������� */
    private String accntType    = "";
    /** G/L�������� desc */
    private String accntTypeDesc= "";
    /** ���� */
    private String uom          = "";
    /** ������ */
    private String uomDesc      = "";
    /** ��û���� **/
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