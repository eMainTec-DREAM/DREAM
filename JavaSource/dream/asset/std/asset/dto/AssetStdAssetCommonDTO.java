package dream.asset.std.asset.dto;

import common.bean.BaseDTO;

/**
 * 회계자산 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class AssetStdAssetCommonDTO extends BaseDTO
{
	/** 회계자산Id */
	private String assetId                   = "";
	/** 필터 회계자산코드 */
	private String filterAssetNo            	= "";
	/** 필터 회계자산명 */
	private String filterDescription 		= "";
	/** 사용여부 */
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
