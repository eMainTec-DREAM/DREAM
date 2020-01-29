package common.mafinder.mamstr.service.spring;

import common.mafinder.mamstr.service.LovInvtListService;

public abstract class AbstractLovInvtListService implements LovInvtListService {

	protected LovInvtListService lovInvtListService;
	
    public AbstractLovInvtListService(LovInvtListService lovInvtListService)
	{
		this.lovInvtListService = lovInvtListService;
	}
}
