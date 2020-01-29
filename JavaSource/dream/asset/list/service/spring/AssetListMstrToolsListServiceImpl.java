package dream.asset.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import dream.asset.list.dao.AssetListMstrToolsListDAO;
import dream.asset.list.dto.AssetListMstrToolsListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.AssetListMstrToolsListService;

/**
 * 구성자재 목록
 * @author kim21017
 * @version $Id: AssetListMstrToolsListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="assetListMstrToolsListServiceTarget"
 * @spring.txbn id="assetListMstrToolsListService"
 * @spring.property name="assetListMstrToolsListDAO" ref="assetListMstrToolsListDAO"
 */
public class AssetListMstrToolsListServiceImpl implements AssetListMstrToolsListService
{
    private AssetListMstrToolsListDAO assetListMstrToolsListDAO = null;


	public List findPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListMstrToolsListDTO assetListMstrToolsListDTO, User loginUser)
    {
        return assetListMstrToolsListDAO.findPartList(maEqMstrCommonDTO, assetListMstrToolsListDTO, loginUser);
    }

	public AssetListMstrToolsListDAO getAssetListMstrToolsListDAO() {
		return assetListMstrToolsListDAO;
	}

	public void setAssetListMstrToolsListDAO(AssetListMstrToolsListDAO assetListMstrToolsListDAO) {
		this.assetListMstrToolsListDAO = assetListMstrToolsListDAO;
	}

	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListMstrToolsListDTO assetListMstrToolsListDTO, User loginUser)  throws Exception
	{
		return assetListMstrToolsListDAO.findTotalCount(maEqMstrCommonDTO, assetListMstrToolsListDTO, loginUser);
	}
	
	public int[] deleteList(String[] deleteRows, User user) throws Exception
    {
        int[] result = null;
        AssetListMstrToolsListDTO assetListMstrToolsListDTO = new AssetListMstrToolsListDTO();
        List<AssetListMstrToolsListDTO> list = new ArrayList<AssetListMstrToolsListDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		assetListMstrToolsListDTO.setEquipId(deleteRows[i]);
        		
        		list.add((AssetListMstrToolsListDTO) BeanUtils.cloneBean(assetListMstrToolsListDTO));
        	}
        	result = assetListMstrToolsListDAO.deleteList(list, user);
        }
        
        return result;
    }
}

