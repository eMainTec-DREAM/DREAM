package dream.asset.std.product.dto;

import common.bean.BaseDTO;

/**
 * 积魂前格 傍烹 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class AssetStdProductCommonDTO extends BaseDTO
{
	/** 积魂前格Id */
	private String productId                   = "";
	/** 鞘磐 积魂前格内靛 */
	private String filterProductNo            	= "";
	/** 鞘磐 积魂前格疙 */
	private String filterDescription 		= "";
	/** 荤侩咯何 */
	private String filterIsUse              = "";
	
    public String getProductId()
    {
        return productId;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
        super.setAuditKey(productId);
    }
    public String getFilterProductNo()
    {
        return filterProductNo;
    }
    public void setFilterProductNo(String filterProductNo)
    {
        this.filterProductNo = filterProductNo;
    }
    public String getFilterDescription()
    {
        return filterDescription;
    }
    public void setFilterDescription(String filterDescription)
    {
        this.filterDescription = filterDescription;
    }
    public String getFilterIsUse()
    {
        return filterIsUse;
    }
    public void setFilterIsUse(String filterIsUse)
    {
        this.filterIsUse = filterIsUse;
    }
	
}
