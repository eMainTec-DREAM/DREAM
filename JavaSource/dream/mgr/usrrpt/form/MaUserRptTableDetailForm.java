package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptTableDetailDTO;
import dream.mgr.usrrpt.dto.MaUserRptTableListDTO;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maUserRptTableDetailForm"
 */
public class MaUserRptTableDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();
	/** ��뼳�� ��� DTO  */
    private MaUserRptTableListDTO maUserRptTableListDTO = new MaUserRptTableListDTO();
	/** ��뼳�� �� DTO  */
    private MaUserRptTableDetailDTO maUserRptTableDetailDTO = new MaUserRptTableDetailDTO();
    
	public MaUserRptTableListDTO getMaUserRptTableListDTO() 
	{
		return maUserRptTableListDTO;
	}
	
	public void setMaUserRptTableListDTO(MaUserRptTableListDTO maUserRptTableListDTO) 
	{
		this.maUserRptTableListDTO = maUserRptTableListDTO;
	}
	
	public MaUserRptTableDetailDTO getMaUserRptTableDetailDTO() 
	{
		return maUserRptTableDetailDTO;
	}
	
	public void setMaUserRptTableDetailDTO(MaUserRptTableDetailDTO maUserRptTableDetailDTO) 
	{
		this.maUserRptTableDetailDTO = maUserRptTableDetailDTO;
	}

	public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}

	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}

}
