package dream.invt.list.service.spring;

import dream.invt.list.service.InvtDetailService;

public abstract class AbstractInvtDetailService implements InvtDetailService {

	protected InvtDetailService invtDetailService;

    public AbstractInvtDetailService(InvtDetailService invtDetailService)
    {
        this.invtDetailService = invtDetailService;
    }
    
    public InvtDetailService getInvtDetailService()
    {
        return invtDetailService;
    }

    public void setInvtDetailService(InvtDetailService invtDetailService)
    {
        this.invtDetailService = invtDetailService;
    }
}
