package dream.scheduler.list.dto;

import common.bean.BaseDTO;

/**
 * ��ư - �� DTO
 * @author  kim21017
 * @version $Id: MaBatchMngDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaBatchMngDetailDTO extends BaseDTO
{
	/** id */
	private String batPgmId 		= "";
	/** no */
	private String batPgmNo			= "";
	/** ���α׷��� */
	private String batchPgm 		= "";
	/** desc */
	private String batPgmDesc 		= "";
	/** desc */
	private String lastExeTime 		= "";
	/** �������� ���� ���� */
	private String isExec			= "";
	/** �����ֱ� */
	private String execRemark		= "";
	/** �ֱٽ����� */
	private String exeBy			= "";
	
	
	public String getBatPgmId() {
		return batPgmId;
	}
	public void setBatPgmId(String batPgmId) {
		this.batPgmId = batPgmId;
		super.setAuditKey(batPgmId);
	}
	public String getBatPgmNo() {
		return batPgmNo;
	}
	public void setBatPgmNo(String batPgmNo) {
		this.batPgmNo = batPgmNo;
	}
	public String getBatchPgm() {
		return batchPgm;
	}
	public void setBatchPgm(String batchPgm) {
		this.batchPgm = batchPgm;
	}
	public String getBatPgmDesc() {
		return batPgmDesc;
	}
	public void setBatPgmDesc(String batPgmDesc) {
		this.batPgmDesc = batPgmDesc;
	}
	public String getLastExeTime() {
		return lastExeTime;
	}
	public void setLastExeTime(String lastExeTime) {
		this.lastExeTime = lastExeTime;
	}
	public String getIsExec() {
		return isExec;
	}
	public void setIsExec(String isExec) {
		this.isExec = isExec;
	}
	public String getExecRemark() {
		return execRemark;
	}
	public void setExecRemark(String execRemark) {
		this.execRemark = execRemark;
	}
	public String getExeBy() {
		return exeBy;
	}
	public void setExeBy(String exeBy) {
		this.exeBy = exeBy;
	}
}
