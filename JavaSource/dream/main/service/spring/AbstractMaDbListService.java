package dream.main.service.spring;

import dream.main.service.MaDbListService;

public abstract class AbstractMaDbListService implements MaDbListService {

	protected MaDbListService maDbListService;

    public AbstractMaDbListService(MaDbListService maDbListService)
    {
        this.maDbListService = maDbListService;
    }
}
