package dream.consult.program.onlinehelp.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.onlinehelp.dao.ConsultProgramOnlinehelpListDAO;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpCommonDTO;

/**
 * 도움말 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="consultProgramOnlinehelpListDAOTarget"
 * @spring.txbn id="consultProgramOnlinehelpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultProgramOnlinehelpListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultProgramOnlinehelpListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();

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
        query.getOrderByQuery("a.onlinehelp_id","a.comp_no, a.file_name", consultProgramOnlinehelpCommonDTO.getOrderBy(), consultProgramOnlinehelpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultProgramOnlinehelpCommonDTO.getIsLoadMaxCount(), consultProgramOnlinehelpCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDTOList
     * @return
     */
    public int deleteHelp(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
        
        query.append("DELETE FROM TAONLINEHELP              ");
        query.append("WHERE onlinehelp_id = '"+id+"'        ");
        
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if (!"".equals(consultProgramOnlinehelpCommonDTO.getOnlineHelpId()))
        {
            query.getAndQuery("a.onlinehelp_id", consultProgramOnlinehelpCommonDTO.getOnlineHelpId());
            return query.toString();
        }
        query.getLikeQuery("a.file_name", consultProgramOnlinehelpCommonDTO.getFilterFileName());
        query.getLikeQuery("a.description", consultProgramOnlinehelpCommonDTO.getFilterTitle());
        query.getLikeQuery("a.contents", consultProgramOnlinehelpCommonDTO.getFilterContents());
        
        if(!"".equals(consultProgramOnlinehelpCommonDTO.getFilterMenuId()) ) {
            query.append("AND a.file_name IN(SELECT file_name FROM TAPAGE       ");
            query.append("                          WHERE page_id IN(SELECT page_id FROM dbo.SFAPGPAGEINMENU_ALL("+consultProgramOnlinehelpCommonDTO.getFilterMenuId()+") )       ");
            query.append("                          )     ");
        }
        
        return query.toString();
    }
    
    public String findTotalCount(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM TAONLINEHELP a       ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(consultProgramOnlinehelpCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}