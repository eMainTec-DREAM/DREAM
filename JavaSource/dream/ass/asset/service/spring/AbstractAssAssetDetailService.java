package dream.ass.asset.service.spring;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetDetailDTO;
import dream.ass.asset.service.AssAssetDetailService;

public abstract class AbstractAssAssetDetailService implements AssAssetDetailService {

	protected AssAssetDetailService assAssetDetailService;
	
	public AbstractAssAssetDetailService(AssAssetDetailService assAssetDetailService)
	{
		this.assAssetDetailService = assAssetDetailService;
	}
	
	public abstract String updateScore(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
	
	public abstract String findGrade(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
	
	public abstract int asscompletedDetail(AssAssetCommonDTO assAssetCommonDTO, AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
}
