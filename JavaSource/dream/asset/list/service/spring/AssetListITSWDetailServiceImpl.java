package dream.asset.list.service.spring;

import common.bean.User;
import dream.asset.list.dao.AssetListITSWDetailDAO;
import dream.asset.list.dto.AssetListITSWDetailDTO;
import dream.asset.list.dto.AssetListITSWListDTO;
import dream.asset.list.service.AssetListITSWDetailService;

/**
 * Detail Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="assetListITSWDetailServiceTarget"
 * @spring.txbn id="assetListITSWDetailService"
 * @spring.property name="assetListITSWDetailDAO" ref="assetListITSWDetailDAO"
 */
public class AssetListITSWDetailServiceImpl implements AssetListITSWDetailService
{
    private AssetListITSWDetailDAO assetListITSWDetailDAO = null;
    
    public AssetListITSWDetailDTO findDetail(AssetListITSWListDTO assetListITSWListDTO, User user) throws Exception
    {
    	return assetListITSWDetailDAO.findDetail(assetListITSWListDTO, user);
    }
    
    public int insertDetail(AssetListITSWDetailDTO assetListITSWDetailDTO, User user) throws Exception
    {
     	return assetListITSWDetailDAO.insertDetail(assetListITSWDetailDTO, user);
    }
    
    public int updateDetail(AssetListITSWDetailDTO assetListITSWDetailDTO, User user) throws Exception
    {
     	return assetListITSWDetailDAO.updateDetail(assetListITSWDetailDTO, user);
    }

	public AssetListITSWDetailDAO getAssetListITSWDetailDAO() {
		return assetListITSWDetailDAO;
	}

	public void setAssetListITSWDetailDAO(AssetListITSWDetailDAO assetListITSWDetailDAO) {
		this.assetListITSWDetailDAO = assetListITSWDetailDAO;
	}
    

}
