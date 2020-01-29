package dream.comm.service.spring;

import java.util.Map;

import common.bean.User;
import dream.comm.service.MaFinderAcService;

public abstract class AbstractMaFinderAcService implements MaFinderAcService {

	protected MaFinderAcService maFinderAcService;
	
    public AbstractMaFinderAcService(MaFinderAcService maFinderAcService)
	{
		this.maFinderAcService = maFinderAcService;
	}

	public abstract Map findAutoCompleteDesc(String keySearchCol, String keySearchVal,
            Map<String, String> columnMap, String tableName,
            Map<String, String> conditionParam, Map<String, String> labelMap, String lang, boolean limited, User user);
}
