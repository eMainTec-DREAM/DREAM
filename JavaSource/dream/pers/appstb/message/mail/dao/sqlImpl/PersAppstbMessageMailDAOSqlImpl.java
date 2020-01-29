package dream.pers.appstb.message.mail.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.message.mail.dao.PersAppstbMessageMailDAO;

/**
 * 메일수신자설정 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: PersAppstbMessageMailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="persAppstbMessageMailDAOTarget"
 * @spring.txbn id="persAppstbMessageMailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
@Deprecated
public class PersAppstbMessageMailDAOSqlImpl extends BaseJdbcDaoSupportSql implements PersAppstbMessageMailDAO
{
    
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

	
	public List selectWoReqDetail(AppReqDetailDTO appReqDetailDTO, User user)
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("select                                                                                                                                                 ");
		query.append("     (select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0271' and aa.lang = ? )    as TITLE                           ");
		query.append("    ,a.woreq_no                                                                                                     as WOREQ_NO                        ");
		query.append("    ,dbo.SFACODE_TO_DESC(a.woreq_status,'WOREQ_STATUS','SYS','','"+user.getLangId()+"')                             as WOREQ_STATUS                    ");
        query.append("    ,a.description                                                                                                  as WOREQ_TITLE                     ");
		query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.req_emp_id = aa.emp_id)                                                    ");
		query.append("     + '/' + (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.req_dept_id = aa.dept_id)  as EMP_NAME                         ");
		query.append("    ,a.req_phone                                                                                                    as REQ_PHONE                       ");
		query.append("    ,substring(a.req_date,1,4) + '-'  + substring(a.req_date,5,2) + '-' + substring(a.req_date,7,2)                         as REQ_DATE                ");
		query.append("    ,case when a.req_com_date is not null                                                                                                              ");
		query.append("            then  substring(a.req_com_date,1,4) + '-' + substring(a.req_com_date,5,2) + '-' + substring(a.req_com_date,7,2)                            ");
		query.append("            else '-' end                                                                                             as REQ_COM_DATE                   ");
		query.append("    ,isnull((select aa.key_name from talang aa where key_type = 'CODESET' and key_no = 'REQ_PRIORITY.' + a.req_priority and lang = ? ),'-') as REQ_PRIORITY       ");
		query.append("    ,isnull((select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.rec_emp_id = aa.emp_id),'-')                                         ");
		query.append("     + '/' + isnull((select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.rec_dept_id = aa.dept_id),'-')            as REC_NAME    ");
		query.append("    ,isnull((select aa.description from taequipment aa where a.comp_no = aa.comp_no and a.equip_id = aa.equip_id ),'-')           as EQUIP_NAME         ");
		query.append("    ,isnull((select aa.full_desc from taeqloc aa where a.comp_no = aa.comp_no and a.eqloc_id = aa.eqloc_id ),'-')                 as LOC_NAME           ");
		query.append("    ,replace(replace(isnull(a.request,'-'), char(10), '<br>' ),' ', '&nbsp;')                                                      as REQUEST           ");
		query.append("from taworeq a                                                                                                                                          ");
    	query.append("where a.comp_no = ?                                                                                                                                   ");
    	query.append("    and a.woreq_id = ?                                                                                                                                ");

        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,user.getCompNo()
        		,appReqDetailDTO.getObjectId()
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects); 
    }
	public List selectWorkOrderDetail(AppReqDetailDTO appReqDetailDTO, User user)
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("select                                                                                                                                               ");
        query.append("     (select aa.key_name from talang aa where aa.key_type = 'MENU' and aa.key_no = 'WORESULT' and aa.lang = ? )    AS TITLE                          ");
        query.append("    ,a.wo_no                                                                                                       AS WO_NO                          ");
        query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.emp_id = aa.emp_id)                                                      ");
        query.append("     + '/' + (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.dept_id = aa.dept_id)    AS EMP_NAME                       ");
        query.append("    ,(SELECT aa.full_desc FROM TAEQLOC aa WHERE a.comp_no = aa.comp_no ANd a.eqloc_id = aa.eqloc_id)               AS EQLOC                          ");
        query.append("    ,(SELECT bb.description FROM TAEQUIPMENT bb WHERE bb.comp_no = a.comp_no AND bb.equip_id = (SELECT TOP 1 aa.equip_id FROM TAWOEQUIP aa WHERE aa.comp_no = a.comp_no AND aa.wkor_id = a.wkor_id)) AS EQUIP");
        query.append("    ,substring(a.start_date,1,4) + '-' +substring(a.start_date,5,2) + '-' +substring(a.start_date,7,2)+' '+substring(a.start_time,1,2) + ':' +substring(a.start_time,3,2)               AS START_DATE  ");
        query.append("    ,substring(a.end_date,1,4) + '-' +substring(a.end_date,5,2) + '-' +substring(a.end_date,7,2)+' '+substring(a.end_time,1,2) + ':' +substring(a.end_time,3,2)                         AS END_DATE    ");
        query.append("    ,substring(a.wkor_date,1,4) + '-' +substring(a.wkor_date,5,2) + '-' +substring(a.wkor_date,7,2)                                 AS WKOR_DATE          ");
        query.append("    ,replace(replace(isnull(a.perform,'-'), char(10), '<br>' ),' ', '&nbsp;')                                                      AS PERFORM            ");
        query.append("from taworkorder a                                                                                                                                   ");
        query.append("where a.comp_no = ?                                                                                                                                  ");
        query.append("  and a.wkor_id = ?                                                                                                                                  ");

        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getCompNo()
        		,appReqDetailDTO.getObjectId()
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects); 
    }
	public String[] findWoReqRecUserMailList(AppReqDetailDTO appReqDetailDTO, User user)
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("select                                                                                 ");
        query.append("    b.e_mail                                                                           ");
        query.append("from taworeq a inner join taemp b on a.comp_no = b.comp_no and a.rec_emp_id = b.emp_id ");
        query.append("where a.comp_no = ?                                                                    ");
        query.append("    and a.woreq_id = ?                                                                 ");
        query.append("    and b.is_mail_rec = 'Y'                                                            ");
        query.append("    and b.e_mail is not null                                                           ");

        Object[] objects = new Object[] {
        		user.getCompNo()
        		,appReqDetailDTO.getObjectId()
        };
    
        return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }
	
	public String[] findWoReqRecUserEmpNoList(AppReqDetailDTO appReqDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("select                                                                                 ");
		query.append("    b.emp_no                                                                           ");
		query.append("from taworeq a inner join taemp b on a.comp_no = b.comp_no and a.rec_emp_id = b.emp_id ");
		query.append("where a.comp_no = ?                                                                    ");
		query.append("    and a.woreq_id = ?                                                                 ");
		query.append("    and b.is_mail_rec = 'Y'                                                            ");
		query.append("    and b.e_mail is not null                                                           ");
		
		Object[] objects = new Object[] {
				user.getCompNo()
				,appReqDetailDTO.getObjectId()
		};
		
		return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
	}
	
	
	public String[] findWoReqRecWkGrpMailList(AppReqDetailDTO appReqDetailDTO, User user)
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("select                                                                                          ");
        query.append("   c.e_mail                                                                                     ");
        query.append("from taworeq a inner join tawkctr b on a.comp_no = b.comp_no and a.rec_wkctr_id = b.wkctr_id    ");
        query.append("                       inner join taemp c on b.comp_no = c.comp_no and b.wkctr_id = c.wkctr_id  ");
        query.append("where a.comp_no = ?                                                                             ");
        query.append("    and a.woreq_id = ?                                                                          ");
        query.append("    and c.is_mail_rec = 'Y'                                                                     ");
        query.append("    and c.e_mail is not null                                                                    ");


        Object[] objects = new Object[] {
        		user.getCompNo()
        		,appReqDetailDTO.getObjectId()
        };
    
        return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }
	
	public String[] findWoReqRecWkGrpEmpNoList(AppReqDetailDTO appReqDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("select                                                                                          ");
		query.append("   c.emp_no                                                                                     ");
		query.append("from taworeq a inner join tawkctr b on a.comp_no = b.comp_no and a.rec_wkctr_id = b.wkctr_id    ");
		query.append("                       inner join taemp c on b.comp_no = c.comp_no and b.wkctr_id = c.wkctr_id  ");
		query.append("where a.comp_no = ?                                                                             ");
		query.append("    and a.woreq_id = ?                                                                          ");
		query.append("    and c.is_mail_rec = 'Y'                                                                     ");
		query.append("    and c.e_mail is not null                                                                    ");
		
		
		Object[] objects = new Object[] {
				user.getCompNo()
				,appReqDetailDTO.getObjectId()
		};
		
		return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
	}
	
	public String[] findWoReqRecDeptMailList(AppReqDetailDTO appReqDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("select                                                                                          ");
		query.append("   c.e_mail                                                                                     ");
		query.append("from taworeq a inner join tadept b on a.comp_no = b.comp_no and a.rec_dept_id = b.dept_id       ");
		query.append("               inner join taemp c on b.comp_no = c.comp_no and b.dept_id = c.dept_id            ");
		query.append("where a.comp_no = ?                                                                             ");
		query.append("    and a.woreq_id = ?                                                                          ");
		query.append("    and c.is_mail_rec = 'Y'                                                                     ");
		query.append("    and c.e_mail is not null                                                                    ");
		
		
		Object[] objects = new Object[] {
				user.getCompNo()
				,appReqDetailDTO.getObjectId()
		};
		
		return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
	}
	
	public String[] findWoReqRecDeptEmpNoList(AppReqDetailDTO appReqDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("select                                                                                          ");
		query.append("   c.emp_no                                                                                     ");
		query.append("from taworeq a inner join tadept b on a.comp_no = b.comp_no and a.rec_dept_id = b.dept_id       ");
		query.append("               inner join taemp c on b.comp_no = c.comp_no and b.dept_id = c.dept_id            ");
		query.append("where a.comp_no = ?                                                                             ");
		query.append("    and a.woreq_id = ?                                                                          ");
		query.append("    and c.is_mail_rec = 'Y'                                                                     ");
		query.append("    and c.e_mail is not null                                                                    ");
		
		
		Object[] objects = new Object[] {
				user.getCompNo()
				,appReqDetailDTO.getObjectId()
		};
		
		return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	public String[] findDrafterMailList(AppReqDetailDTO appReqDetailDTO, User user)
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("select                                                        ");
        query.append("        b.e_mail                                              ");
        query.append("from taapprusr a inner join taemp b                           ");
        query.append("        on a.comp_no = b.comp_no and a.appr_by = b.emp_id     ");
        query.append("        inner join TAMSGEMPSET c                              ");
        query.append("        on b.comp_no = c.comp_no and b.emp_id = c.emp_id and c.message_object_type = 'APP20' and c.is_use='Y' and c.mail_use='Y'      ");
        query.append("where 1=1                                                     ");
        query.append("    and a.comp_no = ?                                         ");
        query.append("    and a.apprusr_id = (select max(aa.apprusr_id)             ");
        query.append("                              from taapprusr aa               ");
        query.append("                              where aa.comp_no = ?            ");
        query.append("                                  and aa.apprlist_id = ?      ");
        query.append("                                  and aa.apprusr_status = 'A' ");
        query.append("                              )                               ");
        query.append("    and b.is_mail_rec = 'Y'                                   ");
        query.append("    and b.e_mail is not null                                  ");


        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,user.getCompNo()
        		,appReqDetailDTO.getApprlistId()
        };
    
        return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
	public String[] findDrafterEmpNoList(AppReqDetailDTO appReqDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("select                                                        ");
		query.append("        b.emp_no                                              ");
		query.append("from taapprusr a inner join taemp b                           ");
		query.append("        on a.comp_no = b.comp_no and a.appr_by = b.emp_id     ");
		query.append("where 1=1                                                     ");
		query.append("    and a.comp_no = ?                                         ");
		query.append("    and a.apprusr_id = (select max(aa.apprusr_id)             ");
		query.append("                              from taapprusr aa               ");
		query.append("                              where aa.comp_no = ?            ");
		query.append("                                  and aa.apprlist_id = ?      ");
		query.append("                                  and aa.apprusr_status = 'A' ");
		query.append("                              )                               ");
		query.append("    and b.is_mail_rec = 'Y'                                   ");
		query.append("    and b.e_mail is not null                                  ");
		
		
		
		Object[] objects = new Object[] {
				user.getCompNo()
				,user.getCompNo()
				,appReqDetailDTO.getApprlistId()
		};
		
		return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
	}
	
    
    public String[] findApproverMailList(AppReqDetailDTO appReqDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("select                                                    ");
        query.append("        b.e_mail                                          ");
        query.append("from taapprusr a inner join taemp b                       ");
        query.append("        on a.comp_no = b.comp_no and a.appr_by = b.emp_id ");
        query.append("        inner join TAMSGEMPSET c                              ");
        query.append("        on b.comp_no = c.comp_no and b.emp_id = c.emp_id and c.message_object_type = 'APP10' and c.is_use='Y' and c.mail_use='Y'      ");
        query.append("where 1=1                                                 ");
        query.append("    and a.apprusr_action = 'P'                            ");
        query.append("    and a.comp_no = ?                                     ");
        query.append("    and a.apprlist_id = ?                                 ");
        query.append("    and b.is_mail_rec = 'Y'                               ");
        query.append("    and b.e_mail is not null                              ");

        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,appReqDetailDTO.getApprlistId()
        };
    
        return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    public String[] findApproverEmpNoList(AppReqDetailDTO appReqDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("select                                                    ");
    	query.append("        b.emp_no                                          ");
    	query.append("from taapprusr a inner join taemp b                       ");
    	query.append("        on a.comp_no = b.comp_no and a.appr_by = b.emp_id ");
    	query.append("where 1=1                                                 ");
    	query.append("    and a.apprusr_action = 'P'                            ");
    	query.append("    and a.comp_no = ?                                     ");
    	query.append("    and a.apprlist_id = ?                                 ");
    	query.append("    and b.is_mail_rec = 'Y'                               ");
    	query.append("    and b.e_mail is not null                              ");
    	
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,appReqDetailDTO.getApprlistId()
    	};
    	
    	return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    public String findTitle(AppReqDetailDTO appReqDetailDTO, String keyType, String menuKeyNo, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("select                                                                                                        ");
        query.append("        (select bb.key_name from talang bb                                                                    ");
        query.append("         where bb.key_type = 'CODESET'                                                                        ");
        query.append("            and bb.key_no = 'APPR_STATUS.' + case when  a.appr_status = 'P' then 'R' else a.appr_status end  ");
        query.append("            and bb.lang = ? )                                                                                 ");
        query.append("         + ' - [' +                                                                                         ");
        query.append("        (select aa.key_name from talang aa                                                                    ");
        query.append("         where aa.key_type = ?                                                                           ");
        query.append("            and aa.key_no = ?                                                                                 ");
        query.append("            and aa.lang = ? )                                                                                 ");
        query.append("        + ']' +                                                                                             ");
        query.append("        title  as title                                                                                       ");
        query.append("from taapprlist a                                                                                             ");
        query.append("where a.comp_no = ?                                                                                           ");
        query.append("    and a.apprlist_id = ?                                                                                     ");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,keyType
        		,menuKeyNo
        		,user.getLangId()
        		,user.getCompNo()
        		,appReqDetailDTO.getApprlistId()
        };

    
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    public String findApprStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("select appr_status         ");
        query.append("from taapprlist            ");
        query.append("where comp_no = ?          ");
        query.append("    and apprlist_id = ?    ");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,appReqDetailDTO.getApprlistId()
        };

    
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    public List selectApprovalList(AppReqDetailDTO appReqDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT                                                                                                                                                         ");
    	query.append("       appr_seq                                                                                                                   as APPRSEQ                   ");
    	query.append("       ,dbo.SFACODE_TO_DESC(x.apprusr_status,'APPRUSR_STATUS','SYS','',?)                                                      as APPRUSRSTATUS             ");
    	query.append("       ,y.emp_name                                                                                                                as APPRBYNAME                ");
    	query.append("       ,isnull(y.grade,'-')                                                                                                       as GRADE                     ");
    	query.append("       ,(SELECT a.description                                                                                                                                  ");
    	query.append("        FROM   TADEPT a                                                                                                                                        ");
    	query.append("        WHERE  a.comp_no = y.comp_no and a.dept_id = y.dept_id)                                                                    as DEPTNAME                 ");
    	query.append("       ,dbo.SFACODE_TO_DESC(x.apprusr_action,'APPRUSR_ACTION','SYS','',?)                                                       as APPRUSRACTION            ");
    	query.append("       ,case when x.appr_date is not null                                                                                                                      ");
    	query.append("            then  substring(x.appr_date,1,4) + '-' + substring(x.appr_date,5,2) + '-' + substring(x.appr_date,7,2)                                             ");
    	query.append("                      + ' ' + substring(x.appr_time,1,2) + ':' + substring(x.appr_time,3,2) + ':' + substring(x.appr_time,5,2)                                 ");
    	query.append("            else '-' end                                                                                                           as APPRTIME                 ");
    	query.append("      ,replace(replace(isnull(x.REMARK,'-'), char(10), '<br>' ),' ', '&nbsp;')                                                      as REMARK                   ");
    	query.append("FROM TAAPPRUSR x inner join TAEMP y on x.comp_no = y.comp_no and x.appr_by = y.emp_id                                                                          ");
    	query.append("WHERE 1=1                                                                                                                                                      ");
    	query.append("  AND  x.comp_no = ?                                                                                                                                          ");
    	query.append("  AND  x.apprlist_id  = (SELECT                                                                                                                               ");
    	query.append("                               z.apprlist_id apprlistId                                                                                                       ");
    	query.append("                        FROM   TAAPPRLIST z                                                                                                                   ");
    	query.append("                        WHERE  1 = 1                                                                                                                          ");
    	query.append("                            AND  z.comp_no = ?                                                                                                                ");
    	query.append("                            AND  z.object_id = ?                                                                                                              ");
    	query.append("                            AND  z.appr_type = ?                                                                                                              ");
    	query.append("                        )                                                                                                                                     ");
    	query.append("ORDER BY x.appr_date,x.appr_time, x.appr_seq                                                                                                                  ");

        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,user.getCompNo()
        		,user.getCompNo()
        		,appReqDetailDTO.getObjectId()
        		,appReqDetailDTO.getApprType()
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects); 
    }
	@Override
	public List selectPartAdjStkTakeDetail(AppReqDetailDTO appReqDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("SELECT 																							");
    	query.append("     (SELECT aa.key_name                          												");
    	query.append("     	FROM TALANG aa                      														");
    	query.append("      WHERE aa.key_type = 'MENU' AND aa.key_no = 'PTADJ' AND aa.lang = ? )    TITLE               ");
    	query.append("      ,x.ptstktakelist_no														PTSTKTAKELIST_NO	");
    	query.append("      ,dbo.SFACODE_TO_DESC (x.ptstktake_status,'PTSTKTAKE_STATUS','SYS','',?)	PTSTKTAKE_STATUS	");
    	query.append("      ,x.description 															DESCRIPTION			");
    	query.append("      ,x.plan_date															PLAN_DATE			");
    	query.append("      ,ISNULL(act_date,'')													ACT_DATE			");
    	query.append("      ,ISNULL((SELECT wname																		");
    	query.append("        FROM TAWAREHOUSE																			");
    	query.append("        WHERE comp_no = x.comp_no AND wcode_id = x.wcode_id),'')				WNAME				");
    	query.append("      ,(SELECT a.emp_name																			");
    	query.append("        FROM TAEMP a																				");
    	query.append("        WHERE a.comp_no = x.comp_no AND a.emp_id = x.reg_id)										");
    	query.append("       + ' / '																					");
    	query.append("       + (SELECT description																		");
    	query.append("          FROM TADEPT a																			");
    	query.append("          WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id)				EMP_NAME			");
    	query.append(" FROM TAPTSTKTAKELIST x																			");
    	query.append(" WHERE 1=1																						");
    	query.append("        AND x.comp_no = ? 																		");
    	query.append("        AND x.ptstktakelist_id = ?																");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,user.getCompNo()
        		,appReqDetailDTO.getObjectId()
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

	@Override
	public List selectPtBuyReqDetail(AppReqDetailDTO appReqDetailDTO, User user) {

		QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("SELECT                                                       																		");
        query.append("        (SELECT aa.key_name FROM TALANG aa WHERE aa.key_type = 'LABEL' AND aa.key_no = 'appRequest' AND aa.lang = ? )	AS TITLE	");
        query.append("       ,x.ptprlist_no                     									AS PTPRLISTNO        								");
        query.append("       ,dbo.SFACODE_TO_DESC(x.ptprlist_status,'PTPRLIST_STATUS','SYS','',?)   AS PTPRLISTSTATUSDESC								");
        query.append("       ,x.description                     									AS PTPRLISTDESC        								");
        query.append("       ,CASE WHEN LEN(x.req_date) = 8																								");
        query.append("             THEN SUBSTRING(x.req_date,1,4) + '-' + SUBSTRING(x.req_date,5,2) + '-' + SUBSTRING(x.req_date,7,2)					");
        query.append("             ELSE x.REQ_DATE																										");
        query.append("             END                     											AS REQDATE											");
        query.append("       ,(SELECT description                                     																	");
        query.append("         FROM TADEPT a                                        																	");
        query.append("         WHERE a.comp_no = x.comp_no                            																	");
        query.append("           AND a.dept_id = x.dept_id)     									AS DEPTDESC    										");
        query.append("       ,ISNULL(x.remark,'')             										AS REMARK											");
        query.append("FROM   TAPTPRLIST x                                            																	");
        query.append("WHERE  x.comp_no = ?                     																							");
        query.append("  AND  x.ptprlist_id = ?																											");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
        		,appReqDetailDTO.getObjectId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(),getObject(objects)); 
	}
	@Override
	public List selectPtBuyReqItemList(AppReqDetailDTO appReqDetailDTO, User user) {
		
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                			");
        query.append("     (SELECT part_no                                 				");
        query.append("      FROM TAPARTS a                                				");
        query.append("      WHERE a.comp_no = x.comp_no                    				");
        query.append("      AND a.part_id = x.part_id)     				PARTNO        	");
        query.append("     , x.description                        		PARTDESC        ");
        query.append("     , ISNULL((SELECT seller                                 		");
        query.append("        FROM TAPARTS a                                			");
        query.append("        WHERE a.comp_no = x.comp_no                    			");
        query.append("        AND a.part_id = x.part_id),'')     		SELLER    		");
        query.append("     , ISNULL((SELECT model                              			");
        query.append("        FROM TAPARTS a                                			");
        query.append("        WHERE a.comp_no = x.comp_no                    			");
        query.append("        AND a.part_id = x.part_id),'')     		MODEL           ");
        query.append("     , ISNULL(x.pt_size,'')                       PTSIZE        	");
        query.append("     , Format(CONVERT(float,ISNULL(x.rec_qty,0)),'#.########')   						RECQTY  	");
        query.append("     , Format(CONVERT(float,ISNULL(x.unit_price,0)),'#.########')   					UNITPRICE	");
        query.append("     , Format(CONVERT(float,ISNULL(x.unit_price,0)*ISNULL(rec_qty,0)),'#.########')   TOTALPRICE  ");
        query.append("     , ISNULL(x.remark,'')                        REMARK    		");
        query.append("FROM TAPTPRITEM x                                        			");
        query.append("WHERE x.comp_no = ?                        						");
        query.append("  AND  x.ptprlist_id = ?											");
        query.append("ORDER BY x.ptprlist_id desc										");

        Object[] objects = new Object[] {
                user.getCompNo()
        		,appReqDetailDTO.getObjectId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(),getObject(objects)); 
    }
}