package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * 积魂力前 扑诀 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovProductAcListDTO extends BaseDTO
{
    /**   积魂前格 ID */
    private String productId            = "";
    /**   积魂前格 内靛 */
    private String productNo            = "";
    /**   积魂前格疙 */
    private String productDesc          = "";
    /**   厚绊 */
    private String remark               = "";
    /**   荤侩咯何 */
    private String isUse            = "";
    
    public String getProductId()
    {
        return productId;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
    }
    public String getProductNo()
    {
        return productNo;
    }
    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }
    public String getProductDesc()
    {
        return productDesc;
    }
    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
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
