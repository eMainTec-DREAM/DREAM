package dream.mgr.usrgrp.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpDetailDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpMenuDTO;

/**
 * 권한그룹- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maUsrGrpDetailForm"
 */
public class MaUsrGrpDetailForm extends BaseForm
{
    //========================================================================
    /** 권한그룹 공통 */ 
    private MaUsrGrpCommonDTO maUsrGrpCommonDTO = new MaUsrGrpCommonDTO();
    //========================================================================
    /** 권한그룹 상세 */
    private MaUsrGrpDetailDTO maUsrGrpDetailDTO = new MaUsrGrpDetailDTO();
    /** 권한그룹 상세 */
    private MaUsrGrpMenuDTO maUsrGrpMenuDTO = new MaUsrGrpMenuDTO();
    
    /** 메뉴 id/desc */
    private List maUsrGrpMenuDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(MaUsrGrpMenuDTO.class));

	public MaUsrGrpMenuDTO getMaUsrGrpMenuDTO()
    {
        return maUsrGrpMenuDTO;
    }

    public void setMaUsrGrpMenuDTO(MaUsrGrpMenuDTO maUsrGrpMenuDTO)
    {
        this.maUsrGrpMenuDTO = maUsrGrpMenuDTO;
    }

    public MaUsrGrpCommonDTO getMaUsrGrpCommonDTO() 
	{
		return maUsrGrpCommonDTO;
	}

	public void setMaUsrGrpCommonDTO(MaUsrGrpCommonDTO maUsrGrpCommonDTO) 
	{
		this.maUsrGrpCommonDTO = maUsrGrpCommonDTO;
	}

	public MaUsrGrpDetailDTO getMaUsrGrpDetailDTO() 
	{
		return maUsrGrpDetailDTO;
	}

	public void setMaUsrGrpDetailDTO(MaUsrGrpDetailDTO maUsrGrpDetailDTO) 
	{
		this.maUsrGrpDetailDTO = maUsrGrpDetailDTO;
	}

    public List getMaUsrGrpMenuDTOList()
    {
        return maUsrGrpMenuDTOList;
    }

    public void setMaUsrGrpMenuDTOList(List maUsrGrpMenuDTOList)
    {
        this.maUsrGrpMenuDTOList = maUsrGrpMenuDTOList;
    }

}
