package dream.pers.priv.info.form;

import dream.comm.form.MaFinderAcForm;
import dream.pers.priv.info.dto.LovMsgCompListDTO;

/**
 * �޽���Ÿ�� Lov Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovMsgCompListForm"
 */
public class LovMsgCompListForm extends MaFinderAcForm
{
    /** �޽���Ÿ�� Lov DTO */
    private LovMsgCompListDTO lovMsgCompListDTO = new LovMsgCompListDTO();

    public LovMsgCompListDTO getLovMsgCompListDTO()
    {
        return lovMsgCompListDTO;
    }

    public void setLovMsgCompListDTO(LovMsgCompListDTO lovMsgCompListDTO)
    {
        this.lovMsgCompListDTO = lovMsgCompListDTO;
    }
}
