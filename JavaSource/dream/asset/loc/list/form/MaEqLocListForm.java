package dream.asset.loc.list.form;

import common.struts.BaseForm;
import dream.asset.loc.list.dto.MaEqLocCommonDTO;

/**
 * 설비위치 - 목록
 * @author  kim21017
 * @version $Id: MaEqLocListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqLocListForm"
 */
public class MaEqLocListForm extends BaseForm
{    
    //===============================================================
    /** 설비위치 공통 */
    private MaEqLocCommonDTO maEqLocCommonDTO = new MaEqLocCommonDTO();

	public MaEqLocCommonDTO getMaEqLocCommonDTO() {
		return maEqLocCommonDTO;
	}

	public void setMaEqLocCommonDTO(MaEqLocCommonDTO maEqLocCommonDTO) {
		this.maEqLocCommonDTO = maEqLocCommonDTO;
	}

}
