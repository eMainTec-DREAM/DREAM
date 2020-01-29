package dream.note.dayrpt.form;

import common.struts.BaseForm;

import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;

/**
 * - 상세 Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoDayRptDetailForm"
 */
public class MaWoDayRptDetailForm extends BaseForm
{
    //========================================================================
    /**  공통 */ 
    private MaWoDayRptCommonDTO maWoDayRptCommonDTO = new MaWoDayRptCommonDTO();
    //========================================================================
    /**  상세 */
    private MaWoDayRptDetailDTO maWoDayRptDetailDTO = new MaWoDayRptDetailDTO();
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
    
    public MaWoDayRptCommonDTO getMaWoDayRptCommonDTO() {
		return maWoDayRptCommonDTO;
	}

    public void setMaWoDayRptCommonDTO(MaWoDayRptCommonDTO maWoDayRptCommonDTO) {
		this.maWoDayRptCommonDTO = maWoDayRptCommonDTO;
	}

	public MaWoDayRptDetailDTO getMaWoDayRptDetailDTO() {
		return maWoDayRptDetailDTO;
	}

	public void setMaWoDayRptDetailDTO(MaWoDayRptDetailDTO maWoDayRptDetailDTO) {
		this.maWoDayRptDetailDTO = maWoDayRptDetailDTO;
	}
}
