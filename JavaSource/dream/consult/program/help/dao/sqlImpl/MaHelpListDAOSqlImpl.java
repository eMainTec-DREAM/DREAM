package dream.consult.program.help.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.help.dao.MaHelpListDAO;
import dream.consult.program.help.dto.MaHelpCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maHelpListDAOTarget"
 * @spring.txbn id="maHelpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaHelpListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaHelpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maHelpCommonDTO
     * @return List
     */
    public List findHelpList(MaHelpCommonDTO maHelpCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 
        
        query.append("SELECT										");
        query.append("       '' seqNo     							");
        query.append("       ,'' isDelCheck     					");
        query.append("       ,y.helpdesk_no AS helpdeskNo,     		");
        query.getDate("y.req_date", "reqDate");
        query.append("       ,y.description AS description     		");
        query.append("       ,dbo.SFACODE_TO_DESC(y.helpdesk_status,'HELPDESK_STATUS','SYS','','"+user.getLangId()+"') AS helpdeskStatus 	");
        query.append("		 ,(SELECT emp_name						");
        query.append("		  FROM   TAEMP							");
        query.append("		  WHERE comp_no = y.comp_no				");
        query.append("		    AND emp_id = y.req_by)	reqBy,		");
        query.getDate("y.view_date", "viewDate");
        query.append("		 ,y.view_name	viewBy,		");
        query.getDate("y.work_sdate", "workSdate,");
        query.getDate("y.work_edate", "workEdate");
        query.append("		 ,y.work_name	workBy		");
        query.append("       ,y.helpdesk_id AS helpdeskId     		");
        query.append("FROM   TAHELPDESK y                       	");
        query.append("WHERE  1=1									");
        query.append(this.getWhere(maHelpCommonDTO, user));
        query.getOrderByQuery("y.helpdesk_id DESC","y.helpdesk_no DESC", maHelpCommonDTO.getOrderBy(), maHelpCommonDTO.getDirection());
        
//        return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(maHelpCommonDTO.getIsLoadMaxCount(), maHelpCommonDTO.getFirstRow()));

    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maHelpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaHelpCommonDTO maHelpCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getStringEqualQuery("y.comp_no", user.getCompNo());

        // Key No가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maHelpCommonDTO.getHelpdeskId()))
        {
            query.getAndQuery("y.helpdesk_id", maHelpCommonDTO.getHelpdeskId());
            return query.toString();
        }
        
        query.getAndDateQuery("y.req_date", maHelpCommonDTO.getReqDateFrom(), maHelpCommonDTO.getReqDateTo());
        query.getLikeQuery("y.description", maHelpCommonDTO.getDescription());
        query.getCodeLikeQuery("y.helpdesk_status", "dbo.SFACODE_TO_DESC(y.helpdesk_status,'HELPDESK_STATUS','SYS','','"+user.getLangId()+"')", maHelpCommonDTO.getHelpdeskStatus(), maHelpCommonDTO.getHelpdeskStatusDesc());
        query.getLikeQuery("y.perform", maHelpCommonDTO.getRequest());
       
        return query.toString();
    }

    /**
     * 삭제
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     */
    public int deleteHelp(String compNo, String helpdesk_id)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAHELPDESK                        ");
        query.append("WHERE  comp_no  	   = ?                 ");
        query.append("  AND  helpdesk_id   = ?                 ");

        Object[] objects = new Object[] {   
                compNo,
                helpdesk_id
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	public String findTotalCount(MaHelpCommonDTO maHelpCommonDTO, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT                    ");
        query.append("    COUNT(1)              ");
        query.append("FROM   TAHELPDESK y		");
        query.append("WHERE  1 = 1              ");
        query.append(this.getWhere(maHelpCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
        
	}
}