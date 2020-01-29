package dream.work.planappr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���ȹ���� - �� DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public class WorkPlanApprDetailDTO extends BaseDTO
{
	/** ��ȹ����ID */
	private String woPlanApprId 				= "";
	/** �۾����� ������ */
	private String startDate					= "";
	/** �۾����� ������ */
	private String endDate						= "";
	/** �μ�ID */
	private String deptId			 			= "";
	/** �μ��� */
	private String deptDesc			 			= "";
	/** ���� */
	private String description		 			= "";
	/** �ۼ����� */
	private String updDate			 			= "";
	/** �ۼ���ID */
	private String updBy			 			= "";
	/** �ۼ��ڸ� */
	private String updDesc			 			= "";
	/** ��� */
	private String remark			 			= "";
	/** ���� ID */
	private String woPlanApprStatusId			= "";
	/** ���¸� */
	private String woPlanApprStatusDesc			= "";
	/** ����ID */
	private String plantId			 			= "";
	/** ����� */
	private String plantDesc                    = "";
	/** �� */
	private String yyyy                         = "";
	/** ��� */
	private String yyyymm                       = "";
	/** �۾���ȹ���α��� */
	private String woplanapprType               = "";
	
	private String woType                       = "";
	
	
	public String getWoType()
    {
        return woType;
    }
    public void setWoType(String woType)
    {
        this.woType = woType;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getYyyy()
    {
        return yyyy;
    }
    public void setYyyy(String yyyy)
    {
        this.yyyy = yyyy;
    }
    public String getYyyymm()
    {
        return yyyymm;
    }
    public void setYyyymm(String yyyymm)
    {
        this.yyyymm = CommonUtil.convertDate(yyyymm);
    }
    public String getWoplanapprType()
    {
        return woplanapprType;
    }
    public void setWoplanapprType(String woplanapprType)
    {
        this.woplanapprType = woplanapprType;
    }
    public String getWoPlanApprId() {
		return woPlanApprId;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public void setWoPlanApprId(String woPlanApprId) {
		this.woPlanApprId = woPlanApprId;
		super.setAuditKey(woPlanApprId);
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = CommonUtil.convertDate(updDate);
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public String getUpdDesc() {
		return updDesc;
	}
	public void setUpdDesc(String updDesc) {
		this.updDesc = updDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWoPlanApprStatusId() {
		return woPlanApprStatusId;
	}
	public void setWoPlanApprStatusId(String woPlanApprStatusId) {
		this.woPlanApprStatusId = woPlanApprStatusId;
	}
	public String getWoPlanApprStatusDesc() {
		return woPlanApprStatusDesc;
	}
	public void setWoPlanApprStatusDesc(String woPlanApprStatusDesc) {
		this.woPlanApprStatusDesc = woPlanApprStatusDesc;
	}
}
