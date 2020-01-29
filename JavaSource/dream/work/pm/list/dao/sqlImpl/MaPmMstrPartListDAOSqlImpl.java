package dream.work.pm.list.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.MaPmMstrPartListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;

/**
 * 사용자재 목록 dao
 * @author  jung7126
 * @version $Id: MaPmMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maPmMstrPartListDAOTarget"
 * @spring.txbn id="maPmMstrPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmMstrPartListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmMstrPartListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaPmMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findPartList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       '' 							SEQNO,			");
        query.append("       '' 							ISDELCHECK,		");
        query.append("       y.part_no                   	PARTNO,			");
        query.append("       y.description               	PARTDESC,		");
        query.append("       y.pt_size               	    PTSIZE,		    ");
        query.append("       y.model               	        MODEL,		    ");
        query.append("       x.use_qty 						USEQTY,			");
        query.append("       x.pm_id 						PMID,			");
        query.append("       x.pm_part_id 					PMPARTID,		");
        query.append("       (SELECT b.full_desc                            ");
        query.append("        FROM TAPMEQUIP a INNER JOIN TAEQASMB b        ");
        query.append("        ON a.comp_no = b.comp_no                      ");
        query.append("        AND a.eqasmb_id = b.eqasmb_id                 ");
        query.append("        WHERE a.comp_no = x.comp_no                   ");
        query.append("        AND a.pmequip_id = x.pmequip_id) EQASMBDESC   ");
        query.append("FROM   TAPMPART x LEFT OUTER JOIN TAPARTS y           ");
        query.append("ON x.comp_no = y.comp_no                              ");
        query.append("AND x.part_id = y.part_id                             ");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        
        query.getOrderByQuery("x.pm_part_id", "x.part_id", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaPmMstrPartListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] updateDeleteTag(final List<MaPmMstrPartDetailDTO> list, final User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");

        query.append("UPDATE  TAPMPART SET   ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE pm_part_id = ?   ");
        query.append("  AND comp_no = ?       ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {  
                        user.getEmpId()
                        ,deleteTime
                        ,maPmMstrPartDetailDTO.getPmPartId()
                        ,user.getCompNo()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
    
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndNumKeyQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maPmMstrCommonDTO.getPmPartId()))
        {
            query.getAndQuery("x.pm_part_id", maPmMstrCommonDTO.getPmPartId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT				");
        query.append("       COUNT(1)       ");
        query.append("FROM   TAPMPART x LEFT OUTER JOIN TAPARTS y           ");
        query.append("ON x.comp_no = y.comp_no                              ");
        query.append("AND x.part_id = y.part_id                             ");
        query.append("WHERE 1=1				");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
}