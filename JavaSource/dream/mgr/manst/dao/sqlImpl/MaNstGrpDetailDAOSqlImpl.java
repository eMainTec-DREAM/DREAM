package dream.mgr.manst.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.manst.dao.MaNstGrpDetailDAO;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;
import dream.mgr.manst.dto.MaNstGrpDetailDTO;

/**
 * 무정지대표라인 - 상세
 * 
 * @author kim21017
 * @version $Id: MaNstGrpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maNstGrpDetailDAOTarget"
 * @spring.txbn id="maNstGrpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaNstGrpDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaNstGrpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaNstGrpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqLocId
     * @param compNo
     * @return
     */
    public MaNstGrpDetailDTO findDetail(MaNstGrpCommonDTO maNstGrpCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT															");
        query.append("		dbo.SFAPLANTTODESC('"+loginUser.getCompNo()+"', x.pop_plant) AS popPlantDesc");
        query.append("      ,x.pop_plant AS popPlantNo                                  ");
        query.append("		,(SELECT description FROM TADEPT WHERE dept_no = x.pop_dept_no and comp_no = '"+loginUser.getCompNo()+"') AS popDeptDesc	");
        query.append("      ,x.pop_dept_no AS popDeptNo                                 ");
        query.append("		,x.pop_line_no AS popLineNo									");
        query.append("		,x.m_pop_line_no AS mainLineNo								");
        query.append("FROM TAPOPLINE x 												");
        query.append("WHERE 1 = 1								");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.pop_plant", maNstGrpCommonDTO.getPopPlantNo());
        query.getAndQuery("x.pop_dept_no", maNstGrpCommonDTO.getPopDeptNo());
        query.getAndQuery("x.pop_line_no", maNstGrpCommonDTO.getPopLineNo());
        query.getAndQuery("x.m_pop_line_no", maNstGrpCommonDTO.getMainLineNo());  
    
        MaNstGrpDetailDTO maNstGrpDetailDTO = 
        		(MaNstGrpDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaNstGrpDetailDTO()));
        
        return maNstGrpDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaNstGrpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpDetailDTO
     * @return
     */
    public int insertDetail(MaNstGrpDetailDTO maNstGrpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAPOPLNRUNRATE							");
    	query.append("	(comp_no,		poplnrunrate_id,	pop_line_id,	");
    	query.append("	 yyyy,			t_rate								");
    	query.append("	)	VALUES											");
    	query.append("	(?,				NEXT VALUE FOR SQAPOPLNRUNRATE_ID,	?,		");
    	query.append("	 ?,				?									");
    	query.append("	)													");
    	Object[] objects = new Object[] {
//    			maNstGrpDetailDTO.getCompNo(),
//    			maNstGrpDetailDTO.getPopLineId(),
//    			maNstGrpDetailDTO.getYyyy(),
//    			maNstGrpDetailDTO.getTrate()
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaNstGrpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpDetailDTO
     * @return
     */
    public int updateDetail(MaNstGrpDetailDTO maNstGrpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query = new QuerySqlBuffer();

    	query.append("  UPDATE TAPOPLINE            ");
    	query.append("  SET m_pop_line_no  = ?      ");
    	query.append("  WHERE comp_no      = ?      ");
    	query.append("    AND pop_plant    = ?      ");
    	query.append("    AND pop_dept_no  = ?      ");
    	query.append("    AND pop_line_no  = ?      ");

    	Object[] objects = new Object[] {
    	        maNstGrpDetailDTO.getMainLineNo(),
    	        maNstGrpDetailDTO.getCompNo(),
    	        maNstGrpDetailDTO.getPopPlantNo(),
    	        maNstGrpDetailDTO.getPopDeptNo(),
    	        maNstGrpDetailDTO.getPopLineNo()
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(),objects);
    	
    	return rtnValue;
    }

    public int updateLineRate(MaNstGrpDetailDTO maNstGrpDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

        query = new QuerySqlBuffer();

        query.append("  UPDATE TAPOPLNRUNRATE       ");
        query.append("  SET m_pop_line_no  = ?      ");
        query.append("  WHERE comp_no      = ?      ");
        query.append("    AND pop_plant    = ?      ");
        query.append("    AND pop_dept_no  = ?      ");
        query.append("    AND pop_line_no  = ?      ");

        Object[] objects = new Object[] {
                maNstGrpDetailDTO.getMainLineNo(),
                maNstGrpDetailDTO.getCompNo(),
                maNstGrpDetailDTO.getPopPlantNo(),
                maNstGrpDetailDTO.getPopDeptNo(),
                maNstGrpDetailDTO.getPopLineNo()
        };
        
        rtnValue = this.getJdbcTemplate().update(query.toString(),objects);
        
        return rtnValue;
    }
    
}