package dream.consult.comp.list.dto;

import common.bean.BaseDTO;

/**
 * 회사설정 공통 DTO
 * @author  kim21017
 * @version $Id: MaCompMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaCompMngCommonDTO extends BaseDTO
{
	/** 회사설정 ID */
	private String compNo 				= "";
	/** 회사 comp_id */
	private String compId				= "";
	/** 필터 회사설정 No */
	private String filterCompNo 		= "";
	/** 필터 회사설정 명 */
    private String filterCompName       = "";
    
    private String filterInitCtPathYn   = "";
    
    private String filterIsUse          = "";
    
    
	public String getFilterIsUse()
    {
        return filterIsUse;
    }
    public void setFilterIsUse(String filterIsUse)
    {
        this.filterIsUse = filterIsUse;
    }
    public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getFilterInitCtPathYn()
    {
        return filterInitCtPathYn;
    }
    public void setFilterInitCtPathYn(String filterInitCtPathYn)
    {
        this.filterInitCtPathYn = filterInitCtPathYn;
    }
    public String getFilterCompName()
    {
        return filterCompName;
    }
    public void setFilterCompName(String filterCompName)
    {
        this.filterCompName = filterCompName;
    }
    public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getFilterCompNo() {
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) {
		this.filterCompNo = filterCompNo;
	}
}
