package dream.rcm.system.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.system.dao.RcmSysEmpListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEmpListDTO;

/**
 * ºÐ¼®ÀÚ  dao
 * @author  kim21017
 * @version $Id: RcmSysEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysEmpListDAOTarget"
 * @spring.txbn id="rcmSysEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysEmpListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmSysEmpListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmSysEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       '' 							seqNo,			");
        query.append("       '' 							isDelCheck,		");
        query.append("       y.emp_no 						empNo,			");
        query.append("       y.emp_name 					empName,		");
        query.append("		(SELECT description								");
        query.append("		   FROM TADEPT									");
        query.append("		  WHERE comp_no = x.comp_no						");
        query.append("			AND dept_id = y.dept_id)	deptDesc,		");
        query.append("       x.remark 						remark,			");
        query.append("       x.rcmemp_id 					rcmEmpId		");
        query.append("FROM   TARCMEMP x	INNER JOIN TAEMP y					");
        query.append("	ON 	x.comp_no = y.comp_no							");
        query.append(" AND 	x.emp_id = y.emp_id								");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(rcmSysCommonDTO,rcmSysEmpListDTO,loginUser));
        query.getOrderByQuery("x.rcmemp_id","x.rcmemp_id", rcmSysCommonDTO.getOrderBy(), rcmSysCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(rcmSysCommonDTO.getIsLoadMaxCount(), rcmSysCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmSysEmpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TARCMEMP						");
    	query.append("WHERE  rcmemp_id 	= '"+id+"'				");
    	query.append("  AND  comp_no	= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * input
     * @author  kim21017
     * @version $Id: RcmSysEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysEmpListDTO
     * @return List
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, String multiKey)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TARCMEMP							");
    	query.append("	(comp_no,		rcmlist_id,		rcmemp_id,	");
    	query.append("	 emp_id,		emp_no,			remark		");
    	query.append("	)VALUES										");
    	query.append("	(?,				?,			?,				");
    	query.append("	 ?,				(SELECT emp_no FROM TAEMP WHERE comp_no = ? AND emp_id = ?),			?	");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			rcmSysCommonDTO.getCompNo()
    			,rcmSysCommonDTO.getRcmListId()
    			,rcmSysEmpListDTO.getRcmEmpId()
    			,multiKey
    			,rcmSysCommonDTO.getCompNo()
    			,multiKey
    			,""
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    private String getWhere(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.getAndQuery("x.rcmlist_id", rcmSysCommonDTO.getRcmListId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	
    	if (!"".equals(rcmSysEmpListDTO.getRcmEmpId()))
        {
            query.getAndQuery("x.rcmemp_id", rcmSysEmpListDTO.getRcmEmpId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, User user) {
	   	QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("SELECT												");
        query.append("   COUNT(1) 											");
        query.append("FROM   TARCMEMP x	INNER JOIN TAEMP y					");
        query.append("	ON 	x.comp_no = y.comp_no							");
        query.append(" AND 	x.emp_id = y.emp_id								");
        query.append("WHERE 1=1												");
	    query.append(this.getWhere(rcmSysCommonDTO,rcmSysEmpListDTO,user));
	
		List resultList=  getJdbcTemplate().queryForList(query.toString());
	    return QuerySqlBuffer.listToString(resultList);
	
	}
}