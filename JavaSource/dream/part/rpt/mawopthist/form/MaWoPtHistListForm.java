package dream.part.rpt.mawopthist.form;

import common.struts.BaseForm;
import dream.part.rpt.mawopthist.dto.MaWoPtHistCommonDTO;

/**
 * ��ǰ����̷� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWoPtHistListForm"
 */
public class MaWoPtHistListForm extends BaseForm
{    
    //===============================================================
    /** ��ǰ����̷� ���� */
    private MaWoPtHistCommonDTO maWoPtHistCommonDTO = new MaWoPtHistCommonDTO();;

	public MaWoPtHistCommonDTO getMaWoPtHistCommonDTO() 
	{
		return maWoPtHistCommonDTO;
	}

	public void setMaWoPtHistCommonDTO(MaWoPtHistCommonDTO maWoPtHistCommonDTO) 
	{
		this.maWoPtHistCommonDTO = maWoPtHistCommonDTO;
	}

}
