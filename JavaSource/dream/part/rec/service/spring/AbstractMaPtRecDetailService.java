package dream.part.rec.service.spring;

import dream.part.rec.service.MaPtRecDetailService;

public abstract class AbstractMaPtRecDetailService implements MaPtRecDetailService {

	protected MaPtRecDetailService maPtRecDetailService;
	
	public AbstractMaPtRecDetailService(MaPtRecDetailService maPtRecDetailService)
	{
		this.maPtRecDetailService = maPtRecDetailService;
	}
	
}
