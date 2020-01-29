package dream.mgr.rpt.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.rpt.dao.MgrRptLoginLogListDAO;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;

/**
 * 로그인로그 Page - List DAO implements
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrRptLoginLogListDAOTarget"
 * @spring.txbn id="mgrRptLoginLogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrRptLoginLogListDAOOraImpl  extends BaseJdbcDaoSupportOra implements MgrRptLoginLogListDAO
{

    /**
     * grid find
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrRptLoginLogCommonDTO
     * @return List
     */
    public List findList(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = mgrRptLoginLogCommonDTO.getCompNo();
        
        query.append("SELECT																											");
        query.append(" ''                																			AS isDelCheck    	");
        query.append(",''            																			    AS SeqNo			");
        query.append(", a.comp_no  																					AS compNo,			");
        //query.append(", a.login_date  																				AS LoginDate	");
        query.getDate("a.login_date", "LoginDate"																						 );
        query.append(", a.user_id 																					AS UserId			");
        query.append(", (SELECT user_no FROM TAUSER WHERE comp_no = a.comp_no AND user_id = a.user_id) 				AS UserNo			");
        query.append(", (SELECT user_name FROM TAUSER WHERE comp_no = a.comp_no AND user_id = a.user_id) 			AS UserName			");
        query.append(", (SELECT description 																							");
        query.append("    FROM TADEPT 																									");
        query.append("    WHERE comp_no = a.comp_no																						");
        query.append("    AND dept_id = (SELECT dept_id																					");
        query.append("                    FROM TAEMP																					");
        query.append("                    WHERE comp_no = a.comp_no																		");
        query.append("                    and emp_id = (SELECT emp_id 																	");
        query.append("                                    FROM TAUSER 																	");
        query.append("                                    WHERE comp_no = a.comp_no 													");
        query.append("                                    AND user_id = a.user_id)))    							AS deptDesc			");
        query.append(", a.login_time																			    AS LoginHour		");
        query.append(", a.logout_time 																			    AS LogoutHour		");
        query.append(", a.login_terminal 																			AS TerminalType		");
        query.append(", a.terminal_no 																				AS TerminalNo		");
        query.append(", a.terminal_ver 																				AS TerminalVer		");
        query.append(", a.logincclog_id 																			AS LoginccLogId		");
        query.append("FROM TALOGINCCLOG a																								");
        query.append("WHERE 1=1																											");
        query.append(this.getWhere(mgrRptLoginLogCommonDTO, user));
        query.getOrderByQuery("a.login_time desc", mgrRptLoginLogCommonDTO.getOrderBy(), mgrRptLoginLogCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrRptLoginLogCommonDTO.getIsLoadMaxCount(), mgrRptLoginLogCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrRptLoginLogCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        String compNo = mgrRptLoginLogCommonDTO.getCompNo();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        //key값 (한줄 row reload)
        if(!"".equals(mgrRptLoginLogCommonDTO.getLoginccLogId())){
            query.getAndQuery("a.logincclog_id", mgrRptLoginLogCommonDTO.getLoginccLogId());
            return query.toString();
        }
        // ** filter
        //일자
        query.getAndDateQuery("a.login_date", mgrRptLoginLogCommonDTO.getFilterLoginFromDate(), mgrRptLoginLogCommonDTO.getFilterLoginToDate());
        //사용자
        query.getCodeLikeQuery("a.user_id", "(SELECT user_name FROM TAUSER WHERE comp_no = a.comp_no AND user_id = a.user_id)", mgrRptLoginLogCommonDTO.getFilterUserId(), mgrRptLoginLogCommonDTO.getFilterUserDesc());
        //terminal type
        query.getSysCdQuery("a.login_terminal", mgrRptLoginLogCommonDTO.getFilterTerminalTypeId(), mgrRptLoginLogCommonDTO.getFilterTerminalTypeDesc(),"SERVICE_TYPE", compNo, user.getLangId());
        //terminal no
        query.getLikeQuery("a.terminal_no", mgrRptLoginLogCommonDTO.getFilterTerminalNo());
        //terminal ver
        query.getLikeQuery("a.logincclog_id", mgrRptLoginLogCommonDTO.getFilterTerminalVer());
        //부서
        query.getCodeLikeQuery("(SELECT dept_id  FROM TAEMP  WHERE comp_no = a.comp_no  and emp_id = (SELECT emp_id FROM TAUSER WHERE comp_no = a.comp_no AND user_id = a.user_id))", "(SELECT description FROM TADEPT WHERE comp_no = a.comp_no AND dept_id = (SELECT dept_id FROM TAEMP WHERE comp_no = a.comp_no and emp_id = (SELECT emp_id FROM TAUSER WHERE comp_no = a.comp_no AND user_id = a.user_id)))", mgrRptLoginLogCommonDTO.getFilterDeptId(), mgrRptLoginLogCommonDTO.getFilterDeptDesc());
        
        return query.toString();
    }

    /**
     * Filter 조건
     * @author  euna0207
     * @version $Id: mgrRptLoginLogListDAO.java,v 1.0 2017/08/22 15:20:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrRptLoginLogCommonDTO
     * @return
     * @throws Exception
     */

    public String findTotalCount(
            MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TALOGINCCLOG a					");
        query.append("WHERE 1=1 							");
        query.append(this.getWhere(mgrRptLoginLogCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    

}
