package dream.work.close.check.form;

import common.struts.BaseForm;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * 표준항목 리스트 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrWorkCloseCheckPointListForm"
 */
public class MgrWorkCloseCheckPointListForm extends BaseForm
{    
    //===============================================================
    /** 표준항목 공통 */
    private MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = new MgrWorkCloseCheckCommonDTO();
    /** 표준항목 리스트 */
    private MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO = new MgrWorkCloseCheckPointListDTO();
    

	public MgrWorkCloseCheckCommonDTO getMgrWorkCloseCheckCommonDTO() {
		return mgrWorkCloseCheckCommonDTO;
	}

	public void setMgrWorkCloseCheckCommonDTO(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO) {
		this.mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckCommonDTO;
	}

	public MgrWorkCloseCheckPointListDTO getMgrWorkCloseCheckPointListDTO() {
		return mgrWorkCloseCheckPointListDTO;
	}

	public void setMgrWorkCloseCheckPointListDTO(MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO) {
		this.mgrWorkCloseCheckPointListDTO = mgrWorkCloseCheckPointListDTO;
	}
	
}
