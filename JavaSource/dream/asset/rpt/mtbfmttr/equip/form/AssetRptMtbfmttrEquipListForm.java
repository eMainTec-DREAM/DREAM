package dream.asset.rpt.mtbfmttr.equip.form;

import common.struts.BaseForm;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipDetailDTO;

/**
 * MTBF,MTTR(설비)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetRptMtbfmttrEquipListForm"
 */
public class AssetRptMtbfmttrEquipListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO = new AssetRptMtbfmttrEquipCommonDTO();
    
    private AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO = new AssetRptMtbfmttrEquipDetailDTO();
    
    public AssetRptMtbfmttrEquipCommonDTO getAssetRptMtbfmttrEquipCommonDTO()
    {
        return assetRptMtbfmttrEquipCommonDTO;
    }

    public void setAssetRptMtbfmttrEquipCommonDTO(
            AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO)
    {
        this.assetRptMtbfmttrEquipCommonDTO = assetRptMtbfmttrEquipCommonDTO;
    }
    
    public AssetRptMtbfmttrEquipDetailDTO getAssetRptMtbfmttrEquipDetailDTO()
    {
        return assetRptMtbfmttrEquipDetailDTO;
    }

    public void setAssetRptMtbfmttrEquipDetailDTO(
            AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO)
    {
        this.assetRptMtbfmttrEquipDetailDTO = assetRptMtbfmttrEquipDetailDTO;
    }
	
}
