package dream.pers.priv.notice.form;

import common.struts.BaseForm;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.notice.dto.PersPrivNoticeDetailDTO;

/**
 * Notice 설정 - 상세 Form
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="persPrivNoticeDetailForm"
 */
public class PersPrivNoticeDetailForm extends BaseForm
{    
  //===============================================================
    /** 공통 DTO */
    private MaMyInfoCommonDTO maMyInfoCommonDTO = new MaMyInfoCommonDTO();
  //===============================================================
    /** 상세 DTO  */
    private PersPrivNoticeDetailDTO persPrivNoticeDetailDTO = new PersPrivNoticeDetailDTO();

	public PersPrivNoticeDetailDTO getPersPrivNoticeDetailDTO() {
		return persPrivNoticeDetailDTO;
	}
	public void setPersPrivNoticeDetailDTO(PersPrivNoticeDetailDTO persPrivNoticeDetailDTO) {
		this.persPrivNoticeDetailDTO = persPrivNoticeDetailDTO;
	}
	public MaMyInfoCommonDTO getMaMyInfoCommonDTO() {
		return maMyInfoCommonDTO;
	}
	public void setMaMyInfoCommonDTO(MaMyInfoCommonDTO maMyInfoCommonDTO) {
		this.maMyInfoCommonDTO = maMyInfoCommonDTO;
	}
}
