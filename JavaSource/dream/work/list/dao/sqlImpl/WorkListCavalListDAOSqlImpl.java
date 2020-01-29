package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.WorkListCavalListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListCavalListDTO;

/**
 * 작업상세  - 검교정 - 측정값 목록 dao
 * @author  kim21017
 * @version $Id: WorkListCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workListCavalListDAOTarget"
 * @spring.txbn id="workListCavalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListCavalListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkListCavalListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkListCavalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListCavalListDTO
     * @param loginUser
     * @return List
     */
    public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListCavalListDTO workListCavalListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT										");
        query.append("       '' seqNo,								");
        query.append("       '' isDelCheck,							");
        query.append("       x.wocalibetcvalue_id 		woCalibValueId,	");
        query.append("       x.set_value 			setValue,		");
        query.append("       x.basis_value 			basisValue,		");
        query.append("       x.before_value 		beforeValue,	");
        query.append("       x.before_diff_value 	beforeDiffValue,");
        query.append("       x.after_value 			afterValue,		");
        query.append("       x.after_diff_value 	afterDiffValue	");
        query.append("FROM   TAWOCALIBETCVALUE x						");
        query.append("WHERE  1=1									");
        query.append(this.getWhere(maWoResultMstrCommonDTO,workListCavalListDTO,loginUser));
        query.getOrderByQuery("wocalibetcvalue_id", "x.wocalibetcvalue_id", workListCavalListDTO.getOrderBy(), workListCavalListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workListCavalListDTO.getIsLoadMaxCount(), workListCavalListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkListCavalListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCavalList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAWOCALIBETCVALUE				");
    	query.append("WHERE  wocalibetcvalue_id 	= '"+id+"'		");
    	query.append("  AND  comp_no			= '"+compNo+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListCavalListDTO workListCavalListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(workListCavalListDTO.getWoCalibValueId()))
        {
            query.getAndQuery("x.wocalibetcvalue_id", workListCavalListDTO.getWoCalibValueId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListCavalListDTO workListCavalListDTO, User loginUser) throws Exception 
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT                        ");
        query.append("       COUNT(1)               ");
        query.append("FROM   TAWOCALIBETCVALUE x	");
        query.append("WHERE  1=1					");
        query.append(this.getWhere(maWoResultMstrCommonDTO,workListCavalListDTO,loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
}