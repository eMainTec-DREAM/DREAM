package dream.mgr.usrgrp.dto;

import java.util.ArrayList;

import common.bean.BaseDTO;

/**
 * ���ѱ׷� - �޴� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaUsrGrpMenuDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 		   = "";
	/** ����ڱ׷캰�޴�Id */
	private String usrGrpMenuId    = "";
	/** ����ڱ׷�Id */
	private String usrGrpId        = "";
	/** �޴�Id */
	private String menuId          = "";
	/** �޴��� */
	private String menuDesc        = "";
	
	/** ���ÿ��� - checkbox */
	private String isCheck	       = "";
	
	/**  */
	private String usrGrpMenuData = "";
	
	private ArrayList usrGrpMenuDatas = new ArrayList();
	
	public ArrayList getUsrGrpMenuDatas()
    {
        return usrGrpMenuDatas;
    }
    public void setUsrGrpMenuDatas(ArrayList usrGrpMenuDatas)
    {
        this.usrGrpMenuDatas = usrGrpMenuDatas;
    }
    public String getUsrGrpMenuId()
    {
        return usrGrpMenuId;
    }
    public void setUsrGrpMenuId(String usrGrpMenuId)
    {
        this.usrGrpMenuId = usrGrpMenuId;
    }
    public String getUsrGrpMenuData()
    {
        return usrGrpMenuData;
    }
    public void setUsrGrpMenuData(String usrGrpMenuData)
    {
        this.usrGrpMenuData = usrGrpMenuData;
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
    public String getMenuId()
    {
        return menuId;
    }
    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }
    public String getMenuDesc()
    {
        return menuDesc;
    }
    public void setMenuDesc(String menuDesc)
    {
        this.menuDesc = menuDesc;
    }
    public String getIsCheck()
    {
        return isCheck;
    }
    public void setIsCheck(String isCheck)
    {
        this.isCheck = isCheck;
    }
	
}
