package dream.rcm.system.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.org.emp.dto.MaEmpGridDTO;

/**
 * 사원 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="rcmSysListForm"
 */
public class RcmSysListForm extends BaseForm
{    
    //===============================================================
    /** 사원 공통 */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    
    /** 그리드 DTO */
    private List maEmpGridDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(MaEmpGridDTO.class));

	public List getMaEmpGridDTOList()
    {
        return maEmpGridDTOList;
    }

    public void setMaEmpGridDTOList(List maEmpGridDTOList)
    {
        this.maEmpGridDTOList = maEmpGridDTOList;
    }

    public RcmSysCommonDTO getRcmSysCommonDTO() 
	{
		return rcmSysCommonDTO;
	}

	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) 
	{
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}
}
