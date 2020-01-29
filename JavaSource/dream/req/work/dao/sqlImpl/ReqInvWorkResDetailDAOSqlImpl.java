package dream.req.work.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.req.work.dao.ReqInvWorkResDetailDAO;
import dream.req.work.dto.ReqInvWorkResDetailDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * 작업요청-처리사항 상세(투자) dao
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="reqInvWorkResDetailDAOTarget"
 * @spring.txbn id="reqInvWorkResDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqInvWorkResDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ReqInvWorkResDetailDAO
{
    /**
     * detail find
     * @author js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param reqWorkResListDTO
     * @param user
     * @return
     */
	public ReqInvWorkResDetailDTO findDetail(ReqWorkResListDTO reqWorkResListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT													                                                     ");
        query.append("       invtlist_no                                                                         INVTLISTNO		     ");
        query.append("       ,x.description	                                                                     DESCRIPTION 	     ");
        query.append("       ,invt_categ                                                                         INVTCATEG		     ");
        query.append("       ,dbo.SFACODE_TO_DESC(invt_categ,'INVT_CATEG','SYS','','"+user.getLangId()+"')       INVTCATEGDESC	     ");
        query.append("       ,(SELECT aa.full_desc FROM TAEQCTG aa                                                                   ");
        query.append("         WHERE aa.comp_no = x.comp_no and aa.eqctg_id = x.eqctg_id)                        EQCTGDESC		     ");
        query.append("       ,(SELECT aa.full_desc FROM TAEQLOC aa                                                      		     ");
        query.append("         WHERE aa.comp_no = x.comp_no AND aa.eqloc_id = x.eqloc_id)                        EQLOCDESC		     ");
        query.append("       ,x.eqloc_id                                                                         EQLOCID		     ");
        query.append("       ,x.eqctg_id                                                                         EQCTGID		     ");
        query.append("       ,x.equip_id                                                                         EQUIPID		     ");
        query.append("       ,y.description                                                                      EQUIPDESC		     ");
        query.append("       ,x.dept_id                                                                          DEPTID			     ");
        query.append("       ,(SELECT a.description FROM TADEPT a 	                                                                 ");
        query.append("         WHERE  a.comp_no = x.comp_no and a.dept_id = x.dept_id)                           DEPTDESC		     ");
        query.append("       ,emp_id                                                                             EMPID			     ");		
        query.append("       ,(SELECT a.emp_name FROM TAEMP a 				                                                         ");
        query.append("         WHERE a.comp_no = x.comp_no and a.emp_id = x.emp_id)                              EMPDESC		     ");
        query.append("       ,plan_amt                                                                           PLANAMT		     ");
        query.append("       ,plan_sdate                                                                         PLANSDATE		     ");
        query.append("       ,plan_edate                                                                         PLANEDATE		     ");
        query.append("       ,invtlist_status                                                                    INVTLISTSTATUS	     ");
        query.append("       ,dbo.SFACODE_TO_DESC(invtlist_status,'INVTLIST_STATUS','SYS','','"+user.getLangId()+"') INVTLISTSTATUSDESC	 ");
        query.append("       ,invt_Type                                                                          INVTTYPEID			 ");
        query.append("       ,dbo.SFACODE_TO_DESC(invt_Type,'INVT_TYPE','SYS','','"+user.getLangId()+"')         INVTTYPEDESC	     ");
        query.append("       ,invtlist_id                                                                        INVTLISTID		 	 ");
        query.append("       ,invtprctp_id                                                                       INVTPRCTPID		 ");
        query.append("       ,(SELECT a.description FROM TAINVTPRCTP a 	                                                             ");
        query.append("        WHERE a.comp_no = x.comp_no and a.invtprctp_id = x.invtprctp_id)                   INVTDESC		     ");
        query.append("       ,end_date                                                                           ENDDATE			 ");
        query.append("       ,x.remark as                                                                        REMARK           	 ");
        query.append("       ,(SELECT SUM(ISNULL(ACTUAL_AMT,0)) FROM TAINVTPHASE                                                     ");
        query.append("        WHERE comp_no=x.comp_no AND invtlist_id=x.invtlist_id)                             INVTAMT             ");
        query.append("       ,x.plant                                                                            PLANTID			 ");
        query.append("       ,(SELECT description                                         											 ");
        query.append("         FROM TAPLANT                                             											 ");
        query.append("         WHERE comp_no = x.comp_no                                 											 ");
        query.append("           AND plant = x.plant)                    										 PLANTDESC   		 ");
        query.append("FROM   TAINVTLIST x LEFT OUTER JOIN TAEQUIPMENT y 				                                             ");
        query.append("                  ON x.equip_id = y.equip_id  and x.comp_no = y.comp_no                                        ");
        query.append("WHERE  1=1               									                                                     ");
        query.append("  AND  x.invtlist_id = ?  									                                                 ");
        query.append("  AND  x.comp_no = ?  									                                                     ");

        Object[] objects = new Object[] {
        		reqWorkResListDTO.getInvtlistId()
        		,user.getCompNo()
        };

        ReqInvWorkResDetailDTO reqInvWorkResDetailDTO = 
                (ReqInvWorkResDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ReqInvWorkResDetailDTO()));
        
        return reqInvWorkResDetailDTO;
    }
}