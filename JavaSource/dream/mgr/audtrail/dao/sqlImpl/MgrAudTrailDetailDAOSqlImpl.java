package dream.mgr.audtrail.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.audtrail.dao.MgrAudTrailDetailDAO;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrAudTrailDetailDAOTarget"
 * @spring.txbn id="mgrAudTrailDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrAudTrailDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrAudTrailDetailDAO
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
    public MgrAudTrailDetailDTO findDetail(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                                  		");
    	query.append("      x.tracelog_id       TRACELOGID    		");
    	query.append("    , x.update_time       UPDTIME        		");
    	query.append("    , x.file_name       	FILENAME        	");
    	query.append("    , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_no = x.emp_no) UPDBYDESC              		");
    	query.append("    , dbo.SFACODE_TO_DESC(x.data_cud_type, 'DATA_CUD_TYPE', 'SYS', x.comp_no,  ?)     DATACUDTYPEDESC        		");
    	query.append("FROM TATRACELOG x                        		");
    	query.append("WHERE 1=1										");
    	query.append("AND x.comp_no 	= ? 						");
    	query.append("AND x.tracelog_id = ? 						");

    	Object[] objects = new Object[] {
    			user.getLangId()
    			, user.getCompNo()
    			, mgrAudTrailCommonDTO.getTracelogId()
    	};
    	
    	MgrAudTrailDetailDTO mgrAudTrailDetailDTO = 
        		(MgrAudTrailDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrAudTrailDetailDTO()));
        
        return mgrAudTrailDetailDTO;
    }
}