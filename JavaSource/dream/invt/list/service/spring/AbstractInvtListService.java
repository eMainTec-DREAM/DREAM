package dream.invt.list.service.spring;

import dream.invt.list.service.InvtListService;

public abstract class AbstractInvtListService implements InvtListService {

	protected InvtListService invtListService;
	
    public AbstractInvtListService(InvtListService invtListService)
	{
		this.invtListService = invtListService;
	}
    
}
