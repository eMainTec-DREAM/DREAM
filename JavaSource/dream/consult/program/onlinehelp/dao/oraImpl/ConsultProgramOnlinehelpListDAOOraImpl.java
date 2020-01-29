package dream.consult.program.onlinehelp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.onlinehelp.dao.ConsultProgramOnlinehelpListDAO;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpCommonDTO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;

/**
 * 도움말 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="consultProgramOnlinehelpListDAOTarget"
 * @spring.txbn id="consultProgramOnlinehelpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultProgramOnlinehelpListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultProgramOnlinehelpListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpCommonDTO
     * @return List
     */
    public List findHelpList(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    ''      ISDELCHECK        ");
        query.append("    ,''     SEQNO     ");
        query.append("    ,a.file_name             fileName     ");
        query.append("    ,(SELECT description FROM TAPAGE WHERE file_name=a.file_name)     pageDesc        ");
        query.append("    ,(SELECT description FROM TACOMP WHERE comp_no=a.comp_no)      compDesc       ");
        query.append("    ,a.description           description      ");
        query.append("    ,a.upd_date             updDate       ");
        query.append("    ,a.emp_name          empName      ");
        query.append("    ,a.onlinehelp_id        onlinehelpId      ");
        query.append("    ,a.comp_no              compNo        ");
        query.append("FROM TAONLINEHELP a       ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(consultProgramOnlinehelpCommonDTO, user));
        query.getOrderByQuery("a.comp_no, a.file_name", consultProgramOnlinehelpCommonDTO.getOrderBy(), consultProgramOnlinehelpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultProgramOnlinehelpCommonDTO.getIsLoadMaxCount(), consultProgramOnlinehelpCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteHelp(String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TAONLINEHELP				");
    	query.append("WHERE onlinehelp_id = '"+id+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(consultProgramOnlinehelpCommonDTO.getOnlineHelpId()))
        {
            query.getAndQuery("a.onlinehelp_id", consultProgramOnlinehelpCommonDTO.getOnlineHelpId());
            return query.toString();
        }
        query.getLikeQuery("a.file_name", consultProgramOnlinehelpCommonDTO.getFilterFileName());
        query.getLikeQuery("a.description", consultProgramOnlinehelpCommonDTO.getFilterTitle());
        query.getLikeQuery("a.contents", consultProgramOnlinehelpCommonDTO.getFilterContents());
        
        if(!"".equals(consultProgramOnlinehelpCommonDTO.getFilterMenuId()) || !"".equals(consultProgramOnlinehelpCommonDTO.getFilterMenuDesc())) {
            query.append("AND a.file_name IN(SELECT file_name FROM TAPAGE       ");
            query.append("                          WHERE page_id IN(SELECT DISTINCT page_id FROM TAMENU        ");
            if(!"".equals(consultProgramOnlinehelpCommonDTO.getFilterMenuId()))
                query.append("                                             START WITH menu_id="+consultProgramOnlinehelpCommonDTO.getFilterMenuId()+"        ");
            else if(!"".equals(consultProgramOnlinehelpCommonDTO.getFilterMenuDesc()))
                query.append("                                             START WITH description LIKE UPPER('%"+consultProgramOnlinehelpCommonDTO.getFilterMenuDesc()+"%')        ");
            query.append("                                                 CONNECT BY PRIOR menu_id=p_menu_id        ");
            query.append("                                                 )     ");
            query.append("                          )     ");
        }
        
        return query.toString();
    }
    
    public String findTotalCount(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM TAONLINEHELP a       ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(consultProgramOnlinehelpCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}