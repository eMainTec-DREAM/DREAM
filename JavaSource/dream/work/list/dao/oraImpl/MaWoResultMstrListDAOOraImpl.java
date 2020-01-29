package dream.work.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.MaWoResultMstrListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;

/**
 * 작업결과 - 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultMstrListDAOTarget"
 * @spring.txbn id="maWoResultMstrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultMstrListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoResultMstrListDAO
{
	
	private String publicColumnQueryForFindWoList(User user)
    {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT																				");
        query.append("		''															AS seqNo			");
        query.append("		,''															AS isDelCheck		");
        query.append("		,x.wkor_id													AS id				");
        query.append("		,x.wkor_id													AS wkOrId			");
        query.append("		,x.wo_no 													AS woNo				");
        query.append("		,x.description 												AS wkOrDesc			");
        query.append("		,x.wkor_date 												AS startDate		");
        query.append("		,x.start_date||x.start_time 								AS startDatetime	");
        query.append("		,x.end_date||x.end_time 									AS endDatetime		");
        query.append("		,(SELECT aa.full_desc															");
        query.append("			FROM TAEQLOC aa																");
        query.append("			WHERE aa.comp_no = y.comp_no												");
        query.append("			AND aa.eqloc_id = y.eqloc_id )							AS eqLocDesc		");
        query.append("		,y.description												AS equipDesc		");
        query.append("		,y.item_no													AS equipNo			");
        query.append("		,(SELECT aa.description															");
        query.append("			FROM TADEPT aa																");
        query.append("			WHERE aa.comp_no = x.comp_no												");
        query.append("			AND aa.dept_id = x.dept_id )							AS deptDesc			");
        query.append("		,(SELECT aa.description															");
        query.append("			FROM TAWKCTR aa																");
        query.append("			WHERE aa.comp_no = x.comp_no												");
        query.append("			AND aa.wkctr_id = x.wkctr_id )							AS wkCtrDesc		");
        query.append("		,(SELECT SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL) 	AS shiftDesc	");
        query.append("		,(SELECT SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL) 			AS woTypeDesc	");
        query.append("		,(SELECT SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL) 	AS pmTypeDesc	");
        query.append("		,x.work_time 												AS workTime			");
        query.append("		,to_char(to_date(x.start_time,'hh24mi'),'hh24:mi')			AS startTime		");
        query.append("		,to_char(to_date(x.end_time,'hh24mi'),'hh24:mi')			AS endTime			");
        query.append("		,(SELECT a.lndn_start_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no			");
        query.append("			AND a.wkor_id = x.wkor_id) 								AS prodStartTime	");
        query.append("		,(SELECT a.lndn_end_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no			");
        query.append("			AND a.wkor_id = x.wkor_id) 								AS prodEndTime		");
        query.append("		,(SELECT a.lndn_work_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no			");
        query.append("			AND a.wkor_id = x.wkor_id) 								AS lndnTime			");
        query.append("		,(SELECT SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"') FROM DUAL) 		AS woStatusDesc	");
        query.append("		,x.wo_status												AS woStatus			");
        query.append("		,(SELECT aa.emp_name															");
        query.append("		   FROM TAEMP aa																");
        query.append("		  WHERE aa.comp_no = x.comp_no													");
        query.append("			AND aa.emp_id = x.emp_id) 								AS empDesc			");
        query.append("		,(SELECT aa.emp_name															");
        query.append("		   FROM TAEMP aa																");
        query.append("		  WHERE aa.comp_no = y.comp_no													");
        query.append("			AND aa.emp_id  = y.sub_mng_id) 							AS subMng			");
        query.append("		,(SELECT aa.description															");
        query.append("			FROM TAEQCTG aa																");
        query.append("			WHERE aa.comp_no = y.comp_no												");
        query.append("			AND aa.eqctg_id = y.eqctg_id )							AS eqCtgDesc		");
        query.append("		,(SELECT SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL) selfVendorTypeDesc ");
        query.append("		,(SELECT SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no) FROM DUAL)			AS vendorDesc		"); 
        query.append("		,x.vendor_desc												AS vendorName		");
        query.append("		,x.perform 													AS perform			");
        query.append("		,x.wo_type													AS woType			"); 
        query.append("		,x.pm_type													AS pmType			"); 
        query.append("		,x.wo_gen_type												AS woGenType		"); 
        query.append("		,x.labor_amt												AS amt				"); 
        query.append("		,(SELECT SFACODE_TO_DESC(x.wo_gen_type,'WO_GEN_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL)	AS woGenTypeDesc 		");
        query.append("		,(select param1 FROM tacdsysd where list_Type= x.wo_type||'_TYPE' AND cdsysd_no=x.pm_type) AS param 	");
        query.append("		,x.upd_date													AS updDate			");
		
		return query.toString();
    }
	
	private String publicFromQueryForFindWoList(User user)
    {
		QueryBuffer query = new QueryBuffer();
		
		query.append("FROM TAWORKORDER x INNER JOIN 														");
        query.append("						(SELECT a.equip_id												");
        query.append("								,a.wkor_id												");
        query.append("								,b.item_no												");
        query.append("								,a.comp_no												");
        query.append("								,b.sub_mng_id											");
        query.append("								,b.eqctg_id												");
        query.append("								,b.old_eq_no											");
        query.append("								,b.description											");
        query.append("								,b.eq_grade												");
        query.append("								,b.eqctg_type											");
        query.append("								,b.eqloc_id												");
        query.append("								,b.is_last_version										");
        query.append("								,b.is_law_eq											");
        query.append("								,b.plf_type												");
        query.append("								,b.main_mng_id											");
        query.append("								,b.p_equip_id											");
        query.append("								,b.usage_dept											");
        query.append("							FROM (SELECT we.comp_no										");
        query.append("										,we.wkor_id										");
        query.append("										,MAX(we.equip_id) as equip_id					");
        query.append("									FROM TAWOEQUIP we									");
        query.append("									WHERE 1=1											");
        query.getStringEqualQuery("we.comp_no", user.getCompNo());
        query.append("									GROUP BY we.comp_no, we.wkor_id ) a					");
        query.append("									INNER JOIN TAEQUIPMENT b							");
        query.append("									ON a.comp_no = b.comp_no							");
        query.append("									AND a.equip_id = b.equip_id							");
        query.append("									WHERE 1=1											");
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        query.append("									) y													");
        query.append("ON x.comp_no = y.comp_no																");
        query.append("AND x.wkor_id = y.wkor_id																");
        query.append("WHERE 1=1																				");
        
		return query.toString();
    }
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        //공통 SELECT 쿼리
        query.append(this.publicColumnQueryForFindWoList(user));
        
        // 메서드 전용 쿼리 추가
        query.append("		,(SELECT full_desc FROM TAEQASMB WHERE comp_no = x.comp_no AND eqasmb_id = x.eqasmb_id) AS eqAsmbDesc	");
        query.append("		,(SELECT																		");
        query.append("				(SELECT b.key_name														");
        query.append("					FROM talang b														");
        query.append("					WHERE  b.lang = '"+user.getLangId()+"'								");
        query.append("					AND aa.key_type = b.key_type										");
        query.append("					AND aa.key_no = b.key_no) description								");
        query.append("		FROM TAFAILURE aa WHERE aa.comp_no = x.comp_no AND aa.fail_type = 'CA' AND aa.failure_id = (SELECT y.ca_cd FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id)) AS caCd	");
        query.append("		,(SELECT y.ca_desc FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id) AS caDesc			");
        query.append("		,(SELECT																		");
        query.append("				(SELECT b.key_name														");
        query.append("					FROM talang b														");
        query.append("					WHERE  b.lang = '"+user.getLangId()+"'								");
        query.append("					AND aa.key_type = b.key_type										");
        query.append("					AND aa.key_no = b.key_no) description								");
        query.append("			FROM TAFAILURE aa WHERE aa.comp_no = x.comp_no AND aa.fail_type = 'RE' AND aa.failure_id = (SELECT y.re_cd FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id)) AS reCd	");
        query.append("		,(SELECT y.re_desc FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id) AS reDesc     	");
        query.append("		,(SELECT 																		");
        query.append("				( SELECT																");
        query.append("						x1.description													");
        query.append("					FROM  TACDUSRD x1  , TACDUSRM  y1 									");
        query.append("					WHERE x1.cdusrm_id = y1.cdusrm_id  									");
        query.append("  				AND  x1.comp_no = x.comp_no											");
        query.append("  				AND  y1.dir_type = 'CALIB_CORP'										");
        query.append("  				AND x1.cdusrd_no = y.calib_corp) FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no) AS calibCorp	");
        query.append("		,(SELECT y.calib_env FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no) AS calibEnv		");
        query.append("		,(SELECT SFACODE_TO_DESC((SELECT y.calib_result_status FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no),'CALIB_RSLT_STATUS','SYS','','"+user.getLangId()+"') FROM DUAL) AS calibResult	");
        query.append("		,(SELECT SFACODE_TO_DESC((SELECT y.calib_type FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no),'CALIB_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL)  AS calibType	");
        query.append("		,(SELECT b.pm_no																");
        query.append("			FROM TAPMSCHED a INNER JOIN TAPMLST b										");
        query.append("			ON a.comp_no=b.comp_no AND a.pm_id=b.pm_id									");
        query.append("			WHERE a.comp_no=x.comp_no AND a.wkor_id=x.wkor_id) AS pmNo					");
        query.append("    , x.is_draft													AS isDraft			");
        
        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        //메서드 전용 WHERE 절 쿼리
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
        
        query.getOrderByQuery("x.wkor_id DESC", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }
    
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoBmResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        //공통 SELECT 쿼리
        query.append(this.publicColumnQueryForFindWoList(user));
        
        query.append("		,(SELECT full_desc FROM TAEQASMB WHERE comp_no = x.comp_no AND eqasmb_id = x.eqasmb_id) AS eqAsmbDesc	");
        query.append("		,(SELECT																		");
        query.append("				(SELECT b.key_name														");
        query.append("					FROM talang b														");
        query.append("					WHERE  b.lang = '"+user.getLangId()+"'								");
        query.append("					AND aa.key_type = b.key_type										");
        query.append("					AND aa.key_no = b.key_no) description								");
        query.append("		FROM TAFAILURE aa WHERE aa.comp_no = x.comp_no AND aa.fail_type = 'CA' AND aa.failure_id = (SELECT y.ca_cd FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id)) AS caCd	");
        query.append("		,(SELECT y.ca_desc FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id) AS caDesc			");
        query.append("		,(SELECT																		");
        query.append("				(SELECT b.key_name														");
        query.append("					FROM talang b														");
        query.append("					WHERE  b.lang = '"+user.getLangId()+"'								");
        query.append("					AND aa.key_type = b.key_type										");
        query.append("					AND aa.key_no = b.key_no) description								");
        query.append("			FROM TAFAILURE aa WHERE aa.comp_no = x.comp_no AND aa.fail_type = 'RE' AND aa.failure_id = (SELECT y.re_cd FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id)) AS reCd	");
        query.append("		,(SELECT y.re_desc FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id) AS reDesc     	");
        
        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        //메서드 전용 WHERE 절 쿼리
        query.append("  AND  x.wo_type='BM'																	");
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
        query.getOrderByQuery("x.wkor_id DESC", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoCmResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        //공통 SELECT 쿼리
        query.append(this.publicColumnQueryForFindWoList(user));
        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        //메서드 전용 쿼리
        query.append("  AND  x.wo_type='CM'																	");
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
        query.getOrderByQuery("x.wkor_id DESC", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }

    public List findWoTiResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        //공통 SELECT 쿼리
        query.append(this.publicColumnQueryForFindWoList(user));
        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        //메서드 전용 쿼리
        query.append("  AND  x.wo_type='TI'															");
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
        
        query.getOrderByQuery("x.wkor_id DESC", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoPmwResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        //공통 SELECT 쿼리
        query.append(this.publicColumnQueryForFindWoList(user));
        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        //메서드 전용 쿼리
        query.append("  AND  x.wo_type='PMW'															");
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
        
        query.getOrderByQuery("x.wkor_id DESC", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoPmcResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        //공통 SELECT 쿼리
        query.append(this.publicColumnQueryForFindWoList(user));
        
        query.append("		,(SELECT aa.description															");
        query.append("			FROM TADEPT aa																");
        query.append("			WHERE aa.comp_no = y.comp_no												");
        query.append("			AND aa.dept_id = y.usage_dept )							AS usageDeptDesc	");
        query.append("		,(SELECT aa.eqloc_no															");
        query.append("			FROM TAEQLOC aa																");
        query.append("			WHERE aa.comp_no = y.comp_no												");
        query.append("			AND aa.eqloc_id = y.eqloc_id )							AS eqLocNo			");
        query.append("        ,  (SELECT a.description                                                		");
        query.append("              FROM TAPLANT a                                                    		");
        query.append("             WHERE a.comp_no = x.comp_no                                        		");
        query.append("               AND a.plant   = x.plant )                          AS plantDesc 		");
        query.append("        , (SELECT                                                                     ");
        query.getDate("                 aa.next_calib_date", "");
        query.append("             FROM TAWOCALIB aa                                                        ");
        query.append("            WHERE aa.comp_no = x.comp_no                                              ");
        query.append("              AND aa.wkor_id = x.wkor_id)                         AS nextCalibDate   	");
        query.append("        ,  (SELECT a.item_no                                                			");
        query.append("              FROM TAEQUIPMENT a                                                    	");
        query.append("             WHERE a.comp_no = y.comp_no                                        		");
        query.append("               AND a.equip_id   = y.p_equip_id )                  AS pequipNo 		");
        query.append("        ,  (SELECT a.description                                                		");
        query.append("              FROM TAEQUIPMENT a                                                    	");
        query.append("             WHERE a.comp_no = y.comp_no                                        		");
        query.append("               AND a.equip_id   = y.p_equip_id )                  AS pequipDesc 		");
        query.append("        ,  (SELECT (SELECT c.description FROM TADEPT c WHERE c.comp_no = a.comp_no AND c.dept_id = a.usage_dept)	");
        query.append("              FROM TAEQUIPMENT a                                                    	");
        query.append("             WHERE a.comp_no = y.comp_no                                        		");
        query.append("               AND a.equip_id   = y.p_equip_id )                 AS pequipUsaDeptDesc ");
        query.append("		,(SELECT 																		");
        query.append("				( SELECT																");
        query.append("						x1.description													");
        query.append("					FROM  TACDUSRD x1  , TACDUSRM  y1 									");
        query.append("					WHERE x1.cdusrm_id = y1.cdusrm_id  									");
        query.append("  				AND  x1.comp_no = x.comp_no											");
        query.append("  				AND  y1.dir_type = 'CALIB_CORP'										");
        query.append("  				AND x1.cdusrd_no = y.calib_corp) FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no) AS calibCorp1	");
        query.append("		,(SELECT y.calib_env FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no) AS calibEnv		");
        query.append("		,(SELECT SFACODE_TO_DESC((SELECT y.calib_result_status FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no),'CALIB_RSLT_STATUS','SYS','','"+user.getLangId()+"') FROM DUAL) AS calibResult	");
        query.append("		,(SELECT SFACODE_TO_DESC((SELECT y.calib_type FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no),'CALIB_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL)  AS calibType	");
        query.append(" 		,(SELECT SFACODE_TO_DESC(a.calib_corp,'CALIB_CORP','USR','"+user.getCompNo()+"','"+user.getLangId()+"') FROM TAWOCALIB a WHERE a.wkor_id = x.wkor_id)      					AS CALIBCORP	");
        query.append("  	,(SELECT                                                                                                     ");
        query.append("            aa.cycle||(SELECT SFACODE_TO_DESC( aa.period_type, 'PERIOD_TYPE', 'SYS', '', '"+user.getLangId()+"') FROM DUAL)            ");
        query.append("       FROM TAWOCALIB aa                                                                                           ");
        query.append("      WHERE aa.comp_no = x.comp_no                                                                                 ");
        query.append("        AND aa.wkor_id = x.wkor_id)                                                                      AS cycle  ");
      
        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        //메서드 전용 쿼리
        query.append("  AND  x.wo_type='PMC'															");
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
        
        query.getOrderByQuery("x.wkor_id DESC", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }
    public List findWoResultPmiMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        //공통 SELECT 쿼리
        query.append(this.publicColumnQueryForFindWoList(user));
        
        query.append("		,(SELECT y.cycle||y.period_type                                          ");
        query.append("         FROM tapmequip x INNER JOIN tapmlst y                                ");
        query.append("                                  ON x.pm_id = y.pm_id                        ");
        query.append("                                 AND x.comp_no = y.comp_no                    ");
        query.append("                                 AND wo_type = 'PMC'                          ");
        query.append("        WHERE EXISTS (SELECT 1                                                ");
        query.append("                        FROM eqipInfo z                                       ");
        query.append("                       WHERE z.equip_id = x.equip_id)                         ");
        query.append("          AND ROWNUM =1                                                       ");
        query.append("      )                                                       AS cycle        ");
        
        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        //메서드 전용 쿼리
        query.append("      AND  x.wo_type='PMI'                                                        ");
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
        
        query.getOrderByQuery("x.wkor_id DESC", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }
   
    public List findWoResultTrMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        //공통 SELECT 쿼리
        query.append(this.publicColumnQueryForFindWoList(user));
        
        query.append("      ,(SELECT y.cycle||y.period_type                                          ");
        query.append("         FROM tapmequip x INNER JOIN tapmlst y                                ");
        query.append("                                  ON x.pm_id = y.pm_id                        ");
        query.append("                                 AND x.comp_no = y.comp_no                    ");
        query.append("                                 AND wo_type = 'PMC'                          ");
        query.append("        WHERE EXISTS (SELECT 1                                                ");
        query.append("                        FROM eqipInfo z                                       ");
        query.append("                       WHERE z.equip_id = x.equip_id)                         ");
        query.append("          AND ROWNUM =1                                                       ");
        query.append("      )                                                       AS cycle        ");
        
        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        //메서드 전용 쿼리
        query.append("      AND  x.wo_type='TR'                                                        ");
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
       
        query.getOrderByQuery("x.wkor_id DESC", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] updateDeleteTagWoResultMstr(final List<MaWoResultMstrDetailDTO> list, final User user)
    {
    	QueryBuffer query = new QueryBuffer();
        final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");
        
        query.append("UPDATE TAWORKORDER  SET       ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE COMP_NO = ?             ");
        query.append("  AND WKOR_ID =  ?            ");
        query.append("  AND WO_STATUS != 'C'            ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = list.get(i);

                Object[] objects = new Object[]{
                        user.getEmpId()
                        ,deleteTime
                        ,user.getCompNo()
                        ,maWoResultMstrDetailDTO.getWkOrId()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
    
    /**
     * 리포트 바디
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO ) {

		QueryBuffer query = new QueryBuffer();
		String lang = maWoResultMstrCommonDTO.getUserLang();

        query.append("SELECT																	");
        query.append("		x.wo_no woNo														");
        query.append("		,(SELECT description												");
        query.append("			FROM TADEPT														");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND dept_id = x.dept_id) deptDesc								");
        query.append("		,(SELECT emp_name													");
        query.append("			FROM TAEMP														");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND emp_id = x.emp_id) empdesc								");
        query.append("		,TO_CHAR(TO_DATE(x.start_date,'yyyymmdd'),'yyyy-mm-dd')||' '||		");
        query.append("			TO_CHAR(TO_DATE(x.start_time,'HH24MISS'),'HH24:MI')||' ~ '||	");
        query.append("			TO_CHAR(TO_DATE(x.end_date,'yyyymmdd'),'yyyy-mm-dd')||' '||		");
        query.append("			TO_CHAR(TO_DATE(x.end_time,'HH24MISS'),'HH24:MI') woDate		");
        query.append("		,x.work_time workTime												");
        query.append("		,x.description woDesc												");
        query.append("		,z.description 											AS eqDesc	");
        query.append("		,(SELECT full_desc													");
        query.append("			FROM TAEQLOC													");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("				AND eqloc_id = x.eqloc_id	) 				AS eqLocDesc	");
        query.append("		,(SELECT SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+lang+"') FROM DUAL)  woTypeDesc				");
        query.append("		,(SELECT SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+lang+"') FROM DUAL)  pmTypeDesc	");
        query.append("		,(SELECT (SELECT (select b.key_name									");
        query.append("            			from talang b										");
        query.append("            			where  b.lang = '"+lang+"'							");
        query.append("            			and aa.key_type = b.key_type						");
        query.append("            			and aa.key_no = b.key_no)	description				");
        query.append("					FROM TAFAILURE aa										");
        query.append("					WHERE aa.comp_no = x.comp_no							");
        query.append("					  AND aa.failure_id = ca_cd 							");
        query.append("					  AND aa.fail_type='CA')								");
        query.append("			FROM TAWOFAIL													");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND wkor_id = x.wkor_id) caCdDesc								");
        query.append("		,(SELECT (SELECT (select b.key_name									");
        query.append("            			from talang b										");
        query.append("            			where  b.lang = '"+lang+"'							");
        query.append("            			and aa.key_type = b.key_type						");
        query.append("            			and aa.key_no = b.key_no)	description				");
        query.append("					FROM TAFAILURE aa										");
        query.append("					WHERE aa.comp_no = x.comp_no							");
        query.append("					  AND aa.failure_id = re_cd								");
        query.append("					  AND aa.fail_type='RE')								");
        query.append("			FROM TAWOFAIL													");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND wkor_id = x.wkor_id) reCdDesc								");
        query.append("		,x.perform perform													");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woResultReport' AND key_type='LABEL') woResultReport		");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woDate' AND key_type='LABEL') workDate						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woEquip' AND key_type='LABEL') woEquip						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkPoint' AND key_type='LABEL') checkPoint				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woName' AND key_type='LABEL') woName						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqLocName' AND key_type='LABEL') equipLoc					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woType' AND key_type='LABEL') woType						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woRemark' AND key_type='LABEL') woRemark					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woCraft' AND key_type='LABEL') workCraft					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='insertPart' AND key_type='LABEL') insertPart				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='dept' AND key_type='LABEL') dept							");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='manager' AND key_type='LABEL') manager						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='reason' AND key_type='LABEL') reason						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPoint' AND key_type='LABEL') pmPoint						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='empNumber' AND key_type='LABEL') empNumber					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='name' AND key_type='LABEL') empName							");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime2					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark' AND key_type='LABEL') remark						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='startDate' AND key_type='LABEL') startDate					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='endDate' AND key_type='LABEL') endDate						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNo' AND key_type='LABEL') partNumber					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNameSize' AND key_type='LABEL') partNameSize			");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='useQty' AND key_type='LABEL') useQty						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seqNo' AND key_type='LABEL') seqNo							");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='asmbInspect' AND key_type='LABEL') asmbInspect				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkMethod' AND key_type='LABEL') checkMethod				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='fitBasis' AND key_type='LABEL') fitBasis					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkType' AND key_type='LABEL') checkType					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPointRsltStatus' AND key_type='LABEL') pmPointRsltStatus	");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkValUom' AND key_type='LABEL') checkValUom				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='resultVal' AND key_type='LABEL') resultVal					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipNo' AND key_type='LABEL') equipNo						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipName' AND key_type='LABEL') equipName					");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                                           		");
        query.append("ON x.comp_no = y.comp_no                                                                                  ");
        query.append(" AND x.wkor_id = y.wkor_id                                               		                    		");
        query.append("		INNER JOIN TAEQUIPMENT z                                              		                    	");
        query.append("      ON y.comp_no = z.comp_no                                                		                    ");
        query.append("       AND y.equip_id = z.equip_id            		                                                	");
        query.append("WHERE 1=1  																								");
        query.append(" AND x.comp_no = '"+maWoResultMstrCommonDTO.getCompNo()+"'				");
        query.append(" AND x.wkor_id = "+id+"													");

		return getJdbcTemplate().queryForList(query.toString());
	}

    /**
     * 리포트 작업자
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoCraftList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO ) {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT																		");
        query.append("		(SELECT emp_no															");
        query.append("			FROM TAEMP															");
        query.append("			WHERE comp_no = x.comp_no											");
        query.append("			  AND emp_id = x.emp_id) craftEmpNo									");
        query.append("		,(SELECT emp_name														");
        query.append("			FROM TAEMP															");
        query.append("			WHERE comp_no = x.comp_no											");
        query.append("			  AND emp_id = x.emp_id) craftEmpDesc								");
        query.append("		,TO_CHAR(TO_DATE(x.start_date,'yyyymmdd'),'yyyy-mm-dd')||' '||			");
        query.append("			TO_CHAR(TO_DATE(x.start_time,'HH24MISS'),'HH24:MI') craftStartDate	");
        query.append("		,TO_CHAR(TO_DATE(x.end_date,'yyyymmdd'),'yyyy-mm-dd')||' '||			");
        query.append("			TO_CHAR(TO_DATE(x.end_time,'HH24MISS'),'HH24:MI') craftEndDate		");
        query.append("		,TO_CHAR(x.work_time) craftWorkTime										");
        query.append("		,x.remark craftRemark													");
        query.append("FROM TAWOCRAFT x																");
        query.append("WHERE x.comp_no = '"+maWoResultMstrCommonDTO.getCompNo()+"'					");
        query.append("  AND x.wkor_id = "+id+"														");
        query.append("UNION ALL																		");
        query.append("SELECT '','','','','','' FROM DUAL											");

		return getJdbcTemplate().queryForList(query.toString());
	}

    /**
     * 리포트 투입부품
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoPartList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO ) {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT														");
        query.append("		y.part_no partNo										");
        query.append("		,y.description||', '||y.pt_size partDesc				");
        query.append("		,TO_CHAR(x.use_qty) partUseQty							");
        query.append("FROM TAWOPARTS x, TAPARTS y									");
        query.append("WHERE x.comp_no = y.comp_no									");
        query.append("  AND x.part_id = y.part_id									");
        query.append("  AND x.comp_no = '"+maWoResultMstrCommonDTO.getCompNo()+"'	");
        query.append("  AND x.wkor_id = "+id+"										");
        query.append("UNION ALL														");
        query.append("SELECT '','','' FROM DUAL										");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 리포트 검사항목
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoPointList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO ) {

		QueryBuffer query = new QueryBuffer();
		String lang = maWoResultMstrCommonDTO.getUserLang();

        query.append("SELECT TO_CHAR(y.step_num) AS POSEQNO,											");
        query.append("		(SELECT full_desc															");
        query.append("		  FROM TAEQASMB																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND eqasmb_id = y.eqasmb_id)||'/'||										");
        query.append("		y.check_point AS POCHECKPOINT,												");
        query.append("		y.check_method||'/'||y.fit_basis AS POFITBASIS,								");
        query.append("		SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+lang+"') AS POCHECKTYPE,		");
        query.append("		y.check_min||'/'||y.check_basis_val||'/'||y.check_max||'('||y.uom||')' AS POUOM,			");
        query.append("		DECODE(z.wo_status,'C',														");
        query.append("		SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+lang+"'),	");
        query.append("		'')||'' AS POSTATUS,														");
        query.append("		DECODE(z.wo_status,'C', x.result_value ,'')||'' AS POVALUE					");
        query.append("FROM TAWOPOINT x, TAPMPOINT y, TAWORKORDER z										");
        query.append("WHERE x.comp_no = y.comp_no														");
        query.append("AND x.comp_no = z.comp_no															");
        query.append("AND x.wkor_id = z.wkor_id															");
        query.append("	AND x.pm_point_id = y.pm_point_id												");
        query.getAndQuery("x.wkor_id", id);
        query.getAndQuery("x.comp_no", maWoResultMstrCommonDTO.getCompNo());
        query.append("UNION ALL																			");
        query.append("SELECT '','','','','','','' FROM DUAL												");

		return getJdbcTemplate().queryForList(query.toString());
	}
    /**
     * 리포트 작업설비항목
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoEqList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO ) {

		QueryBuffer query = new QueryBuffer();

		query.append("SELECT (SELECT item_no 								");
        query.append("			FROM TAEQUIPMENT 							");
        query.append("			WHERE comp_no = x.comp_no					");
        query.append("			AND   equip_id = x.equip_id )  itemNumber	");
        query.append("		,(SELECT description 							");
        query.append("			FROM TAEQUIPMENT 							");
        query.append("			WHERE comp_no = x.comp_no					");
        query.append("			AND   equip_id = x.equip_id )  itemDesc		");
        query.append("FROM TAWOEQUIP x										");
        query.append("WHERE 1=1												");
        query.getAndQuery("x.wkor_id", id);
        query.getAndQuery("x.comp_no", maWoResultMstrCommonDTO.getCompNo());

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 리포트 작업필수검사항목
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoStPointList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO ) {

		QueryBuffer query = new QueryBuffer();
		String lang = maWoResultMstrCommonDTO.getUserLang();

		query.append("SELECT TO_CHAR(y.step_num) AS STSEQNO,											");
        query.append("		(SELECT full_desc															");
        query.append("		  FROM TAEQASMB																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND eqasmb_id = y.eqasmb_id)||'/'||										");
        query.append("		y.check_point AS STCHECKPOINT,												");
        query.append("		y.check_method||'/'||y.fit_basis AS STFITBASIS,								");
        query.append("		SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+lang+"') AS STCHECKTYPE,	");
        query.append("		y.check_min||'/'||y.check_basis_val||'/'||y.check_max||'('||y.uom||')' AS STUOM,		");
        query.append("		DECODE(z.wo_status,'C',														");
        query.append("		SFACODE_TO_DESC(x.st_point_rslt_status,'ST_POINT_RSLT_STATUS','SYS',x.comp_no,'"+lang+"'),	");
        query.append("		'')||'' AS STSTATUS,														");
        query.append("		DECODE(z.wo_status,'C', x.result_value ,'')||'' AS STVALUE					");
        query.append("FROM TAWOSTPOINT x, TASTWRKPOINT y, TAWORKORDER z									");
        query.append("WHERE x.comp_no = y.comp_no														");
        query.append("AND x.comp_no = z.comp_no															");
        query.append("AND x.wkor_id = z.wkor_id															");
        query.append("	AND x.stwrk_point_id = y.stwrk_point_id											");
        query.getAndQuery("x.wkor_id", id);
        query.getAndQuery("x.comp_no", maWoResultMstrCommonDTO.getCompNo());
        query.append("UNION ALL																			");
        query.append("SELECT '','','','','','','' FROM DUAL												");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {        
    	String lang = user.getLangId();
    	
        QueryBuffer query = new QueryBuffer();
        String startDate = maWoResultMstrCommonDTO.getFilterStartDate();
        String endDate = maWoResultMstrCommonDTO.getFilterEndDate();
        String compNo  =user.getCompNo();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        // CommonDTO의 wkorId가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maWoResultMstrCommonDTO.getWkOrId()))
        {
            query.getAndNumKeyQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.pmsched_id", maWoResultMstrCommonDTO.getPmSchedId());
        
        if (!"".equals(maWoResultMstrCommonDTO.getHiddenWoType())) query.append("AND x.wo_type <> 'PMC'");
        //작업번호
        query.getLikeQuery("x.wo_no", maWoResultMstrCommonDTO.getFilterWoNo());
        //작업명
        query.getLikeQuery("x.description", maWoResultMstrCommonDTO.getFilterWkOrDesc());
        //법정설비
        query.getAndQuery("y.is_law_eq", maWoResultMstrCommonDTO.getFilterIsLawEq());
        //설비등급
        query.getSysCdQuery("y.eq_grade", maWoResultMstrCommonDTO.getFilterEqGrade(), maWoResultMstrCommonDTO.getFilterEqGradeDesc(), "EQ_GRADE", compNo,user.getLangId());
        
        //요청우선순위
        if(!"".equals(maWoResultMstrCommonDTO.getFilterReqPriority()) || !"".equals(maWoResultMstrCommonDTO.getFilterReqPriorityDesc())){
            query.append("AND EXISTS (SELECT 1                        ");
            query.append("            FROM TAWOREQ a INNER JOIN TAWOREQRES b ON a.woreq_id = b.woreq_id         ");
            query.append("            WHERE b.woreqres_method ='WO'             ");
            query.append("              AND b.wkor_id = x.wkor_id               ");
            query.getCodeLikeQuery("a.req_priority", "SFACODE_TO_DESC(a.req_priority,'REQ_PRIORITY','SYS','','"+user.getLangId()+"')", maWoResultMstrCommonDTO.getFilterReqPriority(), maWoResultMstrCommonDTO.getFilterReqPriorityDesc());
            query.append("                  )                                       ");
        }
        
        //작업일자
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
        //내/외자
        query.getSysCdQuery("y.plf_type", maWoResultMstrCommonDTO.getFilterPlfTypeId(), maWoResultMstrCommonDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo,user.getLangId());
    	//작업종류
    	query.getSysCdQuery("x.wo_type", maWoResultMstrCommonDTO.getFilterWoTypeId(), maWoResultMstrCommonDTO.getFilterWoTypeDesc(), "WO_TYPE", compNo,user.getLangId());
        //작업형태
    	query.getSysCdQuery("x.pm_type", maWoResultMstrCommonDTO.getFilterPmTypeId(), maWoResultMstrCommonDTO.getFilterPmTypeDesc(), "x.wo_type||'_TYPE'", compNo,user.getLangId());
    	//shift
    	query.getSysCdQuery("x.shift_type", maWoResultMstrCommonDTO.getFilterShiftId(), maWoResultMstrCommonDTO.getFilterShiftDesc(), "SHIFT_TYPE", compNo,user.getLangId());
        //예방작업#
    	query.getAndQuery("(SELECT pm_no FROM TAPMLST where comp_no = '"+compNo+"' AND pm_id=x.pm_id)", maWoResultMstrCommonDTO.getFilterPmNo());
        //부서
        query.getDeptLevelQuery("x.dept_id", maWoResultMstrCommonDTO.getFilterDeptId(), maWoResultMstrCommonDTO.getFilterDeptDesc(), compNo);
        //설비
        query.getCodeLikeQuery("y.equip_id", "y.description||y.item_no", 
        		maWoResultMstrCommonDTO.getFilterEquipId(), maWoResultMstrCommonDTO.getFilterEquipDesc());
        //설비번호
        query.getStringEqualQuery("y.item_no", maWoResultMstrCommonDTO.getFilterEquipNo());
        
        //제외하고....
        if(!"".equals(maWoResultMstrCommonDTO.getNotPmTypeId())){
            query.append("AND x.pm_type  NOT IN ('"+maWoResultMstrCommonDTO.getNotPmTypeId()+"') ");
        }
        if(!"".equals(maWoResultMstrCommonDTO.getNotWoTypeId())){
            query.append("AND x.wo_type  NOT IN ('"+maWoResultMstrCommonDTO.getNotWoTypeId()+"') ");
        }
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
        		maWoResultMstrCommonDTO.getFilterEmpId(), maWoResultMstrCommonDTO.getFilterEmpDesc());
        
        
        //고장작업
        if(!"".equals(maWoResultMstrCommonDTO.getFilterCaCd())){
        	query.append("AND (SELECT ca_cd FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id ) ='"+maWoResultMstrCommonDTO.getFilterCaCd()+"'  ");

        }
        
      //고장조치
        if(!"".equals(maWoResultMstrCommonDTO.getFilterReCd())){
        	query.append("AND (SELECT re_cd FROM TAWOFAIL y WHERE x.wkor_id=y.wkor_id ) ='"+maWoResultMstrCommonDTO.getFilterReCd()+"'  ");

        }
        
        //관리자(정)
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.main_mng_id AND a.comp_no='"+compNo+"')", 
        		maWoResultMstrCommonDTO.getFilterMainMngId(), maWoResultMstrCommonDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("y.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.sub_mng_id AND a.comp_no='"+compNo+"')", 
        		maWoResultMstrCommonDTO.getFilterSubMngId(), maWoResultMstrCommonDTO.getFilterSubMngName());
        //설비유형
        query.getSysCdQuery("y.eqctg_type", maWoResultMstrCommonDTO.getFilterEqCtgTypeId(), maWoResultMstrCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", compNo,user.getLangId());
        //위치
        query.getEqLocLevelQuery("y.eqloc_id", maWoResultMstrCommonDTO.getFilterEqLocId(), maWoResultMstrCommonDTO.getFilterEqLocDesc(), compNo);
        //종류
        query.getEqCtgLevelQuery("y.eqctg_id", maWoResultMstrCommonDTO.getFilterEqCtgId(), maWoResultMstrCommonDTO.getFilterEqCtgDesc(), compNo);
        //상태
        if("!C".equals(maWoResultMstrCommonDTO.getFilterWoStatus()))
        {
        	query.append("AND x.wo_status != 'C' ");
        }
        else query.getSysCdQuery("x.wo_status", maWoResultMstrCommonDTO.getFilterWoStatus(), maWoResultMstrCommonDTO.getFilterWoStatusDesc(), "WO_STATUS", compNo,user.getLangId());
        
        //원인코드
        if (!"".equals(maWoResultMstrCommonDTO.getCaDesc())) {
        	query.append("AND x.wkor_id IN (										");
        	query.append("		SELECT wkor_id FROM TAWOFAIL WHERE ca_cd  IN (		");
        	query.append("			SELECT aa.failure_id FROM TAFAILURE aa WHERE (select b.key_name from talang b where b.lang = '"+lang+"' and aa.key_type=b.key_type and aa.key_no = b.key_no) = '"+maWoResultMstrCommonDTO.getCaDesc()+"' AND aa.fail_type='CA')");
        	query.append("		 )													");
		}
        //원인코드가 없는 것
        if ("1".equals(maWoResultMstrCommonDTO.getNotCaCd())) {
        	query.append("AND x.wkor_id NOT IN (										");
        	query.append("		SELECT wkor_id FROM TAWOFAIL WHERE comp_no = x.comp_no  ");
        	query.append("									 AND ca_cd is not null)		");
		}
      //조치코드
        if (!"".equals(maWoResultMstrCommonDTO.getReDesc())) {
        	query.append("AND x.wkor_id IN (										");
        	query.append("		SELECT wkor_id FROM TAWOFAIL WHERE re_cd  IN (		");
        	query.append("			SELECT aa.failure_id FROM TAFAILURE aa WHERE (select b.key_name from talang b where b.lang = '"+lang+"' and aa.key_type=b.key_type and aa.key_no = b.key_no) = '"+maWoResultMstrCommonDTO.getReDesc()+"' AND aa.fail_type='RE')");
        	query.append("		 )													");
		}
        //조치코드가 없는 것
        if ("1".equals(maWoResultMstrCommonDTO.getNotReCd())) {
        	query.append("AND x.wkor_id NOT IN (										");
        	query.append("		SELECT wkor_id FROM TAWOFAIL WHERE comp_no = x.comp_no  ");
        	query.append("									 AND re_cd is not null)		");
		}
        
        if (!"".equals(maWoResultMstrCommonDTO.getWkOrId()))
        {
            query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
            return query.toString();
        }
        
        
        if (!"".equals(maWoResultMstrCommonDTO.getFilterCalCorId()))
        {
        	query.append("  AND  EXISTS (SELECT 1 FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no AND y.calib_corp='"+maWoResultMstrCommonDTO.getFilterCalCorId()+"')  	");

            return query.toString();
        }
        
        if (!"".equals(maWoResultMstrCommonDTO.getFilterCalEnv()))
        {
        	query.append("  AND  EXISTS (SELECT 1 FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no AND UPPER(y.calib_env) LIKE UPPER('%"+maWoResultMstrCommonDTO.getFilterCalEnv()+"%'))  	");

            return query.toString();
        }
        
        if (!"".equals(maWoResultMstrCommonDTO.getFilterCalRsltStatId()))
        {
        	query.append("  AND  EXISTS (SELECT 1 FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no AND y.calib_result_status='"+maWoResultMstrCommonDTO.getFilterCalRsltStatId()+"')  	");

            return query.toString();
        }
        if (!"".equals(maWoResultMstrCommonDTO.getFilterCalTypeId()))
        {
        	query.append("  AND  EXISTS (SELECT 1 FROM TAWOCALIB y WHERE x.wkor_id=y.wkor_id AND x.comp_no=y.comp_no AND y.calib_type='"+maWoResultMstrCommonDTO.getFilterCalTypeId()+"')  	");

            return query.toString();
        }
        
        //설비고장(분) 
        query.getInequalityQuery("(SELECT a.eqdn_work_time FROM TAWOFAIL a WHERE a.wkor_id = x.wkor_id)", maWoResultMstrCommonDTO.getConOperDesc(), maWoResultMstrCommonDTO.getFilterEqDnWorkTime());

        
        
        query.getCodeLikeQuery("x.self_vendor_type", "SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+user.getLangId()+"')", maWoResultMstrCommonDTO.getSelfVendorType(), maWoResultMstrCommonDTO.getSelfVendorTypeDesc());
        query.getCodeLikeQuery("x.vendor_id", "SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)", maWoResultMstrCommonDTO.getVendorId(), maWoResultMstrCommonDTO.getVendorDesc());
        
        query.getLikeQuery("x.vendor_desc", maWoResultMstrCommonDTO.getFilterVendorName());

        query.getWkCtrLevelQuery("x.wkctr_id", maWoResultMstrCommonDTO.getFilterWkCtrId(), maWoResultMstrCommonDTO.getFilterWkCtrDesc(), compNo);
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
                maWoResultMstrCommonDTO.getFilterPlantId(), maWoResultMstrCommonDTO.getFilterPlantDesc());
        
        //부위
        query.getCodeLikeQuery("x.eqasmb_id", "(SELECT a.description FROM  TAEQASMB a WHERE a.comp_no = '"+compNo+"' AND a.eqasmb_id = x.eqasmb_id )", 
                maWoResultMstrCommonDTO.getFilterEqAsmbId(), maWoResultMstrCommonDTO.getFilterEqAsmbDesc());
        
        //품의여부
        query.getSysCdQuery("x.is_draft", maWoResultMstrCommonDTO.getFilterIsDraft(), maWoResultMstrCommonDTO.getFilterIsDraft(), "IS_USE", user.getCompNo(), user.getLangId());
        
        // 작업요청 일자
        if (!"".equals(maWoResultMstrCommonDTO.getFilterResStartDate())&& !"".equals(maWoResultMstrCommonDTO.getFilterResEndDate()))
        {
        	query.append("  and x.wkor_id in (select wkor_id		");
        	query.append("                    from TAWOREQRES															");
        	query.append("                    where 1=1 																");
        	query.append("                    and comp_no = '"+compNo+"'												");
        	query.append("                    and res_date >='"+maWoResultMstrCommonDTO.getFilterResStartDate()+"'		");
        	query.append("                    and res_date <= '"+maWoResultMstrCommonDTO.getFilterResEndDate()+"')	");
        }
        
        // 페이지 pm type
        if(!"".equals(maWoResultMstrCommonDTO.getPagePmType())){
            query.getAndQuery("x.pm_type", maWoResultMstrCommonDTO.getPagePmType());
        }
        
        // 상위설비 사용부서
        query.getDeptLevelQuery("y.usage_dept", maWoResultMstrCommonDTO.getFilterUsageDeptId(), maWoResultMstrCommonDTO.getFilterUsageDeptDesc(), user.getCompNo());
        
        // 상위설비
        query.getCodeLikeQuery("y.p_equip_id", "(SELECT c.description||c.item_no FROM TAEQUIPMENT c WHERE c.comp_no = y.comp_no AND c.equip_id = y.p_equip_id)", 
        		maWoResultMstrCommonDTO.getFilterParEquipId(), maWoResultMstrCommonDTO.getFilterParEquipDesc());

        // linked 설비 사용부셔
        query.getStringEqualQuery("y.usage_dept", maWoResultMstrCommonDTO.getLinkedUsageDeptId());

        //최신버전의 설비의 작업만 보여줌.
        query.getAndQuery("y.is_last_version", "Y");
        
        /*Room No.*/
        if(!"".equals(maWoResultMstrCommonDTO.getFilterRoomNumber()))
        {
            query.append(" AND y.eqloc_id IN ( SELECT c.eqloc_id      ");
            query.append("                     FROM TAEQLOC c ");
            query.append("                    WHERE 1=1                 ");
            query.append("                      AND c.comp_no = y.comp_no                 ");
            query.getLikeQuery("c.eqloc_no", maWoResultMstrCommonDTO.getFilterRoomNumber());
            query.append("                 )                                              ");
        }
        //사용부품
        if(!"".equals(maWoResultMstrCommonDTO.getFilterPartId()))
        {
            query.append("AND EXISTS(SELECT wopart_id           ");
            query.append("           FROM TAWOPARTS             ");
            query.append("           WHERE comp_no = x.comp_no  ");
            query.append("           AND wkor_id = x.wkor_id    ");
            query.getAndQuery("part_id", maWoResultMstrCommonDTO.getFilterPartId());
            query.append("           )                          ");
        }
        
        //작업상세내용
        query.getLikeQuery("x.perform", maWoResultMstrCommonDTO.getFilterWoPerform());
        
        //작업자
        if(!"".equals(maWoResultMstrCommonDTO.getFilterWorkerId())||!"".equals(maWoResultMstrCommonDTO.getFilterWorkerDesc())){
        	query.append("AND x.wkor_id IN (SELECT a.wkor_id  						");
        	query.append("					FROM TAWOCRAFT a						");
        	query.append("					WHERE 1=1								");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getCodeLikeQuery("a.emp_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = a.emp_id AND aa.comp_no='"+compNo+"')", 
            		maWoResultMstrCommonDTO.getFilterWorkerId(), maWoResultMstrCommonDTO.getFilterWorkerDesc());
            query.append("					)										");
        }
        
        
        return query.toString();
    }

    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user, String woType)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                        ");
        query.append("      COUNT(1)                                                                ");

        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        query.getStringEqualQuery("x.wo_type", woType);
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

	/**
	 * Emergency S/Part Issue
	 * @param wkorId
	 * @param compNo
	 * @return
	 */
	public int[] updateEmgPart(final List<MaWoResultMstrDetailDTO> list, final User user) 
	{
		QueryBuffer query = new QueryBuffer();
        
    	query.append("UPDATE TAPTEMGISSLIST SET                     ");
        query.append("       ptemg_task_status      = ?,            ");
        query.append("       wkor_id                = ?             ");
        query.append("WHERE  comp_no                = ?             ");
        query.append("  AND  wkor_id                = ?             ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = list.get(i);

                Object[] objects = new Object[] {
                        "W",
                        "",
                        user.getCompNo(),
                        maWoResultMstrDetailDTO.getWkOrId()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
	}
	

	public int create4wp(String wkorId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("begin                                                          ");
    	query.append("SP_WOMAKE_4WP_BYONE(                                          ");
    	query.append("                  '"+user.getCompNo()+"'          ");
    	query.append("                 ,"+wkorId+"         ");
    	query.append("                 );                                            ");
    	query.append("end;                                                           ");
    	
    	this.getJdbcTemplate().execute(query.toString());
    	
    	return 0;
    }

	@Override
	public List findReportWoListLabel(String string, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		
		QueryBuffer query = new QueryBuffer();
		String lang = maWoResultMstrCommonDTO.getUserLang();

        query.append("SELECT																	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woResultReport' AND key_type='LABEL') woResultReport,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='number' AND key_type='LABEL') num,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woDate' AND key_type='LABEL') workDate,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woEquip' AND key_type='LABEL') woEquip,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipNo' AND key_type='LABEL') equipNo,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkPoint' AND key_type='LABEL') checkPoint,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woName' AND key_type='LABEL') woName,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipName' AND key_type='LABEL') equipName,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqLocName' AND key_type='LABEL') equipLoc,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woType' AND key_type='LABEL') woType,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmType' AND key_type='LABEL') pmType,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='action' AND key_type='LABEL') action,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woRemark' AND key_type='LABEL') woRemark,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woCraft' AND key_type='LABEL') workCraft,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='empNumber' AND key_type='LABEL') empNumber,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='name' AND key_type='LABEL') empName,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime2,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark' AND key_type='LABEL') remark,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='insertPart' AND key_type='LABEL') insertPart,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNo' AND key_type='LABEL') partNumber,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNameSize' AND key_type='LABEL') partNameSize,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='useQty' AND key_type='LABEL') useQty,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seqNo' AND key_type='LABEL') seqNo,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='asmbInspect' AND key_type='LABEL') asmbInspect,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkMethod' AND key_type='LABEL') checkMethod,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='fitBasis' AND key_type='LABEL') fitBasis,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkType' AND key_type='LABEL') checkType,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPointRsltStatus' AND key_type='LABEL') pmPointRsltStatus,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='dept' AND key_type='LABEL') dept,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='manager' AND key_type='LABEL') manager,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='reason' AND key_type='LABEL') reason,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='startDate' AND key_type='LABEL') startDate,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='endDate' AND key_type='LABEL') endDate,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='endDate' AND key_type='LABEL') endDate,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkValUom' AND key_type='LABEL') checkValUom,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPoint' AND key_type='LABEL') pmPoint,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='resultVal' AND key_type='LABEL') resultVal					");
        query.append("FROM DUAL;														");

		return getJdbcTemplate().queryForList(query.toString());
	}
	
	/**
     * grid find
     * @author  js.lee
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoPmwOvhResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("WITH eqipInfo AS (                                                            		");
        query.append("    SELECT a.equip_id,                                                        		");
        query.append("           a.wkor_id,                                                         		");
        query.append("           b.item_no,                                                         		");
        query.append("           a.comp_no,                                                         		");
        query.append("           b.sub_mng_id,                                                      		");
        query.append("           b.eqctg_id,                                                        		");
        query.append("           b.old_eq_no,                                                       		");
        query.append("           b.description ,                                                    		");
        query.append("           b.eq_grade ,                                                       		");
        query.append("           b.eqctg_type                                                       		");
        query.append("     FROM  TAWOEQUIP a, TAEQUIPMENT b                                         		");
        query.append("     WHERE a.comp_no = b.comp_no                                              		");
        query.append("       AND a.equip_id = b.equip_id   )                                        		");
        query.append("SELECT                                                                        		");
        query.append("        ''                                                            seqNo,        		");
        query.append("        ''                                                            isDelCheck,    		");
        query.append("      x.wkor_id                                                   	ID,        			");
        query.append("        x.wkor_id                                                   	wkOrId,        		");
        query.append("        x.wo_no                                                    	woNo,      			");
        query.append("        x.description                                                 wkOrDesc,    		");
        query.append("        x.wkor_date                                                 	startDate,    		");
        query.append("      x.start_date||x.start_time                                  	startDatetime,		");
        query.append("      x.end_date||x.end_time                                     	 	endDatetime,		");
        query.append("     TO_CHAR(DECODE(y.eqctg_type,'MD','('||y.old_eq_no||')'||y.description, y.description)) AS equipDesc,      		");
        query.append("      y.item_no  									   	      			AS equipNo,    		");
        query.append("        (SELECT description                                                        		");
        query.append("           FROM TADEPT                                                            		");
        query.append("          WHERE comp_no = x.comp_no                                                		");
        query.append("            AND dept_id = x.dept_id)                                 deptDesc,    		");
        query.append("         (SELECT description                                                    			");
        query.append("          FROM TAWKCTR                                                            		");
        query.append("          WHERE comp_no = x.comp_no                                                		");
        query.append("          AND wkctr_id = x.wkctr_id)                                 wkCtrDesc,    		");
        query.append("        (SELECT SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL)              shiftDesc,    		");
        query.append("        (SELECT SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL)                  woTypeDesc,    		");
        query.append("        (SELECT SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL)          pmTypeDesc,    		");
        query.append("        x.work_time                                                 workTime,    		");
        query.append("        TO_CHAR(TO_DATE(x.start_time,'hh24mi'),'hh24:mi')            startTime,   	");
        query.append("        TO_CHAR(TO_DATE(x.end_time,'hh24mi'),'hh24:mi')                endTime,   	");
        query.append("        (SELECT a.lndn_start_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no    		");
        query.append("            AND a.wkor_id = x.wkor_id) prodStartTime,                            		");
        query.append("        (SELECT a.lndn_end_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no       	");
        query.append("            AND a.wkor_id = x.wkor_id) prodEndTime,                               	");
        query.append("        (SELECT a.lndn_work_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no    		");
        query.append("            AND a.wkor_id = x.wkor_id) lndnTime,                                		");
        query.append("        (SELECT SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"') FROM DUAL)             woStatusDesc,    		");
        query.append("        x.wo_status                                                woStatus,      	");
        query.append("        (SELECT emp_name                                                        		");
        query.append("           FROM TAEMP                                                            		");
        query.append("          WHERE comp_no = x.comp_no                                                	");
        query.append("            AND emp_id = x.emp_id)                                     empDesc,    	");
        query.append("        (SELECT c.emp_name                                							");
        query.append("            FROM TAEMP c                                          					");
        query.append("            WHERE x.comp_no = c.comp_no                                       		");
        query.append("                AND y.sub_mng_id = c.emp_id)                 AS subMng,     			");
        query.append("        (SELECT c.description                            								");
        query.append("            FROM TAEQCTG c                                        					");
        query.append("            WHERE x.comp_no = c.comp_no                                       		");
        query.append("                ANd y.eqctg_id = c.eqctg_id  )             AS eqCtgDesc,      		");
        query.append("        (SELECT SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL)  selfVendorTypeDesc,        		");
        query.append("      (SELECT SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no) FROM DUAL)            vendorDesc,    		");
        query.append("      x.vendor_desc                                                   vendorName,    		");
        query.append("        x.perform                                                     perform,    		");
        query.append("      x.wo_type                                                    woType,        		");
        query.append("      x.pm_type                                                    pmType,        		");
        query.append("      x.wo_gen_type                                                woGenType,    		");
        query.append("      (SELECT param1 FROM TACDSYSD WHERE list_Type= x.wo_type||'_TYPE' AND cdsysd_no=x.pm_type) param		");
        query.append("      , x.upd_date                                           updDate                		");

        //공통 FROM 절 쿼리
        query.append(this.publicFromQueryForFindWoList(user));
        
        query.append("  AND  x.wo_type='PMW'														");
        query.append("  AND  x.pm_type='OVH'														");
        query.append(this.getWhere(maWoResultMstrCommonDTO,user));
        query.getOrderByQuery("4 DESC", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }


	@Override
	public String getData(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
	
		query.append("SELECT 										");
		query.append("    CASE WHEN min(x.exceltab_id) IS NOT NULL 	");
		query.append("             THEN min(x.exceltab_id || ',' || x.description || ',' || x.table_name) ");
		query.append("             ELSE '0'	 						");
		query.append("             END           					");
		query.append("FROM TAEXCELTAB x								");
		query.append("WHERE x.exceltab_no = ?						");
		
	    Object[] objects = new Object[] {
	    		maWoResultMstrCommonDTO.getExceltabNo()
	    };
	
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}

	public String checkWoResultMstrStatus(String wkorId, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("SELECT                            			");
        query.append("       COUNT(1)                   			");
        query.append("  FROM TAWORKORDER x              			");
        query.append(" WHERE 1 = 1                      			");
        query.getAndQuery("x.wkor_id", wkorId);	
        query.append(" AND x.wo_status NOT IN ('PRW', 'PRP', 'P')	");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}

	public int deleteWoResultMstr(String wkorId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	        
    	query.append("DELETE TAWORKORDER         				");
        query.append(" WHERE comp_no = ?             			");
        query.append("   AND wkor_id = ?	           			");
        query.append("   AND wo_status IN ('PRW', 'PRP', 'P')	");
        
        Object[] objects = new Object[] {
	    		 user.getCompNo()
	           , wkorId
	    };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
	
}