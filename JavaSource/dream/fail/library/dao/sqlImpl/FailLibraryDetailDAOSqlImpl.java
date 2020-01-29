package dream.fail.library.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.fail.library.dao.FailLibraryDetailDAO;
import dream.fail.library.dto.FailLibraryDetailDTO;


/**
 * 고장library - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="failLibraryDetailDAOTarget"
 * @spring.txbn id="failLibraryDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class FailLibraryDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements FailLibraryDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     */
    public FailLibraryDetailDTO findDetail(User user, String failureId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
        query.append("SELECT								                    ");
        query.append("       x.comp_no		                        compNo,     ");
        query.append("       x.failure_id                           failureId,  ");
        query.append("       x.failure_no                           failureNo,  ");
        query.append("       x.description		                    description,");
        query.append("       x.fail_type                            failType,   ");
        query.append("       dbo.SFACODE_TO_DESC(x.fail_type, 'FAILURE_TYPE', 'SYS', x.comp_no,'"+user.getLangId()+"') failTypeDesc,    ");
        query.append("       x.is_use                               isUse,      ");
        query.append("       x.ord_no                               ordNo,      ");
        query.append("       x.key_type                             keyType,    ");
        query.append("       x.key_no                               keyNo,      ");
        query.append("       (SELECT key_name									");
        query.append("        FROM TALANG										");
        query.append("        WHERE key_type=x.key_type							");
        query.append("       	AND key_no = x.key_no							");
        query.append("       	AND lang = '"+lang+"') 				failName,	");
        query.append("       x.remark                               remark      ");
        query.append("FROM   TAFAILURE x                                        ");
        query.append("WHERE  x.comp_no    = '"+user.getCompNo()+"'              ");
        query.append("  AND  x.failure_id = '"+failureId+"'	                    ");
    
        FailLibraryDetailDTO failLibraryDetailDTO = 
        		(FailLibraryDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new FailLibraryDetailDTO()));
        
        return failLibraryDetailDTO;
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryDetailDTO
     * @return
     */
    public int insertDetail(FailLibraryDetailDTO failLibraryDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAFAILURE(                                    ");
    	query.append("   comp_no,      failure_id,  failure_no,   description,	");
    	query.append("   fail_type,    is_use,       ord_no,	    ");
    	query.append("   remark,       key_type,   key_no                       ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			   ?,		   ?,		    ?,              ");
    	query.append("	 ?,			   ?,		    ?,              ");
    	query.append("	 ?,            ?,          ?              		        ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
//    			failLibraryDetailDTO.getCompNo(),
//    			failLibraryDetailDTO.getFailureId(),
//    			failLibraryDetailDTO.getFailureNo(),
//    			failLibraryDetailDTO.getDescription(),
//    			failLibraryDetailDTO.getFailType(),
//    			failLibraryDetailDTO.getIsUse(),
//    			failLibraryDetailDTO.getOrdNo(),
//    			failLibraryDetailDTO.getRemark(),
//    			failLibraryDetailDTO.getKeyType(),
//    			failLibraryDetailDTO.getKeyNo()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryDetailDTO
     * @return
     */
    public int updateDetail(FailLibraryDetailDTO  failLibraryDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAFAILURE SET	                ");
    	query.append("	     description    = ?,	        ");
        query.append("       fail_type      = ?,            ");
        query.append("       is_use         = ?,            ");
        query.append("       ord_no         = ?,            ");
        query.append("       remark         = ?,            ");
        query.append("       key_type       = ?,            ");
        query.append("       key_no         = ?             ");
    	query.append("WHERE  comp_no        = ?	            ");
    	query.append("  AND  failure_id     = ?	            ");
    	
    	Object[] objects = new Object[] {
//                failLibraryDetailDTO.getDescription(),
//                failLibraryDetailDTO.getFailType(),
//                failLibraryDetailDTO.getIsUse(),
//                failLibraryDetailDTO.getOrdNo(),
//                failLibraryDetailDTO.getRemark(),
//                failLibraryDetailDTO.getKeyType(),
//                failLibraryDetailDTO.getKeyNo(),
//                failLibraryDetailDTO.getCompNo(),
//                failLibraryDetailDTO.getFailureId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param failLibraryDetailDTO
     * @return
     */
    public String validFailureNo(FailLibraryDetailDTO failLibraryDetailDTO)
    {
        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAFAILURE x                                        ");
        query.append("WHERE  x.comp_no   = '" + failLibraryDetailDTO.getCompNo()   + "'");
//        query.append("  AND  x.failure_no= '" + failLibraryDetailDTO.getFailureNo()+ "'");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}