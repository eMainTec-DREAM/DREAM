package dream.asset.rpt.lcc.equip.form;

import common.struts.BaseForm;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipCommonDTO;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipDetailDTO;

/**
 * ����TOP(����) ��
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetRptLccEquipDetailForm"
 */
public class AssetRptLccEquipDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO = new AssetRptLccEquipCommonDTO();
    
    private AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO = new AssetRptLccEquipDetailDTO();
    
    public AssetRptLccEquipCommonDTO getAssetRptLccEquipCommonDTO()
    {
        return assetRptLccEquipCommonDTO;
    }

    public void setAssetRptLccEquipCommonDTO(
            AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO)
    {
        this.assetRptLccEquipCommonDTO = assetRptLccEquipCommonDTO;
    }
    
    public AssetRptLccEquipDetailDTO getAssetRptLccEquipDetailDTO()
    {
        return assetRptLccEquipDetailDTO;
    }

    public void setAssetRptLccEquipDetailDTO(
            AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO)
    {
        this.assetRptLccEquipDetailDTO = assetRptLccEquipDetailDTO;
    }
	
}
