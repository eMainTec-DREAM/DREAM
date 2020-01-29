package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사용자재 상세 DTO
 * @author  jung7126
 * @version $Id: MaPmMstrPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class MaPmMstrPartDetailDTO extends BaseDTO
{
	/** 작업결과 투입자재 id */
	private String pmPartId			= "";
	/** 투입자재id*/
	private String partId			= "";
	/** 투입자재No*/
	private String partNo			= "";
	/** 투입자재명 */
	private String partDesc			= "";
	/** 규격 */
	private String ptSize			= "";
	/** 모델 */
	private String model			= "";
	/** 사용수량 */
	private String useQty 			= "";
    /** MultiSelect 표준부품 ID */
    private String multiStdPartKey		= "";
    /** MultiSelect 표준부품 DESC */
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