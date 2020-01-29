package dream.work.list.service.spring;

import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.list.service.MaWoResultMstrDetailService;

public abstract class AbstractMaWoResultMstrDetailService implements MaWoResultMstrDetailService {

	protected MaWoResultMstrDetailService maWoResultMstrDetailService;
	
	public AbstractMaWoResultMstrDetailService(MaWoResultMstrDetailService maWoResultMstrDetailService)
	{
		this.maWoResultMstrDetailService = maWoResultMstrDetailService;
	}
}
