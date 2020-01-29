package dream.org.emp.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.MaEmpDetailDTO;

/**
 * ���- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maEmpDetailForm"
 */
public class MaEmpDetailForm extends BaseForm
{
    //========================================================================
    /** ��� ���� */ 
    private MaEmpCommonDTO maEmpCommonDTO = new MaEmpCommonDTO();
    //========================================================================
    /** ��� �� */
    private MaEmpDetailDTO maEmpDetailDTO = new MaEmpDetailDTO();

	public MaEmpCommonDTO getMaEmpCommonDTO() 
	{
		return maEmpCommonDTO;
	}

	public void setMaEmpCommonDTO(MaEmpCommonDTO maEmpCommonDTO) 
	{
		this.maEmpCommonDTO = maEmpCommonDTO;
	}

	public MaEmpDetailDTO getMaEmpDetailDTO() 
	{
		return maEmpDetailDTO;
	}

	public void setMaEmpDetailDTO(MaEmpDetailDTO maEmpDetailDTO) 
	{
		this.maEmpDetailDTO = maEmpDetailDTO;
	}

}
