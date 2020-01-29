package dream.org.emp.service.spring;

import dream.org.emp.service.LovEmpListService;

public abstract class AbstractLovEmpListService implements LovEmpListService {

	protected LovEmpListService lovEmpListService;
	
    public AbstractLovEmpListService(LovEmpListService lovEmpListService)
	{
		this.lovEmpListService = lovEmpListService;
	}
}
