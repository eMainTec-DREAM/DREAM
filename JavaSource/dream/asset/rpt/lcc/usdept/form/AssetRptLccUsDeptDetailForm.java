package dream.asset.rpt.lcc.usdept.form;

import common.struts.BaseForm;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptDetailDTO;

/**
 * 고장TOP(사용부서) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetRptLccUsDeptDetailForm"
 */
public class AssetRptLccUsDeptDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO = new AssetRptLccUsDeptCommonDTO();
    
    private AssetRptLccUsDeptDetailDTO assetRptLccUsDeptDetailDTO = new AssetRptLccUsDeptDetailDTO();
    
    public AssetRptLccUsDeptCommonDTO getAssetRptLccUsDeptCommonDTO()
    {
        return assetRptLccUsDeptCommonDTO;
    }

    public void setAssetRptLccUsDeptCommonDTO(
            AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO)
    {
        this.assetRptLccUsDeptCommonDTO = assetRptLccUsDeptCommonDTO;
    }
    
    public AssetRptLccUsDeptDetailDTO getAssetRptLccUsDeptDetailDTO()
    {
        return assetRptLccUsDeptDetailDTO;
    }

    public void setAssetRptLccUsDeptDetailDTO(
            AssetRptLccUsDeptDetailDTO assetRptLccUsDeptDetailDTO)
    {
        this.assetRptLccUsDeptDetailDTO = assetRptLccUsDeptDetailDTO;
    }
	
}
