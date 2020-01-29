package dream.part.iss.emg.req.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.iss.emg.req.dao.PartIssEmgReqListDAO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;

/**
 * 출고요청 - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partIssEmgReqListDAOTarget"
 * @spring.txbn id="partIssEmgReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartIssEmgReqListDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartIssEmgReqListDAO
{
	public List findIssReqList(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT        ");
        query.append("    ''                                                                                              AS ISDELCHECK            ");
        query.append("    ,''                                                                                             AS SEQNO                 ");
        query.append("    ,a.ptemgissreq_no                                                                               AS ptEmgIssReqNo         ");
        query.append("    ,a.req_date                                                                                     AS issDate               ");
        query.append("    ,(SELECT description FROM TAPLANT                                                                                        ");
        query.append("        WHERE comp_no=a.comp_no AND plant=a.plant)                                                  AS plantDesc             ");
        query.append("    ,(SELECT description FROM TADEPT                                                                                         ");
        query.append("        WHERE comp_no=a.comp_no AND dept_id=a.req_dept)                                             AS issDept               ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE                                                                                          ");
        query.append("        WHERE comp_no=a.comp_no AND wcode_id=a.wcode_id)                                            AS fromWname             ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE                                                                                          ");
        query.append("        WHERE comp_no=a.comp_no AND wcode_id=a.to_wcode_id)                                         AS toWname               ");
        query.append("    ,(SELECT description FROM TACTCTR                                                                                        ");
        query.append("        WHERE comp_no=a.comp_no AND ctctr_id=a.ctctr_id)                                            AS ctctrName             ");
        query.append("    ,a.req_by                                                                                       AS reqBy                 ");
        query.append("    ,(SELECT emp_name FROM TAEMP                                                                                             ");
        query.append("        WHERE comp_no=a.comp_no AND emp_id=a.req_by)                                                AS reqByDesc             ");
        query.append("    ,(SELECT emp_name FROM TAEMP                                                                                             ");
        query.append("        WHERE comp_no=a.comp_no AND emp_id=a.rec_by)                                                AS recByDesc             ");
        query.append("    ,(SELECT description FROM TAEQUIPMENT                                                                                    ");
        query.append("        WHERE comp_no=a.comp_no AND equip_id=a.equip_id)                                            AS equipDesc             ");
        query.append("    ,a.REMARK                                                                                       AS REMARK                ");
        query.append("    ,a.ptemgissreq_status                                                                           AS ptEmgIssReqStatus     ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.ptemgissreq_status,'PTEMGISSREQ_STATUS','SYS','','"+user.getLangId()+"') AS ptEmgIssReqStatusDesc ");
        query.append("    ,a.ptemgissreq_id                                                                               AS ptEmgIssReqId         ");
        query.append("FROM TAPTEMGISSREQ a                                                                                                         ");
        query.append("WHERE  1=1                                                                                                                   ");
        query.append(this.getWhere(partIssEmgReqCommonDTO, user));
        query.getOrderByQuery("a.ptemgissreq_id desc","a.req_date desc", partIssEmgReqCommonDTO.getOrderBy(), partIssEmgReqCommonDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(partIssEmgReqCommonDTO.getIsLoadMaxCount(), partIssEmgReqCommonDTO.getFirstRow()));
    } 

	private String getWhere(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user)
    {       
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.getStringEqualQuery("a.comp_no", user.getCompNo());
        if(!"".equals(partIssEmgReqCommonDTO.getPtEmgIssReqId())){
            query.getAndQuery("a.ptemgissreq_id", partIssEmgReqCommonDTO.getPtEmgIssReqId());
            return query.toString();
        }
      //출고부서
        query.getDeptLevelQuery("a.req_dept", partIssEmgReqCommonDTO.getFilterIssDeptId(), partIssEmgReqCommonDTO.getFilterIssDeptDesc(), user.getCompNo());
        //출고일자
        query.getAndDateQuery("a.req_date", partIssEmgReqCommonDTO.getFilterIssStartDate(), partIssEmgReqCommonDTO.getFilterIssEndDate());
        //수령자
        query.getCodeLikeQuery("a.rec_by", "(SELECT emp_name FROM TAEMP WHERE emp_id = a.rec_by AND comp_no = a.comp_no)", 
                partIssEmgReqCommonDTO.getFilterRecById(), partIssEmgReqCommonDTO.getFilterRecByDesc());
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant)", 
                partIssEmgReqCommonDTO.getFilterPlantId(), partIssEmgReqCommonDTO.getFilterPlantDesc());
        //부품명/규격
        if(!"".equals(partIssEmgReqCommonDTO.getFilterPartNameSize())) {
            query.append("AND EXISTS(SELECT y.part_id                                      ");
            query.append("          FROM TAPTEMGISSLIST x INNER JOIN TAPARTS y             ");
            query.append("          ON x.comp_no = y.comp_no                               ");
            query.append("          AND x.part_id = y.part_id                              ");
            query.append("          WHERE x.comp_no = a.comp_no                            ");
            query.append("          AND x.ptemgissreq_id = a.ptemgissreq_id                ");
            query.getLikeQuery("y.full_desc", partIssEmgReqCommonDTO.getFilterPartNameSize());
            query.append("          )                                                      ");
        }
        //출고상태
        query.getSysCdQuery("a.ptemgissreq_status", partIssEmgReqCommonDTO.getFilterIssStatus(), partIssEmgReqCommonDTO.getFilterIssStatusDesc(), 
                "PTEMGISSREQ_STATUS", user.getCompNo(), user.getLangId());
        //출고No
        query.getAndQuery("a.ptemgissreq_no", partIssEmgReqCommonDTO.getFilterIssNo());
        //코스트센터
        query.getCodeLikeQuery("a.ctctr_id", "(SELECT description FROM TACTCTR WHERE comp_no = a.comp_no AND ctctr_id = a.ctctr_id)", 
                partIssEmgReqCommonDTO.getFilterCtctrId(), partIssEmgReqCommonDTO.getFilterCtctrDesc());
        //설비
        query.getCodeLikeQuery("a.equip_id", "(SELECT description FROM TAEQUIPMENT WHERE comp_no = a.comp_no AND equip_id = a.equip_id)", 
                partIssEmgReqCommonDTO.getFilterEquipId(), partIssEmgReqCommonDTO.getFilterEquipDesc());

    	return query.toString();
    }

    public int deleteIssReqList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("DELETE FROM TAPTEMGISSREQ         ");
        query.append("WHERE  comp_no            = ?     ");
        query.append("  AND  ptemgissreq_id     = ?     ");
        query.append("  AND  ptemgissreq_status IN('WT','DA','X')   ");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAPTEMGISSREQ a																	");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(partIssEmgReqCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

    @Override
    public int deleteIssReqPartsList(String id, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE a FROM TAPTEMGISSLIST a                ");
        query.append("WHERE  a.comp_no        = ?                   ");
        query.append("  AND  a.ptemgissreq_id = ?                   ");
        query.append("  AND  (SELECT ptemgissreq_status             ");
        query.append("        FROM TAPTEMGISSREQ                    ");
        query.append("        WHERE comp_no      = a.comp_no        ");
        query.append("        AND ptemgissreq_id = a.ptemgissreq_id ");
        query.append("        ) IN('WT','DA','X')                   ");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}