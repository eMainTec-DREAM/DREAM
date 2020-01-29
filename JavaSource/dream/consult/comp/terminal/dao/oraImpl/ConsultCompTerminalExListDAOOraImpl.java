package dream.consult.comp.terminal.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.terminal.dao.ConsultCompTerminalExListDAO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;

/**
 * 접근터미널 - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultCompTerminalExListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompTerminalExListDAOTarget"
 * @spring.txbn id="consultCompTerminalExListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompTerminalExListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompTerminalExListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultCompTerminalExListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExCommonDTO
     * @return List
     */
    public List findTerminalExList(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT                   										");
        query.append("        ''                      seqNo							");
        query.append("		 ,''                      isDelCheck					");
        query.append("		 ,x.comp_no               compNo						");
        query.append("       ,(SELECT aa.description                                ");
        query.append("          FROM TACOMP aa                                      ");
        query.append("         WHERE x.comp_no = aa.comp_no) compDesc        	    ");
        query.append("		 ,x.accessmnlex_id          accessMnlExId				");
        query.append("		 ,x.service_type          serviceTypeId					");
        query.append("		 ,SFACODE_TO_DESC(x.service_type,'SERVICE_TYPE','SYS'	");
        query.append("		 			,'','"+user.getLangId()+"') serviceTypeDesc	");
        query.append("		 ,x.terminal_no           terminalNo					");
        query.append("		 ,x.description           description					");
        query.append("		 ,x.is_use                isUseId						");
        query.append("		 ,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS'				");
        query.append("		 			,'','"+user.getLangId()+"') isUseDesc		");
        query.append("		 ,x.remark                remark						");
        query.append("FROM   TAACCESSMNLEX x        								");
        query.append("WHERE  1=1               										");
        query.append(this.getWhere(consultCompTerminalExCommonDTO , user));
        query.getOrderByQuery("x.is_use desc, x.terminal_no", consultCompTerminalExCommonDTO.getOrderBy(), consultCompTerminalExCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultCompTerminalExCommonDTO.getIsLoadMaxCount(), consultCompTerminalExCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: ConsultCompTerminalExListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(consultCompTerminalExCommonDTO.getAccessMnlExId()) && !"".equals(consultCompTerminalExCommonDTO.getCompNo()))
        {
            query.getAndQuery("x.accessmnlex_id", consultCompTerminalExCommonDTO.getAccessMnlExId());
            query.getAndQuery("x.comp_no", consultCompTerminalExCommonDTO.getCompNo());
            return query.toString();
        }
        if(!"".equals(consultCompTerminalExCommonDTO.getFilterCompNo())){
        	query.getAndQuery("x.comp_no", consultCompTerminalExCommonDTO.getFilterCompNo());
        }else if(!"".equals(consultCompTerminalExCommonDTO.getFilterCompNoDesc())){
        	query.append("AND x.comp_no IN (SELECT a.comp_no	");
        	query.append("					FROM TACOMP a		");
        	query.append("					WHERE 1=1			");
        	query.getLikeQuery("a.description", consultCompTerminalExCommonDTO.getFilterCompNoDesc());
        	query.append("					)					");
        }
        
        query.getLikeQuery("x.description", consultCompTerminalExCommonDTO.getFilterDesc());
        //service type
    	query.getSysCdQuery("x.service_type", consultCompTerminalExCommonDTO.getFilterServiceTypeId(), consultCompTerminalExCommonDTO.getFilterServiceTypeDesc(), "SERVICE_TYPE", user.getCompNo(),user.getLangId());
        
        return query.toString();
    }

    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompTerminalExListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteTerminal(String compNo, String acdessMnlExId, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAACCESSMNLEx						");
    	query.append("WHERE 1=1                  					");
    	query.append("  AND comp_no 		='"+compNo+"'	        ");
    	query.append("  AND accessmnlex_id 	="+acdessMnlExId+"		");

    	return this.getJdbcTemplate().update(query.toString());
    }

	public String findTotalCount(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                        ");
        query.append("       COUNT(1)               ");
        query.append("FROM   TAACCESSMNLEx x		");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(consultCompTerminalExCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}