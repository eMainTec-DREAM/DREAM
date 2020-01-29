package dream.work.rpt.mabdpoint.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;

/**
 * 이상점검조치- 상세 Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maBdPointDetailForm"
 */
public class MaBdPointDetailForm extends BaseForm
{
    //========================================================================
    /** 이상점검조치 공통 */ 
    private MaBdPointCommonDTO maBdPointCommonDTO = new MaBdPointCommonDTO();
    //========================================================================
    /** 이상점검조치 상세 */
    private MaBdPointDetailDTO maBdPointDetailDTO = new MaBdPointDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public MaBdPointCommonDTO getMaBdPointCommonDTO() {
		return maBdPointCommonDTO;
	}

    public void setMaBdPointCommonDTO(MaBdPointCommonDTO maBdPointCommonDTO) {
		this.maBdPointCommonDTO = maBdPointCommonDTO;
	}

	public MaBdPointDetailDTO getMaBdPointDetailDTO() {
		return maBdPointDetailDTO;
	}

	public void setMaBdPointDetailDTO(MaBdPointDetailDTO maBdPointDetailDTO) {
		this.maBdPointDetailDTO = maBdPointDetailDTO;
	}
}
