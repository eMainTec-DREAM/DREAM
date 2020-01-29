package dream.rcm.system.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.system.dao.RcmSysEqListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqListDTO;

/**
 * 설비설정  dao
 * @author  kim21017
 * @version $Id: RcmSysEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysEqListDAOTarget"
 * @spring.txbn id="rcmSysEqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysEqListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmSysEqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmSysEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       '' 							seqNo,			");
        query.append("       '' 							isDelCheck,		");
        query.append("       y.item_no 						itemNo,			");
        query.append("       y.description 					description,	");
        query.append("		(SELECT full_desc								");
        query.append("		   FROM TAEQCTG									");
        query.append("		  WHERE comp_no = y.comp_no						");
        query.append("		    AND eqctg_id = y.eqctg_id)	eqCtgDesc,		");
        query.append("		(SELECT full_desc								");
        query.append("		   FROM TAEQLOC									");
        query.append("		  WHERE comp_no = y.comp_no						");
        query.append("		    AND eqloc_id = y.eqloc_id)	eqLocDesc,		");
        query.append("       x.remark 						remark,			");
        query.append("       x.rcmeq_id 					rcmEqId			");
        query.append("FROM   TARCMEQ x INNER JOIN TAEQUIPMENT y	 			");
        query.append("	ON 	 x.comp_no = y.comp_no							");
        query.append(" AND 	 x.equip_id = y.equip_id						");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(rcmSysCommonDTO,rcmSysEqListDTO,loginUser));
        query.getOrderByQuery("x.rcmeq_id","x.rcmeq_id", rcmSysCommonDTO.getOrderBy(), rcmSysCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(rcmSysCommonDTO.getIsLoadMaxCount(), rcmSysCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmSysEqListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TARCMEQ						");
    	query.append("WHERE  rcmeq_id 	= '"+id+"'				");
    	query.append("  AND  comp_no	= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    public int deleteAsmbList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TARCMEQASMB					");
    	query.append("WHERE  rcmeq_id 	= '"+id+"'				");
    	query.append("  AND  comp_no	= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEqListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, String multiKey)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = rcmSysCommonDTO.getCompNo();

    	query.append("INSERT INTO TARCMEQ					");
    	query.append("	(comp_no,	rcmlist_id,	rcmeq_id,	");
    	query.append("	 equip_id,							");
    	query.append("	 eqctg_id,							");
    	query.append("	 eqloc_id,							");
    	query.append("	 remark								");
    	query.append("	)VALUES								");
    	query.append("	(?,			?,			?,			");
    	query.append("	 ?,									");
    	query.append("	 (SELECT eqctg_id FROM TAEQUIPMENT WHERE comp_no = ? AND equip_id = ?),	");
    	query.append("	 (SELECT eqloc_id FROM TAEQUIPMENT WHERE comp_no = ? AND equip_id = ?),	");
    	query.append("	 ?									");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			compNo
    			,rcmSysCommonDTO.getRcmListId()
    			,rcmSysEqListDTO.getRcmEqId()
    			,multiKey
    			,compNo
    			,multiKey
    			,compNo
    			,multiKey
    			,""
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    private String getWhere(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.getAndQuery("x.rcmlist_id", rcmSysCommonDTO.getRcmListId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	
    	if (!"".equals(rcmSysEqListDTO.getRcmEqId()))
        {
            query.getAndQuery("x.rcmeq_id", rcmSysEqListDTO.getRcmEqId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, User user) {
    	
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("SELECT												");
        query.append("    COUNT(1)											");
        query.append("FROM   TARCMEQ x INNER JOIN TAEQUIPMENT y	 			");
        query.append("	ON 	 x.comp_no = y.comp_no							");
        query.append(" AND 	 x.equip_id = y.equip_id						");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(rcmSysCommonDTO,rcmSysEqListDTO,user));

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    
	}
    
}