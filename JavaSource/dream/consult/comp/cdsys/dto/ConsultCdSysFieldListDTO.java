package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;

/**
 * �ý����ڵ� - �з�  DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class ConsultCdSysFieldListDTO extends BaseDTO
{
	/** �ý����ڵ�������ID */
	private String cdSysDId 	= "";

	public String getCdSysDId() {
		return cdSysDId;
	}

	public void setCdSysDId(String cdSysDId) {
		this.cdSysDId = cdSysDId;
	}
}