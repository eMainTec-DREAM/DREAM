package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.WorkListBmRplPartSerialListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;

/**
 * 작업결과 부품Serial 목록 dao
 * @author  kim21017
 * @version $Id: WorkListBmRplPartSerialListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workListBmRplPartSerialListDAOTarget"
 * @spring.txbn id="workListBmRplPartSerialListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListBmRplPartSerialListDAOSqlImpl extends BaseJdbcDaoSupportOra implements WorkListBmRplPartSerialListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkListBmRplPartSerialListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListBmRplPartSerialListDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, MaWoResultPartDetailDTO maWoResultPartDetailDTO,User loginUser) 
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       '' seqNo,									");
        query.append("       '' isDelCheck								");
        query.append("       ,woparts_serial_id wopartsSerialId			");
        query.append("       ,wopart_id wopartId						");
        query.append("       ,part_id partId							");
        query.append("       ,in_serial_no inSerialNo					");
        query.append("       ,in_equip_id inEquipId						");
        query.append("       ,out_serial_no notSerialNo					");
        query.append("       ,out_equip_id outEquipId					");
        query.append("       ,remark									");
        query.append("FROM   TAWOPARTS_SERIAL							");
        query.append("WHERE  1=1										");
        query.append(this.getWhere(maWoResultMstrCommonDTO,workListBmRplPartSerialListDTO,maWoResultPartDetailDTO, loginUser));
        query.getOrderByQuery("woparts_serial_id", "woparts_serial_id DESC", workListBmRplPartSerialListDTO.getOrderBy(), workListBmRplPartSerialListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workListBmRplPartSerialListDTO.getIsLoadMaxCount(), workListBmRplPartSerialListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String woCraftId=id;
    	
    	query.append("DELETE FROM TAWOPARTS_SERIAL							");
    	query.append("WHERE  woparts_serial_id 		= '"+woCraftId+"'		");
    	query.append("  AND  comp_no				= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("part_id", maWoResultPartDetailDTO.getPartId());
    	query.getAndQuery("wopart_id", maWoResultPartDetailDTO.getWoPartId());
    	query.getAndQuery("comp_no", loginUser.getCompNo());
    	if (!"".equals(workListBmRplPartSerialListDTO.getWopartSerialId()))
        {
            query.getAndQuery("x.wocraft_id", workListBmRplPartSerialListDTO.getWopartSerialId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM   TAWOPARTS_SERIAL							");
        query.append("WHERE  1=1										");
        query.append(this.getWhere(maWoResultMstrCommonDTO,workListBmRplPartSerialListDTO,maWoResultPartDetailDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
    
    
}