package dream.org.emp.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.org.emp.dao.OrgEmpTrainDetailDAO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainDetailDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;

/**
 * 구매신청 item 상세 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="orgEmpTrainDetailDAOTarget"
 * @spring.txbn id="orgEmpTrainDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgEmpTrainDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements OrgEmpTrainDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainListDTO
     * @param maEmpCommonDTO
     * @return
     */
    public OrgEmpTrainDetailDTO findDetail(OrgEmpTrainListDTO orgEmpTrainListDTO, MaEmpCommonDTO maEmpCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 	                               	   ");
        query.append("        x.emptrainlist_id empTrainListId	   ");
        query.append("      , x.emp_id empId		               ");
        query.append("      , x.courselist_id courseListId		   ");      
        query.append("      , x.train_fdate trainFdate		       ");
        query.append("      , x.train_tdate trainTdate		       ");
        query.append("      , x.train_agency trainAgency		   ");
        query.append("      , y.description trainDesc		       ");
        query.append("      , y.description trainDesc		       ");
        query.append("      , x.place place		                   ");
        query.append("      , x.teacher teacher		               ");
        query.append("      , x.remark remark		               ");
        query.append("  FROM  TAEMPTRAINLIST x, TACOURSELIST y	   ");
        query.append(" WHERE  x.courselist_id = y.courselist_id	   ");
        query.append("   AND  x.comp_no = '"+ user.getCompNo() +"' ");
        query.getAndQuery("x.emptrainlist_id", orgEmpTrainListDTO.getEmpTrainListId());
    
        OrgEmpTrainDetailDTO orgEmpTrainDetailDTO = 
        		(OrgEmpTrainDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new OrgEmpTrainDetailDTO()));
        
        return orgEmpTrainDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainDetailDTO
     * @param maEmpCommonDTO
     * @return
     */
    public int updateDetail(OrgEmpTrainDetailDTO orgEmpTrainDetailDTO, MaEmpCommonDTO maEmpCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append(" UPDATE TAEMPTRAINLIST SET            ");
    	query.append(" 	      courselist_id			= ?		");
    	query.append("	     ,train_agency			= ?		");
    	query.append("	     ,train_fdate			= ?		");
    	query.append("	     ,train_tdate			= ?		");
    	query.append("	     ,place		       		= ?		");
    	query.append("	     ,teacher				= ?		");
    	query.append("	     ,remark				= ?		");
    	query.append("  WHERE emptrainlist_id       = ?		");
    	query.append("    AND comp_no				= ? 	");
    	
    	Object[] objects = new Object[] {
    		     orgEmpTrainDetailDTO.getCourseListId()
    		   , orgEmpTrainDetailDTO.getTrainAgency()
    		   , orgEmpTrainDetailDTO.getTrainFdate()
    		   , orgEmpTrainDetailDTO.getTrainTdate()
    		   , orgEmpTrainDetailDTO.getPlace()
    		   , orgEmpTrainDetailDTO.getTeacher()
    		   , orgEmpTrainDetailDTO.getRemark()
    		   , orgEmpTrainDetailDTO.getEmpTrainListId()
    		   , maEmpCommonDTO.getCompNo()    			
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainDetailDTO
     * @param maEmpCommonDTO
     * @return
     */
    public int insertDetail(OrgEmpTrainDetailDTO orgEmpTrainDetailDTO, MaEmpCommonDTO maEmpCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO  TAEMPTRAINLIST (	");
    	query.append("	           comp_no		    ");
    	query.append("           , emptrainlist_id  ");
    	query.append("           , emp_id           ");
    	query.append("	         , courselist_id	");
    	query.append("           , train_fdate      ");
    	query.append("           , train_tdate      ");
    	query.append("	         , train_agency     ");
    	query.append("           , place            ");
    	query.append("           , teacher          ");
    	query.append("	         , remark			");
    	query.append(" ) VALUES (					");
    	query.append("	           ?    		    ");
    	query.append("           , ?                ");
    	query.append("           , ?                ");
    	query.append("	         , ?				");
    	query.append("	         , ?				");
    	query.append("           , ?                ");
    	query.append("           , ?                ");
    	query.append("           , ?                ");
    	query.append("           , ?                ");
    	query.append("	         , ?				");
    	query.append(" )							");
    	
    	Object[] objects = new Object[] {
    			maEmpCommonDTO.getCompNo(),
    			orgEmpTrainDetailDTO.getEmpTrainListId(),
    			maEmpCommonDTO.getEmpId(),
    			orgEmpTrainDetailDTO.getCourseListId(),
    			orgEmpTrainDetailDTO.getTrainFdate(),
    			orgEmpTrainDetailDTO.getTrainTdate(),
    			orgEmpTrainDetailDTO.getTrainAgency(),
    			orgEmpTrainDetailDTO.getPlace(),
    			orgEmpTrainDetailDTO.getTeacher(),
    			orgEmpTrainDetailDTO.getRemark()
    	};
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
}