package dream.part.list.form;

import common.bean.User;
import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptListDTO;

/**
 * ��ǰ���μ� ���
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrUsedDeptListForm"
 */
public class MaPtMstrUsedDeptListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
    /**  ��ǰ���μ� ���  */
    private MaPtMstrUsedDeptListDTO maPtMstrUsedDeptListDTO = new MaPtMstrUsedDeptListDTO();
	

	public MaPtMstrCommonDTO getMaPtMstrCommonDTO() 
	{
		return maPtMstrCommonDTO;
	}

	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) 
	{
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}

	public MaPtMstrUsedDeptListDTO getMaPtMstrUsedDeptListDTO() 
	{
		return maPtMstrUsedDeptListDTO;
	}

	public void setMaPtMstrUsedDeptListDTO(MaPtMstrUsedDeptListDTO maPtMstrUsedDeptListDTO) 
	{
		this.maPtMstrUsedDeptListDTO = maPtMstrUsedDeptListDTO;
	}

}
