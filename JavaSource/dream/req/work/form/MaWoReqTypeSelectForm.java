package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.MaWoReqTypeSelectDTO;

/**
 * �۾���û���� �˾� Form
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoReqTypeSelectForm"
 */
public class MaWoReqTypeSelectForm extends BaseForm
{
	private MaWoReqTypeSelectDTO maWoReqTypeSelectDTO  = new MaWoReqTypeSelectDTO();

	public MaWoReqTypeSelectDTO getMaWoReqTypeSelectDTO() {
		return maWoReqTypeSelectDTO;
	}

	public void setMaWoReqTypeSelectDTO(MaWoReqTypeSelectDTO maWoReqTypeSelectDTO) {
		this.maWoReqTypeSelectDTO = maWoReqTypeSelectDTO;
	}
}
