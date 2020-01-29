package dream.consult.comp.intf.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.intf.dao.ConsultCompIntfMapListDAO;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;

/**
 * Interface Log Page - List DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultCompIntfMapListDAOTarget"
 * @spring.txbn id="consultCompIntfMapListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompIntfMapListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompIntfMapListDAO
{
	public List findList(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapListDTO consultCompIntfMapListDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
        
        query.append("SELECT                                                                                        		");
        query.append("      ''                                                                        AS seqNo      		");
        query.append("      ,''                                                                       AS isDelCheck 		");
        query.append("    , x.intfmap_id 															  AS intfmapId			");
        query.append("    , x.intf_id 																  AS intfId				");
        query.append("    , dbo.SFACODE_TO_DESC(x.intf_param_type, 'INTF_PARAM_TYPE', 'SYS',x.comp_no,'"+lang+"') paramTypeDesc		");
        query.append("    , x.param_seq 															  AS paramSeq			");
        query.append("    , x.s_table_name 															  AS stabName			");
        query.append("    , x.s_field_name 															  AS sfieldName			");
        query.append("    , dbo.SFACODE_TO_DESC(x.s_is_pk,'IS_USE','SYS', x.comp_no, '"+lang+"')      AS sisPkDesc			");
        query.append("    , x.s_field_type  														  AS sfieldTypeDesc		");
        query.append("    , x.s_field_size  														  AS sfieldSizeDesc		");
        query.append("    , dbo.SFACODE_TO_DESC(x.s_is_not_null,'IS_USE','SYS', x.comp_no, '"+lang+"')AS sisNotNullDesc		");
        query.append("    , x.s_remark  															  AS sremark			");
        query.append("    , x.t_table_name 															  AS ttabName			");
        query.append("    , x.t_field_name 															  AS tfieldName			");
        query.append("    , dbo.SFACODE_TO_DESC(x.t_it_pk,'IS_USE','SYS', x.comp_no, '"+lang+"')      AS tisPkDesc			");
        query.append("    , x.t_field_type  														  AS tfieldTypeDesc		");
        query.append("    , x.t_field_size  														  AS tfieldSizeDesc		");
        query.append("    , dbo.SFACODE_TO_DESC(x.t_it_not_null,'IS_USE','SYS', x.comp_no, '"+lang+"')AS tisNotNullDesc		");
        query.append("    , x.t_remark  															  AS tremark			");
        query.append("FROM TAINTFMAP x																						");
        query.append("WHERE  1=1                                                                           			        ");
        query.append(this.getWhere(consultCompIntfCommonDTO, consultCompIntfMapListDTO, user));
        query.getOrderByQuery("x.intfmap_id","x.param_seq", consultCompIntfMapListDTO.getOrderBy(), consultCompIntfMapListDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(consultCompIntfMapListDTO.getIsLoadMaxCount(), consultCompIntfMapListDTO.getFirstRow()));
    } 

	private String getWhere(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapListDTO consultCompIntfMapListDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.getAndQuery("x.comp_no", user.getCompNo());
		
        if(!"".equals(consultCompIntfMapListDTO.getIntfId())){
            query.getAndQuery("x.intfmap_id", consultCompIntfMapListDTO.getIntfMapId());
            return query.toString();
        }
        query.getLikeQuery("x.intf_id", consultCompIntfCommonDTO.getIntfId());

    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("DELETE FROM TAINTFMAP         ");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  intfmap_id     = ?     ");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapListDTO consultCompIntfMapListDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                        ");
        query.append("       COUNT(1)               ");
        query.append("FROM TAINTFMAP x              ");
        query.append("WHERE  1=1                    ");
    	query.append(this.getWhere(consultCompIntfCommonDTO, consultCompIntfMapListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}