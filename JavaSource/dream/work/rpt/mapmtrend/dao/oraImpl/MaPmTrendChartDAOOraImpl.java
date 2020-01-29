package dream.work.rpt.mapmtrend.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.mapmtrend.dao.MaPmTrendChartDAO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendChartDTO;

/**
 * 예방점검경향분석 DAO
 * @author  kim21017
 * @version $Id: MaPmTrendChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPmTrendChartDAOTarget"
 * @spring.txbn id="maPmTrendChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmTrendChartDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPmTrendChartDAO
{
    /**
     * 예방점검경향분석 find grid
     * @author  kim21017
     * @version $Id: MaPmTrendChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmTrendChartDTO
     * @return List
     */
    public List findPmList(MaPmTrendChartDTO maPmTrendChartDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT  															");
        query.append("		'' 									AS seqNo				");
        query.append("      ,y.pm_type              			AS pmType               ");
        query.append("      ,y.description          			AS pmInsDesc            ");
        query.append("		,x.pm_point_id 						AS pmPointId			");
        query.append("		,(SELECT c.full_desc										");
        query.append("		  FROM TAEQLOC c											");
        query.append("		  WHERE x.comp_no = c.comp_no								");
        query.append("			AND b.eqloc_id = c.eqloc_id) 	AS eqLocDesc			");
        query.append("		,(SELECT c.full_desc										");
        query.append("		  FROM TAEQCTG c											");
        query.append("		  WHERE x.comp_no = c.comp_no								");
        query.append("			 AND b.eqctg_id = c.eqctg_id) 	AS eqCtgDesc			");
        query.append("		,b.description		 				AS equipDesc			");
        query.append("		,b.item_no		 					AS equipNo				");
        query.append("		,(SELECT description FROM TAEQASMB WHERE comp_no = x.comp_no AND eqasmb_id = x.eqasmb_id)	");
        query.append("		  || '/'													");
        query.append("		  || x.check_point 					AS asmbPoint			");
        query.append("		,x.check_min||' / '||x.check_basis_val||' / '||x.check_max	AS checkVal	");
        query.append("		,x.uom 								AS uom					");
        query.append("      ,y.emp_id       					AS empId                ");
        query.append("      ,(SELECT emp_name FROM TAEMP WHERE emp_id = y.emp_id)	AS empDesc		");
        query.append("      ,b.equip_id    						AS equipId  			");
        query.append("FROM TAPMPOINT x INNER JOIN TAPMLST y                             ");
        query.append("ON x.comp_no = y.comp_no                                          ");
        query.append(" AND x.pm_id = y.pm_id                                           	");
        query.append("	INNER JOIN TAPMEQUIP a                                          ");
        query.append("	ON y.comp_no = a.comp_no                                        ");
        query.append("	 AND y.pm_id = a.pm_id                                          ");
        query.append("		INNER JOIN TAEQUIPMENT b                                    ");
        query.append("		ON a.comp_no = b.comp_no                                    ");
        query.append("		 AND a.equip_id = b.equip_id                                ");
        query.append("WHERE y.pm_type in ('INS','RINS','DINS')        					");

        query.append(this.getWhere(maPmTrendChartDTO, user));
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 예방점검경향분석 find chartwfww
     * @author  kim21017
     * @version $Id: MaPmTrendChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmTrendChartDTO
     * @return List
     */
    public List findPmChart(MaPmTrendChartDTO maPmTrendChartDTO)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT ROW_NUMBER() OVER(ORDER BY y.end_date) AS ID	");
        query.append("		,TO_CHAR(TO_DATE(y.end_date,'yyyymmdd'),'mm/dd') days");
        query.append("		,x.result_value cnt								");
        query.append("		,z.check_min minval								");
        query.append("		,z.check_max maxval								");

        if("RINS".equals(maPmTrendChartDTO.getPmType()))
        {
            query.append("FROM TAPMINSPOINT x, TAPMINSLIST y, TAPMPOINT z   ");
            query.append("WHERE x.comp_no = y.comp_no                       ");
            query.append("AND y.comp_no = z.comp_no                         ");
            query.append("AND x.pm_point_id = z.pm_point_id                 ");
            query.append("AND x.pminslist_id = y.pminslist_id               ");
            //query.append("AND y.pmsched_status = 'C'                        ");
        }
        else if("DINS".equals(maPmTrendChartDTO.getPmType()))
        {
            query.append("FROM TAPMINSDPOINT x, TAPMINSDLIST y, TAPMPOINT z ");
            query.append("WHERE x.comp_no = y.comp_no                       ");
            query.append("AND y.comp_no = z.comp_no                         ");
            query.append("AND x.pm_point_id = z.pm_point_id                 ");
            query.append("AND x.pminsdlist_id = y.pminsdlist_id             ");
            //query.append("AND y.pmsched_status = 'C'                        ");
        }
        else// ("INS".equals(maPmTrendChartDTO.getPmType()))
        {
            query.append("FROM TAWOPOINT x, TAWORKORDER y , TAPMPOINT z     ");
            query.append("WHERE x.comp_no = y.comp_no                       ");
            query.append("  AND y.comp_no = z.comp_no                       ");
            query.append("  AND x.wkor_id = y.wkor_id                       ");
            query.append("  AND x.pm_point_id = z.pm_point_id               ");
        }
        query.getAndQuery("x.pm_point_id", maPmTrendChartDTO.getPmPointId());
        query.getAndDateQuery("y.end_date", maPmTrendChartDTO.getFilterStartDate(), maPmTrendChartDTO.getFilterEndDate());
        query.getAndQuery("x.comp_no", maPmTrendChartDTO.getCompNo());
        query.append("ORDER BY y.end_date									");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaPmTrendChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmTrendChartDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPmTrendChartDTO maPmTrendChartDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", maPmTrendChartDTO.getCompNo());
        query.getAndQuery("x.check_type", "VAL");
        query.getLikeQuery("b.item_no", maPmTrendChartDTO.getFilterEquipNo());
        
        query.getLikeQuery("b.description", maPmTrendChartDTO.getFilterEquipDesc());
        
        query.getEqLocLevelQuery("b.eqloc_id", maPmTrendChartDTO.getFilterEqLocId(), maPmTrendChartDTO.getFilterEqLocDesc(), maPmTrendChartDTO.getCompNo());
        
        query.getEqCtgLevelQuery("b.eqctg_id", maPmTrendChartDTO.getFilterEqCtgId(), maPmTrendChartDTO.getFilterEqCtgDesc(), maPmTrendChartDTO.getCompNo());
        
        query.getSysCdQuery("b.plf_type", maPmTrendChartDTO.getFilterPlfTypeId(), maPmTrendChartDTO.getFilterPlfTypeDesc(), "PLF_TYPE", maPmTrendChartDTO.getCompNo(),user.getLangId());
        
        query.getLikeQuery("b.is_law_eq", maPmTrendChartDTO.getFilterIsLawEq());
        
        query.getLikeQuery("b.maker", maPmTrendChartDTO.getFilterMaker());
        
        query.getLikeQuery("b.model_no", maPmTrendChartDTO.getFilterModelNo());
        
        query.getCodeLikeQuery("b.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = b.main_mng_id AND a.comp_no=b.comp_no)", 
                maPmTrendChartDTO.getFilterMainMngId(), maPmTrendChartDTO.getFilterMainMngName());
        
        query.getCodeLikeQuery("b.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = b.sub_mng_id AND a.comp_no=b.comp_no)", 
                maPmTrendChartDTO.getFilterSubMngId(), maPmTrendChartDTO.getFilterSubMngName());
        
        query.getDeptLevelQuery("y.dept_id", maPmTrendChartDTO.getFilterDeptId(),maPmTrendChartDTO.getFilterDeptDesc(), maPmTrendChartDTO.getCompNo());
        //담당자
        query.getCodeLikeQuery("y.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.emp_id AND a.comp_no='"+maPmTrendChartDTO.getCompNo()+"')", 
                maPmTrendChartDTO.getFilterEmpId(), maPmTrendChartDTO.getFilterEmpDesc());

        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maPmTrendChartDTO.getFilterPlantId(), maPmTrendChartDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    public int createWo(MaPmTrendChartDTO maPmTrendChartDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT SQAWKOR_ID.nextval ");
        query.append("FROM DUAL                 ");
        
        String wkorId = QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
        
        query = new QueryBuffer();
        int rtnValue  = 0;
        query.append("INSERT INTO TAWORKORDER(                          ");
        query.append("        comp_no     , wkor_id       , wo_no       ");
        query.append("      , wkor_date   , description   , wo_status   ");
        query.append("      , wo_type     , pm_type       , dept_id     ");
        query.append("      , emp_id      , wo_gen_type )               ");

        query.append("SELECT                                                                                                        ");
        query.append("  x.comp_no,                  '"+wkorId+"',           '"+wkorId+"',                                           ");
        query.append("  TO_CHAR(sysdate,'yyyymmdd') , x.description         , 'P'                                                   ");
        query.append(" ,'"+maPmTrendChartDTO.getSelectedWoType()+"',    '"+maPmTrendChartDTO.getSelectedPmType()+"'      , '"+loginUser.getDeptId()+"' ");
        query.append(" , '"+loginUser.getEmpId()+"'          ,'WOPOINT'                                                                                ");
        query.append("FROM (                                                                                                        ");
        
        if("INS".equals(maPmTrendChartDTO.getPmType()))
        {
        query.append("SELECT                                                                                                        ");
        query.append("     v.comp_no                                                                                                ");
        query.append("     ,(SELECT b.description                                                               					");
        query.append("       FROM TAWOEQUIP a INNER JOIN TAEQUIPMENT b                                                              ");
        query.append("       ON a.comp_no = b.comp_no                                                                         		");
        query.append("         AND a.equip_id = b.equip_id                                                                     		");
        query.append("       WHERE a.wkor_id = v.wkor_id                                                                       		");
        query.append("         AND a.comp_no = v.comp_no)||' '||                                                            		");
        query.append("      (SELECT a.description FROM TAEQASMB a WHERE a.comp_no = v.comp_no AND a.eqasmb_id =                     ");
        query.append("      (SELECT b.eqasmb_id FROM TAPMPOINT b WHERE b.comp_no='"+loginUser.getCompNo()+"' AND b.pm_point_id = v.pm_point_id))||' '||       ");
        query.append("      (SELECT c.check_point FROM TAPMPOINT c WHERE c.comp_no='"+loginUser.getCompNo()+"' AND c.pm_point_id = v.pm_point_id) AS description      ");
        query.append("      , v.pm_point_id                                                                                         ");
        query.append("FROM TAWOPOINT v                                                                                              ");
        query.append("WHERE  1=1                                                                                                    ");
        query.append("ORDER BY LENGTH(description) DESC                                                                             ");
        query.append(") x                                                                                                           ");
        query.append("WHERE ROWNUM = 1                                                                                              ");

        }
        else if("RINS".equals(maPmTrendChartDTO.getPmType()))
        {
            
        query.append("SELECT        ");
        query.append("     v.comp_no            ");
        query.append("    ,(SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = v.equip_id) ||' '||                          ");
        query.append("      (SELECT a.description FROM TAEQASMB a WHERE a.comp_no = v.comp_no AND a.eqasmb_id =                     ");
        query.append("      (SELECT b.eqasmb_id FROM TAPMPOINT b WHERE b.comp_no='"+loginUser.getCompNo()+"' AND b.pm_point_id = v.pm_point_id))||' '||           ");
        query.append("      (SELECT c.check_point FROM TAPMPOINT c WHERE c.comp_no='"+loginUser.getCompNo()+"' AND c.pm_point_id = v.pm_point_id) AS description  ");
        query.append("    , v.pm_point_id                                                                                           ");
        query.append("FROM TAPMINSPOINT v                                                                                           ");
        query.append("WHERE 1=1                                                                                                     ");
        query.append("ORDER BY LENGTH(description) DESC                                                                             ");
        query.append(") x                                                                                                           ");
        query.append("WHERE ROWNUM = 1                                                                                              ");
        
        }
        
        else if("DINS".equals(maPmTrendChartDTO.getPmType()))
        {
            
        query.append("SELECT                                                                                                        ");
        query.append("     v.comp_no                                                                                                ");
        query.append("    ,(SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = v.equip_id) ||' '||                          ");
        query.append("      (SELECT a.description FROM TAEQASMB a WHERE a.comp_no = v.comp_no AND a.eqasmb_id =                     ");
        query.append("      (SELECT b.eqasmb_id FROM TAPMPOINT b WHERE b.comp_no='"+loginUser.getCompNo()+"' AND b.pm_point_id = v.pm_point_id))||' '||           ");
        query.append("      (SELECT c.check_point FROM TAPMPOINT c WHERE c.comp_no='"+loginUser.getCompNo()+"' AND c.pm_point_id = v.pm_point_id) AS description  ");
        query.append("    , v.pm_point_id                                                                                           ");
        query.append("FROM TAPMINSDPOINT v                                                                                          ");
        query.append("WHERE 1=1                                                                                                     ");
        query.append("ORDER BY LENGTH(description) DESC                                                                             ");
        query.append(") x                                                                                                           ");
        query.append("WHERE ROWNUM = 1                                                                                              ");
             
        }
        
        query.getAndQuery("x.pm_point_id", maPmTrendChartDTO.getPmPointId());
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }

    @Override
    public String findTotalCount(MaPmTrendChartDTO maPmTrendChartDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                           					");
        query.append("       COUNT(1)                                   				");
        query.append("FROM TAPMPOINT x INNER JOIN TAPMLST y                             ");
        query.append("ON x.comp_no = y.comp_no                                          ");
        query.append(" AND x.pm_id = y.pm_id                                           	");
        query.append("	INNER JOIN TAPMEQUIP a                                          ");
        query.append("	ON y.comp_no = a.comp_no                                        ");
        query.append("	 AND y.pm_id = a.pm_id                                          ");
        query.append("		INNER JOIN TAEQUIPMENT b                                    ");
        query.append("		ON a.comp_no = b.comp_no                                    ");
        query.append("		 AND a.equip_id = b.equip_id                                ");
        query.append("WHERE y.pm_type in ('INS','RINS','DINS')        					");
        query.append(this.getWhere(maPmTrendChartDTO, loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}