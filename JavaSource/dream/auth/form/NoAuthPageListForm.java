package dream.auth.form;

import common.struts.BaseForm;
import dream.auth.dto.NoAuthPageCommonDTO;

/**
 * 권한없음 페이지 form
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="noAuthPageListForm"
 */
public class NoAuthPageListForm extends BaseForm
{    
    /** 공통 */
    private NoAuthPageCommonDTO noAuthPageCommonDTO = new NoAuthPageCommonDTO();

	public NoAuthPageCommonDTO getNoAuthPageCommonDTO() {
		return noAuthPageCommonDTO;
	}

	public void setNoAuthPageCommonDTO(NoAuthPageCommonDTO noAuthPageCommonDTO) {
		this.noAuthPageCommonDTO = noAuthPageCommonDTO;
	};

    
}
