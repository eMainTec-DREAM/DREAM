package dream.org.wrkgrp.form;

import common.struts.BaseForm;
import dream.org.wrkgrp.dto.MaWkCtrCommonDTO;
import dream.org.wrkgrp.dto.MaWkCtrDetailDTO;

/**
 * 작업그룹- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWkCtrDetailForm"
 */
public class MaWkCtrDetailForm extends BaseForm
{
    //========================================================================
    /** 작업그룹 공통 */ 
    private MaWkCtrCommonDTO maWkCtrCommonDTO = new MaWkCtrCommonDTO();
    //========================================================================
    /** 작업그룹 상세 */
    private MaWkCtrDetailDTO maWkCtrDetailDTO = new MaWkCtrDetailDTO();

	public MaWkCtrCommonDTO getMaWkCtrCommonDTO() {
		return maWkCtrCommonDTO;
	}

	public void setMaWkCtrCommonDTO(MaWkCtrCommonDTO maWkCtrCommonDTO) {
		this.maWkCtrCommonDTO = maWkCtrCommonDTO;
	}

	public MaWkCtrDetailDTO getMaWkCtrDetailDTO() {
		return maWkCtrDetailDTO;
	}

	public void setMaWkCtrDetailDTO(MaWkCtrDetailDTO maWkCtrDetailDTO) {
		this.maWkCtrDetailDTO = maWkCtrDetailDTO;
	}
}
