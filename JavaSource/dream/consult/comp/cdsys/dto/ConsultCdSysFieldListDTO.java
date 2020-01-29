package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;

/**
 * 시스템코드 - 분류  DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class ConsultCdSysFieldListDTO extends BaseDTO
{
	/** 시스템코드유형상세ID */
	private String cdSysDId 	= "";

	public String getCdSysDId() {
		return cdSysDId;
	}

	public void setCdSysDId(String cdSysDId) {
		this.cdSysDId = cdSysDId;
	}
}