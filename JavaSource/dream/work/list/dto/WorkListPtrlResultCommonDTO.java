package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * ��ȸ���� �۾���� ��� DTO
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultCommonDTO.java,v 1.1 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 */
public class WorkListPtrlResultCommonDTO extends BaseDTO
{
	/** PM ��ȸ���� ��� id */
	private String pmPtrlRsltListId 	         = "";
	
	/** ������ ID */
    private String pmPtrlSchedId                = "";
	
	/** Filter ���� �������� */
	private String filterPtrlStartDate          = "";
	/** Filter ���� �������� */
	private String filterPtrlEndDate 	         = "";
    /** Filter ���˿�������  */
    private String filterPtrlDate               = "";
	/** Filter ��ȸ���� ID */
	private String filterPtrlWorkId 	         = "";
	/** Filter ��ȸ������ */
	private String filterPtrlWorkTitle 	     = "";
	/** Filter ��ȸ����No */
	private String filterPtrlWorkNo 	         = "";
	/**Filter �μ� ID*/
    private String filterDeptId                 = "";
    /**Filter �μ���*/
    private String filterDeptDesc               = "";    
	/** Filter �۾��׷� ID */
	private String filterPtrlWorkGrpId 	     = "";
	/** Filter �۾��׷�� */
	private String filterPtrlWorkGrpDesc 	     = "";
	/** Filter ����� ID*/
	private String filterManagerId 	         = "";
	/** Filter ����ڸ�*/
	private String filterManagerDesc 	         = "";
	/** Filter ���˿Ϸ� �������� */
    private String filterPtrlComStartDate       = "";
    /** Filter ���˿Ϸ� �������� */
    private String filterPtrlComEndDate         = "";
    /** Filter ���˿Ϸ����� */
    private String filterPtrlComDate            = "";
    /** Filter ������ ID*/
    private String filterPtrlInspectorId        = "";
    /** Filter �����ڸ�*/
    private String filterPtrlInspectorDesc      = "";
    
    
    
    public String getPmPtrlSchedId()
    {
        return pmPtrlSchedId;
    }
    public void setPmPtrlSchedId(String pmPtrlSchedId)
    {
        this.pmPtrlSchedId = pmPtrlSchedId;
    }
    public String getPmPtrlRsltListId()
    {
        return pmPtrlRsltListId;
    }
    public void setPmPtrlRsltListId(String pmPtrlRsltListId)
    {
        this.pmPtrlRsltListId = pmPtrlRsltListId;
        super.setAuditKey(pmPtrlRsltListId);
    }
    public String getFilterPtrlStartDate()
    {
        return filterPtrlStartDate;
    }
    public void setFilterPtrlStartDate(String filterPtrlStartDate)
    {
        this.filterPtrlStartDate = filterPtrlStartDate;
    }
    public String getFilterPtrlEndDate()
    {
        return filterPtrlEndDate;
    }
    public void setFilterPtrlEndDate(String filterPtrlEndDate)
    {
        this.filterPtrlEndDate = filterPtrlEndDate;
    }
    public String getFilterPtrlDate()
    {
        return filterPtrlDate;
    }
    public void setFilterPtrlDate(String filterPtrlDate)
    {
        this.filterPtrlDate = filterPtrlDate;
    }
    public String getFilterPtrlWorkId()
    {
        return filterPtrlWorkId;
    }
    public void setFilterPtrlWorkId(String filterPtrlWorkId)
    {
        this.filterPtrlWorkId = filterPtrlWorkId;
    }
    public String getFilterPtrlWorkTitle()
    {
        return filterPtrlWorkTitle;
    }
    public void setFilterPtrlWorkTitle(String filterPtrlWorkTitle)
    {
        this.filterPtrlWorkTitle = filterPtrlWorkTitle;
    }
    public String getFilterPtrlWorkNo()
    {
        return filterPtrlWorkNo;
    }
    public void setFilterPtrlWorkNo(String filterPtrlWorkNo)
    {
        this.filterPtrlWorkNo = filterPtrlWorkNo;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getFilterPtrlWorkGrpId()
    {
        return filterPtrlWorkGrpId;
    }
    public void setFilterPtrlWorkGrpId(String filterPtrlWorkGrpId)
    {
        this.filterPtrlWorkGrpId = filterPtrlWorkGrpId;
    }
    public String getFilterPtrlWorkGrpDesc()
    {
        return filterPtrlWorkGrpDesc;
    }
    public void setFilterPtrlWorkGrpDesc(String filterPtrlWorkGrpDesc)
    {
        this.filterPtrlWorkGrpDesc = filterPtrlWorkGrpDesc;
    }
    public String getFilterManagerId()
    {
        return filterManagerId;
    }
    public void setFilterManagerId(String filterManagerId)
    {
        this.filterManagerId = filterManagerId;
    }
    public String getFilterManagerDesc()
    {
        return filterManagerDesc;
    }
    public void setFilterManagerDesc(String filterManagerDesc)
    {
        this.filterManagerDesc = filterManagerDesc;
    }
    public String getFilterPtrlComStartDate()
    {
        return filterPtrlComStartDate;
    }
    public void setFilterPtrlComStartDate(String filterPtrlComStartDate)
    {
        this.filterPtrlComStartDate = filterPtrlComStartDate;
    }
    public String getFilterPtrlComEndDate()
    {
        return filterPtrlComEndDate;
    }
    public void setFilterPtrlComEndDate(String filterPtrlComEndDate)
    {
        this.filterPtrlComEndDate = filterPtrlComEndDate;
    }
    public String getFilterPtrlComDate()
    {
        return filterPtrlComDate;
    }
    public void setFilterPtrlComDate(String filterPtrlComDate)
    {
        this.filterPtrlComDate = filterPtrlComDate;
    }
    public String getFilterPtrlInspectorId()
    {
        return filterPtrlInspectorId;
    }
    public void setFilterPtrlInspectorId(String filterPtrlInspectorId)
    {
        this.filterPtrlInspectorId = filterPtrlInspectorId;
    }
    public String getFilterPtrlInspectorDesc()
    {
        return filterPtrlInspectorDesc;
    }
    public void setFilterPtrlInspectorDesc(String filterPtrlInspectorDesc)
    {
        this.filterPtrlInspectorDesc = filterPtrlInspectorDesc;
    }
	
}