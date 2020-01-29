package dream.work.rpt.toteng.dto;

import common.bean.BaseDTO;

/**
 * 에너지사용량(집계) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptTotEngCommonDTO extends BaseDTO
{
    /** 일자(년) */
    private String filterYear    		= "";

	public String getFilterYear() {
		return filterYear;
	}

	public void setFilterYear(String filterYear) {
		this.filterYear = filterYear;
	}
}