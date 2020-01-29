package dream.work.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.list.dao.WoPlanListDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;

/**
 * 작업계획목록 - 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="woPlanListDAOTarget"
 * @spring.txbn id="woPlanListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanListDAOOraImpl extends BaseJdbcDaoSupportOra implements WoPlanListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @return List
     */
    public List findWoResultMstrList(WoPlanCommonDTO woPlanCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                														");
        query.append("		 ''                                                  											AS ISDELCHECK			");
        query.append("     , ''                                                  											As SEQNO       			");
        query.append("     , x.wkor_id                                           											AS WKORID      			");
        query.append("     , x.wo_no                                             											AS WONO        			");
        query.append("     , x.description                                       											AS WKORDESC    			");
        query.append("     , x.wkor_date                                         											AS PLANDATE    			");
        query.append("     , (SELECT a.full_desc                                                													");
        query.append("       	FROM TAEQLOC a                                                  													");
        query.append("         WHERE a.comp_no  = x.comp_no                                     													");
        query.append("           AND a.eqloc_id = z.eqloc_id)                    											AS EQLOCDESC    		");
        query.append("     , z.item_no                                   													AS EQUIPNO   			");
        query.append("     , (CASE z.eqctg_type WHEN 'MD' THEN '('||z.old_eq_no||')'||z.description                    								");
        query.append("              			ELSE z.description	                                                								");
        query.append("         					 END)																		AS EQUIPDESC    		");
        query.append("	   , (SELECT a.description																									");
        query.append("	   		FROM TAEQASMB a																										");
        query.append("	   	   WHERE a.comp_no = x.comp_no																							");
        query.append("	   		 AND a.eqasmb_id = y.eqasmb_id)																AS EQASMBDESC			");
        query.append("	   , (SELECT a.description																									");
        query.append("	   		FROM TAPLANT a																										");
        query.append("	   	   WHERE a.comp_no = x.comp_no																							");
        query.append("	   		 AND a.plant   = x.plant)																	AS PLANTDESC			");
        query.append("     , (SELECT a.description                                                        											");
        query.append("          FROM TADEPT a                                                           											");
        query.append("         WHERE a.comp_no = x.comp_no                                                											");
        query.append("           AND a.dept_id = x.dept_id)                         										AS DEPTDESC     		");
        query.append("     , (SELECT a.description                                                        											");
        query.append("          FROM TAWKCTR a                                                           											");
        query.append("         WHERE a.comp_no  = x.comp_no                                                											");
        query.append("           AND a.wkctr_id = x.wkctr_id)                         										AS WKCTRDESC    		");
        query.append("     , (SELECT a.emp_name                                                        												");
        query.append("          FROM TAEMP a                                                                										");
        query.append("         WHERE a.comp_no = x.comp_no                                                											");
        query.append("           AND a.emp_id  = x.emp_id)                            	 									AS EMPDESC      		");
        query.append("     , (SELECT a.emp_name                                                        												");
        query.append("       	FROM TAEMP a                                                             											");
        query.append("         WHERE a.comp_no = x.comp_no                                               											");
        query.append("           AND a.emp_id  = x.sub_emp_id)                    											AS SUBEMPDESC      		");
        query.append("     , SFACODE_TO_DESC(x.shift_type, 'SHIFT_TYPE', 'SYS', '', '"+user.getLangId()+"')					AS SHIFTDESC    		");
        query.append("     , SFACODE_TO_DESC(x.wo_type, 'WO_TYPE', 'SYS', '', '"+user.getLangId()+"')         				AS WOTYPEDESC			");
        query.append("     , SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE', 'SYS', '', '"+user.getLangId()+"')  			AS PMTYPEDESC   		");
        query.append("     , SFACODE_TO_DESC(x.woplan_status, 'WOPLAN_STATUS', 'SYS', '', '"+user.getLangId()+"')    		AS WOPLANSTATUSDESC 	");
        query.append("     , SFACODE_TO_DESC(x.self_vendor_type, 'SELF_VENDOR_TYPE', 'SYS', '', '"+user.getLangId()+"')		AS SELFVENDORTYPEDESC	");
        query.append("     , SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)   											AS VENDORDESC    		");
        query.append("     , x.start_date   																				AS STARTDATE    		");
        query.append("     , x.start_time    																				AS STARTTIME    		");
        query.append("     , x.end_date   																					AS ENDDATE    			");
        query.append("     , x.end_time    																					AS ENDTIME    			");
        query.append("     , x.work_time                                         											AS WORKTIME        		");
        query.append("     , x.perform                                          											AS PERFORM            	");
        query.append("     , x.woplan_status 																				AS WOPLANSTATUS         ");
        query.append("     , x.wo_type                                            											AS WOTYPE            	");
        query.append("     , x.pm_type                                            											AS PMTYPE            	");
        query.append("     , x.wo_gen_type                                        											AS WOGENTYPE        	");
        query.append("     , (SELECT param1  																										");
        query.append("     		FROM TACDSYSD  																										");
        query.append("     	   WHERE list_type = x.wo_type ||'_type'   																				");
        query.append("     		 AND cdsysd_no = x.pm_type) 																AS PARAM   				");
        query.append("  FROM TAWOPLAN x INNER JOIN TAWOEQUIP y																						");
        query.append("					   ON x.comp_no = y.comp_no     																			");
        query.append("					  AND x.wkor_id = y.wkor_id      																			");
        query.append("					INNER JOIN TAEQUIPMENT z      																				");
        query.append("					   ON z.comp_no  = y.comp_no      																			");
        query.append("					  AND z.equip_id = y.equip_id      																			");
        query.append(" WHERE 1 = 1                     																								");
        query.append(this.getWhere(woPlanCommonDTO, user));
        query.getOrderByQuery("4 DESC", woPlanCommonDTO.getOrderBy(), woPlanCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(woPlanCommonDTO.getIsLoadMaxCount(), woPlanCommonDTO.getFirstRow()));
    }
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] updateDeleteTagWoPlanMstr(final List<WoPlanDetailDTO> list, final User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");
        
    	query.append("UPDATE TAWOPLAN  SET      						");
        query.append("		 IS_DELETED  = 'Y'							");
        query.append("     , DELETE_BY   = ?     						");
        query.append("     , DELETE_TIME = ?     						");
        query.append(" WHERE COMP_NO 	 = ?       						");
        query.append("   AND WKOR_ID 	 = ?       						");
        query.append("   AND WOPLAN_STATUS IN ('PPW', 'PPWDA', 'PPP')	");
        
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
                WoPlanDetailDTO maWoSchedDetailDTO = list.get(i);

                Object[] objects = new Object[]{
                        user.getEmpId()
                        ,deleteTime
                        ,user.getCompNo()
                        ,maWoSchedDetailDTO.getWkOrId()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
        });
    }
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WoPlanCommonDTO woPlanCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // 회사
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        // 작업계획 삭제 여부
        query.getStringEqualQuery("x.is_deleted", "N");
        // 설비 최신 여부
        query.getAndQuery("z.is_last_version", "Y");
        // 설비 삭제 여부
        query.getAndQuery("z.is_deleted", "N");
        // CommonDTO의 wkorId가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(woPlanCommonDTO.getWkOrId()))
        {
            query.getAndNumKeyQuery("x.wkor_id", woPlanCommonDTO.getWkOrId());
            
            return query.toString();
        }
        // 작업번호
        query.getLikeQuery("x.wo_no", woPlanCommonDTO.getFilterWoNo());
        // 작업명
        query.getLikeQuery("x.description", woPlanCommonDTO.getFilterWkOrDesc());
        // 작업일자
        query.getAndDateQuery("x.wkor_date", woPlanCommonDTO.getFilterStartDate(), woPlanCommonDTO.getFilterEndDate());
        // 설비
        query.getCodeLikeQuery("z.equip_id", "z.description||z.item_no", 
        		woPlanCommonDTO.getFilterEquipId(), woPlanCommonDTO.getFilterEquipDesc());
        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM TAPLANT a WHERE a.comp_no = '"+ user.getCompNo() +"' AND a.plant = x.plant)", 
        		woPlanCommonDTO.getFilterPlantId(), woPlanCommonDTO.getFilterPlantDesc());
        // 부서
        query.getDeptLevelQuery("x.dept_id", woPlanCommonDTO.getFilterDeptId(), woPlanCommonDTO.getFilterDeptDesc(), user.getCompNo());
        // 작업그룹
        query.getCodeLikeQuery("x.wkctr_id", "(SELECT a.description FROM TAWKCTR a WHERE a.comp_no = '"+ user.getCompNo() +"' AND a.wkctr_id = x.wkctr_id)", woPlanCommonDTO.getFilterWkCtrId(), woPlanCommonDTO.getFilterWkCtrDesc());
        // 담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no = '"+user.getCompNo()+"')", 
        		woPlanCommonDTO.getFilterEmpId(), woPlanCommonDTO.getFilterEmpDesc());
        // 부 담당자
        query.getCodeLikeQuery("x.sub_emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.sub_emp_id AND a.comp_no = '"+user.getCompNo()+"')", 
        		woPlanCommonDTO.getFilterSubEmpId(), woPlanCommonDTO.getFilterSubEmpDesc());
        // 설비위치
        query.getEqLocLevelQuery("z.eqloc_id", woPlanCommonDTO.getFilterEqLocId(), woPlanCommonDTO.getFilterEqLocDesc(), user.getCompNo());
        // 설비종류
        query.getEqCtgLevelQuery("z.eqctg_id", woPlanCommonDTO.getFilterEqCtgId(), woPlanCommonDTO.getFilterEqCtgDesc(), user.getCompNo());
        // 법정설비여부
        query.getAndQuery("(SELECT a.is_law_eq FROM TAEQUIPMENT a WHERE a.comp_no = '"+ user.getCompNo() +"' AND a.equip_id = y.equip_id)", woPlanCommonDTO.getFilterIsLawEq());
        // 관리자(정)
        query.getCodeLikeQuery("z.main_mng_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = z.main_mng_id AND a.comp_no = '"+user.getCompNo()+"')", 
        		woPlanCommonDTO.getFilterMainMngId(), woPlanCommonDTO.getFilterMainMngName());
        // 관리자(부)
        query.getCodeLikeQuery("z.sub_mng_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = z.sub_mng_id AND a.comp_no = '"+user.getCompNo()+"')", 
        		woPlanCommonDTO.getFilterSubMngId(), woPlanCommonDTO.getFilterSubMngName());
        // 작업종류
        query.getSysCdQuery("x.wo_type", woPlanCommonDTO.getFilterWoTypeId(), woPlanCommonDTO.getFilterWoTypeDesc(), "WO_TYPE", user.getCompNo() ,user.getLangId());
        // 작업형태
        query.getSysCdQuery("x.pm_type", woPlanCommonDTO.getFilterPmTypeId(), woPlanCommonDTO.getFilterPmTypeDesc(), "x.wo_type+'_TYPE'", user.getCompNo(), user.getLangId());
        // 상태
        query.getSysCdQuery("x.woplan_status", woPlanCommonDTO.getFilterWoPlanStatus(), woPlanCommonDTO.getFilterWoPlanStatusDesc(), "WOPLAN_STATUS", user.getCompNo(), user.getLangId());
        // 자가/외주구분
        query.getCodeLikeQuery("x.self_vendor_type", "dbo.SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+user.getLangId()+"')", woPlanCommonDTO.getSelfVendorType(), woPlanCommonDTO.getSelfVendorTypeDesc());
        // 거래처
        query.getCodeLikeQuery("x.vendor_id", "dbo.SFAIDTODESC(x.vendor_id, '', 'VENDOR', '"+ user.getCompNo() +"')", woPlanCommonDTO.getVendorId(), woPlanCommonDTO.getVendorDesc());
        // 예방작업번호
        query.getLikeQuery("(SELECT pm_no FROM TAPMLST a WHERE comp_no = '"+ user.getCompNo() +"' AND pm_id = x.pm_id)", woPlanCommonDTO.getFilterPmNo());
        // 교대조
        query.getSysCdQuery("x.shift_type", woPlanCommonDTO.getFilterShiftId(), woPlanCommonDTO.getFilterShiftDesc(), "SHIFT_TYPE", user.getCompNo(), user.getLangId());
        // 설비유형
        query.getSysCdQuery("z.eqctg_type", woPlanCommonDTO.getFilterEqCtgTypeId(), woPlanCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }

    public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, User user, String woType)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            				");
        query.append("       COUNT(1)                    				");
        query.append("  FROM TAWOPLAN x INNER JOIN TAWOEQUIP y			");
        query.append("					   ON x.comp_no = y.comp_no     ");
        query.append("					  AND x.wkor_id = y.wkor_id     ");
        query.append("					INNER JOIN TAEQUIPMENT z      	");
        query.append("					   ON z.comp_no  = y.comp_no    ");
        query.append("					  AND z.equip_id = y.equip_id	");
        query.append(" WHERE 1 = 1                     					");
        query.append(this.getWhere(woPlanCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}