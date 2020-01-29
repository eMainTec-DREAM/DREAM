package dream.req.work.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.req.work.dao.ReqWorkResDetailDAO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResDetailDTO;

/**
 * 작업요청-처리사항 상세 dao
 * @author  kim21017
 * @version $Id: ReqWorkResDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="reqWorkResDetailDAOTarget"
 * @spring.txbn id="reqWorkResDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqWorkResDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ReqWorkResDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ReqWorkResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param woDayListId
     * @param woDayActId
     * @param compNo
     * @return
     */
    public ReqWorkResDetailDTO findDetail(String woReqId, String woReqResId, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT												");
        query.append("        x.woreqres_id				woReqResId,			");
        query.append("        x.res_date				resDate,			");
        query.append("        x.wores_status			resStatusId,		");
        query.append("		  SFACODE_TO_DESC(x.wores_status,'WORES_STATUS','SYS','','"+user.getLangId()+"')			resStatusDesc,	");
        query.append("        x.emp_id					empId,				");
        query.append("        (SELECT emp_name								");
        query.append("         FROM TAEMP									");
        query.append("        WHERE 1=1										");
        query.append("          AND comp_no = x.comp_no						");
        query.append("          AND emp_id = x.emp_id) empDesc,				");
        query.append("        x.dept_id					deptId,				");
        query.append("        (SELECT description							");
        query.append("         FROM TADEPT									");
        query.append("        WHERE 1=1										");
        query.append("          AND comp_no = x.comp_no						");
        query.append("          AND dept_id = x.dept_id) deptDesc,			");
        query.append("        x.response				response			");
        query.append("FROM TAWOREQRES x 									");
        query.append("WHERE 1=1												");
        query.append("  AND	 x.woreq_id 		= '"+woReqId+"'				");
        query.append("  AND	 x.woreqres_id 		= '"+woReqResId+"'			");
        query.append("  AND  x.comp_no			= '"+user.getCompNo()+"'	");

        ReqWorkResDetailDTO reqWorkResDetailDTO =
        		(ReqWorkResDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ReqWorkResDetailDTO()));

        return reqWorkResDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: ReqWorkResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param reqWorkResDetailDTO
     * @param reqWorkCommonDTO
     * @return
     */
    public int updateDetail(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

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
    			reqWorkResDetailDTO.getResDate()
    			,reqWorkResDetailDTO.getResStatusId()
    			,reqWorkResDetailDTO.getEmpId()
    			,reqWorkResDetailDTO.getDeptId()
    			,reqWorkResDetailDTO.getResponse()
    			,reqWorkCommonDTO.getWoReqId()
    			,reqWorkResDetailDTO.getWoReqResId()
    			,reqWorkCommonDTO.getCompNo()
    	};

    	return getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * detail insert
     * @author kim21017
     * @version $Id: ReqWorkResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param reqWorkResDetailDTO
     * @param reqWorkCommonDTO
     * @return
     */
    public int insertDetail(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAWOREQRES				");
    	query.append("	(comp_no,		woreq_id,			");
    	query.append("	 woreqres_id,	res_date,			");
    	query.append("	 wores_status,	dept_id,			");
    	query.append("	 emp_id,		response,			");
    	query.append("	 wkor_id,       woreqres_method  	");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,             ?    				");
    	query.append("	)									");

    	Object[] objects = new Object[] {
    			reqWorkCommonDTO.getCompNo(),
    			reqWorkCommonDTO.getWoReqId(),
    			reqWorkResDetailDTO.getWoReqResId(),
    			reqWorkResDetailDTO.getResDate(),
    			reqWorkResDetailDTO.getResStatusId(),
    			reqWorkResDetailDTO.getDeptId(),
    			reqWorkResDetailDTO.getEmpId(),
    			reqWorkResDetailDTO.getResponse(),
    			reqWorkResDetailDTO.getWkorId(),
    			reqWorkResDetailDTO.getWoreqresMethod()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }

    public int changeHdrStatus(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAWOREQ SET				");
    	query.append("	woreq_status			= ?		");
    	query.append("WHERE woreq_id			= ?		");
    	query.append("  AND comp_no				= ?		");

    	Object[] objects = new Object[] {
    			reqWorkResDetailDTO.getResStatusId()
    			,reqWorkCommonDTO.getWoReqId()
    			,reqWorkCommonDTO.getCompNo()
    	};

    	return getJdbcTemplate().update(query.toString(), objects);
    }
}