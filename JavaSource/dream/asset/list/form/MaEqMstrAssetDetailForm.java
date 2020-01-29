package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrAssetDetailDTO;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 관련자산
 * @author  kim2107
 * @version $Id: MaEqMstrAssetDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrAssetDetailForm"
 */
public class MaEqMstrAssetDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** 관련자산 목록 DTO  */
    private MaEqMstrAssetListDTO maEqMstrAssetListDTO = new MaEqMstrAssetListDTO();
	/** 관련자산 상세 DTO  */
    private MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO = new MaEqMstrAssetDetailDTO();
    
	public MaEqMstrAssetListDTO getMaEqMstrAssetListDTO() {
		return maEqMstrAssetListDTO;
	}
	public void setMaEqMstrAssetListDTO(MaEqMstrAssetListDTO maEqMstrAssetListDTO) {
		this.maEqMstrAssetListDTO = maEqMstrAssetListDTO;
	}
	public MaEqMstrAssetDetailDTO getMaEqMstrAssetDetailDTO() {
		return maEqMstrAssetDetailDTO;
	}
	public void setMaEqMstrAssetDetailDTO(MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO) {
		this.maEqMstrAssetDetailDTO = maEqMstrAssetDetailDTO;
	}
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
}
