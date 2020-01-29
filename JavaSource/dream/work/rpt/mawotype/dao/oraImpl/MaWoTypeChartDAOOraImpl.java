package dream.work.rpt.mawotype.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.mawotype.dao.MaWoTypeChartDAO;
import dream.work.rpt.mawotype.dto.MaWoTypeChartDTO;

/**
 * 작업유형별현황 DAO
 * @author  kim21017
 * @version $Id: MaWoTypeChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoTypeChartDAOTarget"
 * @spring.txbn id="maWoTypeChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoTypeChartDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoTypeChartDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoTypeChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoTypeChartDTO
     * @return List
     */
    public List findList(MaWoTypeChartDTO maWoTypeChartDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT '' seqNo						");
        query.append("		 ,x.cdsysd_no woType			");
        query.append("		 ,MAX(x.description) woTypeDesc	");
        query.append("		 ,NVL(COUNT(y.wkor_id),0) woCnt	");
        query.append("		 ,NVL(ROUND(COUNT(y.wkor_id)/	");
        query.append("			(SELECT count(1) 			");
        query.append("			FROM TAWORKORDER y			");
        query.append("			WHERE 1=1					");
        query.append(this.getWhere(maWoTypeChartDTO));
        query.append("			) * 100,2),0) woCntRate		");
        query.append("		 ,NVL(SUM(y.work_time),0) woTime");
        query.append("		 ,NVL(ROUND(SUM(y.work_time)/	");
        query.append("			(SELECT sum(work_time) 		");
        query.append("			FROM TAWORKORDER y			");
        query.append("			WHERE 1=1					");
        query.append(this.getWhere(maWoTypeChartDTO));
        query.append("			) * 100,2),0) woTimeRate	");
        query.append("FROM TACDSYSD x, TAWORKORDER y		");
        query.append("WHERE x.cdsysd_no = y.wo_type			");
        query.getAndQuery("x.list_type", "WO_TYPE");
        query.append(this.getWhere(maWoTypeChartDTO));
        query.append("GROUP BY x.cdsysd_no					");
        query.append("ORDER bY x.cdsysd_no					");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * wo cnt  find
     * @author  kim21017
     * @version $Id: MaWoTypeChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoTypeChartDTO
     * @return List
     */
    public List findWoCntChart(MaWoTypeChartDTO maWoTypeChartDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT 								");
        query.append("		 MAX(x.description) woType		");
        query.append("		 ,NVL(ROUND(COUNT(y.wkor_id)/	");
        query.append("			(SELECT count(1) 			");
        query.append("			FROM TAWORKORDER y			");
        query.append("			WHERE 1=1					");
        query.append(this.getWhere(maWoTypeChartDTO));
        query.append("			) * 100,2),0) rate			");
//        query.append("		 ,'#'||TO_CHAR(ROUND(DBMS_RANDOM.VALUE(1, 255),0),'fmXXX')||	");
//        query.append("		 TO_CHAR(ROUND(DBMS_RANDOM.VALUE(1, 255),0),'fmXXX')||			");
//        query.append("		 TO_CHAR(ROUND(DBMS_RANDOM.VALUE(1, 255),0),'fmXXX') color		");
        query.append("FROM TACDSYSD x, TAWORKORDER y		");
        query.append("WHERE x.cdsysd_no = y.wo_type			");
        query.getAndQuery("x.list_type", "WO_TYPE");
        query.append(this.getWhere(maWoTypeChartDTO));
        query.append("GROUP BY x.cdsysd_no					");
        query.append("ORDER bY x.cdsysd_no					");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * wo time  find
     * @author  kim21017
     * @version $Id: MaWoTypeChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoTypeChartDTO
     * @return List
     */
    public List findWoTimeChart(MaWoTypeChartDTO maWoTypeChartDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 								");
        query.append("		 MAX(x.description) woType		");
        query.append("		 ,NVL(ROUND(SUM(y.work_time)/	");
        query.append("			(SELECT sum(work_time) 		");
        query.append("			FROM TAWORKORDER y			");
        query.append("			WHERE 1=1					");
        query.append(this.getWhere(maWoTypeChartDTO));
        query.append("			) * 100,2),0) rate			");
//        query.append("		 ,'#'||TO_CHAR(ROUND(DBMS_RANDOM.VALUE(1, 255),0),'fmXXX')||	");
//        query.append("		 TO_CHAR(ROUND(DBMS_RANDOM.VALUE(1, 255),0),'fmXXX')||			");
//        query.append("		 TO_CHAR(ROUND(DBMS_RANDOM.VALUE(1, 255),0),'fmXXX') color		");
        query.append("FROM TACDSYSD x, TAWORKORDER y		");
        query.append("WHERE x.cdsysd_no = y.wo_type			");
        query.getAndQuery("x.list_type", "WO_TYPE");
        query.append(this.getWhere(maWoTypeChartDTO));
        query.append("GROUP BY x.cdsysd_no					");
        query.append("ORDER bY x.cdsysd_no					");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaWoTypeChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoTypeChartDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoTypeChartDTO maWoTypeChartDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("y.comp_no", maWoTypeChartDTO.getCompNo());
        query.getAndDateQuery("y.wkor_date", maWoTypeChartDTO.getFilterStartDate(), maWoTypeChartDTO.getFilterEndDate());
       // 공장 
        if(!"".equals(maWoTypeChartDTO.getFilterPlantId())||!"".equals(maWoTypeChartDTO.getFilterPlantDesc())){
        	
        	query.append("AND y.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.append("									AND eqloc_id IN (	");
        	query.append("									SELECT eqloc_id	");
        	query.append("									FROM TAEQLOC		");
        	query.append("									WHERE 1=1			");
        	query.getPlantCdQuery("plant", maWoTypeChartDTO.getFilterPlantId(), maWoTypeChartDTO.getFilterPlantDesc(), maWoTypeChartDTO.getCompNo());
            query.append("									)))					");
        
        }
        //위치
        if(!"".equals(maWoTypeChartDTO.getFilterEqLocId())||!"".equals(maWoTypeChartDTO.getFilterEqLocDesc())){
        	query.append("AND y.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqLocLevelQuery("eqloc_id", maWoTypeChartDTO.getFilterEqLocId(), maWoTypeChartDTO.getFilterEqLocDesc(), maWoTypeChartDTO.getCompNo());
            query.append("									))					");
        }
        //종류
        if(!"".equals(maWoTypeChartDTO.getFilterEqCtgId())||!"".equals(maWoTypeChartDTO.getFilterEqCtgDesc())){
        	query.append("AND y.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqCtgLevelQuery("eqctg_id", maWoTypeChartDTO.getFilterEqCtgId(), maWoTypeChartDTO.getFilterEqCtgDesc(), maWoTypeChartDTO.getCompNo());
            query.append("									))					");
        }
        return query.toString();
    }
}