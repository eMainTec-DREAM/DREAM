package dream.ass.asset.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.ass.asset.dao.AssAssetScoreDetailDAO;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreDetailDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;

/**
 * 평가점수 - Detail DAO implements
 * @author youngjoo38
 * @version $Id: AssAssetScoreDetailDAOOraImpl.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assAssetScoreDetailDAOTarget"
 * @spring.txbn id="assAssetScoreDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssAssetScoreDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssAssetScoreDetailDAO
{
	
    public AssAssetScoreDetailDTO findDetail(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                                            ");
    	query.append("       x.eqasspval_id                   AS eqAssPValId            ");
        query.append("       , SFACODE_TO_DESC(x.ASS_POINT_TYPE, 'ASS_POINT_TYPE', 'SYS', x.comp_no, ?) AS assPointTypeDesc      ");
        query.append("       , x.ass_point_type                 AS assPointTypeId       ");
        query.append("       , x.assbasepoint_id                AS assBasePointId       ");
        query.append("       , x.ass_point                      AS assBasePointDesc     ");
        query.append("       , x.assbasepval_id                 AS assBasePValId        ");
        query.append("       , x.ass_eval                       AS assEvalDesc          ");
        query.append("       , x.eval_value                     AS eqAssPValDesc        ");
        query.append("       , x.REMARK                         AS remark               ");
        query.append("       , (SELECT a.assbaselist_id                                 ");
        query.append("          FROM TAASSBASEPVAL a                                    ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("              AND a.assbasepval_id = x.assbasepval_id) AS assBaseListId       ");
        query.append("FROM TAEQASSPVAL x                                                ");
        query.append("WHERE  1=1                                                        ");
        query.append("AND  x.eqasslist_id       = ?                                     ");
        query.append("AND  x.comp_no            = ?                                     ");
        query.append("AND  x.eqasspval_id        = ?                                    ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,assAssetCommonDTO.getEqasslistId()
                ,user.getCompNo()
                ,assAssetScoreListDTO.getEqAssPValId()
        };
    
        AssAssetScoreDetailDTO assAssetScoreDetailDTO = 
        		(AssAssetScoreDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssAssetScoreDetailDTO()));
        
        return assAssetScoreDetailDTO;
        
    }

    public int insertDetail(AssAssetCommonDTO assAssetCommonDTO,AssAssetScoreDetailDTO assAssetScoreDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAEQASSPVAL (        ");
    	query.append("  comp_no                        ");
    	query.append(", eqasspval_id                   ");
    	query.append(", eqasslist_id                   ");
    	query.append(", assbasepoint_id                ");
    	query.append(", ass_point_type                 ");
    	query.append(", ass_point                      ");
    	query.append(", assbasepval_id                 ");
    	query.append(", ass_eval                       ");
    	query.append(", eval_value                     ");
    	query.append(", REMARK                         ");
    	query.append(") VALUES (                       ");
    	query.append("?                                ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(")                                ");

    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			 , assAssetScoreDetailDTO.getEqAssPValId()
                 , assAssetCommonDTO.getEqasslistId()
                 , assAssetScoreDetailDTO.getAssBasePointId()
                 , assAssetScoreDetailDTO.getAssPointTypeId()
                 , assAssetScoreDetailDTO.getAssBasePointDesc()
                 , assAssetScoreDetailDTO.getAssBasePValId()
                 , assAssetScoreDetailDTO.getAssEvalDesc()
                 , assAssetScoreDetailDTO.getEqAssPValDesc()
                 , assAssetScoreDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(AssAssetCommonDTO assAssetCommonDTO,AssAssetScoreDetailDTO assAssetScoreDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAEQASSPVAL SET					");
    	query.append("	assbasepval_id			= ?				");
    	query.append("	,ass_eval				= ?				");
    	query.append("	,eval_value				= ?				");
    	query.append("	,remark					= ?				");
    	query.append("WHERE  comp_no			= ?				");
    	query.append("  AND  eqasslist_id		= ?				");
    	query.append("  AND  eqasspval_id      	= ?				");
    	
    	Object[] objects = new Object[] {
    	        assAssetScoreDetailDTO.getAssBasePValId()
    	        , assAssetScoreDetailDTO.getAssEvalDesc()
    	        , assAssetScoreDetailDTO.getEqAssPValDesc()
    	        , assAssetScoreDetailDTO.getRemark()
    			, user.getCompNo()
    			, assAssetCommonDTO.getEqasslistId()
    			, assAssetScoreDetailDTO.getEqAssPValId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}