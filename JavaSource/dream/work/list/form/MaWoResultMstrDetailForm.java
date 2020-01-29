package dream.work.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.work.list.dto.MaWoResultFailDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;


/**
 * �۾����- �� Form
 * @author  kim21017
 * @version $Id: MaWoResultMstrDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultMstrDetailForm"
 */
public class MaWoResultMstrDetailForm extends BaseForm
{
    //========================================================================
    /** �۾���� ���� */ 
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    //========================================================================
    /** �۾���� �� */
    private MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = new MaWoResultMstrDetailDTO();
    
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    /** �۾���� ���峻�� �� DTO  */
    private MaWoResultFailDetailDTO maWoResultFailDetailDTO = new MaWoResultFailDetailDTO();
    /** �۾���� �˱��� �� DTO  */
    private MaWoResultPmCalDTO maWoResultPmCalDTO = new MaWoResultPmCalDTO();
    /** ���� ���� �̷� DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    /** �۾���û DTO */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    /** �۾���û DTO */
    private MaWoReqDetailDTO maWoReqDetailDTO = new MaWoReqDetailDTO();

	public MaWoReqDetailDTO getMaWoReqDetailDTO() {
		return maWoReqDetailDTO;
	}
	public void setMaWoReqDetailDTO(MaWoReqDetailDTO maWoReqDetailDTO) {
		this.maWoReqDetailDTO = maWoReqDetailDTO;
	}
	public MaWoReqCommonDTO getMaWoReqCommonDTO()
    {
        return maWoReqCommonDTO;
    }
    public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO)
    {
        this.maWoReqCommonDTO = maWoReqCommonDTO;
    }

    public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }

    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }

    public MaWoResultPmCalDTO getMaWoResultPmCalDTO() {
		return maWoResultPmCalDTO;
	}

	public void setMaWoResultPmCalDTO(MaWoResultPmCalDTO maWoResultPmCalDTO) {
		this.maWoResultPmCalDTO = maWoResultPmCalDTO;
	}

	public MaWoResultFailDetailDTO getMaWoResultFailDetailDTO() {
		return maWoResultFailDetailDTO;
	}

	public void setMaWoResultFailDetailDTO(MaWoResultFailDetailDTO maWoResultFailDetailDTO) {
		this.maWoResultFailDetailDTO = maWoResultFailDetailDTO;
	}

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
    
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

    public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public MaWoResultMstrDetailDTO getMaWoResultMstrDetailDTO() {
		return maWoResultMstrDetailDTO;
	}

	public void setMaWoResultMstrDetailDTO(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
		this.maWoResultMstrDetailDTO = maWoResultMstrDetailDTO;
	}
}
