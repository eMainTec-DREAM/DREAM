package mobile.dream.mapm.mains.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import mobile.dream.mapm.mains.dao.MaPmInsListDAO;
import mobile.dream.mapm.mains.dto.MaPmInsCommonDTO;
import mobile.dream.mapm.mains.form.MaPmInsListForm;

/**
 * 설비종류 - 목록 dao
 * @author  jung7126
 * @version $Id: MaPmInsListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maPmInsListDAOTarget"
 * @spring.txbn id="maPmInsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmInsListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPmInsListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaPmInsListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsCommonDTO
     * @return List
     */
    public List findList(MaPmInsCommonDTO maPmInsCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPmInsCommonDTO.getCompNo();

        query.append("SELECT                                                                                        ");
        query.append("        '' seqNo,                                                                             ");
        query.append("        '' isDelCheck,                                                                        ");
        query.append("        pm_no as id,                                                                        	");
        query.append("        x.pm_no pmNo,                                                                         ");
        query.append("        x.description,                                                                        ");
        query.append("		  DECODE(z.eqctg_type,'MD','('||z.old_eq_no||')'||z.description,z.description) AS equipDesc,	");
        query.append("        SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+compNo+"') deptDesc,                           ");
        query.append("		 (SELECT description																	");
        query.append("		  FROM TAWKCTR																			");
        query.append("		  WHERE comp_no = x.comp_no																");
        query.append("		  AND wkctr_id = x.wkctr_id) wkCtrDesc,													");
        query.append("        (SELECT a.emp_name                                            						");
        query.append("         FROM   TAEMP a                                               						");
        query.append("         WHERE  a.emp_id = x.emp_id) empName,                         						");
        query.append("        SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') pmTypeDesc,         ");
        query.append("        SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') scheduleType,    ");
        query.append("        TO_CHAR(cycle) || SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') cycleDesc,");
        query.append("        x.USAGE,                                                                              ");
        query.getDate("x.last_sch_date", "lastSchDate,");
        query.append("        x.is_active isActive,                                                                 ");
        query.append("        x.pm_id pmId,                                                                         ");
        query.append("        x.pm_type pmType,                                                                     ");
        query.append("        (SELECT param2 FROM TACDSYSD WHERE cdsysd_no=x.pm_type AND list_type= x.wo_type||'_TYPE') param             ");
        query.append("FROM TAPMLST x INNER JOIN TAPMEQUIP y                                                         ");
        query.append("ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id		                                        ");
        query.append("		INNER JOIN TAEQUIPMENT z                      		                                	");
        query.append("      ON y.comp_no = z.comp_no AND y.equip_id = z.equip_id      								");
        query.append("WHERE   1 = 1											                                        ");
        query.append(this.getWhere(maPmInsCommonDTO,user));
        
        query.getOrderByQuery("x.pm_id", maPmInsCommonDTO.getOrderBy(), maPmInsCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPmInsCommonDTO.getIsLoadMaxCount(), maPmInsCommonDTO.getFirstRow()));

//        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  jung7126
     * @version $Id: MaPmInsListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPmInsCommonDTO maPmInsCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getLikeQuery("x.description", maPmInsCommonDTO.getPmDesc());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(MaPmInsListForm maPmInsListForm, User user) {
		
		QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT                                                                        ");
        query.append("      COUNT(1)                                                                ");
        query.append("FROM TAPMLST x INNER JOIN TAPMEQUIP y                                                         ");
        query.append("ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id		                                        ");
        query.append("		INNER JOIN TAEQUIPMENT z                      		                                	");
        query.append("      ON y.comp_no = z.comp_no AND y.equip_id = z.equip_id      								");
        query.append("WHERE 1=1                                                                     ");
        //query.append("  AND  x.comp_no = '"+compNo+"'                                               ");
        query.append(this.getWhere(maPmInsListForm.getMaPmInsCommonDTO(), user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}