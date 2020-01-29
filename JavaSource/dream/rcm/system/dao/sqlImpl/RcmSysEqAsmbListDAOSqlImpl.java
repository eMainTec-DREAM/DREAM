package dream.rcm.system.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.system.dao.RcmSysEqAsmbListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;

/**
 * 설비부위 목록 dao
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysEqAsmbListDAOTarget"
 * @spring.txbn id="rcmSysEqAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysEqAsmbListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmSysEqAsmbListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmSysEqAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysEqAsmbListDTO
     * @return List
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT         											");
        query.append("        '' 								seqNo          	");
        query.append("       ,'' 								isDelCheck     	");
        query.append("		 ,y.full_desc						description		");
        query.append("       ,x.remark 							remark			");
        query.append("       ,x.rcmeqasmb_id 					rcmEqAsmbId     ");
        query.append("FROM TARCMEQASMB x INNER JOIN TAEQASMB y					");
        query.append("	ON x.comp_no = y.comp_no								");
        query.append(" AND x.eqasmb_id = y.eqasmb_id							");
        query.append("WHERE 1 =	1												");
        query.append("AND x.comp_no = '"+user.getCompNo()+"'					");
        query.append(this.getWhere(rcmSysCommonDTO,rcmSysEqAsmbListDTO));
        query.append("ORDER BY x.rcmeqasmb_id									");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmSysEqAsmbListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TARCMEQASMB						");
    	query.append("WHERE rcmeqasmb_id 	= '"+id+"'				");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEqAsmbListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqListDTO
     * @param maPmMstrCommonDTO
     * @param multiKey
     * @return
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO, String multiKey)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = rcmSysCommonDTO.getCompNo();

    	query.append("INSERT INTO TARCMEQASMB (								");
    	query.append("	comp_no,		rcmlist_id,		    rcmeq_id,		");
    	query.append("	rcmeqasmb_id ,	eqasmb_id,			description,	");
    	query.append("	remark		    									");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?, 				?,									");
    	query.append("	(SELECT description FROM TAEQASMB WHERE comp_no = ? AND eqasmb_id = ?),	");
    	query.append("	?													");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			compNo
    			,rcmSysCommonDTO.getRcmListId()
    			,rcmSysEqAsmbListDTO.getRcmEqId()
    			,rcmSysEqAsmbListDTO.getRcmEqAsmbId()
    			,multiKey
    			,compNo
    			,multiKey
    			,""
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    private String getWhere(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndQuery("x.rcmeq_id", rcmSysEqAsmbListDTO.getRcmEqId());
    	if (!"".equals(rcmSysEqAsmbListDTO.getRcmEqAsmbId()))
        {
            query.getAndQuery("x.rcmeqasmb_id", rcmSysEqAsmbListDTO.getRcmEqAsmbId());
            return query.toString();
        }
    	
    	return query.toString();
    }
}