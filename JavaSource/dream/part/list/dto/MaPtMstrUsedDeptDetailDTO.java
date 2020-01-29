package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 부품사용부서 상세 dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaPtMstrUsedDeptDetailDTO extends BaseDTO
{
    /** 부품사용부서Id - PK */
    private String ptUsedDeptId     = "";  
    /** 자재Id */
    private String partId           = "";  
    /** 부서Id */
    private String deptId           = "";
    /** 부서코드 */
    private String deptNo           = "";
    /** 부서명 */
    private String deptDesc         = "";
    
    public String getDeptNo()
    {
        return deptNo;
    }
    public void setDeptNo(String deptNo)
    {
        this.deptNo = deptNo;
    }
    public String getPtUsedDeptId()
    {
        return ptUsedDeptId;
    }
    public void setPtUsedDeptId(String ptUsedDeptId)
    {
        this.ptUsedDeptId = ptUsedDeptId;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }  
    
}