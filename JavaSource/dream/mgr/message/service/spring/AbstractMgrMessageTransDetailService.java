package dream.mgr.message.service.spring;

import dream.mgr.message.service.MgrMessageTransDetailService;

public abstract class AbstractMgrMessageTransDetailService implements MgrMessageTransDetailService
{
    protected MgrMessageTransDetailService mgrMessageTransDetailService;
    
    public AbstractMgrMessageTransDetailService(MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

}