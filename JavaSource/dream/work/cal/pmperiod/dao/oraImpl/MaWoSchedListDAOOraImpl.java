package dream.work.cal.pmperiod.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.cal.pmperiod.dao.MaWoSchedListDAO;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;

/**
 * 예방작업일정(기간) - 목록 dao
 * @author  kim21017
 * @version $Id: MaWoSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoSchedListDAOTarget"
 * @spring.txbn id="maWoSchedListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoSchedListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoSchedListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoSchedCommonDTO
     * @return List
     */
    public List findSchedList(MaWoSchedCommonDTO maWoSchedCommonDTO, User user)
    {

        QueryBuffer query = new QueryBuffer();
        query.append("SELECT                   																									");
        query.append("       '' 																							seqNo,				");
        query.append("		 '' 																							isDelCheck,			");
        query.append("       x.pmsched_id 																					pmSchedId,      	");
        query.getDate("x.plan_date", "PLANDATE");
        query.append(",																															");
        query.getDate("x.actual_date", "COMPLETEDATE");
        query.append(",																															");
        query.append("       x.sched_date   																				SCHEDDATE,			");
        query.append("       y.pm_no 																						pmNo,        		");
        query.append("       SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"') 			pmSchedStatus,		");
        query.append("       (SELECT emp_id FROM TAWORKORDER WHERE pm_id = x.pm_id AND wkor_id = x.wkor_id) 				empId,       		");
        query.append("       (SELECT emp_name FROM TAEMP WHERE emp_id = (SELECT emp_id FROM TAWORKORDER WHERE pm_id = x.pm_id AND wkor_id = x.wkor_id)) empDesc,     ");
        query.append("       y.description 																					pmDesc,				");
        query.append("       (SELECT b.full_desc                                                              									");
        query.append("          FROM TAEQUIPMENT a, TAEQLOC b                                                  					                ");
        query.append("          WHERE a.comp_no = b.comp_no                                            											");
        query.append("           AND a.eqloc_id = b.eqloc_id                                                    								");
        query.append("           AND a.comp_no = x.comp_no                                                      								");
        query.append("           AND a.equip_id = x.equip_id)                           									AS eqLocDesc,       ");
        query.append("		(SELECT TO_CHAR(DECODE(b.eqctg_type,'MD','('||b.old_eq_no||')'||b.item_no,b.item_no))								");
        query.append("			FROM TAPMEQUIP a, TAEQUIPMENT b																					");
        query.append("			WHERE a.comp_no = b.comp_no																						");
        query.append("				AND a.equip_id = b.equip_id																					");
        query.append("				AND a.pm_id = y.pm_id																						");
        query.append("				AND a.pmequip_id = x.pmequip_id																				");
        query.append("				AND a.comp_no = y.comp_no																					");
         query.append("			) 																							AS equipNo,			");
        query.append("		(SELECT TO_CHAR(DECODE(b.eqctg_type,'MD','('||b.old_eq_no||')'||b.description,b.description))						");
        query.append("			FROM TAPMEQUIP a, TAEQUIPMENT b																					");
        query.append("			WHERE a.comp_no = b.comp_no																						");
        query.append("				AND a.equip_id = b.equip_id																					");
        query.append("				AND a.pm_id = y.pm_id																						");
        query.append("				AND a.pmequip_id = x.pmequip_id																				");
        query.append("				AND a.comp_no = y.comp_no																					");
        query.append("				) 																						AS equipDesc,		");
        query.append("       (SELECT description																								");
        query.append("          FROM TADEPT																										");
        query.append("         WHERE comp_no = y.comp_no																						");
        query.append("           AND dept_id = y.dept_id) 																	deptDesc,			");
        query.append("		 (SELECT description																								");
        query.append("		  FROM TAWKCTR																										");
        query.append("		  WHERE comp_no = y.comp_no																							");
        query.append("		  AND wkctr_id = y.wkctr_id) 																	wkCtrDesc,			");
        query.append("       SFACODE_TO_DESC(y.pm_type,y.wo_type||'_TYPE','SYS','','"+user.getLangId()+"')					pmTypeDesc,			");
        query.append("       y.pm_type 																						pmType,				");
        query.append("       y.wo_type 									 													woType,				");
        query.append("       SFACODE_TO_DESC(y.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"')				scheduleTypeDesc,	");
        query.append("       y.cycle||y.period_type 																		cycle,				");
        query.append("       y.USAGE 																						USAGE,              ");
        query.append("       (SELECT wo_status                                                          										");
        query.append("           FROM TAWORKORDER                                                       										");
        query.append("          WHERE comp_no = x.comp_no                                               										");
        query.append("            AND wkor_id = x.wkor_id) 																	woStatus,           ");
        query.append("       x.pmsched_status 																				pmStatusCode,       ");
        query.append("        y.pm_id 																						pmId,               ");
        query.append("       (SELECT param2 FROM TACDSYSD WHERE cdsysd_no=y.pm_type AND list_type= y.wo_type||'_TYPE')		param,  			");
        query.append("		(SELECT (SELECT b.param1 FROM TACDSYSD b WHERE b.list_type=a.wo_type||'_TYPE' AND b.cdsysd_no= a.pm_type)			");
        query.append("		FROM TAWORKORDER a																									");
        query.append("		WHERE a.comp_no = x.comp_no																							");
        query.append("		AND a.wkor_id = x.wkor_id	)																	woparam,			");
        query.append("       x.wkor_id 																						wkorId              ");
        query.append("       ,z.vendor_id         																			vendorId			");
        query.append("       ,SFAIDTODESC(z.vendor_id, '', 'VENDOR', z.comp_no)   											vendorDesc			");
        query.append("       ,z.tot_amt               																		totAmt				");
        query.append("       ,SFACODE_TO_DESC(z.self_vendor_type, 'SELF_VENDOR_TYPE', 'SYS', '', '"+user.getLangId()+"')	selfVendorTypeDesc  ");
        query.append("       ,(SELECT a.description                                                                                             ");
        query.append("          FROM TAPLANT a                                                                                                  ");
        query.append("         WHERE a.comp_no = y.comp_no                                                                                      ");
        query.append("           AND a.plant = y.plant)                                                                     AS plantDesc        ");
        query.append("      , (SELECT (SELECT b.description FROM TADEPT b WHERE b.comp_no = a.comp_no AND b.dept_id = a.usage_dept)             ");
        query.append("           FROM TAEQUIPMENT a                                                                                             ");
        query.append("          WHERE a.comp_no = x.comp_no                                                                                     ");
        query.append("            AND a.equip_id = x.equip_id)                                                              AS usageDeptDesc	");
        query.append("      , (SELECT a.item_no                                                      											");
        query.append("                  FROM TAEQUIPMENT a                                                                                      ");
        query.append("                  WHERE a.comp_no = x.comp_no                                                                             ");
        query.append("                   AND a.equip_id = (SELECT c.p_equip_id FROM TAEQUIPMENT c WHERE c.comp_no = a.comp_no AND c.equip_id = x.equip_id))		AS pequipNo			");
        query.append("      , (SELECT a.description                                                      										");
        query.append("                  FROM TAEQUIPMENT a                                                                                      ");
        query.append("                  WHERE a.comp_no = x.comp_no                                                                             ");
        query.append("                   AND a.equip_id = (SELECT c.p_equip_id FROM TAEQUIPMENT c WHERE c.comp_no = a.comp_no AND c.equip_id = x.equip_id))     AS pequipDesc		");
        query.append("      , (SELECT (SELECT b.description FROM TADEPT b WHERE b.comp_no = a.comp_no AND b.dept_id = a.usage_dept)             ");
        query.append("                  FROM TAEQUIPMENT a                                                                                      ");
        query.append("                  WHERE a.comp_no = x.comp_no                                                                             ");
        query.append("                   AND a.equip_id = (SELECT c.p_equip_id FROM TAEQUIPMENT c WHERE c.comp_no = a.comp_no AND c.equip_id = x.equip_id))     AS pequipUsaDeptDesc		");
        query.append("      ,x.remark                                                                                        remark             ");
        query.append("    FROM TAPMSCHED x INNER JOIN TAPMLST y																				    ");
        query.append("    ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id																	    ");
        query.append("    INNER JOIN TAPMEQUIP yy                                                                                               ");
        query.append("    ON yy.pm_id = y.pm_id AND yy.comp_no = y.comp_no AND yy.pmequip_id = x.pmequip_id                                     ");
        query.append("    LEFT OUTER JOIN taworkorder z																						    ");
        query.append("    ON x.comp_no = z.comp_no AND x.wkor_id = z.wkor_id                                                        		    ");
        query.append("    WHERE 1=1                                    																		    ");
        query.append(this.getWhere(maWoSchedCommonDTO, user));
        query.getOrderByQuery("x.sched_date, y.description", maWoSchedCommonDTO.getOrderBy(), maWoSchedCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoSchedCommonDTO.getIsLoadMaxCount(), maWoSchedCommonDTO.getFirstRow()));
    }
    

    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaWoSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param day
     * @param status
     * @param compNo
     * @param deptId
     * @param deptDesc
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoSchedCommonDTO maWoSchedCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        query.getAndNumKeyQuery("x.pmequip_id", maWoSchedCommonDTO.getPmEquipId());
        
        query.getSysCdQuery("x.pmsched_status", maWoSchedCommonDTO.getPmSchedStatus(), "", "PMSCHED_STATUS", user.getCompNo(), user.getLangId());
        
        //날짜 전체 검색(리스트조회 사용)
        query.getAndDateQuery("x.sched_date", maWoSchedCommonDTO.getFilterStartDate(), maWoSchedCommonDTO.getFilterEndDate());
        
        //예방점검기준 번호
        query.getAndQuery("y.pm_no", maWoSchedCommonDTO.getFilterPmNo());

    	//부서
    	query.getDeptLevelQuery("y.dept_id", maWoSchedCommonDTO.getFilterDeptId(), maWoSchedCommonDTO.getFilterDeptDesc(), maWoSchedCommonDTO.getCompNo());
    	
    	//설비
    	if(!"".equals(maWoSchedCommonDTO.getFilterEquipId())||!"".equals(maWoSchedCommonDTO.getFilterEquipDesc())){
    	    query.append("AND x.equip_id IN (SELECT a.equip_id                  ");
            query.append("                   FROM TAEQUIPMENT a                 ");
            query.append("                  WHERE 1 = 1                         ");
            query.getStringEqualQuery("a.is_deleted", "N");
            query.getStringEqualQuery("a.comp_no", user.getCompNo());
	    	query.getCodeLikeQuery("a.equip_id", "a.description||a.item_no", 
	    			maWoSchedCommonDTO.getFilterEquipId(), maWoSchedCommonDTO.getFilterEquipDesc());
	        query.append("									)					");
	    }
    	
    	//위치
    	if(!"".equals(maWoSchedCommonDTO.getFilterEqLocId())||!"".equals(maWoSchedCommonDTO.getFilterEqLocDesc())){
    	    query.append("AND x.equip_id IN (SELECT a.equip_id                  ");
            query.append("                   FROM TAEQUIPMENT a                 ");
            query.append("                  WHERE 1 = 1                         ");
            query.getStringEqualQuery("a.is_deleted", "N");
            query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getEqLocLevelQuery("a.eqloc_id", maWoSchedCommonDTO.getFilterEqLocId(), maWoSchedCommonDTO.getFilterEqLocDesc(), maWoSchedCommonDTO.getCompNo());
            query.append("									)					");
        }
    	
    	//종류
    	if(!"".equals(maWoSchedCommonDTO.getFilterEqCtgId())||!"".equals(maWoSchedCommonDTO.getFilterEqCtgDesc())){
    	    query.append("AND x.equip_id IN (SELECT a.equip_id                  ");
            query.append("                   FROM TAEQUIPMENT a                 ");
            query.append("                  WHERE 1 = 1                         ");
            query.getStringEqualQuery("a.is_deleted", "N");
            query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getEqCtgLevelQuery("a.eqctg_id", maWoSchedCommonDTO.getFilterEqCtgId(), maWoSchedCommonDTO.getFilterEqCtgDesc(), maWoSchedCommonDTO.getCompNo());
            query.append("									)					");
        }

    	//설비유형 
    	if(!"".equals(maWoSchedCommonDTO.getFilterEqCtgTypeId())||!"".equals(maWoSchedCommonDTO.getFilterEqCtgTypeDesc())){
    	    query.append("AND x.equip_id IN (SELECT a.equip_id                  ");
            query.append("                   FROM TAEQUIPMENT a                 ");
            query.append("                  WHERE 1 = 1                         ");
            query.getStringEqualQuery("a.is_deleted", "N");
            query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getSysCdQuery("a.eqctg_type", maWoSchedCommonDTO.getFilterEqCtgTypeId(), maWoSchedCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", maWoSchedCommonDTO.getCompNo(),user.getLangId());
            query.append("									)					");
        }
    	//법정설비여부
        if(!"".equals(maWoSchedCommonDTO.getFilterIsLawEq())){
            query.append("AND x.equip_id IN (SELECT a.equip_id                  ");
            query.append("                   FROM TAEQUIPMENT a                 ");
            query.append("                  WHERE 1 = 1                         ");
            query.getStringEqualQuery("a.is_deleted", "N");
            query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getLikeQuery("a.is_law_eq", maWoSchedCommonDTO.getFilterIsLawEq());
            query.append("									)					");
        }
        
      //담당자(정)
    	if(!"".equals(maWoSchedCommonDTO.getFilterMainMngId())||!"".equals(maWoSchedCommonDTO.getFilterMainMngName())){
    	    query.append("AND x.equip_id IN (SELECT a.equip_id                  ");
            query.append("                   FROM TAEQUIPMENT a                 ");
            query.append("                  WHERE 1 = 1                         ");
            query.getStringEqualQuery("a.is_deleted", "N");
            query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getCodeLikeQuery("a.main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = a.main_mng_id AND aa.comp_no='"+maWoSchedCommonDTO.getCompNo()+"')", 
        			maWoSchedCommonDTO.getFilterMainMngId(), maWoSchedCommonDTO.getFilterMainMngName());
            query.append("									)					");
        }
    	//담당자(부)
    	if(!"".equals(maWoSchedCommonDTO.getFilterSubMngId())||!"".equals(maWoSchedCommonDTO.getFilterSubMngName())){
    	    query.append("AND x.equip_id IN (SELECT a.equip_id                  ");
            query.append("                   FROM TAEQUIPMENT a                 ");
            query.append("                  WHERE 1 = 1                         ");
            query.getStringEqualQuery("a.is_deleted", "N");
            query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getCodeLikeQuery("a.sub_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = a.sub_mng_id AND aa.comp_no='"+maWoSchedCommonDTO.getCompNo()+"')", 
        			maWoSchedCommonDTO.getFilterSubMngId(), maWoSchedCommonDTO.getFilterSubMngName());
            query.append("									)					");
        }
    	
    	//작업형태
    	query.getSysCdQuery("y.pm_type", maWoSchedCommonDTO.getFilterPmTypeId(), maWoSchedCommonDTO.getFilterPmTypeDesc(), "y.wo_type||'_TYPE'", maWoSchedCommonDTO.getCompNo(),user.getLangId());
    	
    	//작업종류
    	query.getSysCdQuery("y.wo_type", maWoSchedCommonDTO.getFilterWoTypeId(), maWoSchedCommonDTO.getFilterWoTypeDesc(), "WO_TYPE", maWoSchedCommonDTO.getCompNo(),user.getLangId());
    	//작업그룹
    	query.getWkCtrLevelQuery("y.wkctr_id", maWoSchedCommonDTO.getFilterWkCtrId(), maWoSchedCommonDTO.getFilterWkCtrDesc(), maWoSchedCommonDTO.getCompNo());
    	//담당자
        query.getCodeLikeQuery("(SELECT emp_id FROM TAWORKORDER "
                              + "WHERE pm_id = x.pm_id AND wkor_id = x.wkor_id)"
                , "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = (SELECT emp_id FROM TAWORKORDER WHERE pm_id = x.pm_id AND wkor_id = x.wkor_id)"
                + "  AND a.comp_no='"+user.getCompNo()+"')", maWoSchedCommonDTO.getFilterEmpId(), maWoSchedCommonDTO.getFilterEmpDesc());
        
        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = y.plant )", 
        		maWoSchedCommonDTO.getFilterPlantId(), maWoSchedCommonDTO.getFilterPlantDesc());

        //최신버전의 설비의 작업만 보여줌.
        query.append("AND NOT EXISTS (SELECT a.equip_id							");
    	query.append("					 FROM  TAEQUIPMENT a            		");
    	query.append("					 WHERE 1=1                    			");
    	query.append("					 AND   a.equip_id = x.equip_id			");
    	query.getStringEqualQuery("a.comp_no", user.getCompNo());
    	query.getAndQuery("a.is_last_version", "N");
        query.append("									)						");
        
        //설비번호
        if(!"".equals(maWoSchedCommonDTO.getFilterEquipNo()))
        {
	        query.append("AND x.equip_id IN (SELECT a.equip_id  				");
	    	query.append("					FROM TAEQUIPMENT a					");
	    	query.append("					WHERE 1=1							");
	    	query.getStringEqualQuery("a.comp_no", user.getCompNo());
	    	query.getLikeQuery("a.item_no", maWoSchedCommonDTO.getFilterEquipNo());
	        query.append("									)					");
        }
        
        //설비사용부서
        if(!"".equals(maWoSchedCommonDTO.getFilterEqUsaDeptId()) || !"".equals(maWoSchedCommonDTO.getFilterEqUsaDeptDesc()))
        {
        	query.append("AND x.equip_id IN (SELECT a.equip_id  				");
        	query.append("					FROM TAEQUIPMENT a					");
        	query.append("					WHERE 1=1							");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getDeptLevelQuery("a.usage_dept", maWoSchedCommonDTO.getFilterEqUsaDeptId(), maWoSchedCommonDTO.getFilterEqUsaDeptDesc(), user.getCompNo());
        	query.append("									)					");
        }
        
        //상위설비명
        if(!"".equals(maWoSchedCommonDTO.getFilterPequipId()) || !"".equals(maWoSchedCommonDTO.getFilterPequipDesc()))
        {
        	query.append(" AND (SELECT c.p_equip_id FROM TAEQUIPMENT c WHERE c.comp_no = x.comp_no AND c.equip_id = x.equip_id) IN  						");
        	query.append("					(SELECT a.equip_id                  ");
        	query.append("                    FROM TAEQUIPMENT a        		");
        	query.append("                    WHERE a.comp_no = x.comp_no		");
        	query.getCodeLikeQuery("a.equip_id", "a.description", maWoSchedCommonDTO.getFilterPequipId(), maWoSchedCommonDTO.getFilterPequipDesc());
        	query.append("                 )                    				");
        }
        
        //상위설비사용부서
        if(!"".equals(maWoSchedCommonDTO.getFilterPEqUsaDeptId()) || !"".equals(maWoSchedCommonDTO.getFilterPEqUsaDeptDesc()))
        {
        	query.append(" AND x.equip_id IN  											");
        	query.append("(SELECT a.equip_id                  							");
        	query.append("   FROM TAEQUIPMENT a        									");
        	query.append("  WHERE a.comp_no = x.comp_no									");
        	query.append("    AND a.p_equip_id IN (SELECT c.equip_id FROM TAEQUIPMENT c ");
        	query.append("                          WHERE c.comp_no = a.comp_no         ");
        	query.append("                            AND c.usage_dept IN ( SELECT d.dept_id FROM TADEPT d WHERE d.comp_no = c.comp_no  		");
        	query.getDeptLevelQuery("d.dept_id", maWoSchedCommonDTO.getFilterPEqUsaDeptId(), maWoSchedCommonDTO.getFilterPEqUsaDeptDesc(), user.getCompNo());
        	query.append("                                         		   )      		");
        	query.append("                   	  )                              		");
        	query.append("       )														");
        }
                
        return query.toString();
    }



	@Override
	public String findTotalCount(MaWoSchedCommonDTO maWoSchedCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                   											");
        query.append("       COUNT(1)                               					");
        query.append("FROM TAPMSCHED x INNER JOIN TAPMLST y                             ");
        query.append("ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id                    ");
        query.append("INNER JOIN TAPMEQUIP yy                                           ");
        query.append("ON yy.pm_id = y.pm_id AND yy.comp_no = y.comp_no and yy.pmequip_id = x.pmequip_id ");
        query.append("LEFT OUTER JOIN taworkorder z                                     ");
        query.append("ON x.comp_no = z.comp_no AND x.wkor_id = z.wkor_id                ");
        query.append("WHERE 1=1                                                         ");
        query.append(this.getWhere(maWoSchedCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
	
    @Override
    public int[] updateDeleteTag(final List<MaWoSchedDetailDTO> list, final User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");
        
        query.append("UPDATE TAPMSCHED  SET     ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?     ");
        query.append("WHERE COMP_NO = ?              ");
        query.append("  AND PMSCHED_ID =  ?      ");
        query.append("  AND PMSCHED_STATUS != 'C'      ");
        
        
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
                MaWoSchedDetailDTO maWoSchedDetailDTO = list.get(i);

                Object[] objects = new Object[]{
                        user.getEmpId()
                        ,deleteTime
                        ,user.getCompNo()
                        ,maWoSchedDetailDTO.getPmSchedId()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
}