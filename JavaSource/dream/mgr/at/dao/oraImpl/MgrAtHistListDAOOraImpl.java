package dream.mgr.at.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.at.dao.MgrAtHistListDAO;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtHistListDTO;

/**
 * MgrAtHist Page - List DAO implements
 * @author youngjoo38
 * @version $Id: MgrAtHistListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="mgrAtHistListDAOTarget"
 * @spring.txbn id="mgrAtHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrAtHistListDAOOraImpl  extends BaseJdbcDaoSupportOra implements MgrAtHistListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: mgrAtHistListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param mgrAtHistListDTO
     * @return List
     */
    public List findList(MgrAtCommonDTO mgrAtCommonDTO, MgrAtHistListDTO mgrAtHistListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        		");
        query.append("       ''                     	seqNo           	");
        query.append("  , x.tracelogdtl_id           	TRACELOGDTLID		");
        query.append("  , (SELECT MAX(a.tracelog_id) FROM TATRACELOG a WHERE a.tracelog_id < y.tracelog_id AND a.file_name = y.file_name AND a.object_id = y.object_id)     prevTracelogId       		");
        query.append("  , x.tracelog_id             	TRACELOGID        	");
        query.append("    , TO_CHAR(TO_DATE(y.update_time,'YYYYMMDDHH24MISS'),'YYYY-MM-DD HH24:MI:SS')   UPDTIME	");
        query.append("  , SFACODE_TO_DESC(y.data_cud_type, 'DATA_CUD_TYPE', 'SYS', y.comp_no, '"+user.getLangId()+"')                     DATACUDTYPEDESC     		");
        query.append("  , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = y.comp_no AND a.emp_no = y.emp_no)     UPDBYDESC            		");
        query.append("  , (SELECT a.key_name FROM TALANG a WHERE a.lang = '"+user.getLangId()+"' AND a.key_no = z.key_no AND a.key_type = z.key_type)					ITEMDESC            ");
        query.append("  , x.field_id                    FIELDID             ");
        query.append("  , (SELECT b.field_value FROM TATRACELOGDTL b WHERE b.tracelog_id = (SELECT MAX(a.tracelog_id) FROM TATRACELOG a WHERE a.tracelog_id < y.tracelog_id AND a.file_name = y.file_name AND a.object_id = y.object_id)  AND b.field_id = x.field_id) BEFOREVALUE        		");
        query.append("  , x.field_value                 AFTERVALUE          ");
        query.append("FROM TATRACELOGDTL x LEFT OUTER JOIN TATRACELOG y    	");
        query.append("ON x.tracelog_id = y.tracelog_id                 		");
        query.append("LEFT OUTER JOIN TAPGFIELD z 							");
        query.append("ON x.field_id = z.field_id							");
        query.append("AND y.file_name = z.file_name							");
        query.append("WHERE  1=1                                    		");
        query.append(this.getWhere(mgrAtCommonDTO, mgrAtHistListDTO, user));        
        
        query.getOrderByQuery("y.update_time desc", mgrAtCommonDTO.getOrderBy(), mgrAtCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrAtCommonDTO.getIsLoadMaxCount(), mgrAtCommonDTO.getFirstRow()));

    }

    /**
     * Filter Á¶°Ç
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrAtHistListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrAtCommonDTO mgrAtCommonDTO, MgrAtHistListDTO mgrAtHistListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("y.comp_no", user.getCompNo());
        query.getAndQuery("y.file_name", mgrAtHistListDTO.getFileName());
        query.getAndQuery("x.field_id", mgrAtHistListDTO.getFieldId());
        query.getAndQuery("y.object_id", mgrAtHistListDTO.getObjectId());
//        query.append("AND (SELECT MAX(a.tracelog_id) FROM TATRACELOG a WHERE a.tracelog_id < y.tracelog_id AND a.file_name = y.file_name AND a.object_id = y.object_id) IS NOT NULL		");
        query.getAndQuery("y.tracelog_id", mgrAtHistListDTO.getTracelogId());
        
        return query.toString();
    }

    public String findTotalCount(MgrAtCommonDTO mgrAtCommonDTO,
            MgrAtHistListDTO mgrAtHistListDTO, User user) throws Exception 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("FROM TATRACELOGDTL x LEFT OUTER JOIN TATRACELOG y  ");
        query.append("ON x.tracelog_id = y.tracelog_id              ");
        query.append("LEFT OUTER JOIN TAPGFIELD z 					");
        query.append("ON x.field_id = z.field_id					");
        query.append("AND y.file_name = z.file_name					");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(mgrAtCommonDTO, mgrAtHistListDTO, user));        
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}
