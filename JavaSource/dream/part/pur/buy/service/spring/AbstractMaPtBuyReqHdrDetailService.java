package dream.part.pur.buy.service.spring;

import dream.part.pur.buy.service.MaPtBuyReqHdrDetailService;

public abstract class AbstractMaPtBuyReqHdrDetailService implements MaPtBuyReqHdrDetailService {

	protected MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService;

    public AbstractMaPtBuyReqHdrDetailService(MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService)
    {
        this.maPtBuyReqHdrDetailService = maPtBuyReqHdrDetailService;
    }

    public MaPtBuyReqHdrDetailService getMaPtBuyReqHdrDetailService()
    {
        return maPtBuyReqHdrDetailService;
    }

    public void setMaPtBuyReqHdrDetailService(MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService)
    {
        this.maPtBuyReqHdrDetailService = maPtBuyReqHdrDetailService;
    }
}
