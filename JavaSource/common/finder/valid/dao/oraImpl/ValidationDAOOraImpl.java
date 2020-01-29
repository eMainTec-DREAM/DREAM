package common.finder.valid.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.finder.valid.dao.ValidationDAO;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

/**
 * Validation DAOIml
 * @author  javaworker
 * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
 * @since   1.0
 *
 * @spring.bean id="validationDAOTarget"
 * @spring.txbn id="validationDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ValidationDAOOraImpl extends BaseJdbcDaoSupportOra implements ValidationDAO
{
    
    /**
     * TACDSYSD
     * find  Code of Description
     * @author  javaworker
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
     * @since   1.0
     * 
     * @param code
     * @param dirType
     * @return
     */
    public List findSysDirDescCode(String desc, String dirType, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT CDSYSD_NO as code,  								");
        query.append("       nvl((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+user.getLangId()+"'			");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       description										");
        query.append("FROM   TACDSYSD x											");
        query.append("WHERE  1=1												");
        query.append("  AND  cdsysm_id = (SELECT cdsysm_id						");
        query.append("						FROM TACDSYSM						");
        query.append("						WHERE list_type='" + dirType + "'	");
        query.append("					)										");
        query.append(" AND SFACODE_TO_DESC(cdsysd_no,list_type,'SYS','','"+user.getLangId()+"') = '"+desc+"'");
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findSysDirCode(String code, String dirType, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT CDSYSD_NO as code,                                 ");
        query.append("       nvl((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+user.getLangId()+"'			");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       description										");
        query.append("FROM   TACDSYSD x                                         ");
        query.append("WHERE  1=1                                                ");
        query.append("  AND  cdsysm_id = (SELECT cdsysm_id                      ");
        query.append("                      FROM TACDSYSM                       ");
        query.append("                      WHERE list_type='" + dirType + "'   ");
        query.append("                  )                                       ");
        query.append("  AND  CDSYSD_NO = '" + code + "'                         ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * TACDSYSD
     * find  Description of Code 
     * @author  ssong
     * @version $Id $
     * @since   1.0
     * 
     * @param code
     * @param dirType
     * @return
     */
    public List findSysDirCodeDesc(String code, String dirType,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT  cdsysd_no ,                                       ");
        query.append("       nvl((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+user.getLangId()+"'			");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       description										");
        query.append("FROM   TACDSYSD x											");
        query.append("WHERE  1=1												");
        query.append("  AND  cdsysm_id = (SELECT cdsysm_id						");
        query.append("						FROM TACDSYSM						");
        query.append("						WHERE list_type='" + dirType + "'	");
        query.append("					)										");
        query.append("  AND  CDSYSD_NO = '" + code + "'                              ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }
    
    /**
     * 사용자코드 테이블에서 코드로 desc찾기
     * @author  ssong
     * @version $Id $
     * @since   1.0
     * 
     * @param code
     * @param dirType
     * @param compNo
     * @return
     */
    public String findUsrDirCodeDesc(String code, String dirType, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT description									");
        query.append("FROM   TACDUSRD										");
        query.append("WHERE  1=1											");
        query.append("  AND  cdusrm_id = (SELECT cdusrm_id					");
        query.append("						FROM TACDUSRM					");
        query.append("						WHERE dir_type='" + dirType + "'");
        query.append("						  AND comp_no='"+compNo+"'		");
        query.append("					)									");
        query.append("	AND  p_cdusrd_id = 0								");
        query.append("  AND  cdusrd_no = '" + code + "'						");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        if(resultList.size()>1){
        	return "";
        }else{
        	return QueryBuffer.listToString(resultList);
        }
        
    }
    
    /**
     * TACDSYSD
     * find  Description of id 
     * @author  ssong
     * @version $Id $
     * @since   1.0
     * 
     * @param code
     * @return
     */
    public List findSysDirIdDesc(String id, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       nvl((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+user.getLangId()+"'			");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       description										");
        query.append("FROM   TACDSYSD x											");
        query.append("WHERE  1=1												");
        query.append("  AND  cdsysd_id = " + id + "                             ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 사용자코드 테이블에서 id로 desc찾기
     * @author  ssong
     * @version $Id $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public String findUsrDirIdDesc(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT description									");
        query.append("FROM   TACDUSRD										");
        query.append("WHERE  1=1											");
        query.append("  AND  cdusrd_id = '" + id + "'						");
        query.append("  AND  comp_no = '" + compNo + "'						");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        if(resultList.size()>1){
        	return "";
        }else{
        	return QueryBuffer.listToString(resultList);
        }
        
    }
    
    /**
     * TACDUSRD
     * find  Code of Description
     * @author  javaworker
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
     * @since   1.0
     * 
     * @param code
     * @param dirType
     * @param compNo
     * @return
     */
    public List findUsrDirDescCode(String desc, String dirType, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT cdusrd_no                                      ");
        query.append("       ,description                                   ");
        query.append("FROM   TACDUSRD										");
        query.append("WHERE  1=1											");
        query.append("  AND  cdusrm_id = (SELECT cdusrm_id					");
        query.append("						FROM TACDUSRM					");
        query.append("						WHERE dir_type='" + dirType + "'");
        query.append("						  AND comp_no='"+compNo+"'		");
        query.append("					)									");
        query.append("	AND  p_cdusrd_id = 0								");
        query.append("  AND  description = '" + desc + "'					");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findUsrDirCode(String desc, String dirType, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT cdusrd_no                                      ");
        query.append("       ,description                                   ");
        query.append("FROM   TACDUSRD                                       ");
        query.append("WHERE  1=1                                            ");
        query.append("  AND  cdusrm_id = (SELECT cdusrm_id                  ");
        query.append("                      FROM TACDUSRM                   ");
        query.append("                      WHERE dir_type='" + dirType + "'");
        query.append("                        AND comp_no='"+compNo+"'      ");
        query.append("                  )                                   ");
        query.append("  AND  p_cdusrd_id = 0                                ");
        query.append("  AND  cdusrd_no = '" + desc + "'                     ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * sequence nextval
     * @author  wondo
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
     * @since   1.0
     * 
     * @param sequenceName
     * @return
     */
    public String findNextVal(String sequenceName)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT " + sequenceName + ".nextVal       ");
        query.append("FROM   DUAL                               ");

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
    
    /**
     * no nextval(Next No) 다음 넘버 가져오기(ex:eqloc_no)
     * @author  kim21017
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
     * @since   1.0
     * 
     * @param tableName
     * @param columnName
     * @param compNo
     * @return
     */
    public String findNextNoVal(String tableName, String columnName, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        if (tableName.equals("TAFAILURE")){  //고장코드는 2자리로 셋팅...
        	query.append("SELECT 																				");
	        query.append("		TRIM(TO_CHAR(NVL(MAX(TO_NUMBER("+columnName+")),100) +1, '000')) as nextEqLocNo	");
	        query.append("FROM "+tableName+"																			");
	        query.append("WHERE TRANSLATE("+columnName+",'x0123456789','x') IS NULL									");
	        query.getAndQuery("comp_no", compNo);
        }else{
	        query.append("SELECT 																				");
	        query.append("		TRIM(TO_CHAR(NVL(MAX(TO_NUMBER("+columnName+")),10000) +1, '00000')) as nextEqLocNo	");
	        query.append("FROM "+tableName+"																			");
	        query.append("WHERE TRANSLATE("+columnName+",'x0123456789','x') IS NULL									");
	        query.getAndQuery("comp_no", compNo);
        }

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
    
    
    /**
     * menu desc 로 code 검색
     * @author  kim21017
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 kim21017 Exp $
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findMenuDesc(String desc, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       x.menu_id,									");
        query.append("       (SELECT key_name							");
        query.append("        FROM TALANG								");
        query.append("        WHERE key_type='MENU'						");
        query.append("       	AND key_no = x.key_no					");
        query.append("       	AND lang = '"+lang+"') description		");
        query.append("  FROM TAMENU x									");
        query.append(" WHERE 1=1										");
        query.getAndQuery("x.is_use","Y");
        query.append("   AND key_no =	( SELECT key_no					");
    	query.append("					FROM TALANG						");
    	query.append("					WHERE key_type='MENU'			");
    	query.getAndQuery("key_name", desc);
    	query.getAndQuery("lang", lang);
    	query.append("				)									");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * page desc 로 code 검색
     * @author  kim21017
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 kim21017 Exp $
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findPageDesc(String desc)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("       x.page_id,                 ");
        query.append("       x.description              ");
        query.append("  FROM TAPAGE x                   ");
        query.append(" WHERE x.description='"+ desc+"'  ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * button desc 로 code 검색
     * @author  kim21017
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 kim21017 Exp $
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findButtonDesc(String desc)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("       x.button_id,               ");
        query.append("       x.description description  ");
        query.append("  FROM TABUTTON x                 ");
        query.append(" WHERE x.description='"+ desc+"'  ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEmpNo(String desc, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       emp_id      code,                                  ");
        query.append("       emp_no      description                            ");
        query.append("FROM   TAEMP x                                            ");
        query.append("WHERE  x.emp_no   = '"+ desc+"'                           ");
        query.append("  AND x.comp_no = '"+compNo+"'                            ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findUserName(String desc, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                     ");
        query.append("       user_id      userId,                                ");
        query.append("       user_no      userNo,                                ");
        query.append("       user_name    userName,                              ");
        query.append("       e_mail       email                                  ");
        query.append("FROM   TAUSER x                                            ");
        query.append("WHERE  x.user_name   = '"+ desc+"'                           ");
        query.append("  AND x.comp_no = '"+compNo+"'                             ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEmpName(String desc, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       emp_id      code,                                  ");
        query.append("       emp_no      empNo,                                 ");
        query.append("       emp_name    description                            ");
        query.append("FROM   TAEMP x                                            ");
        query.append("WHERE  x.emp_name = '"+ desc+"'                           ");
        query.append("  AND x.comp_no = '"+compNo+"'                            ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEmpId(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       emp_id      code,                                  ");
        query.append("       emp_no      empNo,                                 ");
        query.append("       emp_name    description                            ");
        query.append("FROM   TAEMP x                                            ");
        query.append("WHERE  TO_CHAR(x.emp_no) = '"+ id+"'                      ");
        query.append("  AND x.comp_no = '"+compNo+"'                            ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param pageId
     * @return
     */
    public List findCdUsrdDesc(String expCode, String desc)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       cdusrd_id     code,                                ");
        query.append("       description    description                         ");
        query.append("FROM   TACDUSRD x                                         ");
        query.append("WHERE  x.cdusrm_id   = '"+ expCode+"'                     ");
        query.append("  AND  x.description = '"+ desc+"'                        ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findCdUsrdCode(String expCode, String desc)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       cdusrd_id     code,                                ");
        query.append("       description    description                         ");
        query.append("FROM   TACDUSRD x                                         ");
        query.append("WHERE  x.cdusrm_id   = '"+ expCode+"'                     ");
        query.append("  AND  x.cdusrd_no = '"+ desc+"'                          ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param pageId
     * @return
     */
    public List findDeptDesc(String desc,String expCode)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       dept_id        code,                               ");
        query.append("       dept_no        deptNo,                             ");
        query.append("       description    description                         ");
        query.append("FROM   TADEPT x                                           ");
        query.append("WHERE  x.description = '"+ desc+"'                        ");
        if("IS_LOWEST_LVL".equals(expCode)){
        	query.getAndQuery("x.is_lowest_lvl", "Y");
        }else{
        	query.getAndQuery("x.dept_categ", expCode);
        }
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findDeptCode(String desc, String expCode)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       dept_id        code,                               ");
        query.append("       dept_no        deptNo,                             ");
        query.append("       description    description                         ");
        query.append("FROM   TADEPT x                                           ");
        query.append("WHERE  x.dept_no = '"+ desc+"'                            ");
        if("IS_LOWEST_LVL".equals(expCode)){
        	query.getAndQuery("x.is_lowest_lvl", "Y");
        }else{
        	query.getAndQuery("x.dept_categ", expCode);
        }
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param pageId
     * @return
     */
    public List findUsrGrpDesc(String desc)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       usrgrp_id     code,                                ");
        query.append("       group_name    description                          ");
        query.append("FROM   TAUSRGRP x                                         ");
        query.append("WHERE  x.group_name = '"+ desc+"'                         ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param compNo
     * @param codeKind
     * @return
     */
    public List findEqLocDesc(String desc, String compNo, String extCode)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       eqloc_id      AS code,                             ");
        query.append("       full_desc     AS description                       ");
        query.append("FROM   TAEQLOC x                                          ");
        query.append("WHERE  x.full_desc = '"+ desc+"'                          ");
        query.append("  AND  x.comp_no     = '"+ compNo+"'                      ");
        query.getAndQuery("x.eqloc_lvl_type", extCode);
        query.append("  AND  x.is_use      = 'Y'                                ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqLocFullDesc(String desc, String compNo, String expCode)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       eqloc_id       code,                               ");
        query.append("       full_desc      description                         ");
        query.append("FROM   TAEQLOC x                                          ");
        query.append("WHERE  x.full_desc   = '"+ desc+"'                        ");
        query.append("  AND  x.comp_no     = '"+ compNo+"'                      ");
        if("IS_LOWEST_LVL".equals(expCode))
        query.getAndQuery("x.is_lowest_lvl", "Y");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param compNo
     * @param codeKind
     * @return
     */
    public List findEqCtgDesc(String desc, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       eqctg_id       code,                               ");
        query.append("       full_desc      description                         ");     
        query.append("FROM   TAEQCTG x                                          ");
       	query.append("WHERE  x.description = '"+ desc+"'                        ");
        query.append("  AND  x.comp_no     = '"+ compNo+"'                      ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqCtgFullDesc(String desc, String compNo, String expCode)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       eqctg_id       code,                               ");
        query.append("       full_desc      description                         ");
        query.append("FROM   TAEQCTG x                                          ");
        query.append("WHERE  x.full_desc   = '"+ desc+"'                        ");
        query.append("  AND  x.comp_no     = '"+ compNo+"'                      ");
        if("IS_LOWEST_LVL".equals(expCode))
            query.getAndQuery("x.is_lowest_lvl", "Y");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqCtgAsmbDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       eq_ctg_asmb_id     code,                           ");
        query.append("       description        description                     ");
        query.append("FROM   TAEQCTGASMB x                                      ");
        query.append("WHERE  x.description = '"+ desc+"'                        ");
        query.append("  AND  x.eqctg_id    = '"+expCode+"'                      ");
        query.append("  AND  x.comp_no     = '"+compNo+"'                       ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @param expCode2
     * @return
     */
    public List findEqAsmbDesc(String desc, String expCode,String compNo, String expCode2)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       eqasmb_id          code,                           ");
        query.append("       description        description                     ");
        query.append("FROM   TAEQASMB x                                         ");
        query.append("WHERE  x.description = '"+ desc+"'                        ");
        query.getAndQuery("x.equip_id", expCode2);
        query.append("  AND  x.comp_no     = '"+compNo+"'                       ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findFailureDesc(String desc, String expCode,String compNo, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       x.failure_id        code,                          ");
        query.append("       (select aa.key_name								");
        query.append("            from talang aa								");
        query.append("            where  aa.lang = '"+lang+"'					");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no)	description			");
        query.append("FROM   TAFAILURE x                                        ");
        query.append("WHERE  1=1                                                ");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getStringEqualQuery("x.fail_type", expCode);
        if(!"".equals(desc)&&desc!=null){
        	query.append("AND x.key_no IN	( SELECT key_no							");
        	query.append("					FROM TALANG								");
        	query.append("					WHERE key_type='FAILCODE'				");
        	query.getStringEqualQuery("lang", lang);
        	query.getStringEqualQuery("key_name", desc);
        	query.append("				)											");
        }
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findWcodeDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       wcode_id           code,                           ");
        query.append("       wname              description,                    ");
        query.append("       wh_type            whType,                         ");
        query.append("       x.out_wcode outWcode,                              ");
        query.append("       x.out_plant outPlant                               ");
        query.append("FROM   TAWAREHOUSE x                                      ");
        query.append("WHERE  x.wname       = '"+ desc+"'                        ");
        query.getAndQuery("x.wh_categ", "PART");
        query.append("  AND  x.comp_no     = '"+compNo+"'                       ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findTWcodeDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       wcode_id           code,                           ");
        query.append("       wname              description,                    ");
        query.append("       x.out_wcode outWcode,                              ");
        query.append("       x.out_plant outPlant                               ");
        query.append("FROM   TAWAREHOUSE x                                      ");
        query.append("WHERE  x.wname       = '"+ desc+"'                        ");
        query.getAndQuery("x.wh_categ", "TOOL");
        query.append("  AND  x.comp_no     = '"+compNo+"'                       ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * desc 로 id 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findVendorDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("       vendor_id    vendorId,                 ");
        query.append("       vendor_no    vendorNo,                 ");
        query.append("       description vendorDesc,                ");
        query.append("       address||', '||repname    addressRep,  ");
        query.append("       person       person,                   ");
        query.append("       office       office,                   ");
        query.append("       mobile       mobile,                   ");
        query.append("       email        email                     ");
        query.append("FROM   TAVENDOR x                             ");
        query.append("WHERE  x.description = '"+ desc+"'            ");
        query.append("  AND  x.comp_no     = '"+compNo+"'           ");
        query.getAndQuery("is_use", "Y");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * desc 로 id 검색(TACTCTR)
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param compNo
     * @return
     */
    public List findCtCtrDesc(String desc, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("       ctctr_id       AS ctCtrId,                 ");
        query.append("       description    AS ctCtrDesc                ");
        query.append("FROM   TACTCTR x                                  ");
        query.append("WHERE  x.description = '"+ desc+"'                ");
        query.append("  AND  x.comp_no     = '"+compNo+"'               ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * desc 로 id 검색(TAWKCTR)
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param compNo
     * @return
     */
    public List findWkCtrDesc(String desc, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                                            ");
    	query.append("       wkctr_id       AS wkCtrId,                 ");
    	query.append("       description    AS wkCtrDesc                ");
    	query.append("FROM   TAWKCTR x                                  ");
    	query.append("WHERE  x.description = '"+ desc+"'                ");
    	query.append("  AND  x.comp_no     = '"+compNo+"'               ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * key_name으로 key_type,key_no찾기
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @param lang
     * @return
     */
    public List findLangDesc(String desc, String expCode,String compNo, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                  ");
        query.append("       key_type    keyType,             ");
        query.append("       key_no      keyNo                ");
        query.append("FROM   TALANG x                         ");
        query.append("WHERE  x.key_name    = '"+ desc+"'      ");
        query.append("  AND  x.lang    = '"+ lang+"'          ");
        query.getAndQuery("key_type", expCode);
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * partNo로 partId찾기
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findPartsDesc(String desc, String expCode,String compNo, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       part_id     code,                                  ");
        query.append("       part_no     description,                           ");
        query.append("       description||','||pt_size     nameSize,            ");
        query.append("       maker maker,										");
        query.append("       model model,										");
        query.append("       plf_type plfType,									");
        query.append("       SFACODE_TO_DESC(plf_type,'PLF_TYPE','SYS','','"+lang+"') plfTypeDesc,");
        query.append("       '1'								 useQty,		");
        query.append("       last_price							lastPrice		");
        query.append("FROM   TAPARTS x                                          ");
        query.append("WHERE  1 = 1                                              ");
//        query.append("  AND  x.part_no     like '"+ desc+"%'                        ");
        query.append("  AND  x.part_no     = '"+desc+"'                         ");
        query.append("  AND  x.comp_no     = '"+compNo+"'                       ");
        query.getAndQuery("x.part_categ", "SPPT");
        query.append("ORDER BY  x.part_no                        ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * partNo로 partId찾기
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findTpartsDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       part_id     code,                                  ");
        query.append("       part_no     description,                           ");
        query.append("       description||','||pt_size     nameSize             ");
        query.append("FROM   TAPARTS x                                          ");
        query.append("WHERE  x.part_no     = '"+ desc+"'                        ");
        query.append("  AND  x.comp_no     = '"+compNo+"'                       ");
        query.getAndQuery("x.part_categ", "TOOL");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * partNo로 partId찾기2
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findReqPartsDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       part_id     code,                                  ");
        query.append("       part_no     description,                           ");
        query.append("       description ptName,                                ");
        query.append("       pt_size     ptSize                                 ");
        query.append("FROM   TAPARTS x                                          ");
        query.append("WHERE  x.part_no     = '"+ desc+"'                        ");
        query.append("  AND  x.comp_no     = '"+compNo+"'                       ");
        query.getAndQuery("x.part_categ", "SPPT");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 자산찾기
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqAssetDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                 ");
        query.append("       asset_id     code,              ");
        query.append("       asset_no     aseetNo,           ");
        query.append("       description  assetDesc,         ");
        query.append("       TO_CHAR(TO_DATE (acq_date,'yyyy-mm-dd'),'yyyy-mm-dd')	acqDate,	");
        query.append("       TO_CHAR(buyer_amt,'FM999,999,999,999') buyerAmt,					");
        query.append("       TO_CHAR(dep_amt,'FM999,999,999,999') depAmt,						");
        query.append("       TO_CHAR(res_amt,'FM999,999,999,999') resAmt,						");
        query.append("       asset_no     description        ");
        query.append("FROM   TAASSET x                       ");
        query.append("WHERE  x.asset_no     = '"+ desc+"'    ");
        query.append("  AND  x.comp_no     = '"+compNo+"'    ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 무정지라인찾기
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findLineDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                   ");
        query.append("       pop_line_id  code,                ");
        query.append("       pop_line_name  lineDesc           ");
        query.append("FROM   TAPOPLINE x                       ");
        query.append("WHERE  x.pop_line_name     = '"+ desc+"' ");
        query.append("  AND  x.comp_no     = '"+compNo+"'      ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 설비기안 품의서 찾기
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqAppDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                 ");
        query.append("       eqapp_id  eqAppId,              ");
        query.append("       title     title,                ");
        query.append("       contents  contents,             ");
        query.append("       receiver  receiver,             ");
        query.getDate("req_date", "reqDate");
        query.append("       ,                               ");
        query.getDate("app_date", "appDate");
        query.append("FROM   TEEQAPPLIST x                   ");
        query.append("WHERE  x.eqapp_no     = '"+ desc+"'    ");
        query.append("  AND  x.comp_no     = '"+compNo+"'    ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 수리기안 품의서 찾기
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findPtAppDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("       title      title,              ");
        query.getDate("rec_date", "recDate,");
        query.append("       eq_desc    eqDesc,             ");
        query.append("       tot_amt    totAmt,             ");
        query.append("       contents   contents,           ");
        query.append("FROM   TEPTAPPLIST x                  ");
        query.append("WHERE  x.ptapp_id    = '"+ desc+"'    ");
        query.append("  AND  x.comp_no     = '"+compNo+"'   ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * MES LINE 
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findMesLineDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                            ");
        query.append("       mes_line_id       AS mesLineId,            ");
        query.append("       mes_line_name     AS mesLineName           ");
        query.append("FROM   TAMESLINE x                                ");
        query.append("WHERE  x.mes_line_name = '"+ desc+"'              ");
        query.append("  AND  x.comp_no     = '"+compNo+"'               ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * WO_NO
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * @return
     */
    public List findWoNo(String desc, String expCode,String compNo,String expCode2,String expCode3, String lang)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                   	                      			");
        query.append("       wkor_id                      			AS wkorId,           		");
        query.append("       wo_no                      			AS woNo,             		");
        query.append("       description              				AS woDesc,           		");
        query.append("       SFACODE_TO_DESC(wo_status,'WO_STATUS','SYS','','"+lang+"') AS woStatusDesc,     ");
        query.append("       (SELECT MAX(b.description) 				             			");
        query.append("        FROM TAWOEQUIP a, TAEQUIPMENT b                        			");
        query.append("        WHERE a.comp_no = b.comp_no                            			");
        query.append("         AND a.equip_id = b.equip_id                           			");
        query.append("         AND a.wkor_id = x.wkor_id                             			");
        query.append("         AND a.comp_no = x.comp_no                             			");
        query.append("         AND ROWNUM = 1		)  				AS equipDesc,        		");
        query.append("       (SELECT MAX(c.full_desc)                           	 			");
        query.append("        FROM TAWOEQUIP a, TAEQUIPMENT b, TAEQLOC c             			");
        query.append("        WHERE a.comp_no = b.comp_no                            			");
        query.append("         AND b.comp_no = c.comp_no                             			");
        query.append("         AND a.equip_id = b.equip_id                           			");
        query.append("         AND b.eqloc_id = c.eqloc_id                           			");
        query.append("         AND a.wkor_id = x.wkor_id                             			");
        query.append("         AND a.comp_no = x.comp_no                             			");
        query.append("         AND ROWNUM = 1		)   			AS eqLocDesc         		");
        query.append("FROM TAWORKORDER x                                                        ");
        query.append("WHERE  x.description = '"+ desc+"'                                        ");
        query.append("  AND  x.comp_no     = '"+compNo+"'                                       ");
        
        if(!"".equals(expCode2)){
        	if(expCode2.indexOf("|")>-1){
        		String[] arr = expCode2.split("\\|");
        		String str = "";
        		for (int i = 0; i < arr.length; i++) {
					str += "'"+arr[i]+"',";
				}
        		str = str.substring(0,str.length()-1);
        		query.append("AND x.pm_type IN ("+str+")	");
        	}else{
        		query.getAndQuery("x.pm_type", expCode2);
        	}
        }
        
        query.getAndQuery("x.wo_status", expCode3);
        query.getAndQuery("x.wo_type", expCode);
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * 설비
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqDesc(String desc, String expCode,String compNo, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        

        query.append("SELECT															");
        query.append("		x.equip_id									equipId,		");
        query.append("		x.description								equipDesc,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQLOC												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqloc_id = x.eqloc_id)				eqLocDesc,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQCTG												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqctg_id = x.eqctg_id)				eqCtgDesc,		");
        query.append("		x.eqloc_id									eqLocId,		");
        query.append("		x.item_no									itemNo,			");
        query.append("		x.maker										maker,			");
        query.append("		x.model_no									modelNo,		");
        query.append("		SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+lang+"')	plfTypeDesc,");
        query.append("		 x.is_law_eq								isLawEq,		");
        query.append("		(SELECT description											");
        query.append("		   FROM TADEPT												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND dept_id = x.dept_id)				deptDesc,		");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.main_mng_id)				mainMngName,	");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.sub_mng_id)				subMngName		");
        query.append("FROM   TAEQUIPMENT x                       ");
        query.append("WHERE  x.description = '"+ desc+"'    ");
        query.append("  AND  x.comp_no     = '"+compNo+"'    ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqCode(String desc, String expCode,String compNo, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        

        query.append("SELECT                                                            ");
        query.append("      x.equip_id                                  equipId,        ");
        query.append("      x.description                               equipDesc,      ");
        query.append("      (SELECT full_desc                                           ");
        query.append("         FROM TAEQLOC                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc,      ");
        query.append("      (SELECT full_desc                                           ");
        query.append("         FROM TAEQCTG                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtgDesc,      ");
        query.append("      x.eqloc_id                                  eqLocId,        ");
        query.append("      x.item_no                                   itemNo,         ");
        query.append("      x.maker                                     maker,          ");
        query.append("      x.model_no                                  modelNo,        ");
        query.append("      SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+lang+"')   plfTypeDesc,");
        query.append("       x.is_law_eq                                isLawEq,        ");
        query.append("      (SELECT description                                         ");
        query.append("         FROM TADEPT                                              ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND dept_id = x.dept_id)                deptDesc,       ");
        query.append("      (SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName,    ");
        query.append("      (SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName      ");
        query.append("FROM   TAEQUIPMENT x                       ");
        query.append("WHERE  x.item_no = '"+ desc+"'    ");
        query.append("  AND  x.comp_no     = '"+compNo+"'    ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * vendor명으로 vendorId찾기
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqVendorDesc(String desc, String expCode,String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("       vendor_id    vendorId,                 ");
        query.append("       description  vendorDesc,               ");
        query.append("       address||', '||rep_name addressRep,    ");
        query.append("       person       person,                   ");
        query.append("       office       office,                   ");
        query.append("       mobile       mobile,                   ");
        query.append("       email        email                     ");
        query.append("FROM   TAVENDOR x                             ");
        query.append("WHERE  x.description = '"+ desc+"'            ");
        query.append("  AND  x.comp_no     = '"+compNo+"'           ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * YN
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findYn(String desc)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("       x.yn     code									");
        query.append("FROM   (SELECT 'Y' yn from DUAL						");
        query.append("			union all SELECT 'N' yn from DUAL ) x		");
        query.append("WHERE  x.yn     = '"+ desc+"'							");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * LEVEL(1,2,3,4)
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findLvl(String desc)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("       x.lvl     code									");
        query.append("FROM   (SELECT '1' lvl from DUAL						");
        query.append("			union all SELECT '2' lvl from DUAL 			");
        query.append("			union all SELECT '3' lvl from DUAL 			");
        query.append("			union all SELECT '4' lvl from DUAL ) x		");
        query.append("WHERE  x.lvl     = '"+ desc+"'						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findFileAttach(String code, String expCode, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                      ");
//        query.append("       DECODE((SELECT                       ");
//        query.append("                     x.secur_grade          ");
//        query.append("               FROM  TADOCUMENT x           ");
//        query.append("               WHERE x.doc_id = a.doc_id), '"+user.getSecurGrade()+"', a.docdata_id, '') docdata_id,              ");
        query.append("       a.docdata_id,                        ");
        query.append("       a.file_name,                         ");
        query.append("       a.file_ext,                          ");
        query.append("       a.nf_file_path nfFilePath,           ");
        query.append("       '"+expCode+"' expCode,               ");
        query.append("       (SELECT                              ");
        query.append("              x.secur_grade                 ");
        query.append("        FROM  TADOCUMENT x                  ");
        query.append("        WHERE x.doc_id = a.doc_id) securGrade, ");
        query.append("       a.file_size                          ");
        query.append("FROM   TADOCDATA a                          ");
        query.append("WHERE  a.doc_id = '" + code + "'            ");
        query.append("ORDER BY a.docdata_id DESC                  ");

        return getJdbcTemplate().queryForList(query.toString());
    }
      
    public List findImageAttach(String code, String expCode, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("       imgdata_id,                        ");
        query.append("       file_name,                         ");
        query.append("       file_ext,                          ");
        query.append("       nf_file_path nfFilePath,           ");
        query.append("       '"+expCode+"' expCode              ");
        query.append("FROM   TAIMGDATA                          ");
        query.append("WHERE  image_id = '" + code + "'          ");
        query.append("ORDER BY imgdata_id DESC                  ");

        return getJdbcTemplate().queryForList(query.toString());
    }

	public List findPlantDesc(String desc, String compNo) 
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("       plant,                             ");
        query.append("       description                        ");
        query.append("FROM   TAPLANT                            ");
        query.append("WHERE  description = '" + desc + "'       ");

        return getJdbcTemplate().queryForList(query.toString());
	}
}