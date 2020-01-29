package dream.asset.std.product.dto;

import common.bean.BaseDTO;

/**
 * ����ǰ�� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class AssetStdProductCommonDTO extends BaseDTO
{
	/** ����ǰ��Id */
	private String productId                   = "";
	/** ���� ����ǰ���ڵ� */
	private String filterProductNo            	= "";
	/** ���� ����ǰ��� */
	private String filterDescription 		= "";
	/** ��뿩�� */
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
