package dream.consult.comp.time.dto;

import common.bean.BaseDTO;
/**
 * �����޷� LOV DTO
 * @author youngjoo38
 * @version $Id: LovLnWrkAcListDTO.java,v 1.0 2017/12/14 11:12:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class LovLnWrkAcListDTO extends BaseDTO
{
    /** �����޷� ID */
    private String lnWrkListId      = "";
    /** �����޷� DESC */
    private String lnWrkListDesc    = "";
    /** ȸ�� NO */
    private String compNo           = "";
    /** ȸ�� DESC */
    private String compDesc         = "";
    
    public String getLnWrkListId()
    {
        return lnWrkListId;
    }
    public void setLnWrkListId(String lnWrkListId)
    {
        this.lnWrkListId = lnWrkListId;
    }
    public String getLnWrkListDesc()
    {
        return lnWrkListDesc;
    }
    public void setLnWrkListDesc(String lnWrkListDesc)
    {
        this.lnWrkListDesc = lnWrkListDesc;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getCompDesc()
    {
        return compDesc;
    }
    public void setCompDesc(String compDesc)
    {
        this.compDesc = compDesc;
    }
}
