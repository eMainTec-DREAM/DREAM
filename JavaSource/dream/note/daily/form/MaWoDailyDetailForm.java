package dream.note.daily.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * - 상세 Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoDailyDetailForm"
 */
public class MaWoDailyDetailForm extends BaseForm
{
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    
    //========================================================================
    /**  공통 */ 
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
    //========================================================================
    /**  상세 */
    private MaWoDailyDetailDTO maWoDailyDetailDTO = new MaWoDailyDetailDTO();

    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }

    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }

    public MaWoDailyCommonDTO getMaWoDailyCommonDTO() {
		return maWoDailyCommonDTO;
	}

    public void setMaWoDailyCommonDTO(MaWoDailyCommonDTO maWoDailyCommonDTO) {
		this.maWoDailyCommonDTO = maWoDailyCommonDTO;
	}

	public MaWoDailyDetailDTO getMaWoDailyDetailDTO() {
		return maWoDailyDetailDTO;
	}

	public void setMaWoDailyDetailDTO(MaWoDailyDetailDTO maWoDailyDetailDTO) {
		this.maWoDailyDetailDTO = maWoDailyDetailDTO;
	}
}
