package dream.consult.comp.user.dto;

import common.bean.BaseDTO;

/**
 * CompUser Page - ���� DTO
 * @author youngjoo38
 * @version $Id: ConsultCompUserCommonDTO.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class ConsultCompUserCommonDTO extends BaseDTO
{
    /**�����ID*/
    private String userId                  = "";
    
    /**Filter ȸ���ڵ�*/
    private String filterCompNo            = "";    
    /**Filter ȸ���*/
    private String filterCompDesc          = "";
    /**Filter ���ѱ׷�Id */
    private String filterUsrGrpId          = ""; 
    /**Filter ���ѱ׷� */
    private String filterUsrGrpDesc        = "";
    /**Filter ���� ID*/
    private String filterUserId            = "";
    /**Filter �α��ΰ���*/
    private String filterUserNo            = "";
    /**Filter ������*/
    private String filterUserName          = "";
    /**Filter �����*/
    private String filterEmpName          = "";
    /**Filter �μ� ID*/
    private String filterDeptId            = "";
    /**Filter �μ���*/
    private String filterDeptDesc          = "";    
    /**Filter ��뿩�� ID*/
    private String filterIsUseId           = "";
    /**Filter ��뿩�� */
    private String filterIsUseDesc         = "";
    
	public String getFilterUserId()
    {
        return filterUserId;
    }
    public void setFilterUserId(String filterUserId)
    {
        this.filterUserId = filterUserId;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getFilterCompNo()
    {
        return filterCompNo;
    }
    public void setFilterCompNo(String filterCompNo)
    {
        this.filterCompNo = filterCompNo;
    }
    public String getFilterCompDesc()
    {
        return filterCompDesc;
    }
    public void setFilterCompDesc(String filterCompDesc)
    {
        this.filterCompDesc = filterCompDesc;
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
    public String getFilterUserName()
    {
        return filterUserName;
    }
    public void setFilterUserName(String filterUserName)
    {
        this.filterUserName = filterUserName;
    }
    public String getFilterIsUseDesc()
    {
        return filterIsUseDesc;
    }
    public void setFilterIsUseDesc(String filterIsUseDesc)
    {
        this.filterIsUseDesc = filterIsUseDesc;
    }
    public String getFilterIsUseId()
    {
        return filterIsUseId;
    }
    public void setFilterIsUseId(String filterIsUseId)
    {
        this.filterIsUseId = filterIsUseId;
    }
    public String getFilterUsrGrpId()
    {
        return filterUsrGrpId;
    }
    public void setFilterUsrGrpId(String filterUsrGrpId)
    {
        this.filterUsrGrpId = filterUsrGrpId;
    }
    public String getFilterUsrGrpDesc()
    {
        return filterUsrGrpDesc;
    }
    public void setFilterUsrGrpDesc(String filterUsrGrpDesc)
    {
        this.filterUsrGrpDesc = filterUsrGrpDesc;
    }
    public String getFilterEmpName()
    {
        return filterEmpName;
    }
    public void setFilterEmpName(String filterEmpName)
    {
        this.filterEmpName = filterEmpName;
    }
    public String getFilterUserNo()
    {
        return filterUserNo;
    }
    public void setFilterUserNo(String filterUserNo)
    {
        this.filterUserNo = filterUserNo;
    }
    
}
