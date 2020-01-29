package dream.note.dayrpt.form;

import common.struts.BaseForm;

import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;

/**
 * - �� Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoDayRptDetailForm"
 */
public class MaWoDayRptDetailForm extends BaseForm
{
    //========================================================================
    /**  ���� */ 
    private MaWoDayRptCommonDTO maWoDayRptCommonDTO = new MaWoDayRptCommonDTO();
    //========================================================================
    /**  �� */
    private MaWoDayRptDetailDTO maWoDayRptDetailDTO = new MaWoDayRptDetailDTO();
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
