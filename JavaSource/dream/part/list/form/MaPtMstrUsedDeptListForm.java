package dream.part.list.form;

import common.bean.User;
import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptListDTO;

/**
 * 부품사용부서 목록
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrUsedDeptListForm"
 */
public class MaPtMstrUsedDeptListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
    /**  부품사용부서 목록  */
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
