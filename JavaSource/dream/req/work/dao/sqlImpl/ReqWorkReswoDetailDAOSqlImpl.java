package dream.req.work.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.req.work.dao.ReqWorkReswoDetailDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWorkReswoDetailDTO;

/**
 * 작업요청-처리사항 상세 dao
 * @author  kim21017
 * @version $Id: ReqWorkReswoDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="reqWorkReswoDetailDAOTarget"
 * @spring.txbn id="reqWorkReswoDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqWorkReswoDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ReqWorkReswoDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ReqWorkReswoDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param woDayListId
     * @param woDayActId
     * @param compNo
     * @return
     */
    public ReqWorkReswoDetailDTO findDetail(String woReqId, String woReqResId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT																			");
        query.append("		x.wkor_id													WKORID,			");
        query.append("		x.wo_no														WONO,			");
        query.append("		x.wo_status													WOSTATUSID,		");
        query.append("		dbo.SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')			WOSTATUSDESC,	");
        query.append("		x.description												WKORDESC,		");
        query.append("		x.dept_id													DEPTID,			");
        query.append("		(SELECT description															");
        query.append("		   FROM TADEPT																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND dept_id = x.dept_id)								DEPTDESC,		");
        query.append("       x.wkctr_id	                             					WKCTRID,		");
        query.append("		 (SELECT description														");
        query.append("		  FROM TAWKCTR																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND wkctr_id = x.wkctr_id) 			 					WKCTRDESC,		");
        query.append("		x.emp_id													EMPID,			");
        query.append("		(SELECT emp_name															");
        query.append("		   FROM TAEMP																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND emp_id = x.emp_id)									EMPDESC,		");
        query.append("		x.wkor_date													WKORDATE,		");
        query.append("		x.perform													PERFORM,		");
        query.append("		x.pmaction													PMACTION,		");
        query.append("		x.wo_gen_type												WOGENTYPE 		");
        query.append("FROM TAWORKORDER x																");
        query.append("WHERE 1=1																			");
        query.append("  AND x.wkor_id = (SELECT a.wkor_id FROM TAWOREQRES a WHERE a.woreqres_id = ?	)   ");
        query.append("  AND x.comp_no = ?																");

        Object[] objects = new Object[] {
        		woReqResId,
        		user.getCompNo()
    	};
        
        ReqWorkReswoDetailDTO reqWorkReswoDetailDTO = 
        		(ReqWorkReswoDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new ReqWorkReswoDetailDTO()));
        
        return reqWorkReswoDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: ReqWorkReswoDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param reqWorkReswoDetailDTO
     * @param maWoReqCommonDTO
     * @return
     */
    public int updateDetail(ReqWorkReswoDetailDTO reqWorkReswoDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOREQRES SET				");
    	query.append("	res_date				= ?,	");
    	query.append("	wores_status			= ?,	");
    	query.append("	emp_id					= ?,	");
    	query.append("	dept_id					= ?,	");
    	query.append("	response				= ?		");
    	query.append("WHERE woreq_id			= ?		");
    	query.append("  AND woreqres_id			= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			reqWorkReswoDetailDTO.getResDate()
    			,reqWorkReswoDetailDTO.getResStatusId()
    			,reqWorkReswoDetailDTO.getEmpId()
    			,reqWorkReswoDetailDTO.getDeptId()
    			,reqWorkReswoDetailDTO.getResponse()
    			,maWoReqCommonDTO.getWoReqId()
    			,reqWorkReswoDetailDTO.getWoReqResId()
    			,maWoReqCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ReqWorkReswoDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param reqWorkReswoDetailDTO
     * @param maWoReqCommonDTO
     * @return
     */
    public int insertDetail(ReqWorkReswoDetailDTO reqWorkReswoDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOREQRES				");
    	query.append("	(comp_no,		woreq_id,			");
    	query.append("	 woreqres_id,	res_date,				");
    	query.append("	 wores_status,	dept_id,			");
    	query.append("	 emp_id,		response			");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?					");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			maWoReqCommonDTO.getCompNo(),
    			maWoReqCommonDTO.getWoReqId(),
    			reqWorkReswoDetailDTO.getWoReqResId(),
    			reqWorkReswoDetailDTO.getResDate(),
    			reqWorkReswoDetailDTO.getResStatusId(),
    			reqWorkReswoDetailDTO.getDeptId(),
    			reqWorkReswoDetailDTO.getEmpId(),
    			reqWorkReswoDetailDTO.getResponse()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int changeHdrStatus(ReqWorkReswoDetailDTO reqWorkReswoDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOREQ SET				");
    	query.append("	woreq_status			= ?		");
    	query.append("WHERE woreq_id			= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			reqWorkReswoDetailDTO.getResStatusId()
    			,maWoReqCommonDTO.getWoReqId()
    			,maWoReqCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}