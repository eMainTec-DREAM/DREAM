package dream.mgr.usage.cal.dto;

import common.bean.BaseDTO;
/**
 * ���޷� LOV DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class LovUsageWrkAcListDTO extends BaseDTO
{
    /** ���޷� ID */
    private String lnWrkListId      = "";
    /** ���޷� DESC */
    private String lnWrkListDesc    = "";
    /** ȸ�� NO */
    private String compNo           = "";
    /** ȸ�� DESC */
    private String compDesc         = "";
    
    public String getLnWrkListId() {
		return lnWrkListId;
	}
	public void setLnWrkListId(String lnWrkListId) {
		this.lnWrkListId = lnWrkListId;
	}
	public String getLnWrkListDesc() {
		return lnWrkListDesc;
	}
	public void setLnWrkListDesc(String lnWrkListDesc) {
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
