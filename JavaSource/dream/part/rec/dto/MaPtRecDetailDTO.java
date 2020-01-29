package dream.part.rec.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����԰� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtRecDetailDTO extends BaseDTO
{
	/** �����԰�ID */
	private String prRecListId     = "";
	/** �԰��ȣ */
	private String prRecListNo     = "";
	/** �԰����Id */
	private String prRecListStatus = "";
	/** �԰���¸� */
	private String prRecListStatusDesc = "";
	/** ���μ�Id */
	private String deptId          = "";
	/** ���μ��� */
	private String deptDesc        = "";
	/** �԰�â��Id */
	private String wcodeId         = "";
	/** �԰�â��� */
	private String wname           = "";
	/** �ŷ�ó Id */
	private String vendorId        = "";
	/** �ŷ�ó�� */
	private String vendorDesc      = "";
	/** �԰����� */
	private String recDate         = "";
	/** �԰�ǰ�� ����Id */
	private String partId          = "";
	/** �԰�ǰ�� �����ȣ */
	private String partNo          = "";
	/** �԰�ǰ�� �����/�԰� */
	private String partNameSize    = "";
	/** �԰���� */
	private String recQty          = "";
	/** �԰�ܰ� */
	private String unitPrice       = "";
	/** �԰�ݾ� */
	private String totPrice        = "";
	/** �˼���Id */
	private String inspector       = "";
	/** �˼��ڸ� */
	private String inspectorName   = "";
	/** ��� */
	private String remark          = "";
	/** ������ */
	private String partGrade       = "";
	/** �����޸� */
	private String partGradeDesc   = "";
	
	private String isSerial        = "";
	
	private String countWo         = "";
	
	/** �԰�ǰ�� ����� */
	private String partDesc        = "";
	/** �԰�ǰ�� ����԰� */
	private String partSize    	   = "";
	/** �԰�ǰ�� model */
	private String model    	   = "";
	/** �԰�ǰ�� ���� */
	private String uom    		   = "";
	/** ǰ�� ���� ���� ID */
	private String isMakePartNoId  = "";
	/** ǰ�� ���� ���� */
	private String isMakePartNo    = "";

	/** ����  */
	private String plantId		   = "";
	/** �����  */
	private String plantDesc	   = "";
	
	private String invtlistId      = "";
	
	private String invtlistDesc    = "";
	
	private String polistId        = "";
	
	private String polistDesc      = "";
	
	private String ptpritemId      = "";
	
	private String poitemId        = "";
	/** ȭ����� ID */
	private String currencyId		= "";
	/** ȭ����� DESC */
	private String currencyDesc		= "";
	
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyDesc() {
		return currencyDesc;
	}
	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}
	public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public String getPoitemId()
    {
        return poitemId;
    }
    public void setPoitemId(String poitemId)
    {
        this.poitemId = poitemId;
    }
    public String getPtpritemId()
    {
        return ptpritemId;
    }
    public void setPtpritemId(String ptpritemId)
    {
        this.ptpritemId = ptpritemId;
    }
    public String getPolistId()
    {
        return polistId;
    }
    public void setPolistId(String polistId)
    {
        this.polistId = polistId;
    }
    public String getPolistDesc()
    {
        return polistDesc;
    }
    public void setPolistDesc(String polistDesc)
    {
        this.polistDesc = polistDesc;
    }
    public String getInvtlistId()
    {
        return invtlistId;
    }
    public void setInvtlistId(String invtlistId)
    {
        this.invtlistId = invtlistId;
    }
    public String getInvtlistDesc()
    {
        return invtlistDesc;
    }
    public void setInvtlistDesc(String invtlistDesc)
    {
        this.invtlistDesc = invtlistDesc;
    }
    public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getIsMakePartNoId() {
		return isMakePartNoId;
	}
	public void setIsMakePartNoId(String isMakePartNoId) {
		this.isMakePartNoId = isMakePartNoId;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public String getIsMakePartNo() {
		return isMakePartNo;
	}
	public void setIsMakePartNo(String isMakePartNo) {
		this.isMakePartNo = isMakePartNo;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getPartSize() {
		return partSize;
	}
	public void setPartSize(String partSize) {
		this.partSize = partSize;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getCountWo() {
		return countWo;
	}
	public void setCountWo(String countWo) {
		this.countWo = countWo;
	}
	public String getIsSerial() {
		return isSerial;
	}
	public void setIsSerial(String isSerial) {
		this.isSerial = isSerial;
	}
	public String getPartGrade()
    {
        return partGrade;
    }
    public void setPartGrade(String partGrade)
    {
        this.partGrade = partGrade;
    }
    public String getPartGradeDesc() {
		return partGradeDesc;
	}
	public void setPartGradeDesc(String partGradeDesc) {
		this.partGradeDesc = partGradeDesc;
	}
	/** �԰��̷� - C:�԰�Ϸ�, R:�԰���� */
	private String ptRecMode   = "";

    public String getPtRecMode()
    {
        return ptRecMode;
    }
    public void setPtRecMode(String ptRecMode)
    {
        this.ptRecMode = ptRecMode;
    }
    public String getWname()
    {
        return wname;
    }
    public void setWname(String wname)
    {
        this.wname = wname;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getPrRecListId()
    {
        return prRecListId;
    }
    public void setPrRecListId(String prRecListId)
    {
        this.prRecListId = prRecListId;
        super.setAuditKey(prRecListId);
    }
    public String getPrRecListNo()
    {
        return prRecListNo;
    }
    public void setPrRecListNo(String prRecListNo)
    {
        this.prRecListNo = prRecListNo;
    }
    public String getPrRecListStatus()
    {
        return prRecListStatus;
    }
    public void setPrRecListStatus(String prRecListStatus)
    {
        this.prRecListStatus = prRecListStatus;
    }
    public String getPrRecListStatusDesc()
    {
        return prRecListStatusDesc;
    }
    public void setPrRecListStatusDesc(String prRecListStatusDesc)
    {
        this.prRecListStatusDesc = prRecListStatusDesc;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getVendorId()
    {
        return vendorId;
    }
    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
    }
    public String getVendorDesc()
    {
        return vendorDesc;
    }
    public void setVendorDesc(String vendorDesc)
    {
        this.vendorDesc = vendorDesc;
    }
    public String getRecDate()
    {
        return recDate;
    }
    public void setRecDate(String recDate)
    {
        this.recDate = CommonUtil.convertDate(recDate);
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartNameSize()
    {
        return partNameSize;
    }
    public void setPartNameSize(String partNameSize)
    {
        this.partNameSize = partNameSize;
    }
    public String getRecQty()
    {
        return recQty;
    }
    public void setRecQty(String recQty)
    {
        this.recQty = CommonUtil.convertMoney(recQty);
    }
    public String getUnitPrice()
    {
        return unitPrice;
    }
    public void setUnitPrice(String unitPrice)
    {
        this.unitPrice = CommonUtil.convertMoney(unitPrice);
    }
    public String getTotPrice()
    {
        return totPrice;
    }
    public void setTotPrice(String totPrice)
    {
        this.totPrice = CommonUtil.convertMoney(totPrice);
    }
    public String getInspector()
    {
        return inspector;
    }
    public void setInspector(String inspector)
    {
        this.inspector = inspector;
    }
    public String getInspectorName()
    {
        return inspectorName;
    }
    public void setInspectorName(String inspectorName)
    {
        this.inspectorName = inspectorName;
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
