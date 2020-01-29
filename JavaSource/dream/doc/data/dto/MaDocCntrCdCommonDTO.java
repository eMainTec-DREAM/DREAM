package dream.doc.data.dto;

import common.bean.BaseDTO;

/**
 * 일반자료실 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaDocCntrCdCommonDTO extends BaseDTO
{   
    /** 자료 Id */
    private String docCntrId                = "";
    
    /** Filter-회사코드 */
    private String filterCompNo             = "";
    /** 제목 */
    private String filterDescription        = "";
    /** 등록일자 From  */
    private String filterFromDate           = "";
    /** 등록일자  To */
    private String filterToDate             = "";
    /** 자료번호 */
    private String filterDocCntrNo          = "";
    /** 설비종류 */
    private String filterEqCtgId            = "";
    private String filterEqCtgDesc          = "";
    /** 등록부서Id */
    private String filterDeptId             = "";
    /** 등록부서명 */
    private String filterDeptDesc           = "";
    /** 등록자Id */
    private String filterUserId             = "";
    /** 등록자명 */
    private String filterUserName           = "";
    /** 공장 Id */
    private String filterPlantId       = "";
    /** 공장명 */
    private String filterPlantDesc     = "";
    /** 일반자료실 종류 */
    private String docCntrType              = "";
    /** 일반자료실 첨부파일종류 */
    private String objectType               = "";
    
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getObjectType()
    {
        return objectType;
    }
    public void setObjectType(String objectType)
    {
        this.objectType = objectType;
    }
    public String getDocCntrType()
    {
        return docCntrType;
    }
    public void setDocCntrType(String docCntrType)
    {
        this.docCntrType = docCntrType;
    }
    public String getFilterEqCtgId()
    {
        return filterEqCtgId;
    }
    public void setFilterEqCtgId(String filterEqCtgId)
    {
        this.filterEqCtgId = filterEqCtgId;
    }
    public String getFilterEqCtgDesc()
    {
        return filterEqCtgDesc;
    }
    public void setFilterEqCtgDesc(String filterEqCtgDesc)
    {
        this.filterEqCtgDesc = filterEqCtgDesc;
    }
    public String getDocCntrId()
    {
        return docCntrId;
    }
    public void setDocCntrId(String docCntrId)
    {
        this.docCntrId = docCntrId;
        super.setAuditKey(docCntrId);
    }
    public String getFilterCompNo()
    {
        return filterCompNo;
    }
    public void setFilterCompNo(String filterCompNo)
    {
        this.filterCompNo = filterCompNo;
    }
    public String getFilterDescription()
    {
        return filterDescription;
    }
    public void setFilterDescription(String filterDescription)
    {
        this.filterDescription = filterDescription;
    }
    public String getFilterFromDate()
    {
        return filterFromDate;
    }
    public void setFilterFromDate(String filterFromDate)
    {
        this.filterFromDate = filterFromDate;
    }
    public String getFilterToDate()
    {
        return filterToDate;
    }
    public void setFilterToDate(String filterToDate)
    {
        this.filterToDate = filterToDate;
    }
    public String getFilterDocCntrNo()
    {
        return filterDocCntrNo;
    }
    public void setFilterDocCntrNo(String filterDocCntrNo)
    {
        this.filterDocCntrNo = filterDocCntrNo;
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
    public String getFilterUserId()
    {
        return filterUserId;
    }
    public void setFilterUserId(String filterUserId)
    {
        this.filterUserId = filterUserId;
    }
    public String getFilterUserName()
    {
        return filterUserName;
    }
    public void setFilterUserName(String filterUserName)
    {
        this.filterUserName = filterUserName;
    }    
}
