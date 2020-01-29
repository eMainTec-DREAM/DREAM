package dream.org.dept.dto;

import common.bean.BaseDTO;

/**
 * 부서검색 팝업 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovDeptListDTO extends BaseDTO
{
    /** Search Code */
    private String deptNo			= "";
    /** Search Description */
    private String deptDesc			= "";
    /** extCode1 */
    private String extCode1 		= "";
    /** extCode2 */
    private String extCode2 		= "";
    /** code type : dept_categ의 검색 조건으로 사용 */
    private String codeType 		= "";
    /** 레벨 */
    private String lvl				= "";
    /** 보전부서여부 */
    private String isMaint           = "";
    private String isMaintDesc     = "";
    /** 공장 ID */
    private String plantId            = "";
    /** 공장 DESC */
    private String plantDesc          = "";
    
    
    public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	public String getDeptNo()
    {
        return deptNo;
    }
    public void setDeptNo(String deptNo)
    {
        this.deptNo = deptNo;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getExtCode1()
    {
        return extCode1;
    }
    public void setExtCode1(String extCode1)
    {
        this.extCode1 = extCode1;
    }
    public String getExtCode2()
    {
        return extCode2;
    }
    public void setExtCode2(String extCode2)
    {
        this.extCode2 = extCode2;
    }
    public String getCodeType()
    {
        return codeType;
    }
    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }
    public String getIsMaint()
    {
        return isMaint;
    }
    public void setIsMaint(String isMaint)
    {
        this.isMaint = isMaint;
    }
    public String getIsMaintDesc()
    {
        return isMaintDesc;
    }
    public void setIsMaintDesc(String isMaintDesc)
    {
        this.isMaintDesc = isMaintDesc;
    }
    

}
