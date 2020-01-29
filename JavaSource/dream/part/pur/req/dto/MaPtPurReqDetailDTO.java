package dream.part.pur.req.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��ǰ���� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtPurReqDetailDTO extends BaseDTO
{
    /** ��û��ȣ -Key-*/
    private String reqId            = "";
    /** ��û��ȣ */
    private String reqNo            = "";
    /** �ۼ����� */
    private String inputStatus      = "";
    /** �ۼ����� */
    private String inputStatusDesc  = "";
    /** ǰ��id*/
    private String partId           = "";
    /** ǰ��No*/
    private String partNo           = "";
    /** erpǰ��*/
    private String erpPartNo        = "";
    /** ǰ�� */
    private String partDesc         = "";
    /** �� */
    private String ptModel          = "";
    /** �԰� */
    private String ptSize           = "";
    /** ���� */
    private String qty              = "";	
    /** ��û�μ��� */
    private String entDeptDesc      = "";
    /** ��û�μ� */
    private String entDept          = "";
    /** ��û�� */
    private String enterById        = "";
    /** ��û�ڸ� */
    private String enterByName      = "";  
    /** ��û���� */
    private String reDate           = "";
    /** ��� */
    private String remark           = "";
    /** Detail Title */
    private String detailTitle      = "";
	/** �������� */
	private String recDate		 	= "";
	/** �����μ� �� */
    private String recDeptDesc      = "";
    /** �����μ� */
    private String recDept          = "";
	/** ������ */
	private String recBy		 	= "";
	/** �����ڸ� */
	private String recByName	 	= "";
	/** ��뼳�� */
	private String usedEquip        = "";
	/** ��뼳�� �� */
	private String usedEquipDesc    = "";
	/** ���뵵 */
	private String usage            = "";
	/** ���� */
	private String plant            = "";
	/** ���� �� */
	private String plantDesc        = "";
	/** ���Ž�û���� */
	private String prDate           = "";
	/** ���� */
	private String uom              = "";
	/** ����desc */
	private String uomDesc          = "";
	/** �����ۼ����� */
	private String prOnQty          = "";
	/** ���ż��� */
	private String prQty            = "";
	/** �����ۼ����� */
	private String poOnQty          = "";
	/** ���ּ��� */
	private String poQty            = "";
	/** �԰��ۼ����� */
	private String grOnQty          = "";
	/** �԰���� */
	private String grQty            = "";
	/** �ֽű��Ŵܰ� */
	private String lastPrice        = "";
	
	
    public String getLastPrice()
    {
        return lastPrice;
    }
    public void setLastPrice(String lastPrice)
    {
        this.lastPrice = lastPrice;
    }
    public String getPrOnQty()
    {
        return prOnQty;
    }
    public void setPrOnQty(String prOnQty)
    {
        this.prOnQty = prOnQty;
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
    public String getPrQty()
    {
        return prQty;
    }
    public void setPrQty(String prQty)
    {
        this.prQty = prQty;
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
    public String getUom()
    {
        return uom;
    }
    public void setUom(String uom)
    {
        this.uom = uom;
    }
    public String getErpPartNo()
    {
        return erpPartNo;
    }
    public void setErpPartNo(String erpPartNo)
    {
        this.erpPartNo = erpPartNo;
    }
    public String getUomDesc()
    {
        return uomDesc;
    }
    public void setUomDesc(String uomDesc)
    {
        this.uomDesc = uomDesc;
    }
    public String getPtModel() {
		return ptModel;
	}
	public void setPtModel(String ptModel) {
		this.ptModel = ptModel;
	}
	public String getUsedEquip()
    {
        return usedEquip;
    }
    public void setUsedEquip(String usedEquip)
    {
        this.usedEquip = usedEquip;
    }
    public String getUsedEquipDesc()
    {
        return usedEquipDesc;
    }
    public void setUsedEquipDesc(String usedEquipDesc)
    {
        this.usedEquipDesc = usedEquipDesc;
    }
    public String getUsage()
    {
        return usage;
    }
    public void setUsage(String usage)
    {
        this.usage = usage;
    }
    public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getPrDate()
    {
        return prDate;
    }
    public void setPrDate(String prDate)
    {
        this.prDate = CommonUtil.convertDate(prDate);
    }
    public String getRecDeptDesc()
    {
        return recDeptDesc;
    }
    public void setRecDeptDesc(String recDeptDesc)
    {
        this.recDeptDesc = recDeptDesc;
    }
    public String getRecDept()
    {
        return recDept;
    }
    public void setRecDept(String recDept)
    {
        this.recDept = recDept;
    }
    public String getRecDate() {
		return recDate;
	}
	public void setRecDate(String recDate) {
		this.recDate = CommonUtil.convertDate(recDate);
	}
	public String getRecBy() {
		return recBy;
	}
	public void setRecBy(String recBy) {
		this.recBy = recBy;
	}
	public String getRecByName() {
		return recByName;
	}
	public void setRecByName(String recByName) {
		this.recByName = recByName;
	}
    public String getDetailTitle()
    {
        return detailTitle;
    }
    public void setDetailTitle(String detailTitle)
    {
        this.detailTitle = detailTitle;
    }
    public String getInputStatus()
    {
        return inputStatus;
    }
    public void setInputStatus(String inputStatus)
    {
        this.inputStatus = inputStatus;
    }
    public String getInputStatusDesc()
    {
        return inputStatusDesc;
    }
    public void setInputStatusDesc(String inputStatusDesc)
    {
        this.inputStatusDesc = inputStatusDesc;
    }
    public String getReqId()
    {
        return reqId;
    }
    public void setReqId(String reqId)
    {
        this.reqId = reqId;
        super.setAuditKey(reqId);
    }
    public String getReqNo()
    {
        return reqNo;
    }
    public void setReqNo(String reqNo)
    {
        this.reqNo = reqNo;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
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
    public String getQty()
    {
        return qty;
    }
    public void setQty(String qty)
    {
        this.qty = CommonUtil.convertMoney(qty);
    }
    public String getEntDeptDesc()
    {
        return entDeptDesc;
    }
    public void setEntDeptDesc(String entDeptDesc)
    {
        this.entDeptDesc = entDeptDesc;
    }
    public String getEntDept()
    {
        return entDept;
    }
    public void setEntDept(String entDept)
    {
        this.entDept = entDept;
    }
    public String getEnterById()
    {
        return enterById;
    }
    public void setEnterById(String enterById)
    {
        this.enterById = enterById;
    }
    public String getEnterByName()
    {
        return enterByName;
    }
    public void setEnterByName(String enterByName)
    {
        this.enterByName = enterByName;
    }
    public String getReDate()
    {
        return reDate;
    }
    public void setReDate(String reDate)
    {
        this.reDate = CommonUtil.convertDate(reDate);
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
 
}
