package dream.part.rec.service.spring;

import dream.part.rec.service.MaPtRecListService;

public abstract class AbstractMaPtRecListService implements MaPtRecListService {

	protected MaPtRecListService maPtRecListService;
	
	public AbstractMaPtRecListService(MaPtRecListService maPtRecListService)
	{
		this.maPtRecListService = maPtRecListService;
	}
	
}
