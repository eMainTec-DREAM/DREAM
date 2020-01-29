package dream.org.emp.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.MaEmpGridDTO;

/**
 * ��� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maEmpListForm"
 */
public class MaEmpListForm extends BaseForm
{    
    //===============================================================
    /** ��� ���� */
    private MaEmpCommonDTO maEmpCommonDTO = new MaEmpCommonDTO();
    
    /** �׸��� DTO */
    private List maEmpGridDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(MaEmpGridDTO.class));

	public List getMaEmpGridDTOList()
    {
        return maEmpGridDTOList;
    }

    public void setMaEmpGridDTOList(List maEmpGridDTOList)
    {
        this.maEmpGridDTOList = maEmpGridDTOList;
    }

    public MaEmpCommonDTO getMaEmpCommonDTO() 
	{
		return maEmpCommonDTO;
	}

	public void setMaEmpCommonDTO(MaEmpCommonDTO maEmpCommonDTO) 
	{
		this.maEmpCommonDTO = maEmpCommonDTO;
	}
}
