package dream.comm.form;

import common.struts.BaseForm;

import dream.comm.dto.CommLogScrnTraceDTO;

/**
 * AutoComplete Form
 * @author  jung7126
 * @version $Id: ValidationForm.java,v 1.1 2013/08/30 09:12:46 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="commLogScreenTraceForm"
 */
public class CommLogScreenTraceForm extends BaseForm
{
    
	private CommLogScrnTraceDTO commLogScrnTraceDTO = new CommLogScrnTraceDTO();
	
	

	public CommLogScrnTraceDTO getCommLogScrnTraceDTO() {
		return commLogScrnTraceDTO;
	}

	public void setCommLogScrnTraceDTO(CommLogScrnTraceDTO commLogScrnTraceDTO) {
		this.commLogScrnTraceDTO = commLogScrnTraceDTO;
	}
	
	
	
    
}
