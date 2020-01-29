package dream.consult.comp.cdsys.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.dto.MaCdSysDTOList;

/**
 * 시스템코드 - 목록 form
 * @author  kim21017
 * @version $Id: MaCdSysListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maCdSysListForm"
 */
public class MaCdSysListForm extends BaseForm
{    
    //===============================================================
    /** 시스템코드 공통 */
    private MaCdSysCommonDTO maCdSysCommonDTO = new MaCdSysCommonDTO();
    /** 그리드 DTO */
    private List maCdSysDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(MaCdSysDTOList.class));
    
	public MaCdSysCommonDTO getMaCdSysCommonDTO() {
		return maCdSysCommonDTO;
	}

	public void setMaCdSysCommonDTO(MaCdSysCommonDTO maCdSysCommonDTO) {
		this.maCdSysCommonDTO = maCdSysCommonDTO;
	}

	public List getMaCdSysDTOList() {
		return maCdSysDTOList;
	}

	public void setMaCdSysDTOList(List maCdSysDTOList) {
		this.maCdSysDTOList = maCdSysDTOList;
	}
	
}
