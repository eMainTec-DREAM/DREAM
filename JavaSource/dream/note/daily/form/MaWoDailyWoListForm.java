package dream.note.daily.form;

import common.struts.BaseForm;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 일일작업 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoDailyWoListForm"
 */
public class MaWoDailyWoListForm extends BaseForm
{    
    //===============================================================
    /** 일일작업 공통 */
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
    /** 일일작업 상세 */
    private MaWoDailyDetailDTO maWoDailyDetailDTO = new MaWoDailyDetailDTO();
    /** 작업상세 공통 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    
	public MaWoDailyDetailDTO getMaWoDailyDetailDTO()
    {
        return maWoDailyDetailDTO;
    }

    public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public void setMaWoDailyDetailDTO(MaWoDailyDetailDTO maWoDailyDetailDTO)
    {
        this.maWoDailyDetailDTO = maWoDailyDetailDTO;
    }

    public MaWoDailyCommonDTO getMaWoDailyCommonDTO() {
		return maWoDailyCommonDTO;
	}

	public void setMaWoDailyCommonDTO(MaWoDailyCommonDTO maWoDailyCommonDTO) {
		this.maWoDailyCommonDTO = maWoDailyCommonDTO;
	}
}
