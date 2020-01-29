package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * WorkPmiCInsPoint Page - Detail DTO
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointDetailDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkPmiCInsPointDetailDTO extends BaseDTO
{
    /** KEY �ϻ����� ���˰�� ID */                                
    private String pmInsDPointId        = "";  
    
    /** �������� ��� ID */                                
    private String pmInsDListId        = "";  
    /**  �����׸� ID */                                
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
    private String stwrkPointDesc     = "";    
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
    private String checkTypeDesc        = "";    
    /** ���Ѱ� */
    private String checkMin         = "";
    /** ���ذ� */
    private String checkBasisVal    = "";
    /** ���Ѱ� */
    private String checkMax         = "";
    /** ��� */                                    
    private String remark           = "";
    
    /** ������/���� */                                    
    private String checkValUom        = "";

    /** ù��° ���˰� */                                    
    private String resultValue        = "";
    /** �ι�° ���˰� */                                    
    private String resultValue2        = "";
    /** ����° ���˰� */                                    
    private String resultValue3        = "";
    
    /** ������� ID */                                    
    private String pmPointRsltStatusId        = "";
    /** ������� */                                    
    private String pmPointRsltStatus        = "";
    /** �˻缼�γ��� */                                    
    private String insDetail        = "";
    
    
    public String getPmInsDListId()
    {
        return pmInsDListId;
    }
    public void setPmInsDListId(String pmInsDListId)
    {
        this.pmInsDListId = pmInsDListId;
    }
    public String getResultValue()
    {
        return resultValue;
    }
    public void setResultValue(String resultValue)
    {
        this.resultValue = CommonUtil.convertMoney(resultValue);
    }
    public String getResultValue2()
    {
        return resultValue2;
    }
    public void setResultValue2(String resultValue2)
    {
        this.resultValue2 = CommonUtil.convertMoney(resultValue2);
    }
    public String getResultValue3()
    {
        return resultValue3;
    }
    public void setResultValue3(String resultValue3)
    {
        this.resultValue3 = CommonUtil.convertMoney(resultValue3);
    }
    public String getPmPointRsltStatusId()
    {
        return pmPointRsltStatusId;
    }
    public void setPmPointRsltStatusId(String pmPointRsltStatusId)
    {
        this.pmPointRsltStatusId = pmPointRsltStatusId;
    }
    public String getPmInsDPointId()
    {
        return pmInsDPointId;
    }
    public void setPmInsDPointId(String pmInsDPointId)
    {
        this.pmInsDPointId = pmInsDPointId;
    }
    public String getPmPointRsltStatus()
    {
        return pmPointRsltStatus;
    }
    public void setPmPointRsltStatus(String pmPointRsltStatus)
    {
        this.pmPointRsltStatus = pmPointRsltStatus;
    }
    public String getInsDetail()
    {
        return insDetail;
    }
    public void setInsDetail(String insDetail)
    {
        this.insDetail = insDetail;
    }
    public String getCheckValUom()
    {
        return checkValUom;
    }
    public void setCheckValUom(String checkValUom)
    {
        this.checkValUom = checkValUom;
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
        this.stepNum = stepNum;
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
    public String getCheckTypeDesc()
    {
        return checkTypeDesc;
    }
    public void setCheckTypeDesc(String checkTypeDesc)
    {
        this.checkTypeDesc = checkTypeDesc;
    }
    public String getCheckMin()
    {
        return checkMin;
    }
    public void setCheckMin(String checkMin)
    {
        this.checkMin = checkMin;
    }
    public String getCheckBasisVal()
    {
        return checkBasisVal;
    }
    public void setCheckBasisVal(String checkBasisVal)
    {
        this.checkBasisVal = checkBasisVal;
    }
    public String getCheckMax()
    {
        return checkMax;
    }
    public void setCheckMax(String checkMax)
    {
        this.checkMax = checkMax;
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
