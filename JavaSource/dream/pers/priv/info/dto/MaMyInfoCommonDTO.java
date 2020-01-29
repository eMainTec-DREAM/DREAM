package dream.pers.priv.info.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������ ���� DTO
 * @author  kim21017
 * @version $Id: MaMyInfoCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaMyInfoCommonDTO extends BaseDTO
{
	/** �����ȣ */
	private String userNo 		= "";
	/** �α��� ���� */
	private String loginNo 		= "";
	/** ���Id */
	private String userId 		= "";
	/** ������ */
	private String userName 	= "";
	/** ���� */
	private String userGroup 	= "";
	/** ����� */
	private String regDate 		= "";
	/** �ֱ������� */
	private String loginDate 	= "";
	/** ����ó */
	private String phone 		= "";
	/** �̸��� */
	private String email 		= "";
	/** �ʱ�ȭ�� */
	private String menuId 		= "";
	/** �ʱ�ȭ��� */
	private String menuDesc 	= "";
	
	private String usrMenuId    = "";
	/** ���� �޴� ID*/
	private String linkMenuId   = "";
	/**���ȵ��*/
	private String securGradeId   	= "";
	private String securGradeDesc   = "";
	/**ȭ��Fontũ��*/
	private String scrnFontSizeId  	= "";
	private String scrnFontSizeDesc	= "";
	/**���α�����*/
	private String shiftTypeId   	= "";
	private String shiftTypeDesc	= "";
    /** ��ġID */
    private String eqLocId		= "";
    /** ��ġ�� */
    private String eqLocDesc	= "";
    /** Dashboard View */
    private String alarmViewYn  = "";
    /** �۾��׷� id */
    private String wkCtrId		= "";
    /** �۾��׷� �� */
    private String wkCtrDesc	= "";
    /** ��������id */
	private String eqCtgTypeId		= "";
	/** ���������� */
	private String eqCtgTypeDesc	= "";
	/** Filter��μ�Id */
	private String filterDeptId 	= "";
	/** Filter�� �μ��� */
	private String filterDeptDesc 	= "";
	/** Menu Display? */
	private String menuDisplay 		= "";
	/** Menu Display Desc */
	private String menuDisplayDesc	= "";
	/** Filter�۾������ Id */
	private String filterEmpId 		= "";
	/** Filter�۾������ �� */
	private String filterEmpDesc 	= "";
	/** Filter�۾��׷�  Id */
	private String filterWkCtrId 	= "";
	/** Filter�۾��׷� �� */
	private String filterWkCtrDesc 	= "";
	/** ���� ���� ���� */
	private String isMailRec 		= "";
	/** ���ֺ��� ���μ�Id */
	private String filterUsageDeptId	= "";
	/** ���ֺ��� ���μ��� */
	private String filterUsageDeptDesc	= "";
    /** ���ֺ��� �����ڵ� */
    private String filterPlant		= "";
    /** ���ֺ��� ����� */
    private String filterPlantDesc	= "";
	/** �޽������ż��� ID */
	private String msgEmpSetId		= "";
	/** bee �ʱ�ȭ�� ID */
	private String beeInitMenuId	= "";
	/** bee �ʱ�ȭ��� */
	private String beeInitMenuDesc	= "";
	/** �˸� ��� ���� ID */
	private String alarmEmpSetId    = "";
		
	public String getAlarmEmpSetId()
    {
        return alarmEmpSetId;
    }
    public void setAlarmEmpSetId(String alarmEmpSetId)
    {
        this.alarmEmpSetId = alarmEmpSetId;
    }
    public String getBeeInitMenuId() {
		return beeInitMenuId;
	}
	public void setBeeInitMenuId(String beeInitMenuId) {
		this.beeInitMenuId = beeInitMenuId;
	}
	public String getBeeInitMenuDesc() {
		return beeInitMenuDesc;
	}
	public void setBeeInitMenuDesc(String beeInitMenuDesc) {
		this.beeInitMenuDesc = beeInitMenuDesc;
	}
	public String getMsgEmpSetId() {
		return msgEmpSetId;
	}
	public void setMsgEmpSetId(String msgEmpSetId) {
		this.msgEmpSetId = msgEmpSetId;
	}
	public String getFilterUsageDeptId() {
		return filterUsageDeptId;
	}
	public void setFilterUsageDeptId(String filterUsageDeptId) {
		this.filterUsageDeptId = filterUsageDeptId;
	}
	public String getFilterUsageDeptDesc() {
		return filterUsageDeptDesc;
	}
	public void setFilterUsageDeptDesc(String filterUsageDeptDesc) {
		this.filterUsageDeptDesc = filterUsageDeptDesc;
	}
	public String getIsMailRec()
    {
        return isMailRec;
    }
    public void setIsMailRec(String isMailRec)
    {
        this.isMailRec = isMailRec;
    }
    public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpDesc() {
		return filterEmpDesc;
	}
	public void setFilterEmpDesc(String filterEmpDesc) {
		this.filterEmpDesc = filterEmpDesc;
	}
	public String getMenuDisplayDesc()
    {
        return menuDisplayDesc;
    }
    public void setMenuDisplayDesc(String menuDisplayDesc)
    {
        this.menuDisplayDesc = menuDisplayDesc;
    }
    public String getMenuDisplay()
    {
        return menuDisplay;
    }
    public void setMenuDisplay(String menuDisplay)
    {
        this.menuDisplay = menuDisplay;
    }
    public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getAlarmViewYn()
    {
        return alarmViewYn;
    }
    public void setAlarmViewYn(String alarmViewYn)
    {
        this.alarmViewYn = alarmViewYn;
    }
    public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getFilterPlant() {
		return filterPlant;
	}
	public void setFilterPlant(String filterPlant) {
		this.filterPlant = filterPlant;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getLinkMenuId()
    {
        return linkMenuId;
    }
    public String getSecurGradeId() {
		return securGradeId;
	}
	public void setSecurGradeId(String securGradeId) {
		this.securGradeId = securGradeId;
	}
	public String getSecurGradeDesc() {
		return securGradeDesc;
	}
	public void setSecurGradeDesc(String securGradeDesc) {
		this.securGradeDesc = securGradeDesc;
	}
	public String getScrnFontSizeId() {
		return scrnFontSizeId;
	}
	public void setScrnFontSizeId(String scrnFontSizeId) {
		this.scrnFontSizeId = scrnFontSizeId;
	}
	public String getScrnFontSizeDesc() {
		return scrnFontSizeDesc;
	}
	public void setScrnFontSizeDesc(String scrnFontSizeDesc) {
		this.scrnFontSizeDesc = scrnFontSizeDesc;
	}
	public void setLinkMenuId(String linkMenuId)
    {
        this.linkMenuId = linkMenuId;
    }
    public String getUsrMenuId()
    {
        return usrMenuId;
    }
    public void setUsrMenuId(String usrMenuId)
    {
        this.usrMenuId = usrMenuId;
    }
    public String getLoginNo() {
		return loginNo;
	}
	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = CommonUtil.convertDate(loginDate);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getShiftTypeId() {
		return shiftTypeId;
	}
	public void setShiftTypeId(String shiftTypeId) {
		this.shiftTypeId = shiftTypeId;
	}
	public String getShiftTypeDesc() {
		return shiftTypeDesc;
	}
	public void setShiftTypeDesc(String shiftTypeDesc) {
		this.shiftTypeDesc = shiftTypeDesc;
	}
	public String getEqCtgTypeId() {
		return eqCtgTypeId;
	}
	public void setEqCtgTypeId(String eqCtgTypeId) {
		this.eqCtgTypeId = eqCtgTypeId;
	}
	public String getEqCtgTypeDesc() {
		return eqCtgTypeDesc;
	}
	public void setEqCtgTypeDesc(String eqCtgTypeDesc) {
		this.eqCtgTypeDesc = eqCtgTypeDesc;
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
	
	
	
}
