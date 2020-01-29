package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.list.dto.AssetListPartOpQtyDTO;

/**
 * 설비운용기간사용예상수량
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface AssetListPartOpQtyDAO extends BaseJdbcDaoSupportIntf
{
    public List find(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
    
    public int insert(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
    
    public int update(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
    
    public int delete(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
    
    public String findTotalCount(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
    
}