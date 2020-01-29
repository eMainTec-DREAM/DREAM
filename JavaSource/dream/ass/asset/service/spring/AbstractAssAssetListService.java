package dream.ass.asset.service.spring;

import java.util.List;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.service.AssAssetListService;

public abstract class AbstractAssAssetListService implements AssAssetListService
{
    protected AssAssetListService assAssetListService;

    public AbstractAssAssetListService(AssAssetListService assAssetListService)
    {
        this.assAssetListService = assAssetListService;
    }
    
    public abstract List findList(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception;
    public abstract int deleteList(String[] deleteRows, User user) throws Exception;
    public abstract String findTotalCount(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception;
}
