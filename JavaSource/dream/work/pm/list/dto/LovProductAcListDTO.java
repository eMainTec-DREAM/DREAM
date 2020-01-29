package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * ������ǰ �˾� DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovProductAcListDTO extends BaseDTO
{
    /**   ����ǰ�� ID */
    private String productId            = "";
    /**   ����ǰ�� �ڵ� */
    private String productNo            = "";
    /**   ����ǰ��� */
    private String productDesc          = "";
    /**   ��� */
    private String remark               = "";
    /**   ��뿩�� */
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
