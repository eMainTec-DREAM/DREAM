package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * ǥ�������׸� �˾� DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovStdCheckPointListDTO extends BaseDTO
{
    /** ǥ�������׸� ID */
    private String stdCheckPointId      = "";
    /** ǥ�������׸� NO */
    private String stdCheckPointNo      = "";
    /** �׸񱸺� */
    private String checkPointType       = "";
    /** �׸� */
    private String checkPoint       = "";
    /** ���� */
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
