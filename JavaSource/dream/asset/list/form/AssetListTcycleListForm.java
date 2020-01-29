package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.AssetListTcycleListDTO;

/**
 * 설비제원(스펙) 목록
 * @author  kim21017
 * @version $Id: AssetListTcycleListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="assetListTcycleListForm"
 */
public class AssetListTcycleListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  설비제원(스펙) 목록  */
    private AssetListTcycleListDTO assetListTcycleListDTO = new AssetListTcycleListDTO();
	

	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}

	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}

	public AssetListTcycleListDTO getAssetListTcycleListDTO() {
		return assetListTcycleListDTO;
	}

	public void setAssetListTcycleListDTO(AssetListTcycleListDTO assetListTcycleListDTO) {
		this.assetListTcycleListDTO = assetListTcycleListDTO;
	}
}
