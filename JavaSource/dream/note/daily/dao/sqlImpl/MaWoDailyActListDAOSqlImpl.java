package dream.note.daily.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.note.daily.dao.MaWoDailyActListDAO;
import dream.note.daily.dto.MaWoDailyActListDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * 일일작업 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoDailyActListDAOTarget"
 * @spring.txbn id="maWoDailyActListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDailyActListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoDailyActListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyCommonDTO
     * @return List
     */
    public List findList(MaWoDailyCommonDTO maWoDailyCommonDTO,MaWoDailyActListDTO maWoDailyActListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
            query.append("SELECT												");
            query.append("        ''						seqNo,				");
            query.append("        ''						isDelCheck,			");
            query.append("        x.wodayact_id				woDayActId,			");
            query.append("        x.eq_name					equipDesc,			");
            query.append("        x.act_contents			actContents,		");
            query.append("        x.action					action,				");
            query.append("        (SELECT emp_name								");
            query.append("         FROM TAEMP									");
            query.append("        WHERE 1=1										");
            query.append("          AND comp_no = x.comp_no						");
            query.append("          AND emp_id = x.emp_id) empName				");
            query.append("FROM TAWODAYACT x 									");
            query.append("WHERE 1=1												");
            query.append(this.getWhere(maWoDailyCommonDTO,maWoDailyActListDTO,user));
            query.append("ORDER BY x.ord_no										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    

    public int deleteList(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAWODAYACT               ");
        query.append("WHERE wodayact_id     = "+id+"       ");
        query.append("  AND comp_no  = '"+compNo+"'        ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }
    private String getWhere(MaWoDailyCommonDTO maWoDailyCommonDTO,MaWoDailyActListDTO maWoDailyActListDTO, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.wodaylist_id", maWoDailyCommonDTO.getWoDayListId());
        
        if (!"".equals(maWoDailyActListDTO.getWoDayActId()))
        {
            query.getAndQuery("x.wodayact_id", maWoDailyActListDTO.getWoDayActId());
            return query.toString();
        }
        
        return query.toString();
    }
    
}