package dream.mgr.message.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.mgr.message.dao.MgrMessageTransDetailDAO;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;
import dream.mgr.message.dto.MgrMessageTransDetailDTO;

/**
 * Message Transfer Page - Detail DAO implements
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrMessageTransDetailDAOTarget"
 * @spring.txbn id="mgrMessageTransDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrMessageTransDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrMessageTransDetailDAO
{
	
    
    public MgrMessageTransDetailDTO findDetail(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            	");
        query.append("      x.messageList_Id  							AS messageId    	");
        query.append("      ,x.description                              AS description  	");
        query.append("      ,x.contents                                 AS contents     	");
        query.append("      ,x.receivers                                AS receiver  		");
        query.append("      ,x.method_type                              AS methodTypeId		");
        query.append("      ,SFACODE_TO_DESC(x.method_type,'METHOD_TYPE','SYS','','"+user.getLangId()+"')		AS methodTypeDesc	");
        query.append("      ,x.message_status                           AS msgStatusId		");
        query.append("      ,SFACODE_TO_DESC(x.message_status,'MESSAGE_STATUS','SYS','','"+user.getLangId()+"')	AS msgStatusDesc	");
        query.append("      ,x.retry_cnt                                AS retryCnt  		");
        query.append("		,to_char(to_date(x.cre_time,'YYYYMMDDHH24MISS'),'YYYY-MM-DD HH24:MI:SS')	AS creTime	");
        query.append("		,to_char(to_date(x.send_time,'YYYYMMDDHH24MISS'),'YYYY-MM-DD HH24:MI:SS')	AS sendTime	");
        query.append("      ,x.fail_msg                                 AS failMsg      	");
        query.append("FROM TAMESSAGELIST x                                              	");
    	query.append("WHERE  1=1															");
    	query.append(" AND	x.messageList_Id    = ?											");
    	query.append("  AND x.comp_no    		= ?											");
        
        Object[] objects = new Object[] {
        		mgrMessageTransCommonDTO.getMessageId()
    			,user.getCompNo()
    	};
    
        MgrMessageTransDetailDTO mgrMessageTransDetailDTO = 
        		(MgrMessageTransDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrMessageTransDetailDTO()));
        
        return mgrMessageTransDetailDTO;
        
    }

	@Override
	public int insertDetail(MgrMessageTransDetailDTO mgrMessageTransDetailDTO, User user) throws Exception {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAMESSAGELIST										");
    	query.append("	(comp_no		, messagelist_id	, description			");
    	query.append(" , contents		, receivers			, method_type			");
    	query.append(" , message_status , retry_cnt			, cre_time				");
    	query.append(" , send_emp_no    , rec_emp_no        , message_object_type   ");
    	query.append(" , object_id      , object_no									");
    	query.append("	)	VALUES	(												");
    	query.append("	 ?				, ?				    , ?						");
    	query.append(" , ?				, ?				    , ?						");
    	query.append(" , ?				, ?				    , ?						");
    	query.append(" , ?				, ?				    , ?						");
    	query.append(" , ?				, ?				    						");
    	query.append("	)															");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,mgrMessageTransDetailDTO.getMessageId()
    			,mgrMessageTransDetailDTO.getDescription()
    			,mgrMessageTransDetailDTO.getContents()
    			,mgrMessageTransDetailDTO.getReceiver()
    			,mgrMessageTransDetailDTO.getMethodTypeId()
    			,mgrMessageTransDetailDTO.getMsgStatusId()
    			,"0"
    			,DateUtil.getDateTime()
    			,mgrMessageTransDetailDTO.getSendEmpNo()
    			,mgrMessageTransDetailDTO.getRecEmpNo()
    			,mgrMessageTransDetailDTO.getMsgObjType()
    			,mgrMessageTransDetailDTO.getObjectId()
    			,mgrMessageTransDetailDTO.getObjectNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
	
	

    @Override
	public int updateResultLog(MgrMessageTransDetailDTO mgrMessageTransDetailDTO, User user) throws Exception {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAMESSAGELIST							");
    	query.append("	SET message_status 	= ?						");
    	query.append("	   ,fail_msg 		= ?						");
    	query.append("	   ,retry_cnt 		= (retry_cnt+1)			");
    	query.append("WHERE 1=1										");
    	query.append("AND comp_no 			= ?						");
    	query.append("AND messagelist_id 	= ?						");
    	
    	Object[] objects = new Object[] {
    			mgrMessageTransDetailDTO.getMsgStatusId()
    			,mgrMessageTransDetailDTO.getFailMsg()
    			,user.getCompNo()
    			,mgrMessageTransDetailDTO.getMessageId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
    public String findMailTitle(String compNo, String errorLogId) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT comp_no||'['||description||']에서 오류['||?||'] 확인 요청건'");
        query.append("FROM TACOMP                                               ");
        query.append("WHERE comp_no = ?                                         ");
        
        Object[] objects = new Object[] {
                errorLogId
                ,compNo
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }

    @Override
    public List findErrorLog(String errorLogId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("      '오류확인'                                     AS TITLE        ");
        query.append("    , x.errorlog_id                               AS ERROR_LOG_NO ");
        query.append("    , TO_CHAR(x.err_date,'yyyymmddhh24miss')      AS ERROR_DATE   ");
        query.append("    , x.comp_no ||' / '||x.user_no                AS COMP_USER    ");
        query.append("    , x.req_url                                   AS URL          ");
        query.append("    , REPLACE(REPLACE(x.stacktrace_clob, chr(13), ''), chr(10), '<br>') AS ERROR_LOG    ");
        query.append("FROM TAERRORLOG x                                                 ");
        query.append("WHERE  1=1                                                        ");
        query.append("  AND errorlog_id = ?                                             ");
        
        Object[] objects = new Object[] {
                errorLogId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public String isUseMailCateg(String messageObjectType, String compNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    CASE WHEN count(*) > 0 THEN 'Y' ELSE 'N' END isUse    ");
        query.append("FROM TAMESSAGECATEG a INNER JOIN TAMSGCOMPSET b           ");
        query.append("ON a.message_object_type = b.message_object_type          ");
        query.append("AND a.is_use   = 'Y'                                      ");
        query.append("AND b.is_use   = 'Y'                                      ");
        query.append("AND a.mail_use = 'Y'                                      ");
        query.append("AND b.mail_use = 'Y'                                      ");
        query.append("AND a.message_object_type = ?                             ");
        query.append("AND b.comp_no             = ?                             ");
        
        Object[] objects = new Object[] {
                messageObjectType
                ,compNo
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    @Override
    public String isUseSmsCateg(String messageObjectType, String compNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    CASE WHEN count(*) > 0 THEN 'Y' ELSE 'N' END isUse    ");
        query.append("FROM TAMESSAGECATEG a INNER JOIN TAMSGCOMPSET b           ");
        query.append("ON a.message_object_type = b.message_object_type          ");
        query.append("AND a.is_use   = 'Y'                                      ");
        query.append("AND b.is_use   = 'Y'                                      ");
        query.append("AND a.sms_use  = 'Y'                                      ");
        query.append("AND b.sms_use  = 'Y'                                      ");
        query.append("AND a.message_object_type = ?                             ");
        query.append("AND b.comp_no             = ?                             ");
        
        Object[] objects = new Object[] {
                messageObjectType
                ,compNo
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }

    @Override
    public String isUseKakaoCateg(String messageObjectType, String compNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    CASE WHEN count(*) > 0 THEN 'Y' ELSE 'N' END isUse    ");
        query.append("FROM TAMESSAGECATEG a INNER JOIN TAMSGCOMPSET b           ");
        query.append("ON a.message_object_type = b.message_object_type          ");
        query.append("AND a.is_use   = 'Y'                                      ");
        query.append("AND b.is_use   = 'Y'                                      ");
        query.append("AND a.kakao_use  = 'Y'                                    ");
        query.append("AND b.kakao_use  = 'Y'                                    ");
        query.append("AND a.message_object_type = ?                             ");
        query.append("AND b.comp_no             = ?                             ");
        
        Object[] objects = new Object[] {
                messageObjectType
                ,compNo
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    @Override
    public List findNextApprUser(String apprListId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                    ");
        query.append("        b.emp_id AS EMP_ID                                ");
        query.append("        ,b.emp_no AS EMP_NO                               ");
        query.append("        ,b.e_mail AS E_MAIL                               ");
        query.append("from taapprusr a inner join taemp b                       ");
        query.append("        on a.comp_no = b.comp_no and a.appr_by = b.emp_id ");
        query.append("where 1=1                                                 ");
        query.append("    and a.apprusr_action = 'P'                            ");
        query.append("    and a.comp_no = ?                                     ");
        query.append("    and a.apprlist_id = ?                                 ");
        query.append("    and b.e_mail is not null                              ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,apprListId
        };
    
        return getJdbcTemplate().queryForList(query.toString(),objects);
    }

    @Override
    public String isUseMailEmp(String messageObjectType, String compNo, String empId) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    CASE WHEN count(*) > 0 THEN 'Y' ELSE 'N' END isUse    ");
        query.append("FROM TAMSGEMPSET a INNER JOIN TAEMP b                     ");
        query.append("ON a.comp_no = b.comp_no AND a.emp_id = b.emp_id          ");
        query.append("WHERE 1=1                                                 ");
        query.append("AND a.is_use   = 'Y'                                      ");
        query.append("AND a.mail_use = 'Y'                                      ");
        query.append("AND a.message_object_type = ?                             ");
        query.append("AND a.comp_no             = ?                             ");
        query.append("AND a.emp_id              = ?                             ");
        query.append("AND b.is_mail_rec = 'Y'                                   ");
        
        Object[] objects = new Object[] {
                messageObjectType
                ,compNo
                ,empId
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    @Override
    public String isUseSmsEmp(String messageObjectType, String compNo, String empId) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    CASE WHEN count(*) > 0 THEN 'Y' ELSE 'N' END isUse    ");
        query.append("FROM TAMSGEMPSET                                          ");
        query.append("WHERE 1=1                                                 ");
        query.append("AND is_use   = 'Y'                                        ");
        query.append("AND sms_use  = 'Y'                                        ");
        query.append("AND message_object_type = ?                               ");
        query.append("AND comp_no             = ?                               ");
        query.append("AND emp_id              = ?                               ");
        
        Object[] objects = new Object[] {
                messageObjectType
                ,compNo
                ,empId
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    @Override
    public String isUseKakaoEmp(String messageObjectType, String compNo, String empId) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    CASE WHEN count(*) > 0 THEN 'Y' ELSE 'N' END isUse    ");
        query.append("FROM TAMSGEMPSET                                          ");
        query.append("WHERE 1=1                                                 ");
        query.append("AND is_use   = 'Y'                                        ");
        query.append("AND kakao_use  = 'Y'                                      ");
        query.append("AND message_object_type = ?                               ");
        query.append("AND comp_no             = ?                               ");
        query.append("AND emp_id              = ?                               ");
        
        Object[] objects = new Object[] {
                messageObjectType
                ,compNo
                ,empId
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }

    @Override
    public List getDataREQ10(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                                                                                                                ");
        query.append("     (select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0271' and aa.lang = ? )   AS TITLE                          ");
        query.append("    ,a.woreq_no                                                                                                     AS WOREQ_NO                       ");
        query.append("    ,SFACODE_TO_DESC(a.woreq_status,'WOREQ_STATUS','SYS','',?)                                                      AS WOREQ_STATUS                   ");
        query.append("    ,a.description                                                                                                  AS WOREQ_TITLE                    ");
        query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.req_emp_id = aa.emp_id)                                                   ");
        query.append("     || '/' || (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.req_dept_id = aa.dept_id) AS EMP_NAME                       ");
        query.append("    ,a.req_phone                                                                                                    AS REQ_PHONE                      ");
        query.append("    ,substr(a.req_date,0,4) || '-' ||substr(a.req_date,5,2) || '-' ||substr(a.req_date,7,2)                         AS REQ_DATE                       ");
        query.append("    ,case when a.req_com_date is not null                                                                                                             ");
        query.append("            then  substr(a.req_com_date,0,4) || '-' ||substr(a.req_com_date,5,2) || '-' ||substr(a.req_com_date,7,2)                                  ");
        query.append("            else '-' end                                                                                            AS REQ_COM_DATE                   ");
        query.append("    ,nvl((select aa.key_name from talang aa where key_type = 'CODESET' and key_no = 'REQ_PRIORITY.' || a.req_priority and lang = ? ),'-') AS REQ_PRIORITY      ");
        query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.rec_emp_id = aa.emp_id)                                                   ");
        query.append("     || '/' || (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.rec_dept_id = aa.dept_id)            AS REC_NAME            ");
        query.append("    ,nvl((select aa.description from taequipment aa where a.comp_no = aa.comp_no and a.equip_id = aa.equip_id ),'-')           AS EQUIP_NAME          ");
        query.append("    ,nvl((select aa.full_desc from taeqloc aa where a.comp_no = aa.comp_no and a.eqloc_id = aa.eqloc_id ),'-')                 AS LOC_NAME            ");
        query.append("    ,replace(replace(nvl(a.request,'-'), chr(10), '<br>' ),' ', '&nbsp;')                                                      AS REQUEST             ");
        query.append("    ,a.comp_no                                                                                                      AS COMP_NO                        ");
        query.append("    ,a.rec_emp_id                                                                                                   AS REC_EMP_ID                     ");
        query.append("    ,a.rec_wkctr_id                                                                                                 AS REC_WKCTR_ID                   ");
        query.append("    ,a.rec_dept_id                                                                                                  AS REC_DEPT_ID                    ");
        query.append("    ,(select aa.emp_no from taemp aa where a.comp_no = aa.comp_no and a.req_emp_id = aa.emp_id)                     AS REQ_EMP_NO                     ");
        query.append("    ,(select aa.emp_name from taemp aa where a.comp_no = aa.comp_no and a.req_emp_id = aa.emp_id)                   AS REQ_EMP_NAME                   ");
        query.append("    ,nvl((select aa.item_no from taequipment aa where a.comp_no = aa.comp_no and a.equip_id = aa.equip_id ),'-')    AS ITEM_NO                        ");
        query.append("    ,nvl(a.mo_desc,'-')                                                                                             AS MO_DESC                        ");
        query.append("    ,a.comp_no                                                                                                      AS COMP_NO                        ");
        query.append("    ,(select aa.description from TACOMP aa where a.comp_no = aa.comp_no)                                            AS COMP_NAME                      ");
        query.append("from taworeq a                                                                                                                                        ");
        query.append("where a.comp_no = ?                                                                                                                                   ");
        query.append("    and a.woreq_id = ?                                                                                                                                ");

        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }
    
    
    

    @Override
	public List getMsgSendServCompData(String compNo) throws Exception {
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                 ");
        query.append("     program_type    AS PROGRAM_TYPE   ");
        query.append("     ,key_value      AS KEY_VALUE      ");
        query.append("FROM TAMSGSENDSERVCOMP a               ");
        query.append("WHERE 1=1                              ");
        query.append("AND a.comp_no = '"+compNo+"'           ");

        return getJdbcTemplate().queryForList(query.toString()); 
	}

	@Override
    public List getEmp(Map map, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT a.comp_no AS COMP_NO            ");
        query.append("    ,a.emp_id    AS EMP_ID             ");
        query.append("    ,a.emp_no    AS EMP_NO             ");
        query.append("    ,a.e_mail    AS E_MAIL             ");
        query.append("    ,a.emp_name  AS EMP_NAME           ");
        query.append("    ,a.m_phone   AS M_PHONE            ");
        query.append("    ,(select param1 from tacdsysd where cdsysd_no = a.country_code) AS COUNTRY_CODE ");
        query.append("FROM TAEMP a                           ");
        query.append("WHERE 1 = 1                            ");
        query.append(getAndQuery("a.comp_no", map));
        query.append(getAndQuery("a.emp_id", map));
        query.append(getAndQuery("a.wkctr_id", map));
        query.append(getAndQuery("a.dept_id", map));
        if(map.containsKey("WKOR_ID") && map.containsKey("COMP_NO")){
            if(map.get("COMP_NO")==null || map.get("WKOR_ID")==null) {
                query.append("AND 1 = 2");
            }
            else {
                query.append("AND a.emp_id IN (SELECT req_emp_id FROM TAWOREQ                    ");
                query.append("                WHERE 1=1                                         ");
                query.append("                AND comp_no = '"+map.get("COMP_NO").toString()+"'");
                query.append("                AND woreq_id IN (SELECT woreq_id FROM TAWOREQRES   ");
                query.append("                                WHERE 1=1                         ");
                query.append("                                AND comp_no = '"+map.get("COMP_NO").toString()+"'");
                query.append("                                AND wkor_id = '"+map.get("WKOR_ID").toString()+"'");
                query.append("                                )                                 ");
                query.append("                )                                                 ");
            }
        }
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    private String getAndQuery(String columnName, Map columnMap)
    {
        String f = "AND 1 = 2";
        if("".equals(columnMap) | "undefined".equals(columnMap) | columnMap == null ) return f;
        
        String colStr = "";
        if(columnName.indexOf(".") > 0) colStr = columnName.split("\\.")[1];
        else colStr = columnName;
        
        for(Object obj:columnMap.keySet()){
            String key = (String) obj;
            if(key.toUpperCase().equals(colStr.toUpperCase())){
                String columnValue = String.valueOf(columnMap.get(key));
                if("".equals(columnValue)) return f;
                
                if("NULL".equals(columnValue.toUpperCase()))
                {
                    return f;
                }
                else return "  AND  "+ columnName  + " = '" + columnValue+"'";
            }
        }
        
        return "";
    }

    @Override
    public List getDataRQC10(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                                                                                                                ");
        query.append("     (select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0271' and aa.lang = ? )    as TITLE                           ");
        query.append("    ,a.comp_no                                                                                                      as COMP_NO                        ");
        query.append("    ,a.woreq_no                                                                                                     as WOREQ_NO                       ");
        query.append("    ,a.woreq_status                                                                                                 as STATUS                         ");
        query.append("    ,SFACODE_TO_DESC(a.woreq_status,'WOREQ_STATUS','SYS','','"+user.getLangId()+"')                                 as WOREQ_STATUS                   ");
        query.append("    ,a.description                                                                                                  as WOREQ_TITLE                    ");
        query.append("    ,a.req_emp_id                                                                                                   as REQ_EMP_ID                     ");
        query.append("    ,a.rec_emp_id                                                                                                   as REC_EMP_ID                     ");
        query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.req_emp_id = aa.emp_id)                                                   ");
        query.append("     || '/' || (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.req_dept_id = aa.dept_id) as EMP_NAME                       ");
        query.append("    ,a.req_phone                                                                                                    as REQ_PHONE                      ");
        query.append("    ,substr(a.req_date,0,4) || '-' ||substr(a.req_date,5,2) || '-' ||substr(a.req_date,7,2)                         as REQ_DATE                       ");
        query.append("    ,case when a.req_com_date is not null                                                                                                             ");
        query.append("            then  substr(a.req_com_date,0,4) || '-' ||substr(a.req_com_date,5,2) || '-' ||substr(a.req_com_date,7,2)                                  ");
        query.append("            else '-' end                                                                                             as REQ_COM_DATE                   ");
        query.append("    ,nvl((select aa.key_name from talang aa where key_type = 'CODESET' and key_no = 'REQ_PRIORITY.' || a.req_priority and lang = ? ),'-') as REQ_PRIORITY      ");
        query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.rec_emp_id = aa.emp_id)                                                   ");
        query.append("     || '/' || (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.rec_dept_id = aa.dept_id)            as REC_NAME           ");
        query.append("    ,nvl((select aa.description from taequipment aa where a.comp_no = aa.comp_no and a.equip_id = aa.equip_id ),'-')           as EQUIP_NAME         ");
        query.append("    ,nvl((select aa.full_desc from taeqloc aa where a.comp_no = aa.comp_no and a.eqloc_id = aa.eqloc_id ),'-')                 as LOC_NAME           ");
        query.append("    ,replace(replace(nvl(a.request,'-'), chr(10), '<br>' ),' ', '&nbsp;')                                                      as REQUEST            ");
        query.append("    ,replace(replace(nvl(a.review,'-'), chr(10), '<br>' ),' ', '&nbsp;')                                                       as REVIEW             ");
        query.append("from taworeq a                                                                                                                                        ");
        query.append("where a.comp_no = ?                                                                                                                                   ");
        query.append("    and a.woreq_id = ?                                                                                                                                ");

        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataWRK10(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                                                                                                                ");
        query.append("     (select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0275' and aa.lang = ? )    as TITLE                          ");
        query.append("    ,a.comp_no                                                                                                     as COMP_NO                        ");
        query.append("    ,a.wkor_id                                                                                                     as WKOR_ID                        ");
        query.append("    ,a.dept_id                                                                                                     as DEPT_ID                        ");
        query.append("    ,a.wo_no                                                                                                     as WO_NO                       ");
        query.append("    ,SFACODE_TO_DESC(a.wo_status,'WO_STATUS','SYS','',?)                                                         as WO_STATUS                   ");
        query.append("    ,a.description                                                                                               as WO_TITLE                    ");
        query.append("    ,substr(a.wkor_date,0,4) || '-' ||substr(a.wkor_date,5,2) || '-' ||substr(a.wkor_date,7,2)    AS WKOR_DATE  ");
        query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.emp_id = aa.emp_id)                                                   ");
        query.append("     || '/' || (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.dept_id = aa.dept_id) as EMP_NAME                       ");
        query.append("    ,(SELECT bb.description FROM TAEQUIPMENT bb WHERE bb.comp_no = a.comp_no AND bb.equip_id = (SELECT aa.equip_id FROM TAWOEQUIP aa WHERE aa.comp_no = a.comp_no AND aa.wkor_id = a.wkor_id and rownum=1)) AS EQUIP");
        query.append("    ,nvl((SELECT aa.full_desc FROM TAEQASMB aa WHERE a.comp_no = aa.comp_no ANd a.eqasmb_id = aa.eqasmb_id),'-') AS EQASMB ");
        query.append("    ,replace(replace(nvl(a.perform,'-'), chr(10), '<br>' ),' ', '&nbsp;')                                                      as PERFORM            ");
        query.append("from taworkorder a                                                                                                                                        ");
        query.append("where a.comp_no = ?                                                                                                                                   ");
        query.append("    and a.wkor_id = ?                                                                                                                                ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataPRI10(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                                                                                        ");
        query.append("        (SELECT aa.key_name FROM TALANG aa WHERE aa.key_type = 'MESSAGE' AND aa.key_no = 'MSG0276' AND aa.lang = ? ) AS TITLE ");
        query.append("       ,x.ptprlist_no                         AS PTPRLISTNO                                                                   ");
        query.append("       ,SFACODE_TO_DESC(x.ptprlist_status,'PTPRLIST_STATUS','SYS','',?)    AS PTPRLISTSTATUSDESC                              ");
        query.append("       ,x.description                         AS PTPRLISTDESC                                                                 ");
        query.append("       ,CASE WHEN LENGTH(x.req_date) = 8                                                                                      ");
        query.append("             THEN SUBSTR(x.req_date,1,4) || '-' || SUBSTR(x.req_date,5,2) || '-' || SUBSTR(x.req_date,7,2)                    ");
        query.append("             ELSE x.REQ_DATE                                                                                                  ");
        query.append("             END                              AS REQDATE                                                                      ");
        query.append("       ,(SELECT description                                                                                                   ");
        query.append("         FROM TADEPT a                                                                                                        ");
        query.append("         WHERE a.comp_no = x.comp_no                                                                                          ");
        query.append("           AND a.dept_id = x.dept_id)         AS DEPTDESC                                                                     ");
        query.append("       ,replace(replace(nvl(x.remark,'-'), chr(10), '<br>' ),' ', '&nbsp;') AS REMARK                                         ");
        query.append("       ,x.comp_no                             AS COMP_NO                                                                      ");
        query.append("       ,x.rec_by                              AS REC_BY                                                                       ");
        query.append("FROM   TAPTPRLIST x                                                                                                           ");
        query.append("WHERE  x.comp_no = ?                                                                                                          ");
        query.append("  AND  x.ptprlist_id = ?                                                                                                      ");

        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataISS10(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("     (SELECT aa.key_name FROM talang aa WHERE aa.key_type = 'MESSAGE' AND aa.key_no = 'MSG0277' AND aa.lang = ? ) AS TITLE            ");
        query.append("    ,a.ptisslist_id                                                                                               AS PTISSLIST_NO     ");
        query.append("    ,SFACODE_TO_DESC(a.ptiss_status,'PTISS_STATUS','SYS','',?)                                                    AS PTISS_STATUS     ");
        query.append("    ,substr(a.issue_date,1,4) || '-' ||substr(a.issue_date,5,2) || '-' ||substr(a.issue_date,7,2)                 AS ISSUE_DATE       ");
        query.append("    ,SFACODE_TO_DESC(a.ptiss_type,'PTISS_TYPE','SYS','',?)                                                        AS PTISS_TYPE       ");
        query.append("    ,nvl((SELECT wname FROM TAWAREHOUSE aa                                                                                            ");
        query.append("        WHERE aa.comp_no=a.comp_no AND aa.wcode_id=a.wcode_id),'-')                                               AS WCODE_NAME       ");
        query.append("    ,(SELECT part_no FROM TAPARTS WHERE comp_no=a.comp_no AND part_id=a.part_id)                                  AS PART_NO          ");
        query.append("    ,(SELECT description||', '||nvl(pt_size,'') FROM TAPARTS WHERE comp_no=a.comp_no AND part_id=a.part_id)       AS PART_DESC        ");
        query.append("    ,a.issue_qty                                                                                                  AS ISSUE_QTY        ");
        query.append("    ,a.comp_no                                                                                                    AS COMP_NO          ");
        query.append("    ,a.rec_by                                                                                                     AS REC_BY           ");
        query.append("FROM TAPTISSLIST a                                                                                                                    ");
        query.append("where a.comp_no = ?                                                                                                                   ");
        query.append("and a.ptisslist_id = ?                                                                                                                ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataQNA20(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                                ");
        query.append("     (select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0278' and aa.lang = ? ) AS TITLE            ");
        query.append("     ,y.helpdesk_no                                                                                               AS HELPDESK_NO      ");
        query.append("     ,SFACODE_TO_DESC(y.helpdesk_status,'HELPDESK_STATUS','SYS','',?)                                             AS HELPDESK_STATUS  ");
        query.append("     ,y.description                                                                                               AS HELPDESK_TITLE   ");
        query.append("     ,(SELECT emp_name                                                                                                                ");
        query.append("      FROM   TAEMP                                                                                                                    ");
        query.append("      WHERE comp_no = y.comp_no                                                                                                       ");
        query.append("        AND emp_id = y.req_by)                                                                                    AS REQ_BY_DESC      ");
        query.append("     ,case when y.req_date is not null                                                                                                ");
        query.append("            then  substr(y.req_date,1,4) || '-' ||substr(y.req_date,5,2) || '-' ||substr(y.req_date,7,2)                              ");
        query.append("            else '-' end                                                                                          AS REQ_DATE         ");
        query.append("     ,y.view_name                                                                                                 AS VIEW_BY          ");
        query.append("     ,case when y.view_date is not null                                                                                               ");
        query.append("            then  substr(y.view_date,1,4) || '-' ||substr(y.view_date,5,2) || '-' ||substr(y.view_date,7,2)                           ");
        query.append("            else '-' end                                                                                          AS VIEW_DATE        ");
        query.append("     ,replace(replace(nvl(y.request,'-'), chr(10), '<br>' ),' ', '&nbsp;')                                        AS REQUEST          ");
        query.append("     ,replace(replace(nvl(y.perform,'-'), chr(10), '<br>' ),' ', '&nbsp;')                                        AS PERFORM          ");
        query.append("     ,y.comp_no                                                                                                   AS COMP_NO          ");
        query.append("     ,y.req_by                                                                                                    AS REQ_BY           ");
        query.append("FROM   TAHELPDESK y                                                                                                                   ");
        query.append("WHERE  1=1                                                                                                                            ");
        query.append("  AND  y.comp_no  = ?                                                                                                                 ");
        query.append("  AND  y.helpdesk_id  = ?                                                                                                             ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataUSR10(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    (select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0279' and aa.lang = ? ) AS TITLE       ");
        query.append("    ,(select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0281' and aa.lang = ? ) AS SUB_TITLE       ");
        query.append("    ,a.comp_no    AS COMP_NO      ");
        query.append("    ,a.emp_id     AS EMP_ID       ");
        query.append("    ,a.user_no    AS USER_NO      ");
        query.append("    ,a.user_name  AS USER_NAME    ");
        query.append("    ,a.otp_key    AS OTP_KEY      ");
        query.append("FROM TAUSER a                     ");
        query.append("WHERE a.comp_no = ?               ");
        query.append("AND a.user_id   = ?               ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataUSR20(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    (select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0282' and aa.lang = ? ) AS TITLE       ");
        query.append("    ,(select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0281' and aa.lang = ? ) AS SUB_TITLE       ");
        query.append("    ,a.comp_no    AS COMP_NO      ");
        query.append("    ,a.emp_id     AS EMP_ID       ");
        query.append("    ,a.user_no    AS USER_NO      ");
        query.append("    ,a.user_name  AS USER_NAME    ");
        query.append("    ,a.otp_key    AS OTP_KEY      ");
        query.append("    ,a.password   AS PASSWORD     ");
        query.append("FROM TAUSER a                     ");
        query.append("WHERE a.comp_no = ?               ");
        query.append("AND a.user_id   = ?               ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataPPR10(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                                                                                ");
        query.append("        (select aa.key_name from talang aa where aa.key_type = 'MESSAGE' and aa.key_no = 'MSG0276' and aa.lang = ? ) AS TITLE ");
        query.append("      , x.ptpnlist_no                                                                AS PTPNLISTNO     ");
        query.append("      , SFACODE_TO_DESC(x.ptpnlist_status, 'PTPNLIST_STATUS', 'SYS', '',?)           AS PTPNLISTSTATUS ");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 b.part_no                                                                             ");
        query.append("            FROM TAPARTS  b                                                                            ");
        query.append("            WHERE b.comp_no = x.comp_no                                                                ");
        query.append("              AND b.part_id = x.part_id )                                            AS PARTNO         ");
        query.append("      , x.description                                                                AS PARTDESC       ");
        query.append("      , x.rec_qty                                                                    AS RECQTY         ");
        query.append("      , x.pt_size                                                                    AS PTSIZE         ");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 c.description                                                                         ");
        query.append("            FROM TADEPT c                                                                              ");
        query.append("           WHERE c.comp_no = x.comp_no                                                                 ");
        query.append("             AND c.dept_id = x.dept_id )                                             AS DEPTDESC       ");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 d.emp_name                                                                            ");
        query.append("            FROM TAEMP d                                                                               ");
        query.append("           WHERE d.comp_no = x.comp_no                                                                 ");
        query.append("             AND d.emp_id = x.user_id )                                              AS EMPNAME,       ");
        query.getDate("x.req_date", "REQDATE");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 e.description                                                                         ");
        query.append("            FROM TAPLANT e                                                                             ");
        query.append("           WHERE e.comp_no = x.comp_no                                                                 ");
        query.append("             AND e.plant = x.plant )                                                 AS PLANTDESC      ");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 f.description                                                                         ");
        query.append("            FROM TAEQUIPMENT f                                                                         ");
        query.append("           WHERE f.comp_no = x.comp_no                                                                 ");
        query.append("             AND f.equip_id = x.equip_id )                                           AS EQUIPDESC      ");
        query.append("      , nvl(x.usage,'-')                                                             AS USAGE          ");
        query.append("      , replace(replace(nvl(x.remark,'-'), chr(10), '<br>' ),' ', '&nbsp;')          AS REMARK         ");
        query.append("      , x.comp_no                                                                    AS COMP_NO        ");
        query.append("      , x.rec_by                                                                     AS REC_BY         ");
        query.append("      , x.rec_dept                                                                   AS REC_DEPT       ");
        query.append("  FROM TAPTPNLIST x                                                                                    ");
        query.append(" WHERE comp_no     = ?                                                                                 ");
        query.append("   AND ptpnlist_id = ?                                                                                 ");

        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getSubListPRI10(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    ");
        query.append("     (SELECT part_no                                      ");
        query.append("      FROM TAPARTS a                                      ");
        query.append("      WHERE a.comp_no = x.comp_no                         ");
        query.append("        AND a.part_id = x.part_id)        PARTNO          ");
        query.append("     , x.description ||',' || x.PT_SIZE   PARTDESC        ");
        query.append("     , x.rec_qty                          RECQTY          ");
        query.append("FROM TAPTPRITEM x                                         ");
        query.append("WHERE x.comp_no = ?                                       ");
        query.append("  AND x.ptprlist_id = ?                                   ");
        query.append("ORDER BY x.description                                    ");

        Object[] objects = new Object[] {
                user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getApprList(String apprListId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("    apprlist_id AS APPRLIST_ID    ");
        query.append("    ,appr_type AS APPR_TYPE       ");
        query.append("    ,appr_status AS APPR_STATUS   ");
        query.append("    ,object_id AS OBJECT_ID       ");
        query.append("    ,title AS TITLE               ");
        query.append("FROM TAAPPRLIST                   ");
        query.append("WHERE comp_no   = ?               ");
        query.append("and apprlist_id = ?               ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,apprListId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataAPP10_REQWORK(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                                                                                                                ");
        query.append("    a.woreq_no                                                                                                      as WOREQ_NO                       ");
        query.append("    ,SFACODE_TO_DESC(a.woreq_status,'WOREQ_STATUS','SYS','',?)                                                      as WOREQ_STATUS                   ");
        query.append("    ,a.description                                                                                                  as WOREQ_TITLE                    ");
        query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.req_emp_id = aa.emp_id)                                                   ");
        query.append("     || '/' || (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.req_dept_id = aa.dept_id) as EMP_NAME                       ");
        query.append("    ,a.req_phone                                                                                                    as REQ_PHONE                      ");
        query.append("    ,substr(a.req_date,0,4) || '-' ||substr(a.req_date,5,2) || '-' ||substr(a.req_date,7,2)                         as REQ_DATE                       ");
        query.append("    ,case when a.req_com_date is not null                                                                                                             ");
        query.append("            then  substr(a.req_com_date,0,4) || '-' ||substr(a.req_com_date,5,2) || '-' ||substr(a.req_com_date,7,2)                                  ");
        query.append("            else '-' end                                                                                             as REQ_COM_DATE                   ");
        query.append("    ,nvl((select aa.key_name from talang aa where key_type = 'CODESET' and key_no = 'REQ_PRIORITY.' || a.req_priority and lang = ? ),'-') as REQ_PRIORITY      ");
        query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.rec_emp_id = aa.emp_id)                                                   ");
        query.append("     || '/' || (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.rec_dept_id = aa.dept_id)            as REC_NAME           ");
        query.append("    ,nvl((select aa.description from taequipment aa where a.comp_no = aa.comp_no and a.equip_id = aa.equip_id ),'-')           as EQUIP_NAME         ");
        query.append("    ,nvl((select aa.full_desc from taeqloc aa where a.comp_no = aa.comp_no and a.eqloc_id = aa.eqloc_id ),'-')                 as LOC_NAME           ");
        query.append("    ,replace(replace(nvl(a.request,'-'), chr(10), '<br>' ),' ', '&nbsp;')                                                      as REQUEST            ");
        query.append("from taworeq a                                                                                                                                        ");
        query.append("where a.comp_no = ?                                                                                                                                   ");
        query.append("    and a.woreq_id = ?                                                                                                                                ");

        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataAPP10_PTBUYREQ(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                                ");
        query.append("        x.ptprlist_no                                                         AS PTPRLISTNO                                           ");
        query.append("       ,SFACODE_TO_DESC(x.ptprlist_status,'PTPRLIST_STATUS','SYS','',?)       AS PTPRLISTSTATUSDESC                                   ");
        query.append("       ,x.description                                                         AS PTPRLISTDESC                                         ");
        query.append("       ,CASE WHEN LENGTH(x.req_date)= 8                                                                                               ");
        query.append("             THEN SUBSTR(x.req_date,1,4) || '-' || SUBSTR(x.req_date,5,2) || '-' || SUBSTR(x.req_date,7,2)                            ");
        query.append("             ELSE x.REQ_DATE                                                                                                          ");
        query.append("             END                                                              AS REQDATE                                              ");
        query.append("       ,(SELECT description                                                                                                           ");
        query.append("         FROM TADEPT a                                                                                                                ");
        query.append("         WHERE a.comp_no = x.comp_no                                                                                                  ");
        query.append("           AND a.dept_id = x.dept_id)                                         AS DEPTDESC                                             ");
        query.append("       ,x.REMARK                                                              AS REMARK                                               ");
        query.append("FROM   TAPTPRLIST x                                                                                                                   ");
        query.append("WHERE  x.comp_no = ?                                                                                                                  ");
        query.append("  AND  x.ptprlist_id = ?                                                                                                              ");

        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }
    
    @Override
    public List getSubListAPP10_PTBUYREQ(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("     (SELECT part_no                                      ");
        query.append("      FROM TAPARTS a                                      ");
        query.append("      WHERE a.comp_no = x.comp_no                         ");
        query.append("      AND a.part_id = x.part_id)              PARTNO      ");
        query.append("     , x.description                          PARTDESC    ");
        query.append("     , (SELECT seller                                     ");
        query.append("        FROM TAPARTS a                                    ");
        query.append("        WHERE a.comp_no = x.comp_no                       ");
        query.append("        AND a.part_id = x.part_id)            SELLER      ");
        query.append("     , (SELECT model                                      ");
        query.append("        FROM TAPARTS a                                    ");
        query.append("        WHERE a.comp_no = x.comp_no                       ");
        query.append("        AND a.part_id = x.part_id)            MODEL       ");
        query.append("     , x.pt_size                              PTSIZE      ");
        query.append("     , x.rec_qty                              RECQTY      ");
        query.append("     , NVL(x.unit_price,0)                    UNITPRICE   ");
        query.append("     , NVL(x.unit_price,0)*NVL(x.rec_qty,0)   TOTALPRICE  ");
        query.append("     , x.remark                               REMARK      ");
        query.append("FROM TAPTPRITEM x                                         ");
        query.append("WHERE x.comp_no = ?                                       ");
        query.append("  AND  x.ptprlist_id = ?                                  ");
        query.append("ORDER BY x.ptprlist_id DESC                               ");

        Object[] objects = new Object[] {
                user.getCompNo()
                ,objectId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataAPP10_PTSTKTAKE(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                            ");
        query.append("       x.ptstktakelist_no                                                     PTSTKTAKELIST_NO    ");
        query.append("      ,SFACODE_TO_DESC (x.ptstktake_status,'PTSTKTAKE_STATUS','SYS','',?)     PTSTKTAKE_STATUS    ");
        query.append("      ,x.description                                                          DESCRIPTION         ");
        query.append("      ,x.plan_date                                                            PLAN_DATE           ");
        query.append("      ,act_date                                                               ACT_DATE            ");
        query.append("      ,(SELECT wname                                                                              ");
        query.append("        FROM TAWAREHOUSE                                                                          ");
        query.append("        WHERE comp_no = x.comp_no AND wcode_id = x.wcode_id)                  WNAME               ");
        query.append("      ,(SELECT a.emp_name                                                                         ");
        query.append("        FROM TAEMP a                                                                              ");
        query.append("        WHERE a.comp_no = x.comp_no AND a.emp_id = x.reg_id)                                      ");
        query.append("       || ' / '                                                                                   ");
        query.append("       || (SELECT description                                                                     ");
        query.append("           FROM TADEPT a                                                                          ");
        query.append("           WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id)             EMP_NAME            ");
        query.append(" FROM TAPTSTKTAKELIST x                                                                           ");
        query.append(" WHERE 1=1                                                                                        ");
        query.append("        AND x.comp_no = ?                                                                         ");
        query.append("        AND x.ptstktakelist_id = ?                                                                ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getCompNo()
                ,objectId
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getDataAPP10_WORKORDER(String objectId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                                                                                                                ");
        query.append("     a.wo_no                                                                                                     as WO_NO                       ");
        query.append("    ,(select aa.emp_name   from taemp aa where a.comp_no = aa.comp_no and a.emp_id = aa.emp_id)                                                   ");
        query.append("     || '/' || (select aa.description   from tadept aa where a.comp_no = aa.comp_no and a.dept_id = aa.dept_id) as EMP_NAME                       ");
        query.append("    ,(SELECT aa.full_desc FROM TAEQLOC aa WHERE a.comp_no = aa.comp_no ANd a.eqloc_id = aa.eqloc_id) AS EQLOC ");
        query.append("    ,(SELECT bb.description FROM TAEQUIPMENT bb WHERE bb.comp_no = a.comp_no AND bb.equip_id = (SELECT aa.equip_id FROM TAWOEQUIP aa WHERE aa.comp_no = a.comp_no AND aa.wkor_id = a.wkor_id and rownum=1)) AS EQUIP");
        query.append("    ,substr(a.start_date,0,4) || '-' ||substr(a.start_date,5,2) || '-' ||substr(a.start_date,7,2)||' '||substr(a.start_time,0,2) || ':' ||substr(a.start_time,3,2)                         AS START_DATE  ");
        query.append("    ,substr(a.end_date,0,4) || '-' ||substr(a.end_date,5,2) || '-' ||substr(a.end_date,7,2)||' '||substr(a.end_time,0,2) || ':' ||substr(a.end_time,3,2)                         AS END_DATE  ");
        query.append("    ,substr(a.wkor_date,0,4) || '-' ||substr(a.wkor_date,5,2) || '-' ||substr(a.wkor_date,7,2)    AS WKOR_DATE  ");
        query.append("    ,replace(replace(nvl(a.perform,'-'), chr(10), '<br>' ),' ', '&nbsp;')                                                      as PERFORM            ");
        query.append("from taworkorder a                                                                                                                                        ");
        query.append("where a.comp_no = ?                                                                                                                                   ");
        query.append("    and a.wkor_id = ?                                                                                                                                ");

        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,objectId
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List getApprUsrList(String apprListId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                                                        ");
        query.append("       appr_seq                                                                                                                   as APPRSEQ                  ");
        query.append("       ,SFACODE_TO_DESC(x.apprusr_status,'APPRUSR_STATUS','SYS','',?)                                                             as APPRUSRSTATUS            ");
        query.append("       ,y.emp_name                                                                                                                as APPRBYNAME               ");
        query.append("       ,(CASE WHEN y.grade is not null then y.grade ELSE 'N/A' END)                                                               as GRADE                    ");
        query.append("       ,(SELECT a.description                                                                                                                                 ");
        query.append("        FROM   TADEPT a                                                                                                                                       ");
        query.append("        WHERE  a.comp_no = y.comp_no and a.dept_id = y.dept_id)                                                                    as DEPTNAME                ");
        query.append("       ,SFACODE_TO_DESC(x.apprusr_action,'APPRUSR_ACTION','SYS','',?)                                                              as APPRUSRACTION           ");
        query.append("       ,case when x.appr_date is not null                                                                                                                     ");
        query.append("            then  substr(x.appr_date,0,4) || '-' ||substr(x.appr_date,5,2) || '-' ||substr(x.appr_date,7,2)                                                   ");
        query.append("                      || ' ' || substr(x.appr_time,0,2) || ':' || substr(x.appr_time,3,2) || ':' || substr(x.appr_time,5,2)                                   ");
        query.append("            else '-' end                                                                                                           as APPRTIME                ");
        query.append("      ,replace(replace(nvl(x.REMARK,'-'), chr(10), '<br>' ),' ', '&nbsp;')                                                         as REMARK                  ");
        query.append("FROM TAAPPRUSR x inner join TAEMP y on x.comp_no = y.comp_no and x.appr_by = y.emp_id                                                                         ");
        query.append("WHERE 1=1                                                                                                                                                     ");
        query.append("  AND  x.comp_no = ?                                                                                                                                          ");
        query.append("  AND  x.apprlist_id  = ?                                                                                                                                     ");
        query.append("ORDER BY x.appr_date,x.appr_time, x.appr_seq                                                                                                                  ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,apprListId
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

    @Override
    public List findApprDrafter(String apprListId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                        ");
        query.append("        b.emp_id AS EMP_ID                                    ");
        query.append("        ,b.emp_no AS EMP_NO                                   ");
        query.append("        ,b.e_mail AS E_MAIL                                   ");
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
        query.append("    and b.e_mail is not null                                  ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,user.getCompNo()
                ,apprListId
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects);
    }
    
}