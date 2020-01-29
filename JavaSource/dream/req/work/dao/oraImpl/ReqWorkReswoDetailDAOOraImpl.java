package dream.req.work.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
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
public class ReqWorkReswoDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ReqWorkReswoDetailDAO
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
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT																			");
        query.append("		x.wkor_id													wkOrId,			");
        query.append("		x.wo_no														woNo,			");
        query.append("		x.wo_status													woStatusId,		");
        query.append("		SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')			woStatusDesc,	");
        query.append("		x.description												wkOrDesc,		");
        query.append("		x.dept_id													deptId,			");
        query.append("		(SELECT description															");
        query.append("		   FROM TADEPT																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND dept_id = x.dept_id)								deptDesc,		");
        query.append("       x.wkctr_id	                             					wkCtrId,		");
        query.append("		 (SELECT description														");
        query.append("		  FROM TAWKCTR																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND wkctr_id = x.wkctr_id) 			 					wkCtrDesc,		");
        query.append("		x.emp_id													empId,			");
        query.append("		(SELECT emp_name															");
        query.append("		   FROM TAEMP																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND emp_id = x.emp_id)									empDesc,		");
        query.append("		x.wkor_date													wkorDate,		");
        query.append("		x.perform													perform,		");
        query.append("		x.pmaction													pmAction,		");
        query.append("		x.wo_gen_type												woGenType 		");
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
}