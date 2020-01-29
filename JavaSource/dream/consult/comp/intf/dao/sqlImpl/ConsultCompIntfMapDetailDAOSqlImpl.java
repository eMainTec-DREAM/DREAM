package dream.consult.comp.intf.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.intf.dao.ConsultCompIntfMapDetailDAO;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapDetailDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;

/**
 * Interface Log Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultCompIntfMapDetailDAOTarget"
 * @spring.txbn id="consultCompIntfMapDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompIntfMapDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompIntfMapDetailDAO
{
	
    public ConsultCompIntfMapDetailDTO findDetail(ConsultCompIntfMapListDTO consultCompIntfMapListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String lang = user.getLangId();
    	
        query.append("SELECT                                                                                        		");
        query.append("      ''                                                                        AS seqNo      		");
        query.append("      ,''                                                                       AS isDelCheck 		");
        query.append("    , x.intfmap_id 															  AS intfmapId			");
        query.append("    , x.intf_id 																  AS intfId				");
        query.append("    , x.param_seq 															  AS paramSeq			");
        query.append("	  , x.intf_param_type 														  AS paramType			");
        query.append("    , dbo.SFACODE_TO_DESC(x.intf_param_type, 'INTF_PARAM_TYPE', 'SYS',x.comp_no,'"+lang+"') paramTypeDesc		");
        query.append("    , x.s_table_name 															  AS stabName			");
        query.append("    , x.s_field_name 															  AS sfieldName			");
        query.append("    , x.s_is_pk	 															  AS sisPk				");
        query.append("    , dbo.SFACODE_TO_DESC(x.s_is_pk,'IS_USE','SYS', x.comp_no, '"+lang+"')      AS sisPkDesc			");
        query.append("    , x.s_field_type  														  AS sfieldTypeDesc		");
        query.append("    , x.s_field_size  														  AS sfieldSizeDesc		");
        query.append("    , x.s_is_not_null  														  AS sisNotNull			");
        query.append("    , dbo.SFACODE_TO_DESC(x.s_is_not_null,'IS_USE','SYS', x.comp_no, '"+lang+"')AS sisNotNullDesc		");
        query.append("	  , x.s_default_size														  AS sdefaultVal		");
        query.append("    , x.s_remark  															  AS sremark			");
        query.append("    , x.t_table_name 															  AS ttabName			");
        query.append("    , x.t_field_name 															  AS tfieldName			");
        query.append("    , x.t_it_pk	 															  AS tisPk				");
        query.append("    , dbo.SFACODE_TO_DESC(x.t_it_pk,'IS_USE','SYS', x.comp_no, '"+lang+"')      AS tisPkDesc			");
        query.append("    , x.t_field_type  														  AS tfieldTypeDesc		");
        query.append("    , x.t_field_size  														  AS tfieldSizeDesc		");
        query.append("    , x.t_it_not_null	  														  AS tisNotNull			");
        query.append("    , dbo.SFACODE_TO_DESC(x.t_it_not_null,'IS_USE','SYS', x.comp_no, '"+lang+"')AS tisNotNullDesc		");
        query.append("	  , x.t_default_size														  AS tdefaultVal		");
        query.append("    , x.t_remark  															  AS tremark			");
        query.append("FROM TAINTFMAP x																						");
        query.append("WHERE  1=1                                                                                    		");
        query.append("AND    x.intfmap_id = ?                                                                       		");
        query.append("AND    x.comp_no    = ?                                                                       		");
        
        Object[] objects = new Object[] {
                 consultCompIntfMapListDTO.getIntfMapId()
               , user.getCompNo()
        };
    
        ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO = 
        		(ConsultCompIntfMapDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultCompIntfMapDetailDTO()));
        
        return consultCompIntfMapDetailDTO;
        
    }

	public int insertDetail(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAINTFMAP (											");
        query.append("    comp_no			, intfmap_id		, intf_id               ");
        query.append("  , param_seq         , intf_param_type   , s_table_name          ");
        query.append("  , s_field_name      , s_is_pk           , s_field_type          ");
        query.append("  , s_field_size    	, s_is_not_null     , s_default_size        ");
        query.append("  , s_remark		    , t_table_name      , t_field_name          ");
        query.append("  , t_it_pk           , t_field_type      , t_field_size    	    ");
        query.append("  , t_it_not_null     , t_default_size    , t_remark		        ");
        query.append(") VALUES (                										");
        query.append("    ?                 , ?                 , ?                     ");
        query.append("  , ?                 , ?                 , ?                     ");
        query.append("  , ?                 , ?                 , ?                     ");
        query.append("  , ?                 , ?                 , ?                     ");
        query.append("  , ?                 , ?                 , ?                     ");
        query.append("  , ?                 , ?                 , ?                     ");
        query.append("  , ?                 , ?                 , ?                     ");
        query.append(")                                									");

        Object[] objects = new Object[] {
        		user.getCompNo()
               , consultCompIntfMapDetailDTO.getIntfMapId()
               , consultCompIntfCommonDTO.getIntfId()
               , consultCompIntfMapDetailDTO.getParamSeq()
               , consultCompIntfMapDetailDTO.getParamType()
               , consultCompIntfMapDetailDTO.getStabName()
               , consultCompIntfMapDetailDTO.getSfieldName()
               , consultCompIntfMapDetailDTO.getSisPk()
               , consultCompIntfMapDetailDTO.getSfieldTypeDesc()
               , consultCompIntfMapDetailDTO.getSfieldSizeDesc()
               , consultCompIntfMapDetailDTO.getSisNotNull()
               , consultCompIntfMapDetailDTO.getSdefaultVal()
               , consultCompIntfMapDetailDTO.getSremark()
               , consultCompIntfMapDetailDTO.getTtabName()
               , consultCompIntfMapDetailDTO.getTfieldName()
               , consultCompIntfMapDetailDTO.getTisPk()
               , consultCompIntfMapDetailDTO.getTfieldTypeDesc()
               , consultCompIntfMapDetailDTO.getTfieldSizeDesc()
               , consultCompIntfMapDetailDTO.getTisNotNull()
               , consultCompIntfMapDetailDTO.getTdefaultVal()
               , consultCompIntfMapDetailDTO.getTremark()
        };
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
	}

	public int updateDetail(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("UPDATE TAINTFMAP SET              										  ");
        query.append("    param_seq     = ?    , intf_param_type  = ?    , s_table_name    = ?    ");
        query.append("  , s_field_name  = ?    , s_is_pk          = ?    , s_field_type    = ?    ");
        query.append("  , s_field_size  = ?    , s_is_not_null    = ?    , s_default_size  = ?    ");
        query.append("  , s_remark		= ?    , t_table_name     = ?    , t_field_name    = ?    ");
        query.append("  , t_it_pk       = ?    , t_field_type     = ?    , t_field_size    = ?    ");
        query.append("  , t_it_not_null = ?    , t_default_size   = ?    , t_remark		   = ?    ");
        query.append("WHERE  comp_no    = ?             										  ");
        query.append("  AND  intfmap_id = ?             										  ");
        query.append("  AND  intf_id    = ?             										  ");
        
        Object[] objects = new Object[] {
                    consultCompIntfMapDetailDTO.getParamSeq()
                  , consultCompIntfMapDetailDTO.getParamType()
                  , consultCompIntfMapDetailDTO.getStabName()
                  , consultCompIntfMapDetailDTO.getSfieldName()
                  , consultCompIntfMapDetailDTO.getSisPk()
                  , consultCompIntfMapDetailDTO.getSfieldTypeDesc()
                  , consultCompIntfMapDetailDTO.getSfieldSizeDesc()
                  , consultCompIntfMapDetailDTO.getSisNotNull()
                  , consultCompIntfMapDetailDTO.getSdefaultVal()
                  , consultCompIntfMapDetailDTO.getSremark()
                  , consultCompIntfMapDetailDTO.getTtabName()
                  , consultCompIntfMapDetailDTO.getTfieldName()
                  , consultCompIntfMapDetailDTO.getTisPk()
                  , consultCompIntfMapDetailDTO.getTfieldTypeDesc()
                  , consultCompIntfMapDetailDTO.getTfieldSizeDesc()
                  , consultCompIntfMapDetailDTO.getTisNotNull()
                  , consultCompIntfMapDetailDTO.getTdefaultVal()
                  , consultCompIntfMapDetailDTO.getTremark()
                  , user.getCompNo()
                  , consultCompIntfMapDetailDTO.getIntfMapId()
                  , consultCompIntfCommonDTO.getIntfId()
        };
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
	}
    
}