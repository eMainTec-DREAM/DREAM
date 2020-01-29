package dream.tool.adj.form;

import common.struts.BaseForm;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisDetailDTO;

/**
 * 공기구반납- 상세 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttDisDetailForm"
 */
public class MaPttDisDetailForm extends BaseForm
{
    //========================================================================
    /** 공기구반납 공통 */ 
    private MaPttDisCommonDTO maPttDisCommonDTO = new MaPttDisCommonDTO();
    //========================================================================
    /** 공기구반납 상세 */
    private MaPttDisDetailDTO maPttDisDetailDTO = new MaPttDisDetailDTO();

    public MaPttDisCommonDTO getMaPttDisCommonDTO() {
		return maPttDisCommonDTO;
	}

    public void setMaPttDisCommonDTO(MaPttDisCommonDTO maPttDisCommonDTO) {
		this.maPttDisCommonDTO = maPttDisCommonDTO;
	}

	public MaPttDisDetailDTO getMaPttDisDetailDTO() {
		return maPttDisDetailDTO;
	}

	public void setMaPttDisDetailDTO(MaPttDisDetailDTO maPttDisDetailDTO) {
		this.maPttDisDetailDTO = maPttDisDetailDTO;
	}
}
