package dream.app.onlinehelp.form;

import common.struts.BaseForm;
import dream.app.onlinehelp.dto.AppOnlinehelpCommonDTO;
import dream.app.onlinehelp.dto.AppOnlinehelpDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * ȭ�麰 ���� �� 
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="appOnlinehelpDetailForm"
 */
public class AppOnlinehelpDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private AppOnlinehelpCommonDTO appOnlinehelpCommonDTO = new AppOnlinehelpCommonDTO();

	/** �� DTO  */
    private AppOnlinehelpDetailDTO appOnlinehelpDetailDTO = new AppOnlinehelpDetailDTO();
    /** ÷�ι��� DTO  */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }
    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
    public AppOnlinehelpDetailDTO getAppOnlinehelpDetailDTO() {
		return appOnlinehelpDetailDTO;
	}
	public void setAppOnlinehelpDetailDTO(AppOnlinehelpDetailDTO appOnlinehelpDetailDTO) {
		this.appOnlinehelpDetailDTO = appOnlinehelpDetailDTO;
	}
	public AppOnlinehelpCommonDTO getAppOnlinehelpCommonDTO() {
		return appOnlinehelpCommonDTO;
	}
	public void setAppOnlinehelpCommonDTO(AppOnlinehelpCommonDTO appOnlinehelpCommonDTO) {
		this.appOnlinehelpCommonDTO = appOnlinehelpCommonDTO;
	}
}
