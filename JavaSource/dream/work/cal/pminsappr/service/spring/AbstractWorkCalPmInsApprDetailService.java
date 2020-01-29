package dream.work.cal.pminsappr.service.spring;

import dream.work.cal.pminsappr.service.WorkCalPmInsApprDetailService;

public abstract class AbstractWorkCalPmInsApprDetailService implements WorkCalPmInsApprDetailService {

	protected WorkCalPmInsApprDetailService workCalPmInsApprDetailService;
	
	public AbstractWorkCalPmInsApprDetailService(WorkCalPmInsApprDetailService workCalPmInsApprDetailService)
	{
		this.workCalPmInsApprDetailService = workCalPmInsApprDetailService;
	}

}
