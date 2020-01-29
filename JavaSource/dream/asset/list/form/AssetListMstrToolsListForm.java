package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.AssetListMstrToolsListDTO;

/**
 * �������� ���
 * @author  kim21017
 * @version $Id: AssetListMstrToolsListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="assetListMstrToolsListForm"
 */
public class AssetListMstrToolsListForm extends BaseForm
{
    //===============================================================
    /** ���� */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  �������� ���  */
    private AssetListMstrToolsListDTO assetListMstrToolsListDTO = new AssetListMstrToolsListDTO();
    /** ���񸶽��� �� */
    private MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();
    

	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}

	public MaEqMstrDetailDTO getMaEqMstrDetailDTO() {
		return maEqMstrDetailDTO;
	}

	public void setMaEqMstrDetailDTO(MaEqMstrDetailDTO maEqMstrDetailDTO) {
		this.maEqMstrDetailDTO = maEqMstrDetailDTO;
	}

	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}

	public AssetListMstrToolsListDTO getAssetListMstrToolsListDTO() {
		return assetListMstrToolsListDTO;
	}

	public void setAssetListMstrToolsListDTO(AssetListMstrToolsListDTO assetListMstrToolsListDTO) {
		this.assetListMstrToolsListDTO = assetListMstrToolsListDTO;
	}
}
