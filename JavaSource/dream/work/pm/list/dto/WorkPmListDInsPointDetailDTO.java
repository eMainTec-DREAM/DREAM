package dream.work.pm.list.dto;

import java.util.List;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * WorkPmListDInsPoint Page - Detail DTO
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointDetailDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkPmListDInsPointDetailDTO extends BaseDTO
{
    /**Key �ϻ������׸� ID */                                
    private String pmInsDPointId        = "";  

    /** �����׸� ID */                                
    private String pmPointId        = "";  
    /**  �����׸� NO */                                
    private String pmPointNo        = "";  
    
    /** ǥ�������׸� ID */                                
    private String checkPointId     = "";  
    /** PM ID */
    private String pmId             = "";
    
    /** �����׸� DESC */
    private String checkPoint       = "";    
    
    /** ǥ�������׸� ID */
    private String stwrkPointId     = "";    
    /** ǥ�������׸� DESC */
    private String stwrkPointDesc   = "";    
    /** ���˼��� */
    private String stepNum          = "";    
    /** ���˺��� ID */
    private String eqAsmbId         = "";    
    /** ���˺��� DESC */
    private String eqAsmbDesc       = "";    
    /** ��������*/                                   
    private String checkValue       = "";  
    /** ����*/                                     
    private String uom              = "";  
    /** ���࿩�� ID */                                  
    private String isActiveId       = "";  
    /** ���࿩�� */                                  
    private String isActive         = "";  
    /** ���˹�� ID */
    private String checkMethodId    = "";    
    /** ���˹�� DESC */
    private String checkMethod      = "";    
    /** �������� */
    private String fitBasis         = "";    
    /** ��ġ/���� ���� ID*/
    private String checkTypeId      = "";    
    /** ��ġ/���� ���� DESC*/
    private String checkType        = "";    
    /** ���Ѱ� */
    private String checkMin         = "";
    /** ���ذ� */
    private String checkBasisVal    = "";
    /** ���Ѱ� */
    private String checkMax         = "";
    /** ��� */                                    
    private String remark           = "";
	/** ���������� �����׸��ڵ� */
	private String eqCtgPmPointId 		= "";

    
	private List slideFileList      = null;
    
    public List getSlideFileList() {
		return slideFileList;
	}
	public void setSlideFileList(List slideFileList) {
		this.slideFileList = slideFileList;
	}
	
	public String getEqCtgPmPointId() {
		return eqCtgPmPointId;
	}
	public void setEqCtgPmPointId(String eqCtgPmPointId) {
		this.eqCtgPmPointId = eqCtgPmPointId;
	}
	public String getPmInsDPointId()
    {
        return pmInsDPointId;
    }
    public void setPmInsDPointId(String pmInsDPointId)
    {
        this.pmInsDPointId = pmInsDPointId;
    }
    public String getPmPointNo()
    {
        return pmPointNo;
    }
    public void setPmPointNo(String pmPointNo)
    {
        this.pmPointNo = pmPointNo;
    }
    public String getIsActiveId()
    {
        return isActiveId;
    }
    public void setIsActiveId(String isActiveId)
    {
        this.isActiveId = isActiveId;
    }
    public String getStwrkPointId()
    {
        return stwrkPointId;
    }
    public void setStwrkPointId(String stwrkPointId)
    {
        this.stwrkPointId = stwrkPointId;
    }
    public String getStwrkPointDesc()
    {
        return stwrkPointDesc;
    }
    public void setStwrkPointDesc(String stwrkPointDesc)
    {
        this.stwrkPointDesc = stwrkPointDesc;
    }
    public String getPmPointId()
    {
        return pmPointId;
    }
    public void setPmPointId(String pmPointId)
    {
        this.pmPointId = pmPointId;
        super.setAuditKey(pmPointId);
    }
    public String getCheckPointId()
    {
        return checkPointId;
    }
    public void setCheckPointId(String checkPointId)
    {
        this.checkPointId = checkPointId;
    }
    public String getPmId()
    {
        return pmId;
    }
    public void setPmId(String pmId)
    {
        this.pmId = pmId;
    }
    public String getCheckPoint()
    {
        return checkPoint;
    }
    public void setCheckPoint(String checkPoint)
    {
        this.checkPoint = checkPoint;
    }
    public String getStepNum()
    {
        return stepNum;
    }
    public void setStepNum(String stepNum)
    {
        this.stepNum = CommonUtil.convertMoney(stepNum);
    }
    public String getEqAsmbId()
    {
        return eqAsmbId;
    }
    public void setEqAsmbId(String eqAsmbId)
    {
        this.eqAsmbId = eqAsmbId;
    }
    public String getEqAsmbDesc()
    {
        return eqAsmbDesc;
    }
    public void setEqAsmbDesc(String eqAsmbDesc)
    {
        this.eqAsmbDesc = eqAsmbDesc;
    }
    public String getCheckValue()
    {
        return checkValue;
    }
    public void setCheckValue(String checkValue)
    {
        this.checkValue = checkValue;
    }
    public String getUom()
    {
        return uom;
    }
    public void setUom(String uom)
    {
        this.uom = uom;
    }
    public String getIsActive()
    {
        return isActive;
    }
    public void setIsActive(String isActive)
    {
        this.isActive = isActive;
    }
    public String getCheckMethodId()
    {
        return checkMethodId;
    }
    public void setCheckMethodId(String checkMethodId)
    {
        this.checkMethodId = checkMethodId;
    }
    public String getCheckMethod()
    {
        return checkMethod;
    }
    public void setCheckMethod(String checkMethod)
    {
        this.checkMethod = checkMethod;
    }
    public String getFitBasis()
    {
        return fitBasis;
    }
    public void setFitBasis(String fitBasis)
    {
        this.fitBasis = fitBasis;
    }
    public String getCheckTypeId()
    {
        return checkTypeId;
    }
    public void setCheckTypeId(String checkTypeId)
    {
        this.checkTypeId = checkTypeId;
    }
    public String getCheckType()
    {
        return checkType;
    }
    public void setCheckType(String checkType)
    {
        this.checkType = checkType;
    }
    public String getCheckMin()
    {
        return checkMin;
    }
    public void setCheckMin(String checkMin)
    {
        this.checkMin = CommonUtil.convertMoney(checkMin);
    }
    public String getCheckBasisVal()
    {
        return checkBasisVal;
    }
    public void setCheckBasisVal(String checkBasisVal)
    {
        this.checkBasisVal = CommonUtil.convertMoney(checkBasisVal);
    }
    public String getCheckMax()
    {
        return checkMax;
    }
    public void setCheckMax(String checkMax)
    {
        this.checkMax = CommonUtil.convertMoney(checkMax);
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
