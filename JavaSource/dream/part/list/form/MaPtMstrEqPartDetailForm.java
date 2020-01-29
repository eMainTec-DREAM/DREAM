package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrEqPartDetailDTO;
import dream.part.list.dto.MaPtMstrEqPartListDTO;

/**
 * ��뼳��
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrEqPartDetailForm"
 */
public class MaPtMstrEqPartDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	/** ��뼳�� ��� DTO  */
    private MaPtMstrEqPartListDTO maPtMstrEqPartListDTO = new MaPtMstrEqPartListDTO();
	/** ��뼳�� �� DTO  */
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
