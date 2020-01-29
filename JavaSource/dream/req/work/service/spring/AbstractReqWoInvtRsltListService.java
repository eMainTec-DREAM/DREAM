package dream.req.work.service.spring;

import dream.req.work.service.ReqWoInvtRsltListService;

public abstract class AbstractReqWoInvtRsltListService implements ReqWoInvtRsltListService {

	protected ReqWoInvtRsltListService reqWoInvtRsltListService;

    public AbstractReqWoInvtRsltListService(ReqWoInvtRsltListService reqWoInvtRsltListService)
    {
        this.reqWoInvtRsltListService = reqWoInvtRsltListService;
    }

   
}
