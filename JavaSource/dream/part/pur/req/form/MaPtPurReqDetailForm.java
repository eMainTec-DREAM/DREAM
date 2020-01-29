package dream.part.pur.req.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * 何前荐府- 惑技 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtPurReqDetailForm"
 */
public class MaPtPurReqDetailForm extends BaseForm
{
    //========================================================================
    /** 何前荐府 傍烹 */ 
    private MaPtReqCommonDTO maPtReqCommonDTO = new MaPtReqCommonDTO();
    //========================================================================
    /** 何前荐府 惑技 */
    private MaPtPurReqDetailDTO maPtPurReqDetailDTO = new MaPtPurReqDetailDTO();
    /** 梅何巩辑 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }

    public MaPtReqCommonDTO getMaPtReqCommonDTO() 
    {
		return maPtReqCommonDTO;
	}

    public void setMaPtReqCommonDTO(MaPtReqCommonDTO maPtReqCommonDTO) 
    {
		this.maPtReqCommonDTO = maPtReqCommonDTO;
	}

	public MaPtPurReqDetailDTO getMaPtPurReqDetailDTO() 
	{
		return maPtPurReqDetailDTO;
	}

	public void setMaPtPurReqDetailDTO(MaPtPurReqDetailDTO maPtPurReqDetailDTO) 
	{
		this.maPtPurReqDetailDTO = maPtPurReqDetailDTO;
	}
}
