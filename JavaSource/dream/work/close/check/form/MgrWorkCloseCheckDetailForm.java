package dream.work.close.check.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckDetailDTO;

/**
 * MgrWorkCloseCheck Page - Detail Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="mgrWorkCloseCheckDetailForm"
 */
public class MgrWorkCloseCheckDetailForm extends BaseForm
{
    private MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = new MgrWorkCloseCheckCommonDTO();
    private MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO = new MgrWorkCloseCheckDetailDTO();
    /** Ã·ºÎ¹®¼­ DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    public MgrWorkCloseCheckCommonDTO getMgrWorkCloseCheckCommonDTO() {
        return mgrWorkCloseCheckCommonDTO;
    }
    public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public void setMgrWorkCloseCheckCommonDTO(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO) {
        this.mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckCommonDTO;
    }
    public MgrWorkCloseCheckDetailDTO getMgrWorkCloseCheckDetailDTO() {
        return mgrWorkCloseCheckDetailDTO;
    }
    public void setMgrWorkCloseCheckDetailDTO(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO) {
        this.mgrWorkCloseCheckDetailDTO = mgrWorkCloseCheckDetailDTO;
    }
}
