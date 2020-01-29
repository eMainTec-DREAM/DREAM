package dream.part.adj.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.adj.dao.MaPtFcRecListDAO;
import dream.part.adj.dto.MaPtFcRecCommonDTO;

/**
 * �����԰� - ��� dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtFcRecListDAOTarget"
 * @spring.txbn id="maPtFcRecListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtFcRecListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtFcRecListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecCommonDTO
     * @return List
     */
    public List findList(MaPtFcRecCommonDTO maPtFcRecCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maPtFcRecCommonDTO.getCompNo();
        
        query.append("SELECT ''                                             seqNo,       	    ");
        query.append("       ''                                             isDelCheck,         ");
        query.append("       x.comp_no                                      compNo,        		");
        query.append("       x.fcreclist_id                                 fcRecListId,  	    ");
        query.append("       x.fcreclist_no                                 fcRecListNo, 	    ");
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)  deptDesc,       "); 
        query.append("       x.rec_date                               		   recDate,			");
        query.append("       x.fcreclist_status                                fcRecListStatus, "); 
        query.append("       dbo.SFACODE_TO_DESC(x.fcreclist_status, 'FCRECLIST_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') recStatusDesc, ");
        query.append("       y.part_no                                         partNo,          ");
        query.append("       y.description+', '+ISNULL(y.pt_size,'')           partNameSize,    ");
        query.append("       CONVERT( INT, ISNULL(x.rec_qty, 0) )              recQty,          "); 
        query.append("       ISNULL(x.unit_price, 0)                           unitPrice,       "); 
        query.append("       ISNULL(x.tot_price, 0)                            totPrice         ");
        query.append("		, y.description 									partDesc		");
        query.append("		, y.model 											partModel		");
        query.append("		, y.pt_size 										partSize		");
        query.append("FROM   TAPTFCRECLIST x, TAPARTS y                                         ");
        query.append("WHERE x.comp_no = y.comp_no                                               ");
        query.append("  AND x.part_id = y.part_id                                               ");
        query.append("  AND x.comp_no = '"+compNo+"'                                            ");
        query.append(this.getWhere(maPtFcRecCommonDTO,user));
        query.getOrderByQuery("x.fcreclist_id","x.fcreclist_id DESC", maPtFcRecCommonDTO.getOrderBy(), maPtFcRecCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtFcRecCommonDTO.getIsLoadMaxCount(), maPtFcRecCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String fcRecListId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAPTFCRECLIST		              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  fcreclist_id  = '"+fcRecListId+"'    ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter ����
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtFcRecCommonDTO maPtFcRecCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        if (!"".equals(maPtFcRecCommonDTO.getFcRecListId()))
        {
            query.getAndQuery("x.fcreclist_id", maPtFcRecCommonDTO.getFcRecListId());
            return query.toString();
        }
        //����
        query.getSysCdQuery("x.fcreclist_status", maPtFcRecCommonDTO.getFcRecStatus(), maPtFcRecCommonDTO.getFcRecStatusDesc(), "FCRECLIST_STATUS", maPtFcRecCommonDTO.getCompNo(),user.getLangId());
          
        // �μ�
          query.getDeptLevelQuery("x.dept_id", maPtFcRecCommonDTO.getFilterDeptId(), maPtFcRecCommonDTO.getFilterDeptDesc(), maPtFcRecCommonDTO.getCompNo());
        //part_no
          query.getCodeLikeQuery("y.part_id", "y.part_no", maPtFcRecCommonDTO.getFilterPartId(), maPtFcRecCommonDTO.getFilterPartDesc());
          
        //�԰�����
        String startDate = maPtFcRecCommonDTO.getFilterRecStartDate();
        String endDate = maPtFcRecCommonDTO.getFilterRecEndDate();
        query.getAndDateQuery("x.rec_date", startDate, endDate);

        
        // ǰ��/�԰�
        query.getLikeQuery("y.full_desc", maPtFcRecCommonDTO.getFilterPartNameSize());
        
        //����
        query.getAndQuery("x.fcreclist_status", maPtFcRecCommonDTO.getFcRecStatus());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(MaPtFcRecCommonDTO maPtFcRecCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPTFCRECLIST x, TAPARTS y                         ");
        query.append("WHERE x.comp_no = y.comp_no                               ");
        query.append("  AND x.part_id = y.part_id                               ");
        query.append("  AND x.comp_no = '"+user.getCompNo()+"'                                            ");
        query.append(this.getWhere(maPtFcRecCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
        
	}
}