package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업결과 검사항목 목록 DTO
 * @author  kim21017
 * @version $Id: MaWoResultPointListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultPointListDTO extends BaseDTO
{
	/** 검사항목 id */
	private String woPointId 	= "";
	/** 검사항목 id */
	private String pmPointId 	= "";
	/** 상태 id */
	private String pmSchedStatus	= "";

	
	public String getPmSchedStatus()
    {
        return pmSchedStatus;
    }
    public void setPmSchedStatus(String pmSchedStatus)
    {
        this.pmSchedStatus = pmSchedStatus;
    }
    public String getPmPointId() {
		return pmPointId;
	}
	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
	}
	public String getWoPointId() {
		return woPointId;
	}
	public void setWoPointId(String woPointId) {
		this.woPointId = woPointId;
	}
}