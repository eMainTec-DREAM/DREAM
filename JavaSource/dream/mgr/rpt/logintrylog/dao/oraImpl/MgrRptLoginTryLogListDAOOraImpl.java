package dream.mgr.rpt.logintrylog.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.rpt.logintrylog.dao.MgrRptLoginTryLogListDAO;
import dream.mgr.rpt.logintrylog.dto.MgrRptLoginTryLogCommonDTO;

/**
 * 로그인 시도 로그 Page - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrRptLoginTryLogListDAOTarget"
 * @spring.txbn id="mgrRptLoginTryLogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrRptLoginTryLogListDAOOraImpl  extends BaseJdbcDaoSupportOra implements MgrRptLoginTryLogListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrRptLoginTryLogCommonDTO
     * @return List
     */
    public List findList(MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 								");
        query.append("      '' 					seqNo		");
        query.append("    ,									");
        query.getDate("x.login_date", "loginDate");
        query.append("    , x.user_no     		userNo		");
        query.append("    , SFACODE_TO_DESC(x.login_terminal, 'SERVICE_TYPE', 'SYS', x.comp_no,'"+user.getLangId()+"') 	tmlTypeDesc	");
        query.append("    , x.terminal_no 		tmlNo		");
        query.append("    , x.terminal_ver    	tmlVerDesc	");
        query.append("    ,									");
        query.getTime("x.login_time", "loginTime");
        query.append("    , SFACODE_TO_DESC(x.is_success, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') 	isSuccessDesc	");
        query.append("FROM TALOGINTRYLOG x 					");
        query.append("WHERE 1=1								");
        query.append(this.getWhere(mgrRptLoginTryLogCommonDTO, user));
        query.getOrderByQuery("x.login_date DESC, x.login_time DESC", mgrRptLoginTryLogCommonDTO.getOrderBy(), mgrRptLoginTryLogCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrRptLoginTryLogCommonDTO.getIsLoadMaxCount(), mgrRptLoginTryLogCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrRptLoginTryLogCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());

        //일자
        query.getAndDateQuery("x.login_date", mgrRptLoginTryLogCommonDTO.getFilterLoginFromDate(), mgrRptLoginTryLogCommonDTO.getFilterLoginToDate());
        
        //사용자
        query.getLikeQuery("x.user_no", mgrRptLoginTryLogCommonDTO.getFilterUserId());
        
        //terminal type
        query.getSysCdQuery("x.login_terminal", mgrRptLoginTryLogCommonDTO.getFilterTerminalTypeId(), mgrRptLoginTryLogCommonDTO.getFilterTerminalTypeDesc(),"SERVICE_TYPE", user.getCompNo(), user.getLangId());
        
        //terminal no
        query.getLikeQuery("x.terminal_no", mgrRptLoginTryLogCommonDTO.getFilterTerminalNo());
        
        //terminal ver
        query.getLikeQuery("x.terminal_ver", mgrRptLoginTryLogCommonDTO.getFilterTerminalVer());
        
        //성공여부
        query.getSysCdQuery("x.is_success", mgrRptLoginTryLogCommonDTO.getFilterIsSuccess(), mgrRptLoginTryLogCommonDTO.getFilterIsSuccessDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrRptLoginTryLogCommonDTO
     * @return
     * @throws Exception
     */

    public String findTotalCount(
            MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TALOGINTRYLOG x 					");
        query.append("WHERE 1=1 							");
        query.append(this.getWhere(mgrRptLoginTryLogCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    

}
