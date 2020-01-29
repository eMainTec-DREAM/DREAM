package dream.asset.rpt.bdintensity.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.bdintensity.dao.AssetRptBdIntensityListDAO;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityCommonDTO;
import dream.asset.rpt.bdintensity.service.AssetRptBdIntensityListService;

/**
 * 설비별 고장강도율 목록 - List Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="assetRptBdIntensityListServiceTarget"
 * @spring.txbn id="assetRptBdIntensityListService"
 * @spring.property name="assetRptBdIntensityListDAO" ref="assetRptBdIntensityListDAO"
 */
public class AssetRptBdIntensityListServiceImpl implements AssetRptBdIntensityListService
{
    private AssetRptBdIntensityListDAO assetRptBdIntensityListDAO = null;

    public List findList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception
    {      
    	List result = null;
    	
    	switch(assetRptBdIntensityCommonDTO.getFilterSeparation()){
    	case "L1":
    		result = assetRptBdIntensityListDAO.findPlantList(assetRptBdIntensityCommonDTO,user); 
    		break;
    	case "L2":
    		result = assetRptBdIntensityListDAO.findEqLocList(assetRptBdIntensityCommonDTO,user); 
    		break;
    	case "L3":
    		result = assetRptBdIntensityListDAO.findUsageDeptList(assetRptBdIntensityCommonDTO,user); 
    		break;
    	case "L4":
    		result = assetRptBdIntensityListDAO.findEqCtgList(assetRptBdIntensityCommonDTO,user); 
    		break;
    	case "L5":
    		result = assetRptBdIntensityListDAO.findEqpList(assetRptBdIntensityCommonDTO,user); 
    		break;
    	default:
    		result = assetRptBdIntensityListDAO.findPlantList(assetRptBdIntensityCommonDTO,user); 
    		break;
    	}    	
    	
        return result;
        
    }

    public AssetRptBdIntensityListDAO getAssetRptBdIntensityListDAO() {
        return assetRptBdIntensityListDAO;
    }

    public void setAssetRptBdIntensityListDAO(AssetRptBdIntensityListDAO assetRptBdIntensityListDAO) {
        this.assetRptBdIntensityListDAO = assetRptBdIntensityListDAO;
    }    
    
    public String findTotalCount(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO,User user)  throws Exception
    {
        return assetRptBdIntensityListDAO.findTotalCount(assetRptBdIntensityCommonDTO, user);
    }
}
