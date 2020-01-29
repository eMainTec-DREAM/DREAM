package dream.pers.appr.dto;

import common.bean.BaseDTO;
/**
 * �����̷� - ���� DTO
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 *
 */
public class PersApprHistCommonDTO extends BaseDTO
{
	/**���α׷� ���̵� ID*/
	private String apprListId 			= "";
	/**Filter ��û ������*/
	private String filterStartDate 	= "";
	/**Filter ��û ������*/
	private String filterEndDate 	= "";
	/**Filter ���籸�� ID */
	private String filterApprTypeId 		= "";
	/**Filter ���籸�� DESC */
	private String filterApprTypeDesc 		= "";
	/**Filter ������� ID */
	private String filterApprusrStatusId 	= "";
	/**Filter ������� DESC */
	private String filterApprusrStatusDesc 	= "";
	/** ��û�� ID */
	private String reqBy       = "";
	
	private String reqByName   = "";
	/** ������ ID */
	private String apprBy      = "";
	
	private String apprByName  = "";
	
	
	public String getReqBy()
    {
        return reqBy;
    }
    public void setReqBy(String reqBy)
    {
        this.reqBy = reqBy;
    }
    public String getReqByName()
    {
        return reqByName;
    }
    public void setReqByName(String reqByName)
    {
        this.reqByName = reqByName;
    }
    public String getApprBy()
    {
        return apprBy;
    }
    public void setApprBy(String apprBy)
    {
        this.apprBy = apprBy;
    }
    public String getApprByName()
    {
        return apprByName;
    }
    public void setApprByName(String apprByName)
    {
        this.apprByName = apprByName;
    }
    public String getFilterApprTypeId() {
		return filterApprTypeId;
	}
	public void setFilterApprTypeId(String filterApprTypeId) {
		this.filterApprTypeId = filterApprTypeId;
	}
	public String getFilterApprTypeDesc() {
		return filterApprTypeDesc;
	}
	public void setFilterApprTypeDesc(String filterApprTypeDesc) {
		this.filterApprTypeDesc = filterApprTypeDesc;
	}
	public String getFilterApprusrStatusId() {
		return filterApprusrStatusId;
	}
	public void setFilterApprusrStatusId(String filterApprusrStatusId) {
		this.filterApprusrStatusId = filterApprusrStatusId;
	}
	public String getFilterApprusrStatusDesc() {
		return filterApprusrStatusDesc;
	}
	public void setFilterApprusrStatusDesc(String filterApprusrStatusDesc) {
		this.filterApprusrStatusDesc = filterApprusrStatusDesc;
	}
	public String getApprListId() {
		return apprListId;
	}
	public void setApprListId(String apprListId) {
		this.apprListId = apprListId;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = filterEndDate;
	}
	
	
}
