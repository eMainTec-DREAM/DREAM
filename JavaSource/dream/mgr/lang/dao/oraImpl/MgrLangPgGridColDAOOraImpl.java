package dream.mgr.lang.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.lang.dao.MgrLangPgGridColDAO;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * 다국어사용된필드 
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrLangPgGridColDAOTarget"
 * @spring.txbn id="mgrLangPgGridColDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrLangPgGridColDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrLangPgGridColDAO
{
    
	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) 
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append(getColums(maResCommonDTO, user));
        query.append(getTables(maResCommonDTO, user));
        query.append(this.getWhere(maResCommonDTO, user));
        query.append(getOrderBy(maResCommonDTO, user));
        
		return getJdbcTemplate().queryForList(query.toString(maResCommonDTO.getIsLoadMaxCount(), maResCommonDTO.getFirstRow()));
	
    }

	private String getColums(MaResCommonDTO maResCommonDTO, User user) {
    	
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT 														");
    	query.append(" ''                                               SEQNO		");
    	query.append(" , (SELECT a.file_name 										");
    	query.append("     FROM TAPAGE a 											");
    	query.append("     WHERE a.page_id = (SELECT aa.page_id FROM TAPGGRID aa WHERE aa.pggrid_id = x.pggrid_id)) 	PAGEID		");
    	query.append(" , (SELECT a.description 																						");
    	query.append("      FROM TAPAGE a 																							");
    	query.append("    WHERE a.page_id = (SELECT aa.page_id FROM TAPGGRID aa WHERE aa.pggrid_id = x.pggrid_id))      PAGEDESC	");
    	query.append(" , column_id                                                                                      COLUMNID	");
    	query.append(" , (SELECT a.description FROM TAPGGRID a WHERE a.pggrid_id = x.pggrid_id)	                        GRIDID		");
    	query.append(" , (SELECT a.key_name 																						");
    	query.append("     FROM TALANG a 																							");
    	query.append("   WHERE a.key_no = x.key_no AND a.key_type = x.key_type AND a.lang = '"+user.getLangId()+"') 	COLUMNDESC	");

		return query.toString();
	}
	
	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		
		QueryBuffer query = new QueryBuffer();
       
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(maResCommonDTO, user));
        query.append(this.getWhere(maResCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
	
	private String getTables(MaResCommonDTO maResCommonDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append(" FROM TAPGGRIDCOL x		");
		
		return query.toString();
	}
	
	private String getOrderBy(MaResCommonDTO maResCommonDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.getOrderByQuery("x.pggridcol_id", maResCommonDTO.getOrderBy(), maResCommonDTO.getDirection());		
		
		return query.toString();
	}

	private String getWhere(MaResCommonDTO maResCommonDTO, User user) 
    {        
        QueryBuffer query = new QueryBuffer();
      
        query.append("WHERE 1 = 1	");
		query.append("AND x.key_no = (SELECT a.key_no 					");
		query.append("					FROM TALANG a					");
		query.append("					WHERE a.lang_id = '"+maResCommonDTO.getLangId()+"'");
		query.append("					AND a.key_type = 'LABEL')		");
		
        return query.toString();
    }
}