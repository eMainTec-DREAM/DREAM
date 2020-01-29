package dream.app.not.dto;

import common.bean.BaseDTO;

/**
 * 통보문서 DTO
 * @author  javaworker
 * @version $Id: AppNotDTO.java,v 1.1 2013/08/30 09:10:26 javaworker Exp $ 
 * @since   1.0
 */
public class AppNotDTO extends BaseDTO
{
    /** 확인여부(Y/N) */
    private String isCheck = "";
    /** 결재번호(Key) */
    private String appFlowNo = "";
    
    public String getIsCheck()
    {
        return isCheck;
    }
    public void setIsCheck(String isCheck)
    {
        this.isCheck = isCheck;
    }
    public String getAppFlowNo()
    {
        return appFlowNo;
    }
    public void setAppFlowNo(String appFlowNo)
    {
        this.appFlowNo = appFlowNo;
    }
}