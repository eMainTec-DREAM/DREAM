package dream.rcm.taskmap.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 질의 - 상세  DTO
 * @author  kim21017
 * @version $Id: RcmTaskMapItemDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmTaskMapItemDetailDTO extends BaseDTO
{
	/** Key ID */
	private String pmTaskmapId 	= "";

	private String taskMapNo 	= "";
	
	private String taskDesc 	= "";

	private String ytypeDesc 	= "";
	
	private String yidDesc 	= "";
	
	private String ytask 	= "";
	
	private String remark 	= "";
	
	private String ytypeId 	= "";
	
	private String yid 	= "";

	
	
	private String ntypeDesc 	= "";
	
	private String nidDesc 	= "";
	
	private String ntask 	= "";
	
	private String nemark 	= "";
	
	private String ntypeId 	= "";
	
	private String nid 	= "";

	public String getPmTaskmapId() {
		return pmTaskmapId;
	}

	public void setPmTaskmapId(String pmTaskmapId) {
		this.pmTaskmapId = pmTaskmapId;
		super.setAuditKey(pmTaskmapId);
	}

	public String getTaskMapNo() {
		return taskMapNo;
	}

	public void setTaskMapNo(String taskMapNo) {
		this.taskMapNo = taskMapNo;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getYtypeDesc() {
		return ytypeDesc;
	}

	public void setYtypeDesc(String ytypeDesc) {
		this.ytypeDesc = ytypeDesc;
	}

	public String getYidDesc() {
		return yidDesc;
	}

	public void setYidDesc(String yidDesc) {
		this.yidDesc = yidDesc;
	}

	public String getYtask() {
		return ytask;
	}

	public void setYtask(String ytask) {
		this.ytask = ytask;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getYtypeId() {
		return ytypeId;
	}

	public void setYtypeId(String ytypeId) {
		this.ytypeId = ytypeId;
	}


	public String getNtypeDesc() {
		return ntypeDesc;
	}

	public void setNtypeDesc(String ntypeDesc) {
		this.ntypeDesc = ntypeDesc;
	}

	public String getNidDesc() {
		return nidDesc;
	}

	public void setNidDesc(String nidDesc) {
		this.nidDesc = nidDesc;
	}

	public String getNtask() {
		return ntask;
	}

	public void setNtask(String ntask) {
		this.ntask = ntask;
	}

	public String getNemark() {
		return nemark;
	}

	public void setNemark(String nemark) {
		this.nemark = nemark;
	}

	public String getNtypeId() {
		return ntypeId;
	}

	public void setNtypeId(String ntypeId) {
		this.ntypeId = ntypeId;
	}

	public String getYid() {
		return yid;
	}

	public void setYid(String yid) {
		this.yid = yid;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

}
