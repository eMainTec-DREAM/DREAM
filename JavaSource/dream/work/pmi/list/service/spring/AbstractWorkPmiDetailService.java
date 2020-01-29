package dream.work.pmi.list.service.spring;

import dream.work.pmi.list.service.WorkPmiDetailService;

public abstract class AbstractWorkPmiDetailService implements WorkPmiDetailService {

	protected WorkPmiDetailService workPmiDetailService;
	
	public AbstractWorkPmiDetailService(WorkPmiDetailService workPmiDetailService)
	{
		this.workPmiDetailService = workPmiDetailService;
	}
	
}
