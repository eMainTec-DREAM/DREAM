package dream.rcm.system.dto;

import common.bean.BaseDTO;

/**
 * 운전환경   DTO
 * @author  kim21017
 * @version $Id: RcmSysFEnvListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmSysFEnvListDTO extends BaseDTO
{
	/** 운전환경ID */
	private String rcmFEnvId 	= "";
	/** 기능정의ID */
	private String rcmFuncId 	= "";

	public String getRcmFEnvId() {
		return rcmFEnvId;
	}

	public void setRcmFEnvId(String rcmFEnvId) {
		this.rcmFEnvId = rcmFEnvId;
	}

	public String getRcmFuncId() {
		return rcmFuncId;
	}

	public void setRcmFuncId(String rcmFuncId) {
		this.rcmFuncId = rcmFuncId;
	}


}