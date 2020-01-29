package dream.consult.comp.user.dto;

import common.bean.BaseDTO;

/**
 * CompUser Page - 공통 DTO
 * @author youngjoo38
 * @version $Id: ConsultCompUserCommonDTO.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class ConsultCompUserCommonDTO extends BaseDTO
{
    /**사용자ID*/
    private String userId                  = "";
    
    /**Filter 회사코드*/
    private String filterCompNo            = "";    
    /**Filter 회사명*/
    private String filterCompDesc          = "";
    /**Filter 권한그룹Id */
    private String filterUsrGrpId          = ""; 
    /**Filter 권한그룹 */
    private String filterUsrGrpDesc        = "";
    /**Filter 계정 ID*/
    private String filterUserId            = "";
    /**Filter 로그인계정*/
    private String filterUserNo            = "";
    /**Filter 계정명*/
    private String filterUserName          = "";
    /**Filter 사원명*/
    private String filterEmpName          = "";
    /**Filter 부서 ID*/
    private String filterDeptId            = "";
    /**Filter 부서명*/
    private String filterDeptDesc          = "";    
    /**Filter 사용여부 ID*/
    private String filterIsUseId           = "";
    /**Filter 사용여부 */
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
