package dream.pers.mamymenu.dto;

import common.bean.BaseDTO;

/**
 * ����ڸ޴� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaMyMenuDTO extends BaseDTO
{
	
	/** ȸ���ڵ� */
	private String compNo                  = "";
	/** ���ѱ׷�Id */
	private String usrGrpId                = "";
	/** ���ѱ׷�No */
	private String usrGrpNo                = "";
	/** �����Id */
	private String userId                = "";
	/** Filter-ȸ���ڵ� */
	private String filterCompNo 		   = "";
	/** Filter-�׷�� */
	private String filterGroupName		   = "";
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsrGrpNo() {
		return usrGrpNo;
	}
	public void setUsrGrpNo(String usrGrpNo) {
		this.usrGrpNo = usrGrpNo;
	}
	public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getUsrGrpId()
    {
        return usrGrpId;
    }
    public void setUsrGrpId(String usrGrpId)
    {
        this.usrGrpId = usrGrpId;
    }
    public String getFilterCompNo() 
	{
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) 
	{
		this.filterCompNo = filterCompNo;
	}
	public String getFilterGroupName() {
		return filterGroupName;
	}
	public void setFilterGroupName(String filterGroupName) 
	{
		this.filterGroupName = filterGroupName;
	}

	

}
