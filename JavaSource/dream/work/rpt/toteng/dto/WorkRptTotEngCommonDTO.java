package dream.work.rpt.toteng.dto;

import common.bean.BaseDTO;

/**
 * ��������뷮(����) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptTotEngCommonDTO extends BaseDTO
{
    /** ����(��) */
    private String filterYear    		= "";

	public String getFilterYear() {
		return filterYear;
	}

	public void setFilterYear(String filterYear) {
		this.filterYear = filterYear;
	}
}