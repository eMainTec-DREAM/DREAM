package dream.comm.dao.oraImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.comm.dao.MaFinderAcDAO;

/**
 * Validation DAOIml
 * @author  javaworker
 * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="maFinderAcDAOTarget"
 * @spring.txbn id="maFinderAcDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaFinderAcDAOOraImpl extends BaseJdbcDaoSupportOra implements MaFinderAcDAO
{
    public List findAutoCompleteCommon(String keySearchCol, String keySearchVal,
            Map<String, String> columnMap, String tableName,
            Map<String, String> conditionParam, Map<String, String> labelMap, String acLength, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        String columnName= "";
        String colNm = "";
        //if(acLength == "" || acLength == "null") acLength = "10";
        
        query.append("SELECT                                                    ");
        int colSeq = 1;
        for( String key : columnMap.keySet() ){
            
            if("1".equals(columnMap.get(key))){
                colSeq++;
                continue;
            }
            
//            if(labelMap != null && labelMap.containsKey(key)) colNm = columnMap.size() == colSeq? " \""+labelMap.get(key)+"\"":" \""+labelMap.get(key)+"\",";
//            else  colNm = columnMap.size() == colSeq? " \"Z"+colSeq+"\"":" \"Z"+colSeq+"\",";
            
            colNm = columnMap.size() == colSeq? "":",";
            
            columnName = String.valueOf(columnMap.get(key));
            columnName = colSeq == 1? "DISTINCT("+columnName+") Zdisplay":columnName;
            columnName = columnName+colNm;
            query.append(columnName);
            colSeq++;
        }
        query.append("FROM "+tableName);
        query.append("WHERE  1 = 1                                              ");
        query.getLikeQuery(keySearchCol, keySearchVal);
        for( String conditionColumn : conditionParam.keySet() ){
            if(conditionColumn.indexOf("CUSTOM") == 0)
            {
                query.append("AND "+convertString(conditionParam.get(conditionColumn)));
            }
            else query.getAndQuery(conditionColumn,convertString(conditionParam.get(conditionColumn)));
        }
        if(!(acLength == "" || acLength == "null")) query.append("AND rownum <= "+acLength+"                                 ");
        
        query.append("ORDER BY  1");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * 중복 채크
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param keySearchCol
     * @param keySearchVal
     * @param tableName
     * @param conditionParam
     * @return
     */
    public int findValCnt(String keySearchCol, String keySearchVal, String tableName,
            Map<String, String> conditionParam)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT COUNT(1)                                           ");
        query.append("FROM "+tableName);
        query.append("WHERE  1 = 1                                              ");
        query.getAndQuery(keySearchCol, keySearchVal);
        for( String conditionColumn : conditionParam.keySet() ){
            if(conditionColumn.indexOf("CUSTOM") == 0)
            {
                query.append("AND "+convertString(conditionParam.get(conditionColumn)));
            }
            else query.getAndQuery(conditionColumn,convertString(conditionParam.get(conditionColumn)));
        }
        
        String rtnCnt = QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
        
        return Integer.parseInt(rtnCnt);
    }

    public List findSysDirDescCode(String sValue, String codeType, User user)
    {
        QueryBuffer query = new QueryBuffer();
 
        query.append("SELECT                                                    ");
        query.append("       nvl((select aa.key_name                            ");
        query.append("            from talang aa                                ");
        query.append("            where  lang = '"+user.getLangId()+"'          ");
        query.append("            and x.key_type = aa.key_type                  ");
        query.append("            and x.key_no = aa.key_no), x.description)     ");
        query.append("       description,                                       ");
        query.append("       CDSYSD_NO                                          ");
        query.append("FROM   TACDSYSD x                                         ");
        query.append("WHERE  1=1                                                ");
        query.getAndQuery("x.is_use", "Y");
        query.append("  AND  cdsysm_id = (SELECT cdsysm_id                      ");
        query.append("                      FROM TACDSYSM                       ");
        query.append("                      WHERE list_type='" + codeType + "'  ");
        query.append("                  )                                       ");
        query.getLikeQuery("SFACODE_TO_DESC(cdsysd_no,list_type,'SYS','','"+user.getLangId()+"')", sValue);
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    
    public List findCdsysd(String keySearchVal,Map<String, String> colMap, Map<String, String> conditionParam, String lang, String acLength, User user) {

        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    												");
        query.append("		 nvl('['|| cdsysd_no ||'] '|| (SELECT aa.key_name    												");
        query.append("           							FROM TALANG aa                                						");
        query.append("            						   WHERE  lang = '"+lang+"'                      						");
        query.append("            							 AND x.key_type = aa.key_type                  						");
        query.append("            							 AND x.key_no = aa.key_no), '['|| cdsysd_no ||'] '|| x.description)	");
        query.append("       Zdisplay                                           												");
        query.append("     , nvl((SELECT aa.key_name                           													");
        query.append("              FROM TALANG aa                                												");
        query.append("             WHERE lang = '"+lang+"'                      												");
        query.append("            	 AND x.key_type = aa.key_type                  												");
        query.append("            	 AND x.key_no = aa.key_no), x.description)     												");        
        query.append("       description                                        												");
        query.append("     , cdsysd_no                                         													");
        query.append("     , param1                                            													");
        query.append("  FROM TACDSYSD x                                         												");
        query.append(" WHERE 1 = 1                                                												");
        query.getAndQuery("x.is_use", "Y");
        query.append("   AND cdsysm_id IN (SELECT cdsysm_id                     												");
        query.append("                       FROM TACDSYSM                       												");
        query.append("                      WHERE 1 = 1                         												");
        
        query.getAndQuery("list_type", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        query.getAndQuery("param1", conditionParam);
        query.getAndQuery("param2", conditionParam);
        query.getAndQuery("cdsysd_no", conditionParam);

        if("param22".equals(conditionParam.get("param22")))
        {
            query.append("  AND NVL(x.param2,' ') != 'PM'    ");
        }
        if(conditionParam.containsKey("p_list_type")){
            query.append("AND list_type IN(SELECT param1                        ");
            query.append("                 FROM TACDSYSD                        ");
            query.append("                 WHERE cdsysm_id=(SELECT cdsysm_id    ");
            query.append("                                  FROM TACDSYSM       ");
            query.append("                                  WHERE 1=1           ");
            query.getAndQuery("list_type", conditionParam.get("p_list_type"));
            query.append("                                  )                   ");
            query.append("                 )                                    ");
        }
        if(conditionParam.containsKey("p_cdsysd_no")){
            query.append("AND list_type IN(SELECT param1                        ");
            query.append("                 FROM TACDSYSD                        ");
            query.append("                 WHERE 1=1                            ");
            query.getAndQuery("cdsysd_no", conditionParam.get("p_cdsysd_no"));
            query.append("                 )                                    ");
        }
        
        query.append("                   )                                      ");
        query.getLikeQuery("SFACODE_TO_DESC(cdsysd_no,list_type,'SYS','','"+lang+"')", keySearchVal);
        query.append("ORDER BY  1                                               ");
        
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    public List findCdsysm(String keySearchVal,Map<String, String> colMap, Map<String, String> conditionParam, String lang, String acLength, User user) {

        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    ");
        query.append("       nvl((select aa.key_name                            ");
        query.append("            from talang aa                                ");
        query.append("            where  lang = '"+lang+"'                      ");
        query.append("            and x.key_type = aa.key_type                  ");
        query.append("            and x.key_no = aa.key_no), x.description)     ");
        query.append("       Zdisplay                                           ");
        query.append("       ,nvl((select aa.key_name                           ");
        query.append("            from talang aa                                ");
        query.append("            where  lang = '"+lang+"'                      ");
        query.append("            and x.key_type = aa.key_type                  ");
        query.append("            and x.key_no = aa.key_no), x.description)     ");
        query.append("       description                                        ");
        query.append("       ,list_type                                         ");
        query.append("       ,cdsysm_id                                         ");
        query.append("FROM   TACDSYSM x                                         ");
        query.append("WHERE  1=1                                                ");
        query.getAndQuery("is_use", conditionParam);
        query.getLikeQuery("(SELECT a.key_name FROM TALANG a WHERE 1=1 AND a.lang='"+lang+"' AND a.key_type=x.key_type AND a.key_no = x.key_no)", keySearchVal);
        query.append("ORDER BY list_type                                        ");
        
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    public List findCdusrm(String keySearchVal,Map<String, String> colMap, Map<String, String> conditionParam, String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
        

        query.append("SELECT                                                    ");
        query.append("       MAX(x.description) AS Zdisplay                     ");
        query.append("       ,MAX(x.description)    AS description              ");
        query.append("       ,x.dir_type                                        ");
        query.append("       ,MAX(x.cdusrm_id) As cdusrm_id                     ");
        query.append("FROM   TACDUSRM x                                         ");
        query.append("WHERE  1=1                                                ");
        query.getAndQuery("is_use", conditionParam);
        query.getLikeQuery("x.description", keySearchVal);
        query.append("GROUP BY x.dir_type                                       ");
        query.append("ORDER BY x.dir_type                                       ");
        
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    public List findUser(String keySearchVal,Map<String, String> colMap, Map<String, String> conditionParam, String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       DISTINCT(x.user_no||','||y.emp_name) AS Zdisplay           ");//First one only For Display
        query.append("       ,y.emp_name AS emp_name                                    ");
        query.append("       ,x.user_id  AS user_id                                     ");
        query.append("       ,x.comp_no  AS comp_no                                     ");
        query.append("FROM   TAUSER x, TAEMP y                                          ");
        query.append("WHERE  1 = 1                                                      ");
        query.append("  AND  x.emp_id=y.emp_id                                          ");
        query.append("  AND  x.is_use = 'Y'                                             ");
        query.getAndQuery("x.comp_no", conditionParam);
        if(conditionParam.containsKey("dept_id") && !"".equals(conditionParam.get("dept_id"))) {
            query.append("AND y.dept_id  IN (SELECT dept_id                                         ");
            query.append("                 FROM tadept                                              ");
            query.append("                 WHERE 1=1                                                ");
            query.getAndQuery("comp_no", conditionParam);
            query.append("                 START WITH dept_id ='"+conditionParam.get("dept_id")+"'  ");
            query.append("                 CONNECT BY PRIOR dept_id = p_dept_id)                    ");

        }
        query.getLikeQuery("y.emp_name", keySearchVal);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findEquipment(String keySearchVal, Map<String, String> colMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       DISTINCT('[' || item_no ||']'|| description)   AS Zdisplay ");//First one only For Display
        query.append("       ,description           AS description                      ");
        query.append("       ,equip_id              AS equip_id                         ");
        query.append("       ,item_no               AS item_no                          ");
        query.append("       ,eqloc_id              AS eqloc_id                         ");
        query.append("       ,(SELECT a.eqloc_no FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = x.eqloc_id)	AS eqloc_no	");
        query.append("       ,eqctg_id              AS eqctg_id                         ");
        query.append("       ,eq_status             AS eq_status                        ");
        query.append("       ,serial_no             AS serial_no                        ");
        query.append("       ,eqctg_type            AS eqctg_type                       ");
        query.append("       ,(SELECT full_desc FROM TAEQLOC WHERE comp_no = '"+user.getCompNo()+"' AND eqloc_id = x.eqloc_id) AS eqLocDesc");
        query.append("       ,(SELECT full_desc FROM TAEQCTG WHERE comp_no = '"+user.getCompNo()+"' AND eqctg_id = x.eqctg_id) AS eqCtgDesc");
        query.append("       ,SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"') AS eqStatusDesc");
        query.append("       ,(SELECT pmcalibstdtp_id FROM TAEQTOOL WHERE comp_no = '"+user.getCompNo()+"' AND equip_id = x.equip_id) AS pmcalibstdtp_id");
        query.append("       ,CASE WHEN x.ctctr_id IS NOT NULL          ");
        query.append("                  THEN x.ctctr_id                 ");
        query.append("              ELSE (SELECT a.ctctr_id             ");
        query.append("                      FROM TADEPT a               ");
        query.append("                      WHERE 1=1                   ");
        query.append("                      AND a.comp_no = x.comp_no   ");
        query.append("                      AND a.dept_id = x.dept_id   ");
        query.append("                  )                               ");
        query.append("          END ctCtrId                             ");
        query.append("       ,CASE WHEN x.ctctr_id IS NOT NULL          ");
        query.append("                  THEN (SELECT a.description                  ");
        query.append("                          FROM TACTCTR a                      ");
        query.append("                          WHERE a.comp_no = x.comp_no         ");
        query.append("                          AND a.ctctr_id = x.ctctr_id         ");
        query.append("                      )                                       ");
        query.append("              ELSE (SELECT (SELECT b.description              ");
        query.append("                              FROM TACTCTR b                  ");
        query.append("                              WHERE b.comp_no = a.comp_no     ");
        query.append("                              AND b.ctctr_id = a.ctctr_id     ");
        query.append("                              )                               ");
        query.append("                      FROM TADEPT a                           ");
        query.append("                      WHERE 1=1                               ");
        query.append("                      AND a.comp_no = x.comp_no               ");
        query.append("                      AND a.dept_id = x.dept_id               ");
        query.append("                  )                                           ");
        query.append("          END ctCtrDesc                                       ");

        query.append("        ,x.p_equip_id                         parEquipId,     ");
        query.append("        (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id) parEquipDesc,     ");
        query.append("        (SELECT b.description FROM TADEPT b WHERE b.comp_no = x.comp_no AND b.dept_id = (SELECT a.usage_dept FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id)  ) parEqUsaDeptDesc,               ");
        query.append("        (SELECT a.eqloc_id FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id) parEqLocId,      ");
        query.append("        (SELECT b.full_desc                                   ");
        query.append("           FROM TAEQLOC b                                     ");
        query.append("          WHERE b.comp_no = x.comp_no                         ");
        query.append("            AND b.eqloc_id = (SELECT a.eqloc_id FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id) )                parEqLocDesc       ");

        query.append("      , x.usage_dept                          usage_dept      ");
        query.append("      , (SELECT a.description                                 ");
        query.append("           FROM TADEPT a                                      ");
        query.append("          WHERE a.comp_no = x.comp_no                         ");
        query.append("            AND a.dept_id = x.usage_dept)     usageDeptDesc   ");
        query.append("		,(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant ) plantDesc	");
        query.append("FROM   TAEQUIPMENT x                                          ");
        query.append("WHERE  1 = 1                                                  ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.is_last_version", "Y");
        query.getStringEqualQuery("x.IS_DELETED", "N");
        query.getLikeQuery("'[' || item_no ||']'|| description", keySearchVal);
        query.getAndQuery("x.eq_status", conditionParam);
        query.getAndQuery("x.eqctg_type", conditionParam);
        query.getDeptLevelQuery("x.dept_id", conditionParam, user.getCompNo());
        query.getEqLocLevelQuery("x.eqloc_id", conditionParam, user.getCompNo());
        query.getEqCtgLevelQuery("x.eqctg_id", conditionParam, user.getCompNo());
        query.getAndQuery("x.plant", conditionParam);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findEquipLoc(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       DISTINCT('['|| eqloc_no ||']'|| full_desc)    AS Zdisplay  ");//First one only For Display
        query.append("       ,description           AS description                      ");
        query.append("       ,full_desc             AS full_desc                        ");
        query.append("       ,eqloc_id              AS eqloc_id                         ");
        query.append("       ,eqloc_no              AS eqLocNo                          ");
        query.append("       ,is_lowest_lvl         AS isLowestLvl                      ");
        query.append("FROM   TAEQLOC                                                    ");
        query.append("WHERE  1 = 1                                                      ");
        query.getLikeQuery("full_desc||eqloc_no", keySearchVal);
        
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("eqloc_lvl_type", conditionParam);
        query.getAndQuery("is_lowest_lvl", conditionParam);
        query.getAndQuery("plant", conditionParam);
        query.getAndQuery("is_use", "Y");
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findEquipCtg(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       DISTINCT(full_desc)    AS Zdisplay                         ");//First one only For Display
        query.append("       ,full_desc             AS full_desc                        ");
        query.append("       ,description           AS description                      ");
        query.append("       ,eqctg_id              AS eqctg_id                         ");
        query.append("       ,eqctg_no              AS eqctg_no                         ");
        query.append("       ,is_lowest_lvl         AS isLowestLvl                      ");
        query.append("FROM   TAEQCTG                                                    ");
        query.append("WHERE  1 = 1                                                      ");
        query.getAndQuery("comp_no", conditionParam);
        query.getLikeQuery("full_desc", keySearchVal);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findDept(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
        Map cols = new LinkedHashMap<String,String>();
        
        query.append("SELECT                                                            ");
        query.append("       x.dept_no ||', '|| x.description ||', '|| (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant) AS Zdisplay                      ");//First one only For Display
        query.append("       ,x.description                                             ");
        query.append("       ,x.dept_id                                                 ");
        query.append("       ,x.is_maint                                                ");
        query.append("       ,(SELECT a.emp_id                                          ");
        query.append("          FROM TAEMP a                                            ");
        query.append("          WHERE a.comp_no=x.comp_no                               ");
        query.append("          AND a.dept_id = x.dept_id                               ");
        query.append("          AND a.is_response='Y'                                   ");
        query.append("          AND rownum = 1) responseBy                              ");
        query.append("       ,(SELECT a.emp_name                                        ");
        query.append("          FROM TAEMP a                                            ");
        query.append("          WHERE a.comp_no=x.comp_no                               ");
        query.append("          AND a.dept_id = x.dept_id                               ");
        query.append("          AND a.is_response='Y'                                   ");
        query.append("          AND rownum = 1) responseDesc                            ");
        query.append("       , x.plant                                    plant         ");
        query.append("       , (SELECT a.description                                    ");
        query.append("          FROM TAPLANT a                                          ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("            AND a.plant = x.plant)                  plantDesc     ");
        query.append("       , x.wcode_id                                               ");
        query.append("       , (SELECT a.wname                                          ");
        query.append("          FROM TAWAREHOUSE a                                      ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("            AND a.wcode_id = x.wcode_id)            wname         ");
        query.append("FROM   TADEPT x                                                   ");
        query.append("WHERE  1 = 1                                                      ");
        query.getAndQuery("x.comp_no", conditionParam);
        query.getAndQuery("x.is_maint", conditionParam);
        query.getAndQuery("x.is_monitoring", conditionParam);
        query.getAndQuery("x.plant", conditionParam);
        query.getAndQuery("x.is_use", conditionParam);
        query.getAndQuery("x.dept_categ", conditionParam);
        query.getLikeQuery("x.description", keySearchVal);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findEmp(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                              		            ");
        query.append("      DISTINCT('[' || emp_no ||']'||emp_name                      ");//First one only For Display
        query.append("                  ||'(' || (SELECT description FROM TADEPT WHERE comp_no ='"+user.getCompNo()+"' AND dept_id = x.dept_id) ||')') AS Zdisplay");//First one only For Display
        query.append("      ,emp_name                                                   ");
        query.append("      ,emp_id                                                           ");
        query.append("      ,emp_no                                                           ");
        query.append("      ,dept_id                                                          ");
        query.append("        ,grade                                                          ");
        query.append("      ,(SELECT description FROM TADEPT WHERE comp_no ='"+user.getCompNo()+"' AND dept_id = x.dept_id) AS deptDesc");
        query.append("      ,comp_no  														  comp_no              ");
        query.append("		,(select y.description from tacomp y where y.comp_no = x.comp_no) compDesc             ");
        query.append("      ,m_phone                                                         ");
        query.append("      ,e_mail                                                          ");
        query.append("      ,plant                 plant                                     ");
        query.append("      ,(SELECT y.description FROM TAPLANT y WHERE y.plant = x.plant) plantDesc     ");
        query.append("       ,(SELECT wcode_id											");
        query.append("        FROM TADEPT												");
        query.append("        WHERE comp_no = x.comp_no									");
        query.append("        AND dept_id = x.dept_id)            		wcode_id		");
        query.append("       ,(SELECT WNAME												");
        query.append("        FROM TAWAREHOUSE 											");
        query.append("        WHERE comp_no = x.comp_no 								");
        query.append("                and WCODE_ID = (SELECT wcode_id					");
        query.append("                                FROM TADEPT						");
        query.append("                                WHERE comp_no = x.comp_no			");
        query.append("                                and dept_id = x.dept_id))  wname	");
        query.append("FROM  TAEMP x                                                     ");
        query.append("WHERE 1 = 1                                                       ");
        if(conditionParam.containsKey("comp_no")) {
            query.getAndQuery("comp_no", conditionParam);
        }
        else {
            query.getAndQuery("comp_no", user.getCompNo());
        }
        
        //근무여부 기본 값
        if(conditionParam.containsKey("is_join")) {
        	query.getAndQuery("is_join", conditionParam);
        }else{
        	query.getAndQuery("is_join", "Y");
        }
        
        query.getLikeQuery("emp_no || emp_name", keySearchVal);
        query.getDeptLevelQuery("dept_id", conditionParam, user.getCompNo());
        query.getAndQuery("plant", conditionParam);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findUserRptTable(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       DISTINCT(description)  AS Zdisplay                         ");//First one only For Display
        query.append("       ,table_name            AS table_name                       ");
        query.append("       ,table_id              AS table_id                         ");
        query.append("       ,description           AS description                      ");
        query.append("FROM   TATABLE                                                    ");
        query.append("WHERE  1 = 1                                                      ");
        query.getLikeQuery("table_name", keySearchVal);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findUserRpttabCol(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                                ");
        query.append("       DISTINCT('('||column_name||') '||DESCRIPTION) AS Zdisplay      ");//First one only For Display
        query.append("       ,tabcol_id         AS tabcol_id                                ");
        query.append("       ,column_name       AS column_name                              ");
        query.append("       ,table_id          AS table_id                                 ");
        query.append("       ,description       AS description                              ");
        query.append("FROM   TATABCOL                                                       ");
        query.append("WHERE  1 = 1                                                          ");
        query.getLikeQuery("column_name", keySearchVal);
        query.getAndQuery("table_id", conditionParam);
        query.append("ORDER BY  1                                                           ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }


    @Override
    public List findParts(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       DISTINCT(x.full_desc) AS Zdisplay                          ");
        query.append("       ,x.part_id      AS part_id                                 ");
        query.append("       ,x.part_no         AS part_no                              ");
        query.append("       ,x.description     AS description                          ");
        query.append("       ,x.full_desc   AS full_desc                                ");
        query.append("       ,x.maker       AS maker                                    ");
        query.append("       ,x.model       AS model                                    ");
        query.append("       ,x.uom         AS uom                                      ");
        query.append("       ,(SELECT SFACODE_TO_DESC(x.uom, 'UOM', 'USR', x.comp_no,'"+user.getLangId()+"') FROM DUAL) AS uom_desc ");
        query.append("       ,x.plf_type        AS plf_type                             ");
        query.append("       ,(SELECT SFACODE_TO_DESC(plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL) AS plf_type_desc ");
        query.append("       ,TO_CHAR(x.last_price,'999,999,999,999') AS last_price     ");
        query.append("       ,x.vendor_code AS vendor_code                              ");
        query.append("       ,x.pt_size     AS pt_size                                  ");
        query.append("       ,x.description||', '||pt_size AS partNameSize              ");
        query.append("       ,x.is_serial_part AS is_serial_part                        ");
        query.append("       ,(SELECT SUM(stock_qty)                                    ");
        query.append("          FROM TAPTSTOCK                                          ");
        query.append("         WHERE comp_no=x.comp_no                                  ");
        query.append("           AND part_id=x.part_id                                  ");
        query.getAndQuery("wcode_id", conditionParam);
        query.append("         ) stockQty                                               ");
        query.append("       ,x.erp_part_no  AS erp_part_no                             ");
        query.append("       ,x.seller       AS seller                                  ");
        query.append("       ,(SELECT SFACODE_TO_DESC(x.part_group, 'PART_GROUP', 'USR', x.comp_no,'"+user.getLangId()+"') FROM DUAL)  partGroupDesc       ");
        query.append("       ,currency      AS currency                               	");
        query.append("       ,SFACODE_TO_DESC(currency,'CURRENCY','SYS','','"+ user.getLangId() +"')	AS currencyDesc	");
        query.append("FROM  TAPARTS x                                                   ");
        query.append("WHERE 1 = 1                                                       ");
        query.getAndQuery("comp_no", conditionParam);
        query.getStringEqualQuery("IS_DELETED", "N");
        query.getLikeQuery("full_desc", keySearchVal);
        if(conditionParam.containsKey("wcode_id") && !"".equals(conditionParam.get("wcode_id"))) {
            query.append("AND part_id  IN (SELECT part_id                                        ");
            query.append("                 FROM taptstock                                       ");
            query.append("                 WHERE 1=1                                            ");
            query.getAndQuery("comp_no", conditionParam);
            query.append("                  and wcode_id = '"+conditionParam.get("wcode_id")+"')");
        }
        //설비부위
        if(conditionParam.containsKey("eqasmb_id") && (!"".equals(conditionParam.containsKey("eqasmb_id")) || !"".equals(conditionParam.containsKey("eqasmb_desc")) ) )
		{
        	query.append("AND EXISTS (SELECT 1 FROM TAEQPART a INNER JOIN TAEQUIPMENT b		");
        	query.append("                ON a.comp_no = b.comp_no							");
        	query.append("               AND a.equip_id = b.equip_id						");
        	query.append("             WHERE 1=1											");
        	query.append("               AND a.comp_no = x.comp_no							");
        	query.append("               AND a.part_id = x.part_id							");
        	query.getAndQuery("a.equip_id", conditionParam.get("equip_id"));
        	
        	if(!"".equals(conditionParam.get("eqasmb_id")))
        	{
        		query.append("AND exists (   SELECT eqasmb_id                               ");
                query.append("                       FROM TAEQASMB c                        ");
                query.append("                       WHERE c.comp_no = b.comp_no            ");
                query.append("                       and c.eqasmb_id = a.eqasmb_id          ");
                query.append("                       START wITH c.eqasmb_id = "+conditionParam.get("eqasmb_id")+"    ");
                query.append("                       CONNECT BY PRIOR c.eqasmb_id = c.p_eqasmb_id	");
                query.append("           )                                               	");
        	}
        	else
        	{
        		query.append("AND exists (   SELECT eqasmb_id                               ");
                query.append("                       FROM TAEQASMB c                        ");
                query.append("                       WHERE c.comp_no = b.comp_no            ");
                query.append("                       START wITH UPPER(c.full_desc) LIKE '%"+conditionParam.get("eqasmb_desc")+"%'	");
                query.append("                       CONNECT BY PRIOR c.eqasmb_id = c.p_eqasmb_id    ");
                query.append("           )                                           		");
        	}
        	query.append("           )														");
		}
        
        //재고수량관리여부
        if(conditionParam.containsKey("is_stock_control") && !"".equals(conditionParam.get("is_stock_control"))) 
        {

        	query.getAndQuery("x.is_stock_control", conditionParam.get("is_stock_control"));
        }
        
        query.getAndQuery("part_categ", conditionParam);
        query.append("ORDER BY  1                                                   ");

         return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    @Override
    public List findPartsDesc(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                        ");
        query.append("      DISTINCT(description) AS Zdisplay                       ");
        query.append("      ,description                                            ");
//      query.append("      ,full_desc                                              ");
//      query.append("      ,model                                                  ");
//      query.append("      ,part_group                                             ");
//      query.append("      ,part_id                                                ");
//      query.append("      ,part_no                                                ");
//      query.append("      ,description||', '||pt_size AS partNameSize             ");
//      query.append("      ,pt_size                                                ");
//      query.append("      ,last_price                                                   ");
//      query.append("      ,is_serial_part                                         ");
        query.append("FROM  TAPARTS                                                 ");
        query.append("WHERE 1 = 1                                                   ");
        query.getStringEqualQuery("comp_no", user.getCompNo());
        query.getStringEqualQuery("IS_DELETED", "N");
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("part_group", conditionParam);
        query.getAndQuery("model", conditionParam);
        query.getAndQuery("pt_size", conditionParam);
        query.append("ORDER BY  1                                                   ");

         return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    @Override
    public List findPartsModel(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                        ");
        query.append("      DISTINCT(model) AS Zdisplay                             ");
        query.append("      ,model                                                  ");
        query.append("      ,full_desc                                              ");
        query.append("      ,part_id                                                ");
        query.append("      ,part_no                                                ");
        query.append("      ,description                                            ");
        query.append("      ,part_group                                             ");
        query.append("      ,description||', '||pt_size AS partNameSize             ");
        query.append("      ,pt_size                                                ");
        query.append("      ,last_price                                             ");
        query.append("      ,is_serial_part                                         ");
        query.append("FROM  TAPARTS                                                 ");
        query.append("WHERE 1 = 1                                                   ");
        query.getStringEqualQuery("comp_no", user.getCompNo());
        query.getStringEqualQuery("IS_DELETED", "N");
        query.getLikeQuery("model", keySearchVal);
        query.getAndQuery("part_group", conditionParam);
        query.getAndQuery("description", conditionParam);
        query.getAndQuery("pt_size", conditionParam);
        query.append("ORDER BY  1                                                   ");

         return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    @Override
    public List findPartsPtSize(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                        ");
        query.append("      DISTINCT(pt_size) AS Zdisplay                           ");
        query.append("      ,pt_size                                                ");
        query.append("      ,full_desc                                              ");
        query.append("      ,description                                            ");
        query.append("      ,model                                                  ");
        query.append("      ,part_group                                             ");
        query.append("      ,part_id                                                ");
        query.append("      ,part_no                                                ");
        query.append("      ,description||', '||pt_size AS partNameSize             ");
        query.append("      ,last_price                                             ");
        query.append("      ,is_serial_part                                         ");
        query.append("FROM  TAPARTS                                                 ");
        query.append("WHERE 1 = 1                                                   ");
        query.getStringEqualQuery("comp_no", user.getCompNo());
        query.getStringEqualQuery("IS_DELETED", "N");
        query.getLikeQuery("pt_size", keySearchVal);
        query.getAndQuery("part_group", conditionParam);
        query.getAndQuery("model", conditionParam);
        query.getAndQuery("description", conditionParam);
        query.append("ORDER BY  1                                                   ");

         return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findEhUser(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       y.key_name                 AS Zdisplay                     ");//First one only For Display
        query.append("       ,y.key_name                AS key_name                     ");
        query.append("       ,x.ehmenu_id               AS ehmenu_id                    ");
        query.append("FROM   TAEHMENU x INNER JOIN TALANG y                             ");
        query.append("  ON   x.key_no||x.key_type = y.key_no||y.key_type                ");
        query.append(" AND   y.lang='"+lang+"'                                          ");
        query.append(" AND   x.is_use = 'Y'                                             ");
        query.getLikeQuery("key_name", keySearchVal);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findLang(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       DISTINCT(x.key_name)               AS Zdisplay                     ");//First one only For Display
        query.append("       ,x.key_type                AS key_type                     ");
        query.append("       ,x.key_no                  AS key_no                       ");
        query.append("       ,x.key_name                AS key_name                     ");
        query.append("FROM   TALANG x                                                   ");
        query.append("WHERE  1=1                                                        ");
        query.append("  AND  x.lang='"+lang+"'                                          ");
        query.getLikeQuery("key_name", keySearchVal);
        query.getAndQuery("key_type", conditionParam);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findPage(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();
    
        query.append("SELECT                                                            ");
        query.append("       x.file_name||', '||x.description           AS Zdisplay                         ");//First one only For Display
        query.append("       ,x.description         AS description                      ");
        query.append("       ,x.page_id             AS page_id                          ");
        query.append("       ,x.file_name           AS file_name                        ");
        query.append("FROM   TAPAGE x                                                   ");
        query.append("WHERE  1=1                                                        ");     
        query.getLikeQuery("file_name||description", keySearchVal);
        if(conditionParam.containsKey("menu_id") && !"".equals(conditionParam.get("menu_id"))) {
            query.append("AND page_id IN(SELECT c_page_id                                                              ");
            query.append("               FROM TAPGPAGE                                                                 ");
            query.append("               START WITH page_id IN (SELECT page_id                                         ");
            query.append("                                      FROM TAMENU                                            ");
            query.append("                                      START WITH menu_id='"+conditionParam.get("menu_id")+"' ");
            query.append("                                      CONNECT BY PRIOR menu_id=p_menu_id                     ");
            query.append("                                      )                                                      ");
            query.append("               CONNECT BY PRIOR c_page_id=page_id                                            ");
            query.append("               UNION                                                                         ");
            query.append("               SELECT page_id FROM TAMENU WHERE menu_id='"+conditionParam.get("menu_id")+"'  ");
            query.append(")                                                                                            ");
        }
        query.getAndQuery("is_use", conditionParam);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findPMenu(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       y.key_name             AS Zdisplay                         ");//First one only For Display
        query.append("       ,y.key_name            AS description                      ");
        query.append("       ,x.p_menu_id           AS p_menu_id                        ");
        query.append("       ,x.menu_id             AS menu_id                          ");
        query.append("       ,x.page_id             AS page_id                          ");
        query.append("       ,(SELECT file_name FROM TAPAGE WHERE page_id = x.page_id) as file_name        ");
        query.append("       ,x.ord_no                                                  ");
        query.append("FROM   TAMENU x INNER JOIN TALANG y                               ");
        query.append("  ON   x.key_no=y.key_no AND x.key_type=y.key_type                ");
        query.append(" AND   y.lang = '"+user.getLangId()+"'                            ");
        query.append("WHERE  1=1                                                        ");     
        query.getLikeQuery("y.key_name", keySearchVal);
        query.getAndQuery("x.service_type", conditionParam);
        if(conditionParam.containsKey("AUTH") || conditionParam.containsKey("auth"))
        {
            String auth = "".equals(conditionParam.get("AUTH"))?conditionParam.get("auth"):conditionParam.get("AUTH");
            if("Y".equals(auth))  query.append("  AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z)      ");
        }
        else query.append("  AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z)      ");
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findWarehouse(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       x.wname            AS Zdisplay                             ");//First one only For Display
        query.append("       ,x.wname           AS wname                                ");
        query.append("       ,x.wcode_id        AS wcode_id                             ");
        query.append("       ,x.wcode           AS wcode                                ");
        query.append("       ,x.plant           AS plantId                                ");
        query.append("       ,(SELECT description FROM TAPLANT WHERE comp_no = x.comp_no AND plant=x.plant) plantDesc   ");
        query.append("FROM   TAWAREHOUSE x                                              ");
        query.append("WHERE  1=1                                                        ");
        query.getLikeQuery("wname", keySearchVal);
        query.getAndQuery("x.comp_no", conditionParam);
        query.getAndQuery("wh_categ", conditionParam);
        query.getAndQuery("wh_type", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        query.getAndQuery("plant", conditionParam);
        query.getAndQuery("comp_no", conditionParam);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findFailuare(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    ");
        query.append("       y.key_name         AS Zdisplay                     ");//First one only For Display
        query.append("       ,y.key_name        AS failureDesc                  ");
        query.append("       ,x.failure_no      AS failure_no                   ");
        query.append("       ,x.failure_id      AS failure_id                   ");
        query.append("FROM   TAFAILURE x INNER JOIN TALANG y                    ");
        query.append("  ON   x.key_no =y.key_no AND x.key_type= y.key_type AND y.lang= '"+user.getLangId()+"'                  ");     
        query.append("WHERE  1=1                                                ");     
        query.getAndQuery("x.is_use", "Y");
        query.getLikeQuery("y.key_name", keySearchVal);
        query.getAndQuery("x.fail_type", conditionParam);
        query.getAndQuery("x.comp_no", conditionParam);
        query.append("ORDER BY  1                                               ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    public List findCertList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       x.cert_name                AS Zdisplay                     ");//First one only For Display
        query.append("       ,x.cert_name                                               ");//First one only For Display
        query.append("       ,x.certlist_id             AS certlist_id                  ");
        query.append("       ,x.cert_no                 AS cert_no                      ");
        query.append("       ,x.cert_type               AS cert_type                    ");
        query.append("       ,SFACODE_TO_DESC(x.cert_type,'CERT_TYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"') AS certTypeDesc");
        query.append("FROM   TACERTLIST x                                               ");
        query.append("WHERE  1=1                                                        ");     
        query.getLikeQuery("cert_name", keySearchVal);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    public List findCourseList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       x.description          AS Zdisplay                         ");//First one only For Display
        query.append("       ,x.description         AS description                      ");
        query.append("       ,x.courselist_id       AS courselist_id                    ");
        query.append("       ,x.courselist_no       AS courselist_no                    ");
        query.append("FROM   TACOURSELIST x                                             ");
        query.append("WHERE  1=1                                                        ");     
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        query.append("ORDER BY  1                                                       ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findDocctgList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       DISTINCT(x.full_desc)      AS Zdisplay                     ");//First one only For Display
        query.append("       ,x.description             AS description                  ");
        query.append("       ,x.docctg_id               AS docctg_id                    ");
        query.append("FROM   TADOCCTG x                                                 ");
        query.append("WHERE  1=1                                                        ");   
        query.append("  AND  x.is_use = 'Y'                                             ");   
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("x.comp_no", conditionParam);
        query.getAndQuery("x.p_docctg_id", conditionParam);
        if(conditionParam.containsKey("docctg_id") && (!"".equals(conditionParam.containsKey("docctg_id"))))
		{
        	query.getLikeQuery("x.docctg_id", "-"+conditionParam.get("docctg_id"));
		}
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findPlantList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("       DISTINCT(x.description)    AS Zdisplay                     ");//First one only For Display
        query.append("       ,x.description             AS description                  ");
        query.append("       ,x.plant                   AS plant                        ");
        query.append("FROM   TAPLANT x  INNER JOIN TAUSRPLANTAUTH y ON x.plant = y.plant AND y.is_auth= 'Y'       ");
        query.append("WHERE  1=1                                                        ");   
        query.getAndQuery("y.user_id", user.getUserId());
        query.getAndQuery("x.is_use", "Y");
        query.getLikeQuery("x.description", keySearchVal);
        query.getAndQuery("x.comp_no", conditionParam);
        query.getAndQuery("x.is_use", conditionParam);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findUsrdList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
    
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("       DISTINCT(description)    AS Zdisplay                         ");
        query.append("      ,cdusrd_id      ");
        query.append("      ,cdusrd_no      ");
        query.append("      ,description        ");
        query.append("      ,full_desc        ");
        query.append("      ,remark         ");
        query.append("FROM  TACDUSRD        ");
        query.append("WHERE 1=1     ");
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        query.append("AND cdusrm_id = (SELECT cdusrm_id         ");
        query.append("                                FROM TACDUSRM         ");
        query.append("                                WHERE 1=1         ");
        query.getAndQuery("dir_type", conditionParam);
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        query.getAndQuery("cdusrm_id", conditionParam);
        query.append("                                )     ");
        query.getLikeQuery("description", keySearchVal);
        query.append("ORDER BY 1                    ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findRcmList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
    
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                        ");
        query.append("       DISTINCT(x.description)        AS Zdisplay             ");
        query.append("       ,x.description                 AS description          ");
        query.append("       ,x.rcmlist_id                  AS rcmlist_id           ");
        query.append("       ,x.rcmlist_no                  AS rcmlist_no           ");
        query.append("FROM   TARCMLIST x                                            ");
        query.append("WHERE  1=1                                                    ");
        query.getLikeQuery("x.description", keySearchVal);  
        query.append("ORDER BY  1                                                   ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    public List findEqToolList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                 ");
        query.append("       DISTINCT(y.description)        AS Zdisplay      ");
        query.append("       ,x.equip_id                    AS equip_id      ");
        query.append("       ,y.description                 AS description   ");
        query.append("       ,y.item_no                     AS item_no       ");
        query.append("       ,y.serial_no                   AS serial_no     ");
        query.append("       ,(SELECT                                        ");
        query.getDate("first_Value(z.next_calib_date) OVER (ORDER BY wkor_date DESC)", "next_calib_date");
        query.append("         FROM   TAWORKORDER A INNER JOIN TAWOEQUIP b ON A.wkor_id = b.wkor_id      ");
        query.append("                INNER JOIN TAWOCALIB z ON A.wkor_id = z.wkor_id                    ");
        query.append("         WHERE  b.equip_id = y.equip_id                                            ");
        query.append("           AND ROWNUM = 1                                                          ");
        query.append("        ) next_calib_date                                                          ");
        query.append("       ,(SELECT                                                                    ");
        query.append("                NVL(first_value(z.calib_cert_no) OVER (ORDER BY wkor_date DESC), first_value(A.wkor_id) OVER (ORDER BY wkor_date DESC))   ");
        query.append("         FROM   TAWORKORDER A INNER JOIN TAWOEQUIP b ON A.wkor_id = b.wkor_id      ");
        query.append("                INNER JOIN TAWOCALIB z ON A.wkor_id = z.wkor_id                    ");
        query.append("         WHERE  b.equip_id = y.equip_id                                            ");
        query.append("           AND ROWNUM = 1                                                          ");
        query.append("        ) calib_cert_no                                                            ");
        query.append("        ,(SELECT SUBSTR(SYS_CONNECT_BY_PATH(description, '>'), 2)     ");
        query.append("         FROM TAEQLOC z   ");
        query.append("         WHERE z.eqloc_id = y.eqloc_id    ");
        query.append("         START WITH p_eqloc_id = 0    ");
        query.append("         CONNECT BY PRIOR eqloc_id = p_eqloc_id   ");
        query.append("        ) eqLocDesc   ");
        query.append("        ,(SELECT A.description    ");
        query.append("          FROM TAPLANT A  ");
        query.append("          WHERE A.plant = y.plant ");
        query.append("          ) plantDesc ");
        query.append("        ,(SELECT A.description   ");
        query.append("          FROM TAEQUIPMENT A  ");
        query.append("          WHERE A.equip_id = y.p_equip_id ");
        query.append("         ) pequipDesc                 ");
        query.append("        ,(SELECT A.item_no   ");
        query.append("           FROM TAEQUIPMENT A ");
        query.append("           WHERE A.equip_id = y.p_equip_id    ");
        query.append("         ) pitemNo                           ");
        query.append("        ,eqctg_type eqctgType                         ");
        query.append("FROM   TAEQTOOL x INNER JOIN TAEQUIPMENT y            ");
        query.append("  ON   x.equip_id = y.equip_id                        ");
        query.append(" AND   x.comp_no = y.comp_no                          ");
        query.append("WHERE  1 = 1                                          ");
//        query.append("AND    x.is_standard_eq = 'Y'                         ");
        query.getStringEqualQuery("y.IS_DELETED", "N");
        query.getStringEqualQuery("y.is_last_version", "Y");
        
        query.getLikeQuery("y.description", keySearchVal);
        query.getAndQuery("x.is_standard_eq", conditionParam);
        query.getAndQuery("y.eqctg_type", conditionParam);
        query.getAndQuery("y.eq_status", conditionParam);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    public List findWrkcalList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                ");
        query.append("       DISTINCT(description)      AS Zdisplay         ");
        query.append("       ,wrkcallist_id             AS wrkcallist_id    ");
        query.append("       ,description               AS description      ");
        query.append("       ,wrkcallist_no             AS wrkcallist_no    ");
        query.append("FROM   TAWRKCALLIST                                   ");
        query.append("WHERE  1=1                                            ");
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    public List findCompList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                            ");
        query.append("       DISTINCT(description)  AS Zdisplay         ");
        query.append("       ,comp_no               AS comp_no          ");
        query.append("       ,description           AS description      ");
        query.append("FROM   TACOMP                                     ");
        query.append("WHERE  1=1                                        ");
        query.getLikeQuery("description", keySearchVal);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    public List findEqAsmbList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                ");
        query.append("       DISTINCT(NVL(full_desc,description))        AS Zdisplay         ");
        query.append("       ,eqasmb_id                 AS eqasmb_id        ");
        query.append("       ,description               AS description      ");
        query.append("       ,full_desc                 AS full_desc        ");
        query.append("       ,is_eqtype_asmb            AS is_eqtype_asmb   ");
        query.append("       ,ord_no                    AS ord_no           ");
        query.append("FROM   TAEQASMB                                       ");
        query.append("WHERE  1=1                                            ");
        query.getStringEqualQuery("comp_no", user.getCompNo());
        query.getLikeQuery("full_desc", keySearchVal);
        query.getAndQuery("comp_no",conditionParam);
        query.getAndQuery("equip_id", conditionParam, "Y");
        query.getAndQuery("is_use", conditionParam);
        query.append("ORDER BY ord_no                                       ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    public List findEqAsmbByPmList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                ");
        query.append("       DISTINCT(full_desc)        AS Zdisplay         ");
        query.append("       ,eqasmb_id                 AS eqasmb_id        ");
        query.append("       ,description               AS description      ");
        query.append("       ,full_desc                 AS full_desc        ");
        query.append("       ,is_eqtype_asmb            AS is_eqtype_asmb   ");
        query.append("       ,ord_no                    AS ord_no           ");
        query.append("FROM   TAEQASMB                                       ");
        query.append("WHERE  1=1                                            ");
        query.getStringEqualQuery("comp_no", user.getCompNo());
        query.getLikeQuery("description", keySearchVal);
        query.append("AND equip_id IN(SELECT equip_id         ");
        query.append("                             FROM TAPMEQUIP       ");
        query.append("                             WHERE 1=1        ");
        query.getStringEqualQuery("comp_no", user.getCompNo());
        query.getStringEqualQuery("IS_DELETED", "N");
        query.getAndQuery("comp_no",conditionParam);
        query.getAndQuery("pm_id", conditionParam);
        query.append("                             )                                ");
        query.append("ORDER BY ord_no                                       ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    public List findTaskNoList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                                    ");
        query.append("       DISTINCT(pmtaskmap_no)||'/'||description   AS Zdisplay             ");//First one only For Display
        query.append("       ,pmtaskmap_no                              AS pmtaskmap_no         ");
        query.append("       ,pmtaskmap_id                              AS pmtaskmap_id         ");
        query.append("FROM   TAPMTASKMAP                                                        ");
        query.append("WHERE  1 = 1                                                              ");
        query.append("  AND  comp_no = '"+user.getCompNo()+"'                                   ");
        query.getLikeQuery("pmtaskmap_no||description", keySearchVal);
        if(!"".equals(conditionParam.get("task_id"))&&!"null".equals(conditionParam.get("task_id"))&&conditionParam.get("task_id")!= null)
        {
            query.append("AND pmtaskmap_id !='"+conditionParam.get("task_id")+"'                ");
        }
        if(!"".equals(conditionParam.get("tasklist_id"))&&!"null".equals(conditionParam.get("tasklist_id"))&&conditionParam.get("tasklist_id")!= null)
        {
            query.append("AND pmtaskmaplist_id ='"+conditionParam.get("tasklist_id")+"'         ");
        }
        query.append("ORDER BY  1                                                               ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    public List findCrityList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                ");
        query.append("       DISTINCT(description)      AS Zdisplay         ");
        query.append("       ,critylist_id              AS critylist_id     ");
        query.append("       ,description               AS description      ");
        query.append("FROM   TACRITYLIST                                    ");
        query.append("WHERE  1=1                                            ");
        query.append("  AND  is_use = 'Y'                                   ");
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    public List findTaskMapList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    ");
        query.append("       DISTINCT(description)      AS Zdisplay             ");
        query.append("       ,pmtaskmaplist_id          AS pmtaskmaplist_id     ");
        query.append("       ,pmtaskmaplist_no          AS pmtaskmaplist_no     ");
        query.append("       ,description               AS description          ");
        query.append("FROM   TAPMTASKMAPLIST                                    ");
        query.append("WHERE  1=1                                                ");
        query.append("  AND  is_use = 'Y'                                       ");
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findRcmCrityValList(String keySearchVal, Map<String, String> columnMap,Map<String, String> conditionParam, String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    ");
        if(conditionParam.containsKey("col_name"))
            query.append("DISTINCT(row_name ||'('||col_name||')') AS Zdisplay   ");
        else query.append("DISTINCT(col_name||'('||row_name||')') AS Zdisplay   ");

        query.append("       ,col_name          AS col_name                     ");
        query.append("       ,row_name          AS row_name                     ");
        query.append("       ,crityvalue        AS crityvalue                   ");
        query.append("       ,critycolor        AS critycolor                   ");
        query.append("       ,is_critical       AS is_critical                  ");
        query.append("       ,crity_lvl         AS crity_lvl                    ");
        query.append("       ,crityvalue_id     AS crityvalue_id                ");
        query.append("       ,critylist_id      AS critylist_id                 ");
        query.append("       ,critycol_id       AS critycol_id                  ");
        query.append("       ,crityrow_id       AS crityrow_id                  ");
        query.append("       ,col_ord_no        AS col_ord_no                   ");
        query.append("       ,row_ord_no        AS row_ord_no                   ");
        query.append("FROM   TACRITYVALUE                                       ");
        query.append("WHERE  1=1                                                ");
        
        query.getLikeQuery("crityvalue", keySearchVal);
        query.getAndQuery("row_name", conditionParam);
        query.getAndQuery("col_name", conditionParam);
        query.getAndQuery("critylist_id", conditionParam);
        query.getAndQuery("comp_no", conditionParam);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findCdsysdCode(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    ");
        query.append("       nvl((select aa.key_name                            ");
        query.append("            from talang aa                                ");
        query.append("            where  lang = '"+lang+"'                      ");
        query.append("            and x.key_type = aa.key_type                  ");
        query.append("            and x.key_no = aa.key_no), x.description)     ");        
        query.append("       description                                        ");
        query.append("       ,cdsysd_no                                         ");
        query.append("FROM   TACDSYSD x                                         ");
        query.append("WHERE  1=1                                                ");
        query.getAndQuery("x.is_use", "Y");
        query.append("  AND  cdsysm_id = (SELECT cdsysm_id                      ");
        query.append("                      FROM TACDSYSM                       ");
        query.append("                      WHERE 1 = 1                         ");
        
        query.getAndQuery("list_type", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        
        query.append("                  )                                       ");
        query.getLikeQuery("cdsysd_no", keySearchVal);
        query.append("ORDER BY  1                                               ");
        
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findInvtprcTpList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                            ");
        query.append("      DISTINCT(description) AS Zdisplay           ");
        query.append("      ,invtprctp_id                               ");
        query.append("      ,invtprctp_no                               ");
        query.append("      ,description                                ");
        query.append("FROM   TAINVTPRCTP                                ");
        query.append("WHERE  1=1                                        ");
        query.append("AND    is_use = 'Y'                               ");
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findAssBaseList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                                            ");
        query.append("       DISTINCT(x.description)                                AS Zdisplay         ");
        query.append("       ,x.assbaselist_id                                      AS assBaseListId    ");
        query.append("       ,x.assbaselist_no                                      AS assBaseListNo    ");
        query.append("       ,x.description                                         AS assBaseListDesc  ");
        query.append("       ,x.is_use                                              AS isUseId          ");
        query.append("       ,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''                                ");
        query.append("                          ,'"+lang+"')                        AS isUseDesc        ");
        query.append("       ,x.reg_date                                            AS regDate          ");
        query.append("       ,x.remark                                              AS remark           ");
        query.append("FROM   TAASSBASELIST x                                                            ");
        query.append("WHERE  1=1                                                                        ");

        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findAssBasePointValList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                                            ");
        query.append("       x.ass_eval                                             AS Zdisplay         ");
        query.append("       ,x.assbaselist_id                                      AS assBaseListId    ");
        query.append("       ,x.assbasepoint_id                                     AS assBasePointId   ");
        query.append("       ,x.assbasepval_id                                      AS assBasePvalId    ");
        query.append("       ,x.eval_value                                          AS evalValue        ");
        query.append("       ,x.ass_eval                                            AS assEval          ");
        query.append("       ,x.is_use                                              AS isUseId          ");
        query.append("       ,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''                                ");
        query.append("                          ,'"+lang+"')                        AS isUseDesc        ");
        query.append("FROM   TAASSBASEPVAL x                                                            ");
        query.append("WHERE  1=1                                                                        ");

        query.getLikeQuery("ass_eval", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        query.getAndQuery("assbaselist_id", conditionParam);
        query.getAndQuery("assbasepoint_id", conditionParam);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findInvtList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();
        
        String notIn = conditionParam.get("NOT_IN_STATUS");
        
        query.append("SELECT                                ");
        query.append("       description    as Zdisplay     ");
        query.append("       ,description   as description  ");
        query.append("       ,invtlist_id   as invtlistId   ");
        query.append("       ,invtprctp_id  as invtprctpId  ");
        query.append("FROM   TAINVTLIST                     ");
        query.append("WHERE  1=1                            ");

        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("invtprctp_id", conditionParam);
        if(!"".equals(notIn) && notIn!=null) {
            query.append("AND invtlist_status NOT IN("+notIn+")    ");
        }
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findInvtPrcphList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
     
        query.append("SELECT                                            ");
        query.append("       SFACODE_TO_DESC(y.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+user.getCompNo()+"','"+lang+"')  ||' / '||SFACODE_TO_DESC(y.invt_proc_stype,'INVT_PROC_STYPE','USR','"+user.getCompNo()+"','"+lang+"') as Zdisplay        ");
        query.append("       ,SFACODE_TO_DESC(y.invt_proc_stype,'INVT_PROC_STYPE','USR','"+user.getCompNo()+"','"+lang+"')  invtProcStypeDesc   ");
        query.append("       ,SFACODE_TO_DESC(y.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+user.getCompNo()+"','"+lang+"')  invtProcLtypeDesc   ");
        query.append("       ,y.invt_proc_stype invtProcStype           ");
        query.append("       ,y.invt_proc_ltype invtProcLtype           ");
        query.append("       ,y.invtprcph_id invtprcphId                ");
        query.append("       ,y.invtprctp_id invtprctpId                ");
        query.append("       ,y.ref_depart refDepart                    ");
        query.append("       ,y.ref_doc refDoc                          ");
        query.append("       ,y.doc_prefix docPrefix                   ");
        query.append("FROM   TAINVTPRCTP x INNER JOIN TAINVTPRCPH y     ");
        query.append("  ON   x.invtprctp_id = y.invtprctp_id            ");
        query.append("WHERE  1=1                            ");
        query.getLikeQuery("SFACODE_TO_DESC(y.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+user.getCompNo()+"','"+lang+"')||SFACODE_TO_DESC(y.invt_proc_stype,'INVT_PROC_STYPE','USR','"+user.getCompNo()+"','"+lang+"')", keySearchVal);
        query.getAndQuery("y.comp_no", conditionParam);
        query.getAndQuery("y.invtprctp_id", conditionParam);
        query.getAndQuery("y.is_use", conditionParam);

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findWoList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User loginUser) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    	");
        query.append("       x.description 							AS Zdisplay						");
        query.append("       ,x.wkor_id 							AS wkorId                   	");
        query.append("       ,x.wo_no 								AS woNo                     	");
        query.append("       ,x.description 						AS woDesc         				");
        query.append("       ,SFACODE_TO_DESC(wo_status,'WO_STATUS','SYS','','"+loginUser.getLangId()+"')	AS woStatusDesc	");
        query.append("       ,y.description  						AS equipDesc             		");
        query.append("       ,(SELECT c.full_desc                           						");
        query.append("         FROM TAEQLOC c                    									");
        query.append("         WHERE c.comp_no = x.comp_no                                 			");
        query.append("         	AND c.eqloc_id = y.eqloc_id	)   	AS eqLocDesc            		");
        query.append("       ,(SELECT description                                					");
        query.append("         FROM TADEPT                                                      	");
        query.append("         WHERE comp_no = x.comp_no                                        	");
        query.append("           AND dept_id = x.dept_id)         	AS deptDesc   					");
        query.append("       ,SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"')				AS woTypeDesc ");
        query.append("       ,SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+loginUser.getLangId()+"')	AS pmTypeDesc ");
        query.append("       ,(SELECT emp_name                                                  	");
        query.append("         FROM TAEMP                                                       	");
        query.append("         WHERE comp_no = x.comp_no                                        	");
        query.append("           AND emp_id = x.emp_id)          	AS empDesc        				");
        query.append("       ,(SELECT c.emp_name                              						");
        query.append("         FROM TAEMP c                        									");
        query.append("         WHERE c.comp_no = x.comp_no                                   		");
        query.append("          AND y.sub_mng_id = c.emp_id   )     AS subMng       				");
        query.append("FROM TAWORKORDER x LEFT OUTER JOIN (SELECT 									");
		query.append("                                        a.comp_no								");
		query.append("                                        ,a.wkor_id							");
		query.append("                                        ,b.item_no							");
		query.append("                                        ,b.description    					");
		query.append("                                        ,b.eqloc_id							");
		query.append("                                        ,b.sub_mng_id							");
		query.append("                                    FROM TAWOEQUIP a INNER JOIN TAEQUIPMENT b	");
		query.append("                                    ON a.comp_no = b.comp_no                  ");
		query.append("                                    AND a.equip_id = b.equip_id				");
		query.append("                                   ) y										");
		query.append("ON y.comp_no = x.comp_no                                                  	");
		query.append(" AND y.wkor_id = x.wkor_id  													");
        query.append("WHERE 1=1                                                                 	");
        query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");

        query.getLikeQuery("x.description", keySearchVal);
        query.getAndQuery("x.wo_status", conditionParam);
        query.append("ORDER BY x.wo_no DESC                                                     	");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    @Override
    public List findWkCtrList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User loginUser) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("       description    AS Zdisplay                     ");
        query.append("       ,wkctr_id      AS wkctr_id                     ");
        query.append("       ,p_wkctr_id    AS p_wkctr_id                   ");
        query.append("       ,wkctr_no      AS wkctr_no                     ");
        query.append("       ,description   AS description                  ");
        query.append("FROM   TAWKCTR                                        ");
        query.append("WHERE  1=1                                            ");
        query.getAndQuery("is_use", conditionParam);
        query.getAndQuery("comp_no", conditionParam);
        query.getLikeQuery("description", keySearchVal);
        query.append("ORDER BY description DESC                             ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    } 

    @Override
    public List findAssetList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                ");
        query.append("       description AS Zdisplay,                                       ");
        query.append("       asset_id asset_id,                                             ");
        query.append("       asset_no asset_no,                                             ");
        query.append("       description description,                                       ");
        query.append("       TO_CHAR(TO_DATE (acq_date,'yyyy-mm-dd'),'yyyy-mm-dd') acqDate, ");
        query.append("       TO_CHAR(buyer_amt,'FM999,999,999,999') buyerAmt,               ");
        query.append("       TO_CHAR(dep_amt,'FM999,999,999,999') depAmt,                   ");
        query.append("       TO_CHAR(res_amt,'FM999,999,999,999') resAmt                    ");
        query.append("FROM   TAASSET                                                        ");
        query.append("WHERE  1=1                                                            ");
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        query.getLikeQuery("asset_no", keySearchVal);
        query.getAndQuery("is_use", conditionParam);
        query.append("ORDER BY description DESC                             ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    @Override
    public List findPmList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User loginUser) {
        
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                        ");
        query.append("        x.pm_no 										AS pmNo									");
        query.append("        ,x.pm_no 										AS Zdisplay								");
        query.append("        ,x.description 								AS description							");
        query.append("        ,z.description								AS equipDesc							");
        query.append("        ,SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+loginUser.getCompNo()+"')	AS deptDesc			");
        query.append("        ,(SELECT a.emp_name                                            						");
        query.append("          FROM   TAEMP a                                               						");
        query.append("          WHERE  a.emp_id = x.emp_id) 				AS empName                         		");
        query.append("        ,SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')		AS pmTypeDesc	");
        query.append("        ,SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')	AS scheduleType	");
        query.append("        ,TO_CHAR(x.cycle) || SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')	AS cycleDesc	");
        query.append("        ,x.cycle 										AS cycle								");
        query.append("        ,SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')		AS periodType	");
        query.append("        ,x.USAGE										AS USAGE								");
        query.append("        ,''  											AS lastSchDate							");
        query.append("        ,x.is_active  								AS isActive								");
        query.append("        ,x.pm_id  									AS pmId									");
        query.append("        ,x.pm_type  									AS pmType								");
        query.append("        ,x.remark 									AS remark								");
        query.append("        ,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=x.pm_type AND list_type= x.wo_type||'_TYPE')	AS param	");
        query.append("FROM    TAPMLST x LEFT OUTER JOIN TAPMEQUIP y 												");
		query.append("ON x.comp_no = y.comp_no                                     									");
		query.append(" AND x.pm_id = y.pm_id                      													");
		query.append("			INNER JOIN TAEQUIPMENT z															");
		query.append("          ON y.comp_no = z.comp_no															");
		query.append("           AND y.equip_id = z.equip_id														");
        query.append("WHERE   1=1                                                                               	");
        query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        query.getStringEqualQuery("y.IS_DELETED", "N");
        query.getStringEqualQuery("z.IS_DELETED", "N");
        query.getAndQuery("x.pm_type", conditionParam);
        query.getLikeQuery("x.pm_no", keySearchVal);

        query.append("ORDER BY x.description DESC                               									");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findEqCtgAsmbList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User loginUser) {
        
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                   ");
        query.append("      description             AS Zdisplay            ");
        query.append("      ,description            AS description         ");
        query.append("      ,remark                  AS remark              ");
        query.append("      ,eq_ctg_asmb_id     AS eq_ctg_asmb_id  ");
        query.append("FROM TAEQCTGASMB                                 ");
        query.append("WHERE  1 = 1                                           ");
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("eqctg_id", conditionParam);
        query.getLikeQuery("description", keySearchVal);
        query.append("ORDER BY  description     ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findVendorList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       vendor_no||', '||description        AS Zdisplay,                    ");
        query.append("       vendor_id              ,                           ");
        query.append("       vendor_no              ,                           ");
        query.append("       description            ,                           ");
        query.append("       address||', '||repname addrNrepName,               ");
        query.append("       person,                                            ");
        query.append("       office,                                            ");
        query.append("       mobile,                                            ");
        query.append("       email                                              ");
        query.append("FROM   TAVENDOR                                           ");
        query.append("WHERE  1=1                                                ");
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        query.getLikeQuery("vendor_no||description", keySearchVal);
        query.append("ORDER BY  description                                     ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findCtCtrList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                            ");
        query.append("      DISTINCT(description) Zdisplay,                                                                             ");
        query.append("      description,                                                                                                ");
        query.append("      ctctr_id                                                                                                    ");
        query.append("      ,ctctr_no                                                                                                   ");
        query.append("      ,in_wcode_id        inWcodeId                                                                               ");
        query.append("      ,(select a.wname from TAWAREHOUSE a where a.comp_no = x.comp_no and a.wcode_id = x.in_wcode_id) inWcodeDesc ");
        query.append("FROM TACTCTR x                                                                                                    ");
        query.append("WHERE  1 = 1                                                                                                      ");
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("plant", conditionParam);
        query.getAndQuery("is_use", conditionParam);
        query.getAndQuery("in_wcode_id", conditionParam);
        query.append("ORDER BY ctctr_no       ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findMesLineList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       DISTINCT(mes_line_name)    AS Zdisplay     ");//First one only For Display
        query.append("       ,mes_line_name                                       ");
        query.append("       ,mes_line_id                                            ");
        query.append("FROM   TAMESLINE                                          ");
        query.append("WHERE  1 = 1                                                 ");
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        query.append("ORDER BY  1                                                  ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findUsrRptTabList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("       DISTINCT(table_name)    AS Zdisplay            ");
        query.append("       ,usrrpttab_id                                  ");
        query.append("       ,usrrptlist_id                                 ");
        query.append("       ,table_id                                      ");
        query.append("       ,table_name                                    ");
        query.append("FROM   TAUSRRPTTAB x                                  ");
        query.append("WHERE  1 = 1                                          ");
        
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        query.append("ORDER BY  1                                           ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findUsrGrpList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("        x.group_name      AS Zdisplay                 ");
        query.append("      , x.usrgrp_id                                   ");
        query.append("      , x.usrgrp_no                                   ");
        query.append("      , x.group_name                                  ");
        query.append("FROM TAUSRGRP x                                       ");
        query.append("WHERE  1 = 1                                          ");
        
        query.getLikeQuery("group_name", keySearchVal);
        query.getAndQuery("comp_no", conditionParam);
        query.getAndQuery("is_admin_group", conditionParam);
        query.append("ORDER BY  1                                           ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findPartEquipList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user) {

        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                ");
        query.append("       DISTINCT(serial_no||' ('||a.item_no ||' , '||a.description||')') AS Zdisplay   ");
        query.append("       ,a.equip_id equipId                                                            ");
        query.append("       ,a.item_no itemNo                                                              ");
        query.append("       ,a.description                                                                 ");
        query.append("       ,b.part_id partId                                                              ");
        query.append("       ,c.description partDesc                                                        ");
        query.append("       ,c.part_no partNo                                                              ");
        query.append("       ,a.serial_no serialNo                                                          ");
        query.append("FROM   TAEQUIPMENT a INNER JOIN TAEQDEVICE b ON a.equip_id = b.equip_id               ");
        query.append("       INNER JOIN TAPARTS c ON b.part_id = c.part_id                                  ");
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        query.getStringEqualQuery("a.IS_DELETED", "N");
        query.getStringEqualQuery("a.is_last_version", "Y");
        query.getStringEqualQuery("c.IS_DELETED", "N");

        
        query.getLikeQuery("serial_no", keySearchVal);
        query.getAndQuery("a.comp_no", conditionParam);
        query.getAndQuery("b.part_id", conditionParam);
        query.getAndQuery("a.eq_status", conditionParam);

        query.append("ORDER BY  1                                                                           ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    public List findCalibStdList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user) {

        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                        ");
        query.append("       measure_range AS Zdisplay              ");
        query.append("       ,a.pmcalibstdtp_id pmCalibStdTpId      ");
        query.append("       ,a.pmcalibstdtp_no pmCalibStdTpNo      ");
        query.append("       ,a.measure_range measureRange          ");
        query.append("FROM   TAPMCALIBSTDTP a                       ");
        query.append("WHERE 1=1                                     ");
        query.getAndQuery("a.comp_no", conditionParam);

        query.append("ORDER BY  1                                   ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
  @Override
  public List findStdCheckPointList(String keySearchVal, Map<String, String> columnMap,
          Map<String, String> conditionParam, String lang, String acLength, User user) {
      
      QueryBuffer query = new QueryBuffer();
      
      query.append("SELECT                       ");
      query.append("     x.comp_no               ");
      query.append("   , x.std_check_point_id    ");
      query.append("   , x.std_check_point_no    ");
      query.append("   , x.check_point_type      ");
      query.append("   , x.check_point		     ");
      query.append("   , x.check_point		   AS Zdisplay ");
      query.append("   , x.check_value           ");
      query.append("   , x.uom                   ");
      query.append("   , x.part_id               ");
      query.append("   , x.is_active             ");
      query.append("   , x.remark                ");
      query.append("FROM TASTDCHECKPOINT x       ");
      query.append("WHERE 1=1                    ");
      query.append(" AND x.is_active = 'Y'       ");

      query.getLikeQuery("x.check_point", keySearchVal);
      query.getAndQuery("x.comp_no", conditionParam);
      query.getAndQuery("x.std_check_point_no", conditionParam);
      query.getAndQuery("x.check_point_type", conditionParam);
      query.getAndQuery("x.check_point", conditionParam);
      query.getAndQuery("x.check_value", conditionParam);
      
      query.append("ORDER BY  2                  ");
      
      return getJdbcTemplate().queryForList(query.toString(acLength));
  }
  
  @Override
  public List findStWrkWorkList(String keySearchVal, Map<String, String> columnMap,
          Map<String, String> conditionParam, String lang, String acLength, User user) {
      
      QueryBuffer query = new QueryBuffer();
           
        query.append("SELECT        ");
        query.append("       SFACODE_TO_DESC(x.wo_type, 'WO_TYPE', 'SYS', x.comp_no, '"+user.getLang()+"') AS Zdisplay  ");
        query.append("       ,x.stwrkwork_id        ");
        query.append("       ,SFACODE_TO_DESC(x.wo_type, 'WO_TYPE', 'SYS', x.comp_no, '"+user.getLang()+"') AS wo_type          ");
        query.append("       ,remark        ");
        query.append("FROM   TASTWRKWORK x      ");
        query.append("WHERE  1=1        ");



      query.getLikeQuery("SFACODE_TO_DESC(x.wo_type, 'WO_TYPE', 'SYS', x.comp_no, '"+user.getLang()+"')", keySearchVal);
      query.getAndQuery("comp_no", conditionParam);
      query.getAndQuery("stwrk_id", conditionParam);
      
      query.append("ORDER BY stwrkwork_id                  ");
      
      return getJdbcTemplate().queryForList(query.toString(acLength));
  }
  
  @Override
  public List findProductList(String keySearchVal, Map<String, String> columnMap,
          Map<String, String> conditionParam, String lang, String acLength, User user) {
      
      QueryBuffer query = new QueryBuffer();
      
      query.append("SELECT                            ");
      query.append("   x.description AS Zdisplay      ");
      query.append(" , x.comp_no      compNo          ");
      query.append(" , x.product_id   productId       ");
      query.append(" , x.product_no   productNo       ");
      query.append(" , x.description  productDesc     ");
      query.append(" , x.is_use       isUse           ");
      query.append(" , x.remark       remark          ");
      query.append("FROM TAPRODUCT x                  ");
      query.append("WHERE  1 = 1                      ");
      
      query.getLikeQuery("product_id", keySearchVal);
      query.getLikeQuery("product_no", keySearchVal);
      query.getAndQuery("comp_no", conditionParam);
      query.getAndQuery("is_use", conditionParam);
      query.append("ORDER BY  3                       ");
      
      return getJdbcTemplate().queryForList(query.toString(acLength));
  }

  @Override
  public List findLnWrkList(String keySearchVal, Map<String, String> columnMap,
          Map<String, String> conditionParam, String lang, String acLength, User user) {
      
      QueryBuffer query = new QueryBuffer();
      
      query.append("SELECT                                            ");
      query.append("     description              AS Zdisplay         ");
      query.append("    ,lnwrklist_id             lnWrkListId         ");
      query.append("    ,comp_no                  compNo              ");
      query.append("    ,(SELECT description FROM TACOMP WHERE comp_no = a.comp_no)   compDesc        ");
      query.append("    ,lnwrklist_id             lnWrkListNo         ");
      query.append("    ,description              description         ");
      query.append("    ,(SELECT description FROM TAEQLOC WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)    eqlocDesc     ");
      query.append("    ,(SELECT description FROM TAWRKCALLIST WHERE comp_no = a.comp_no AND wrkcallist_id = a.wrkcallist_id) wrkCalListDesc      ");
      query.append("    ,(SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant)          plantDesc     ");
      query.append("    ,a.REMARK                 remark              ");
      
      query.append("    ,a.plant                  plant               ");
      query.append("    ,a.eqloc_id               eqloc_Id            ");
      query.append("    ,(SELECT b.full_desc                          ");
      query.append("       FROM TAEQLOC b                             ");
      query.append("       WHERE b.comp_no = a.comp_no                ");
      query.append("       AND b.eqloc_id = a.eqloc_id)   lineDesc    ");

      query.append("FROM TALNWRKLIST a                                ");
      query.append("WHERE  1 = 1                                      ");
      
      query.getAndQuery("comp_no", conditionParam);
      query.getAndQuery("a.lnwrk_calendar_type", "R");
      query.getLikeQuery("description", keySearchVal);
      query.append("ORDER BY  2                                           ");
      
      return getJdbcTemplate().queryForList(query.toString(acLength));
  }
  
  @Override
  public List findCMenu(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
          String lang, String acLength, User user) {
      QueryBuffer query = new QueryBuffer();

      query.append("SELECT                                                            ");
      query.append("       y.key_name             AS Zdisplay                         ");//First one only For Display
      query.append("       ,y.key_name            AS description                      ");
      query.append("       ,x.p_menu_id           AS p_menu_id                        ");
      query.append("       ,x.menu_id             AS menu_id                          ");
      query.append("FROM   TAMENU x INNER JOIN TALANG y                               ");
      query.append("  ON   x.key_no=y.key_no AND x.key_type=y.key_type                ");
      query.append(" AND   y.lang = '"+user.getLangId()+"'                            ");
      query.append("WHERE  1=1                                                        ");     
      query.getLikeQuery("y.key_name", keySearchVal);
//      query.getLikeQuery("x.service_type", "WEB");
      query.getAndQuery("x.is_use", conditionParam);
      if(conditionParam.containsKey("AUTH") || conditionParam.containsKey("auth"))
      {
          String auth = conditionParam.get("AUTH") == null?conditionParam.get("auth"):conditionParam.get("AUTH");
          if("Y".equals(auth))  query.append("  AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z)      ");
      }
      else query.append("  AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z)      ");
      query.append("ORDER BY  1                                                       ");

      return getJdbcTemplate().queryForList(query.toString(acLength));
  }
  
  @Override
  public List findPmEquipList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
          String lang, String acLength, User user) {
      QueryBuffer query = new QueryBuffer();
      
      query.append("SELECT                                                ");
      query.append("   x.description || ' ' ||                            ");
      query.append("   (SELECT a.description FROM TAPRODUCT a WHERE a.product_id = x.product_id) AS Zdisplay      ");
      query.append("  , x.product_id    productId                         ");
      query.append("  , (SELECT a.product_no FROM TAPRODUCT a WHERE a.product_id = x.product_id) productNo        ");
      query.append("  , (SELECT a.description FROM TAPRODUCT a WHERE a.product_id = x.product_id) productDesc     ");
      query.append("  , x.pm_id         pmId                              ");
      query.append("  , x.pm_no         pmNo                              ");
      query.append("  , x.description   pmDesc                            ");
      query.append("  , x.REMARK        REMARK                            ");
      query.append("  , y.pmequip_id    pmEquipId                         ");
      query.append("  , (SELECT a.item_no                                 ");
      query.append("      FROM TAEQUIPMENT a                              ");
      query.append("      WHERE a.equip_id = y.equip_id)      equipNo     ");
      query.append("  , (SELECT                                           ");
      query.append("        (SELECT full_desc                             ");
      query.append("         FROM TAEQLOC                                 ");
      query.append("         WHERE eqloc_id = a.eqloc_id                  ");
      query.append("           AND comp_no = a.comp_no)                   ");
      query.append("     FROM TAEQUIPMENT a                               ");
      query.append("     WHERE a.comp_no = x.comp_no                      ");
      query.append("       AND a.equip_id = y.equip_id)       eqLocDesc   ");
      query.append("   , (SELECT a.description                            ");
      query.append("      FROM TAEQUIPMENT a                              ");
      query.append("      WHERE a.equip_id = y.equip_id)      equipName   ");
      query.append("   , (SELECT emp_name FROM TAEMP WHERE emp_id = x.emp_id)       empDesc         ");
      query.append("   , (select description from tadept where dept_id = x.dept_id) deptDesc        ");
      query.append("FROM TAPMLST x, TAPMEQUIP y                           ");
      query.append("WHERE x.pm_id = y.pm_id                               ");
      query.append("   and x.comp_no = y.comp_no                          ");
      query.append("AND x.pm_type = 'CINS'                                ");
      query.getStringEqualQuery("x.comp_no", user.getCompNo());
      query.getStringEqualQuery("x.IS_DELETED", "N");
      query.append("AND (SELECT a.is_use FROM TAPRODUCT a WHERE a.product_id = x.product_id) = 'Y'  ");
      query.append("AND x.is_active = 'Y'                                 ");
      query.getAndQuery("x.comp_no", columnMap);
      query.append("ORDER BY  1                                           ");
      
      return getJdbcTemplate().queryForList(query.toString(acLength));
  }
  
  @Override
  public List findExcelTabList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
          String lang, String acLength, User user) {
      QueryBuffer query = new QueryBuffer();
      
      query.append("SELECT                                          ");
      query.append("    description             AS Zdisplay         ");
      query.append("    ,exceltab_id            exceltab_id         ");
      query.append("    ,exceltab_no            exceltab_no         ");
      query.append("    ,description            description         ");
      query.append("    ,table_name             table_name          ");
      query.append("    ,upload_pgm_name        upload_pgm_name     ");
      query.append("    ,sheet_name             sheet_name          ");
      query.append("    ,excel_upload_type      excel_upload_type   ");
      query.append("FROM TAEXCELTAB                                 ");
      query.append("WHERE 1=1                                       ");
      query.getAndQuery("excellist_status", conditionParam);
      query.getAndQuery("is_use", conditionParam);
      query.getAndQuery("is_use_config", conditionParam);
      query.getLikeQuery("description", keySearchVal);
      query.append("ORDER BY  1                                     ");
      
      return getJdbcTemplate().queryForList(query.toString(acLength));
  }

    @Override
    public List findEmgPartList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                            ");
        query.append("        y.full_desc AS Zdisplay                                                   ");
        query.append("        ,ptemgisslist_id ptemgisslistId                                           ");
        query.append("        ,(SELECT a.description                                                    ");
        query.append("          FROM   TADEPT a                                                         ");
        query.append("          WHERE  a.dept_id = x.issue_dept) issueDept,                             ");
        query.getDate("issue_date", "issueDate,");
//        query.append("        ,issue_date issueDate     ");
        query.append("        y.part_no partNo                                                          ");
        query.append("        ,y.full_desc partDesc                                                     ");
        query.append("        ,x.part_grade partGrade                                                   ");
        query.append("        ,SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"') partGradeDesc  ");
        query.append("        ,x.issue_qty issueQty                                                     ");
        query.append("        ,(SELECT emp_name                                                         ");
        query.append("          FROM   TAEMP a                                                          ");
        query.append("          WHERE  a.emp_id = x.rec_by) recByName                                   ");
        query.append("        ,ptemgiss_status ptemgissStatus                                           ");
        query.append("        ,SFACODE_TO_DESC(x.ptemgiss_status,'PTEMGISS_STATUS','SYS','','"+user.getLangId()+"') ptemgissStatusDesc     ");
        query.append("        ,ptemg_task_status ptemgTaskStatus                                        ");
        query.append("        ,SFACODE_TO_DESC(x.ptemg_task_status,'PTEMG_TASK_STATUS','SYS','','"+user.getLangId()+"') ptemgTaskStatusDesc     ");
        query.append("        ,(SELECT a.wo_no                                                          ");
        query.append("          FROM   TAWORKORDER a                                                    ");
        query.append("          WHERE  a.wkor_id = x.wkor_id) woNo                                      ");
        query.append("        ,(SELECT a.description                                                    ");
        query.append("          FROM   TAWORKORDER a                                                    ");
        query.append("          WHERE  a.wkor_id = x.wkor_id) woDesc                                    ");
        query.append("        ,x.remark                                                                 ");
        query.append("        ,x.req_date reqDate                                                       ");
        query.append("        ,(SELECT description                                                      ");
        query.append("          from tadept                                                             ");
        query.append("          where dept_id=x.req_dept) reqDeptDesc                                   ");
        query.append("        ,(SELECT emp_name                                                         ");
        query.append("          FROM   TAEMP a                                                          ");
        query.append("          WHERE  a.emp_id = x.req_by) reqByName                                   ");
        query.append("        ,(SELECT wname                                                            ");
        query.append("          FROM   TAWAREHOUSE a                                                    ");
        query.append("          WHERE  a.wcode_id = x.wcode_id) fromWname                               ");
        query.append("        ,(SELECT wname                                                            ");
        query.append("          FROM   TAWAREHOUSE a                                                    ");
        query.append("          WHERE  a.wcode_id = x.to_wcode_id) toWname                              ");
        query.append("FROM TAPTEMGISSLIST x, TAPARTS y                                                  ");
        query.append("WHERE x.part_id = y.part_id                                                       ");
        query.append("AND ptemgiss_status = 'C'");
        query.append("AND ptemg_task_status = 'W'");
        query.getLikeQuery("y.full_desc", keySearchVal);
        query.append("ORDER BY  1                                                       ");

        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findEqCtgPartList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                ");
        query.append("          (SELECT description||', '||pt_size                          ");
        query.append("           FROM TAPARTS                                               ");
        query.append("          WHERE comp_no = x.comp_no                                   ");
        query.append("            AND part_id = x.part_id)                Zdisplay          ");
        query.append("        , x.eq_ctg_part_id                          eqCtgPartId       ");
        query.append("        , x.eq_ctg_asmb_id                          eqCtgAsmbId       ");
        query.append("        , (SELECT description                                         ");
        query.append("           FROM TAEQCTGASMB                                           ");
        query.append("          WHERE comp_no = x.comp_no                                   ");
        query.append("            AND eq_ctg_asmb_id = x.eq_ctg_asmb_id)  eqCtgAsmbDesc     ");
        query.append("        , x.part_id                                 partId            ");
        query.append("        , (SELECT part_no                                             ");
        query.append("           FROM TAPARTS                                               ");
        query.append("          WHERE comp_no = x.comp_no                                   ");
        query.append("            AND part_id = x.part_id)                partNo            ");
        query.append("        , (SELECT description||', '||pt_size                          ");
        query.append("           FROM TAPARTS                                               ");
        query.append("          WHERE comp_no = x.comp_no                                   ");
        query.append("            AND part_id = x.part_id)                partNameSize      ");
        query.append("FROM   TAEQCTGPART x                                                  ");
        query.append("WHERE  1=1                                                            ");
        query.getAndQuery("x.comp_no", conditionParam);
        query.getAndQuery("x.is_use", conditionParam);
        query.getAndQuery("x.eqctg_id", conditionParam);
        
        query.append("ORDER BY  1                                                           ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findEqCtgSpecList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("     x.prompt                       Zdisplay          ");
        query.append("   , x.eq_ctg_asmb_id               eqCtgAsmbId       ");
        query.append("   , (SELECT a.description                            ");
        query.append("      FROM TAEQCTGASMB a                              ");
        query.append("      WHERE a.comp_no = x.comp_no                     ");
        query.append("      AND a.eq_ctg_asmb_id = x.eq_ctg_asmb_id)    eqCtgAsmbDesc       ");
        query.append("   , x.categ                        category          ");
        query.append("   , x.prompt                       prompt            ");
        query.append("   , x.uom                          uom               ");
        query.append("   , x.eqctg_id                     eqctgid           ");
        query.append("   , x.eqctgspec_id                 eqCtgSpecId       ");
        query.append("FROM TAEQCTGSPEC x                                    ");
        query.append("WHERE  1=1                                            ");
        query.getAndQuery("x.comp_no", conditionParam);
        query.getAndQuery("x.is_use", conditionParam);
        query.getAndQuery("x.eqctg_id", conditionParam);
        query.append("ORDER BY x.categ                                      ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findLinkedFuncList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("    x.description         Zdisplay            ");
        query.append("  , x.linkedfunc_id       LINKEDFUNCID        ");
        query.append("  , x.linkedfunc_no       LINKEDFUNCNO        ");
        query.append("  , x.description         LINKEDFUNCDESC      ");
        query.append("  , x.linkedfunc_method   METHOD              ");
        query.append("  , x.is_use              ISUSEID             ");
        query.append("  , SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS','', '"+user.getLangId()+"') ISUSEDESC       ");
        query.append("  , x.REMARK              REMARK              ");
        query.append("FROM TALINKEDFUNC x                           ");
        query.append("WHERE  1=1                                    ");     
        query.getAndQuery("x.is_use", conditionParam);
        query.getAndQuery("x.description", keySearchVal);
        query.append("ORDER BY x.linkedfunc_no                      ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findMeasureTime(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    ");
        query.append("       x.description          AS Zdisplay                 ");
        query.append("       ,x.description         AS measureTime              ");
        query.append("       ,x.cdsysd_no           AS measureTimeId            ");
        query.append("FROM   TACDSYSD x                                         ");
        query.append("WHERE  1=1                                                ");
        query.getAndQuery("x.list_type", "MEASURE_TIME");
        query.getAndQuery("x.is_use", conditionParam);
        query.append("ORDER BY x.ord_no                                         ");
        
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findWoPlanList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User loginUser) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    	");
        query.append("       x.description 									AS Zdisplay             ");
        query.append("       ,x.wkor_id 									AS wkorId               ");
        query.append("       ,x.wo_no 										AS woNo                 ");
        query.append("       ,x.description 								AS woDesc               ");
        query.append("       ,SFACODE_TO_DESC(wo_status,'WO_STATUS','SYS','','"+loginUser.getLangId()+"')	AS woStatusDesc	");
        query.append("       ,z.description									AS equipDesc  			 ");
        query.append("       ,(SELECT c.full_desc                           						");
        query.append("         FROM TAEQLOC c                    									");
        query.append("         WHERE c.comp_no = x.comp_no                                			");
        query.append("            AND c.eqloc_id = z.eqloc_id )   			AS eqLocDesc            ");
        query.append("       ,(SELECT description                                                 	");
        query.append("         FROM TADEPT                                                      	");
        query.append("         WHERE comp_no = x.comp_no                                         	");
        query.append("           AND dept_id = x.dept_id)                   AS deptDesc   			");
        query.append("       ,SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"')             AS woTypeDesc	");
        query.append("       ,SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+loginUser.getLangId()+"')	AS pmTypeDesc	");
        query.append("       ,(SELECT emp_name                                                    	");
        query.append("         FROM TAEMP                                                       	");
        query.append("         WHERE comp_no = x.comp_no                                         	");
        query.append("           AND emp_id = x.emp_id)                     AS empDesc        		");
        query.append("       ,(SELECT c.emp_name                              						");
        query.append("          FROM TAEMP c                        								");
        query.append("          WHERE c.comp_no = x.comp_no                                   		");
        query.append("            AND c.emp_id = z.sub_mng_id )           	AS subMng       		");
        query.append("FROM TAWOPLAN x INNER JOIN TAWOEQUIP y										");
        query.append("ON x.comp_no = y.comp_no														");
        query.append(" AND x.wkor_id = y.wkor_id													");
        query.append("		INNER JOIN TAEQUIPMENT z												");
        query.append("      ON y.comp_no = z.comp_no												");
        query.append("       AND y.equip_id = z.equip_id											");
        query.append("WHERE 1=1                                                                 	");
        query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");

        query.getLikeQuery("x.description", keySearchVal);
        query.getAndQuery("x.wo_status", conditionParam);
        query.append("ORDER BY x.wo_no DESC                                                       	");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    @Override
    public List findDbConList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User loginUser) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("         x.description   AS Zdisplay      ");
        query.append("       , x.dbcontents_id AS usrDbCntId   ");
        query.append("       , x.description   AS usrDbCntDesc ");
        query.append("  FROM TADBCONTENTS x                     ");
        query.append(" WHERE 1=1                                ");
        query.getAndQuery("x.is_use", conditionParam);
        query.append(" ORDER BY x.dbcontents_id                 ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findPtStckList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                             ");
        query.append("      c.full_desc                  AS Zdisplay                     ");
        query.append("      ,a.comp_no                                                   ");
        query.append("      ,a.wcode_id                                                  ");
        query.append("      ,b.wname                                                     ");
        query.append("      ,a.part_id                                                   ");
        query.append("      ,c.part_no                                                   ");
        query.append("      ,c.full_desc                 AS pt_name_size                ");
        query.append("      ,c.description               AS pt_desc                      ");
        query.append("      ,c.pt_size                   AS pt_size                      ");
        query.append("      ,c.model                     AS model                        ");
        query.append("      ,c.part_group                                                ");
        query.append("      ,SFACODE_TO_DESC(c.part_group, 'PART_GROUP', 'USR', a.comp_no,'"+user.getLangId()+"')      part_group_desc   ");
        query.append("      ,c.pt_abc_class                                              ");
        query.append("      ,SFACODE_TO_DESC(c.pt_abc_class, 'PT_ABC_CLASS', 'SYS', a.comp_no,'"+user.getLangId()+"')  pt_abc_class_desc ");
        query.append("      ,a.part_grade                                                ");
        query.append("      ,SFACODE_TO_DESC(a.part_grade, 'PART_GRADE', 'SYS', a.comp_no,'"+user.getLangId()+"')      part_grade_desc   ");
        query.append("      ,a.stock_qty                                                 ");
        query.append("      ,a.bin_no                                                    ");
        query.append("      ,(SELECT safty_qty                                           ");
        query.append("        FROM TAPTSAFTYSTOCK aa                                     ");
        query.append("        WHERE aa.comp_no = a.comp_no                               ");
        query.append("        AND aa.wcode_id = a.wcode_id                               ");
        query.append("        AND aa.part_id = a.part_id) AS min_safty_qty               ");
        query.append("      ,(SELECT max_safty_qty                                       ");
        query.append("        FROM TAPTSAFTYSTOCK aa                                     ");
        query.append("        WHERE aa.comp_no = a.comp_no                               ");
        query.append("        AND aa.wcode_id = a.wcode_id                               ");
        query.append("        AND aa.part_id = a.part_id) AS max_safty_qty               ");
        query.append("FROM TAPTSTOCK a INNER JOIN TAWAREHOUSE b                          ");
        query.append("ON a.comp_no = b.comp_no                                           ");
        query.append("AND a.wcode_id = b.wcode_id                                        ");
        query.append("INNER JOIN TAPARTS c                                               ");
        query.append("ON a.comp_no = c.comp_no                                           ");
        query.append("AND a.part_id = c.part_id                                          ");
        query.append("WHERE 1=1                                                          ");
        query.getAndQuery("b.is_use", "Y");
        query.getAndQuery("c.is_use", "Y");
        query.getAndQuery("c.is_deleted", "N");
        query.getAndQuery("a.comp_no", conditionParam);
        query.getAndQuery("b.wh_categ", conditionParam);
        query.getAndQuery("b.wcode_id", conditionParam);
        query.getAndQuery("c.part_categ", conditionParam);
        query.getAndQuery("c.model", conditionParam);
        if(conditionParam.containsKey("stock_qty")) 
        {
        	query.append("AND nvl(a.stock_qty,0) >= "+conditionParam.get("stock_qty")+"");
        }
        query.append("ORDER BY c.part_no, b.wname       ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findPtprList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength,
            User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("        x.description||'('||y.description||')'  AS Zdisplay      ");
        query.append("       ,x.ptprlist_id prprlistId ");
        query.append("       ,x.ptprlist_no ptprlistNo ");
        query.append("       ,z.polist_id polistId ");
        query.append("       ,z.ptpritem_id ptpritemId ");
        query.append("       ,x.description||'('||y.description||')' description    ");
        query.append("       ,x.ptprlist_status ptprlistStatus ");
        query.append("       ,x.dept_id deptId ");
        query.append("      ,(SELECT description                       ");
        query.append("        FROM   TADEPT                              ");
        query.append("        WHERE  comp_no = x.comp_no             ");
        query.append("         AND   dept_id = x.dept_id) deptDesc      ");
        query.append("       ,x.req_date reqDate   ");
        query.append("       ,y.description itemDesc   ");
        query.append("       ,y.rec_qty recQty     ");
        query.append("       ,y.pt_size spec   ");
        query.append("       ,y.unit_price unitPrice   ");
        query.append("            ,y.part_id partId     ");
        query.append("            ,(SELECT A.part_no    ");
        query.append("              FROM TAPARTS A  ");
        query.append("              WHERE A.part_id = y.part_id ");
        query.append("             ) partNo ");

        query.append("FROM TAPTPOITEM z INNER JOIN TAPTPRITEM y ON z.ptpritem_id = y.ptpritem_id    ");
        query.append("INNER JOIN TAPTPRLIST x ON y.ptprlist_id = x.ptprlist_id  ");

        query.append("WHERE  1 = 1                                  ");
   
        query.getAndQuery("x.ptprlist_status", conditionParam);
        query.getAndQuery("x.dept_id", conditionParam);
        query.getAndQuery("x.comp_no", conditionParam);
        
        query.getAndQuery("x.description", keySearchVal);
        
        query.append("ORDER BY x.ptprlist_id desc                  ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    

    @Override
    public List findEqAssList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("        x.eqasslist_no || ',' || y.DESCRIPTION        AS Zdisplay         ");
        query.append("        ,x.eqasslist_id                               AS eqasslistId      ");
        query.append("FROM TAEQASSLIST x JOIN TAEQUIPMENT y                                     ");
        query.append("ON X.COMP_NO = Y.COMP_NO                                                  ");
        query.append("    AND X.EQUIP_ID = Y.EQUIP_ID                                           ");
        query.append("WHERE  1=1                                                                ");
        // 평가등급기준표
        if(conditionParam.containsKey("eqasslistId") && !"".equals(conditionParam.get("eqasslistId")))
        {
            query.append("AND x.assbaselist_id = (SELECT a.assbaselist_id       ");
            query.append("                      FROM TAEQASSLIST a              ");
            query.append("                      WHERE 1=1                       ");
            query.getAndQuery("a.eqasslist_id", conditionParam.get("eqasslistId"));
            query.getAndQuery("a.comp_no", user.getCompNo());
            query.append("                   )                                  ");
        }
        
        query.append("ORDER BY x.ass_date desc                                  ");
        
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findPtpnList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength,
            User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("        y.description ||','|| y.pt_size       Zdisplay        ");
        query.append("       ,x.ptpnlist_id                         ptPnListId      ");
        query.append("       ,x.part_id                             partId          ");
        query.append("       ,x.pt_size                             ptSize          ");
        query.append("       ,x.rec_qty                             recQty          ");
        query.append("FROM TAPTPNLIST x LEFT OUTER JOIN TAPARTS y                   ");
        query.append("ON x.COMP_NO = y.COMP_NO AND x.PART_ID = y.PART_ID            ");
        query.append("WHERE  1 = 1                                                  ");
        query.getAndQuery("x.comp_no", conditionParam);
        
        query.append("ORDER BY x.ptpnlist_id desc                                   ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findEqCtgPartAcAcList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                                                                                                   ");
        query.append(" x.comp_no                                                                                            AS compNo           ");
        query.append(" ,x.eqctgpmpoint_id                                                                                   AS eqCtgPmPointId   ");
        query.append(" ,(SELECT eqctg_id FROM TAEQCTG WHERE comp_no = x.comp_no AND eqctg_id = x.eqctg_id)                  AS eqCtgId          ");
        query.append(" ,x.cycle                                                                                             AS cycle            ");
        query.append(" ,x.eq_ctg_asmb_id                                                                                        AS eqasmbId         ");
        query.append(" ,(SELECT description FROM TAEQASMB WHERE comp_no = x.comp_no AND eq_ctg_asmb_id = x.eq_ctg_asmb_id)          AS eqasmbDesc       ");
        query.append(" ,x.period_type                                                                                       AS periodTypeId     ");
        query.append(" ,SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE','SYS','"+user.getCompNo()+"', '"+user.getLangId()+"')  AS periodTypeDesc   ");
        query.append(" ,x.check_point                                                                                       AS checkPoint       ");
        query.append(" ,x.check_method                                                                                      AS checkMethod      ");
        query.append(" ,x.fit_basis                                                                                         AS fitBasis         ");
        query.append(" ,x.check_type                                                                                        AS checkTypeId      ");
        query.append(" ,SFACODE_TO_DESC(x.check_type, 'CHECK_TYPE','SYS','"+user.getCompNo()+"','"+user.getLangId()+"')     AS checkTypeDesc    ");
        query.append(" ,x.check_min                                                                                         AS checkMin         ");
        query.append(" ,x.check_basis_val                                                                                   AS checkBasisVal    ");
        query.append(" ,x.check_max                                                                                         AS checkMax         ");
        query.append(" ,x.uom                                                                                               AS uom              ");
        query.append(" ,x.ord_no                                                                                            AS ordNo            ");
        query.append(" , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"')          AS isUse            ");
        query.append(" ,x.remark                                                                                            AS remark           ");
        query.append(" ,x.work_time                                                                                         AS PredTime         ");
        query.append(" FROM TAEQCTGPMPOINT x                                                                                                    ");
        query.append(" WHERE 1=1                                                                                                                ");

        return getJdbcTemplate().queryForList(query.toString(acLength));  
    }

    @Override
    public List findWoReqList(String keySearchVal, Map<String, String> columnMap, 
            Map<String, String> conditionParam, String lang, String acLength, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("       description                                        AS Zdisplay     ");
        query.append("      ,woreq_id                                           AS woReqId      ");
        query.append("      ,woreq_no                                           AS woReqNo      ");
        query.append("      ,description                                        AS woReqDesc    ");
        query.append("      ,SFACODE_TO_DESC(x.woreq_status,'WOREQ_STATUS','SYS','','"+user.getLangId()+"') AS woReqStatusDesc  ");
        query.append("      ,(SELECT a.description                                              ");
        query.append("        FROM TAEQUIPMENT a                                                ");
        query.append("        WHERE a.comp_no = x.comp_no                                       ");
        query.append("         AND  a.equip_id = x.equip_id    )                AS equipDesc    ");
        query.append("      ,(SELECT a.full_desc                                                ");
        query.append("         FROM TAEQLOC a                                                   ");
        query.append("         WHERE a.comp_no = x.comp_no                                      ");
        query.append("          AND a.eqloc_id = x.eqloc_id    )                AS eqLocDesc    ");
        query.append("      ,(SELECT description                                                ");
        query.append("         FROM TADEPT                                                      ");
        query.append("        WHERE comp_no = x.comp_no                                         ");
        query.append("          AND dept_id = x.req_dept_id     )               AS reqDept      ");
        query.append("      ,SFACODE_TO_DESC(x.woreq_type,'WOREQ_TYPE','SYS','','"+user.getLangId()+"')     AS woReqTypeDesc    ");
        query.append("      ,(SELECT emp_name                                                   ");
        query.append("         FROM TAEMP                                                       ");
        query.append("         WHERE comp_no = x.comp_no                                        ");
        query.append("          AND emp_id = x.req_emp_id       )               AS reqEmp       ");
        query.append("FROM TAWOREQ x                                                            ");
        query.append("WHERE 1=1                                                                 ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getLikeQuery("x.description", keySearchVal);

        query.append("ORDER BY x.woreq_no DESC                                                  ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findPartListBinList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT         ");
        query.append("       x.bin_no                       AS Zdisplay      ");
        query.append("      ,X.PTBINLIST_ID                 AS ptBinListId   ");
        query.append("      ,x.wcode_id                     AS wcode_id      ");
        query.append("      ,X.BIN_NO                       AS bin_no        ");
        query.append("      ,x.remark                       AS remark        ");
        query.append("FROM taptbinlist x        ");
        query.append("WHERE 1=1        ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.wcode_id", conditionParam);
        query.getAndQuery("x.is_use", conditionParam);
        query.getLikeQuery("x.PTBINLIST_ID", keySearchVal);
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findAccountList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                ");
        query.append("    x.description AS Zdisplay         ");
        query.append("    ,x.comp_no        ");
        query.append("    ,x.accnt_id       ");
        query.append("    ,x.accnt_no       ");
        query.append("    ,x.description    ");
        query.append("    ,SFACODE_TO_DESC(x.accnt_type,'ACCNT_TYPE','SYS','','"+user.getLangId()+"') accnt_type     ");
        query.append("    ,x.is_use         ");
        query.append("    ,x.remark         ");
        query.append("FROM TAACCOUNT x      ");
        query.append("WHERE 1=1             ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getLikeQuery("x.description", keySearchVal);
        query.getAndQuery("x.accnt_type", conditionParam);
        query.getAndQuery("x.is_use", conditionParam);
        query.append("ORDER BY x.description");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
    
    @Override
    public List findWoPartsList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                      ");
        query.append("       y.pt_desc                                                                        AS Zdisplay,        ");
        query.append("       x.wkor_id                                                                        AS wkorId,          ");
        query.append("       x.wo_no                                                                          AS woNo,            ");
        query.append("       x.description                                                                    AS woDesc,          ");
        query.append("       x.wkor_date                                                                      AS woDate,          ");
        query.append("       y.item_no                                              						  AS equipNo,         ");
        query.append("       y.description                                              					  AS equipDesc,       ");
        query.append("       (SELECT c.full_desc                                                              					  ");
        query.append("        FROM TAEQLOC c                                                          							  ");
        query.append("        WHERE c.comp_no = x.comp_no                                                                         ");
        query.append("         AND c.eqloc_id = y.eqloc_id    )                                               AS eqLocDesc,       ");
        query.append("       SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')         AS woStatusDesc,    ");
        query.append("       (SELECT description                                                                                  ");
        query.append("        FROM TADEPT                                                                                         ");
        query.append("        WHERE comp_no = x.comp_no                                                                           ");
        query.append("        AND dept_id = x.dept_id)                                                        AS deptDesc,        ");
        query.append("       (SELECT description                                                                                  ");
        query.append("        FROM TAWKCTR                                                                                        ");
        query.append("        WHERE comp_no = x.comp_no                                                                           ");
        query.append("        AND wkctr_id = x.wkctr_id)                                                      AS wkCtrDesc,       ");
        query.append("       (SELECT emp_name                                                                                     ");
        query.append("        FROM TAEMP                                                                                          ");
        query.append("        WHERE comp_no = x.comp_no                                                                           ");
        query.append("        AND emp_id = x.emp_id)                                                          AS empDesc,         ");
        query.append("       y.wopart_id                                                                      AS woPartId,        ");
        query.append("       (SELECT wname                                                                                        ");
        query.append("        FROM TAWAREHOUSE                                                                                    ");
        query.append("        WHERE comp_no = y.comp_no                                                                           ");
        query.append("        AND wcode_id = y.wcode_id)                                                      AS issWname,        ");
        query.append("       (SELECT part_no                                                                                      ");
        query.append("        FROM TAPARTS                                                                                        ");
        query.append("        WHERE comp_no = y.comp_no                                                                           ");
        query.append("        AND part_id = y.part_id)                                                        AS partNo,          ");
        query.append("       (SELECT full_desc                                                                                    ");
        query.append("        FROM TAPARTS                                                                                        ");
        query.append("        WHERE comp_no = y.comp_no                                                                           ");
        query.append("        AND part_id = y.part_id)                                                        AS partPtSize,      ");
        query.append("       SFACODE_TO_DESC(y.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"')       AS partGrade,       ");
        query.append("       y.use_qty                                                                        AS useQty,          ");
        query.append("       y.remark                                                                         AS remark           ");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOPARTS y                                                                   		");
		query.append("ON x.comp_no = y.comp_no                                                                                    		");
		query.append(" AND x.wkor_id = y.wkor_id                                                                                   		");
		query.append("		INNER JOIN TAWOEQUIP a                              														");
		query.append("		ON x.comp_no = a.comp_no                                                                          			");
		query.append("		 AND x.wkor_id = a.wkor_id 																		  			");
		query.append("				INNER JOIN TAEQUIPMENT b                              		  			  			  			  	");
		query.append("          	ON a.comp_no = b.comp_no                                	  			  			  			  	");
		query.append("          	 AND a.equip_id = b.equip_id									  			  			  			");
		query.append("WHERE 1=1                                                                                                   		");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getLikeQuery("y.pt_desc", keySearchVal);
        query.getAndQuery("x.wo_status", conditionParam);
        query.getAndQuery("y.wcode_id", conditionParam);
        query.getAndQuery("y.part_id", conditionParam);
        query.getAndQuery("y.part_grade", conditionParam);
        query.getAndQuery("y.ptisslist_id", conditionParam);
        query.append("ORDER BY x.wo_no");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findPtIssList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                              ");
        query.append("    a.ptisslist_id                                                                          AS Zdisplay             ");
        query.append("    ,a.ptisslist_id                                                                         AS ptisslistId          ");
        query.append("    ,(SELECT description FROM TADEPT                                                                                ");
        query.append("      WHERE comp_no = a.comp_no                                                                                     ");
        query.append("      AND dept_id = a.issue_dept)                                                           AS issueDeptDesc        ");
        query.append("    ,(SELECT emp_name FROM TAEMP                                                                                    ");
        query.append("      WHERE comp_no = a.comp_no                                                                                     ");
        query.append("      AND emp_id = a.issue_by)                                                              AS issueByDesc          ");
        query.append("    ,a.issue_date                                                                           AS issueDate            ");
        query.append("    ,b.part_no                                                                              AS partNo               ");
        query.append("    ,b.description                                                                          AS partName             ");
        query.append("    ,SFACODE_TO_DESC(a.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"')             AS partGradeDesc        ");
        query.append("    ,a.issue_qty                                                                            AS issueQty             ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE                                                                                 ");
        query.append("      WHERE comp_no = a.comp_no                                                                                     ");
        query.append("      AND wcode_id = a.wcode_id)                                                            AS issueWname           ");
        query.append("    ,SFACODE_TO_DESC(a.ptiss_type,'PTISS_TYPE','SYS','','"+user.getLangId()+"')             AS ptIssTypeDesc        ");
        query.append("    ,SFACODE_TO_DESC(a.ptiss_status,'PTISS_STATUS','SYS','','"+user.getLangId()+"')         AS ptIssStatusDesc      ");
        query.append("    ,(SELECT description FROM TADEPT                                                                                ");
        query.append("      WHERE comp_no = a.comp_no                                                                                     ");
        query.append("      AND dept_id = a.rec_dept)                                                             AS recDeptDesc          ");
        query.append("    ,(SELECT emp_name FROM TAEMP                                                                                    ");
        query.append("      WHERE comp_no = a.comp_no                                                                                     ");
        query.append("      AND emp_id = a.rec_by)                                                                AS recByDesc            ");
        query.append("    ,a.remark                                                                               AS remark               ");
        query.append("FROM TAPTISSLIST a INNER JOIN TAPARTS b                                                                             ");
        query.append("ON a.comp_no = b.comp_no                                                                                            ");
        query.append("AND a.part_id = b.part_id                                                                                           ");
        query.append("WHERE 1=1                                                                                                           ");
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.getLikeQuery("a.ptisslist_id", keySearchVal);
        query.getAndQuery("a.ptiss_status", conditionParam);
        query.getAndQuery("a.plant", conditionParam);
        query.getAndQuery("a.rec_dept", conditionParam);
        query.getAndQuery("a.rec_by", conditionParam);
        query.append("ORDER BY a.issue_date desc");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

    @Override
    public List findPoItemList(String keySearchVal, Map<String, String> columnMap, 
            Map<String, String> conditionParam, String lang, String acLength, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                                           ");
        query.append("        y.polist_id                              AS POLISTID      ");
        query.append("      , y.polist_no                              AS POLISTNO      ");
        query.append("      , x.ptpoitem_id                            AS POITEMID      ");
        query.append("      , z.part_id                                AS PARTID       ");
        query.append("      , z.part_no                                AS PARTNO       ");
        query.append("      , z.description ||' / '|| z.pt_size        AS PARTNAMESIZE  ");
        query.append("      , x.po_qty                                 AS POQTY         ");
        query.append("      , x.ptpritem_id                            AS PRITEMID      ");
        query.append("      , x.gr_qty                                 AS GRQTY         ");
        query.append("      , y.plant                                  AS PLANT         ");
        query.append("      , ( SELECT c.description                                    ");
        query.append("            FROM TAPLANT c                                        ");
        query.append("           WHERE c.comp_no = y.comp_no                            ");
        query.append("             AND c.plant = y.plant )             AS PLANTDESC     ");
        query.append("      , y.vendor_id                              AS VENDORID      ");
        query.append("      , y.part_grade                             AS PARTGRADE     ");
        query.append("      , z.description                            AS PARTDESC      ");
        query.append("      , z.pt_size                                AS PARTSIZE      ");
        query.append("      , z.uom                                    AS UOM           ");
        query.append("   FROM TAPTPOITEM x                                              ");
        query.append("  INNER JOIN TAPTPOLIST y                                         ");
        query.append("          ON x.comp_no = y.comp_no                                ");
        query.append("         AND x.polist_id = y.polist_id                            ");
        query.append("  INNER JOIN TAPARTS z                                            ");
        query.append("          ON x.comp_no = z.comp_no                                ");
        query.append("         AND x.part_id = z.part_id                                ");
        query.append("  WHERE x.po_qty > x.gr_qty                                       ");
        query.append("  ORDER BY x.ptptoitem_id DESC                                    ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }

	@Override
	public List findToolAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
			String lang, String acLength, User user) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                        ");
        query.append("      DISTINCT(description) AS Zdisplay                       ");
        query.append("      ,part_id                                                ");
        query.append("      ,part_no                                                ");
        query.append("      ,description                                            ");
        query.append("      ,pt_size                                                ");
        query.append("      ,model                                                  ");
        query.append("      ,maker                                                  ");
        query.append("FROM  TAPARTS                                                 ");
        query.append("WHERE 1 = 1                                                   ");
        query.getAndQuery("comp_no", user.getCompNo());
        query.getStringEqualQuery("is_deleted", "N");
        query.getLikeQuery("description", keySearchVal);
        query.getAndQuery("part_categ", "TOOL");
        query.append("ORDER BY  1                                                   ");

         return getJdbcTemplate().queryForList(query.toString(acLength));
    }
	@Override
    public List findWoLetCtgAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("      DISTINCT(x.description) AS Zdisplay         ");
        query.append("      ,x.woletctg_id          AS woLetCtgId       ");
        query.append("      ,x.woletctg_type        AS woLetCtgType     ");
        query.append("      ,SFACODE_TO_DESC(x.woletctg_type,'WOLETCTG_TYPE','SYS','','ko') AS woLetCtgTypeDesc ");
        query.append("      ,x.description          AS woLetCtgDesc     ");
        query.append("FROM  TAWOLETCTG x                                ");
        query.append("WHERE 1 = 1                                       ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getLikeQuery("x.description", keySearchVal);
        
        query.append("ORDER BY  1                                       ");
        
        return getJdbcTemplate().queryForList(query.toString(acLength));
    }
	
	@Override
	public List findMsgCategAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
			String lang, String acLength, User user)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                                            ");
		query.append("      DISTINCT(SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', '"+user.getLangId()+"' )) AS Zdisplay         ");
		query.append("	  , x.messagecateg_id		msgCategId			");
        query.append("    , x.message_object_type 	msgObjType			");
        query.append("    , SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', '"+user.getLangId()+"' ) msgObjTypeDesc		");
        query.append("    , SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )  isMailUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )   isSmsUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )    isUseDesc			");
        query.append("    , x.remark    			remark 				");
        query.append("FROM TAMESSAGECATEG x								");
		query.append("WHERE 1 = 1                                       ");
		query.getLikeQuery("x.message_object_type||SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', '"+user.getLangId()+"' )", keySearchVal);
		query.getAndQuery("x.mail_use", conditionParam);
		query.getAndQuery("x.sms_use", conditionParam);
		query.getAndQuery("x.is_use", conditionParam);
		query.append("ORDER BY  1                                       ");
		
		return getJdbcTemplate().queryForList(query.toString(acLength));
	}
	
	@Override
	public List findMsgCompAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
			String lang, String acLength, User user)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                                            ");
		query.append("      DISTINCT(x.message_object_type||', '||SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', '"+user.getLangId()+"' )) AS Zdisplay         ");
		query.append("	  , x.msgcompset_id			msgCompSetId		");
		query.append("    , x.message_object_type 	msgObjType			");
		query.append("    , SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', '"+user.getLangId()+"' ) msgObjTypeDesc		");
		query.append("    , SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )  isMailUseDesc		");
		query.append("    , SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )   isSmsUseDesc		");
		query.append("    , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )    isUseDesc			");
		query.append("    , x.remark    			remark 				");
		query.append("FROM TAMSGCOMPSET x								");
		query.append("WHERE 1 = 1                                       ");
		query.getLikeQuery("x.message_object_type||SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', '"+user.getLangId()+"' )", keySearchVal);
		query.getAndQuery("x.mail_use", conditionParam);
		query.getAndQuery("x.sms_use", conditionParam);
		query.getAndQuery("x.is_use", conditionParam);
		query.append("ORDER BY  1                                       ");
		
		return getJdbcTemplate().queryForList(query.toString(acLength));
	}
	
    @Override
    public List findUsrPlantAuthAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
    		String lang, String acLength, User user) {
    	
    	QueryBuffer query = new QueryBuffer();
    	
        query.append("SELECT                                                            ");
        query.append("       DISTINCT(x.description)    AS Zdisplay                     ");//First one only For Display
        query.append("       ,x.description             AS description                  ");
        query.append("       ,x.plant                   AS plant                        ");
        query.append("FROM   TAPLANT x 													");
        query.append("WHERE  1=1                                                        ");   
        query.getAndQuery("x.is_use", "Y");
        query.getLikeQuery("x.description", keySearchVal);
        query.getAndQuery("x.comp_no", conditionParam);
        query.getAndQuery("x.is_use", conditionParam);
        query.append("ORDER BY  1                                                       ");
   	
    	return getJdbcTemplate().queryForList(query.toString(acLength));
    }

	public List findPmUInsAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
			String lang, String acLength, User user)
	{
		QueryBuffer query = new QueryBuffer();
    	
        query.append("SELECT                                                            		");
        query.append("		 x.pm_no 									AS pmNo                	");
        query.append("      ,x.description								AS Zdisplay				");
        query.append("      ,x.description 								AS description			");
        query.append("      ,(SELECT b.description                                           	");
        query.append("        FROM TAPMEQUIP a, TAEQUIPMENT b                                   ");
        query.append("        WHERE a.comp_no = b.comp_no                                       ");
        query.append("         AND a.equip_id = b.equip_id                                      ");
        query.append("         AND a.pm_id = x.pm_id                                            ");
        query.append("         AND a.comp_no = x.comp_no                                        ");
        query.append("         AND rownum = 1 ) 						AS equipDesc			");
        query.append("      ,SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+user.getCompNo()+"')	AS deptDesc	");
        query.append("      ,(SELECT a.emp_name                                            		");
        query.append("        FROM   TAEMP a                                               		");
        query.append("        WHERE  a.emp_id = x.emp_id) 				AS empName				");
        query.append("      ,SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"')					AS pmTypeDesc	");
        query.append("      ,SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"')				AS scheduleType	");
        query.append("      ,TO_CHAR(cycle) || SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"')	AS cycleDesc	");
        query.append("      ,x.cycle 									AS cycle				");
        query.append("      ,SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"') 					AS periodType	");
        query.append("      ,x.USAGE									AS usage                ");
        query.append("      ,x.is_active								AS isActive             ");
        query.append("      ,x.pm_id									AS pmId                 ");
        query.append("      ,x.pm_type									AS pmType               ");
        query.append("      ,x.remark									AS remark             	");
        query.append("      ,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=x.pm_type AND list_type= x.wo_type||'_TYPE')	AS param	");
        query.append("FROM    TAPMLST x                                                         ");
        query.append("WHERE  1=1                                                        		");   
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        query.getAndQuery("x.is_active", conditionParam);
        query.getAndQuery("x.wo_type", conditionParam);
        query.getAndQuery("x.plant", conditionParam);
        query.getLikeQuery("x.pm_no", keySearchVal);

        query.append("ORDER BY x.description DESC                               				");

    	return getJdbcTemplate().queryForList(query.toString(acLength));
	}

	  @Override
	  public List findUsageWrkAcList(String keySearchVal, Map<String, String> columnMap,
	          Map<String, String> conditionParam, String lang, String acLength, User user) {
	      
	      QueryBuffer query = new QueryBuffer();
	      
	      query.append("SELECT                                            ");
	      query.append("     description              AS Zdisplay         ");
	      query.append("    ,lnwrklist_id             lnWrkListId         ");
	      query.append("    ,comp_no                  compNo              ");
	      query.append("    ,(SELECT description FROM TACOMP WHERE comp_no = a.comp_no)   compDesc        ");
	      query.append("    ,lnwrklist_id             lnWrkListNo         ");
	      query.append("    ,description              description         ");
	      query.append("    ,(SELECT description FROM TAEQLOC WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)    eqlocDesc     ");
	      query.append("    ,(SELECT description FROM TAWRKCALLIST WHERE comp_no = a.comp_no AND wrkcallist_id = a.wrkcallist_id) wrkCalListDesc      ");
	      query.append("    ,(SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant)          plantDesc     ");
	      query.append("    ,a.REMARK                 remark              ");
	      
	      query.append("    ,a.plant                  plant               ");
	      query.append("    ,a.eqloc_id               eqloc_Id            ");
	      query.append("    ,(SELECT b.full_desc                          ");
	      query.append("       FROM TAEQLOC b                             ");
	      query.append("       WHERE b.comp_no = a.comp_no                ");
	      query.append("       AND b.eqloc_id = a.eqloc_id)   lineDesc    ");

	      query.append("FROM TALNWRKLIST a                                ");
	      query.append("WHERE  1 = 1                                      ");
	      
	      query.getAndQuery("comp_no", conditionParam);
	      query.getAndQuery("a.lnwrk_calendar_type", "U");
	      query.getLikeQuery("description", keySearchVal);
	      query.append("ORDER BY  2                                           ");
	      
	      return getJdbcTemplate().queryForList(query.toString(acLength));
	  }
	  
	  @Override
	  public List findMgrContractList(String keySearchVal, Map<String, String> columnMap,
			  Map<String, String> conditionParam, String lang, String acLength, User user) {
		  
		  QueryBuffer query = new QueryBuffer();
		  
	      query.append("SELECT                                            															");
	      query.append("      x.contract_id 																		contractId		");
		  query.append("    , x.contract_no 																		contractNo		");
		  query.append("    , x.description 																		Zdisplay		");
		  query.append("    , x.description 																		contractDesc	");
		  query.append("	  , x.vendor_id																			vendorId		");
		  query.append("    , (SELECT a.vendor_no FROM TAVENDOR a 																	");
		  query.append("        WHERE a.comp_no = x.comp_no 																		");
		  query.append("             AND a.vendor_id = x.vendor_id) 												vendorNo		");
		  query.append("    , (SELECT a.description FROM TAVENDOR a 																");
		  query.append("        WHERE a.comp_no = x.comp_no 																		");
		  query.append("             AND a.vendor_id = x.vendor_id) 												vendorDesc		");
		  query.append("    , x.contract_date 																		contractDate	");
		  query.append("    , x.start_date || '~' || x.end_date 													contractPeriod	");
		  query.append("    , x.start_date 																		contractStartDate	");
		  query.append("    , x.end_date 																			ContractEndDate	");
		  query.append("    , x.tot_amt 																			contractAmount	");
		  query.append("    , (SELECT SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getCompNo()+"') FROM dual ) 	isUse			");
		  query.append("    , x.remark 																				remark			");
	      query.append("FROM TACONTRACT x                                 ");
	      query.append("WHERE  1 = 1                                      ");
		  
		  query.getAndQuery("comp_no", conditionParam);
		  query.getAndQuery("is_use", conditionParam);
	      query.getLikeQuery("x.description", keySearchVal);
		  query.append("ORDER BY  2                                       ");
		  
		  return getJdbcTemplate().queryForList(query.toString(acLength));
	  }
	  
	  @Override
	  public List findWorkServiceList(String keySearchVal, Map<String, String> columnMap,
			  Map<String, String> conditionParam, String lang, String acLength, User user) {
		  
		  QueryBuffer query = new QueryBuffer();
		  
		  query.append("SELECT                                            														");
		  query.append("	a.service_id     											  					  AS serviceId      ");
	      query.append("	,a.service_no 												  					  AS serviceNo      ");
	      query.append("	,a.description 												  					  AS Zdisplay    	");
	      query.append("	,a.description 												  					  AS serviceName    ");
	      query.append("	,SFACODE_TO_DESC(a.service_uom,'SERVICE_UOM', 'SYS', '',  '"+user.getLangId()+"') AS serviceUom     ");
	      query.append("	,a.reg_date      											  					  AS regDate        ");
	      query.append("	,(SELECT  description FROM tadept x        															");
	      query.append("	  WHERE x.comp_no = a.comp_no AND x.dept_id =a.dept_id) 		  				  AS deptDesc       ");
	      query.append("	,(SELECT  emp_name FROM taemp y        																");
	      query.append("	  WHERE y.comp_no = a.comp_no AND y.emp_id =a.emp_id) 			  				  AS empDesc        ");
	      query.append("	,SFACODE_TO_DESC(a.is_use,'IS_USE','SYS','','"+user.getLangId()+"') 			  AS isUse          ");
	      query.append("	,a.remark 													  					  AS remark         ");
	      query.append("FROM taservice a        																				");
	      query.append("WHERE  1 = 1                                      														");
		  
		  query.getAndQuery("comp_no", conditionParam);
		  query.getAndQuery("is_use", conditionParam);
	      query.getLikeQuery("a.description", keySearchVal);
		  query.append("ORDER BY  2                                       ");
		  
		  return getJdbcTemplate().queryForList(query.toString(acLength));
	  }
	  
}