package dream.req.work.service.spring;

import dream.req.work.service.MaWoReqResDetailService;

public abstract class AbstractMaWoReqResDetailService implements MaWoReqResDetailService {

	protected MaWoReqResDetailService maWoReqResDetailService;

    public AbstractMaWoReqResDetailService(MaWoReqResDetailService maWoReqResDetailService)
    {
        this.maWoReqResDetailService = maWoReqResDetailService;
    }

   
}
