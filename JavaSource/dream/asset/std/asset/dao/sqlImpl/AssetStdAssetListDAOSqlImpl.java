package dream.asset.std.asset.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.std.asset.dao.AssetStdAssetListDAO;
import dream.asset.std.asset.dto.AssetStdAssetCommonDTO;

/**
 * 회계자산 - 목록 dao
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="assetStdAssetListDAOTarget"
 * @spring.txbn id="assetStdAssetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetStdAssetListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetStdAssetListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetCommonDTO
     * @return List
     */
    public List findAssetList(AssetStdAssetCommonDTO assetStdAssetCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT        								");
        query.append("    ''                        isDelCheck      ");
        query.append("    ,''                       seqNo       	");
        query.append("    ,x.asset_id               assetId      	");
        query.append("    ,x.asset_no               assetNo       	");
        query.append("    ,x.description            description		");
        query.append("    ,x.acq_date               acqDate        	");
        query.append("    ,x.buyer_amt              buyerAmt        ");
        query.append("    ,x.dep_amt                depAmt     		");
        query.append("    ,x.res_amt                resAmt        	");
        query.append("    ,x.is_use                 isUse      		");
        query.append("    ,x.remark                 REMARK      	");
        query.append("FROM TAASSET x        						");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(assetStdAssetCommonDTO, user));
        query.getOrderByQuery("x.asset_id", "x.asset_no", assetStdAssetCommonDTO.getOrderBy(), assetStdAssetCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetStdAssetCommonDTO.getIsLoadMaxCount(), assetStdAssetCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AssetStdAssetCommonDTO assetStdAssetCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if(!("".equals(assetStdAssetCommonDTO.getAssetId())||assetStdAssetCommonDTO.getAssetId()==null))
        {
            query.getAndQuery("x.asset_id", assetStdAssetCommonDTO.getAssetId());
            return query.toString();
        }
        query.getLikeQuery("x.asset_no", assetStdAssetCommonDTO.getFilterAssetNo());
        query.getLikeQuery("x.description", assetStdAssetCommonDTO.getFilterDescription());
        
        // 사용여부 
        query.getSysCdQuery("x.is_use", assetStdAssetCommonDTO.getFilterIsUse(), assetStdAssetCommonDTO.getFilterIsUse(), "IS_USE", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deptId
     * @return
     */
    public int deleteAsset(String assetId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAASSET          ");
        query.append("WHERE  comp_no  = ?    ");
        query.append("  AND  asset_id  = ?    ");      
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,assetId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(AssetStdAssetCommonDTO assetStdAssetCommonDTO,User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAASSET x                  ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(assetStdAssetCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}