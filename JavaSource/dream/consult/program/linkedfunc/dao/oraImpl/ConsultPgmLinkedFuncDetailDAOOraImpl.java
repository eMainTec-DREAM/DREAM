package dream.consult.program.linkedfunc.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.linkedfunc.dao.ConsultPgmLinkedFuncDetailDAO;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncDetailDTO;

/**
 * PgmLinkedFunc Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="consultPgmLinkedFuncDetailDAOTarget"
 * @spring.txbn id="consultPgmLinkedFuncDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmLinkedFuncDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultPgmLinkedFuncDetailDAO
{

    public ConsultPgmLinkedFuncDetailDTO findPgmLinkedFuncDetail(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 									");
        query.append("   x.linkedfunc_id 		LINKEDFUNCID	");
        query.append("  , x.linkedfunc_no 		LINKEDFUNCNO	");
        query.append("  , x.description 		LINKEDFUNCDESC	");
        query.append("  , x.linkedfunc_method 	METHOD			");
        query.append("  , x.is_use 				ISUSEID			");
        query.append("  , SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS','', ?) ISUSEDESC		");
        query.append("  , x.REMARK 				REMARK			");
        query.append("FROM TALINKEDFUNC x						");
        query.append("WHERE  1=1                        		");
        query.append("AND    x.linkedfunc_id = ?				");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,consultPgmLinkedFuncCommonDTO.getLinkedFuncId()
        };
    
        ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO = 
                (ConsultPgmLinkedFuncDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultPgmLinkedFuncDetailDTO()));
        
        return consultPgmLinkedFuncDetailDTO;
        
    }
    

    public int insertPgmLinkedFuncDetail(ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("INSERT INTO TALINKEDFUNC (	");
        query.append("    linkedfunc_id             ");
        query.append("  , linkedfunc_no             ");
        query.append("  , linkedfunc_method         ");
        query.append("  , description               ");
        query.append("  , is_use                    ");
        query.append("  , remark                    ");
        query.append(" ) VALUES (                  	");
        query.append("    ?                       	");
        query.append("  , ?                      	");
        query.append("  , ?                      	");
        query.append("  , ?                      	");
        query.append("  , ?                      	");
        query.append("  , ?                      	");
        query.append(" )                         	");
        
        Object[] objects = new Object[] {
        		  consultPgmLinkedFuncDetailDTO.getLinkedFuncId()
        		, consultPgmLinkedFuncDetailDTO.getLinkedFuncNo()
        		, consultPgmLinkedFuncDetailDTO.getMethod()
        		, consultPgmLinkedFuncDetailDTO.getLinkedFuncDesc()
        		, consultPgmLinkedFuncDetailDTO.getIsUseId()
        		, consultPgmLinkedFuncDetailDTO.getRemark()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    
    
    public int updatePgmLinkedFuncDetail(ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TALINKEDFUNC SET       ");
        query.append("    linkedfunc_no     = ?		");
        query.append("  , description       = ?     ");
        query.append("  , linkedfunc_method	= ?     ");
        query.append("  , is_use            = ?     ");
        query.append("  , remark            = ?     ");
        query.append("WHERE linkedfunc_id   = ?     ");
        
        Object[] objects = new Object[] {
        		  consultPgmLinkedFuncDetailDTO.getLinkedFuncNo()
        		, consultPgmLinkedFuncDetailDTO.getLinkedFuncDesc()
        		, consultPgmLinkedFuncDetailDTO.getMethod()
        		, consultPgmLinkedFuncDetailDTO.getIsUseId()
        		, consultPgmLinkedFuncDetailDTO.getRemark()
        		, consultPgmLinkedFuncDetailDTO.getLinkedFuncId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
}
