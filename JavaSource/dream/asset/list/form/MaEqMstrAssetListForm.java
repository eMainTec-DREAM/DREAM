package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * �����ڻ� ���
 * @author  kim21017
 * @version $Id: MaEqMstrAssetListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrAssetListForm"
 */
public class MaEqMstrAssetListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  �����ڻ� ���  */
    private MaEqMstrAssetListDTO maEqMstrAssetListDTO = new MaEqMstrAssetListDTO();
	

	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}

	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}

	public MaEqMstrAssetListDTO getMaEqMstrAssetListDTO() {
		return maEqMstrAssetListDTO;
	}

	public void setMaEqMstrAssetListDTO(MaEqMstrAssetListDTO maEqMstrAssetListDTO) {
		this.maEqMstrAssetListDTO = maEqMstrAssetListDTO;
	}
}
