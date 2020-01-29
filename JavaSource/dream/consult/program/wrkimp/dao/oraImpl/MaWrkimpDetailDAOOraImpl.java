package dream.consult.program.wrkimp.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.wrkimp.dao.MaWrkimpDetailDAO;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;
import dream.consult.program.wrkimp.dto.MaWrkimpDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maWrkimpDetailDAOTarget"
 * @spring.txbn id="maWrkimpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWrkimpDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWrkimpDetailDAO
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
    public MaWrkimpDetailDTO findDetail(MaWrkimpCommonDTO maWrkimpCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        		");
        query.append("       '' seqNo                                       ");
        query.append("       ,'' isDelCheck         						");
        query.append("       ,x.gowrkimp_id AS gowrkimpId                  	");
        query.append("       ,x.gowrkimp_no gowrkimpNo            			");
        query.append("       ,x.description           						");
        query.append("       ,x.gowrkimp_status gowrkimpStatus             	");
        query.append("       ,SFACODE_TO_DESC(x.gowrkimp_status,'GOWRKIMP_STATUS','SYS','','"+user.getLangId()+"') AS gowrkimpStatusDesc         ");
        query.append("       ,x.write_name writeByName     						");
        query.append("       ,x.write_date writeDate     					");
        query.append("       ,x.view_name  viewByName                       ");
        query.append("       ,x.view_date viewDate     						");
        query.append("       ,x.work_sdate workSdate     					");
        query.append("       ,x.work_edate workEdate     					");
        query.append("       ,x.work_name  workByName                       ");
        
        
        query.append("       ,y.helpdesk_no AS helpdeskNo             		");
        query.append("       ,y.helpdesk_status helpdeskStatus     ");
        query.append("       ,SFACODE_TO_DESC(y.helpdesk_status,'HELPDESK_STATUS','SYS','','"+user.getLangId()+"') AS helpdeskStatus          ");
        query.append("       ,y.req_by      reqBy							");
        query.append("       ,(SELECT aa.emp_name                           ");
        query.append("         FROM   TAEMP aa                              ");
        query.append("         WHERE aa.comp_no = y.comp_no                 ");
        query.append("            AND aa.emp_id = y.req_by)    reqByName    ");
        query.append("       ,y.req_date reqDate          					");
        query.append("       ,y.request      								");
        query.append("       ,x.helpdesk_id AS helpdeskId                  	");
        query.append("       ,x.comp_No     AS compNo                    	");
        
        query.append("       ,x.work_time                   as workImpTime                                                     ");
        query.append("       ,x.perform                     as perform                                                      ");
        query.append("       ,x.wrkimp_cre_type             as wrkimpCreTypeId                                              ");
        query.append("       ,SFACODE_TO_DESC(x.wrkimp_cre_type,'WRKIMP_CRE_TYPE','SYS','','"+user.getLangId()+"') as wrkimpCreTypeDesc       ");
        query.append("       ,x.wrkimp_site                 as wrkimpSite                                                   ");
        
        
        query.append("FROM   TAGOWRKIMP x left outer join TAHELPDESK y on x.comp_no = y.comp_no and x.helpdesk_id = y.helpdesk_id                  	");
        query.append("WHERE  1=1             	                         ");
        query.append("  AND  x.gowrkimp_id  = '"+maWrkimpCommonDTO.getGowrkimpId()+"'	");
    
        MaWrkimpDetailDTO maWrkimpDetailDTO = 
        		(MaWrkimpDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWrkimpDetailDTO()));
        
        return maWrkimpDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWrkimpDetailDTO
     * @return
     */
    public int insertDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAGOWRKIMP								");
    	query.append("(     												");
    	query.append("          helpdesk_id  ,gowrkimp_id     				");
    	query.append("         ,gowrkimp_no  ,gowrkimp_status  ,description ");
    	query.append("           ,write_date  ,write_Name     		");
    	query.append("         ,view_date  ,view_Name  ,work_sdate     		");
    	query.append("         ,work_edate  ,work_Name  ,comp_no     		");
    	query.append("         ,work_time  ,perform  ,wrkimp_cre_type    	");
    	query.append("         ,wrkimp_site                        			");
    	query.append(") VALUES (     										");
    	query.append("         ?      		,?     							");
    	query.append("        ,?      		,?      		,?     			");
    	query.append("             		,?      		,?     			");
    	query.append("        ,?      		,?      		,?     			");
    	query.append("        ,?      		,?      		,?     			");
    	query.append("        ,?      		,?      		,?     			");
    	query.append("        ,?      	           			                ");
    	query.append(")     												");

    	
    	Object[] objects = new Object[] {
    			maWrkimpDetailDTO.getHelpdeskId()
    			,maWrkimpDetailDTO.getGowrkimpId()
    			,maWrkimpDetailDTO.getGowrkimpNo()
    			,maWrkimpDetailDTO.getGowrkimpStatus()
    			,maWrkimpDetailDTO.getDescription()
    			,maWrkimpDetailDTO.getWriteDate()
    			,maWrkimpDetailDTO.getWriteByName()
    			,maWrkimpDetailDTO.getViewDate()
    			,maWrkimpDetailDTO.getViewByName()
    			,maWrkimpDetailDTO.getWorkSdate()
    			,maWrkimpDetailDTO.getWorkEdate()
    			,maWrkimpDetailDTO.getWorkByName()
    			,maWrkimpDetailDTO.getCompNo()
    			,maWrkimpDetailDTO.getWorkImpTime()
    			,maWrkimpDetailDTO.getPerform()
    			,maWrkimpDetailDTO.getWrkimpCreTypeId()
    			,maWrkimpDetailDTO.getWrkimpSite()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWrkimpDetailDTO
     * @return
     */
    public int updateHelpDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAHELPDESK SET	    		");
    	query.append("	     helpdesk_status  	= ?		");
    	query.append("      ,view_date			= ? 	");
    	query.append("      ,view_name			= ?     ");
    	query.append("      ,work_sdate			= ?     ");
    	query.append("      ,work_edate			= ?     ");
    	query.append("      ,work_name			= ?     ");
    	query.append("      ,perform            = ?     ");
    	query.append("WHERE  comp_no      = ?	");
    	query.append("  AND  helpdesk_id  = ?	");
    	
    	Object[] objects = new Object[] {
    			maWrkimpDetailDTO.getHelpdeskStatus(),
    			maWrkimpDetailDTO.getViewDate()
    			,maWrkimpDetailDTO.getViewByName()
    			,maWrkimpDetailDTO.getWorkSdate()
    			,maWrkimpDetailDTO.getWorkEdate()
    			,maWrkimpDetailDTO.getWorkByName()
    			,maWrkimpDetailDTO.getPerform()
    			,maWrkimpDetailDTO.getCompNo()
    			,maWrkimpDetailDTO.getHelpdeskId()
    	};
    	
    	
    	
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updateDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAGOWRKIMP SET	    		");
    	query.append("        gowrkimp_no		= ?     ");
    	query.append("       ,gowrkimp_status 	= ?     ");
    	query.append("       ,description		= ?     ");
    	query.append("       ,write_date		= ?     ");
    	query.append("       ,write_name		= ?     ");
    	query.append("       ,view_date			= ?     ");
    	query.append("       ,view_name			= ?     ");
    	query.append("       ,work_sdate		= ?     ");
    	query.append("       ,work_edate		= ?     ");
    	query.append("       ,work_name			= ?     ");
    	query.append("      ,work_time			= ?     ");
    	query.append("      ,perform			= ?     ");
    	query.append("      ,wrkimp_cre_type	= ?     ");
    	query.append("      ,wrkimp_site		= ?     ");

    	query.append("WHERE  1=1  		                ");
    	query.append("  AND  gowrkimp_id  		= ?		");
    	
    	Object[] objects = new Object[] {
    			maWrkimpDetailDTO.getGowrkimpNo()
    			,maWrkimpDetailDTO.getGowrkimpStatus()
    			,maWrkimpDetailDTO.getDescription()
    			,maWrkimpDetailDTO.getWriteDate()
    			,maWrkimpDetailDTO.getWriteByName()
    			,maWrkimpDetailDTO.getViewDate()
    			,maWrkimpDetailDTO.getViewByName()
    			,maWrkimpDetailDTO.getWorkSdate()
    			,maWrkimpDetailDTO.getWorkEdate()
    			,maWrkimpDetailDTO.getWorkByName()
    			,maWrkimpDetailDTO.getWorkImpTime()
    			,maWrkimpDetailDTO.getPerform()
    			,maWrkimpDetailDTO.getWrkimpCreTypeId()
    			,maWrkimpDetailDTO.getWrkimpSite()
    			,maWrkimpDetailDTO.getGowrkimpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
}