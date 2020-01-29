package dream.work.close.check.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointDetailDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * ǥ���׸� ����Ʈ- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrWorkCloseCheckPointDetailForm"
 */
public class MgrWorkCloseCheckPointDetailForm extends BaseForm
{
    //========================================================================
    /** ǥ���׸� ���� */ 
    private MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = new MgrWorkCloseCheckCommonDTO();
    //========================================================================
    /** ǥ���׸񸮚� ��� */
    private MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO = new MgrWorkCloseCheckPointListDTO();
    /** ǥ���׸񸮚� �� */
    private MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO = new MgrWorkCloseCheckPointDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
	public MgrWorkCloseCheckCommonDTO getMgrWorkCloseCheckCommonDTO() {
		return mgrWorkCloseCheckCommonDTO;
	}

	public void setMgrWorkCloseCheckCommonDTO(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO) {
		this.mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckCommonDTO;
	}

	public MgrWorkCloseCheckPointDetailDTO getMgrWorkCloseCheckPointDetailDTO() 
	{
		return mgrWorkCloseCheckPointDetailDTO;
	}

	public void setMgrWorkCloseCheckPointDetailDTO(MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO) 
	{
		this.mgrWorkCloseCheckPointDetailDTO = mgrWorkCloseCheckPointDetailDTO;
	}

	public MgrWorkCloseCheckPointListDTO getMgrWorkCloseCheckPointListDTO() {
		return mgrWorkCloseCheckPointListDTO;
	}

	public void setMgrWorkCloseCheckPointListDTO(MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO) {
		this.mgrWorkCloseCheckPointListDTO = mgrWorkCloseCheckPointListDTO;
	}

}
