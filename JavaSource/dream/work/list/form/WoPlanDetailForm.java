package dream.work.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;

/**
 * �۾���ȹ���- �� Form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="woPlanDetailForm"
 */
public class WoPlanDetailForm extends BaseForm
{
    //========================================================================
    /** �۾���� ���� */ 
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    //========================================================================
    /** �۾���� �� */
    private WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
    
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    /** ���� ���� �̷� DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();

	public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }

    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }

    public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
    
	public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}

    public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}

	public WoPlanDetailDTO getWoPlanDetailDTO() {
		return woPlanDetailDTO;
	}

	public void setWoPlanDetailDTO(WoPlanDetailDTO woPlanDetailDTO) {
		this.woPlanDetailDTO = woPlanDetailDTO;
	}
}
