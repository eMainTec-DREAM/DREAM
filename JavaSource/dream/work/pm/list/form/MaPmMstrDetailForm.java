package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;

/**
 * �� Form
 * @author  jung7126
 * @version $Id: MaPmMstrDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmMstrDetailForm"
 */
public class MaPmMstrDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    //========================================================================
    /** �� */
    private MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
    /** ���� ���� �̷� DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

	public AppReqCommonDTO getAppReqCommonDTO() {
		return appReqCommonDTO;
	}

	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO) {
		this.appReqCommonDTO = appReqCommonDTO;
	}

	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}

    public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}

	public MaPmMstrDetailDTO getMaPmMstrDetailDTO() {
		return maPmMstrDetailDTO;
	}

	public void setMaPmMstrDetailDTO(MaPmMstrDetailDTO maPmMstrDetailDTO) {
		this.maPmMstrDetailDTO = maPmMstrDetailDTO;
	}
}
