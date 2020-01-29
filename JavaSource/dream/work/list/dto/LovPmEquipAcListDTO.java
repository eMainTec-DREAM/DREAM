package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 생산품목 Lov 팝업 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovPmEquipAcListDTO extends BaseDTO
{
    /**   생산품목 ID */
    private String productId            = "";
    /**   생산품목 코드 */
    private String productNo            = "";
    /**   생산품목명 */
    private String productDesc          = "";
    /**   비고 */
    private String remark               = "";
    /**   사용여부 */
    private String isUse            = "";
    
    /**   예방점검 ID */
    private String pmId            = "";
    /**   예방점검 # */
    private String pmNo            = "";
    /**   예방점검 Desc */
    private String pmDesc            = "";
    
    /**   pmEquipId  */
    private String pmEquipId            = "";
    
    /**   설비 ID */
    private String equipId            = "";
    /**   설비 DESC */
    private String equipDesc            = "";
    /**   부서 ID */
    private String deptId            = "";
    /**   부서 DESC */
    private String deptDesc            = "";
    /**   담당자 ID */
    private String empId            = "";
    /**   담당자 DESC */
    private String empDesc            = "";
    
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
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
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpDesc()
    {
        return empDesc;
    }
    public void setEmpDesc(String empDesc)
    {
        this.empDesc = empDesc;
    }
    public String getPmEquipId()
    {
        return pmEquipId;
    }
    public void setPmEquipId(String pmEquipId)
    {
        this.pmEquipId = pmEquipId;
    }
    public String getPmId()
    {
        return pmId;
    }
    public void setPmId(String pmId)
    {
        this.pmId = pmId;
    }
    public String getPmNo()
    {
        return pmNo;
    }
    public void setPmNo(String pmNo)
    {
        this.pmNo = pmNo;
    }
    public String getPmDesc()
    {
        return pmDesc;
    }
    public void setPmDesc(String pmDesc)
    {
        this.pmDesc = pmDesc;
    }
    public String getProductId()
    {
        return productId;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
    }
    public String getProductNo()
    {
        return productNo;
    }
    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }
    public String getProductDesc()
    {
        return productDesc;
    }
    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
