package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.AssetListITSWDetailDTO;
import dream.asset.list.dto.AssetListITSWListDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;

/**
 * List Form
 * @author youngjoo38
 * @version $Id: AssetListITSWListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="assetListITSWListForm"
 * */
public class AssetListITSWListForm extends BaseForm {
    
    private AssetListITSWListDTO assetListITSWListDTO = new AssetListITSWListDTO();
    private AssetListITSWDetailDTO assetListITSWDetailDTO = new AssetListITSWDetailDTO();
    private MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();

	public AssetListITSWDetailDTO getAssetListITSWDetailDTO() {
		return assetListITSWDetailDTO;
	}

	public MaEqMstrDetailDTO getMaEqMstrDetailDTO() {
		return maEqMstrDetailDTO;
	}

	public void setMaEqMstrDetailDTO(MaEqMstrDetailDTO maEqMstrDetailDTO) {
		this.maEqMstrDetailDTO = maEqMstrDetailDTO;
	}

	public void setAssetListITSWDetailDTO(AssetListITSWDetailDTO assetListITSWDetailDTO) {
		this.assetListITSWDetailDTO = assetListITSWDetailDTO;
	}

	public AssetListITSWListDTO getAssetListITSWListDTO() {
        return assetListITSWListDTO;
    }

    public void setAssetListITSWListDTO(AssetListITSWListDTO assetListITSWListDTO) {
        this.assetListITSWListDTO = assetListITSWListDTO;
    }
}