package dream.mgr.lang.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.lang.dao.MgrLangMenuDAO;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * 일일작업확인 - 작업 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrLangMenuDAOTarget"
 * @spring.txbn id="mgrLangMenuDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrLangMenuDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrLangMenuDAO
{

	@Override
	public List findList(MaResCommonDTO maResCommonDTO, User user) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append(getColums(maResCommonDTO, user));
        query.append(getTables(maResCommonDTO, user));
        query.append(this.getWhere(maResCommonDTO, user));
        query.append(getOrderBy(maResCommonDTO, user));
        
		return getJdbcTemplate().queryForList(query.toString(maResCommonDTO.getIsLoadMaxCount(), maResCommonDTO.getFirstRow()));
		
    }

	@Override
	public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT								");
		query.append("    COUNT(1)							");
		query.append(getTables(maResCommonDTO, user));
		query.append(this.getWhere(maResCommonDTO, user));
		
		List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
	
	private String getColums(MaResCommonDTO maResCommonDTO, User user) {
    	
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT							");
    	query.append(" ''				SEQNO			");
    	query.append(" , x.menu_no 		MENUCODE		");
    	query.append(" , (SELECT a.key_name 			 ");
    	query.append("		FROM talang a				 ");
    	query.append("		WHERE a.key_no = x.key_no    ");
    	query.append("		AND a.key_type = x.key_type  ");
    	query.append("		AND a.lang = '"+user.getLangId()+"')  	MENUDESC");
    	
		return query.toString();
	}
	
	
	private String getTables(MaResCommonDTO maResCommonDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("FROM TAMENU x										");

		return query.toString();
	}
	
	private String getOrderBy(MaResCommonDTO maResCommonDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getOrderByQuery("x.menu_id","x.menu_no", maResCommonDTO.getOrderBy(), maResCommonDTO.getDirection());		
		
		return query.toString();
	}

	private String getWhere(MaResCommonDTO maResCommonDTO, User user) 
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("WHERE 1 = 1	");
		query.append("AND x.key_no = (SELECT a.key_no 				 	");
		query.append("					FROM TALANG a					");
		query.append("					WHERE a.lang_id = '"+maResCommonDTO.getLangId()+"'");
		query.append("					AND a.key_type = 'MENU')		");
		
        return query.toString();
    }
   
}