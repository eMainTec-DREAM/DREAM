package intf.dream.android.offline.madoclib.dto;

import common.bean.BaseDTO;

/**
 * 공통 DTO
 * @author  kim21017
 * @version $Id: AnIfDocLibCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class AnIfDocLibCommonDTO extends BaseDTO
{
    /** 문서코드 */
	private String docId            = "";
	/** 문서명 */
	private String docDesc          = "";
	/** 등록부서 */
    private String regDeptId        = "";
    /** 등록부서명 */
    private String regDeptDesc      = "";
    /** 문서분류 */
    private String docCateg         = "";
    /** 문서분류명 */
    private String docCategDesc     = "";
    /** 등록자 */
    private String regId            = "";
    /** 등록자명 */ 
    private String regDesc          = "";
    /** 참조ID */
    private String objectId         = "";
    /** 문서구분 */
    private String objectType       = "";
    /** 보안등급 */
    private String securGrade       = "";
    /**  */
    private String securGradeDesc   = "";
    /** 제목 */
    private String description      = "";
    
    /** 인터페이스 종류 */
    private String ifType           = "";
    /** 저장 파일 이름 */
    private String docDataId        = "";
    /** 저장 파일 저장 경로 */
    private String nfFilePath       = "";
    
    
    public String getDocDataId()
    {
        return docDataId;
    }

    public void setDocDataId(String docDataId)
    {
        this.docDataId = docDataId;
    }

    public String getNfFilePath()
    {
        return nfFilePath;
    }

    public void setNfFilePath(String nfFilePath)
    {
        this.nfFilePath = nfFilePath;
    }

    public String getIfType()
    {
        return ifType;
    }

    public void setIfType(String ifType)
    {
        this.ifType = ifType;
    }

    public String getSecurGrade() {
		return securGrade;
	}

	public void setSecurGrade(String securGrade) {
		this.securGrade = securGrade;
	}

	public String getSecurGradeDesc() {
		return securGradeDesc;
	}

	public void setSecurGradeDesc(String securGradeDesc) {
		this.securGradeDesc = securGradeDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

    public String getObjectType()
    {
        return objectType;
    }

    public void setObjectType(String objectType)
    {
        this.objectType = objectType;
    }

    public String getDocCategDesc()
    {
        return docCategDesc;
    }

    public void setDocCategDesc(String docCategDesc)
    {
        this.docCategDesc = docCategDesc;
    }

    public String getDocId()
    {
        return docId;
    }

    public void setDocId(String docId)
    {
        this.docId = docId;
    }

    public String getDocDesc()
    {
        return docDesc;
    }

    public void setDocDesc(String docDesc)
    {
        this.docDesc = docDesc;
    }

    public String getRegDeptId()
    {
        return regDeptId;
    }

    public void setRegDeptId(String regDeptId)
    {
        this.regDeptId = regDeptId;
    }

    public String getRegDeptDesc()
    {
        return regDeptDesc;
    }

    public void setRegDeptDesc(String regDeptDesc)
    {
        this.regDeptDesc = regDeptDesc;
    }

    public String getDocCateg()
    {
        return docCateg;
    }

    public void setDocCateg(String docCateg)
    {
        this.docCateg = docCateg;
    }

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }

    public String getRegDesc()
    {
        return regDesc;
    }

    public void setRegDesc(String regDesc)
    {
        this.regDesc = regDesc;
    }
    
}
