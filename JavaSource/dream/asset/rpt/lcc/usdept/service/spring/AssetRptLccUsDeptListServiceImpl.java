package dream.asset.rpt.lcc.usdept.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.usdept.dao.AssetRptLccUsDeptListDAO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;
import dream.asset.rpt.lcc.usdept.service.AssetRptLccUsDeptListService;

/**
 * 고장TOP(사용부서) 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptLccUsDeptListServiceTarget"
 * @spring.txbn id="assetRptLccUsDeptListService"
 * @spring.property name="assetRptLccUsDeptListDAO" ref="assetRptLccUsDeptListDAO"
 */
public class AssetRptLccUsDeptListServiceImpl implements AssetRptLccUsDeptListService
{
    private AssetRptLccUsDeptListDAO assetRptLccUsDeptListDAO = null;
    
	public AssetRptLccUsDeptListDAO getAssetRptLccUsDeptListDAO()
    {
        return assetRptLccUsDeptListDAO;
    }
	
    public void setAssetRptLccUsDeptListDAO(
            AssetRptLccUsDeptListDAO assetRptLccUsDeptListDAO)
    {
        this.assetRptLccUsDeptListDAO = assetRptLccUsDeptListDAO;
    }
    
    public List findList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser)
    {
    	List result = null;
    	
    	switch(assetRptLccUsDeptCommonDTO.getFilterDwSeparation())
    	{
    		case "L1" :
    			result = assetRptLccUsDeptListDAO.findPlantList(assetRptLccUsDeptCommonDTO, loginUser);
    			break;
    		case "L2" :
    			result = assetRptLccUsDeptListDAO.findTeamList(assetRptLccUsDeptCommonDTO, loginUser);
    			break;
    		case "L3" :
    			result = assetRptLccUsDeptListDAO.findPartList(assetRptLccUsDeptCommonDTO, loginUser);
    			break;
    		case "L4" :
    			result = assetRptLccUsDeptListDAO.findEquipList(assetRptLccUsDeptCommonDTO, loginUser);
    			break;
    		default :
    			result = assetRptLccUsDeptListDAO.findList(assetRptLccUsDeptCommonDTO, loginUser);
    			break;
    	}
    	
    	return result;
    }

    @Override
    public String findTotalCount(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User user)
    {
        return assetRptLccUsDeptListDAO.findTotalCount(assetRptLccUsDeptCommonDTO, user);
    }
	
}

