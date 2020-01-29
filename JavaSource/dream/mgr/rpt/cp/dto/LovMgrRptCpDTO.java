package dream.mgr.rpt.cp.dto;

import common.bean.BaseDTO;

/**
 * ��¹� ���� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class LovMgrRptCpDTO extends BaseDTO
{
    /** ��¹� ���� ID */
    private String rptCpFileId 	= "";
    /** ȸ�纰 ��¹� ID */
    private String rptCpListId 	= "";
    /** ��¹� NO */
    private String rptListNo 	= "";
    /** ��¹� �̸� */
    private String rptListName 	= "";
    /** ��¹������ּ� */
    private String svrAddr 		= "";
    /** ����Ʈ ������ ���ϸ� */
    private String designFile 	= "";
    /** ����Ʈ query ���ϸ� */
    private String queryFile 	= "";
    /** Report ���� */
    private String rptFileType 	= "";
    /** ��뿩�� */
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
