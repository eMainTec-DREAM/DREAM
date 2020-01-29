package dream.doc.img.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ÷�ι��� - �� DTO
 * @author  jung7126
 * @version $Id: maDocImgDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaDocImgDetailDTO extends BaseDTO
{
    /** ������� */
    private String regDate = "";
    /** ����� */
    private String regId = "";
    /** ��Ϻμ� */
    private String deptId = "";
    /** ����ID[�����̺� KEY] */
    private String objectId = "";
    /** ��������[��:���񸶽�:EQMSTR] */
    private String objectType = "";
    /** �������ϵ��ID */
    private String imageId = "";
    /** ȸ���ڵ� */
    private String compNo = "";
    
    private String imageNo  = "";
    
    private String objectTypeDesc   = "";
    
    private String description  = "";
    
    private String deptDesc     = "";
    
    private String regDesc  = "";
    
    private String nfFilePath   = "";
    
    private String imgDataId    = "";

    
    public String getImgDataId()
    {
        return imgDataId;
    }
    public void setImgDataId(String imgDataId)
    {
        this.imgDataId = imgDataId;
    }
    public void setNfFilePath(String nfFilePath)
    {
        this.nfFilePath = nfFilePath;
    }
    public String getNfFilePath()
    {
        return nfFilePath;
    }
    public String getImageNo()
    {
        return imageNo;
    }
    public void setImageNo(String imageNo)
    {
        this.imageNo = imageNo;
    }
    public String getObjectTypeDesc()
    {
        return objectTypeDesc;
    }
    public void setObjectTypeDesc(String objectTypeDesc)
    {
        this.objectTypeDesc = objectTypeDesc;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getRegDesc()
    {
        return regDesc;
    }
    public void setRegDesc(String regDesc)
    {
        this.regDesc = regDesc;
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
