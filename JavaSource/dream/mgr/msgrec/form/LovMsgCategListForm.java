package dream.mgr.msgrec.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.msgrec.dto.LovMsgCategListDTO;

/**
 * �޽���Ÿ�� Lov Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovMsgCategListForm"
 */
public class LovMsgCategListForm extends MaFinderAcForm
{
    /** �޽���Ÿ�� Lov DTO */
    private LovMsgCategListDTO lovMsgCategListDTO = new LovMsgCategListDTO();

    public LovMsgCategListDTO getLovMsgCategListDTO()
    {
        return lovMsgCategListDTO;
    }

    public void setLovMsgCategListDTO(LovMsgCategListDTO lovMsgCategListDTO)
    {
        this.lovMsgCategListDTO = lovMsgCategListDTO;
    }
}
