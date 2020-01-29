package dream.asset.std.ctctr.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.std.ctctr.dao.AssetStdCtctrListDAO;
import dream.asset.std.ctctr.dto.AssetStdCtctrCommonDTO;
import dream.asset.std.ctctr.service.AssetStdCtctrListService;

/**
 * CostCenter - ¸ñ·Ï serviceimpl
 * @author ghlee
 * @version
 * @since 1.0
 * 
 * @spring.bean id="assetStdCtctrListServiceTarget"
 * @spring.txbn id="assetStdCtctrListService"
 * @spring.property name="assetStdCtctrListDAO" ref="assetStdCtctrListDAO"
 */
public class AssetStdCtctrListServiceImpl implements AssetStdCtctrListService
{
    private AssetStdCtctrListDAO assetStdCtctrListDAO = null;

    public AssetStdCtctrListDAO getAssetStdCtctrListDAO() 
    {
		return assetStdCtctrListDAO;
	}

	public void setAssetStdCtctrListDAO(AssetStdCtctrListDAO assetStdCtctrListDAO) 
	{
		this.assetStdCtctrListDAO = assetStdCtctrListDAO;
	}

	public List findCtctrList(AssetStdCtctrCommonDTO assetStdCtctrCommonDTO,User user) throws Exception
    {     
        return assetStdCtctrListDAO.findCtctrList(assetStdCtctrCommonDTO,user);
    }

	public int deleteList(String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assetStdCtctrListDAO.deleteCtctr(id, user);
            }
        
        return result;
    }
	
    @Override
    public String findTotalCount(AssetStdCtctrCommonDTO assetStdCtctrCommonDTO,User user) throws Exception
    {
        return assetStdCtctrListDAO.findTotalCount(assetStdCtctrCommonDTO, user);
    }
}

