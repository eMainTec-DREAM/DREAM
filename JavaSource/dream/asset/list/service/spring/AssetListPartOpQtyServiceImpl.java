package dream.asset.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.list.dao.AssetListPartOpQtyDAO;
import dream.asset.list.dto.AssetListPartOpQtyDTO;
import dream.asset.list.service.AssetListPartOpQtyService;

/**
 * 설비운용기간사용예상수량
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetListPartOpQtyServiceTarget"
 * @spring.txbn id="assetListPartOpQtyService"
 * @spring.property name="assetListPartOpQtyDAO" ref="assetListPartOpQtyDAO"
 */
public class AssetListPartOpQtyServiceImpl implements AssetListPartOpQtyService
{
	private AssetListPartOpQtyDAO assetListPartOpQtyDAO = null;
	
	public AssetListPartOpQtyDAO getAssetListPartOpQtyDAO()
    {
        return assetListPartOpQtyDAO;
    }

    public void setAssetListPartOpQtyDAO(AssetListPartOpQtyDAO assetListPartOpQtyDAO)
    {
        this.assetListPartOpQtyDAO = assetListPartOpQtyDAO;
    }

    @Override
    public List findList(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        return assetListPartOpQtyDAO.find(assetListPartOpQtyDTO, user);
    }
    
    @Override
    public AssetListPartOpQtyDTO findDetail(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        Map map = (Map) CommonUtil.makeJson(assetListPartOpQtyDAO.find(assetListPartOpQtyDTO, user)).get(0);
        return (AssetListPartOpQtyDTO) CommonUtil.makeDTO(map, AssetListPartOpQtyDTO.class);
    }

    @Override
    public int updateDetail(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        return assetListPartOpQtyDAO.update(assetListPartOpQtyDTO, user);
    }

    @Override
    public int inputDetail(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        return assetListPartOpQtyDAO.insert(assetListPartOpQtyDTO, user);
    }
    
    @Override
    public int deleteList(String[] deleteRows, User user) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            AssetListPartOpQtyDTO assetListPartOpQtyDTO = null;
            for(String id : deleteRows)
            {
                assetListPartOpQtyDTO = new AssetListPartOpQtyDTO();
                assetListPartOpQtyDTO.setEqPartOpQtyId(id);
                result = result + assetListPartOpQtyDAO.delete(assetListPartOpQtyDTO, user);
            }
        }
        
        return result;
    }

    @Override
    public String findTotalCount(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception
    {
        return assetListPartOpQtyDAO.findTotalCount(assetListPartOpQtyDTO, user);
    }

}

