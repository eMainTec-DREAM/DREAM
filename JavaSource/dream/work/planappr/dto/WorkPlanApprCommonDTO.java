package dream.work.planappr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���ȹ���� ���� DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class WorkPlanApprCommonDTO extends BaseDTO
{
	/** �۾���ȹ���� ID */
	private String woPlanApprId					= "";
	/** filter- �ۼ����� ������ */
	private String filterStartDate				= "";
	/** filter- �ۼ����� ������ */
	private String filterEndDate				= "";
	/** filter- �μ�ID */
	private String filterDeptId					= "";
	/** filter- �μ��� */
	private String filterDeptDesc				= "";
	/** filter- ���� */
	private String filterDesc					= "";
	/** filter- ����ID */
	private String filterPlantId				= "";
	/** filter- ����� */
	private String filterPlantDesc				= "";
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
        this.yyyymm = yyyymm;
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
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public void setWoPlanApprId(String woPlanApprId) {
		this.woPlanApprId = woPlanApprId;
		super.setAuditKey(woPlanApprId);
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterDesc() {
		return filterDesc;
	}
	public void setFilterDesc(String filterDesc) {
		this.filterDesc = filterDesc;
	}
	
}
