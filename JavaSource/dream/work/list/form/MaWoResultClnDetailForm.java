package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultClnDetailDTO;
import dream.work.list.dto.MaWoResultClnListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업설비 상세
 * @author  kim2107
 * @version $Id: MaWoResultClnDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultClnDetailForm"
 */
public class MaWoResultClnDetailForm extends BaseForm
{
    //===============================================================
    /** 공통 DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** 작업결과 설비 목록 DTO  */
    private MaWoResultClnListDTO maWoResultClnListDTO = new MaWoResultClnListDTO();
	/** 작업결과 설비 상세 DTO  */
    private MaWoResultClnDetailDTO maWoResultClnDetailDTO = new MaWoResultClnDetailDTO();
    
	public MaWoResultClnListDTO getMaWoResultClnListDTO() {
		return maWoResultClnListDTO;
	}
	public void setMaWoResultClnListDTO(MaWoResultClnListDTO maWoResultClnListDTO) {
		this.maWoResultClnListDTO = maWoResultClnListDTO;
	}
	public MaWoResultClnDetailDTO getMaWoResultClnDetailDTO() {
		return maWoResultClnDetailDTO;
	}
	public void setMaWoResultClnDetailDTO(MaWoResultClnDetailDTO maWoResultClnDetailDTO) {
		this.maWoResultClnDetailDTO = maWoResultClnDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
