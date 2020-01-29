package dream.asset.std.asset.dto;

import common.bean.BaseDTO;

/**
 * ȸ���ڻ� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class AssetStdAssetCommonDTO extends BaseDTO
{
	/** ȸ���ڻ�Id */
	private String assetId                   = "";
	/** ���� ȸ���ڻ��ڵ� */
	private String filterAssetNo            	= "";
	/** ���� ȸ���ڻ�� */
	private String filterDescription 		= "";
	/** ��뿩�� */
	private String filterIsUse              = "";
	
    public String getAssetId()
    {
        return assetId;
    }
    public void setAssetId(String assetId)
    {
        this.assetId = assetId;
        super.setAuditKey(assetId);
    }
    public String getFilterAssetNo()
    {
        return filterAssetNo;
    }
    public void setFilterAssetNo(String filterAssetNo)
    {
        this.filterAssetNo = filterAssetNo;
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
