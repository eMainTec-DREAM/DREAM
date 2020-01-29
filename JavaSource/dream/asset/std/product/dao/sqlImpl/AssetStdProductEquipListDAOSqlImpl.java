package dream.asset.std.product.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.std.product.dao.AssetStdProductEquipListDAO;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;

/**
 * 생산설비 - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="assetStdProductEquipListDAOTarget"
 * @spring.txbn id="assetStdProductEquipListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetStdProductEquipListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetStdProductEquipListDAO
{
	public List findList(AssetStdProductCommonDTO assetStdProductCommonDTO, AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT										");
        query.append("		''							seqNo		");
        query.append("	  , ''							isDelCheck	");
        query.append("    , x.prdequip_id   			PRDEQUIPID	");
        query.append("    , x.prod_seq   				prodSeq		");
        query.append("    , y.item_no   				equipNo		");
        query.append("    , y.description 				equipDesc	");
        query.append("    , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = y.comp_no AND a.eqloc_id =  y.eqloc_id) eqLocDesc		");
        query.append("    , (SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_prdequip_id) pprdEquipNo		");
        query.append("    , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_prdequip_id) pprdEquipDesc		");
        query.append("FROM TAPRDEQUIP x INNER JOIN TAEQUIPMENT y 	");
        query.append("ON x.comp_no = y.comp_no 						");
        query.append("AND x.equip_id = y.equip_id					");
        query.append("WHERE  1=1									");
        query.append(this.getWhere(assetStdProductCommonDTO, assetStdProductEquipListDTO, user));

    	query.getOrderByQuery("x.prdequip_id", "x.prod_seq", assetStdProductCommonDTO.getOrderBy(), assetStdProductCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(assetStdProductCommonDTO.getIsLoadMaxCount(), assetStdProductCommonDTO.getFirstRow()));
    } 
	
	private String getWhere(AssetStdProductCommonDTO assetStdProductCommonDTO, AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.product_id", assetStdProductCommonDTO.getProductId());
		
        if (!"".equals(assetStdProductEquipListDTO.getAssetStdProductEquipId()))
        {
            query.getAndQuery("x.prdequip_id", assetStdProductEquipListDTO.getAssetStdProductEquipId());
        }
        
		return query.toString();
	}
	
    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAPRDEQUIP			");
        query.append("WHERE  comp_no 			= ?		");
        query.append("  AND  prdequip_id  		= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
	public String findTotalCount(AssetStdProductCommonDTO assetStdProductCommonDTO, AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user)
			throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT										");
        query.append("		COUNT(1)								");
        query.append("FROM TAPRDEQUIP x INNER JOIN TAEQUIPMENT y 	");
        query.append("ON x.comp_no = y.comp_no 						");
        query.append("AND x.equip_id = y.equip_id					");
    	query.append("WHERE  1=1									");
    	query.append(this.getWhere(assetStdProductCommonDTO, assetStdProductEquipListDTO, user));
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}