package dream.mgr.mail.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.mail.dao.MaMailDetailDAO;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailDetailDTO;

/**
 * 메일수신자설정 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maMailDetailDAOTarget"
 * @spring.txbn id="maMailDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMailDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaMailDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @return
     */
    public MaMailDetailDTO findDetail(MaMailCommonDTO maMailCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = maMailCommonDTO.getUserLang();
        
        query.append("SELECT									");
        query.append("       x.mail_list_id 		AS mailListId,		");
        query.append("       x.mail_list_no 		AS mailListNo,		");
        query.append("       x.description  		AS mailDesc,    	");
        query.append("       x.remark				AS remark,			");
        query.append("       x.cycle				AS cycle,			");
        query.append("       x.time_type			AS timeType,		");
        query.append("		 dbo.SFACODE_TO_DESC(x.time_type,'TIME_TYPE','SYS','','"+lang+"') AS timeTypeDesc,			");
        query.append("       x.method_type			AS methodType,		");
        query.append("		 dbo.SFACODE_TO_DESC(x.method_type,'METHOD_TYPE','SYS','','"+lang+"') AS methodTypeDesc,	");
        query.append("       x.mail_scope_type		AS mailScopeTypeId,	");
        query.append("		 dbo.SFACODE_TO_DESC(x.mail_scope_type,'MAIL_SCOPE_TYPE','SYS','','"+lang+"') AS mailScopeTypeDesc,	");
        query.append("       x.is_use				AS isActive,		");
        query.append("       x.script				AS script,			");
        query.append("       x.start_exe_date		AS startExeDate,	");
        query.append("       x.start_exe_time		AS startExeTime,	");
        query.append("       x.last_sch_time		AS lastSchTime, 	");
        query.append("       x.title_script			AS titleScript,		");
        query.append("       x.target_script		AS targetScript,	");
        query.append("       x.scope_script			AS scopeScript		");
        query.append("FROM   TAMAILLIST x								");
        query.append("WHERE  1=1										");
        query.append("  AND  x.mail_list_id = ?							");
    
        Object[] objects = new Object[] {
        		maMailCommonDTO.getMailListId()
    	};
        
        MaMailDetailDTO maMailDetailDTO = 
        		(MaMailDetailDTO)getJdbcTemplate().query(query.toString(), getObject(objects), new MwareExtractor(new MaMailDetailDTO()));
        
        return maMailDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailDTO
     * @return
     */
    public int insertDetail(MaMailDetailDTO maMailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAMAILLIST							");
    	query.append("	(comp_no,		mail_list_id,	mail_list_no,	");
    	query.append("	 description,	remark,			cycle,			");
    	query.append("	 time_type,		method_type,	is_use,			");
    	query.append("	 script,        title_script,   start_exe_date,	");
    	query.append("	 start_exe_time,mail_scope_type,target_script,  ");
    	query.append("	 scope_script                             	    ");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,             ?,              ?,				");
    	query.append("	 ?,             ?,              ?,				");
    	query.append("	 ?                              				");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			maMailDetailDTO.getCompNo()
    			,maMailDetailDTO.getMailListId()
    			,maMailDetailDTO.getMailListNo()
    			,maMailDetailDTO.getMailDesc()
    			,maMailDetailDTO.getRemark()
    			,maMailDetailDTO.getCycle()
    			,maMailDetailDTO.getTimeType()
    			,maMailDetailDTO.getMethodType()
    			,maMailDetailDTO.getIsActive()
    			,maMailDetailDTO.getScript()
    			,maMailDetailDTO.getTitleScript()
    			,maMailDetailDTO.getStartExeDate()
    			,maMailDetailDTO.getStartExeTime()
    			,maMailDetailDTO.getMailScopeTypeId()
    			,maMailDetailDTO.getTargetScript()
    			,maMailDetailDTO.getScopeScript()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailDTO
     * @return
     */
    public int updateDetail(MaMailDetailDTO maMailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAMAILLIST SET			");
    	query.append("	mail_list_no		= ? 	");
    	query.append("	,description		= ? 	");
    	query.append("	,remark				= ? 	");
    	query.append("	,cycle				= ? 	");
    	query.append("	,time_type			= ? 	");
    	query.append("	,method_type		= ? 	");
    	query.append("	,is_use				= ? 	");
    	query.append("	,title_script		= ? 	");
    	query.append("	,script				= ?		");
    	query.append("	,start_exe_date		= ?		");
    	query.append("	,start_exe_time		= ?		");
    	query.append("	,last_sch_time		= null	");
    	query.append("	,target_script		= ?		");
    	query.append("	,scope_script		= ?		");
    	query.append("WHERE mail_list_id	= ?		");
    	query.append("  AND comp_no 		= ?		");
    	
    	Object[] objects = new Object[] {
    			maMailDetailDTO.getMailListNo()
    			,maMailDetailDTO.getMailDesc()
    			,maMailDetailDTO.getRemark()
    			,maMailDetailDTO.getCycle()
    			,maMailDetailDTO.getTimeType()
    			,maMailDetailDTO.getMethodType()
    			,maMailDetailDTO.getIsActive()
    			,maMailDetailDTO.getTitleScript()
    			,maMailDetailDTO.getScript()
    			,maMailDetailDTO.getStartExeDate()
    			,maMailDetailDTO.getStartExeTime()
    			,maMailDetailDTO.getTargetScript()
    			,maMailDetailDTO.getScopeScript()
    			,maMailDetailDTO.getMailListId()
    			,maMailDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int updateMailSendTime(MaMailDetailDTO maMailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAMAILLIST SET			    ");
    	query.append("	last_sch_time		= getdate()	");
    	query.append("WHERE mail_list_id	= ?		    ");
    	query.append("  AND comp_no 		= ?		    ");
    	
    	Object[] objects = new Object[] {
    			 maMailDetailDTO.getMailListId()
    			,maMailDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public String findTitleMessageQuery(String strQuery)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(strQuery);
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    public List sendMessageQuery(String strQuery)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(strQuery);
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * mail list
     * @author hankyul
     * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @return
     */
    public String[] findSendList(MaMailDetailDTO maMailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	String sendCol = "x.e_mail";
        if("SMS".equals(maMailDetailDTO.getMethodType())){
        	sendCol = "x.phone";
        }
        
        query.append("SELECT									");
        query.append("       "+sendCol+"  		AS eMail    	");
        query.append("FROM   TAMAILUSR x INNER JOIN TAEMP y		");
        query.append("ON x.comp_no = y.comp_no					");
        query.append("AND x.emp_id = y.emp_id					");
        query.append("WHERE  1=1								");
        query.append("  AND  x.mail_list_id = ?     	        ");
        query.append("  AND  x.comp_no = ?	                    ");
        
        Object[] objects = new Object[] {
        		maMailDetailDTO.getMailListId()
    			,maMailDetailDTO.getCompNo()
    	};
    
        return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
    }
    
    public String[][] findAllMailingList(MaMailDetailDTO maMailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("select                                                                                                                                                                                                                  ");
    	query.append("     mail_list_id, comp_no                                                                                                                                                                                                      ");
    	query.append("from tamaillist a                                                                                                                                                                                                       ");
    	query.append("where 1=1                                                                                                                                                                                                               ");
    	query.append("    and 1 = (case when a.time_type = 'D'                                                                               ");
    	query.append("                and DATEDIFF(DAY,isnull(a.last_sch_time,convert(datetime,a.start_exe_date)),getdate() )  >= a.cycle    ");
    	query.append("                and replace(convert(varchar(8), getdate(), 114),':','') > start_exe_time + '00'                        ");
    	query.append("             then 1                                                                                                    ");
    	query.append("             when a.time_type = 'M'                                                                                    ");
    	query.append("                and DATEDIFF(MONTH,isnull(a.last_sch_time,convert(datetime,a.start_exe_date)),getdate() )  >= a.cycle  ");
    	query.append("                and replace(convert(varchar(8), getdate(), 114),':','') > start_exe_time + '00'                        ");
    	query.append("             then 1                                                                                                    ");
    	query.append("             when a.time_type = 'H'                                                                                    ");
    	query.append("                and DATEDIFF(HOUR,isnull(a.last_sch_time,convert(datetime,a.start_exe_date)),getdate() )  >= a.cycle   ");
    	query.append("                and right(replace(convert(varchar(8), getdate(), 114),':',''),4) > right(start_exe_time + '00',4)      ");
    	query.append("            then 1                                                                                                     ");
    	query.append("            else 0                                                                                                     ");
    	query.append("            end   )                                                                                                    ");
    	query.append("    and is_use = ?                                                                                                                                                                                                    ");
    	Object[] objects = new Object[] {
    			"Y"
    	};
    	
    	return QueryBuffer.toStringArray(this.getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
    }
    
    public String selectTitleScript(MaMailDetailDTO maMailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT									");
    	query.append("       x.title_script	AS titleScript	    ");
    	query.append("FROM   tamaillist x						");
    	query.append("WHERE  1=1								");
    	query.append("  AND  x.mail_list_id = ?              	");
    	query.append("  AND  x.comp_no = ?                  	");
    	
    	Object[] objects = new Object[] {
    			maMailDetailDTO.getMailListId()
    			,maMailDetailDTO.getCompNo()
    	};
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
    }
    public String selectExeScript(MaMailDetailDTO maMailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT									");
    	query.append("       x.script       	AS script    	");
    	query.append("FROM   tamaillist x						");
    	query.append("WHERE  1=1								");
    	query.append("  AND  x.mail_list_id = ?              	");
    	query.append("  AND  x.comp_no = ?                  	");
    	
    	Object[] objects = new Object[] {
    			maMailDetailDTO.getMailListId()
    			,maMailDetailDTO.getCompNo()
    	};
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
    }
    public String selectLinkTitle(){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT							");
    	query.append("		top 1 x.key_name AS keyName	");
    	query.append("FROM TALANG x						");
    	query.append("WHERE 1=1							");
    	query.append("AND key_type = 'LABEL'			");
    	query.append("AND key_no   = 'loginPgMsg02'		");
    	query.append("AND lang     = 'ko'				");
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

	@Override
	public String[] getStringArrayFromScript(String script) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(script);
    
        return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString()));
    }
	
	@Override
	public String[][] getMultiStringArrayFromScript(String script) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append(script);
		
		return QueryBuffer.toStringArray(this.getJdbcTemplate().queryForList(query.toString()));
	}
    
}