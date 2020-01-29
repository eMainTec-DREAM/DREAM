package dream.invt.list.service.spring;

import dream.invt.list.service.InvtLovService;

public abstract class AbstractInvtLovService implements InvtLovService {

	protected InvtLovService invtLovService;
	
    public AbstractInvtLovService(InvtLovService invtLovService)
	{
		this.invtLovService = invtLovService;
	}
}
