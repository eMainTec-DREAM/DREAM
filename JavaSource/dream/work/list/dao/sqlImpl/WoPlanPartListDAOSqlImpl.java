package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.WoPlanPartListDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanPartListDTO;

/**
 * 작업계획목록 - 투입부품 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="woPlanPartListDAOTarget"
 * @spring.txbn id="woPlanPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanPartListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WoPlanPartListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanPartListDTO
     * @param loginUser
     * @return List
     */
    public List findPartList(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       '' 							seqNo,			");
        query.append("       '' 							isDelCheck,		");
        query.append("       x.wkor_id 						wkOrId,			");
        query.append("       x.wopartplan_id 				woPartId,		");
        query.append("       (SELECT wname									");
        query.append("          FROM TAWAREHOUSE							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND wcode_id = x.wcode_id) wcodeDesc,		");
        query.append("       (SELECT part_no								");
        query.append("          FROM TAPARTS								");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND part_id = x.part_id) 	partNo,			");
        query.append("       (SELECT description+', '+ISNULL(pt_size,'')	");
        query.append("          FROM TAPARTS								");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND part_id = x.part_id) 	partDesc,		");
        query.append("       x.use_qty 						useQty,			");
        query.append("		dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+loginUser.getLangId()+"')	partGrade,");
        query.append("       x.remark 						remark			");
        query.append("FROM   TAWOPLANPARTS x								");
        query.append("WHERE  1=1											");
        query.append(this.getWhere(woPlanCommonDTO,woPlanPartListDTO,loginUser));
//        query.append("ORDER BY x.part_id									");
//        return getJdbcTemplate().queryForList(query.toString());
        query.getOrderByQuery("x.wopartplan_id", "x.part_id", woPlanPartListDTO.getOrderBy(), woPlanPartListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(woPlanPartListDTO.getIsLoadMaxCount(), woPlanPartListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePartList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String woPartId=id;
    	
    	query.append("DELETE FROM TAWOPLANPARTS						");
    	query.append("WHERE  wopartplan_id 		= ?	                ");
    	query.append("  AND  comp_no		= ?			            ");
    	
    	Object[] objects = new Object[] {
    			woPartId
    			,compNo
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    private String getWhere(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.wkor_id", woPlanCommonDTO.getWkOrId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(woPlanPartListDTO.getWoPartId()))
        {
            query.getAndQuery("x.wopartplan_id", woPlanPartListDTO.getWoPartId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	public int updateEmgPart(String woPartId, String compNo) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("UPDATE TAPTEMGISSLIST SET            			");
		query.append("       ptemg_task_status      = ?,         	");
		query.append("       wkor_id          		= ?     		");
		query.append("WHERE  comp_no      			= ?         	");
		query.append("  AND  wopartplan_id       	= ?         	");

    	
    	Object[] objects = new Object[] {
    			"W",
    			"",
    			compNo,
    			woPartId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, User loginUser)
			throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT						");
        query.append("       COUNT(1)               ");
        query.append("FROM   TAWOPLANPARTS x		");
        query.append("WHERE  1=1					");
        query.append(this.getWhere(woPlanCommonDTO,woPlanPartListDTO,loginUser));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
	
	@Override
    public String validWoPart(WoPlanCommonDTO woPlanCommonDTO, String multiKey) throws Exception
    {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT count(*)           ");
        query.append("FROM TAWOPLANPARTS            ");
        query.append("WHERE 1=1                 ");
        query.getAndQuery("comp_no", woPlanCommonDTO.getCompNo());
        query.getAndQuery("wkor_id", woPlanCommonDTO.getWkOrId());
        query.getAndQuery("part_id", multiKey);
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

    @Override
    public int inputPartList(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, String multiKey) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAWOPLANPARTS ( comp_no, wopartplan_id, wkor_id, wcode_id, part_id, part_grade, use_qty, remark )       ");
        query.append("SELECT comp_no                                            ");
        query.append("       ,?                                                 ");
        query.append("       ,?                                                 ");
        query.append("       ,isnull(to_wcode_id,wcode_id)  as wcode_id         ");
        query.append("       ,part_id                                           ");
        query.append("       ,part_grade                                        ");
        query.append("       ,issue_qty                                         ");
        query.append("       ,'Emg. Issued S/Part'                              ");
        query.append("FROM   TAPTEMGISSLIST x                                   ");
        query.append("WHERE  ptemgisslist_id = ?                                ");
        query.append("  AND  comp_no = ?                                        ");
        query.append("  AND  ptemg_task_status = ?                              ");

        
        Object[] objects = new Object[] {
                woPlanPartListDTO.getWoPartId(),
                woPlanCommonDTO.getWkOrId(),
                multiKey,
                woPlanCommonDTO.getCompNo(),
                "W"
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public int updateEmgPart(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, String multiKey) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTEMGISSLIST SET                     ");
        query.append("       ptemg_task_status      = ?,            ");
        query.append("       wopart_id              = ?,            ");
        query.append("       wkor_id                = ?             ");
        query.append("WHERE  comp_no                = ?             ");
        query.append("  AND  ptemgisslist_id        = ?             ");

        
        Object[] objects = new Object[] {
                "C",
                woPlanPartListDTO.getWoPartId(),
                woPlanCommonDTO.getWkOrId(),
                woPlanCommonDTO.getCompNo(),
                multiKey
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}