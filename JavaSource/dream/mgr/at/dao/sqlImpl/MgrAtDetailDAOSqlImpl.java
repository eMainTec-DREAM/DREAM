package dream.mgr.at.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.at.dao.MgrAtDetailDAO;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrAtDetailDAOTarget"
 * @spring.txbn id="mgrAtDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrAtDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrAtDetailDAO
{
	/**
     * Detail Á¶Èñ
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param revisionhistId
     * @param user
     * @return
     * @throws Exception
     */
    public MgrAtDetailDTO findDetail(MgrAtCommonDTO mgrAtCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT 												");
    	query.append("     x.tracelog_id       			TRACELOGID  		");
    	query.append("    , y.tracelogdtl_id     		TRACELOGDTLID       ");
    	query.append("    , x.file_name + ' / ' +        					");
    	query.append("    		(SELECT a.description FROM TAPAGE a WHERE a.file_name = x.file_name )       		FILENAMEFIELDDESC	");
    	query.append("    , y.field_id  + ' / ' +							");
    	query.append("          (SELECT a.key_name FROM TALANG a WHERE a.lang = ? AND a.key_no = z.key_no AND a.key_type = z.key_type) 			FIELDIDITEMDESC		");
    	query.append("    , dbo.SFACODE_TO_DESC(x.data_cud_type, 'DATA_CUD_TYPE', 'SYS', x.comp_no, ?)     				DATACUDTYPEDESC     ");
    	query.append("    , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_no = x.emp_no) 	UPDBYDESC        	");
    	query.append("    , x.update_time   			UPDTIME				");
    	query.append("    , y.field_value       		AFTERVALUE          ");
    	query.append("    , x.object_id         		objectId            ");
    	query.append("    , x.file_name					fileName			");
    	query.append("    , y.field_id 					fieldId				");
    	query.append("FROM TATRACELOG x LEFT OUTER JOIN TATRACELOGDTL y    	");
    	query.append("ON x.tracelog_id = y.tracelog_id                		");
        query.append("LEFT OUTER JOIN TAPGFIELD z 							");
        query.append("ON y.field_id = z.field_id							");
        query.append("AND x.file_name = z.file_name							");
    	query.append("WHERE 1=1                                     		");
    	query.append("  AND x.comp_no 		 = ?							");
    	query.append("  AND y.tracelogdtl_id = ?							");

    	Object[] objects = new Object[] {
    			user.getLangId()
    			, user.getLangId()
    			, user.getCompNo()
    			, mgrAtCommonDTO.getTracelogDtlId()
    	};
    	
    	MgrAtDetailDTO mgrAtDetailDTO = 
        		(MgrAtDetailDTO)getJdbcTemplate().query(query.toString(), getObject(objects), new MwareExtractor(new MgrAtDetailDTO()));
        
        return mgrAtDetailDTO;
    }
}