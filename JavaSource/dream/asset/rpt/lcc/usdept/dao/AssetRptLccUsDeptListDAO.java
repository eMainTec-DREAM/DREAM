package dream.asset.rpt.lcc.usdept.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;

/**
 * 고장TOP(사용부서) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccUsDeptListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccUsDeptCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser);
    public List findPlantList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser);
    public List findTeamList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser);
    public List findPartList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser);
    public List findEquipList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser);

    public String findTotalCount(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User user);
    
}