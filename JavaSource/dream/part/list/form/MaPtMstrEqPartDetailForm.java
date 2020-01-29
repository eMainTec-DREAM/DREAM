package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrEqPartDetailDTO;
import dream.part.list.dto.MaPtMstrEqPartListDTO;

/**
 * 사용설비
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrEqPartDetailForm"
 */
public class MaPtMstrEqPartDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	/** 사용설비 목록 DTO  */
    private MaPtMstrEqPartListDTO maPtMstrEqPartListDTO = new MaPtMstrEqPartListDTO();
	/** 사용설비 상세 DTO  */
    private MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO = new MaPtMstrEqPartDetailDTO();
    
	public MaPtMstrEqPartListDTO getMaPtMstrEqPartListDTO() 
	{
		return maPtMstrEqPartListDTO;
	}
	
	public void setMaPtMstrEqPartListDTO(MaPtMstrEqPartListDTO maPtMstrEqPartListDTO) 
	{
		this.maPtMstrEqPartListDTO = maPtMstrEqPartListDTO;
	}
	
	public MaPtMstrEqPartDetailDTO getMaPtMstrEqPartDetailDTO() 
	{
		return maPtMstrEqPartDetailDTO;
	}
	
	public void setMaPtMstrEqPartDetailDTO(MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO) 
	{
		this.maPtMstrEqPartDetailDTO = maPtMstrEqPartDetailDTO;
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
