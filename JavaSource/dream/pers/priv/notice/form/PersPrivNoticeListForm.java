package dream.pers.priv.notice.form;

import common.struts.BaseForm;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.notice.dto.PersPrivNoticeDetailDTO;

/**
 * Notice 설정 목록 Form
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="persPrivNoticeListForm"
 */
public class PersPrivNoticeListForm extends BaseForm
{    
  //===============================================================
    /** 공통 DTO */
    private MaMyInfoCommonDTO maMyInfoCommonDTO = new MaMyInfoCommonDTO();
  //===============================================================
    private PersPrivNoticeDetailDTO persPrivNoticeDetailDTO = new PersPrivNoticeDetailDTO();    
    
    public PersPrivNoticeDetailDTO getPersPrivNoticeDetailDTO()
    {
        return persPrivNoticeDetailDTO;
    }

    public void setPersPrivNoticeDetailDTO(
            PersPrivNoticeDetailDTO persPrivNoticeDetailDTO)
    {
        this.persPrivNoticeDetailDTO = persPrivNoticeDetailDTO;
    }

    public MaMyInfoCommonDTO getMaMyInfoCommonDTO() {
		return maMyInfoCommonDTO;
	}

	public void setMaMyInfoCommonDTO(MaMyInfoCommonDTO maMyInfoCommonDTO) {
		this.maMyInfoCommonDTO = maMyInfoCommonDTO;
	}
}
