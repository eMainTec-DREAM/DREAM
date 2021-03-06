package dream.asset.std.product.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.std.product.dao.AssetStdProductListDAO;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;

/**
 * 생산품목 - 목록 dao
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="assetStdProductListDAOTarget"
 * @spring.txbn id="assetStdProductListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetStdProductListDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetStdProductListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdProductCommonDTO
     * @return List
     */
    public List findProductList(AssetStdProductCommonDTO assetStdProductCommonDTO, User user)
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT        							");
        query.append("    ''                    isDelCheck      ");
        query.append("    ,''                   seqNo       	");
        query.append("    ,x.product_id         productId       ");
        query.append("    ,x.product_no         productNo       ");
        query.append("    ,x.description		description		");
        query.append("    ,x.is_use             isUse    		");
        query.append("    ,x.remark             REMARK    		");
        query.append("FROM TAPRODUCT x        					");
    	query.append("WHERE  1=1                                ");
    	query.append(this.getWhere(assetStdProductCommonDTO, user));
    	query.getOrderByQuery("x.product_no", assetStdProductCommonDTO.getOrderBy(), assetStdProductCommonDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(assetStdProductCommonDTO.getIsLoadMaxCount(), assetStdProductCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdProductCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AssetStdProductCommonDTO assetStdProductCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if(!("".equals(assetStdProductCommonDTO.getProductId())||assetStdProductCommonDTO.getProductId()==null))
        {
            query.getAndQuery("x.product_id", assetStdProductCommonDTO.getProductId());
            return query.toString();
        }
        query.getLikeQuery("x.product_no", assetStdProductCommonDTO.getFilterProductNo());
        query.getLikeQuery("x.description", assetStdProductCommonDTO.getFilterDescription());
        
        // 사용여부 
        query.getSysCdQuery("x.is_use", assetStdProductCommonDTO.getFilterIsUse(), assetStdProductCommonDTO.getFilterIsUse(), "IS_USE", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param productId
     * @return
     */
    public int deleteProduct(String productId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TAPRODUCT          ");
        query.append("WHERE  comp_no  = ?    ");
        query.append("  AND  product_id  = ?    ");      
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,productId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(AssetStdProductCommonDTO assetStdProductCommonDTO,User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPRODUCT x                                      ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(assetStdProductCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}