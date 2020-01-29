package dream.rcm.pmtask.dto;

import common.bean.BaseDTO;
/**
 * LOV DTO
 * @author kim21017
 * @version $Id: RcmPmtaskAcListDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmPmtaskAcListDTO extends BaseDTO
{
	/**   시스템 분석 리스트 ID */
	private String rcmlistId			= "";
	/**   시스템 분석 리스트 코드 */
	private String rcmlistNo		    = "";
	/**   시스템 분석명 */
	private String rcmlistDesc		    = "";
	
    public String getRcmlistId()
    {
        return rcmlistId;
    }
    public void setRcmlistId(String rcmlistId)
    {
        this.rcmlistId = rcmlistId;
    }
    public String getRcmlistNo()
    {
        return rcmlistNo;
    }
    public void setRcmlistNo(String rcmlistNo)
    {
        this.rcmlistNo = rcmlistNo;
    }
    public String getRcmlistDesc()
    {
        return rcmlistDesc;
    }
    public void setRcmlistDesc(String rcmlistDesc)
    {
        this.rcmlistDesc = rcmlistDesc;
    }
	
	
}
