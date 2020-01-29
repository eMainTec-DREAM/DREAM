package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� �� DTO
 * @author  jung7126
 * @version $Id: MaPmMstrPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class MaPmMstrPartDetailDTO extends BaseDTO
{
	/** �۾���� �������� id */
	private String pmPartId			= "";
	/** ��������id*/
	private String partId			= "";
	/** ��������No*/
	private String partNo			= "";
	/** ��������� */
	private String partDesc			= "";
	/** �԰� */
	private String ptSize			= "";
	/** �� */
	private String model			= "";
	/** ������ */
	private String useQty 			= "";
    /** MultiSelect ǥ�غ�ǰ ID */
    private String multiStdPartKey		= "";
    /** MultiSelect ǥ�غ�ǰ DESC */
    private String multiStdPartDesc 	= "";
	
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
    public String getPmPartId()
    {
        return pmPartId;
    }
    public void setPmPartId(String pmPartId)
    {
        this.pmPartId = pmPartId;
        super.setAuditKey(pmPartId);
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
    public String getUseQty()
    {
        return useQty;
    }
    public void setUseQty(String useQty)
    {
        this.useQty = CommonUtil.convertMoney(useQty);
    }
	public String getMultiStdPartKey() {
		return multiStdPartKey;
	}
	public void setMultiStdPartKey(String multiStdPartKey) {
		this.multiStdPartKey = multiStdPartKey;
	}
	public String getMultiStdPartDesc() {
		return multiStdPartDesc;
	}
	public void setMultiStdPartDesc(String multiStdPartDesc) {
		this.multiStdPartDesc = multiStdPartDesc;
	}
    
}