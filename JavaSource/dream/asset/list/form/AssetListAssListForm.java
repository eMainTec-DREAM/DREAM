package dream.asset.list.form;

import common.struts.BaseForm;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetDetailDTO;
import dream.asset.list.dto.AssetListAssListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;


/**
 * AssetListAss Page - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="assetListAssListForm"
 * */
public class AssetListAssListForm extends BaseForm {
    private AssAssetCommonDTO assAssetCommonDTO = new AssAssetCommonDTO();
    private AssAssetDetailDTO assAssetDetailDTO = new AssAssetDetailDTO();
    private AssetListAssListDTO assetListAssListDTO = new AssetListAssListDTO();
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();

    public AssAssetDetailDTO getAssAssetDetailDTO()
    {
        return assAssetDetailDTO;
    }

    public void setAssAssetDetailDTO(AssAssetDetailDTO assAssetDetailDTO)
    {
        this.assAssetDetailDTO = assAssetDetailDTO;
    }

    public AssAssetCommonDTO getAssAssetCommonDTO()
    {
        return assAssetCommonDTO;
    }

    public void setAssAssetCommonDTO(AssAssetCommonDTO assAssetCommonDTO)
    {
        this.assAssetCommonDTO = assAssetCommonDTO;
    }

    public MaEqMstrCommonDTO getMaEqMstrCommonDTO()
    {
        return maEqMstrCommonDTO;
    }

    public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO)
    {
        this.maEqMstrCommonDTO = maEqMstrCommonDTO;
    }

    public AssetListAssListDTO getAssetListAssListDTO() {
        return assetListAssListDTO;
    }

    public void setAssetListAssListDTO(AssetListAssListDTO assetListAssListDTO) {
        this.assetListAssListDTO = assetListAssListDTO;
    }
}