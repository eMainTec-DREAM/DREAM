package dream.asset.rpt.maintcost.form;

import common.struts.BaseForm;
import dream.asset.rpt.maintcost.dto.AssetRptMaintCostCommonDTO;


/**
 * ���������� ������Ȳ ��� - List Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="assetRptMaintCostListForm"
 * */
public class AssetRptMaintCostListForm extends BaseForm {
    
    private AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO = new AssetRptMaintCostCommonDTO();

    public AssetRptMaintCostCommonDTO getAssetRptMaintCostCommonDTO() {
        return assetRptMaintCostCommonDTO;
    }

    public void setAssetRptMaintCostCommonDTO(AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO) {
        this.assetRptMaintCostCommonDTO = assetRptMaintCostCommonDTO;
    }

}