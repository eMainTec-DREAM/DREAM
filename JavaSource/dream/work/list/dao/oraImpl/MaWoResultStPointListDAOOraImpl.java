package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.dao.MaWoResultStPointListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointListDTO;

/**
 * 작업결과 작업필수검사항목 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultStPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultStPointListDAOTarget"
 * @spring.txbn id="maWoResultStPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultStPointListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoResultStPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultStPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultStPointListDTO
     * @param loginUser
     * @return List
     */
    public List findStPointList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultStPointListDTO maWoResultStPointListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																	");
        query.append("		''					 		seqNo,									");
        query.append("		'' 							isDelCheck,								");
        query.append("		x.wostpoint_id woStPointId,												");
        query.append("		y.step_num stepNum,													");
        query.append("		(SELECT description													");
        query.append("		   FROM TAEQASMB													");
        query.append("		  WHERE comp_no = y.comp_no											");
        query.append("			AND eqasmb_id = y.eqasmb_id) eqAsmbDesc,						");
        query.append("		y.check_point CHECKPOINT,											");
        query.append("		y.check_method checkMethod,											");
        query.append("		y.fit_basis fitBasis,												");
        query.append("		SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS','','"+loginUser.getLangId()+"') checkTypeDesc,	");
        query.append("		SFACODE_TO_DESC(x.st_point_rslt_status,'ST_POINT_RSLT_STATUS','SYS','','"+loginUser.getLangId()+"') rsltStatusDesc,	");
        query.append("		x.result_value resultValue,											");
        query.append("		y.check_min checkMin,												");
        query.append("		y.check_basis_val checkBasisVal,									");
        query.append("		y.check_max checkMax,												");
        query.append("		y.uom uom,															");
        query.append("		y.is_active isActive												");
        query.append("FROM TAWOSTPOINT x, TASTWRKPOINT y												");
        query.append("WHERE x.comp_no = y.comp_no												");
        query.append("  AND x.stwrk_point_id = y.stwrk_point_id										");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultStPointListDTO,loginUser));
        query.getOrderByQuery("y.step_num", maWoResultStPointListDTO.getOrderBy(), maWoResultStPointListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultStPointListDTO.getIsLoadMaxCount(), maWoResultStPointListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultStPointListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteStPointList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	String woStPointId=id;
    	
    	query.append("DELETE FROM TAWOSTPOINT						");
    	query.append("WHERE  wostpoint_id 		= '"+woStPointId+"'	");
    	query.append("  AND  comp_no			= '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultStPointListDTO maWoResultStPointListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("y.is_active", "Y");
    	if (!"".equals(maWoResultStPointListDTO.getWoStPointId()))
        {
            query.getAndQuery("x.wostpoint_id", maWoResultStPointListDTO.getWoStPointId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultStPointListDTO maWoResultStPointListDTO, User loginUser) throws Exception {
		
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("FROM TAWOSTPOINT x, TASTWRKPOINT y			");
        query.append("WHERE x.comp_no = y.comp_no					");
        query.append("  AND x.stwrk_point_id = y.stwrk_point_id		");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultStPointListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);   
	}
}