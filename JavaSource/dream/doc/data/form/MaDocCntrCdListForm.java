package dream.doc.data.form;

import common.struts.BaseForm;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;

/**
 * �Ϲ��ڷ�� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maDocCntrCdListForm"
 */
public class MaDocCntrCdListForm extends BaseForm
{    
    //===============================================================
    /** �Ϲ��ڷ�� ���� */
    private MaDocCntrCdCommonDTO maDocCntrCdCommonDTO = new MaDocCntrCdCommonDTO();;

	public MaDocCntrCdCommonDTO getMaDocCntrCdCommonDTO() 
	{
		return maDocCntrCdCommonDTO;
	}

	public void setMaDocCntrCdCommonDTO(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO) 
	{
		this.maDocCntrCdCommonDTO = maDocCntrCdCommonDTO;
	}

}
