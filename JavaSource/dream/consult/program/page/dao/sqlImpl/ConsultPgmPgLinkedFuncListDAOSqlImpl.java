package dream.consult.program.page.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.ConsultPgmPgLinkedFuncListDAO;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 연결기능 목록 dao
 * @author  youngjoo38
 * @version $Id: ConsultPgmPgLinkedFuncListDAO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
 * @since   1.0
 * @spring.bean id="consultPgmPgLinkedFuncListDAOTarget"
 * @spring.txbn id="consultPgmPgLinkedFuncListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmPgLinkedFuncListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmPgLinkedFuncListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: ConsultPgmPgLinkedFuncListDAO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param consultPgmPgLinkedFuncListDTO
     * @return List
     */
    public List findFieldList(MaPgMngCommonDTO maPgMngCommonDTO, ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("      '' seqNo		 							");
        query.append("    , '' isDelCheck								");
        query.append("    , x.pglinkedfunc_id 		PGLINKEDFUNCID		");
        query.append("    , y.description           LINKEDFUNCDESC		");
        query.append("    , y.linkedfunc_method    	METHOD   			");
        query.append("    , (SELECT a.key_name FROM TALANG a WHERE a.key_type = x.key_type AND a.key_no = x.key_no AND a.lang = '"+user.getLangId()+"') KEYNAME		");
        query.append("    , x.key_type 				KEYTYPE				");
        query.append("    , x.key_no 				KEYNO				");	
        query.append("    , x.ord_no 				ORDNO				");
        query.append("    , dbo.SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS','', '"+user.getLangId()+"') ISUSEDESC		");
        query.append("    , x.REMARK         		REMARK				");
        query.append("FROM TAPGLINKEDFUNC x INNER JOIN TALINKEDFUNC y	");
        query.append("ON x.linkedfunc_id = y.linkedfunc_id				");
        query.append("WHERE 1=1											");
        query.append(this.getWhere(maPgMngCommonDTO,consultPgmPgLinkedFuncListDTO));
        query.getOrderByQuery("x.pglinkedfunc_id","x.ord_no", maPgMngCommonDTO.getOrderBy(), maPgMngCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPgMngCommonDTO.getIsLoadMaxCount(), maPgMngCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id: ConsultPgmPgLinkedFuncListDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteFieldList(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAPGLINKEDFUNC			");
    	query.append("WHERE  pglinkedfunc_id = '"+id+"'		");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPgMngCommonDTO maPgMngCommonDTO, ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.page_id", maPgMngCommonDTO.getPageId());
        
        if (!"".equals(consultPgmPgLinkedFuncListDTO.getPgLinkedFuncId()))
        {
            query.getAndQuery("x.pglinkedfunc_id", consultPgmPgLinkedFuncListDTO.getPgLinkedFuncId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT              								");
        query.append("   COUNT(1)        								");
        query.append("FROM TAPGLINKEDFUNC x INNER JOIN TALINKEDFUNC y	");
        query.append("ON x.linkedfunc_id = y.linkedfunc_id				");
        query.append("WHERE 1=1           								");
        query.append(this.getWhere(maPgMngCommonDTO,consultPgmPgLinkedFuncListDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}