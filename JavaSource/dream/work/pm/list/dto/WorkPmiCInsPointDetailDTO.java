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
    /** KEY 일상점검 점검결과 ID */                                
    private String pmInsDPointId        = "";  
    
    /** 예방점검 결과 ID */                                
    private String pmInsDListId        = "";  
    /**  점검항목 ID */                                
    private String pmPointId        = "";  
    /**  점검항목 NO */                                
    private String pmPointNo        = "";  
    /** 표준점검항목 ID */                                
    private String checkPointId     = "";  
    /** PM ID */
    private String pmId             = "";
    /** 점검항목 DESC */
    private String checkPoint       = "";    
    /** 표준점검항목 ID */
    private String stwrkPointId     = "";    
    /** 표준점검항목 DESC */
    private String stwrkPointDesc     = "";    
    /** 점검순서 */
    private String stepNum          = "";    
    /** 점검부위 ID */
    private String eqAsmbId         = "";    
    /** 점검부위 DESC */
    private String eqAsmbDesc       = "";    
    /** 측정내용*/                                   
    private String checkValue       = "";  
    /** 단위*/                                     
    private String uom              = "";  
    /** 시행여부 ID */                                  
    private String isActiveId       = "";  
    /** 시행여부 */                                  
    private String isActive         = "";  
    /** 점검방법 ID */
    private String checkMethodId    = "";    
    /** 점검방법 DESC */
    private String checkMethod      = "";    
    /** 적정기준 */
    private String fitBasis         = "";    
    /** 수치/판정 구분 ID*/
    private String checkTypeId      = "";    
    /** 수치/판정 구분 DESC*/
    private String checkTypeDesc        = "";    
    /** 하한값 */
    private String checkMin         = "";
    /** 기준값 */
    private String checkBasisVal    = "";
    /** 상한값 */
    private String checkMax         = "";
    /** 비고 */                                    
    private String remark           = "";
    
    /** 설정값/단위 */                                    
    private String checkValUom        = "";

    /** 첫번째 점검값 */                                    
    private String resultValue        = "";
    /** 두번째 점검값 */                                    
    private String resultValue2        = "";
    /** 세번째 점검값 */                                    
    private String resultValue3        = "";
    
    /** 판정결과 ID */                                    
    private String pmPointRsltStatusId        = "";
    /** 판정결과 */                                    
    private String pmPointRsltStatus        = "";
    /** 검사세부내용 */                                    
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
