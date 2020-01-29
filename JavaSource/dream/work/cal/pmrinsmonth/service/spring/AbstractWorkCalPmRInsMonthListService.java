package dream.work.cal.pmrinsmonth.service.spring;

import dream.org.emp.service.LovEmpListService;
import dream.work.cal.pmrinsmonth.service.WorkCalPmRInsMonthListService;

public abstract class AbstractWorkCalPmRInsMonthListService implements WorkCalPmRInsMonthListService {

	protected WorkCalPmRInsMonthListService workCalPmRInsMonthListService;
	
    public AbstractWorkCalPmRInsMonthListService(WorkCalPmRInsMonthListService workCalPmRInsMonthListService)
	{
		this.workCalPmRInsMonthListService = workCalPmRInsMonthListService;
	}
}
