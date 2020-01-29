package dream.part.iss.emg.req.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;

import dream.part.iss.emg.req.dao.PartIssEmgReqPartsListDAO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="partIssEmgReqPartsListDAOTarget"
 * @spring.txbn id="partIssEmgReqPartsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartIssEmgReqPartsListDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartIssEmgReqPartsListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssEmgReqCommonDTO
     * @return List
     */
    public List findPtIssEmgList(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                                 ");
        query.append("SELECT                                                                                                            ");
        query.append("        ''                                                                                        seqNo           ");
        query.append("        ,''                                                                                       isDelCheck      ");
        query.append("        ,ptemgisslist_id                                                                          ptEmgIssListId  ");
        query.append("        ,x.part_id                                                                                partId          ");
        query.append("        ,x.part_grade                                                                             partGrade       ");
        query.append("        ,y.part_no                                                                                partNo          ");
        query.append("        ,y.full_desc                                                                              ptNameSize      ");
        query.append("        ,x.issue_qty                                                                              issueQty        ");
        query.append("        ,dbo.SFACODE_TO_DESC(x.ptemgiss_status,'PTEMGISS_STATUS','SYS','','"+user.getLangId()+"') ptEmgIssStatus  ");
        query.append("        ,x.remark                                                                                 remark          ");
        query.append("FROM TAPTEMGISSLIST x, TAPARTS y                                                                                  ");
        query.append("WHERE x.part_id = y.part_id                                                                                       ");
        query.append("AND x.comp_no = y.comp_no                                                                                         ");
        query.append(this.getWhere(partIssEmgReqCommonDTO, user));
        query.append("ORDER BY ptemgisslist_id asc                                                                                     ");
        
        return getJdbcTemplate().queryForList(query.toString(partIssEmgReqCommonDTO.getIsLoadMaxCount(), partIssEmgReqCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssEmgReqCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        query.getAndQuery("x.ptemgissreq_id", partIssEmgReqCommonDTO.getPtEmgIssReqId());
        query.getAndQuery("x.comp_no", user.getCompNo());
        if (!"".equals(partIssEmgReqCommonDTO.getPtEmgIssListId()))
        {
            query.getAndQuery("x.ptemgisslist_id", partIssEmgReqCommonDTO.getPtEmgIssListId());
            return query.toString();
        }
        
        return query.toString();
    }

    public int deletePtIssEmg(String ptemgisslist_id,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE a FROM TAPTEMGISSLIST a                  ");
        query.append("WHERE  a.comp_no         = ?                    ");
        query.append("  AND  a.ptemgisslist_id = ?                    ");
        query.append("  AND  (SELECT ptemgissreq_status               ");
        query.append("        FROM TAPTEMGISSREQ                      ");
        query.append("        WHERE comp_no       = a.comp_no         ");
        query.append("        AND ptemgissreq_id  = a.ptemgissreq_id  ");
        query.append("        ) IN('WT','DA')                         ");
        
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,ptemgisslist_id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAPTEMGISSLIST x, TAPARTS y                                                  ");
        query.append("WHERE x.part_id = y.part_id                                                       ");
        query.append("AND x.comp_no = y.comp_no                                              ");
        query.append(this.getWhere(partIssEmgReqCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}