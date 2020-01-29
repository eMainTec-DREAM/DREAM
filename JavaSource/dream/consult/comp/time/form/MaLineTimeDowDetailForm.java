package dream.consult.comp.time.form;

import common.struts.BaseForm;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowDetailDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;

/**
 * 요일별설정
 * @author  kim2107
 * @version $Id: MaLineTimeDowDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maLineTimeDowDetailForm"
 */
public class MaLineTimeDowDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaLineTimeCommonDTO maLineTimeCommonDTO       = new MaLineTimeCommonDTO();
	/** 요일별 설정 목록 DTO  */
    private MaLineTimeDowListDTO maLineTimeDowListDTO     = new MaLineTimeDowListDTO();
	/** 요일별 설정 상세 DTO  */
    private MaLineTimeDowDetailDTO maLineTimeDowDetailDTO = new MaLineTimeDowDetailDTO();
    
	public MaLineTimeDowListDTO getMaLineTimeDowListDTO() {
		return maLineTimeDowListDTO;
	}
	public void setMaLineTimeDowListDTO(MaLineTimeDowListDTO maLineTimeDowListDTO) {
		this.maLineTimeDowListDTO = maLineTimeDowListDTO;
	}
	public MaLineTimeDowDetailDTO getMaLineTimeDowDetailDTO() {
		return maLineTimeDowDetailDTO;
	}
	public void setMaLineTimeDowDetailDTO(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO) {
		this.maLineTimeDowDetailDTO = maLineTimeDowDetailDTO;
	}
	public MaLineTimeCommonDTO getMaLineTimeCommonDTO() {
		return maLineTimeCommonDTO;
	}
	public void setMaLineTimeCommonDTO(MaLineTimeCommonDTO maLineTimeCommonDTO) {
		this.maLineTimeCommonDTO = maLineTimeCommonDTO;
	}
}
