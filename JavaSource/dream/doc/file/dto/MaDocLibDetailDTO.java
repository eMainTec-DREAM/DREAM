package dream.doc.file.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 첨부문서 - 상세 DTO
 * @author  jung7126
 * @version $Id: MaDocLibDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaDocLibDetailDTO extends BaseDTO
{
    /** 등록일자 */
    private String regDate = "";
    /** 등록자 */
    private String regId = "";
    /**  */
    private String regIdDesc    = "";
    /** 등록부서 */
    private String deptId = "";
    /**  */
    private String deptIdDesc   = "";
    /** 보안등급 */
    private String securGrade = "";
    /**  */
    private String securGradeDesc   = "";
    /** 문서타입(사양서,도면,기타) */
    private String docCateg = "";
    /**  */
    private String docCategDesc = "";
    /** 제목 */
    private String description = "";
    /** 문서번호 */
    private String docNo = "";
    /** 문서코드 */
    private String docId = "";
    /** 회사코드 */
    private String compNo = "";
    
    private String docDataId    = "";
    
    private String nfFilePath   = "";
    /** 문서분류 ID */
    private String docctgId      = "";
    /** 문서분류명 */
    private String docctgDesc   = "";
    
    private String pubdocYn      = "N";
    
    private String objectType      = "";
    
    private String objectTypeDesc      = "";
    
    /** 공장 ID */
    private String plantId      = "";
    /** 공장명 */
    private String plantDesc   	= "";
    /** 비고 */
    private String remark   	= "";
    
    
    public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getObjectType() {
      return objectType;
   }
   public void setObjectType(String objectType) {
      this.objectType = objectType;
   }
   public String getObjectTypeDesc() {
      return objectTypeDesc;
   }
   public void setObjectTypeDesc(String objectTypeDesc) {
      this.objectTypeDesc = objectTypeDesc;
   }
   public String getPubdocYn() {
      return pubdocYn;
   }
   public void setPubdocYn(String pubdocYn) {
      this.pubdocYn = pubdocYn;
   }
   public String getDocctgId() {
      return docctgId;
   }
   public void setDocctgId(String docctgId) {
      this.docctgId = docctgId;
   }
   public String getDocctgDesc() {
      return docctgDesc;
   }
   public void setDocctgDesc(String docctgDesc) {
      this.docctgDesc = docctgDesc;
   }
   public String getNfFilePath()
    {
        return nfFilePath;
    }
    public void setNfFilePath(String nfFilePath)
    {
        this.nfFilePath = nfFilePath;
    }
    public String getDocDataId()
    {
        return docDataId;
    }
    public void setDocDataId(String docDataId)
    {
        this.docDataId = docDataId;
    }
    public String getRegDate()
    {
        return regDate;
    }
    public void setRegDate(String regDate)
    {
        this.regDate = CommonUtil.convertDate(regDate);
    }
    public String getRegId()
    {
        return regId;
    }
    public void setRegId(String regId)
    {
        this.regId = regId;
    }
    public String getRegIdDesc()
    {
        return regIdDesc;
    }
    public void setRegIdDesc(String regIdDesc)
    {
        this.regIdDesc = regIdDesc;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptIdDesc()
    {
        return deptIdDesc;
    }
    public void setDeptIdDesc(String deptIdDesc)
    {
        this.deptIdDesc = deptIdDesc;
    }
    public String getSecurGrade()
    {
        return securGrade;
    }
    public void setSecurGrade(String securGrade)
    {
        this.securGrade = securGrade;
    }
    public String getSecurGradeDesc()
    {
        return securGradeDesc;
    }
    public void setSecurGradeDesc(String securGradeDesc)
    {
        this.securGradeDesc = securGradeDesc;
    }
    public String getDocCateg()
    {
        return docCateg;
    }
    public void setDocCateg(String docCateg)
    {
        this.docCateg = docCateg;
    }
    public String getDocCategDesc()
    {
        return docCategDesc;
    }
    public void setDocCategDesc(String docCategDesc)
    {
        this.docCategDesc = docCategDesc;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getDocNo()
    {
        return docNo;
    }
    public void setDocNo(String docNo)
    {
        this.docNo = docNo;
    }
    public String getDocId()
    {
        return docId;
    }
    public void setDocId(String docId)
    {
        this.docId = docId;
        super.setAuditKey(docId);
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
}