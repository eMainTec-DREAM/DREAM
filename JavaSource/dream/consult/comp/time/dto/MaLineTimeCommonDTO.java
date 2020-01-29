package dream.consult.comp.time.dto;

import common.bean.BaseDTO;

/**
 * 가동시간설정 공통
 * @author  kim21017
 * @version $Id: MaLineTimeCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaLineTimeCommonDTO extends BaseDTO
{
	/** 가동달력ID */
	private String lnWrkListId 					= "";
	/** 회사 */
	private String compNo 		     	= "";
	/** 필터 회사명 */
	private String filterCompDesc 			= "";
	/** 필터 가동달력명 */
	private String filterLnWrkListDesc         = "";
	
    public String getLnWrkListId()
    {
        return lnWrkListId;
    }
    public void setLnWrkListId(String lnWrkListId)
    {
        this.lnWrkListId = lnWrkListId;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getFilterCompDesc()
    {
        return filterCompDesc;
    }
    public void setFilterCompDesc(String filterCompDesc)
    {
        this.filterCompDesc = filterCompDesc;
    }
    public String getFilterLnWrkListDesc()
    {
        return filterLnWrkListDesc;
    }
    public void setFilterLnWrkListDesc(String filterLnWrkListDesc)
    {
        this.filterLnWrkListDesc = filterLnWrkListDesc;
    }
	
}
