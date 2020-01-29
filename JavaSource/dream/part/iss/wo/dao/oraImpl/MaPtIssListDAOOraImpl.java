package dream.part.iss.wo.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.iss.wo.dao.MaPtIssListDAO;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtIssListDAOTarget"
 * @spring.txbn id="maPtIssListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtIssListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtIssListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssCommonDTO
     * @return List
     */
    public List findPtIssList(MaPtIssCommonDTO maPtIssCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                        						");
        query.append("     	  ''                                                         								AS ISDELCHECK   	");
        query.append("	 	, ''                                                         								AS SEQNO        	");
        query.append("     	, x.ptisslist_id                                               								AS PTISSLISTID      ");
        query.append("     	, x.issue_date                                                 								AS ISSUEDATE        ");
        query.append("     	, y.part_no                                                   								AS PARTNO       	");
        query.append("     	, y.description                                               								AS PARTDESC     	");
        query.append("     	, y.pt_size                                                   								AS PTSIZE       	");
        query.append("     	, y.model                                                     								AS MODEL        	");
        query.append("     	, y.maker                                                     								AS MAKER        	");
        query.append("     	, SFACODE_TO_DESC(x.part_grade, 'PART_GRADE', 'SYS', '', '"+user.getLangId()+"')			AS PARTGRADEDESC	");
        query.append("     	, x.issue_qty                                                						 		AS USEQTY			");
        query.append("     	, x.ptiss_status                                               								AS PTISSSTATUS      ");
        query.append("     	, SFACODE_TO_DESC(x.ptiss_status, 'PTISS_STATUS', 'SYS', '', '"+user.getLangId()+"') 		AS PTISSSTATUSDESC	");
        query.append("     	, SFACODE_TO_DESC(x.ptiss_type, 'PTISS_TYPE','SYS', '', '"+user.getLangId()+"')     		AS PTISSTYPEDESC	");
        query.append("     	, (SELECT a.emp_name                              																");
        query.append("     		 FROM TAEMP a                               																");
        query.append("     		WHERE a.comp_no = x.comp_no                               													");
        query.append("     		  AND a.emp_id  = x.issue_by)     														AS ISSUEBYDESC      ");
        query.append("     	, (SELECT emp_name                               																");
        query.append("     		 FROM TAEMP a                  																				");
        query.append("     		WHERE a.comp_no = x.comp_no                        															");
        query.append("     		  AND a.emp_id  = x.rec_by)         													AS RECBYDESC        ");
        query.append("     	, x.remark                                                      							AS REMARK           ");
        query.append("  FROM  TAPTISSLIST x INNER JOIN TAPARTS y																			");
        query.append("  					   ON y.comp_no = x.comp_no  																	");
        query.append("  					  AND y.part_id = x.part_id																		");
        query.append(" WHERE  1 = 1                                                                                                         ");
        query.append(this.getWhere(maPtIssCommonDTO, user));
        query.getOrderByQuery("x.ptisslist_id DESC", maPtIssCommonDTO.getOrderBy(), "".equals(maPtIssCommonDTO.getDirection())?"desc":maPtIssCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtIssCommonDTO.getIsLoadMaxCount(), maPtIssCommonDTO.getFirstRow()));

    }
    public String findTotalCount(MaPtIssCommonDTO maPtIssCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT                                            ");
        query.append("        COUNT(1)                                  ");
        query.append("  FROM TAPTISSLIST x INNER JOIN TAPARTS y			");
        query.append("  					  ON y.comp_no = x.comp_no  ");
        query.append("  					 AND y.part_id = x.part_id	");
        query.append(" WHERE 1 = 1                                      ");
        query.append(this.getWhere(maPtIssCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtIssCommonDTO maPtIssCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if (!"".equals(maPtIssCommonDTO.getPtisslistId()))
        {
            query.getAndQuery("x.ptisslist_id", maPtIssCommonDTO.getPtisslistId());
            return query.toString();
        }
        
        if(!"".equals(maPtIssCommonDTO.getStartDateFrom()) || !"".equals(maPtIssCommonDTO.getStartDateTo()) || !"".equals(maPtIssCommonDTO.getDeptId()) || !"".equals(maPtIssCommonDTO.getDeptDesc()) || !"".equals(maPtIssCommonDTO.getEquipId()) || !"".equals(maPtIssCommonDTO.getEquipDesc()) || !"".equals(maPtIssCommonDTO.getWoNo()) || !"".equals(maPtIssCommonDTO.getWoDesc()) || !"".equals(maPtIssCommonDTO.getEmpId()) || !"".equals(maPtIssCommonDTO.getEmpDesc()))
        {
			query.append("AND x.ptisslist_id IN (SELECT aa.ptisslist_id               					");
			query.append("                         FROM TAWOPARTS aa INNER JOIN TAWORKORDER bb			");
			query.append("                        						ON bb.comp_no  = aa.comp_no		");
			query.append("                        					   AND bb.wkor_id  = aa.wkor_id     ");
			query.append("                         					 INNER JOIN TAWOEQUIP cc 			");
			query.append("                        						ON cc.comp_no  = bb.comp_no     ");
			query.append("                        					   AND cc.wkor_id  = bb.wkor_id     ");
			query.append("                         					 INNER JOIN	TAEQUIPMENT dd			");
			query.append("                        						ON dd.comp_no  = cc.comp_no     ");
			query.append("                        					   AND dd.equip_id = cc.equip_id    ");
			query.append("                        WHERE aa.comp_no  	= x.comp_no        				");
			query.append("                          AND aa.ptisslist_id = x.ptisslist_id        		");
			query.getAndDateQuery("bb.wkor_date", maPtIssCommonDTO.getStartDateFrom(), maPtIssCommonDTO.getStartDateTo());
			query.getDeptLevelQuery("bb.dept_id", maPtIssCommonDTO.getDeptId(), maPtIssCommonDTO.getDeptDesc(), user.getCompNo());
			query.getCodeLikeQuery("dd.equip_id", "dd.description", maPtIssCommonDTO.getEquipId(), maPtIssCommonDTO.getEquipDesc());
			query.getLikeQuery("bb.wo_no", maPtIssCommonDTO.getWoNo());
			query.getLikeQuery("bb.description", maPtIssCommonDTO.getWoDesc());
			query.getCodeLikeQuery("bb.emp_id", "(SELECT emp_name FROM TAEMP WHERE comp_no = bb.comp_no and emp_id = bb.emp_id)", maPtIssCommonDTO.getEmpId(), maPtIssCommonDTO.getEmpDesc());
			query.append("                         )                                    ");
        }
        
query.getCodeLikeQuery("x.part_id", "(SELECT a.full_desc FROM TAPARTS a WHERE a.comp_no = x.comp_no and a.part_id = x.part_id)", maPtIssCommonDTO.getPartId(), maPtIssCommonDTO.getPartDesc());
        
        query.getAndDateQuery("x.issue_date", maPtIssCommonDTO.getIssDateFrom(), maPtIssCommonDTO.getIssDateTo());
        
        query.getCodeLikeQuery("x.ptiss_status", "dbo.SFACODE_TO_DESC(x.ptiss_status,'PTISS_STATUS','SYS','','"+user.getLangId()+"')", maPtIssCommonDTO.getIssStatus(), maPtIssCommonDTO.getIssStatusDesc());

        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
        		maPtIssCommonDTO.getFilterPlantId(), maPtIssCommonDTO.getFilterPlantDesc());
        
        //출고창고
        query.getCodeLikeQuery("x.wcode_id", "(SELECT wname FROM TAWAREHOUSE WHERE comp_no = x.comp_no AND wcode_id = x.wcode_id)", 
                maPtIssCommonDTO.getFilterWcodeId(), maPtIssCommonDTO.getFilterWname());
        
        //출고부서
        query.getDeptLevelQuery("x.issue_dept", maPtIssCommonDTO.getFilterIssueDeptId(), maPtIssCommonDTO.getFilterIssueDeptDesc(), user.getCompNo());
        
        //수령자
        query.getCodeLikeQuery("x.rec_by", "(SELECT emp_name FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = x.rec_by)", 
                maPtIssCommonDTO.getFilterRecById(), maPtIssCommonDTO.getFilterRecByDesc());
        
        //출고번호
        query.getLikeQuery("x.ptisslist_id", maPtIssCommonDTO.getFilterPtIssListNo());
        
        //출고구분
        query.getSysCdQuery("x.ptiss_type", maPtIssCommonDTO.getFilterPtissTypeId(), maPtIssCommonDTO.getFilterPtissTypeDesc(), "PTISS_TYPE", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }

    public int deletePtIss(String ptisslistId, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAPTISSLIST                 ");
        query.append("WHERE comp_no = ?                       ");
        query.append("    and ptisslist_id = ?                ");
        query.append("    and ptisslist_id not in (select aa.ptisslist_id                                            ");
        query.append("                                    from taptisslist aa inner join  taworkorder bb on 1=1      ");
        query.append("                                                                  and aa.comp_no = bb.comp_no  ");
        query.append("                                                                  and aa.wkor_id = bb.wkor_id  ");
        query.append("                                                                  and bb.wo_status = 'C'       ");
        query.append("                                    where 1=1                                                  ");
        query.append("                            )                                                                  ");
        query.append("    and ptiss_status NOT IN ('C')      ");

        Object[] objects = new Object[]{
        		user.getCompNo()
        		,ptisslistId
        };
        
        return this.getJdbcTemplate().update(query.toString(),objects);
    }
    
    public int deleteWoParts(String ptIssListId, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOPARTS                																	");
        query.append(" WHERE comp_no = ?                    																");
        query.append("   AND wopart_id = (SELECT a.wopart_id FROM TAPTISSLIST a WHERE a.comp_no = ? AND a.ptisslist_id = ?)	");
        query.append("   AND wopart_id NOT IN (SELECT aa.wopart_id                                                  		");
        query.append("                           FROM taptisslist aa INNER JOIN taworkorder bb      						");
        query.append("                           					    ON 1 = 1      										");
        query.append("                                                 AND aa.comp_no = bb.comp_no  						");
        query.append("                                                 AND aa.wkor_id = bb.wkor_id  						");
        query.append("                                                 AND bb.wo_status = 'C')       						");
        
        Object[] objects = new Object[]{
        		 user.getCompNo()
        	   , user.getCompNo()
        	   , ptIssListId
        };
        
        return this.getJdbcTemplate().update(query.toString(),objects);
    }
    @Override
    public int unlinkWoParts(String ptisslistId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAWOPARTS SET                                         ");
        query.append("   ptisslist_id = NULL                                       ");
        query.append("WHERE ptisslist_id = ?                                       ");
        query.append("  AND comp_no   = ?                                          ");
        
        Object[] objects = new Object[] {
                ptisslistId
                ,user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
}

