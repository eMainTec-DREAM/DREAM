package dream.scheduler.autoschedule.service.spring;

import dream.scheduler.autoschedule.service.MaBatchService;

public abstract class AbstractMaBatchService implements MaBatchService {

	protected MaBatchService maBatchService;
	
	public AbstractMaBatchService(MaBatchService maBatchService)
	{
		this.maBatchService = maBatchService;
	}

}
