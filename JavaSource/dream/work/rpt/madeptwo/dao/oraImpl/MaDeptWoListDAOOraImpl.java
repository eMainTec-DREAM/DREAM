package dream.work.rpt.madeptwo.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class MaDeptWoListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaDeptWoListDAO
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT '' AS seqNo														");
        query.append("		,x.dept_id AS id													");
        query.append("		,x.dept_id AS deptId												");
        query.append("		,x.p_dept_id AS pDeptId												");
        query.append("		,x.description AS deptDesc											");
        query.append("		,NVL(y.totalCnt,0) AS totalCnt										");
        query.append("		,NVL(y.bmCnt,0) AS bmCnt											");
        query.append("		,NVL(y.pmCnt,0) AS pmCnt											");
        query.append("		,NVL(y.cmCnt,0) AS cmCnt											");
        query.append("		,NVL(z.totalTime,0) AS totalTime									");
        query.append("		,NVL(z.bmTime,0) AS bmTime											");
        query.append("		,NVL(z.pmTime,0) AS pmTime											");
        query.append("		,NVL(z.cmTime,0) AS cmTime											");
        query.append("		,level			AS lvl												");
        query.append("		,MIN(LEVEL) OVER() AS minLvl										");
        query.append("FROM TADEPT x,(select  x.dept_id AS deptId								");
        query.append("			,COUNT(x.dept_id) AS totalCnt									");
        query.append("			,SUM(DECODE(x.wo_type,'BM',1,0)) AS bmCnt						");
        query.append("			,SUM(DECODE(substr(x.wo_type,0,2),'PM',1,0)) AS pmCnt			");
        query.append("			,SUM(DECODE(x.wo_type,'CM',1,0)) AS cmCnt						");
        query.append("		FROM TAWORKORDER x													");
        query.append("		WHERE 1=1															");
        query.append("		AND x.dept_id is not null											");
        query.append("		AND (x.wo_type IN('BM','CM') OR x.wo_type LIKE 'PM%')				");
        query.append(this.getWhere(maDeptWoListDTO, user));
        query.append("		GROUP BY x.comp_no, x.dept_id) y									");
        query.append("		,(SELECT  x.dept_id AS deptId										");
        query.append("				,SUM(x.work_time) AS totalTime								");
        query.append("				,SUM(DECODE(x.wo_type,'BM',x.work_time,0)) AS bmTime		");
        query.append("				,SUM(DECODE(substr(x.wo_type,0,2),'PM',x.work_time,0)) AS pmTime		");
        query.append("				,SUM(DECODE(x.wo_type,'CM',x.work_time,0)) AS cmTime		");
        query.append("			FROM TAWORKORDER x												");
        query.append("			WHERE 1=1														");
        query.append("			AND x.dept_id is not null										");
        query.append("          AND (x.wo_type IN('BM','CM') OR x.wo_type LIKE 'PM%')           ");
        query.append(this.getWhere(maDeptWoListDTO, user));
        query.append("			GROUP BY x.comp_no, x.dept_id) z								");
        query.append("WHERE x.dept_id = y.deptId(+)												");
        query.append("AND x.dept_id = z.deptId(+)												");
    	query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        //부서
        query.getDeptLevelQuery("x.dept_id", maDeptWoListDTO.getFilterDeptId(), maDeptWoListDTO.getFilterDeptDesc(), maDeptWoListDTO.getCompNo());
        query.append("START WITH x.p_dept_id = 0												");
        query.append("CONNECT BY PRIOR x.dept_id = x.p_dept_id									");
		
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
    	QueryBuffer query = new QueryBuffer();
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
    	QueryBuffer query = new QueryBuffer();
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findTimeChart(MaDeptWoListDTO maDeptWoListDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
}