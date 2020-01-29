package dream.comm.revision.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.comm.revision.dao.CommRevHistDetailDAO;
import dream.comm.revision.dto.CommRevHistDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author jung7126
 * @version $Id: CommRevHistDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="commRevHistDetailDAOTarget"
 * @spring.txbn id="commRevHistDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CommRevHistDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements CommRevHistDetailDAO
{
	/**
     * Detail Á¶Èñ
     * @author jung7126
     * @version $Id: CommRevHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param revisionhistId
     * @param user
     * @return
     * @throws Exception
     */
    public CommRevHistDetailDTO findPmDetail(String revisionhistId, User user) throws Exception
    {
    	String lang = user.getLang();
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT 																									");
    	query.append("        x.revisionhist_id 															revisionhistId 		");
    	query.append("        ,x.revision_type 																revisionType 		");
    	query.append("        ,dbo.SFACODE_TO_DESC(x.revision_type,'REVISION_TYPE','SYS','',?) 				revisionTypeDesc 	");
    	query.append("        ,x.revision_object_type 														revisionObjType 	");
    	query.append("        ,dbo.SFACODE_TO_DESC(x.revision_object_type,'REVISION_OBJECT_TYPE','SYS','',?) 	revisionObjTypeDesc ");
    	query.append("        ,x.object_no 																	objectNo 			");
    	query.append("        ,y.description 																description 		");
    	query.append("        ,x.doc_no 																	docNo 				");
    	query.append("        ,x.rev_no 																	revNo 				");
    	query.append("        ,x.revision_status 															revisionStatusId 	");
    	query.append("        ,dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','',?) 			revisionStatusDesc 	");
    	query.append("        ,x.wrk_date 																	wrkDate 			");
    	query.append("        ,(SELECT dept_id FROM TAEMP b WHERE b.comp_no = x.comp_no AND b.emp_id = x.wrk_id) 	wrkDeptId 	");
    	query.append("        ,(SELECT a.description FROM TADEPT a INNER JOIN TAEMP b ON a.comp_no = b.comp_no AND a.dept_id = b.dept_id WHERE b.comp_no = x.comp_no AND b.emp_id = x.wrk_id) wrkDeptDesc ");
    	query.append("        ,x.wrk_id 																	wrkEmpId 			");
    	query.append("        ,(SELECT emp_name FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = x.wrk_id) wrkEmpDesc 			");
    	query.append("        ,x.rev_desc 																	revDesc 			");
    	query.append("FROM	  TAREVISIONHIST x INNER JOIN TAPMLST y 															");
    	query.append("ON 	  x.comp_no = y.comp_no 																			");
    	query.append("AND 	  x.object_id = y.pm_id 																			");
    	query.append("WHERE   x.comp_no = ? 																					");
    	query.append("AND 	  x.revisionhist_id = ? 																			");
    	
    	Object[] objects = new Object[] {
    			lang
    			,lang
    			,lang
    			,user.getCompNo()
    			,revisionhistId
    	};
    	
    	CommRevHistDetailDTO commRevHistDetailDTO = 
        		(CommRevHistDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new CommRevHistDetailDTO()));
        
        return commRevHistDetailDTO;
    }
    
    public CommRevHistDetailDTO findAssetDetail(String revisionhistId, User user) throws Exception
    {
    	String lang = user.getLang();
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT 																									");
    	query.append("        x.revisionhist_id 															revisionhistId 		");
    	query.append("        ,x.revision_type 																revisionType 		");
    	query.append("        ,dbo.SFACODE_TO_DESC(x.revision_type,'REVISION_TYPE','SYS','',?) 					revisionTypeDesc 	");
    	query.append("        ,x.revision_object_type 														revisionObjType 	");
    	query.append("        ,dbo.SFACODE_TO_DESC(x.revision_object_type,'REVISION_OBJECT_TYPE','SYS','',?) 	revisionObjTypeDesc ");
    	query.append("        ,x.object_no 																	objectNo 			");
    	query.append("        ,y.description 																description 		");
    	query.append("        ,x.doc_no 																	docNo 				");
    	query.append("        ,x.rev_no 																	revNo 				");
    	query.append("        ,x.revision_status 															revisionStatusId 	");
    	query.append("        ,dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','',?) 				revisionStatusDesc 	");
    	query.append("        ,x.wrk_date 																	wrkDate 			");
    	query.append("        ,(SELECT dept_id FROM TAEMP b WHERE b.comp_no = x.comp_no AND b.emp_id = x.wrk_id) 	wrkDeptId 	");
    	query.append("        ,(SELECT a.description FROM TADEPT a INNER JOIN TAEMP b ON a.comp_no = b.comp_no AND a.dept_id = b.dept_id WHERE b.comp_no = x.comp_no AND b.emp_id = x.wrk_id) wrkDeptDesc ");
    	query.append("        ,x.wrk_id 																	wrkEmpId 			");
    	query.append("        ,(SELECT emp_name FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = x.wrk_id) wrkEmpDesc 			");
    	query.append("        ,x.rev_desc 																	revDesc 			");
    	query.append("FROM	  TAREVISIONHIST x INNER JOIN TAEQUIPMENT y 														");
    	query.append("ON 	  x.comp_no 	= y.comp_no 																		");
    	query.append("AND 	  x.object_id 	= y.equip_id 																		");
    	query.append("WHERE   x.comp_no 	= ? 																				");
    	query.append("AND 	  x.revisionhist_id = ? 																			");
    	
    	Object[] objects = new Object[] {
    			lang
    			,lang
    			,lang
    			,user.getCompNo()
    			,revisionhistId
    	};
    	
    	CommRevHistDetailDTO commRevHistDetailDTO = 
        		(CommRevHistDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new CommRevHistDetailDTO()));
        
        return commRevHistDetailDTO;
    }
    /**
     * revision update
     * @author jung7126
     * @version $Id: CommRevHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param commRevHistDetailDTO
     * @return
     */
    public int updateRevHist(CommRevHistDetailDTO commRevHistDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAREVISIONHIST SET         ");
    	query.append("  doc_no			= ?				");
    	query.append("  ,rev_no			= ?        		");
    	query.append("WHERE 1=1                         ");
    	query.append("  and  comp_no 		= ?             ");
    	query.append("  and revisionhist_id = ?             ");

    	
    	Object[] objects = new Object[] {
    	        commRevHistDetailDTO.getDocNo()
    	        ,commRevHistDetailDTO.getRevNo()
    	        ,commRevHistDetailDTO.getCompNo()
    	        ,commRevHistDetailDTO.getRevisionhistId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}