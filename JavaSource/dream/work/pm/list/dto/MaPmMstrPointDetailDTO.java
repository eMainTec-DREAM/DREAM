package dream.work.pm.list.dto;

import java.util.List;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �˻��׸� �� DTO
 * @author  jung7126
 * @version $Id: MaPmMstrPointDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class MaPmMstrPointDetailDTO extends BaseDTO
{
	/** �����׸�ID */
	private String pmPointId				= "";
	/** ���˼��� */
	private String stepNum 					= "";
	/** ���˺��� */
	private String eqAsmbDesc 				= "";
	/** ���˺��� */
    private String eqAsmbToDesc               = "";
	/** ���˺��� */
	private String eqAsmbId                 = "";
	/** �����׸� */
	private String checkPoint 				= "";
	/** ���˹�� */
	private String checkMethod 				= "";
	/** �������� */
	private String fitBasis 				= "";
	/** ��ġ�������и� */
	private String checkTypeDesc            = "";
	/** ��ġ�������� */
	private String checkType                = "";
	/** ��� */
	private String remark 					= "";
	/** ���� */
	private String uom                      = "";
	/** ��뿩�� */
	private String isActive                 = "";
	/** PM ID */
	private String pmId                     = "";
	/** ȸ���ڵ� */
	private String compNo                   = "";
	
	private String checkMin                 = "";
	
	private String checkMax                 = "";
	
	private String checkBasisVal            = "";
	
	private String equipId                  = "";
	
	/** ���������� �����׸��ڵ� */
	private String eqCtgPmPointId 		= "";
	
    /** ǥ�������׸� ID */
    private String stwrkPointId     = "";    
    /** ǥ�������׸� DESC */
    private String stwrkPointDesc   = "";
    /** MultiSelect ǥ�������׸� */
    private String multiStdPointKey     = "";    
    /** MultiSelect ǥ�������׸� */
    private String multiStdPointDesc   = "";
    /** pmType */
    private String pmType     = "";    
    /** ������ ���� */
	private String isRunValue				= "";
	/** ���� */
	private String fitRate					= "";
	
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
	public String getMultiStdPointKey() {
		return multiStdPointKey;
	}
	public void setMultiStdPointKey(String multiStdPointKey) {
		this.multiStdPointKey = multiStdPointKey;
	}
	public String getMultiStdPointDesc() {
		return multiStdPointDesc;
	}
	public void setMultiStdPointDesc(String multiStdPointDesc) {
		this.multiStdPointDesc = multiStdPointDesc;
	}
	public String getIsRunValue() {
		return isRunValue;
	}
	public void setIsRunValue(String isRunValue) {
		this.isRunValue = isRunValue;
	}
	public String getFitRate() {
		return fitRate;
	}
	public void setFitRate(String fitRate) {
		this.fitRate = fitRate;
	}
	public String getEqAsmbToDesc()
    {
        return eqAsmbToDesc;
    }
    public void setEqAsmbToDesc(String eqAsmbToDesc)
    {
        this.eqAsmbToDesc = eqAsmbToDesc;
    }
    public String getPmType()
    {
        return pmType;
    }
    public void setPmType(String pmType)
    {
        this.pmType = pmType;
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
    public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getCheckMin()
    {
        return checkMin;
    }
    public void setCheckMin(String checkMin)
    {
        this.checkMin = CommonUtil.convertMoney(checkMin);
    }
    public String getCheckMax()
    {
        return checkMax;
    }
    public void setCheckMax(String checkMax)
    {
        this.checkMax = CommonUtil.convertMoney(checkMax);
    }
    public String getCheckBasisVal()
    {
        return checkBasisVal;
    }
    public void setCheckBasisVal(String checkBasisVal)
    {
        this.checkBasisVal = CommonUtil.convertMoney(checkBasisVal);
    }
    public String getEqAsmbId()
    {
        return eqAsmbId;
    }
    public void setEqAsmbId(String eqAsmbId)
    {
        this.eqAsmbId = eqAsmbId;
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
    public String getStepNum()
    {
        return stepNum;
    }
    public void setStepNum(String stepNum)
    {
        this.stepNum = CommonUtil.convertMoney(stepNum);
    }
    public String getEqAsmbDesc()
    {
        return eqAsmbDesc;
    }
    public void setEqAsmbDesc(String eqAsmbDesc)
    {
        this.eqAsmbDesc = eqAsmbDesc;
    }
    public String getCheckPoint()
    {
        return checkPoint;
    }
    public void setCheckPoint(String checkPoint)
    {
        this.checkPoint = checkPoint;
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
    public String getCheckTypeDesc()
    {
        return checkTypeDesc;
    }
    public void setCheckTypeDesc(String checkTypeDesc)
    {
        this.checkTypeDesc = checkTypeDesc;
    }
    public String getCheckType()
    {
        return checkType;
    }
    public void setCheckType(String checkType)
    {
        this.checkType = checkType;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
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
    public String getPmId()
    {
        return pmId;
    }
    public void setPmId(String pmId)
    {
        this.pmId = pmId;
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



