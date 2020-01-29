package dream.asset.std.asset.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.asset.std.asset.dao.AssetStdAssetDetailDAO;
import dream.asset.std.asset.dto.AssetStdAssetDetailDTO;

/**
 * 회계자산 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="assetStdAssetDetailDAOTarget"
 * @spring.txbn id="assetStdAssetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetStdAssetDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetStdAssetDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetNo
     * @return
     */
    public AssetStdAssetDetailDTO findDetail(User user, String assetId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        ");
        query.append("    x.asset_id                  assetId       ");
        query.append("    ,x.asset_no                 assetNo       ");
        query.append("    ,x.description              description       ");
        query.append("    ,x.acq_date                acqDate        ");
        query.append("    ,x.buyer_amt              buyerAmt        ");
        query.append("    ,x.dep_amt                 depAmt     ");
        query.append("    ,x.res_amt                  resAmt        ");
        query.append("    ,x.is_use                    isUse      ");
        query.append("    ,x.remark                   REMARK      ");
        query.append("FROM TAASSET x        ");
        query.append("WHERE  1=1            ");
        query.append("  and x.comp_no = ?           ");
        query.append("  and x.asset_id = ?           ");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
    			,assetId
    	};
    
        AssetStdAssetDetailDTO assetStdAssetDetailDTO = 
                (AssetStdAssetDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssetStdAssetDetailDTO()));

        
        return assetStdAssetDetailDTO;
    }
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetDetailDTO
     * @return
     */
    public int insertDetail(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAASSET (        ");
        query.append("    comp_no,       asset_id,       asset_no,      ");
        query.append("    description,    acq_date,      buyer_amt,     ");
        query.append("    dep_amt,       res_amt,       is_use,            ");
        query.append("    REMARK            ");
        query.append("    )VALUES(      ");
        query.append("    ?,                ?,                  ?,        ");
        query.append("    ?,                ?,                  ?,        ");
        query.append("    ?,                ?,                  ?,        ");
        query.append("    ?                                                ");
        query.append("    )                                                 ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,assetStdAssetDetailDTO.getAssetId()
                ,assetStdAssetDetailDTO.getAssetNo()
                ,assetStdAssetDetailDTO.getDescription()
                ,assetStdAssetDetailDTO.getAcqDate()
                ,assetStdAssetDetailDTO.getBuyerAmt()
                ,assetStdAssetDetailDTO.getDepAmt()
                ,assetStdAssetDetailDTO.getResAmt()
                ,assetStdAssetDetailDTO.getIsUse()
                ,assetStdAssetDetailDTO.getRemark()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetDetailDTO
     * @return
     */
    public int updateDetail(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAASSET SET       ");
        query.append("    asset_no        = ?       ");
        query.append("    ,description    = ?       ");
        query.append("    ,acq_date       = ?       ");
        query.append("    ,buyer_amt    = ?     ");
        query.append("    ,dep_amt       = ?        ");
        query.append("    ,res_amt        = ?       ");
        query.append("    ,is_use          = ?      ");
        query.append("    ,REMARK       = ?     ");
        query.append("WHERE 1=1     ");
        query.append("AND  comp_no        = ?             ");
        query.append("AND  asset_id        = ?              ");
        
        Object[] objects = new Object[] {
                assetStdAssetDetailDTO.getAssetNo()
                ,assetStdAssetDetailDTO.getDescription()
                ,assetStdAssetDetailDTO.getAcqDate()
                ,assetStdAssetDetailDTO.getBuyerAmt()
                ,assetStdAssetDetailDTO.getDepAmt()
                ,assetStdAssetDetailDTO.getResAmt()
                ,assetStdAssetDetailDTO.getIsUse()
                ,assetStdAssetDetailDTO.getRemark()
                ,user.getCompNo()
                ,assetStdAssetDetailDTO.getAssetId()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * valid assetNo
     * @author ghlee
     * @version $Id:  $
     * @since   1.0
     * 
     * @param assetStdAssetDetailDTO
     * @return
     */
    public String validAssetNo(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TAASSET x                        ");
        query.append("WHERE 1=1 ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.asset_no", assetStdAssetDetailDTO.getAssetNo());
        query.append("AND x.asset_id != "+assetStdAssetDetailDTO.getAssetId());
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}