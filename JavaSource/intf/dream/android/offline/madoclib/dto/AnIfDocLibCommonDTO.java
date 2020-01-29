package intf.dream.android.offline.madoclib.dto;

import common.bean.BaseDTO;

/**
 * ���� DTO
 * @author  kim21017
 * @version $Id: AnIfDocLibCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class AnIfDocLibCommonDTO extends BaseDTO
{
    /** �����ڵ� */
	private String docId            = "";
	/** ������ */
	private String docDesc          = "";
	/** ��Ϻμ� */
    private String regDeptId        = "";
    /** ��Ϻμ��� */
    private String regDeptDesc      = "";
    /** �����з� */
    private String docCateg         = "";
    /** �����з��� */
    private String docCategDesc     = "";
    /** ����� */
    private String regId            = "";
    /** ����ڸ� */ 
    private String regDesc          = "";
    /** ����ID */
    private String objectId         = "";
    /** �������� */
    private String objectType       = "";
    /** ���ȵ�� */
    private String securGrade       = "";
    /**  */
    private String securGradeDesc   = "";
    /** ���� */
    private String description      = "";
    
    /** �������̽� ���� */
    private String ifType           = "";
    /** ���� ���� �̸� */
    private String docDataId        = "";
    /** ���� ���� ���� ��� */
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
