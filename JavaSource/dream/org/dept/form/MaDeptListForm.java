package dream.org.dept.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.org.dept.dto.MaDeptCommonDTO;
import dream.org.dept.dto.MaDeptGridDTO;

/**
 * �����μ� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maDeptListForm"
 */
public class MaDeptListForm extends BaseForm
{    
    //===============================================================
    /** �����μ� ���� */
    private MaDeptCommonDTO maDeptCommonDTO = new MaDeptCommonDTO();;
    /** �׸��� DTO */
    private List maDeptGridDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(MaDeptGridDTO.class));
    
	public MaDeptCommonDTO getMaDeptCommonDTO() 
	{
		return maDeptCommonDTO;
	}

	public void setMaDeptCommonDTO(MaDeptCommonDTO maDeptCommonDTO) 
	{
		this.maDeptCommonDTO = maDeptCommonDTO;
	}

	public List getMaDeptGridDTOList() 
	{
		return maDeptGridDTOList;
	}

	public void setMaDeptGridDTOList(List maDeptGridDTOList) 
	{
		this.maDeptGridDTOList = maDeptGridDTOList;
	}
	
}
