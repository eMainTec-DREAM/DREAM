package dream.part.iss.emg.req.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.req.dao.PartIssEmgReqPartsDetailDAO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;

/**
 * 긴급출고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPtIssEmgDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 * @spring.bean id="partIssEmgReqPartsDetailDAOTarget"
 * @spring.txbn id="partIssEmgReqPartsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartIssEmgReqPartsDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements PartIssEmgReqPartsDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgCommonDTO
     * @return
     */
    public PartIssEmgReqPartsDetailDTO findDetail(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																				");
        query.append("        '' seqNo     																	");
        query.append("        ,'' isDelCheck     															");
        query.append("        ,ptemgisslist_id ptEmgIssListId     											");
        query.append("        ,y.part_no partNo     														");
        query.append("        ,y.part_id partId     														");
        query.append("        ,y.full_desc partDesc     													");
        query.append("        ,x.part_grade partGrade     													");
        query.append("        ,SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"') partGradeDesc     			");
        query.append("        ,x.issue_qty issueQty     													");
        query.append("        ,x.remark                                                                     ");
        query.append("        ,(select stock_qty from taptstock where comp_no = x.comp_no and wcode_id = x.wcode_id and part_id = x.part_id and part_grade = x.part_grade) stockQty  ");
        query.append("FROM TAPTEMGISSLIST x, TAPARTS y								");
        query.append("WHERE x.part_id = y.part_id									");
        query.append(" AND x.comp_no = y.comp_no									");
        query.append("  AND x.comp_no = ?											");
        query.append("  AND ptemgisslist_id = ?										");
    
        Object[] objects  = new Object[] {
                user.getCompNo()
        		,partIssEmgReqCommonDTO.getPtEmgIssListId()
        };
        
        PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO = 
        		(PartIssEmgReqPartsDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new PartIssEmgReqPartsDetailDTO()));
        
        return partIssEmgReqPartsDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssEmgReqPartsDetailDTO
     * @return
     */
    public int insertPtIssEmgList(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAPTEMGISSLIST                                ");
        query.append("  (comp_no,           ptemgisslist_id,                    ");
        query.append("   part_id,           part_grade,                         ");
        query.append("   issue_qty,         remark,                             ");
        query.append("   ptemg_task_status, ptemgiss_status,                    ");
        query.append("   ptiss_type,        ptemgissreq_id,                     ");
        query.append("   wcode_id,          issue_date,                         ");
        query.append("   issue_dept,        issue_by,                           ");
        query.append("   equip_id,          plant,                              ");
        query.append("   rec_by,            to_wcode_id,                        ");
        query.append("   ctctr_id,          req_qty,                            ");
        query.append("   req_date,          req_dept,                           ");
        query.append("   req_by                                                 ");
        query.append("  ) VALUES                                                ");
        query.append("  (?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?                                                      ");
        query.append("  )                                                       ");
        
        Object[] objects = new Object[] {
                user.getCompNo(),
                partIssEmgReqPartsDetailDTO.getPtEmgIssListId(),
                partIssEmgReqPartsDetailDTO.getPartId(),
                partIssEmgReqPartsDetailDTO.getPartGrade(),
                partIssEmgReqPartsDetailDTO.getIssueQty(),
                partIssEmgReqPartsDetailDTO.getRemark(),
                partIssEmgReqPartsDetailDTO.getPtemgTaskStatus(),
                partIssEmgReqPartsDetailDTO.getPtemgissStatus(),
                partIssEmgReqPartsDetailDTO.getPtissType(),
                partIssEmgReqPartsDetailDTO.getPtEmgIssReqId(),
                partIssEmgReqPartsDetailDTO.getWcodeId(),
                partIssEmgReqPartsDetailDTO.getReqDate(),
                partIssEmgReqPartsDetailDTO.getReqDept(),
                partIssEmgReqPartsDetailDTO.getReqBy(),
                partIssEmgReqPartsDetailDTO.getEquipId(),
                partIssEmgReqPartsDetailDTO.getPlantId(),
                partIssEmgReqPartsDetailDTO.getRecBy(),
                partIssEmgReqPartsDetailDTO.getToWcodeId(),
                partIssEmgReqPartsDetailDTO.getCtctrId(),
                partIssEmgReqPartsDetailDTO.getIssueQty(),
                partIssEmgReqPartsDetailDTO.getReqDate(),
                partIssEmgReqPartsDetailDTO.getReqDept(),
                partIssEmgReqPartsDetailDTO.getReqBy()
        };
            
        return getJdbcTemplate().update(query.toString(), objects);
    }

    public int updatePtIssEmgList(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAPTEMGISSLIST SET                             ");
        query.append("  part_id                     = ?,                    ");
        query.append("  part_grade                  = ?,                    ");
        query.append("  req_qty                     = ?,                    ");
        query.append("  issue_qty                   = ?,                    ");
        query.append("  remark                      = ?                     ");
        query.append("WHERE ptemgisslist_id         = ?                     ");
        query.append("AND comp_no                   = ?                     ");
        
        Object[] objects = new Object[] {
                partIssEmgReqPartsDetailDTO.getPartId(),
                partIssEmgReqPartsDetailDTO.getPartGrade(),
                partIssEmgReqPartsDetailDTO.getIssueQty(),
                partIssEmgReqPartsDetailDTO.getIssueQty(),
                partIssEmgReqPartsDetailDTO.getRemark(),
                partIssEmgReqPartsDetailDTO.getPtEmgIssListId(),
                user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
        
    }
//    public int updateReqInfo(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user)
//    {
//    	QueryBuffer query = new QueryBuffer();
//    	
//    	query.append("UPDATE (                                                   ");
//    	query.append("          SELECT                                           ");
//    	query.append("             a.req_by a_req_by                             ");
//    	query.append("             ,b.req_by b_req_by                            ");
//    	query.append("             ,a.rec_by a_rec_by                            ");
//    	query.append("             ,a.req_dept a_req_dept                        ");
//    	query.append("             ,b.req_dept b_req_dept                        ");
//    	query.append("             ,a.req_date a_req_date                        ");
//    	query.append("             ,b.req_date b_req_date                        ");
//    	query.append("             ,a.issue_qty a_issue_qty                      ");
//    	query.append("             ,a.req_qty a_req_qty                          ");
//    	query.append("             ,a.wcode_id a_wcode_id                        ");
//    	query.append("             ,b.wcode_id b_wcode_id                        ");
//    	query.append("             ,a.to_wcode_id a_to_wcode_id                  ");
//    	query.append("             ,b.to_wcode_id b_to_wcode_id                  ");
//    	query.append("             ,a.comp_no a_comp_no                          ");
//    	query.append("             ,a.ptemgisslist_id a_ptemgisslist_id          ");
//    	query.append("          FROM TAPTEMGISSLIST a INNER JOIN TAPTEMGISSREQ b ");
//    	query.append("          ON a.comp_no = b.comp_no                         ");
//    	query.append("          AND a.ptemgissreq_id = b.ptemgissreq_id          ");
//    	query.append("       )                                                   ");
//    	query.append("SET                                                        ");
//    	query.append("  a_req_by = b_req_by                                      ");
//    	query.append("  ,a_rec_by = b_req_by                                     ");
//    	query.append("  ,a_req_dept = b_req_dept                                 ");
//    	query.append("  ,a_req_date = b_req_date                                 ");
//    	query.append("  ,a_issue_qty = a_req_qty                                 ");
//    	query.append("  ,a_wcode_id = b_wcode_id                                 ");
//    	query.append("  ,a_to_wcode_id = b_to_wcode_id                           ");
//    	query.append("WHERE a_comp_no             = ?                            ");
//    	query.append("AND a_ptemgisslist_id       = ?                            ");
//
//
//        Object[] objects = new Object[] {
//        		user.getCompNo()
//                ,partIssEmgReqPartsDetailDTO.getPtEmgIssListId()
//        };
//    	
//    	return getJdbcTemplate().update(query.toString(), objects);
//    	
//    }
    
    
    


}