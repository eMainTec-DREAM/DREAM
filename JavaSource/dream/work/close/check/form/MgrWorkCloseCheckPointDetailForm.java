package dream.work.close.check.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointDetailDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * 표준항목 리스트- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrWorkCloseCheckPointDetailForm"
 */
public class MgrWorkCloseCheckPointDetailForm extends BaseForm
{
    //========================================================================
    /** 표준항목 공통 */ 
    private MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = new MgrWorkCloseCheckCommonDTO();
    //========================================================================
    /** 표준항목리슽 목록 */
    private MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO = new MgrWorkCloseCheckPointListDTO();
    /** 표준항목리슽 상세 */
    private MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO = new MgrWorkCloseCheckPointDetailDTO();
    /** 첨부문서 DTO */
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
