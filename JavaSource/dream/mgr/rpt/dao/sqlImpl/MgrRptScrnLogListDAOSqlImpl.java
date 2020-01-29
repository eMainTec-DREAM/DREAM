package dream.mgr.rpt.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.rpt.dao.MgrRptScrnLogListDAO;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;
import dream.mgr.rpt.dto.MgrRptScrnLogCommonDTO;

/**
 * 화면접속로그 Page - List DAO implements
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrRptScrnLogListDAOTarget"
 * @spring.txbn id="mgrRptScrnLogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrRptScrnLogListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements MgrRptScrnLogListDAO
{

    /**
     * grid find
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrRptScrnLogCommonDTO
     * @return List
     */
	public List findList(MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                                                                                                ");
        query.append("''                                                                                                               AS isDelCheck        ");
        query.append(",''                                                                                                              AS SeqNo             ");
        query.append(", a.comp_no                                                                                                      AS compNo            ");
        query.append(", c.login_date                                                                                                   AS LoginDate         ");
        query.append(", a.user_id                                                                                                      AS UserId            ");
        query.append(", c.user_no                                                                                                      AS UserNo            ");
        query.append(", c.user_name                                                                                                    AS UserName          ");
        query.append(", (SELECT c.key_name FROM TALANG c WHERE c.key_type = b.key_type AND c.key_no = b.key_no AND c.lang = 'ko')      AS MenuDesc          ");
        query.append(", b.menu_id                                                                                                      AS MenuId            ");
        query.append(", b.page_id                                                                                                      AS PageId            ");
        query.append(", b.file_name                                                                                                    AS fileName          ");
        query.append(", SUBSTRING(a.access_time, 1, 4)+'-'+SUBSTRING(a.access_time, 5, 2)+'-'+SUBSTRING(a.access_time, 7, 2)+' '+SUBSTRING(a.access_time, 9,2)+':'+SUBSTRING(a.access_time, 11,2)+':'+SUBSTRING(a.access_time, 13,2) AS LoginTime");
        query.append(" FROM TAACCESSCCLOG a INNER JOIN TAMENU b ON b.file_name = replace(replace(substring(a.file_name,  LEN(a.file_name) - CHARINDEX('/', reverse(a.file_name))+2,100),'.jsp',''),'.do','')                ");
        query.append(" INNER JOIN TAUSER c ON c.user_id = a.user_id             ");
        query.append(" WHERE 1=1                                                ");
        query.append(this.getWhere(mgrRptLoginLogCommonDTO, mgrRptScrnLogCommonDTO, user));
        query.getOrderByQuery("a.user_id","a.access_time desc", mgrRptScrnLogCommonDTO.getOrderBy(), mgrRptScrnLogCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrRptScrnLogCommonDTO.getIsLoadMaxCount(), mgrRptScrnLogCommonDTO.getFirstRow()));
    }

    
    /**
     * Filter 조건
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrRptScrnLogCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, User user)
    {        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        // ** filter
        //일자
        query.getAndDateQuery("c.login_date", mgrRptScrnLogCommonDTO.getFilterScrnFromDate(), mgrRptScrnLogCommonDTO.getFilterScrnToDate());
        //사용자
        query.getCodeLikeQuery("a.user_id", "c.user_name", mgrRptScrnLogCommonDTO.getFilterUserId(), mgrRptScrnLogCommonDTO.getFilterUserDesc());
        //메뉴
        if(!"".equals(mgrRptScrnLogCommonDTO.getFilterMenuId())) {
        	query.getLikeQuery("b.menu_id", mgrRptScrnLogCommonDTO.getFilterMenuId());
        }
        else if(!"".equals(mgrRptScrnLogCommonDTO.getFilterMenuDesc())){
        	query.append("AND b.key_no IN (SELECT c.key_no FROM TALANG c 			");
        	query.append("                          WHERE c.key_type ='MENU' 		");
        	query.append("                          AND c.lang = 'ko' 				");
        	query.getLikeQuery("c.key_name", mgrRptScrnLogCommonDTO.getFilterMenuDesc());
            query.append(")															");
        }
        
        //화면
        query.getLikeQuery("a.file_name", mgrRptScrnLogCommonDTO.getFilterScrnDesc());
        return query.toString();
    }
    
    /**
     * Filter 조건
     * @author  euna0207
     * @version $Id: mgrRptScrnLogListDAO.java,v 1.0 2018/08/23 15:19:40 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrRptScrnLogCommonDTO
     * @return
     * @throws Exception
     */

    public String findTotalCount(MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                               						 ");
        query.append("       COUNT(1)                      						 ");
        query.append(" FROM TAACCESSCCLOG a INNER JOIN TAMENU b ON b.file_name = replace(replace(substring(a.file_name,  LEN(a.file_name) - CHARINDEX('/', reverse(a.file_name))+2,100),'.jsp',''),'.do','')        		");
        query.append(" INNER JOIN TAUSER c ON c.user_id = a.user_id        		");
        query.append(" WHERE 1=1     											");
        query.append(this.getWhere(mgrRptLoginLogCommonDTO, mgrRptScrnLogCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
   
}
