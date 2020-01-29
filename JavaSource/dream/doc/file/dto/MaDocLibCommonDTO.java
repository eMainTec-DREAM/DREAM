package dream.doc.file.dto;

import common.bean.BaseDTO;

/**
 * ���� DTO
 * @author  jung7126
 * @version $Id: MaDocLibCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaDocLibCommonDTO extends BaseDTO
{
    /** �����ڵ� */
	private String docId            = "";
	/** ������ */
	private String docDesc          = "";
	/** ��Ϻμ� */
    private String regDeptId        = "";
    /** ��Ϻμ��� */
    private String regDeptDesc      = "";
    /** ����Ÿ�� */
    private String docCateg         = "";
    /** ����Ÿ�Ը� */
    private String docCategDesc     = "";
    /** ����� */
    private String regId            = "";
    /** ����ڸ� */ 
    private String regDesc          = "";
    /** ����ID */
    private String objectId         = "";
    /** ����No */
    private String objectNo         = "";
    /** �������� */
    private String objectType       = "";
    /** �������и� */
    private String objectTypeDesc       = "";
    /** ���ȵ�� */
    private String securGrade       = "";
    /**  */
    private String securGradeDesc   = "";
    /** ���� */
    private String description      = "";
    /** �����з� ID */
    private String docctgId			= "";
    /** �����з��� */
    private String docctgDesc		= "";
    /** ������������ */
    private String pubdocYn			= "";
    /** Doc ID form Link */
    private String addedDocId		= "";
    /** ����ID */
    private String filterEquipId    = "";
    /** ����� */
    private String filterEquipDesc  = "";
    /** ����ID */
    private String plantId    		= "";
    /** ����� */
    private String plantDesc  		= "";
    /** ��� */
    private String remark			= "";
    
    
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

	public String getFilterEquipId()
    {
        return filterEquipId;
    }

    public void setFilterEquipId(String filterEquipId)
    {
        this.filterEquipId = filterEquipId;
    }

    public String getFilterEquipDesc()
    {
        return filterEquipDesc;
    }

    public void setFilterEquipDesc(String filterEquipDesc)
    {
        this.filterEquipDesc = filterEquipDesc;
    }

    public String getAddedDocId() {
		return addedDocId;
	}

	public void setAddedDocId(String addedDocId) {
		this.addedDocId = addedDocId;
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
    
    public String getObjectNo()
    {
        return objectNo;
    }

    public void setObjectNo(String objectNo)
    {
        this.objectNo = objectNo;
    }
    

    public String getObjectType()
    {
        return objectType;
    }

    public void setObjectType(String objectType)
    {
        this.objectType = objectType;
    }
    
    public String getObjectTypeDesc()
    {
        return objectTypeDesc;
    }

    public void setObjectTypeDesc(String objectTypeDesc)
    {
        this.objectTypeDesc = objectTypeDesc;
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
        super.setAuditKey(docId);
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
