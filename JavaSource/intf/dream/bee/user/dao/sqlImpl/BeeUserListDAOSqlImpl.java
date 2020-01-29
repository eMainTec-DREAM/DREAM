package intf.dream.bee.user.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.bee.user.dao.BeeUserListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeUserListDAOTarget"
 * @spring.txbn id="beeUserListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeUserListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeUserListDAO
{
	public List findList(Map map) throws Exception
    {
    	String userNo = String.valueOf(map.get("userNo"));
    	String localeId = String.valueOf(map.get("lang"));
    	String compNo = String.valueOf(map.get("compNo"));
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                     ");
        query.append("        a.comp_no compNo,                                  ");
        query.append("        a.user_id userId,                                  ");
        query.append("        a.user_no userNo,                                  ");
        query.append("        a.password,                                        ");
        query.append("        a.user_name userName,                              ");
        query.append("        a.usrgrp_id usrgrpId,                              ");
        query.append("        a.alarm_view_yn alarmViewYn,                       ");
        query.append("        (SELECT z.usrgrp_no                                ");
        query.append("         FROM   TAUSRGRP z                                 ");
        query.append("         WHERE  a.usrgrp_id = z.usrgrp_id) usrgrpName,     ");
        query.append("        a.init_menu_id initMenuId,                         ");
        query.append("        (SELECT                                            ");
        query.append("                y.file_name                                ");
        query.append("         FROM  TAMENU x, TAPAGE y                          ");
        query.append("         WHERE x.page_id = y.page_id                       ");
        query.append("          AND  x.menu_id = a.init_menu_id) fileName,       ");
        query.append("        '"+localeId+"' langId,                             ");
        query.append("        '"+localeId+"' lang,                               ");
        query.append("        dbo.SFACODE_TO_DESC('"+localeId+"','LANG','SYS','','"+localeId+"') langDesc, ");
        query.append("        b.dept_id deptId,                                  ");
        query.append("        dbo.SFAIDTODESC(b.dept_id,'', 'DEPT','"+compNo+"')  deptDesc, ");
        query.append("        a.filter_dept_id filterDeptId,                                  ");
        query.append("        dbo.SFAIDTODESC(a.filter_dept_id,'', 'DEPT','"+compNo+"')  filterDeptDesc, ");
        query.append("        b.emp_id   empId,                                  ");
        query.append("        b.emp_name empName,                                ");
        query.append("       (SELECT wcode_id FROM TADEPT                        ");
        query.append("        WHERE  comp_no = b.comp_no                         ");
        query.append("          AND  dept_id = b.dept_id) wcodeId,               ");
        query.append("       (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("        WHERE  comp_no  = b.comp_no                        ");
        query.append("          AND  wcode_id = (SELECT wcode_id FROM TADEPT     ");
        query.append("                           WHERE  comp_no = b.comp_no      ");
        query.append("                             AND  dept_id = b.dept_id)) wname,        ");
        query.append("       (SELECT twcode_id FROM TADEPT                       ");
        query.append("        WHERE  comp_no = b.comp_no                         ");
        query.append("          AND  dept_id = b.dept_id) twcodeId,              ");
        query.append("       (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("        WHERE  comp_no  = b.comp_no                        ");
        query.append("          AND  wcode_id = (SELECT twcode_id FROM TADEPT     ");
        query.append("                           WHERE  comp_no = b.comp_no      ");
        query.append("                             AND  dept_id = b.dept_id)) twname,       ");
        query.append("         a.secur_grade securGrade,                         ");
        query.append("         scrn_font_size scrnFontSize,                      ");
        query.append("         (SELECT b.ct_path           			             ");
        query.append("          FROM   TACOMP b                       			 ");
        query.append("          WHERE  b.comp_no = a.comp_no) ctPath,            ");
        query.append("			a.shift_type 					     shiftType,	 ");
        query.append("			a.change_pwd_date 					 changePwdDate,	 ");
        query.append("			dbo.SFACODE_TO_DESC(a.shift_type,'SHIFT_TYPE','SYS','','"+localeId+"') shiftTypeDesc,	");
        query.append("       b.wkctr_id	                             					wkCtrId,		");
        query.append("		 (SELECT description														");
        query.append("		  FROM TAWKCTR																");
        query.append("		  WHERE comp_no = b.comp_no													");
        query.append("		  AND wkctr_id = b.wkctr_id) 			 					wkCtrDesc,		");
        query.append("			a.eqloc_id eqLocId,								");
        query.append("			(SELECT full_desc								");
        query.append("			   FROM TAEQLOC x								");
        query.append("			WHERE x.eqloc_id = a.eqloc_id					");
        query.getAndQuery("x.comp_no", compNo);
        query.append("			) eqLocDesc,									");
        query.append("			b.plant plant,									");
        query.append("			dbo.SFAPLANTTODESC(b.comp_no, b.plant) plantDesc,	");
        query.append("			a.eqctg_type eqCtgTypeId,									");
        query.append("			dbo.SFACODE_TO_DESC(a.eqctg_type,'EQCTG_TYPE','SYS','','"+localeId+"') eqCtgTypeDesc ");
        query.append("          ,a.menu_display menuDisplay                      ");
        query.append("          ,a.filter_wkctr_id					filterWkctrId		");
        query.append("			,(SELECT description								");
        query.append("		  		FROM TAWKCTR									");
        query.append("		  		WHERE comp_no = a.comp_no						");
        query.append("		  		AND wkctr_id = a.filter_wkctr_id) 	filterWkctrDesc		");
        query.append("         ,a.filter_wcode_id					filterWcodeId		");
        query.append("         ,(SELECT wname FROM TAWAREHOUSE                      ");
        query.append("        WHERE  comp_no  = a.comp_no                        ");
        query.append("          AND  wcode_id = a.filter_wcode_id) filterWcodeDesc       ");;
        query.append("         ,a.filter_emp_id					filterEmpId		");
        query.append("         ,(SELECT emp_name FROM TAEMP                      ");
        query.append("        WHERE  comp_no  = a.comp_no                        ");
        query.append("          AND  emp_id = a.filter_emp_id) filterEmpDesc     ");
        query.append("         ,isnull((SELECT x.value$                                ");
        query.append("             FROM TACONFIG  x                                    ");
        query.append("            WHERE x.comp_no = a.comp_no                          ");
        query.append("              AND x.name  = 'BARCODE_DIVIDER'),'') barcodeDivider ");
        query.append("         ,isnull((SELECT x.value$                                ");
        query.append("             FROM TACONFIG  x                                    ");
        query.append("            WHERE x.comp_no = a.comp_no                          ");
        query.append("              AND x.name  = 'BARCODE_SEQ'),'') barcodeSeq        ");
        query.append("         ,isnull((SELECT x.value$                                ");
        query.append("             FROM TACONFIG  x                                    ");
        query.append("            WHERE x.comp_no = a.comp_no                          ");
        query.append("              AND x.name  = 'WORK_START_BASE_TIME'),'') WORKSTARTBASETIME        ");
        query.append("        ,a.filter_usage_dept_id filterUsageDeptId          ");
        query.append("        ,dbo.SFAIDTODESC(a.filter_usage_dept_id,'', 'DEPT','"+compNo+"')  filterUsageDeptDesc ");
        query.append("        ,a.filter_day_interval  filterDayInterval                              ");
        query.append("			,CASE WHEN a.filter_plant is null THEN b.plant ELSE a.filter_plant END  filterPlant										");
        query.append("			,dbo.SFAPLANTTODESC(b.comp_no, CASE WHEN a.filter_plant is null THEN b.plant ELSE a.filter_plant END) filterPlantDesc	");
        query.append("			,(SELECT (SELECT y.file_name FROM TAPAGE y WHERE y.page_id = x.page_id) FROM TAMENU x WHERE x.menu_id = a.bee_init_menu_id) beeInitMenuId		");
        query.append("         ,isnull((SELECT x.value$                                ");
        query.append("             FROM TACONFIG  x                                    ");
        query.append("            WHERE x.comp_no = a.comp_no                          ");
        query.append("              AND x.name  = 'GRID_MAX_LOAD_COUNT'),'0') GRIDMAXLOADCOUNT ");
        query.append("        ,b.emp_no EMPNO                                    ");
        query.append("        ,(SELECT x.dept_no								");
        query.append("          FROM TADEPT x									");
        query.append("          WHERE x.comp_no = b.comp_no						");
        query.append("          AND x.dept_id = b.dept_id) 	DEPTNO				");
        query.append("FROM    TAUSER a, TAEMP b                                  ");
        query.append("WHERE a.emp_id = b.emp_id                                  ");
        query.append("  AND   a.comp_no = b.comp_no                              ");
        query.append("  AND   a.comp_no = ?                                      ");
        query.append("  AND   UPPER(a.user_no) = UPPER(?)                        ");
        query.append("  AND   a.is_use != 'N'                                    ");
        query.append("  AND   b.is_join != 'N'                                   ");

        Object [] objects = new Object[]{
        		compNo
        		,userNo   
        };
        
        return getJdbcTemplate().queryForList(query.toString(), getObject(objects));
    } 
    
	public int updateFilter(Map map, String interval) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;
        
        query.append("UPDATE TAUSER SET                     ");
        query.append("          filter_dept_id = ?          ");
        query.append("          ,filter_wkctr_id = ?        ");
        query.append("          ,eqloc_id = ?               ");
        query.append("          ,eqctg_type = ?             ");
        query.append("          ,filter_wcode_id = ?        ");
        query.append("          ,filter_emp_id   = ?        ");
        query.append("          ,filter_usage_dept_id   = ? ");
        query.append("          ,filter_day_interval   = ?  ");
        query.append("          ,filter_plant   = ?  ");
        query.append("WHERE comp_no = ?                     ");
        query.append("  AND user_id = ?                     ");
        
        objects = new Object[] {
        		convertString(map.get("filterDeptId"))
        		,convertString(map.get("filterWkctrId"))
        		,convertString(map.get("eqLocId"))
        		,convertString(map.get("eqCtgType"))
        		,convertString(map.get("filterWcodeId"))
        		,convertString(map.get("filterEmpId"))
        		,convertString(map.get("filterUsageDeptId"))
        		,interval
        		,convertString(map.get("plant"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("userId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

}