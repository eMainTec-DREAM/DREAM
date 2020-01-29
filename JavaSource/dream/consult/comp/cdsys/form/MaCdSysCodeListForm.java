package dream.consult.comp.cdsys.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.consult.comp.cdsys.dto.ListTypeCodeDTOList;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 detail - code 목록
 * @author  kim21017
 * @version $Id: MaCdSysCodeListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maCdSysCodeListForm"
 */
public class MaCdSysCodeListForm extends BaseForm
{    
    //===============================================================
    /** 시스템코드 공통 */
    private MaCdSysCommonDTO maCdSysCommonDTO = new MaCdSysCommonDTO();
    /** 시스템코드 detail-code  */
    private MaCdSysCodeListDTO maCdSysCodeListDTO = new MaCdSysCodeListDTO();
    /** 그리도 DTO */
    private List listTypeCodeDTOList = ListUtils.lazyList(new ArrayList(), getDtoFactory(ListTypeCodeDTOList.class));
	
    
    public List getListTypeCodeDTOList() {
		return listTypeCodeDTOList;
	}

	public void setListTypeCodeDTOList(List listTypeCodeDTOList) {
		this.listTypeCodeDTOList = listTypeCodeDTOList;
	}

	public MaCdSysCommonDTO getMaCdSysCommonDTO() {
		return maCdSysCommonDTO;
	}

	public void setMaCdSysCommonDTO(MaCdSysCommonDTO maCdSysCommonDTO) {
		this.maCdSysCommonDTO = maCdSysCommonDTO;
	}

	public MaCdSysCodeListDTO getMaCdSysCodeListDTO() {
		return maCdSysCodeListDTO;
	}

	public void setMaCdSysCodeListDTO(MaCdSysCodeListDTO maCdSysCodeListDTO) {
		this.maCdSysCodeListDTO = maCdSysCodeListDTO;
	}
}
