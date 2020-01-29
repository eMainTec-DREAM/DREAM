package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업결과 작업자 목록 DTO
 * @author  kim21017
 * @version $Id: WorkListCavalListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListCavalListDTO extends BaseDTO
{
	/** 작업결과 작업자 id */
	private String woCalibValueId 	= "";

	public String getWoCalibValueId() {
		return woCalibValueId;
	}

	public void setWoCalibValueId(String woCalibValueId) {
		this.woCalibValueId = woCalibValueId;
	}

	
}