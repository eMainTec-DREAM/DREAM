package dream.tool.rec.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����԰� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPttRecDetailDTO extends BaseDTO
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
