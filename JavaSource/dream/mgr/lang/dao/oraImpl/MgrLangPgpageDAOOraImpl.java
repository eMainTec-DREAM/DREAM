package dream.mgr.lang.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.lang.dao.MgrLangPgpageDAO;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * 다국어사용된탭 
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrLangPgpageDAOTarget"
 * @spring.txbn id="mgrLangPgpageDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrLangPgpageDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrLangPgpageDAO
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

    	query.append("SELECT                            		");
    	query.append(" ''                SEQNO            		");
    	query.append(" , (SELECT a.file_name FROM TAPAGE a WHERE a.page_id = x.page_id)            	PAGEID            		");
    	query.append(" , (SELECT a.description FROM TAPAGE a WHERE a.page_id = x.page_id)         	PAGEDESC        		");
    	query.append(" , (SELECT a.file_name FROM TAPAGE a WHERE a.page_id = x.c_page_id)        	CPAGEID            		");
    	query.append(" , (SELECT a.key_name 																				");
    	query.append("		FROM TALANG a 																					");
    	query.append("		WHERE a.key_no = x.key_no 							");
    	query.append("		AND a.key_type = x.key_type  						");
    	query.append("		AND lang = '"+user.getLangId()+"')     CPAGEDESC    ");
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
		
		query.append("FROM TAPGPAGE x						");
		
		return query.toString();
	}
	
	private String getOrderBy(MaResCommonDTO maResCommonDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.getOrderByQuery("x.page_id", maResCommonDTO.getOrderBy(), maResCommonDTO.getDirection());		
		
		return query.toString();
	}

	private String getWhere(MaResCommonDTO maResCommonDTO, User user) 
    {        
        QueryBuffer query = new QueryBuffer();
      
        query.append("WHERE 1 = 1	");
		query.append("AND x.key_no = (SELECT a.key_no 					");
		query.append("					FROM TALANG a					");
		query.append("					WHERE a.lang_id = '"+maResCommonDTO.getLangId()+"'");
		query.append("					AND a.key_type = 'TAB')		");
		
        return query.toString();
    }
}