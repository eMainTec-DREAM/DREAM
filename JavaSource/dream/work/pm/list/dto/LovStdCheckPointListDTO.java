package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * 표준점검항목 팝업 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovStdCheckPointListDTO extends BaseDTO
{
    /** 표준점검항목 ID */
    private String stdCheckPointId      = "";
    /** 표준점검항목 NO */
    private String stdCheckPointNo      = "";
    /** 항목구분 */
    private String checkPointType       = "";
    /** 항목 */
    private String checkPoint       = "";
    /** 내용 */
    private String checkValue       = "";
    
    
    public String getStdCheckPointId()
    {
        return stdCheckPointId;
    }
    public void setStdCheckPointId(String stdCheckPointId)
    {
        this.stdCheckPointId = stdCheckPointId;
    }
    public String getStdCheckPointNo()
    {
        return stdCheckPointNo;
    }
    public void setStdCheckPointNo(String stdCheckPointNo)
    {
        this.stdCheckPointNo = stdCheckPointNo;
    }
    public String getCheckPointType()
    {
        return checkPointType;
    }
    public void setCheckPointType(String checkPointType)
    {
        this.checkPointType = checkPointType;
    }
    public String getCheckPoint()
    {
        return checkPoint;
    }
    public void setCheckPoint(String checkPoint)
    {
        this.checkPoint = checkPoint;
    }
    public String getCheckValue()
    {
        return checkValue;
    }
    public void setCheckValue(String checkValue)
    {
        this.checkValue = checkValue;
    }
}
