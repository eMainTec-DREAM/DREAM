package dream.work.pmi.list.dto;

import common.bean.BaseDTO;

/**
 * 점검작업 점검 목록 DTO
 * @author  kim21017
 * @version $Id: WorkPmiPointListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkPmiPointListDTO extends BaseDTO
{
	/** 검사항목 id */
	private String pmInsPointId 	= "";
	/** 검사항목 id */
	private String pmPointId 		= "";
    /** 점검항목 상세 페이지  */
    private String pointDetailPageId		= "";
    /** 첨부문서 유무 */
    private String docIsYn		= "";
    /** 상태 */
    private String pmSchedStatus	= "";
    
	public String getPmSchedStatus()
    {
        return pmSchedStatus;
    }
    public void setPmSchedStatus(String pmSchedStatus)
    {
        this.pmSchedStatus = pmSchedStatus;
    }
    public String getDocIsYn() {
		return docIsYn;
	}
	public void setDocIsYn(String docIsYn) {
		this.docIsYn = docIsYn;
	}
	public String getPointDetailPageId() {
		return pointDetailPageId;
	}
	public void setPointDetailPageId(String pointDetailPageId) {
		this.pointDetailPageId = pointDetailPageId;
	}
	public String getPmPointId() {
		return pmPointId;
	}
	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
	}
	public String getPmInsPointId() {
		return pmInsPointId;
	}
	public void setPmInsPointId(String pmInsPointId) {
		this.pmInsPointId = pmInsPointId;
		super.setAuditKey(pmInsPointId);
	}

}