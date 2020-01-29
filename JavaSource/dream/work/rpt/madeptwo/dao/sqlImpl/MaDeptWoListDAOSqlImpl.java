package dream.work.rpt.madeptwo.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.madeptwo.dao.MaDeptWoListDAO;
import dream.work.rpt.madeptwo.dto.MaDeptWoListDTO;

/**
 * 부서별작업분석DAO
 * @author  kim21017
 * @version $Id: MaDeptWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maDeptWoListDAOTarget"
 * @spring.txbn id="maDeptWoListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDeptWoListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaDeptWoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaDeptWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maDeptWoListDTO
     * @return List
     */
    public List findDeptWoList(MaDeptWoListDTO maDeptWoListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 																														");
        query.append("		 '' AS seqNo																											");
        query.append("	   , x.dept_id AS id																										");
        query.append("	   , x.dept_id AS deptId																									");
        query.append("	   , x.p_dept_id AS pDeptId																									");
        query.append("	   , x.description AS deptDesc																								");
        query.append("	   , ISNULL(y.totalCnt,0) AS totalCnt																						");
        query.append("	   , ISNULL(y.bmCnt,0) AS bmCnt																								");
        query.append("	   , ISNULL(y.pmCnt,0) AS pmCnt																								");
        query.append("	   , ISNULL(y.cmCnt,0) AS cmCnt																								");
        query.append("	   , ISNULL(z.totalTime,0) AS totalTime																						");
        query.append("	   , ISNULL(z.bmTime,0) AS bmTime																							");
        query.append("	   , ISNULL(z.pmTime,0) AS pmTime																							");
        query.append("	   , ISNULL(z.cmTime,0) AS cmTime																							");
        query.append("	   , d.lvl																													");
        query.append("	   , MIN(d.lvl) OVER() AS minLvl																							");
        query.append("  FROM TADEPT x INNER JOIN (SELECT * FROM dbo.SFADEPT_ALL('"+maDeptWoListDTO.getCompNo()+"','0')) d							");
        query.append("				     ON x.comp_no = d.comp_no																					");
        query.append("				    AND x.dept_id = d.dept_id																					");
        query.append("	 			  LEFT OUTER JOIN (SELECT x.dept_id AS deptId																	");
        query.append("								 		, COUNT(x.dept_id) AS totalCnt															");
        query.append("			 							, SUM(CASE x.wo_type WHEN 'BM' THEN 1 ELSE 0 END) AS bmCnt								");
        query.append("			 							, SUM(CASE SUBSTRING(x.wo_type,1,2) WHEN 'PM' THEN 1 ELSE 0 END) AS pmCnt				");
        query.append("			 							, SUM(CASE x.wo_type WHEN 'CM' THEN 1 ELSE 0 END) AS cmCnt								");
        query.append("									 FROM TAWORKORDER x																			");
        query.append("		 							WHERE 1=1																					");
        query.append("		 							  AND x.dept_id is not null																	");
        query.append("                                    AND (x.wo_type IN('BM','CM') OR x.wo_type LIKE 'PM%')                                     ");
        query.append(this.getWhere(maDeptWoListDTO, user));
        query.append("									GROUP BY x.comp_no, x.dept_id) y															");
        query.append("					ON x.dept_id = y.deptId																						");
        query.append("				  LEFT OUTER JOIN (SELECT x.dept_id AS deptId																	");
        query.append("										, SUM(x.work_time) AS totalTime															");
        query.append("										, SUM(CASE x.wo_type WHEN 'BM' THEN x.work_time ELSE 0 END) AS bmTime					");
        query.append("										, SUM(CASE SUBSTRING(x.wo_type,1,2) WHEN 'PM' THEN x.work_time ELSE 0 END) AS pmTime	");
        query.append("										, SUM(CASE x.wo_type WHEN 'CM' THEN x.work_time ELSE 0 END) AS cmTime					");
        query.append("									 FROM TAWORKORDER x																			");
        query.append("			 						WHERE 1=1																					");
        query.append("									  AND x.dept_id is not null																	");
        query.append("                                    AND (x.wo_type IN('BM','CM') OR x.wo_type LIKE 'PM%')                                     ");
        query.append(this.getWhere(maDeptWoListDTO, user));
        query.append("									GROUP BY x.comp_no, x.dept_id) z															");
        query.append("					ON x.dept_id = z.deptId																						");
        query.append(" WHERE 1=1																													");
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.is_use", "Y");
        //부서
        query.getDeptLevelQuery("x.dept_id", maDeptWoListDTO.getFilterDeptId(), maDeptWoListDTO.getFilterDeptDesc(), maDeptWoListDTO.getCompNo());

		
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaDeptWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maDeptWoListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaDeptWoListDTO maDeptWoListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maDeptWoListDTO.getCompNo();
    	//회사코드
    	query.getAndQuery("x.comp_no", compNo);
    	//상태- 완료
    	query.getAndQuery("x.wo_status", "C");
    	//작업일자
		query.getAndDateQuery("x.start_date", maDeptWoListDTO.getFilterStartDate(), maDeptWoListDTO.getFilterEndDate());
		//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maDeptWoListDTO.getFilterPlantId(), maDeptWoListDTO.getFilterPlantDesc());
		
        if(!"".equals(maDeptWoListDTO.getFilterEquipId())||!"".equals(maDeptWoListDTO.getFilterEquipDesc()) || !"".equals(maDeptWoListDTO.getFilterEqLocId())||!"".equals(maDeptWoListDTO.getFilterEqLocDesc()) || !"".equals(maDeptWoListDTO.getFilterEqCtgId())||!"".equals(maDeptWoListDTO.getFilterEqCtgDesc())|| !"".equals(maDeptWoListDTO.getFilterPlfTypeId())||!"".equals(maDeptWoListDTO.getFilterPlfTypeDesc())||!"".equals(maDeptWoListDTO.getFilterIsLawEq())||!"".equals(maDeptWoListDTO.getFilterMainMngId())||!"".equals(maDeptWoListDTO.getFilterMainMngName())||!"".equals(maDeptWoListDTO.getFilterSubMngId())||!"".equals(maDeptWoListDTO.getFilterSubMngName())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("equip_id", "description+item_no", maDeptWoListDTO.getFilterEquipId(), maDeptWoListDTO.getFilterEquipDesc());
        	query.getEqLocLevelQuery("eqloc_id", maDeptWoListDTO.getFilterEqLocId(), maDeptWoListDTO.getFilterEqLocDesc(), compNo);
        	query.getEqCtgLevelQuery("eqctg_id", maDeptWoListDTO.getFilterEqCtgId(), maDeptWoListDTO.getFilterEqCtgDesc(), compNo);
        	query.getSysCdQuery("plf_type", maDeptWoListDTO.getFilterPlfTypeId(), maDeptWoListDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo,user.getLangId());
        	query.getLikeQuery("is_law_eq", maDeptWoListDTO.getFilterIsLawEq());
        	query.getCodeLikeQuery("main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = main_mng_id AND a.comp_no='"+compNo+"')", 
            		maDeptWoListDTO.getFilterMainMngId(), maDeptWoListDTO.getFilterMainMngName());
        	query.getCodeLikeQuery("sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = sub_mng_id AND a.comp_no='"+compNo+"')", 
            		maDeptWoListDTO.getFilterSubMngId(), maDeptWoListDTO.getFilterSubMngName());
        	query.append("									))					");
        }
    	
    	return query.toString();
    }
    
    public List findCntChart(MaDeptWoListDTO maDeptWoListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findTimeChart(MaDeptWoListDTO maDeptWoListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
}