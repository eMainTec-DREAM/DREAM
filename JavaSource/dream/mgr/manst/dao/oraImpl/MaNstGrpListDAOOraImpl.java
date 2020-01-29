package dream.mgr.manst.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.manst.dao.MaNstGrpListDAO;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;

/**
 * 무정지대표라인 - 목록 
 * @author  kim21017
 * @version $Id: MaNstGrpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maNstGrpListDAOTarget"
 * @spring.txbn id="maNstGrpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaNstGrpListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaNstGrpListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaNstGrpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpCommonDTO
     * @return List
     */
    public List findNstGrpList(MaNstGrpCommonDTO maNstGrpCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT															");
        query.append("		''										AS seqNo			");
        query.append("		,''										AS isDelCheck		");
        query.append("		,SFAPLANTTODESC('"+maNstGrpCommonDTO.getCompNo()+"', x.pop_plant) as popPlantNo	");
        query.append("		,(SELECT description FROM TADEPT WHERE dept_no = x.pop_dept_no and comp_no = '"+maNstGrpCommonDTO.getCompNo()+"') AS popDeptNo	");
        query.append("		,x.pop_line_no AS popLineNo									");
        query.append("		,x.m_pop_line_no AS mainLineNo								");
        query.append("      ,x.pop_plant as popPlantCode                                ");
        query.append("      ,x.pop_dept_no as popDeptCode                               ");
        query.append("FROM TAPOPLINE x 												");
        query.append("WHERE 1 = 1 								");

        query.append(this.getWhere(maNstGrpCommonDTO));
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaNstGrpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaNstGrpCommonDTO maNstGrpCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", maNstGrpCommonDTO.getCompNo());
        query.getLikeQuery("SFAPLANTTODESC('"+maNstGrpCommonDTO.getCompNo()+"',x.pop_plant)", maNstGrpCommonDTO.getFilterPlant());
        query.getLikeQuery("(SELECT description FROM TADEPT WHERE dept_no = x.pop_dept_no and comp_no = '"+maNstGrpCommonDTO.getCompNo()+"')", maNstGrpCommonDTO.getFilterDept());
        query.getLikeQuery("pop_line_no", maNstGrpCommonDTO.getFilterLine());
        query.getLikeQuery("m_pop_line_no", maNstGrpCommonDTO.getFilterMainLine());
        
        // List의 한 Row만을 재조회
        if (!"".equals(maNstGrpCommonDTO.getPopPlantNo())
        	&&!"".equals(maNstGrpCommonDTO.getPopDeptNo())
        	&&!"".equals(maNstGrpCommonDTO.getPopLineNo()))
        {
            query.getAndQuery("x.pop_plant", maNstGrpCommonDTO.getPopPlantNo());
            query.getAndQuery("x.pop_dept_no", maNstGrpCommonDTO.getPopDeptNo());
            query.getAndQuery("x.pop_line_no", maNstGrpCommonDTO.getPopLineNo());
//            query.getAndQuery("x.m_pop_line_no", maNstGrpCommonDTO.getMainLineNo());
            return query.toString();
        }
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaNstGrpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maNstGrpCommonDTO
     * @return
     */
    public int deleteNstGrp(String id, MaNstGrpCommonDTO maNstGrpCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
//    	String popLineId = id;
//    	String compNo = maNstGrpCommonDTO.getCompNo();
//    	
//    	query.append("DELETE FROM TAPOPLINE					");
//    	query.append("WHERE pop_line_id = '"+popLineId+"'	");
//    	query.append("  AND comp_no	 = '"+compNo+"'			");
//    	rtnValue = this.getJdbcTemplate().update(query.toString());
//
//    	query = new QueryBuffer();
//    	query.append("DELETE FROM TAPOPLNRUNRATE			");
//    	query.append("WHERE pop_line_id = '"+popLineId+"'	");
//    	query.append("  AND comp_no	 = '"+compNo+"'			");
//    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
}