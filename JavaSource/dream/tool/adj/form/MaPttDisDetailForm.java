package dream.tool.adj.form;

import common.struts.BaseForm;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisDetailDTO;

/**
 * ���ⱸ�ݳ�- �� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttDisDetailForm"
 */
public class MaPttDisDetailForm extends BaseForm
{
    //========================================================================
    /** ���ⱸ�ݳ� ���� */ 
    private MaPttDisCommonDTO maPttDisCommonDTO = new MaPttDisCommonDTO();
    //========================================================================
    /** ���ⱸ�ݳ� �� */
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
