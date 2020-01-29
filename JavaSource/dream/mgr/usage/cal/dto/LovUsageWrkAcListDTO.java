package dream.mgr.usage.cal.dto;

import common.bean.BaseDTO;
/**
 * 사용달력 LOV DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class LovUsageWrkAcListDTO extends BaseDTO
{
    /** 사용달력 ID */
    private String lnWrkListId      = "";
    /** 사용달력 DESC */
    private String lnWrkListDesc    = "";
    /** 회사 NO */
    private String compNo           = "";
    /** 회사 DESC */
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
