package dream.consult.program.page.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.ConsultPgmPgLinkedFuncDetailDAO;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 연결기능 상세 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="consultPgmPgLinkedFuncDetailDAOTarget"
 * @spring.txbn id="consultPgmPgLinkedFuncDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmPgLinkedFuncDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmPgLinkedFuncDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param pageId
     * @param pgFieldId
     * @return
     */
    public ConsultPgmPgLinkedFuncDetailDTO findDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT											");
        query.append("      x.page_id				pageId				");
        query.append("    , x.pglinkedfunc_id 		PGLINKEDFUNCID		");
        query.append("    , y.linkedfunc_id         LINKEDFUNCID		");
        query.append("    , y.description           LINKEDFUNCDESC		");
        query.append("    , y.linkedfunc_method    	METHOD   			");
        query.append("    , (SELECT a.key_name FROM TALANG a WHERE a.key_type = x.key_type AND a.key_no = x.key_no AND a.lang = ? ) KEYNAME		");
        query.append("    , x.key_type 				KEYTYPE				");
        query.append("    , x.key_no 				KEYNO				");	
        query.append("    , x.ord_no 				ORDNO				");
        query.append("    , x.is_use                ISUSEID				");
        query.append("    , dbo.SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS','', ? ) ISUSEDESC		");
        query.append("    , x.REMARK         		REMARK				");
        query.append("FROM TAPGLINKEDFUNC x INNER JOIN TALINKEDFUNC y	");
        query.append("ON x.linkedfunc_id = y.linkedfunc_id				");
        query.append("WHERE 1=1											");
        query.append("AND x.page_id 		= ?							");
        query.append("AND x.pglinkedfunc_id = ?							");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		, user.getLangId()
        		, consultPgmPgLinkedFuncDetailDTO.getPageId()
        		, consultPgmPgLinkedFuncDetailDTO.getPgLinkedFuncId()
        };
                
        ConsultPgmPgLinkedFuncDetailDTO resultDTO = 
        		(ConsultPgmPgLinkedFuncDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultPgmPgLinkedFuncDetailDTO()));
        
        return resultDTO;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultPgmPgLinkedFuncDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPGLINKEDFUNC SET	    ");
    	query.append("	  linkedfunc_id	 	 = ?	");
    	//query.append("	, linkedfunc_method	 = ?	");
    	query.append("	, key_type	 		 = ?	");
    	query.append("  , key_no  		 	 = ?    ");
    	query.append("	, ord_no		 	 = ?	");
    	query.append("	, is_use			 = ?	");
    	query.append("  , remark			 = ?    ");
    	//query.append("  , linkedfunc_loc     = ?	");
    	query.append("WHERE pglinkedfunc_id 		 = ?	");
    	query.append("  AND page_id  		 = ?	");
    	
    	Object[] objects = new Object[] {
    			  consultPgmPgLinkedFuncDetailDTO.getLinkedFuncId()
    			, consultPgmPgLinkedFuncDetailDTO.getKeyType()
    			, consultPgmPgLinkedFuncDetailDTO.getKeyNo()
    			, consultPgmPgLinkedFuncDetailDTO.getOrdNo()
    			, consultPgmPgLinkedFuncDetailDTO.getIsUseId()
    			, consultPgmPgLinkedFuncDetailDTO.getRemark()
    			, consultPgmPgLinkedFuncDetailDTO.getPgLinkedFuncId()
    			, maPgMngCommonDTO.getPageId() 
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultPgmPgLinkedFuncDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPGLINKEDFUNC (			");
    	query.append("	 pglinkedfunc_id	, page_id		");
    	query.append(" , linkedfunc_id		, file_name		");
    	query.append(" , linkedfunc_method	, key_type		");
    	query.append(" , key_no				, ord_no		");
    	query.append(" , is_use				, remark   		");
    	//query.append(" , linkedfunc_loc                  		");
    	query.append("	)	VALUES	(						");
    	query.append("	 ?				    , ?				");
    	query.append(" , ?				    , ?				");
    	query.append(" , ?				    , ?				");
    	query.append(" , ?                  , ?             ");
    	query.append(" , ?                  , ?  			");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			  consultPgmPgLinkedFuncDetailDTO.getPgLinkedFuncId()
    			, maPgMngCommonDTO.getPageId()
    			, consultPgmPgLinkedFuncDetailDTO.getLinkedFuncId()
    			, ""
    			, consultPgmPgLinkedFuncDetailDTO.getLinkedFuncMethod()
    			, consultPgmPgLinkedFuncDetailDTO.getKeyType()
    			, consultPgmPgLinkedFuncDetailDTO.getKeyNo()
    			, consultPgmPgLinkedFuncDetailDTO.getOrdNo()
    			, consultPgmPgLinkedFuncDetailDTO.getIsUseId()
    			, consultPgmPgLinkedFuncDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
}