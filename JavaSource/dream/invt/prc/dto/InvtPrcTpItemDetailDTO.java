package dream.invt.prc.dto;

import common.bean.BaseDTO;


/**
 * �������� Item ��  DTO
 * @author  hyosung
 * @version $Id: InvtPrcTpItemDetailDTO.java,v 1.1 2015/12/04 09:10:45 hyosung Exp $
 * @since   1.0
 */
public class InvtPrcTpItemDetailDTO extends BaseDTO
{
	
	/** ���������ܰ� ID */
	private String invtPrcPhId     ="";
	
	/** ��ȸ���� */
	private String ordNo           ="";
	/** ��뿩�� */
	private String isUse           ="";
	private String isUseDesc       ="";
	/** ��з�NO */
	private String invt_LTypeNo    ="";
	/** ��з��� */
	private String invt_LTypeDesc  ="";
	/** �Һз�NO */
	private String invt_STypeNo    ="";
	/** �Һз��� */
	private String invt_STypeDesc  ="";
	/** �����μ��� */
	private String invtRefDept     ="";
	/** ���ù����� */
	private String invtRefDoc      ="";
	/** ��� */
	private String remark          ="";
	/** ��ȣ���� Prefix */
    private String docPrefix       = "";

    
    public String getDocPrefix()
    {
        return docPrefix;
    }

    public void setDocPrefix(String docPrefix)
    {
        this.docPrefix = docPrefix;
    }

    public String getInvtPrcPhId()
    {
        return invtPrcPhId;
    }

    public void setInvtPrcPhId(String invtPrcPhId)
    {
        this.invtPrcPhId = invtPrcPhId;
        super.setAuditKey(invtPrcPhId);
    }

    public String getOrdNo()
    {
        return ordNo;
    }

    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
    }

    public String getIsUse()
    {
        return isUse;
    }

    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }

    public String getIsUseDesc()
    {
        return isUseDesc;
    }

    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }

    public String getInvt_LTypeNo()
    {
        return invt_LTypeNo;
    }

    public void setInvt_LTypeNo(String invt_LTypeNo)
    {
        this.invt_LTypeNo = invt_LTypeNo;
    }

    public String getInvt_LTypeDesc()
    {
        return invt_LTypeDesc;
    }

    public void setInvt_LTypeDesc(String invt_LTypeDesc)
    {
        this.invt_LTypeDesc = invt_LTypeDesc;
    }

    public String getInvt_STypeNo()
    {
        return invt_STypeNo;
    }

    public void setInvt_STypeNo(String invt_STypeNo)
    {
        this.invt_STypeNo = invt_STypeNo;
    }

    public String getInvt_STypeDesc()
    {
        return invt_STypeDesc;
    }

    public void setInvt_STypeDesc(String invt_STypeDesc)
    {
        this.invt_STypeDesc = invt_STypeDesc;
    }

    public String getInvtRefDept()
    {
        return invtRefDept;
    }

    public void setInvtRefDept(String invtRefDept)
    {
        this.invtRefDept = invtRefDept;
    }

    public String getInvtRefDoc()
    {
        return invtRefDoc;
    }

    public void setInvtRefDoc(String invtRefDoc)
    {
        this.invtRefDoc = invtRefDoc;
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