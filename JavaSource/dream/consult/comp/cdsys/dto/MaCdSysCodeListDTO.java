package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;

/**
 * �ý����ڵ� - �з�  DTO
 * @author  kim21017
 * @version $Id: MaCdSysCodeListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaCdSysCodeListDTO extends BaseDTO
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