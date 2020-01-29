package dream.consult.comp.terminal.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.terminal.dao.ConsultCompTerminalListDAO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;

/**
 * 접근터미널 - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultCompTerminalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompTerminalListDAOTarget"
 * @spring.txbn id="consultCompTerminalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompTerminalListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompTerminalListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultCompTerminalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalCommonDTO
     * @return List
     */
    public List findTerminalList(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT                   										");
        query.append("        ''                      seqNo							");
        query.append("		 ,''                      isDelCheck					");
        query.append("		 ,x.comp_no               compNo						");
        query.append("       ,(SELECT aa.description                                ");
        query.append("          FROM TACOMP aa                                      ");
        query.append("         WHERE x.comp_no = aa.comp_no) compDesc        	    ");
        query.append("		 ,x.accessmnl_id          accessMnlId					");
        query.append("		 ,x.service_type          serviceTypeId					");
        query.append("		 ,SFACODE_TO_DESC(x.service_type,'SERVICE_TYPE','SYS'	");
        query.append("		 			,'','"+user.getLangId()+"') serviceTypeDesc	");
        query.append("		 ,x.terminal_no           terminalNo					");
        query.append("		 ,x.description           description					");
        query.append("		 ,x.is_use                isUseId						");
        query.append("		 ,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS'				");
        query.append("		 			,'','"+user.getLangId()+"') isUseDesc		");
        query.append("		 ,x.remark                remark						");
        query.append("FROM   TAACCESSMNL x        									");
        query.append("WHERE  1=1               										");
        query.append(this.getWhere(consultCompTerminalCommonDTO , user));
        query.getOrderByQuery("x.is_use desc, x.terminal_no", consultCompTerminalCommonDTO.getOrderBy(), consultCompTerminalCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultCompTerminalCommonDTO.getIsLoadMaxCount(), consultCompTerminalCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: ConsultCompTerminalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(consultCompTerminalCommonDTO.getAccessMnlId()) && !"".equals(consultCompTerminalCommonDTO.getCompNo()))
        {
            query.getAndQuery("x.accessmnl_id", consultCompTerminalCommonDTO.getAccessMnlId());
            query.getAndQuery("x.comp_no", consultCompTerminalCommonDTO.getCompNo());
            return query.toString();
        }
        if(!"".equals(consultCompTerminalCommonDTO.getFilterCompNo())){
        	query.getAndQuery("x.comp_no", consultCompTerminalCommonDTO.getFilterCompNo());
        }else if(!"".equals(consultCompTerminalCommonDTO.getFilterCompNoDesc())){
        	query.append("AND x.comp_no IN (SELECT a.comp_no	");
        	query.append("					FROM TACOMP a		");
        	query.append("					WHERE 1=1			");
        	query.getLikeQuery("a.description", consultCompTerminalCommonDTO.getFilterCompNoDesc());
        	query.append("					)					");
        }
        
        query.getLikeQuery("x.description", consultCompTerminalCommonDTO.getFilterDesc());
        //service type
    	query.getSysCdQuery("x.service_type", consultCompTerminalCommonDTO.getFilterServiceTypeId(), consultCompTerminalCommonDTO.getFilterServiceTypeDesc(), "SERVICE_TYPE", user.getCompNo(),user.getLangId());
        
        return query.toString();
    }

    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompTerminalListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteTerminal(String compNo, String acdessMnlId, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAACCESSMNL						");
    	query.append("WHERE 1=1                  					");
    	query.append("  AND comp_no 		='"+compNo+"'			");
    	query.append("  AND accessmnl_id 	="+acdessMnlId+"		");

    	return this.getJdbcTemplate().update(query.toString());
    }

	public String findTotalCount(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAACCESSMNL x        								");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(consultCompTerminalCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}