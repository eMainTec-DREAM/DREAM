package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultSelectDTO;

/**
 * �۾�����/���� �˾� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoResultSelectForm"
 */
public class MaWoResultSelectForm extends BaseForm
{
	private MaWoResultSelectDTO maWoResultSelectDTO  = new MaWoResultSelectDTO();

	public MaWoResultSelectDTO getMaWoResultSelectDTO() {
		return maWoResultSelectDTO;
	}

	public void setMaWoResultSelectDTO(MaWoResultSelectDTO maWoResultSelectDTO) {
		this.maWoResultSelectDTO = maWoResultSelectDTO;
	}
}
