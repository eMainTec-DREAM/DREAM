package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqCtgTypeSelectDTO;

/**
 * 설비유형 팝업 Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maEqCtgTypeSelectForm"
 */
public class MaEqCtgTypeSelectForm extends BaseForm
{
	private MaEqCtgTypeSelectDTO maEqCtgTypeSelectDTO  = new MaEqCtgTypeSelectDTO();

	public MaEqCtgTypeSelectDTO getMaEqCtgTypeSelectDTO() {
		return maEqCtgTypeSelectDTO;
	}

	public void setMaEqCtgTypeSelectDTO(MaEqCtgTypeSelectDTO maEqCtgTypeSelectDTO) {
		this.maEqCtgTypeSelectDTO = maEqCtgTypeSelectDTO;
	}
}
