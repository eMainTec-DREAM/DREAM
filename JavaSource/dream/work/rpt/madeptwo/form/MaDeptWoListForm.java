package dream.work.rpt.madeptwo.form;

import common.struts.BaseForm;
import dream.work.rpt.madeptwo.dto.MaDeptWoListDTO;

/**
 * 부서별작업분석
 * @author  kim21017
 * @version $Id: MaDeptWoListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maDeptWoListForm"
 */
public class MaDeptWoListForm extends BaseForm
{    
    //===============================================================
    /** 부서별작업분석 */
    private MaDeptWoListDTO maDeptWoListDTO = new MaDeptWoListDTO();

	public MaDeptWoListDTO getMaDeptWoListDTO() {
		return maDeptWoListDTO;
	}

	public void setMaDeptWoListDTO(MaDeptWoListDTO maDeptWoListDTO) {
		this.maDeptWoListDTO = maDeptWoListDTO;
	}

}
