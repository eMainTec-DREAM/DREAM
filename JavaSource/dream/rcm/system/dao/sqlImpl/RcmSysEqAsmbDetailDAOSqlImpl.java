package dream.rcm.system.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.system.dao.RcmSysEqAsmbDetailDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqAsmbDetailDTO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;

/**
 * 설비부위 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysEqAsmbDetailDAOTarget"
 * @spring.txbn id="rcmSysEqAsmbDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysEqAsmbDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmSysEqAsmbDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysEqAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbListDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public RcmSysEqAsmbDetailDTO findDetail(RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO, RcmSysCommonDTO rcmSysCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT									");
        query.append("    	 x.eqasmb_id 		eqAsmbId		");
        query.append("		,y.full_desc		eqAsmbDesc		");
        query.append("    	,x.remark 			remark			");
        query.append("    	,x.rcmeq_id 		rcmEqId			");
        query.append("    	,x.rcmeqasmb_id 	rcmEqAsmbId		");
        query.append("FROM TARCMEQASMB x INNER JOIN TAEQASMB y	");
        query.append("	ON x.comp_no = y.comp_no				");
        query.append(" AND x.eqasmb_id = y.eqasmb_id			");
        query.append("WHERE 1=1									");
        query.append("AND x.comp_no = '"+user.getCompNo()+"'	");
        query.getAndQuery("x.rcmeqasmb_id",rcmSysEqAsmbListDTO.getRcmEqAsmbId());
    
        RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO1 = 
        		(RcmSysEqAsmbDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmSysEqAsmbDetailDTO()));
        
        return rcmSysEqAsmbDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysEqAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbDetailDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public int updateDetail(RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO, RcmSysCommonDTO rcmSysCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TARCMEQASMB SET			");
    	query.append("	 eqasmb_id				= ?		");
    	query.append("	,description			= ?		");
    	query.append("	,remark				    = ?		");
    	query.append("WHERE rcmeqasmb_id 		= ?		");
    	query.append("  AND comp_no				= ? 	");
    	
    	Object[] objects = new Object[] {
    			 rcmSysEqAsmbDetailDTO.getEqAsmbId()
    			,rcmSysEqAsmbDetailDTO.getEqAsmbDesc()
    			,rcmSysEqAsmbDetailDTO.getRemark()
    			,rcmSysEqAsmbDetailDTO.getRcmEqAsmbId()
    			,rcmSysCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEqAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbDetailDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public int insertDetail(RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO, RcmSysCommonDTO rcmSysCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMEQASMB (								");
    	query.append("	comp_no,		rcmlist_id,		    rcmeq_id,		");
    	query.append("	rcmeqasmb_id ,	eqasmb_id,			description,	");
    	query.append("	remark		    									");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?, 				?,					?,				");
    	query.append("	?													");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			rcmSysCommonDTO.getCompNo()
    			,rcmSysCommonDTO.getRcmListId()
    			,rcmSysEqAsmbDetailDTO.getRcmEqId()
    			,rcmSysEqAsmbDetailDTO.getRcmEqAsmbId()
    			,rcmSysEqAsmbDetailDTO.getEqAsmbId()
    			,rcmSysEqAsmbDetailDTO.getEqAsmbDesc()
    			,rcmSysEqAsmbDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
}