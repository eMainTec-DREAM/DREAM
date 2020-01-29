package dream.asset.categ.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.asset.categ.list.dao.MaEqCtgPartDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;

/**
 * 설비종류별 부품 상세 dao
 * @author  kim21017
 * @version $Id: MaEqCtgPartDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqCtgPartDetailDAOTarget"
 * @spring.txbn id="maEqCtgPartDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCtgPartDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqCtgPartDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqCtgPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartListDTO
     * @param user
     * @return
     */
    public MaEqCtgPartDetailDTO findDetail(MaEqCtgPartListDTO maEqCtgPartListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT															");
        query.append("		x.eqctg_id									eqctgId			");
        query.append("	   , x.eq_ctg_part_id							eqCtgPartId		");
        query.append("	   , x.part_id									partId			");
        query.append("     , y.part_no                                  partNo          ");
        query.append("     , (SELECT description+', '+ISNULL(pt_size,'')                ");
        query.append("         FROM TAPARTS                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND part_id = x.part_id)                partNameSize    ");
        query.append("     , y.description                              partDesc        ");
        query.append("     , y.pt_size                                  ptSize          ");
        query.append("     , y.model                                    model           ");
        query.append("	   , x.eq_ctg_asmb_id							eqCtgAsmbId		");
        query.append("	   , (SELECT description										");
        query.append("		   FROM TAEQCTGASMB											");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eq_ctg_asmb_id = x.eq_ctg_asmb_id)	eqCtgAsmbDesc	");
        query.append("	   , x.use_qty									useQty			");
        query.append("     , x.cycle									cycle			");
        query.append("     , x.period_type								periodType     ");
        query.append("     , (SELECT dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"') )	periodTypeDesc	");
        query.append("     , x.is_use									isUse			");
        query.append("     , x.ord_no									ordNo			");
        query.append("     , x.is_eqtype_part                           isEqTypePart    ");
        query.append("     , x.usage                 					USAGE			");
        query.append("	   , x.schedule_type							SCHEDULETYPEID	");
        query.append("     , (SELECT dbo.SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"') )  scheduleTypeDesc		");
        query.append("FROM   TAEQCTGPART x LEFT OUTER JOIN TAPARTS y                    ");
        query.append("ON     x.comp_no = y.comp_no                                      ");
        query.append("AND    x.part_id = y.part_id                                      ");
        query.append("WHERE	 1=1														");
        query.append("  AND	 x.eq_ctg_part_id 	= ?										");
        query.append("  AND  x.comp_no			= ?										");

        Object[] objects = new Object[] {
        		  maEqCtgPartListDTO.getEqCtgPartId()
        		, user.getCompNo()
    	};
        
        MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = 
        		(MaEqCtgPartDetailDTO)getJdbcTemplate().query(query.toString(),getObject(objects), new MwareExtractor(new MaEqCtgPartDetailDTO()));
        
        return maEqCtgPartDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqCtgPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEQCTGPART SET		");
    	query.append("	  part_id				= ?	");
    	query.append("	, use_qty				= ?	");
    	query.append("	, eq_ctg_asmb_id		= ?	");
    	query.append("	, ord_no				= ?	");
    	query.append("	, is_eqtype_part		= ?	");
    	query.append("	, is_use				= ?	");
    	query.append("  , cycle            		= ? ");
    	query.append("  , period_type      		= ? ");
    	query.append("  , usage		      		= ? ");
    	query.append("  , schedule_type    		= ? ");
    	query.append("WHERE eq_ctg_part_id		= ?	");
    	query.append("  AND comp_no				= ?	");
    	
    	Object[] objects = new Object[] {
  			  maEqCtgPartDetailDTO.getPartId()
  			, maEqCtgPartDetailDTO.getUseQty()
  			, maEqCtgPartDetailDTO.getEqCtgAsmbId()
  			, maEqCtgPartDetailDTO.getOrdNo()
  			, maEqCtgPartDetailDTO.getIsEqTypePart()
  			, maEqCtgPartDetailDTO.getIsUse()
			, maEqCtgPartDetailDTO.getCycle()
			, maEqCtgPartDetailDTO.getPeriodType()
			, maEqCtgPartDetailDTO.getUsage()
			, maEqCtgPartDetailDTO.getScheduleTypeId()
  			, maEqCtgPartDetailDTO.getEqCtgPartId()
  			, user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqCtgPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartDetailDTO
     * @param maEqCatalogCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQCTGPART						");
    	query.append("	(  comp_no				, eq_ctg_part_id	");
    	query.append("	 , eqctg_id				, part_id			");
    	query.append("	 , eq_ctg_asmb_id		, use_qty			");
    	query.append("	 , is_use				, ord_no			");
    	query.append("   , cycle           		, period_type      	");
    	query.append("   , schedule_type        , usage		      	");
    	query.append("	 , is_eqtype_part             				");
    	query.append("	)	VALUES									");
    	query.append("	(  ?				    , ?					");
    	query.append("	 , ?				    , ?					");
    	query.append("	 , ?				    , ?					");
    	query.append("	 , ?				    , ?					");
    	query.append("	 , ?				    , ?					");
    	query.append("	 , ?				    , ?					");
    	query.append("	 , ?              	  						");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
  			  user.getCompNo()
  			, maEqCtgPartDetailDTO.getEqCtgPartId()
  			, maEqCatalogCommonDTO.getEqCtgId()
  			, maEqCtgPartDetailDTO.getPartId()
  			, maEqCtgPartDetailDTO.getEqCtgAsmbId()
  			, maEqCtgPartDetailDTO.getUseQty()
  			, maEqCtgPartDetailDTO.getIsUse()
  			, maEqCtgPartDetailDTO.getOrdNo()
			, maEqCtgPartDetailDTO.getCycle()
			, maEqCtgPartDetailDTO.getPeriodType()
			, maEqCtgPartDetailDTO.getScheduleTypeId()
			, maEqCtgPartDetailDTO.getUsage()
  			, maEqCtgPartDetailDTO.getIsEqTypePart()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail copy
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * @return
     */
    public String copyDetail(String oldEqCtgId, String newEqCtgId, String oldKeyId, String newKeyId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQCTGPART						");
    	query.append("	(  comp_no				, eq_ctg_part_id	");
    	query.append("	 , eqctg_id				, part_id			");
    	query.append("	 , eq_ctg_asmb_id		, use_qty			");
    	query.append("	 , is_use				, ord_no			");
    	query.append("   , cycle                , period_type		");
    	query.append("	 , is_eqtype_part             				");
    	query.append("	) SELECT									");
    	query.append("	   comp_no									");

    	if(!"".equals(newKeyId))
    	{	// Unit 복사인 경우
    		query.append("  , '"+newKeyId+"'					    ");
    	}
    	else
    	{	// 전체 복사인 경우
    		query.append("  , NEXT VALUE FOR sqaeqspec_id   		");
    	}
    	
    	query.append("	 , ?					, part_id 			");
    	query.append("	 , eq_ctg_asmb_id		, use_qty			");
    	query.append("	 , is_use				, ord_no			");
    	query.append("   , cycle                , period_type		");
    	query.append("	 , is_eqtype_part             				");
    	query.append(" FROM TAEQCTGPART								");
    	query.append(" WHERE 1=1									");
    	query.append("	 AND comp_no 	= ?							");
    	query.append("   AND eqctg_id 	= ?							");
    	
    	// Unit 복사인 경우 Key 값을 넣어 하나만 복사한다.
    	query.getAndQuery("eq_ctg_part_id", oldKeyId);
    	
    	Object[] objects = new Object[] {
    		      newEqCtgId
    			, user.getCompNo()
    			, oldEqCtgId
    	};
    	
    	getJdbcTemplate().update(query.toString(), getObject(objects));

    	return "0";
    }
}