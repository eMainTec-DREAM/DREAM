package dream.doc.data.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �Ϲ��ڷ�� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaDocCntrCdDetailDTO extends BaseDTO
{
	/** �ڷ�ID */
	private String docCntrId       = "";
	/** �ڷ��ȣ */
	private String docCntrNo       = "";
	/** ����ڵ�ID(��������) */
	private String eqCtgId         = "";
	/** ����ڵ��(��������) */
	private String eqCtgDesc       = "";
	/** �ڷ�����ID */
	private String docCntrCateg    = "";
	/** �ڷ������� */
	private String docCntrCategDesc= "";
	/** ���� */
	private String description 	   = "";
	/** ���� */
	private String remark          = "";
	/** ������� */
	private String regDate         = "";
	/** �μ�ID */
	private String deptId          = "";
	/** �μ��� */
	private String deptDesc        = "";
	/** �����ID */
	private String userId          = "";
	/** ����ڸ� */
	private String userName        = "";
	/** �Ϲ��ڷ�� ���� */
	private String docCntrType     = "";
	
    public String getDocCntrType()
    {
        return docCntrType;
    }
    public void setDocCntrType(String docCntrType)
    {
        this.docCntrType = docCntrType;
    }
    public String getDocCntrCateg()
    {
        return docCntrCateg;
    }
    public void setDocCntrCateg(String docCntrCateg)
    {
        this.docCntrCateg = docCntrCateg;
    }
    public String getDocCntrCategDesc()
    {
        return docCntrCategDesc;
    }
    public void setDocCntrCategDesc(String docCntrCategDesc)
    {
        this.docCntrCategDesc = docCntrCategDesc;
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
    public String getDocCntrNo()
    {
        return docCntrNo;
    }
    public void setDocCntrNo(String docCntrNo)
    {
        this.docCntrNo = docCntrNo;
    }
    public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getEqCtgDesc()
    {
        return eqCtgDesc;
    }
    public void setEqCtgDesc(String eqCtgDesc)
    {
        this.eqCtgDesc = eqCtgDesc;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getRegDate()
    {
        return regDate;
    }
    public void setRegDate(String regDate)
    {
        this.regDate = CommonUtil.convertDate(regDate);
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
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
