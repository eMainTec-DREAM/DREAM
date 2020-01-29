package common.config.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.MwareConfig;
import common.bean.User;
import common.config.dao.ConfigDAO;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;

/**
 * 기본적인 환경설정을 DB에서 조회할때 
 * 공통으로 사용된다.
 * @author  javaworker
 * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
 * @since   1.0
 * @spring.bean id="configDAOTarget"
 * @spring.txbn id="configDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConfigDAOOraImpl extends BaseJdbcDaoSupportOra implements ConfigDAO
{
    List<Map<String,String>> listRslt = null;
    /**
     * T4CONFIG 의 설정을 조회한다. 
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @param name
     * @return
     * @throws Exception
     */
    public String[][] getConfigInfo(String name)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT name AS name, value$ AS VALUE          ");
        query.append("FROM   TACONFIG                               ");
        query.append("WHERE  1=1                                    ");
        if (name != null && !"".equals(name))
        {
            query.append("  AND  name = '" + name + "'                  ");
        }
        query.append("UNION ALL                                             ");
        query.append("SELECT 'INIT_CT_PATH' AS name, ct_path AS value       ");
        query.append("FROM TACOMP                                           ");
        query.append("WHERE init_ct_path_yn = 'Y'                           ");
        query.append("    and is_use  = 'Y'                                 ");
        query.append("UNION ALL                                             ");
        query.append("SELECT 'COMP_NO' AS name, comp_no AS value            ");
        query.append("FROM TACOMP                                           ");
        query.append("WHERE init_ct_path_yn = 'Y'                           ");
        query.append("    and is_use  = 'Y'                                 ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.toStringArray(resultList);
    }
    
    
    /**
     * Security Table 을 조회한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findSecurityList()
    {
        QueryBuffer query = new QueryBuffer();
     
        query.append("SELECT                            ");
        query.append("       x.usrgrp_id userGroup,     ");
        query.append("       x.pgpage_id objectId,      ");
        query.append("       'TAB' objectType           ");
        query.append("FROM   TAUGPGPGAU x               ");
        query.append("UNION ALL                         ");
        query.append("SELECT                            ");
        query.append("       y.usrgrp_id userGroup,     ");
        query.append("       y.pgbtn_id objectId,       ");
        query.append("       'BUTTON' objectType        ");
        query.append("FROM   TAUGPGBTNAU y              ");
        query.append("UNION ALL                         ");
        query.append("SELECT                            ");
        query.append("       z.usrgrp_id userGroup,     ");
        query.append("       z.page_id objectId,        ");
        query.append("       'PAGE' objectType          ");
        query.append("FROM   TAUGPGAU z                 ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 모든 page 와 button을 로딩한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findPageButton(String pageId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("       a.file_name as fileName,                       ");
        query.append("       (SELECT c.button_no                            ");
        query.append("        FROM   TABUTTON c                             ");
        query.append("        WHERE  c.button_id =x.button_id) buttonId,    ");
        query.append("       x.button_loc buttonLoc,                        ");
        query.append("       x.ord_no,                                      ");
        query.append("       'BUTTON.'||x.key_no remark, ");
//        query.append("       (SELECT key_name");
//        query.append("        FROM TALANG ");
//        query.append("        WHERE key_no  = x.key_no");
//        query.append("          AND key_type = 'BUTTON'");
//        query.append("          AND lang='ko') remark,       ");
        query.append("       x.pgbtn_id pgBtnId,                            ");
        query.append("       (SELECT c.btn_img                              ");
        query.append("        FROM   TABUTTON c                             ");
        query.append("        WHERE  c.button_id = x.button_id) buttonImg,  ");
        query.append("       a.is_chkauth isChkauth,                        ");
        query.append("       x.is_chkauth isChkauthBtn,                     ");
        query.append("       x.is_basic isBasic,                            ");
        query.append("       x.is_set_group isSetGroup                      ");
        query.append("FROM   TAPAGE a ,TAPGBTN x                            ");
        query.append("WHERE  a.page_id = x.page_id                          ");
        query.append("  AND  x.is_use = 'Y'                                 ");
        query.getAndQuery("a.file_name", pageId);
        query.append("ORDER BY 1,3, x.ord_no DESC                           ");
        
        return getJdbcTemplate().queryForList(query.toString());
        //return QueryBuffer.toStringArray(resultList);
    }
    
    /**
     * Security Table 을 조회한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findAllTabPages()
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                         ");
        query.append("       y.file_name pPageName,                  ");
//        query.append("       'TAB.'||y.file_name tabPageName,        ");
        query.append("       'TAB.'||(SELECT b.file_name FROM TAPAGE b WHERE b.page_id = x.c_page_id) tabPageName,      ");
        query.append("       x.key_type||'.'||x.key_no langKey,      ");
        query.append("       pgpage_id pgPageId,                     ");
        query.append("       y.page_id pageId,                       ");
        query.append("       x.is_use isUse,                         ");
        query.append("       (SELECT b.is_chkauth FROM TAPAGE b WHERE b.page_id = x.c_page_id) isChkauth                ");
        query.append("FROM   TAPGPAGE  x, TAPAGE y                   ");
        query.append("WHERE x.page_id = y.page_id                    ");
        query.append("  AND x.is_use = 'Y'                   ");
        /* query.append("  AND y.is_use = 'Y'                   ");*/
        query.append("ORDER BY x.page_id, TO_NUMBER(x.ord_no)        ");


        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * DB에 등록된 전체 page_id 의 menu_path를 조회한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findAllMenuPath()
    {
        QueryBuffer query = new QueryBuffer();
        

        query.append("SELECT (SELECT file_name FROM TAPAGE WHERE page_id=a.page_id) pageId,         ");
        query.append("       to_char(p_menu_id)                                     pMenuId,        ");
        query.append("       to_char(menu_id)                                       menuId,         ");
        query.append("       (SELECT file_name FROM TAPAGE WHERE page_id=a.page_id) menuPageId,     ");
        query.append("       a.description description,                                             ");
        query.append("       a.key_no keyNo                                                         ");
        query.append("FROM TAMENU a                                                                 ");
        query.append("WHERE page_id IS NOT NULL                                                     ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 접속 Log 기록
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @param user
     * @param path
     * @param sessionId 
     */
    public void saveAccessLog(User user, String path)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAACCESSCCLOG (                   ");
        query.append("  access_time                                 ");
        query.append("  ,comp_no                                    ");
        query.append("  ,user_id                                    ");
        query.append("  ,file_name                                  ");
        query.append("  )                                           ");
        query.append("VALUES (                                      ");
        query.append("  ?                                           ");
        query.append("  ,?                                          ");
        query.append("  ,?                                          ");
        query.append("  ,?                                          ");
        query.append(")                                             ");
        
        Object [] object = new Object[]{
                 DateUtil.getDateTime()
                ,user.getCompNo()
                ,user.getUserId()
                ,path
        };
        
        getJdbcTemplate().update(query.toString(), object);
    }


    /**
     * Grid Height
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param user
     * @param currentPageId
     * @param listId
     * @return
     */
    public String getGrdHeight(User user, String currentPageId, String listId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("        height                                        ");
        query.append("FROM TAPGGRID x                                       ");
        query.append("WHERE 1 = 1                                           ");
        query.getAndQuery("(SELECT b.file_name FROM TAPAGE b WHERE x.page_id = b.page_id)", currentPageId);
        query.getAndQuery("x.grid_obj_id", listId);
        
        List resultList = getJdbcTemplate().queryForList(query.toString());

        return QueryBuffer.listToString(resultList);

    }


    public List findAllPages()
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                  ");
        query.append("       page_id pageId,                  ");
        query.append("       file_name fileName,              ");
        query.append("       description,                     ");
        query.append("       REMARK,                          ");
        query.append("       x.key_type||'.'||key_no pageName,");
        query.append("       x.is_chkauth isChkauth           ");
        query.append("FROM   TAPAGE x                         ");
        query.append("WHERE x.is_use = 'Y'                    ");

        return getJdbcTemplate().queryForList(query.toString());
    }


    /**
     * Find Page Field Info
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @return
     */
    public List findPageFields()
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("       page_id pageId,                        ");
        query.append("       (SELECT b.file_name FROM TAPAGE b WHERE b.page_id = x.page_id) fileName,      ");
        query.append("       field_id fieldId,                      ");
        query.append("       hidden_yn hiddenYn,                    ");
        query.append("       ord_no ordNo,                          ");
        query.append("       display_yn displayYn,                  ");
        query.append("       x.key_type||'.'||x.key_no langKey      ");
        query.append("FROM   TAPGFIELD x                            ");
        query.append("ORDER BY ord_no DESC                          ");

        return getJdbcTemplate().queryForList(query.toString());
    }


    public String findLanguage() 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT code FROM (                                        ");
        query.append("SELECT                                                    ");
        query.append("       CDSYSD_NO code                                     ");
        query.append("FROM   TACDSYSD                                           ");
        query.append("WHERE  1=1                                                ");
        query.append("  AND  cdsysm_id IN (SELECT cdsysm_id                     ");
        query.append("                        FROM TACDSYSM                     ");
        query.append("                        WHERE list_type='LANG'            ");
        query.append("                    )                                     ");
        query.append("  AND  UPPER(is_use) = UPPER('Y')                         ");
        query.append("ORDER BY ord_no                                           ");
        query.append(") WHERE ROWNUM = 1                                        ");

        
        List resultList = getJdbcTemplate().queryForList(query.toString());

        return QueryBuffer.listToString(resultList);
    }
    
    public List findLanguages() 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       CDSYSD_NO code                                     ");
        query.append("FROM   TACDSYSD                                           ");
        query.append("WHERE  1=1                                                ");
        query.append("  AND  cdsysm_id IN (SELECT cdsysm_id                     ");
        query.append("                        FROM TACDSYSM                     ");
        query.append("                        WHERE list_type='LANG'            ");
        query.append("                    )                                     ");
        query.append("  AND  is_use = 'Y'                                       ");

        
        List resultList = getJdbcTemplate().queryForList(query.toString());

        return resultList;
    }
    
    public List findCompanies() 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COMP_NO code                                       ");
        query.append("       ,init_ct_path_yn init                              ");
        query.append("       ,package_no                                        ");
        query.append("FROM   TACOMP                                             ");
        query.append("WHERE 1=1                                                 ");
        query.append("    and is_use  = 'Y'                                     ");

        List resultList = getJdbcTemplate().queryForList(query.toString());

        return resultList;
    }


    @Override
    public List<Map> findDecoMap()
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       x.comp_no                                                  ");
        query.append("       ,x.bean_no                                                 ");
        query.append("       ,y.package_no                                              ");
        query.append("FROM   TACOMPFUNC x INNER JOIN TACOMP y ON x.comp_no = y.comp_no  ");
        query.append("WHERE  1=1                                                        ");
        query.append("  AND  x.is_use  = 'Y'                                            ");


        List resultList = getJdbcTemplate().queryForList(query.toString());

        return resultList;
    }

    @Override
    public void saveErrorLog(String errorlogId, String stackTrace, User loginUser, String url) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAERRORLOG (                          ");
        query.append("    errorlog_id,             comp_no,             ");
        query.append("    user_no,                 req_url,             ");
        query.append("    stacktrace_clob,                              ");
        query.append("    err_date                                      ");
        query.append("    )                                             ");
        query.append("VALUES (                                          ");
        query.append("    ?,                       ?,                   ");
        query.append("    ?,                       ?,                   ");
        query.append("    ?,                                            ");
        query.append("    SYSDATE                                       ");
        query.append(")                                                 ");

        
        Object [] object = new Object[]{
                errorlogId,
                loginUser.getCompNo(),
                loginUser.getUserNo(),
                url,
                stackTrace
        };
        
        getJdbcTemplate().update(query.toString(), object);
    }


    @Override
    public List findPageLinkes() 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                ");
        query.append("       y.page_id pageId                                                               ");
        query.append("       ,x.linkedfunc_method linkedfuncMethod                                          ");
        query.append("       ,x.linkedfunc_no linkedfuncNo                                                  ");
        query.append("       ,y.linkedfunc_loc likedfuncLoc                                                 ");
        query.append("       ,z.file_name fileName                                                          ");
        query.append("       ,y.key_type||'.'||y.key_no linkedLabel                                         ");
        query.append("FROM   TALINKEDFUNC x INNER JOIN TAPGLINKEDFUNC y                                     ");
        query.append("  ON   x.linkedfunc_id = y.linkedfunc_id AND x.is_use = 'Y' AND y.is_use = 'Y'        ");
        query.append("       INNER JOIN TAPAGE z ON z.page_id = y.page_id                                   ");
        query.append("ORDER BY  y.page_id, y.linkedfunc_loc, y.ord_no DESC                                  ");

        List resultList = getJdbcTemplate().queryForList(query.toString());

        return resultList;
    }


    @Override
    public List findSysCodes() 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("       cdsysd_no cdsysdNo,                ");
        query.append("       list_type listType,                ");
        query.append("       description,                       ");
        query.append("       param1,                            ");
        query.append("       param2,                            ");
        query.append("       x.key_type||'.'||x.key_no codeLabel        ");
        query.append("       ,y.lang    ");
        query.append("       ,REPlACE(REPLACE(REPLACE(y.key_name,CHR(10),''),CHR(13),''),CHR(0),'') AS keyName     ");
        query.append("FROM   TACDSYSD x INNER JOIN TALANG y ON x.key_type = y.key_type AND x.key_no = y.key_no                      ");
        query.append("INNER JOIN (SELECT cdsysd_no lang, is_use FROM TACDSYSD WHERE list_type ='LANG' AND is_use ='Y') z ON z.lang = y.lang AND z.is_use ='Y'           ");
        query.append("WHERE  x.is_use = 'Y'     ");
        query.append("ORDER BY list_type, ord_no                ");

        List resultList = getJdbcTemplate().queryForList(query.toString());

        return resultList;
    }


    @Override
    public int updateErrorLog(String errorlogId, String sTstr, User loginUser,
            String url) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAERRORLOG SET                                         ");
        query.append("       stacktrace_clob    = stacktrace_clob||?                ");
        query.append("WHERE  errorlog_id        = ?                                 ");
        
        Object[] objects = new Object[] {
                sTstr,
                errorlogId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }


    @Override
    public Map<String,String> executeBatch(int stActInt, String auditKey, String fileName, Map<String,String> auditMap, User user)
    {
        QueryBuffer query = new QueryBuffer();
        Map<String,String> rtnMap = new HashMap<String,String>();

        String dataCudType = null;
        if(stActInt >= 4000 && stActInt <5000)
        {
            dataCudType = "P";
            
            if(!"Y".equals(MwareConfig.getIsUseAuditTrailRead())) dataCudType ="";
        }
        else if(stActInt >= 5000 && stActInt <6000)
            dataCudType = "I";
        else if(stActInt >= 6000 && stActInt <7000)
            dataCudType = "U";
        else if(stActInt >= 7000 && stActInt < 8000)
            dataCudType = "D";
        else if(stActInt >= 8000 && stActInt < 9000)
        {
            dataCudType = "R";
            
            if(!"Y".equals(MwareConfig.getIsUseAuditTrailRead())) dataCudType ="";
        }

    
        String[] sqlArr = null;
        if("I".equals(dataCudType) || "U".equals(dataCudType) ||"R".equals(dataCudType)||"P".equals(dataCudType))
        {
                     
            if("R".equals(dataCudType))sqlArr = new String[1];
            else sqlArr = new String[1];
            
            int cnt = 0;
            String tracelogId = getNextSequence("SQATRACELOG_ID");
            
            rtnMap.put(auditKey, tracelogId);
            
            query.append("INSERT INTO TATRACELOG ( ");
            query.append(" update_time, tracelog_id,    user_no,        ");
            query.append(" emp_no,      dept_no,        data_cud_type,  ");
            query.append(" comp_no,     object_id,      file_name       ");
            query.append(" )                                            ");
            query.append("VALUES (                                      ");
            query.append(" to_char(sysdate,'YYYYmmddHH24MISS') ,  '"+tracelogId+"',       '"+user.getUserNo()+"',         ");
            query.append(" '"+user.getEmpNo()+"',               '"+user.getDeptNo()+"', '"+dataCudType+"',              ");
            query.append(" '"+user.getCompNo()+"',              '"+auditKey+"',         '"+fileName+"'                  ");
            query.append(")                                             ");
           
            sqlArr[cnt] = query.toString();
            
            int[] rslt = this.getJdbcTemplate().batchUpdate(sqlArr);
            
            
            listRslt = new ArrayList<Map<String,String>>();
            Map tempMap = null;
            for (Map.Entry<String, String> entry : auditMap.entrySet())
            {
                tempMap = new HashMap<String, String>();
                tempMap.put("key", entry.getKey());
                tempMap.put("value", entry.getValue());
                listRslt.add(tempMap);
                
            }
            
            QueryBuffer query2 = new QueryBuffer();
            if(!"R".equals(dataCudType))
            {
                query2.append("INSERT INTO TATRACELOGDTL (TRACELOGDTL_ID, TRACELOG_ID, FIELD_ID, FIELD_VALUE)                     ");
                query2.append("VALUES (SQATRACELOGDTL_ID.nextVal, '"+tracelogId+"', ?, ? )                                        ");  

                this.getJdbcTemplate().batchUpdate(query2.toString(), new BatchPreparedStatementSetter() {
                    
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {

                        Map<String,String> temMap = listRslt.get(i);
                        ps.setString(1, String.valueOf(temMap.get("key")));
                        ps.setString(2, String.valueOf(temMap.get("value")));
                    }
                            
                    @Override
                    public int getBatchSize() {
                        return listRslt.size();
                    }
                  });
            }
        }
        else if("D".equals(dataCudType))
        {
            String[] delKeys = null;
            for (Map.Entry<String, String> entry     : auditMap.entrySet())
            {
                if("deleteRows".equals(entry.getKey()))delKeys = entry.getValue().split("\\^\\|\\^");
            }
            
            sqlArr = new String[delKeys.length];
            int cnt = 0;
            String tracelogId = "";
            for(String aKey :delKeys)
            {
                if("".equals(aKey))continue;

                tracelogId = getNextSequence("SQATRACELOG_ID");
                
                rtnMap.put(aKey, tracelogId);
                
                query.setClear();
                query.append("INSERT INTO TATRACELOG ( ");
                query.append(" update_time, tracelog_id,    user_no,        ");
                query.append(" emp_no,      dept_no,        data_cud_type,  ");
                query.append(" comp_no,     object_id,      file_name       ");
                query.append(" )                                            ");
                query.append("VALUES (                                      ");
                query.append(" to_char(sysdate,'YYYYmmddHH24MISS') ,  '"+tracelogId+"',       '"+user.getUserNo()+"',         ");
                query.append(" '"+user.getEmpNo()+"',               '"+user.getDeptNo()+"', '"+dataCudType+"',              ");
                query.append(" '"+user.getCompNo()+"',              '"+aKey+"',         '"+fileName+"'                      ");
                query.append(")                                             ");

                sqlArr[cnt] = query.toString();
                cnt++;
            }

            int[] rslt = this.getJdbcTemplate().batchUpdate(sqlArr);
        }
        
        return rtnMap;
    }


    @Override
    public List<Map> findDecoJspMap()
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                ");
        query.append("       x.comp_no                                                      ");
        query.append("       ,x.file_name                                                   ");
        query.append("       ,y.package_no                                                  ");
        query.append("FROM   TACOMPFUNCJSP x INNER JOIN TACOMP y ON x.comp_no = y.comp_no   ");
        query.append("WHERE  1=1                                                            ");
        query.append("  AND  x.is_use  = 'Y'                                                ");

        List resultList = getJdbcTemplate().queryForList(query.toString());

        return resultList;
    }
}