package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptDetailDTO;
import dream.part.list.dto.MaPtMstrUsedDeptListDTO;

/**
 * ��ǰ���μ�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrUsedDeptDetailForm"
 */
public class MaPtMstrUsedDeptDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	/** ��ǰ���μ� ��� DTO  */
    private MaPtMstrUsedDeptListDTO maPtMstrUsedDeptListDTO = new MaPtMstrUsedDeptListDTO();
	/** ��ǰ���μ� �� DTO  */
    private MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO = new MaPtMstrUsedDeptDetailDTO();
    
	public MaPtMstrUsedDeptListDTO getMaPtMstrUsedDeptListDTO() 
	{
		return maPtMstrUsedDeptListDTO;
	}
	
	public void setMaPtMstrUsedDeptListDTO(MaPtMstrUsedDeptListDTO maPtMstrUsedDeptListDTO) 
	{
		this.maPtMstrUsedDeptListDTO = maPtMstrUsedDeptListDTO;
	}
	
	public MaPtMstrUsedDeptDetailDTO getMaPtMstrUsedDeptDetailDTO() 
	{
		return maPtMstrUsedDeptDetailDTO;
	}
	
	public void setMaPtMstrUsedDeptDetailDTO(MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO) 
	{
		this.maPtMstrUsedDeptDetailDTO = maPtMstrUsedDeptDetailDTO;
	}
	
	public MaPtMstrCommonDTO getMaPtMstrCommonDTO() 
	{
		return maPtMstrCommonDTO;
	}
	
	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) 
	{
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}
}
