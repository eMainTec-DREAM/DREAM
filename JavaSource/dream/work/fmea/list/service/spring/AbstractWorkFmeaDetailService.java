package dream.work.fmea.list.service.spring;

import dream.work.fmea.list.service.WorkFmeaDetailService;

public abstract class AbstractWorkFmeaDetailService implements WorkFmeaDetailService {

	protected WorkFmeaDetailService workFmeaDetailService;
	
	public AbstractWorkFmeaDetailService(WorkFmeaDetailService workFmeaDetailService)
	{
		this.workFmeaDetailService = workFmeaDetailService;
	}

}
