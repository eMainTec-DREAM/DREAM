package dream.part.iss.emg.list.service.spring;

import dream.part.iss.emg.list.service.LovEmgPartListService;

public abstract class AbstractLovEmgPartListService implements LovEmgPartListService {

	protected LovEmgPartListService lovEmgPartListService;
	
	public AbstractLovEmgPartListService(LovEmgPartListService lovEmgPartListService)
	{
		this.lovEmgPartListService = lovEmgPartListService;
	}
}
