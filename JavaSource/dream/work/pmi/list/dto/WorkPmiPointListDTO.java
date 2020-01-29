package dream.work.pmi.list.dto;

import common.bean.BaseDTO;

/**
 * �����۾� ���� ��� DTO
 * @author  kim21017
 * @version $Id: WorkPmiPointListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkPmiPointListDTO extends BaseDTO
{
	/** �˻��׸� id */
	private String pmInsPointId 	= "";
	/** �˻��׸� id */
	private String pmPointId 		= "";
    /** �����׸� �� ������  */
    private String pointDetailPageId		= "";
    /** ÷�ι��� ���� */
    private String docIsYn		= "";
    /** ���� */
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