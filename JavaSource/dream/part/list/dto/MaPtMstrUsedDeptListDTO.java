package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * ��ǰ���μ� ��� dto
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtMstrUsedDeptListDTO extends BaseDTO
{
    /** ������μ�Id - PK */
    private String ptUsedDeptId     = "";  
    /** ����Id */
    private String partId           = "";
    /** �μ�Id */
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