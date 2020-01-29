package dream.mgr.usrcd.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.dto.MaCdUsrGridDTO;
/**
 * 사용자코드관리 action form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maCdUsrListForm"
 */
public class MaCdUsrListForm extends BaseForm
{ 
    /** 사용자코드관리 DTO */
    private MaCdUsrCommonDTO maCdUsrCommonDTO = new MaCdUsrCommonDTO();
    /** 그리드 DTO */
    private List maCdUsrGridDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(MaCdUsrGridDTO.class));

	public MaCdUsrCommonDTO getMaCdUsrCommonDTO()
	{
		return maCdUsrCommonDTO;
	}

	public void setMaCdUsrCommonDTO(MaCdUsrCommonDTO maCdUsrCommonDTO) 
	{
		this.maCdUsrCommonDTO = maCdUsrCommonDTO;
	}

	public List getMaCdUsrGridDTOList() 
	{
		return maCdUsrGridDTOList;
	}

	public void setMaCdUsrGridDTOList(List maCdUsrGridDTOList) 
	{
		this.maCdUsrGridDTOList = maCdUsrGridDTOList;
	}



}
