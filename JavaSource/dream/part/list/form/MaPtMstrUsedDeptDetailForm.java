package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptDetailDTO;
import dream.part.list.dto.MaPtMstrUsedDeptListDTO;

/**
 * 부품사용부서
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrUsedDeptDetailForm"
 */
public class MaPtMstrUsedDeptDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	/** 부품사용부서 목록 DTO  */
    private MaPtMstrUsedDeptListDTO maPtMstrUsedDeptListDTO = new MaPtMstrUsedDeptListDTO();
	/** 부품사용부서 상세 DTO  */
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
