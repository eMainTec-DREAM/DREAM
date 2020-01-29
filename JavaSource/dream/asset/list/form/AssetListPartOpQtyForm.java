package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.AssetListPartOpQtyDTO;
/**
 * 설비운용기간사용예상수량
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts.form name="assetListPartOpQtyForm"
 */
public class AssetListPartOpQtyForm extends BaseForm{
	private AssetListPartOpQtyDTO assetListPartOpQtyDTO = new AssetListPartOpQtyDTO();

    public AssetListPartOpQtyDTO getAssetListPartOpQtyDTO()
    {
        return assetListPartOpQtyDTO;
    }

    public void setAssetListPartOpQtyDTO(AssetListPartOpQtyDTO assetListPartOpQtyDTO)
    {
        this.assetListPartOpQtyDTO = assetListPartOpQtyDTO;
    }

}
