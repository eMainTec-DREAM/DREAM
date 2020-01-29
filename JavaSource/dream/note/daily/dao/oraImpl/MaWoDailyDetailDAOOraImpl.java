package dream.note.daily.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.note.daily.dao.MaWoDailyDetailDAO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 *  - 상세 dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maWoDailyDetailDAOTarget"
 * @spring.txbn id="maWoDailyDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDailyDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoDailyDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param IfFailureCommonDTO
     * @param loginUser
     * @return
     */
    public MaWoDailyDetailDTO findDetail(MaWoDailyCommonDTO maWoDailyCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("        a.wodaylist_id                        woDayListId,        ");
        query.append("        a.wodaylist_id                        woDayListNo,        ");
        query.append("        a.wodaylist_status wodaylistStatus,                       ");
        query.append("        SFACODE_TO_DESC(a.wodaylist_status,'WODAYLIST_STATUS','SYS','','"+loginUser.getLangId()+"') wodaylistStatusDesc,   ");
        query.append("        a.description                         title,              ");
        query.append("        a.wo_date                             woDate,             ");
        query.append("        a.wo_dept                             woDeptId,           ");
        query.append("        (SELECT description                                       ");
        query.append("          FROM TADEPT                                             ");
        query.append("         WHERE comp_no = a.comp_no                                ");
        query.append("           AND dept_id = a.wo_dept)           woDeptDesc,         ");
        query.append("        a.start_fdate                         startFdate,         ");
        query.append("        a.start_ftime                         startFtime,         ");
        query.append("        a.start_edate                         startEdate,         ");
        query.append("        a.start_etime                         startEtime,         ");
        query.append("        a.upd_by                              updById,            ");
        query.append("        (SELECT x.emp_name||'/'||y.description                    ");
        query.append("         FROM TAEMP x INNER JOIN TADEPT y                         ");
        query.append("         ON x.comp_no = y.comp_no                                 ");
        query.append("         AND x.dept_id = y.dept_id                                ");
        query.append("         WHERE x.comp_no = a.comp_no                              ");
        query.append("           AND x.emp_id = a.upd_by)           updByDesc,          ");
        query.append("        a.plant                               plant,              ");
        query.append("        (SELECT description                                       ");
        query.append("         FROM TAPLANT                                             ");
        query.append("         WHERE comp_no = a.comp_no                                ");
        query.append("           AND plant = a.plant)               plantDesc,          ");
        query.append("        a.remark                              remark              ");
        query.append("      , a.equip_id 							equipId				");
        query.append("      , (SELECT b.description 									");
        query.append("           FROM TAEQUIPMENT b 									");
        query.append("          WHERE b.comp_no = a.comp_no 							");
        query.append("            AND b.equip_id = a.equip_id) 		equipDesc			");
        query.append("		, a.wkctr_id							wkCtrId				");
        query.append("    	, (SELECT b.description 									");
        query.append("           FROM TAWKCTR b 										");
        query.append("          WHERE b.comp_no = a.comp_no 							");
        query.append("            AND b.wkctr_id = a.wkctr_id)    	wkCtrDesc			");
        query.append("FROM   TAWODAYLIST a                                              ");
        query.append("WHERE 1 = 1                                                       ");
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.wodaylist_id", maWoDailyCommonDTO.getWoDayListId());
        
        MaWoDailyDetailDTO resultDTO = 
        		(MaWoDailyDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoDailyDetailDTO()));
        
        return resultDTO;
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param IfFailureDetailDTO
     * @return
     */
    public int updateDetail(MaWoDailyDetailDTO maWoDailyDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAWODAYLIST SET                          ");
        query.append("       description             = ?,             ");
        query.append("       wo_date                 = ?,             ");
        query.append("       wo_dept                 = ?,             ");
        query.append("       start_fdate             = ?,             ");
        query.append("       start_ftime             = ?,             ");
        query.append("       start_edate             = ?,             ");
        query.append("       start_etime             = ?,             ");
        query.append("       remark                  = ?              ");
        query.append("      ,wkctr_id                = ?              ");
        query.append("      ,equip_id                = ?              ");
        query.append("WHERE  wodaylist_id            = ?              ");
        query.append("AND  comp_no                   = ?              ");
        
        Object[] objects = new Object[] {
                maWoDailyDetailDTO.getTitle(),
                maWoDailyDetailDTO.getWoDate(),
                maWoDailyDetailDTO.getWoDeptId(),
                maWoDailyDetailDTO.getStartFdate(),
                maWoDailyDetailDTO.getStartFtime(),
                maWoDailyDetailDTO.getStartEdate(),
                maWoDailyDetailDTO.getStartEtime(),
                maWoDailyDetailDTO.getRemark(),
                maWoDailyDetailDTO.getWkCtrId(),
                maWoDailyDetailDTO.getEquipId(),
                maWoDailyDetailDTO.getWoDayListId(),
                user.getCompNo()
        };
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int insertDetail(MaWoDailyDetailDTO maWoDailyDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO TAWODAYLIST                            ");
        query.append("  (comp_no,      wodaylist_id,    wodaylist_status,");
        query.append("   wo_dept,      upd_by,          upd_date,        ");
        query.append("   remark,       wo_date,         plant,           ");
        query.append("   description,  start_fdate,     start_ftime,     ");
        query.append("   start_edate,  start_etime,     wkctr_id         ");
        query.append("  ,equip_id										 ");
        query.append("  )   VALUES                                       ");
        query.append("  (?,             ?,              ?,               ");
        query.append("   ?,             ?,              ?,               ");
        query.append("   ?,             ?,              ?,               ");
        query.append("   ?,             ?,              ?,               ");
        query.append("   ?,             ?,              ?                ");
        query.append("	,?												 ");
        query.append("  )                                                ");
        
        Object[] objects = new Object[] {
                loginUser.getCompNo(),
                maWoDailyDetailDTO.getWoDayListId(),
                maWoDailyDetailDTO.getWoDayListStatus(),
                maWoDailyDetailDTO.getWoDeptId(),
                maWoDailyDetailDTO.getUpdById(),
                DateUtil.getDate(),
                maWoDailyDetailDTO.getRemark(),
                maWoDailyDetailDTO.getWoDate(),
                maWoDailyDetailDTO.getPlant(),
                maWoDailyDetailDTO.getTitle(),
                maWoDailyDetailDTO.getStartFdate(),
                maWoDailyDetailDTO.getStartFtime(),
                maWoDailyDetailDTO.getStartEdate(),
                maWoDailyDetailDTO.getStartEtime(),
                maWoDailyDetailDTO.getWkCtrId()
                ,maWoDailyDetailDTO.getEquipId()
        };
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public int insertBmActivities(MaWoDailyDetailDTO maWoDailyDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
//        query.append("INSERT INTO TAWODAYACT				");
//    	query.append("	(comp_no,		wodaylist_id,		");
//    	query.append("	 wodayact_id,	emp_id,				");
//    	query.append("	 eq_name,		act_contents,		");
//    	query.append("	 action,		ord_no				");
//    	query.append("	)	 								");
//    	query.append("SELECT ?,			?,			");
//    	query.append("		SQAWODAYACT_ID.nextval,x.emp_id,");
//    	query.append("		(SELECT TO_CHAR(WM_CONCAT(DECODE(b.eqctg_type,'MD','('||b.old_eq_no||')'||b.description,b.description)))								");
//        query.append("			FROM TAWOEQUIP a, TAEQUIPMENT b										");
//        query.append("			WHERE a.comp_no = b.comp_no											");
//        query.append("				AND a.equip_id = b.equip_id										");
//        query.append("				AND a.wkor_id = x.wkor_id										");
//        query.append("				AND a.comp_no = x.comp_no										");
//        query.append("			GROUP By a.comp_no, a.wkor_id	),	");
//        query.append("		x.description,					");
//        query.append("		y.re_desc,						");
//        query.append("		'10'							");
//        query.append("FROM TAWORKORDER x, TAWOFAIL y		");
//        query.append("WHERE x.comp_no = y.comp_no			");
//        query.append("AND   x.wkor_id = y.wkor_id			");
//        query.getStringEqualQuery("x.wo_status","C");
//        query.getStringEqualQuery("x.comp_no",loginUser.getCompNo());
//        query.getStringEqualQuery("x.wo_status","C");
//        query.getStringEqualQuery("x.wo_type","BM");
//        query.getStringEqualQuery("x.wkor_date",maWoDailyDetailDTO.getWoDate());
//        query.getAndNumKeyQuery("x.dept_id", maWoDailyDetailDTO.getWoDeptId());
        
        query.append("INSERT INTO TAWODAYACT                			");
        query.append("    (comp_no,        wodaylist_id,        		");
        query.append("     wodayact_id,    emp_id,                		");
        query.append("     eq_name,        act_contents,        		");
        query.append("     action,        ord_no                		");
        query.append("    )                                     		");
        query.append("SELECT  ?,            ?,		            		");
        query.append("        SQAWODAYACT_ID.NEXTVAL, x.emp_id,			");
        query.append("        TO_CHAR(DECODE(b.eqctg_type,'MD','('||b.old_eq_no||')'||b.description,b.description)),    		");
        query.append("        x.description,                    		");
        query.append("        y.re_desc,                        		");
        query.append("        '10'                            			");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOPARTS yy        ");
        query.append("ON x.comp_no = yy.comp_no         				");
        query.append(" AND x.wkor_id = yy.wkor_id   					");
        query.append("	LEFT OUTER JOIN TAWOFAIL y						");
        query.append("	ON  x.wkor_id = y.wkor_id 						");
        query.append("	 AND x.comp_no = y.comp_no       				");
        query.append("		INNER JOIN TAWOEQUIP a                      ");
        query.append("		 ON x.comp_no = a.comp_no                   ");
        query.append("	 	 AND x.wkor_id = a.wkor_id 					");
        query.append("			INNER JOIN TAEQUIPMENT b                ");
        query.append("      	ON a.comp_no = b.comp_no                ");
        query.append("      	 AND a.equip_id = b.equip_id			");
        Object[] objects = new Object[] {
                loginUser.getCompNo(),
                maWoDailyDetailDTO.getWoDayListId(),
        };
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }

    public String checkList(MaWoDailyDetailDTO maWoDailyDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                        ");
        query.append("       wodaylist_id              ");
        query.append("FROM   TAWODAYLIST a                                            ");
        query.append("WHERE  1 = 1                  ");
        query.getAndQuery("a.wo_date", maWoDailyDetailDTO.getWoDate());
        query.getAndQuery("a.wo_dept", maWoDailyDetailDTO.getWoDeptId());

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAWODAYLIST SET                    ");
        query.append("       wodaylist_status   = ?             ");
        query.append("WHERE  wodaylist_id       = ?             ");
        query.append("AND  comp_no       = ?             ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public List findReportWoDetail(MaWoDailyDetailDTO maWoDailyDetailDTO) {

		QueryBuffer query = new QueryBuffer();
		String lang = maWoDailyDetailDTO.getUserLang();
		
        query.append("SELECT										");
        query.append("		TO_CHAR (TO_DATE(x.wo_date,'yyyymmdd'),'yyyy-mm-dd')||' ('||						");
        query.append("(SELECT key_name FROM TALANG WHERE 1=1	");
        query.getAndQuery("lang", lang);
        query.append("AND key_no = 'week'||(SELECT dow FROM TADAY where tday = x.wo_date)");
        query.append(")||')' woToday,								");
        query.append("		TO_CHAR(TO_DATE(x.wo_date,'yyyymmdd'),'dd . mm . yyyy') woToday2,		");
        query.append("		(SELECT description						");
        query.append("			FROM TADEPT							");
        query.append("			WHERE comp_no = x.comp_no			");
        query.append("			AND dept_id = x.wo_dept) deptDesc,	");
        query.append("		TO_CHAR(sysdate,'yyyy-mm-dd HH24:mi') TODAY,																		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woDailyList' AND key_type='LABEL') woDailyList,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipName' AND key_type='LABEL') equipName,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqLocName' AND key_type='LABEL') eqLocName,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='manager' AND key_type='LABEL') manager,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woRemark' AND key_type='LABEL') woRemark,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='workTime' AND key_type='LABEL') workTime,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='insertParts' AND key_type='LABEL') insertParts		");
        query.append("FROM TAWODAYLIST x														");
        query.append("WHERE 1=1																	");
        query.getAndQuery("x.comp_no", maWoDailyDetailDTO.getCompNo());
        query.getAndQuery("x.wodaylist_id", maWoDailyDetailDTO.getWoDayListId());

		return getJdbcTemplate().queryForList(query.toString());
	}
    public List findReportWorkList(MaWoDailyDetailDTO maWoDailyDetailDTO) {

		QueryBuffer query = new QueryBuffer();

		query.append("SELECT																																");
		query.append("		CASE WHEN (select count(1) from tawoequip where comp_no = x.comp_no AND wkor_id = y.wkor_id)<=1									");
		query.append("			THEN (select (SELECT description FROM TAEQUIPMENT a where a.comp_no = b.comp_no AND a.equip_id = b.equip_id) 				");
		query.append("				FROM tawoequip b where comp_no = x.comp_no AND wkor_id = y.wkor_id)														");
		query.append("			ELSE (select (SELECT description FROM TAEQUIPMENT a where a.comp_no = b.comp_no AND a.equip_id = b.equip_id)				");
		query.append("					FROM tawoequip b where comp_no = x.comp_no AND wkor_id = y.wkor_id AND rownum=1)|| ' 외 '||							");
		query.append("				(select count(1)-1 from tawoequip where comp_no = x.comp_no AND wkor_id = y.wkor_id)|| ' 건 '								");
		query.append(" 		END equipDesc,																													");
		query.append("		(SELECT full_desc FROM TAEQLOC 																									");
		query.append("			WHERE comp_no = x.comp_no																									");
		query.append("			AND eqloc_id = y.eqloc_id	)	eqLocDesc,																					");
        query.append("		y.perform woDesc,																												");
		query.append("		TO_CHAR(TO_DATE(y.start_date,'yyyymmdd'),'yyyy-mm-dd')||'  '||TO_CHAR(TO_DATE(y.start_time,'hh24mi'),'hh24:mi')|| ' - ' ||		");
		query.append("			TO_CHAR(TO_DATE(y.end_date,'yyyymmdd'),'yyyy-mm-dd')||'  '||TO_CHAR(TO_DATE(y.end_time,'hh24mi'),'hh24:mi')					");
		query.append("		 	woTime,																														");
		query.append("		(select emp_name FROM TAEMP WHERE 1=1 AND comp_no = x.comp_no AND emp_id = y.emp_id)											");
		query.append("		 empName,																														");
		query.append("		case when (select count(1) from tawoparts where comp_no=y.comp_no AND wkor_id=y.wkor_id)<=1										");
		query.append("			then (select (SELECT description FROM TAPARTS a where a.comp_no = b.comp_no AND a.part_id = b.part_id)						");
		query.append("				FROM tawoparts b where comp_no = x.comp_no AND wkor_id = y.wkor_id)														");
		query.append("			ELSE (select (SELECT description FROM TAPARTS a where a.comp_no = b.comp_no AND a.part_id = b.part_id)						");
		query.append("				FROM tawoparts b where comp_no = x.comp_no AND wkor_id = y.wkor_id AND rownum=1)|| ' 외 ' ||								");
		query.append("				(select count(1)-1 from tawoparts where comp_no = x.comp_no AND wkor_id = y.wkor_id)|| ' 건 '								");
		query.append("		END  woPartDesc																													");
        query.append("FROM TAWODAYLIST x, TAWORKORDER y																										");
        query.append("WHERE 1=1																																");
        query.append("AND x.comp_no = y.comp_no																												");
        query.append("AND x.wo_date = y.wkor_date																											");
        query.append("AND x.wo_dept = y.dept_id																												");
        query.append("and y.wo_type <> 'PMI' 													                                                            ");
        query.append("and y.pm_type <> 'INS' 													                                                           ");
        query.getAndQuery("x.comp_no", maWoDailyDetailDTO.getCompNo());
        query.getAndQuery("x.wodaylist_id", maWoDailyDetailDTO.getWoDayListId());
        query.append("ORDER BY y.wo_no 																														");
		return getJdbcTemplate().queryForList(query.toString());
	}
    public List findReportSLPWorkList1(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) {

		QueryBuffer query = new QueryBuffer();

        int listSize = getListSize(maWoDailyDetailDTO, user).size();

		query.append("SELECT TO_CHAR(rownum) no																												");
		query.append("		,aa.equipDesc																													");
		query.append("		,aa.trouble																														");
		query.append("		,aa.lineStop																													");
		query.append("		,aa.repair																														");
		query.append("		,aa.roa																															");
		query.append("		,aa.report																														");
		query.append("FROM																																	");
		query.append("(																																		");
		query.append("SELECT																																");
		query.append("		CASE WHEN (select count(1) from tawoequip where comp_no = x.comp_no AND wkor_id = y.wkor_id)<=1									");
		query.append("			THEN (select (SELECT description FROM TAEQUIPMENT a where a.comp_no = b.comp_no AND a.equip_id = b.equip_id) 				");
		query.append("				FROM tawoequip b where comp_no = x.comp_no AND wkor_id = y.wkor_id)														");
		query.append("			ELSE (select (SELECT description FROM TAEQUIPMENT a where a.comp_no = b.comp_no AND a.equip_id = b.equip_id)				");
		query.append("					FROM tawoequip b where comp_no = x.comp_no AND wkor_id = y.wkor_id AND rownum=1)|| ' And '||						");
		query.append("				(select count(1)-1 from tawoequip where comp_no = x.comp_no AND wkor_id = y.wkor_id)|| ' Cnt '							");
		query.append(" 		END equipDesc																													");
		query.append(" 		,NVL(z.ca_desc,'-') trouble																										");
		query.append(" 		,DECODE(z.lndn_work_time,null,'-',z.lndn_work_time||' MIN') lineStop															");
		query.append(" 		,DECODE(z.eqdn_work_time,null,'-',z.eqdn_work_time||' MIN') repair																");
		query.append(" 		,NVL(z.re_desc,'-') roa																											");
		query.append(" 		,(SELECT emp_name FROM TAEMP WHERE emp_id = y.emp_id) report																	");
        query.append("FROM TAWODAYLIST x, TAWORKORDER y, TAWOFAIL z																							");
        query.append("WHERE 1=1																																");
        query.append("AND x.comp_no = y.comp_no																												");
        query.append("AND y.comp_no = z.comp_no																												");
        query.append("AND x.wo_date = y.wkor_date																											");
        query.append("AND x.wo_dept = y.dept_id																												");
        query.append("AND y.wkor_id = z.wkor_id																												");
        query.append("and y.wo_type <> 'PMI' 													                                                            ");
        query.append("and y.pm_type <> 'INS' 													                                                            ");
        query.append("AND z.lndn_work_time is not null											                                                            ");
        query.getAndQuery("x.comp_no", maWoDailyDetailDTO.getCompNo());
        query.getAndQuery("x.wodaylist_id", maWoDailyDetailDTO.getWoDayListId());

        for (int i = listSize; i < 5; i++) {
        	query.append("UNION ALL	SELECT '','','','','','' FROM DUAL		");
		}
        
        query.append(") aa");
		return getJdbcTemplate().queryForList(query.toString());
    }
    public List getListSize(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) {

		QueryBuffer query = new QueryBuffer();

		query.append("SELECT																																");
		query.append("		TO_CHAR(rownum) no																												");
		query.append("		,CASE WHEN (select count(1) from tawoequip where comp_no = x.comp_no AND wkor_id = y.wkor_id)<=1								");
		query.append("			THEN (select (SELECT description FROM TAEQUIPMENT a where a.comp_no = b.comp_no AND a.equip_id = b.equip_id) 				");
		query.append("				FROM tawoequip b where comp_no = x.comp_no AND wkor_id = y.wkor_id)														");
		query.append("			ELSE (select (SELECT description FROM TAEQUIPMENT a where a.comp_no = b.comp_no AND a.equip_id = b.equip_id)				");
		query.append("					FROM tawoequip b where comp_no = x.comp_no AND wkor_id = y.wkor_id AND rownum=1)|| ' And '||						");
		query.append("				(select count(1)-1 from tawoequip where comp_no = x.comp_no AND wkor_id = y.wkor_id)|| ' Cnt '							");
		query.append(" 		END equipDesc																													");
		query.append(" 		,NVL(z.ca_desc,'-') trouble																										");
		query.append(" 		,DECODE(z.lndn_work_time,null,'-',z.lndn_work_time||' MIN') lineStop															");
		query.append(" 		,DECODE(z.eqdn_work_time,null,'-',z.eqdn_work_time||' MIN') repair																");
		query.append(" 		,NVL(z.re_desc,'-') roa																											");
		query.append(" 		,(SELECT emp_name FROM TAEMP WHERE emp_id = y.emp_id) report																	");
        query.append("FROM TAWODAYLIST x, TAWORKORDER y, TAWOFAIL z																							");
        query.append("WHERE 1=1																																");
        query.append("AND x.comp_no = y.comp_no																												");
        query.append("AND y.comp_no = z.comp_no																												");
        query.append("AND x.wo_date = y.wkor_date																											");
        query.append("AND x.wo_dept = y.dept_id																												");
        query.append("AND y.wkor_id = z.wkor_id																												");
        query.append("AND y.wo_type <> 'PMI' 													                                                            ");
        query.append("AND y.pm_type <> 'INS' 													                                                            ");
        query.append("AND z.lndn_work_time is not null											                                                            ");
        query.getAndQuery("x.comp_no", maWoDailyDetailDTO.getCompNo());
        query.getAndQuery("x.wodaylist_id", maWoDailyDetailDTO.getWoDayListId());
        
		return getJdbcTemplate().queryForList(query.toString());
    }

    public List findReportSLPWorkList2(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) {

		QueryBuffer query = new QueryBuffer();

		query.append("SELECT																");
		query.append("		rownum no														");
		query.append("		,x.eq_name equipDesc											");
		query.append(" 		,x.act_contents contents										");
		query.append(" 		,x.action action												");
		query.append(" 		,(SELECT emp_name FROM TAEMP WHERE emp_id = x.emp_id) report	");
        query.append("FROM TAWODAYACT x														");
        query.append("WHERE 1=1																");
        query.getAndQuery("x.comp_no", maWoDailyDetailDTO.getCompNo());
        query.getAndQuery("x.wodaylist_id", maWoDailyDetailDTO.getWoDayListId());
        query.append("ORDER BY x.ord_no  													");
        
        return getJdbcTemplate().queryForList(query.toString());
	}

	public int updateStatus(MaWoDailyDetailDTO maWoDailyDetailDTO, User loginUser) 
	{
		QueryBuffer query = new QueryBuffer();

		query.append("UPDATE TAWODAYLIST SET                    ");
        query.append("       wodaylist_status   = ?             ");
        query.append("WHERE  wodaylist_id       = ?             ");
        query.append("  AND  comp_no       		= ?             ");
        
        Object[] objects = new Object[] {
        		"C"
        		,maWoDailyDetailDTO.getWoDayListId()
        		,loginUser.getCompNo()
        };

        return this.getJdbcTemplate().update(query.toString(), objects);
	}
}