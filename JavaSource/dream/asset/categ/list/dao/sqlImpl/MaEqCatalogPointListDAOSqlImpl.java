package dream.asset.categ.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.asset.categ.list.dao.MaEqCatalogPointListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;

/**
 * 설비종류의 점검항목 탭 list dao sql impl
 * @author  euna0207
 * @version $Id: MaEqCatalogPointListDAOSqlImpl.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="maEqCatalogPointListDAOTarget"
 * @spring.txbn id="maEqCatalogPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCatalogPointListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqCatalogPointListDAO
{

	@Override
	public List findList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user)
			throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT                                                                                                                       			");
		query.append(" ''                                                                                                        AS IsDelCheck        		");
		query.append(" ,''                                                                                                       AS SeqNo            		");
		query.append(" , x.eqctgpmpoint_id                                                                                       AS eqCtgPmPointId    		");
		query.append(" , (SELECT eqctg_id FROM TAEQCTG WHERE comp_no = x.comp_no AND eqctg_id = x.eqctg_id) 					AS eqCtgId					");
		query.append(" , x.cycle 																								AS cycle					");
		query.append(" , (SELECT description FROM TAEQCTGASMB WHERE comp_no = x.comp_no AND eq_ctg_asmb_id = x.eq_ctg_asmb_id) 	AS eqasmbDesc				"); 
		query.append(" , x.eq_ctg_asmb_id															             	 			 AS eqasmbId        		");
		query.append(" , dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE','SYS','"+user.getCompNo()+"', '"+user.getLangId()+"')  AS periodTypeDesc    		");
		query.append(" , x.check_point                                                                                           AS 'checkPoint'        	");
		query.append(" , x.check_method                                                                                          AS checkMethod        		");
		query.append(" , x.fit_basis                                                                                             AS fitBasis            	");
		query.append(" , dbo.SFACODE_TO_DESC(x.check_type, 'CHECK_TYPE','SYS','"+user.getCompNo()+"','"+user.getLangId()+"')     AS checkTypeDesc    		");
		query.append(" , CONVERT(varchar, x.check_min) +'/'+ CONVERT(varchar, x.check_basis_val) + '/'+ CONVERT(varchar, x.check_max)  AS minBasisMax    	");
		query.append(" , x.uom                                                                                                	 AS uom                		");
		query.append(" , x.ord_no                                                                                             	 AS ordNo            		");
		query.append(" , dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '"+user.getCompNo()+"', '"+user.getLangId()+"')          AS isUse            		");
		query.append(" , x.REMARK                                                                                                AS REMARK            		");
		query.append(" , x.work_time																							 AS PredTime				");
		
		query.append(" , x.step_num 																							 AS stepNum					");
        query.append(" , (SELECT dbo.SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"') )  AS scheduleType		");
        query.append(" , CASE x.schedule_type 																												");
        query.append("   WHEN 'T' THEN CONVERT(NVARCHAR,x.cycle)+x.period_type																				");
        query.append("   ELSE CONVERT(NVARCHAR,x.USAGE)				 																						");
        query.append("    END                   																				 AS expLife					");
        query.append(" , x.is_eqtype_point                          															 AS isCommCtgPoint    		");
        
		query.append(" FROM TAEQCTGPMPOINT x																												");
		query.append(" WHERE 1=1																															");
		query.append(this.getWhere(maEqCatalogCommonDTO, maEqCatalogPointListDTO, user));
		query.getOrderByQuery("x.eqctgpmpoint_id", "x.eqctgpmpoint_id", maEqCatalogPointListDTO.getOrderBy(), maEqCatalogPointListDTO.getDirection());

       return getJdbcTemplate().queryForList(query.toString(maEqCatalogPointListDTO.getIsLoadMaxCount(), maEqCatalogPointListDTO.getFirstRow()));
	}
	
	private String getWhere(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user)
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getStringEqualQuery("x.comp_no", user.getCompNo());
		query.getAndNumKeyQuery("x.eqctg_id", maEqCatalogCommonDTO.getEqCtgId());

		if(!"".equals(maEqCatalogPointListDTO.getEqCtgPmPointId())){
        	query.getAndQuery("x.eqctgpmpoint_id", maEqCatalogPointListDTO.getEqCtgPmPointId());
        	return query.toString();
        }
		return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAEQCTGPMPOINT			");
        query.append("WHERE  comp_no 		    = ?			");
        query.append("  AND  eqctgpmpoint_id  	= ?			");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
	@Override
	public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user)
			throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("SELECT                                   ");
        query.append("       COUNT(1)                          ");
        query.append("FROM TAEQCTGPMPOINT x					   ");
    	query.append("WHERE  1=1							   ");
    	query.append(this.getWhere(maEqCatalogCommonDTO, maEqCatalogPointListDTO, user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
	
}