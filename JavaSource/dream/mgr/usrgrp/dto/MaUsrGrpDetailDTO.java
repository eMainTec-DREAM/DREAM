package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;

/**
 * 권한그룹 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaUsrGrpDetailDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 			= "";
	/** 사용자그룹 Id */
	private String usrGrpId    		= "";
	/** 사용자그룹코드 */
	private String usrGrpNo    		= "";
	/** 그룹명 */
	private String groupName 		= "";
	/** 비고 */
	private String remark 			= "";
	
	private String usrGrpMenuChkList 	= "";
	private String isUpdateMenu 	= "N";

    public String getIsUpdateMenu()
    {
        return isUpdateMenu;
    }
    public void setIsUpdateMenu(String isUpdateMenu)
    {
        this.isUpdateMenu = isUpdateMenu;
    }
    public String getUsrGrpMenuChkList()
    {
        return usrGrpMenuChkList;
    }
    public void setUsrGrpMenuChkList(String usrGrpMenuChkList)
    {
        this.usrGrpMenuChkList = usrGrpMenuChkList;
    }
    public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getGroupName() 
	{
		return groupName;
	}
	public void setGroupName(String groupName) 
	{
		this.groupName = groupName;
	}
	public String getRemark() 
	{
		return remark;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
    public String getUsrGrpId()
    {
        return usrGrpId;
    }
    public void setUsrGrpId(String usrGrpId)
    {
        this.usrGrpId = usrGrpId;
        super.setAuditKey(usrGrpId);
    }
    public String getUsrGrpNo()
    {
        return usrGrpNo;
    }
    public void setUsrGrpNo(String usrGrpNo)
    {
        this.usrGrpNo = usrGrpNo;
    }
	
}
