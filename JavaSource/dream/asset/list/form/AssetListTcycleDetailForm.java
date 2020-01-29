package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.AssetListTcycleListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.AssetListTcycleDetailDTO;

/**
 * 교정주기
 * @author  kim2107
 * @version $Id: AssetListTcycleDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="assetListTcycleDetailForm"
 */
public class AssetListTcycleDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** 교정주기 목록 DTO  */
    private AssetListTcycleListDTO AssetListTcycleListDTO = new AssetListTcycleListDTO();
	/** 교정주기 상세 DTO  */
    private AssetListTcycleDetailDTO assetListTcycleDetailDTO = new AssetListTcycleDetailDTO();
    
	public AssetListTcycleListDTO getAssetListTcycleListDTO() {
		return AssetListTcycleListDTO;
	}
	public void setAssetListTcycleListDTO(
			AssetListTcycleListDTO assetListTcycleListDTO) {
		AssetListTcycleListDTO = assetListTcycleListDTO;
	}
	public AssetListTcycleDetailDTO getAssetListTcycleDetailDTO() {
		return assetListTcycleDetailDTO;
	}
	public void setAssetListTcycleDetailDTO(AssetListTcycleDetailDTO assetListTcycleDetailDTO) {
		this.assetListTcycleDetailDTO = assetListTcycleDetailDTO;
	}
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
}
