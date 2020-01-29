package dream.consult.comp.cdsys.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.consult.comp.cdsys.dto.ConsultCdSysFieldListDTO;
import dream.consult.comp.cdsys.dto.ListTypeCodeDTOList;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 detail - code 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="consultCdSysFieldListForm"
 */
public class ConsultCdSysFieldListForm extends BaseForm
{    
    //===============================================================
    /** 시스템코드 공통 */
    private MaCdSysCommonDTO maCdSysCommonDTO = new MaCdSysCommonDTO();
    /** 시스템코드 detail-code  */
    private ConsultCdSysFieldListDTO consultCdSysFieldListDTO = new ConsultCdSysFieldListDTO();
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

	public ConsultCdSysFieldListDTO getConsultCdSysFieldListDTO() {
		return consultCdSysFieldListDTO;
	}

	public void setConsultCdSysFieldListDTO(ConsultCdSysFieldListDTO consultCdSysFieldListDTO) {
		this.consultCdSysFieldListDTO = consultCdSysFieldListDTO;
	}
}
