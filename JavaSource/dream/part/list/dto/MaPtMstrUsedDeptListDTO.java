package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 부품사용부서 목록 dto
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtMstrUsedDeptListDTO extends BaseDTO
{
    /** 자재사용부서Id - PK */
    private String ptUsedDeptId     = "";  
    /** 자재Id */
    private String partId           = "";
    /** 부서Id */
    private String deptId           = "";
    
    public String getPtUsedDeptId()
    {
        return ptUsedDeptId;
    }
    public void setPtUsedDeptId(String ptUsedDeptId)
    {
        this.ptUsedDeptId = ptUsedDeptId;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    } 

}