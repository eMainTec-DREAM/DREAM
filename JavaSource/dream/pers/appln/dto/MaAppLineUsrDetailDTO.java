package dream.pers.appln.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��  DTO
 * @author  kim21017
 * @version $Id: MaAppLineUsrDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaAppLineUsrDetailDTO extends BaseDTO
{
    /**  */
    private String remark = "";
    /** ���� */
    private String title = "";
    /** ���缱�ۼ��� */
    private String empId = "";
    /** ���缱ID */
    private String apprlineId = "";
    /** ȸ���ڵ� */
    private String compNo = "";
    /** ����ó���� */
    private String apprBy = "";
    /** ����ó���ڸ� */
    private String apprByName = "";
    /** ������� */
    private String apprSeq = "";
    /** ����ó����ID */
    private String apprlineusrId = "";
    /** ���� */
    private String grade        = "";
    /** �μ��� */
    private String deptName     = "";
    private String apprUsrTypeId     = "";
    private String apprUsrTypeDesc     = "";
    
    
    
    
    
	/** �亯ID */
	private String answerId 	= "";
	/** �亯No */
	private String answerNo 	= "";
	/** �亯���� */
	private String regDate 		= "";
	/** �μ�id */
	private String deptId 		= "";
	/** �μ��� */	
	private String deptDesc 	= "";
	/** �亯��id */
	private String userId 		= "";
	/** �亯�ڸ� */
	private String userDesc 	= "";
	/**���� */
	private String answerDesc 	= "";
	
	
	
	
	public String getApprUsrTypeId() {
		return apprUsrTypeId;
	}
	public void setApprUsrTypeId(String apprUsrTypeId) {
		this.apprUsrTypeId = apprUsrTypeId;
	}
	public String getApprUsrTypeDesc() {
		return apprUsrTypeDesc;
	}
	public void setApprUsrTypeDesc(String apprUsrTypeDesc) {
		this.apprUsrTypeDesc = apprUsrTypeDesc;
	}
	public String getGrade()
    {
        return grade;
    }
    public void setGrade(String grade)
    {
        this.grade = grade;
    }
    public String getDeptName()
    {
        return deptName;
    }
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getApprlineId()
    {
        return apprlineId;
    }
    public void setApprlineId(String apprlineId)
    {
        this.apprlineId = apprlineId;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getApprBy()
    {
        return apprBy;
    }
    public void setApprBy(String apprBy)
    {
        this.apprBy = apprBy;
    }
    public String getApprByName()
    {
        return apprByName;
    }
    public void setApprByName(String apprByName)
    {
        this.apprByName = apprByName;
    }
    public String getApprSeq()
    {
        return apprSeq;
    }
    public void setApprSeq(String apprSeq)
    {
        this.apprSeq = apprSeq;
    }
    public String getApprlineusrId()
    {
        return apprlineusrId;
    }
    public void setApprlineusrId(String apprlineusrId)
    {
        this.apprlineusrId = apprlineusrId;
        super.setAuditKey(apprlineusrId);
    }
    public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getAnswerNo() {
		return answerNo;
	}
	public void setAnswerNo(String answerNo) {
		this.answerNo = answerNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getAnswerDesc() {
		return answerDesc;
	}
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
}