package dream.part.adj.stktake.service.spring;

import dream.part.adj.stktake.service.PartAdjStkTakeDetailService;

public abstract class AbstractPartAdjStkTakeDetailService implements PartAdjStkTakeDetailService {

	protected PartAdjStkTakeDetailService partAdjStkTakeDetailService;

    public AbstractPartAdjStkTakeDetailService(PartAdjStkTakeDetailService partAdjStkTakeDetailService)
    {
        this.partAdjStkTakeDetailService = partAdjStkTakeDetailService;
    }

    public PartAdjStkTakeDetailService getPartAdjStkTakeDetailService()
    {
        return partAdjStkTakeDetailService;
    }

    public void setPartAdjStkTakeDetailService(PartAdjStkTakeDetailService partAdjStkTakeDetailService)
    {
        this.partAdjStkTakeDetailService = partAdjStkTakeDetailService;
    }
}
