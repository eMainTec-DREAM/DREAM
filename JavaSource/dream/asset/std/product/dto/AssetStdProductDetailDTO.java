package dream.asset.std.product.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 积魂前格 - 惑技 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class AssetStdProductDetailDTO extends BaseDTO
{
	/** 积魂前格ID */
	private String productId 			= "";
	/** 积魂前格内靛 */
	private String productNo 			= "";
	/** 积魂前格疙 */
	private String description 		= "";
	/** 荤侩咯何 */
	private String isUse 			= "";
	/** 厚绊 */
	private String remark           = "";
	
    public String getProductId()
    {
        return productId;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
        super.setAuditKey(productId);
    }
    public String getProductNo()
    {
        return productNo;
    }
    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
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
