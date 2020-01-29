package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.MaWoReqTypeSelectDTO;

/**
 * 작업요청유형 팝업 Form
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
