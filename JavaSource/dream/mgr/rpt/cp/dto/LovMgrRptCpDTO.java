package dream.mgr.rpt.cp.dto;

import common.bean.BaseDTO;

/**
 * 출력물 선택 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class LovMgrRptCpDTO extends BaseDTO
{
    /** 출력물 파일 ID */
    private String rptCpFileId 	= "";
    /** 회사별 출력물 ID */
    private String rptCpListId 	= "";
    /** 출력물 NO */
    private String rptListNo 	= "";
    /** 출력물 이름 */
    private String rptListName 	= "";
    /** 출력물서버주소 */
    private String svrAddr 		= "";
    /** 레포트 디자인 파일명 */
    private String designFile 	= "";
    /** 레포트 query 파일명 */
    private String queryFile 	= "";
    /** Report 종류 */
    private String rptFileType 	= "";
    /** 사용여부 */
    private String isUse 	    = "";
    /** param */
    private String param 		= "";
    
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getRptListName()
    {
        return rptListName;
    }
    public void setRptListName(String rptListName)
    {
        this.rptListName = rptListName;
    }
    public String getRptCpFileId()
    {
        return rptCpFileId;
    }
    public void setRptCpFileId(String rptCpFileId)
    {
        this.rptCpFileId = rptCpFileId;
    }
    public String getRptCpListId()
    {
        return rptCpListId;
    }
    public void setRptCpListId(String rptCpListId)
    {
        this.rptCpListId = rptCpListId;
    }
    public String getSvrAddr()
    {
        return svrAddr;
    }
    public void setSvrAddr(String svrAddr)
    {
        this.svrAddr = svrAddr;
    }
    public String getDesignFile()
    {
        return designFile;
    }
    public void setDesignFile(String designFile)
    {
        this.designFile = designFile;
    }
    public String getQueryFile()
    {
        return queryFile;
    }
    public void setQueryFile(String queryFile)
    {
        this.queryFile = queryFile;
    }
    public String getRptFileType()
    {
        return rptFileType;
    }
    public void setRptFileType(String rptFileType)
    {
        this.rptFileType = rptFileType;
    }
    public String getRptListNo()
    {
        return rptListNo;
    }
    public void setRptListNo(String rptListNo)
    {
        this.rptListNo = rptListNo;
    }
    public String getParam()
    {
        return param;
    }
    public void setParam(String param)
    {
        this.param = param;
    }
}
