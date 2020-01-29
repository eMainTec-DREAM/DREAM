package dream.doc.img.dto;

import common.bean.BaseDTO;

/**
 * ���� DTO
 * @author  jung7126
 * @version $Id: MaDocLibCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaDocImgCommonDTO extends BaseDTO
{
    /** ������� */
    private String regDate = "";
    /** ����� */
    private String regId = "";
    /** ����ڸ� */
    private String regDesc  = "";
    /** ��Ϻμ� */
    private String deptId = "";
    /** ��Ϻμ��� */
    private String deptDesc = "";
    /** ����ID[�����̺� KEY] */
    private String objectId = "";
    /** ��������[��:���񸶽�:EQMSTR] */
    private String objectType = "";
    
    private String objectTypeCode   = "";
    
    private String objectTypeDesc   = "";
    
    private String objectDesc = "";
    
    private String subImgType = "";
    
	public String getSubImgType() {
		return subImgType;
	}
	public void setSubImgType(String subImgType) {
		this.subImgType = subImgType;
	}
	public String getObjectDesc() {
		return objectDesc;
	}
	public void setObjectDesc(String objectDesc) {
		this.objectDesc = objectDesc;
	}
	public String getObjectTypeCode()
    {
        return objectTypeCode;
    }
    public void setObjectTypeCode(String objectTypeCode)
    {
        this.objectTypeCode = objectTypeCode;
    }
    /** �������ϵ��ID */
    private String imageId = "";
    /** ȸ���ڵ� */
    private String compNo = "";
    
    private String description  = "";

    
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getObjectTypeDesc()
    {
        return objectTypeDesc;
    }
    public void setObjectTypeDesc(String objectTypeDesc)
    {
        this.objectTypeDesc = objectTypeDesc;
    }
    public String getRegDesc()
    {
        return regDesc;
    }
    public void setRegDesc(String regDesc)
    {
        this.regDesc = regDesc;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getRegDate()
    {
        return regDate;
    }
    public void setRegDate(String regDate)
    {
        this.regDate = regDate;
    }
    public String getRegId()
    {
        return regId;
    }
    public void setRegId(String regId)
    {
        this.regId = regId;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
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
    public String getImageId()
    {
        return imageId;
    }
    public void setImageId(String imageId)
    {
        this.imageId = imageId;
        super.setAuditKey(imageId);
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
