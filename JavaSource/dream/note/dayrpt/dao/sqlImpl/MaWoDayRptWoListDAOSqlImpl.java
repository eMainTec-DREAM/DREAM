package dream.note.dayrpt.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.note.dayrpt.dao.MaWoDayRptWoListDAO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;

/**
 * 업무일지-woList - 작업 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoDayRptWoListDAOTarget"
 * @spring.txbn id="maWoDayRptWoListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDayRptWoListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoDayRptWoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDayRptCommonDTO
     * @return List
     */
    public List findList(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                           		         		");
        query.append("        ''                                                            	seqNo,                		");
        query.append("        x.wkor_id                                                        	wkOrId,                		");
        query.append("        x.wo_no                                                         	woNo,                		");
        query.append("        x.description                                                 	wkOrDesc,            		");
        query.append("        b.item_no                                                 	itemNo,            		");
        query.append("        b.description                                                 	equipDesc,            		");
        query.getDate("x.wkor_date", "startDate,");
        query.append("        (SELECT description                                                                			");
        query.append("           FROM TADEPT                                                                    			");
        query.append("          WHERE comp_no = x.comp_no                                                        			");
        query.append("            AND dept_id = x.dept_id)                                          deptDesc,      			");
        query.append("        dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"')                woTypeDesc,    			");
        query.append("        dbo.SFACODE_TO_DESC(x.pm_type,x.wo_type+'_TYPE','SYS','','"+user.getLangId()+"')        pmTypeDesc,    			");
        query.append("        dbo.SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')            woStatusDesc,         	");
        query.append("        x.work_time                                                           workTime,            	");
        query.append("         (SELECT emp_name                                                                            	");
        query.append("           FROM TAEMP                                                                                	");
        query.append("          WHERE comp_no = x.comp_no                                                                	");
        query.append("            AND emp_id = x.emp_id)                                             empDesc,               ");
        query.append("          (SELECT c.emp_name                                                                        	");
        query.append("            FROM TAEMP c                                                                          	");
        query.append("            WHERE x.comp_no = c.comp_no                                                             	");
        query.append("                AND b.sub_mng_id = c.emp_id)                                    subMng,               ");
        query.append("            (SELECT c.description                                                                   	");
        query.append("            FROM TAEQCTG c                                                                          	");
        query.append("            WHERE x.comp_no = c.comp_no                                                            	");
        query.append("                AND b.eqctg_id = c.eqctg_id  )                                 eqCtgDesc,           	");
        query.append("      (SELECT ISNULL(y.full_desc,y.description) 														");
        query.append("         FROM TAEQASMB y 																				");
        query.append("        WHERE y.comp_no = x.comp_no 																	");
        query.append("          AND y.eqasmb_id = x.eqasmb_id)       								eqAsmbDesc,  			");
        query.append("        x.perform                                                             perform,            	");
        query.append("        (select param1 FROM tACDSYSD where list_type=x.wo_type+'_TYPE' AND cdsysd_no=x.pm_type) param ");
        query.append("FROM TAWORKORDER x                                                                     		");
        query.append("INNER JOIN TAWOEQUIP a                                                                 		");
        query.append("ON x.comp_no = a.comp_no                                                                 		");
        query.append("AND x.wkor_id = a.wkor_id                                                              		");
        query.append("INNER JOIN TAEQUIPMENT b                            		");
        query.append("ON a.comp_no = b.comp_no                          		");
        query.append("AND a.equip_id = b.equip_id                        		");
        query.append("WHERE 1=1                                                 ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.append("      and x.wo_type <> 'PMI' 													");
        query.append("      and x.pm_type <> 'INS' 													");
        query.append("      AND x.wkor_date = ?														");
        query.append("      AND x.dept_id =?														");
        query.getOrderByQuery("x.wkor_id", "x.wkor_id desc", maWoDayRptDetailDTO.getOrderBy(), maWoDayRptDetailDTO.getDirection());
        
        Object[] object = new Object[]{
        		maWoDayRptDetailDTO.getWorkDate().replaceAll("-", ""),
        		maWoDayRptDetailDTO.getDeptId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(maWoDayRptDetailDTO.getIsLoadMaxCount(), maWoDayRptDetailDTO.getFirstRow()),object);
    }
}