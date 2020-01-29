package dream.consult.program.help.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.help.dao.MaHelpDetailDAO;
import dream.consult.program.help.dto.MaHelpCommonDTO;
import dream.consult.program.help.dto.MaHelpDetailDTO;

/**
 * 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maHelpDetailDAOTarget"
 * @spring.txbn id="maHelpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaHelpDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaHelpDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param empId
     * @return
     */
    public MaHelpDetailDTO findDetail(MaHelpCommonDTO maHelpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        User user = maHelpCommonDTO.getLoginUser();
        
        query.append("SELECT										");
        query.append("       y.helpdesk_no AS helpdeskNo     		");
        query.append("       ,y.req_date AS reqDate     			");
        query.append("       ,y.description AS description     		");
        query.append("       ,y.helpdesk_status AS helpdeskStatus 	");
        query.append("       ,dbo.SFACODE_TO_DESC(y.helpdesk_status,'HELPDESK_STATUS','SYS','','"+user.getLangId()+"') AS helpdeskStatusDesc 	");
        query.append("       ,y.req_by AS reqBy     				");
        query.append("		 ,(SELECT emp_name						");
        query.append("		  FROM   TAEMP							");
        query.append("		  WHERE comp_no = y.comp_no				");
        query.append("		    AND emp_id = y.req_by)	reqByName 	");
        query.append("       ,y.view_date AS viewDate      			");
        query.append("		 ,y.view_name	viewByName	           ");
        query.append("       ,y.work_sdate AS workSdate     		");
        query.append("       ,y.work_edate AS workEdate     		");
        query.append("       ,y.request              				");
        query.append("		 ,y.work_name 	workByName	            ");
        query.append("       ,y.perform AS perform             		");
        query.append("       ,y.comp_no AS compNo             		");
        query.append("       ,y.helpdesk_id AS helpdeskId     		");
        query.append("FROM   TAHELPDESK y                           ");
        query.append("WHERE  1=1     								");
        
        query.getStringEqualQuery("y.comp_no", maHelpCommonDTO.getCompNo());
        query.append("  AND  y.helpdesk_id  = '"+maHelpCommonDTO.getHelpdeskId()+"'	");
    
        MaHelpDetailDTO maHelpDetailDTO = 
        		(MaHelpDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaHelpDetailDTO()));
        
        return maHelpDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maHelpDetailDTO
     * @return
     */
    public int insertDetail(MaHelpDetailDTO maHelpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAHELPDESK								");
    	query.append("(     												");
    	query.append("          comp_no     					            ");
    	query.append("         ,helpdesk_id  ,helpdesk_no  	,helpdesk_status");
    	query.append("         ,description  ,request  	   	,req_date     	");
    	query.append("         ,req_by  	         	             	    ");
    	query.append(") VALUES (     										");
    	query.append("         ?                 							");
    	query.append("        ,?      		,?      		,?     			");
    	query.append("        ,?      		,?      		,?     			");
    	query.append("        ,?      		        		     			");
    	query.append(")     												");

    	
    	Object[] objects = new Object[] {
    			maHelpDetailDTO.getCompNo(),
    			maHelpDetailDTO.getHelpdeskId(),
    			maHelpDetailDTO.getHelpdeskNo(),
    			maHelpDetailDTO.getHelpdeskStatus(),
    			maHelpDetailDTO.getDescription(),
    			maHelpDetailDTO.getRequest(),
    			maHelpDetailDTO.getReqDate(),
    			maHelpDetailDTO.getReqBy()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maHelpDetailDTO
     * @return
     */
    public int updateDetail(MaHelpDetailDTO maHelpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAHELPDESK SET        ");
        query.append("       helpdesk_status  = ?,  ");
        query.append("       description  = ?,  ");
        query.append("       request      = ?,  ");
        query.append("       req_by       = ?,  ");
        query.append("       req_date     = ?,  ");
        query.append("       perform      = ?,  ");
        query.append("       view_date    = ?,  ");
        query.append("       view_name    = ?,  ");
        query.append("       work_sdate   = ?,  ");
        query.append("       work_edate   = ?,  ");
        query.append("       work_name    = ?   ");
        query.append("WHERE  comp_no      = ?   ");
        query.append("  AND  helpdesk_id  = ?   ");
        
        Object[] objects = new Object[] {
                maHelpDetailDTO.getHelpdeskStatus(),
                maHelpDetailDTO.getDescription(),
                maHelpDetailDTO.getRequest(),
                maHelpDetailDTO.getReqBy(),
                maHelpDetailDTO.getReqDate(),
                maHelpDetailDTO.getPerform(),
                maHelpDetailDTO.getViewDate(),
                maHelpDetailDTO.getViewByName(),
                maHelpDetailDTO.getWorkSdate(),
                maHelpDetailDTO.getWorkEdate(),
                maHelpDetailDTO.getWorkByName(),
                maHelpDetailDTO.getCompNo(),
                maHelpDetailDTO.getHelpdeskId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
  
		
    /**
     * 요청
     * @param maHelpDetailDTO
     */
    public void insertGowrkimp(MaHelpDetailDTO maHelpDetailDTO) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAGOWRKIMP (                              ");
        query.append("        comp_no, gowrkimp_id, gowrkimp_no,            ");
        query.append("        gowrkimp_status, description, helpdesk_id,     ");
        query.append("        wrkimp_cre_type, wrkimp_site, write_date,      ");
        query.append("        write_name                                    ");
        query.append(" )   ");
        query.append("SELECT comp_no                                        ");
        query.append("       ,?                                             ");
        query.append("       ,?                                             ");
        query.append("       ,'WT'                                          ");
        query.append("       ,description                                   ");
        query.append("       ,helpdesk_id                                   ");
        query.append("       ,'HD'                                          ");
        query.append("       ,(select aa.description from tacomp aa where  aa.comp_no = x.comp_no) as wrkimp_site ");
        query.append("       ,x.req_date                                    ");
        query.append("       ,(select aa.emp_name from taemp aa where  aa.comp_no = x.comp_no and aa.emp_id = x.req_by) as write_name ");
        query.append("FROM   TAHELPDESK x                                   ");
        query.append("WHERE  helpdesk_id = ?                                ");
        query.append("  AND  comp_no = ?                                    ");
        
        Object[] objects = new Object[] {
                maHelpDetailDTO.getGowrkimpId(),
                maHelpDetailDTO.getGowrkimpNo(),
                maHelpDetailDTO.getHelpdeskId(),
                maHelpDetailDTO.getCompNo()
        };
		
    	this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}
    @Override
    public void updateGowrkimp(MaHelpDetailDTO maHelpDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAGOWRKIMP SET         ");
        query.append("       gowrkimp_status  = ?,  ");
        query.append("       description      = ?,  ");
        query.append("       perform          = ?,  ");
        query.append("       view_date        = ?,  ");
        query.append("       view_name        = ?,  ");
        query.append("       work_sdate       = ?,  ");
        query.append("       work_edate       = ?,  ");
        query.append("       work_name        = ?   ");
        query.append("WHERE  comp_no          = ?   ");
        query.append("  AND  helpdesk_id      = ?   ");
        
        Object[] objects = new Object[] {
                maHelpDetailDTO.getGowrkimpStatus(),
                maHelpDetailDTO.getDescription(),
                maHelpDetailDTO.getPerform(),
                maHelpDetailDTO.getViewDate(),
                maHelpDetailDTO.getViewByName(),
                maHelpDetailDTO.getWorkSdate(),
                maHelpDetailDTO.getWorkEdate(),
                maHelpDetailDTO.getWorkByName(),
                maHelpDetailDTO.getCompNo(),
                maHelpDetailDTO.getHelpdeskId()
        };
        
        this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}