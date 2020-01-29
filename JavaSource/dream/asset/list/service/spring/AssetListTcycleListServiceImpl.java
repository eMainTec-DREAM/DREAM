package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.ass.asset.service.AssAssetDetailService;
import dream.asset.list.dao.AssetListTcycleListDAO;
import dream.asset.list.dto.AssetListTcycleListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.AssetListTcycleListService;

/**
 * 교정주기 목록
 * @author kim21017
 * @version $Id: AssetListTcycleListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assetListTcycleListServiceTarget"
 * @spring.txbn id="assetListTcycleListService"
 * @spring.property name="assetListTcycleListDAO" ref="assetListTcycleListDAO"
 */
public class AssetListTcycleListServiceImpl implements AssetListTcycleListService
{
    private AssetListTcycleListDAO assetListTcycleListDAO = null;

	public AssetListTcycleListDAO getAssetListTcycleListDAO() {
		return assetListTcycleListDAO;
	}

	public void setAssetListTcycleListDAO(AssetListTcycleListDAO assetListTcycleListDAO) {
		this.assetListTcycleListDAO = assetListTcycleListDAO;
	}
	
	public List findTcycleList(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListTcycleListDTO assetListTcycleListDTO, User loginUser)
    {      
	    AssAssetDetailService assAssetDetailService = (AssAssetDetailService)CommonUtil.getBean("assAssetDetailService",loginUser);
	    
	    maEqMstrCommonDTO.setEquipId(assAssetDetailService.getLastVersionEquipId(maEqMstrCommonDTO.getEquipId(), loginUser.getCompNo()));
        return assetListTcycleListDAO.findTcycleList(maEqMstrCommonDTO, assetListTcycleListDTO, loginUser);
    }
	
	public int deleteTcycleList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assetListTcycleListDAO.deleteTcycleList(id, compNo);
            }
        
        return result;
    }
	
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListTcycleListDTO assetListTcycleListDTO,User user)  throws Exception
    {
        return assetListTcycleListDAO.findTotalCount(maEqMstrCommonDTO, assetListTcycleListDTO, user);
    }
}

