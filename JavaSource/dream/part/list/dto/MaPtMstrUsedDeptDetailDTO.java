package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * ��ǰ���μ� �� dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaPtMstrUsedDeptDetailDTO extends BaseDTO
{
    /** ��ǰ���μ�Id - PK */
    private String ptUsedDeptId     = "";  
    /** ����Id */
    private String partId           = "";  
    /** �μ�Id */
    private String deptId           = "";
    /** �μ��ڵ� */
    private String deptNo           = "";
    /** �μ��� */
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