package dream.work.close.check.form;

import common.struts.BaseForm;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * ǥ���׸� ����Ʈ - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrWorkCloseCheckPointListForm"
 */
public class MgrWorkCloseCheckPointListForm extends BaseForm
{    
    //===============================================================
    /** ǥ���׸� ���� */
    private MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = new MgrWorkCloseCheckCommonDTO();
    /** ǥ���׸� ����Ʈ */
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
