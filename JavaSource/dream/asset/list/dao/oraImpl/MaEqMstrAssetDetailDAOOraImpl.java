package dream.asset.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrAssetDetailDAO;
import dream.asset.list.dto.MaEqMstrAssetDetailDTO;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 관련자산 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrAssetDetailDAOTarget"
 * @spring.txbn id="maEqMstrAssetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrAssetDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrAssetDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetListDTO
     * @param user
     * @return
     */
    public MaEqMstrAssetDetailDTO findDetail(MaEqMstrAssetListDTO maEqMstrAssetListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT												");
        query.append("       x.equip_id 				equipId				");
        query.append("       , x.eqasset_id 			eqAssetId			");
        query.append("       , y.asset_id 				assetId				");
        query.append("       , y.asset_no 				assetNo				");
        query.append("       , y.description 			assetDesc			");
        query.append("       , y.acq_date 				acqDate				");
        query.append("       , TO_CHAR(y.buyer_amt,'FM999,999,999,999') buyerAmt	");
        query.append("       , TO_CHAR(y.dep_amt,'FM999,999,999,999') 	depAmt		");
        query.append("       , TO_CHAR(y.res_amt,'FM999,999,999,999') 	resAmt		");
        query.append("FROM   TAEQASSET x, TAASSET y							");
        query.append("WHERE  x.comp_no  = y.comp_no							");
        query.append("  AND  x.asset_id = y.asset_id						");
        query.append("  AND	 x.eqasset_id 		= ?							");
        query.append("  AND  x.comp_no			= ?							");

    	Object[] objects = new Object[] {
    			maEqMstrAssetListDTO.getEqAssetId(),
    			user.getCompNo()
    	};
        MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO = 
        		(MaEqMstrAssetDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqMstrAssetDetailDTO()));
        
        return maEqMstrAssetDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEQASSET SET			");
    	query.append("	asset_id			= ?		");
    	query.append("WHERE eqasset_id		= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			maEqMstrAssetDetailDTO.getAssetId(),
    			maEqMstrAssetDetailDTO.getEqAssetId(),
    			user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetDetailDTO
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQASSET							");
    	query.append("	(  comp_no				, eqasset_id		");
    	query.append("	 , asset_id				, equip_id			");
    	query.append("	)	VALUES									");
    	query.append("	(  ?					, ?					");
    	query.append("	 , ?					, ?					");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maEqMstrAssetDetailDTO.getEqAssetId(),
    			maEqMstrAssetDetailDTO.getAssetId(),
    			maEqMstrCommonDTO.getEquipId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail copy
     * @author syyang
     * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param oldEquipId
     * @param newEquipId
     * @param oldKeyId
     * @param newKeyId
     * @param user
     * @return
     */
    public int copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAEQASSET							");
    	query.append("	(  comp_no				, eqasset_id		");
    	query.append("	 , asset_id				, equip_id			");
		query.append(") 	                                	  	");
		query.append("SELECT                                	    ");
		query.append("	 comp_no									");

		//eqasset_id
		if(!"".equals(newKeyId))
		{	// Unit 복사인 경우
			query.append("			, "+newKeyId+"				");
		}else
		{	// 전체 복사인 경우
			query.append("			, SQAeqasset_id.nextval		");
		}
		
		query.append("	 , asset_id			, ?						");
		query.append("FROM	TAEQASSET								");
        query.append("WHERE  comp_no 		= ?						");
        query.append("  AND  equip_id		= ?						");
    	
        // Unit 복사인 경우 Key 값을 넣어 하나만 복사한다.
        query.getAndQuery("eqasset_id", oldKeyId);
    	
    	Object[] objects = new Object[] {
    			  newEquipId
      		  	, user.getCompNo()
      		  	, oldEquipId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}
}