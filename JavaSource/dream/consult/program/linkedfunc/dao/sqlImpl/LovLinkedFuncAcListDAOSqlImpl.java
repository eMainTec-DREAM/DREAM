package dream.consult.program.linkedfunc.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.consult.program.linkedfunc.dao.LovLinkedFuncAcListDAO;
import dream.consult.program.linkedfunc.dto.LovLinkedFuncAcListDTO;

/**
 * Linked Function ÆË¾÷
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovLinkedFuncAcListDAOTarget"
 * @spring.txbn id="lovLinkedFuncAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovLinkedFuncAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovLinkedFuncAcListDAO
{
    /**
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovLinkedFuncAcListDTO
     * @param loginUser
     * @return
     */
    public List findLinkedFuncAcList(LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 										");
        query.append("    x.linkedfunc_id     	LINKEDFUNCID		");
        query.append("  , x.linkedfunc_no    	LINKEDFUNCNO		");
        query.append("  , x.description     	LINKEDFUNCDESC		");
        query.append("  , x.linkedfunc_method   METHOD				");
        query.append("  , x.is_use    			ISUSEID				");
        query.append("  , dbo.SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS','', '"+loginUser.getLangId()+"') ISUSEDESC		");
        query.append("  , x.REMARK       		REMARK 				");
        query.append("FROM TALINKEDFUNC x							");
        query.append("WHERE  1=1									");
        query.getSysCdQuery("x.is_use", lovLinkedFuncAcListDTO.getIsUseId() ,lovLinkedFuncAcListDTO.getIsUseDesc(), "IS_USE", "",loginUser.getLangId());
        query.getLikeQuery("description", lovLinkedFuncAcListDTO.getLinkedFuncDesc());
        query.append("ORDER BY linkedfunc_no						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findAcList(LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO, User user,
            Map<String, String> columnMap, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 										");
        query.append("    x.linkedfunc_id     	LINKEDFUNCID		");
        query.append("  , x.linkedfunc_no    	LINKEDFUNCNO		");
        query.append("  , x.description     	LINKEDFUNCDESC		");
        query.append("  , x.linkedfunc_method   METHOD				");
        query.append("  , x.is_use    			ISUSEID				");
        query.append("  , dbo.SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS','', '"+user.getLangId()+"') ISUSEDESC		");
        query.append("  , x.REMARK       		REMARK 				");
        query.append("FROM TALINKEDFUNC x							");
        query.append("WHERE  1=1									");
        query.getSysCdQuery("x.is_use", lovLinkedFuncAcListDTO.getIsUseId() ,lovLinkedFuncAcListDTO.getIsUseDesc(), "IS_USE", "",user.getLangId());
        query.getLikeQuery("description", lovLinkedFuncAcListDTO.getLinkedFuncDesc());
        query.getAndQuery("is_use", conditionMap);
        query.append("ORDER BY linkedfunc_no						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}