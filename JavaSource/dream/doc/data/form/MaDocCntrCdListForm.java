package dream.doc.data.form;

import common.struts.BaseForm;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;

/**
 * 일반자료실 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maDocCntrCdListForm"
 */
public class MaDocCntrCdListForm extends BaseForm
{    
    //===============================================================
    /** 일반자료실 공통 */
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
