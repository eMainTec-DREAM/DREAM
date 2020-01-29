package dream.invt.list.service.spring;

import dream.invt.list.service.InvtPhaseListService;

public abstract class AbstractInvtPhaseListService implements InvtPhaseListService {

	protected InvtPhaseListService invtPhaseListService;
	
    public AbstractInvtPhaseListService(InvtPhaseListService invtPhaseListService)
	{
		this.invtPhaseListService = invtPhaseListService;
	}
    
}
