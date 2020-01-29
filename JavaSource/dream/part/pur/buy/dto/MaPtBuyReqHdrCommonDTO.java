package dream.part.pur.buy.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���Ž�û ���� DTO
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaPtBuyReqHdrCommonDTO extends BaseDTO
{
	/** �����û id */
	private String ptPrListId 				= "";
	/** ���� ��û�μ�id  */
	private String filterDeptId				= "";
	/** ���� ��û�μ��� */
	private String filterDeptDesc			= "";
	/** ���� ���� ��û���� */
	private String filterStartReqDate		= "";
	/** ���� �� ��û���� */
	private String filterEndReqDate			= "";
	/** ���� ���� */
	private String filterPtPrListDesc		= "";
	/** ���� ����id  */
	private String filterPartId				= "";
	/** ���� ����No */
	private String filterPartNo				= "";
	/** ���� ����� */
	private String filterPartDesc			= "";
	/** ���� �ۼ�����id  */
	private String filterPtPrListStatusId	= "";
	/** ���� �ۼ����¸� */
	private String filterPtPrListStatusDesc	= "";
	/** ���� ǰ��/�԰� */
	private String filterPtDescSize			= "";
	/** ���� ��û��ȣ */
	private String filterPtPrListNo			= "";
	/** ���� ��û��id  */
	private String filterUserId				= "";
	/** ���� ��û�ڸ� */
	private String filterUserDesc			= "";

	/** ����-���� ID */
    private String filterPlantId         	= "";
    /** ����-���� DESC */
    private String filterPlantDesc        	= "";
    
    /** ���� �����û# */
    private String filterPtPnListNo			= "";
    /** ���� ERP PR��ȣ */
    private String filterErpPrNo			= "";
    
    /** ���� - ������ ID */
    private String filterRecById            = "";
    /** ���� - ������ DESC */
    private String filterRecByDesc          = "";

    /** ���� - �������� ID */
    private String filterRecPlantId         = "";
    /** ���� - �������� DESC */
    private String filterRecPlantDesc       = "";
    
	public String getFilterRecById()
    {
        return filterRecById;
    }
    public void setFilterRecById(String filterRecById)
    {
        this.filterRecById = filterRecById;
    }
    public String getFilterRecByDesc()
    {
        return filterRecByDesc;
    }
    public void setFilterRecByDesc(String filterRecByDesc)
    {
        this.filterRecByDesc = filterRecByDesc;
    }
    public String getFilterRecPlantId()
    {
        return filterRecPlantId;
    }
    public void setFilterRecPlantId(String filterRecPlantId)
    {
        this.filterRecPlantId = filterRecPlantId;
    }
    public String getFilterRecPlantDesc()
    {
        return filterRecPlantDesc;
    }
    public void setFilterRecPlantDesc(String filterRecPlantDesc)
    {
        this.filterRecPlantDesc = filterRecPlantDesc;
    }
    public String getFilterErpPrNo()
    {
        return filterErpPrNo;
    }
    public void setFilterErpPrNo(String filterErpPrNo)
    {
        this.filterErpPrNo = filterErpPrNo;
    }
    public String getFilterPtPnListNo() {
		return filterPtPnListNo;
	}
	public void setFilterPtPnListNo(String filterPtPnListNo) {
		this.filterPtPnListNo = filterPtPnListNo;
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
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	public String getPtPrListId() {
		return ptPrListId;
	}
	public void setPtPrListId(String ptPrListId) {
		this.ptPrListId = ptPrListId;
		super.setAuditKey(ptPrListId);
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
	public String getFilterStartReqDate() {
		return filterStartReqDate;
	}
	public void setFilterStartReqDate(String filterStartReqDate) {
		this.filterStartReqDate = CommonUtil.convertDate(filterStartReqDate);
	}
	public String getFilterEndReqDate() {
		return filterEndReqDate;
	}
	public void setFilterEndReqDate(String filterEndReqDate) {
		this.filterEndReqDate = CommonUtil.convertDate(filterEndReqDate);
	}
	public String getFilterPtPrListDesc() {
		return filterPtPrListDesc;
	}
	public void setFilterPtPrListDesc(String filterPtPrListDesc) {
		this.filterPtPrListDesc = filterPtPrListDesc;
	}
	public String getFilterPartId() {
		return filterPartId;
	}
	public void setFilterPartId(String filterPartId) {
		this.filterPartId = filterPartId;
	}
	public String getFilterPartNo() {
		return filterPartNo;
	}
	public void setFilterPartNo(String filterPartNo) {
		this.filterPartNo = filterPartNo;
	}
	public String getFilterPtPrListStatusId() {
		return filterPtPrListStatusId;
	}
	public void setFilterPtPrListStatusId(String filterPtPrListStatusId) {
		this.filterPtPrListStatusId = filterPtPrListStatusId;
	}
	public String getFilterPtPrListStatusDesc() {
		return filterPtPrListStatusDesc;
	}
	public void setFilterPtPrListStatusDesc(String filterPtPrListStatusDesc) {
		this.filterPtPrListStatusDesc = filterPtPrListStatusDesc;
	}
	public String getFilterPtDescSize() {
		return filterPtDescSize;
	}
	public void setFilterPtDescSize(String filterPtDescSize) {
		this.filterPtDescSize = filterPtDescSize;
	}
	public String getFilterPtPrListNo() {
		return filterPtPrListNo;
	}
	public void setFilterPtPrListNo(String filterPtPrListNo) {
		this.filterPtPrListNo = filterPtPrListNo;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public String getFilterUserDesc() {
		return filterUserDesc;
	}
	public void setFilterUserDesc(String filterUserDesc) {
		this.filterUserDesc = filterUserDesc;
	}
	
}
