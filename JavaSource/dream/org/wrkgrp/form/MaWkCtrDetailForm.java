package dream.org.wrkgrp.form;

import common.struts.BaseForm;
import dream.org.wrkgrp.dto.MaWkCtrCommonDTO;
import dream.org.wrkgrp.dto.MaWkCtrDetailDTO;

/**
 * �۾��׷�- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWkCtrDetailForm"
 */
public class MaWkCtrDetailForm extends BaseForm
{
    //========================================================================
    /** �۾��׷� ���� */ 
    private MaWkCtrCommonDTO maWkCtrCommonDTO = new MaWkCtrCommonDTO();
    //========================================================================
    /** �۾��׷� �� */
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
