package dream.rcm.pmtask.dao.sqlImpl;

import java.util.List;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.pmtask.dao.RcmPmtaskMapListDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;

/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: RcmPmtaskMapListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmPmtaskMapListDAOTarget"
 * @spring.txbn id="rcmPmtaskMapListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmPmtaskMapListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmPmtaskMapListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmPmtaskMapListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @param rcmPmtaskMapListDTO
     * @return List
     */
    public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmPmtaskCommonDTO.getCompNo();
        
        query.append("SELECT											");
        query.append("       '' seqNo,									");
        query.append("       '' isDelCheck,								");
        query.append("       x.pmtaskmap_no pmtaskmapNo,				");
        query.append("       x.description mapDesc,						");
        query.append("       x.value rstVal,							");
        query.append("       x.rslt_task rsltTask,						");
        query.append("       x.remark,									");
        query.append("       x.rcmpmtaskmap_id rcmpmtaskmapId,			");
        query.append("       x.rcmpmtask_id rcmpmtaskId,				");
        query.append("       x.rcmlist_id rcmlistId,					");
        query.append("       x.rcmfmea_id rcmfmeaId						");
        query.append("FROM   TARCMPMTASKMAP x 							");
        query.append("WHERE  comp_no = '"+compNo+"'						");
        query.append(this.getWhere(rcmPmtaskCommonDTO,rcmPmtaskMapListDTO));
        query.getOrderByQuery("x.rcmpmtaskmap_id","x.pmtaskmap_no", rcmPmtaskCommonDTO.getOrderBy(), rcmPmtaskCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(rcmPmtaskCommonDTO.getIsLoadMaxCount(), rcmPmtaskCommonDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmPmtaskMapListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public void deleteList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TARCMPMTASKMAP										");
    	query.append("WHERE rcmpmtask_id 	= '"+rcmPmtaskCommonDTO.getRcmpmtaskId()+"' ");
    	
    	this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	if (!"".equals(rcmPmtaskMapListDTO.getRcmpmtaskmapId()))
        {
            query.getAndQuery("rcmpmtaskmap_id", rcmPmtaskMapListDTO.getRcmpmtaskmapId());
            return query.toString();
        }
    	query.getAndQuery("rcmpmtask_id", rcmPmtaskCommonDTO.getRcmpmtaskId());
    	
    	return query.toString();
    }

	@Override
	public List findQuestionList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmPmtaskCommonDTO.getCompNo();
        
        query.append("SELECT										");
        query.append("       y.pmtaskmap_no pmtaskmapNo,			");
        query.append("       y.description,							");
        query.append("       y.taskmap_rslt_ytype taskmapRsltYtype,	");
        query.append("       y.taskmap_rslt_ntype taskmapRsltNtype,	");
        query.append("       y.pmtaskmap_yid pmtaskmapYid,			");
        query.append("       y.pmtaskmap_nid pmtaskmapNid,			");
        query.append("       y.rslt_ntask rsltNtask,				");
        query.append("       y.rslt_ytask rsltYtask,				");
        query.append("       y.pmtaskmap_id pmtaskmapId,          	");
        query.append("       y.remark								");
        query.append("FROM   TARCMLIST x							");
        query.append("       INNER JOIN TAPMTASKMAP y ON x.pmtaskmaplist_id = y.pmtaskmaplist_id	");
        query.append("WHERE  1 = 1									");
        query.getAndNumKeyQuery("x.rcmlist_id", rcmPmtaskCommonDTO.getRcmlistId());
        query.append("ORDER BY y.pmtaskmap_no 						");
        
        return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public void insertMapList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO, JSONObject json) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TARCMPMTASKMAP 						");
    	query.append("	(comp_no,		rcmpmtaskmap_id,rcmpmtask_id,	");
    	query.append("	 rcmlist_id,	rcmfmea_id,		pmtaskmap_id,	");
    	query.append("	 description,	value,			rslt_task,		");
    	query.append("	 pmtaskmap_no,	ord_no							");
    	query.append("	)	VALUES								    	");
    	query.append("	(?,				next value for SQARCMPMTASKMAP_ID,				?,		    	");
    	query.append("	 ?,				?,				?,		    	");
    	query.append("	 ?,				?,				?,		    	");
    	query.append("	 ?,										    	");
    	query.append("			 (SELECT COUNT(1)+1				    	");
    	query.append("			  FROM TARCMPMTASKMAP a			    	");
    	query.append("			  WHERE 1=1						    	");
    	query.append("			  AND a.rcmpmtask_id = ?				");
    	query.append("			)										");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			rcmPmtaskCommonDTO.getCompNo(),
    			rcmPmtaskCommonDTO.getRcmpmtaskId(),
    			rcmPmtaskCommonDTO.getRcmlistId(),
    			rcmPmtaskCommonDTO.getRcmfmeaId(),
    			rcmPmtaskCommonDTO.getPmtaskmapId(),
    			json.get("DESCRIPTION"),
    			"true".equals(rcmPmtaskCommonDTO.getPmtaskmapVal())?"Y":"N",
    			"true".equals(rcmPmtaskCommonDTO.getPmtaskmapVal())?json.get("RSLTYTASK"):json.get("RSLTNTASK"),
    			json.get("PMTASKMAPNO"),
    			rcmPmtaskCommonDTO.getRcmpmtaskId()
    			
    	};
    	 this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO, User user) {
        
		QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmPmtaskCommonDTO.getCompNo();
        
        query.append("SELECT											");
        query.append("  COUNT(1)										");
        query.append("FROM   TARCMPMTASKMAP x 							");
        query.append("WHERE  comp_no = '"+compNo+"'						");
        query.append(this.getWhere(rcmPmtaskCommonDTO,	rcmPmtaskMapListDTO));
    	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
   
	}

}