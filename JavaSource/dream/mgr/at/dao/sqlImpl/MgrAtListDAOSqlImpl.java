package dream.mgr.at.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.at.dao.MgrAtListDAO;
import dream.mgr.at.dto.MgrAtCommonDTO;

/**
 * Audit Trail DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrAtListDAOTarget"
 * @spring.txbn id="mgrAtListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrAtListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrAtListDAO
{
	/**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAtListDTO
     * @return List
     */
    public List findList(MgrAtCommonDTO mgrAtCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT  										");
        query.append("      '' 					seqNo 				");
        query.append("    , x.update_time       UPDTIME				");
        query.append("    , x.tracelog_id       TRACELOGID			");
        query.append("    , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_no = x.emp_no) UPDBYDESC		");
        query.append("    , dbo.SFACODE_TO_DESC(x.data_cud_type, 'DATA_CUD_TYPE', 'SYS', x.comp_no,  '"+user.getLangId()+"') 	DATACUDTYPEDESC		");
        query.append("    , y.tracelogdtl_id 	TRACELOGDTLID		");
        query.append("    , x.file_name      	FILENAME			");
        query.append("    , (SELECT a.description FROM TAPAGE a WHERE a.file_name = x.file_name ) FIELDDESC		");
        query.append("    , y.field_id         	FIELDID				");
        query.append("    , (SELECT a.key_name FROM TALANG a WHERE a.lang = '"+user.getLangId()+"' AND a.key_no = z.key_no AND a.key_type = z.key_type)	    ITEMDESC			");
        query.append("    , y.field_value   	AFTERVALUE			");
        query.append("    , x.object_id     	OBJECTID 			");
        query.append("FROM TATRACELOG x LEFT OUTER JOIN TATRACELOGDTL y	");
        query.append("ON x.tracelog_id = y.tracelog_id				");
        query.append("INNER JOIN TAPGFIELD z 			     		");
        query.append("ON y.field_id = z.field_id					");
        query.append("AND x.file_name = z.file_name					");
        query.append("WHERE 1=1 									");
        query.append(this.getWhere(mgrAtCommonDTO, user));
        query.getOrderByQuery("y.tracelogdtl_id", "x.update_time DESC", mgrAtCommonDTO.getOrderBy(), mgrAtCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrAtCommonDTO.getIsLoadMaxCount(), mgrAtCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAtListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrAtCommonDTO mgrAtCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("x.comp_no", user.getCompNo());
        
        query.append("  AND x.file_name IS NOT NULL                 ");
        query.append("  AND x.file_name != 'null'                   ");
        query.append("  AND x.file_name != ''                       ");
        
        // 화면
        if(!"".equals(mgrAtCommonDTO.getFilterPageId()) || !"".equals(mgrAtCommonDTO.getFilterPageDesc()))
        {
        	query.append("AND x.file_name IN (SELECT a.file_name 	");
        	query.append("                      FROM TAPAGE a 		");
        	query.append("                     WHERE 1=1 			");
        	query.getCodeLikeQuery("a.page_id", "a.file_name", mgrAtCommonDTO.getFilterPageId(), mgrAtCommonDTO.getFilterPageDesc());
        	query.append("                                        )	");
        }
        
        // 변경구분
        query.getSysCdQuery("x.data_cud_type", mgrAtCommonDTO.getFilterCudTypeId(), mgrAtCommonDTO.getFilterCudTypeDesc(), "DATA_CUD_TYPE", user.getCompNo(), user.getLangId());
        
        // 변경일자
        query.getAndDateQuery("SUBSTRING(update_time, 1, 8)", mgrAtCommonDTO.getFilterFromDate(), mgrAtCommonDTO.getFilterToDate());
                
        // 변경자
        query.getCodeLikeQuery("x.emp_no", "(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_no = x.emp_no)", mgrAtCommonDTO.getFilterUpdById(), mgrAtCommonDTO.getFilterUpdByDesc());
        
        // 항목
        if(!"".equals(mgrAtCommonDTO.getFilterKeyName()) || !"".equals(mgrAtCommonDTO.getFilterKeyNo()) || !"".equals(mgrAtCommonDTO.getFilterKeyType()))
		{
        	query.append("AND z.description								");
        	query.append("	IN	( SELECT a.key_name						");
        	query.append("			FROM TALANG	a						");
        	query.append("		   WHERE 1=1							");
        	query.getAndQuery("a.lang", user.getLangId());
        	query.getLikeQuery("a.key_name", mgrAtCommonDTO.getFilterKeyName());
        	query.getAndQuery("a.key_no", mgrAtCommonDTO.getFilterKeyNo());
        	query.getAndQuery("a.key_type", mgrAtCommonDTO.getFilterKeyType());
        	query.append("				)									");
		}
            
        return query.toString();
    }

	public String findTotalCount(MgrAtCommonDTO mgrAtCommonDTO, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                           				");
        query.append("       COUNT(1)                  				");
        query.append("FROM TATRACELOG x LEFT OUTER JOIN TATRACELOGDTL y	");
        query.append("ON x.tracelog_id = y.tracelog_id				");
        query.append("INNER JOIN TAPGFIELD z 				       	");
        query.append("ON y.field_id = z.field_id					");
        query.append("AND x.file_name = z.file_name					");
        query.append("WHERE 1=1 									");
        query.append(this.getWhere(mgrAtCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
    
}