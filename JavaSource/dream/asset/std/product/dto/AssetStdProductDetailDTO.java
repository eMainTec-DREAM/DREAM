package dream.asset.std.product.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����ǰ�� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class AssetStdProductDetailDTO extends BaseDTO
{
	/** ����ǰ��ID */
	private String productId 			= "";
	/** ����ǰ���ڵ� */
	private String productNo 			= "";
	/** ����ǰ��� */
	private String description 		= "";
	/** ��뿩�� */
	private String isUse 			= "";
	/** ��� */
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
