package dream.ass.asset.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.ass.asset.dao.AssAssetScoreListDAO;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;

/**
 * AssAssetScore Page - List DAO implements
 * @author youngjoo38
 * @version $Id: AssAssetScoreListDAOSqlImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assAssetScoreListDAOTarget"
 * @spring.txbn id="assAssetScoreListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssAssetScoreListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements AssAssetScoreListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: assAssetScoreListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param assAssetScoreListDTO
     * @return List
     */
    public List findList(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       ''                                 AS seqNo                ");
        query.append("       ,''                                AS isDelCheck           ");
        query.append("       , x.eqasspval_id                   AS eqAssPValId          ");
        query.append("       , dbo.SFACODE_TO_DESC(x.ASS_POINT_TYPE, 'ASS_POINT_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') AS assPointTypeDesc      ");
        query.append("       , x.ass_point_type                 AS assPointTypeId       ");
        query.append("       , x.assbasepoint_id                AS assBasePointId       ");
        query.append("       , x.ass_point                      AS assBasePointDesc     ");
        query.append("       , x.assbasepval_id                 AS assEvalId            ");
        query.append("       , x.ass_eval                       AS assEvalDesc          ");
        query.append("       , x.eval_value                     AS eqAssPValDesc        ");
        query.append("       , x.REMARK                         AS remark               ");
        query.append("       , x.eqasslist_id                   AS EQASSLISTID          ");
        query.append("       , (SELECT a.assbaselist_id                                 ");
        query.append("          FROM TAASSBASEPVAL a                                    ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("              AND a.assbasepval_id = x.assbasepval_id) AS assBaseListId       ");
        query.append("FROM TAEQASSPVAL x                                                ");
        query.append("WHERE  1=1                                                        ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.eqasslist_id", assAssetCommonDTO.getEqasslistId());
        
        if (!"".equals(assAssetScoreListDTO.getEqAssPValId()))
        {
            query.getAndQuery("x.eqasspval_id", assAssetScoreListDTO.getEqAssPValId());
        }
        query.getOrderByQuery("x.eqasspval_id", "x.eqasspval_id", assAssetCommonDTO.getOrderBy(), assAssetCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assAssetCommonDTO.getIsLoadMaxCount(), assAssetCommonDTO.getFirstRow()));

    }

    private String getWhere(AssAssetScoreListDTO assAssetScoreListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        return query.toString();
    }
    
    /**
     * Filter Á¶°Ç
     * @author  youngjoo38
     * @version $Id: assAssetScoreListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param assAssetScoreListDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAEQASSPVAL   ");
        query.append("WHERE eqasspval_id = ?    ");
        query.append("  AND comp_no = ?         ");
        
        Object[] objects = new Object[] {   
                 id
                ,user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(AssAssetCommonDTO assAssetCommonDTO, 
            AssAssetScoreListDTO assAssetScoreListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAEQASSPVAL x                                      ");
        query.append("WHERE  1=1                                                ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.eqasslist_id", assAssetCommonDTO.getEqasslistId());
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

	@Override
	public int updateList(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAEQASSPVAL										");
        query.append("SET ASSBASEPVAL_ID = a.ASSBASEPVAL_ID						");
        query.append("    ,ASS_EVAL = a.ASS_EVAL								");
        query.append("    ,EVAL_VALUE = a.EVAL_VALUE							");
        query.append("FROM (SELECT * FROM TAEQASSPVAL 							");
        query.append("            WHERE comp_no = ?	 							");
        query.append("            AND eqasslist_id = ?) AS a					");
        query.append("WHERE TAEQASSPVAL.COMP_NO = a.COMP_NO						");
        query.append("AND TAEQASSPVAL.ASSBASEPOINT_ID = a.ASSBASEPOINT_ID		");
        query.append("AND TAEQASSPVAL.COMP_NO = ?								");
        query.append("AND TAEQASSPVAL.eqasslist_id = ?							");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,assAssetScoreListDTO.getEqasslistIdLov()
        		,user.getCompNo()
        		,assAssetCommonDTO.getEqasslistId()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }    
}
