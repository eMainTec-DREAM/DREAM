package dream.asset.std.product.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.std.product.dao.AssetStdProductEquipDetailDAO;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipDetailDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;

/**
 * 생산설비 - Detail DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="assetStdProductEquipDetailDAOTarget"
 * @spring.txbn id="assetStdProductEquipDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetStdProductEquipDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetStdProductEquipDetailDAO
{
	
    public AssetStdProductEquipDetailDTO findDetail(AssetStdProductCommonDTO assetStdProductCommonDTO, AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT										");
    	query.append("  x.prdequip_id   			assetStdProductEquipId 		");
    	query.append(", x.product_id                productId       ");
    	query.append(", x.prod_seq   				prodSeq			");
    	query.append(", y.equip_id                  equipId 		");
    	query.append(", y.item_no   				equipNo			");
    	query.append(", y.description 				equipDesc		");
    	query.append(", (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = y.comp_no AND a.eqloc_id =  y.eqloc_id) eqLocDesc		");
        query.append(", x.p_prdequip_id				pPrdEquipId		");
    	query.append(", (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_prdequip_id) pPrdEquipDesc		");
    	query.append(", x.remark 					remark			");
    	query.append("FROM TAPRDEQUIP x INNER JOIN TAEQUIPMENT y 	");
    	query.append("ON x.comp_no = y.comp_no 						");
    	query.append("AND x.equip_id = y.equip_id					");
    	query.append("WHERE 1=1										");
    	query.append("AND  x.product_id		= ?						");
    	query.append("AND  x.prdequip_id	= ?						");
    	query.append("AND  x.comp_no    	= ?						");
        
        Object[] objects = new Object[] {
        		  assetStdProductCommonDTO.getProductId()
        		, assetStdProductEquipListDTO.getAssetStdProductEquipId()
    			, user.getCompNo()
    	};
    
        AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO = 
        		(AssetStdProductEquipDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssetStdProductEquipDetailDTO()));
        
        return assetStdProductEquipDetailDTO;
        
    }

    public int insertDetail(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAPRDEQUIP (		");
    	query.append("	  comp_no					");
    	query.append("	, prdequip_id				");
    	query.append("	, product_id				");
    	query.append("	, prod_seq					");
    	query.append("	, equip_id					");
    	query.append("	, p_prdequip_id				");
    	query.append("	, remark					");
    	query.append(")VALUES(						");
    	query.append("	  ?							");
    	query.append("	, ?							");
    	query.append("	, ?							");
    	query.append("	, ?							");
    	query.append("	, ?							");
    	query.append("	, ?							");
    	query.append("	, ?							");
    	query.append(")								");
    	
    	Object[] objects = new Object[] {
    			  user.getCompNo()
     			, assetStdProductEquipDetailDTO.getAssetStdProductEquipId()
    			, assetStdProductCommonDTO.getProductId()
    			, assetStdProductEquipDetailDTO.getProdSeq()
    			, assetStdProductEquipDetailDTO.getEquipId()
    			, assetStdProductEquipDetailDTO.getPprdEquipId()
    			, assetStdProductEquipDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAPRDEQUIP SET					");
    	query.append("	  prod_seq			= ?				");
    	query.append("	, equip_id			= ?				");
    	query.append("	, p_prdequip_id		= ?				");
    	query.append("	, remark			= ?				");
    	query.append("WHERE  comp_no		= ?				");
    	query.append("  AND  prdequip_id	= ?				");
    	query.append("  AND  product_id		= ?				");
    	
    	Object[] objects = new Object[] {
    			  assetStdProductEquipDetailDTO.getProdSeq()
    			, assetStdProductEquipDetailDTO.getEquipId()
    			, assetStdProductEquipDetailDTO.getPprdEquipId()
    			, assetStdProductEquipDetailDTO.getRemark()
    			, user.getCompNo()
    			, assetStdProductEquipDetailDTO.getAssetStdProductEquipId()
    			, assetStdProductCommonDTO.getProductId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}