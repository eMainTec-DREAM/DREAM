package dream.asset.loc.list.form;

import common.struts.BaseForm;
import dream.asset.loc.list.dto.MaEqLocCommonDTO;
import dream.asset.loc.list.dto.MaEqLocDetailDTO;

/**
 * 설비위치- 상세 Form
 * @author  kim21017
 * @version $Id: MaEqLocDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqLocDetailForm"
 */
public class MaEqLocDetailForm extends BaseForm
{
    //========================================================================
    /** 설비위치 공통 */ 
    private MaEqLocCommonDTO maEqLocCommonDTO = new MaEqLocCommonDTO();
    //========================================================================
    /** 설비위치 상세 */
    private MaEqLocDetailDTO maEqLocDetailDTO = new MaEqLocDetailDTO();
    

	public MaEqLocCommonDTO getMaEqLocCommonDTO() {
		return maEqLocCommonDTO;
	}

	public void setMaEqLocCommonDTO(MaEqLocCommonDTO maEqLocCommonDTO) {
		this.maEqLocCommonDTO = maEqLocCommonDTO;
	}

	public MaEqLocDetailDTO getMaEqLocDetailDTO() {
		return maEqLocDetailDTO;
	}

	public void setMaEqLocDetailDTO(MaEqLocDetailDTO maEqLocDetailDTO) {
		this.maEqLocDetailDTO = maEqLocDetailDTO;
	}

}
