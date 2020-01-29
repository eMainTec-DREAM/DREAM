package dream.note.dayrpt.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class MaWoDayRptWoListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoDayRptWoListDAO
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
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                                                  			");
        query.append("        ''                                                            seqNo,              		");
        query.append("        x.wkor_id                                                        wkOrId,              	");
        query.append("        x.wo_no                                                          woNo,              		");
        query.append("        x.description                                                    wkOrDesc,          		");
        query.append("        z.item_no                                                    	   itemNo,          		");
        query.append("        z.description                                                    equipDesc,          		");
        query.getDate("x.wkor_date", "startDate,");
        query.append("        (SELECT description                                                                		");
        query.append("           FROM TADEPT                                                                  			");
        query.append("          WHERE comp_no = x.comp_no                                                      			");
        query.append("            AND dept_id = x.dept_id)                                  deptDesc,                   ");
        query.append("        SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','ko')            woTypeDesc,            		");
        query.append("        SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','ko')   pmTypeDesc,            		");
        query.append("        SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','ko')        woStatusDesc,            	");
        query.append("        x.work_time                                                   workTime,            		");
        query.append("        (SELECT emp_name                                                                    		");
        query.append("           FROM TAEMP                                                                        		");
        query.append("          WHERE comp_no = x.comp_no                                                        		");
        query.append("            AND emp_id = x.emp_id)                                     empDesc,            		");
        query.append("        (SELECT c.emp_name                                                                		");
        query.append("            FROM TAEMP c                                                                  		");
        query.append("            WHERE x.comp_no = c.comp_no                                                     		");
        query.append("                AND z.sub_mng_id = c.emp_id)                            subMng,                	");
        query.append("        (SELECT c.description                                                               		");
        query.append("            FROM TAEQCTG c                                                                  		");
        query.append("            WHERE x.comp_no = c.comp_no                                                    		");
        query.append("                AND z.eqctg_id = c.eqctg_id  )                         eqCtgDesc,            		");
        query.append("        (SELECT NVL(y.full_desc,y.description) 													");
        query.append("            FROM TAEQASMB y 																		");
        query.append("          WHERE y.comp_no = x.comp_no 															");
        query.append("               AND y.eqasmb_id = x.eqasmb_id)       					 eqAsmbDesc,    			");
        query.append("        x.perform                                                     perform,            		");
        query.append("        (SELECT param1 FROM TACDSYSD WHERE list_type=x.wo_type||'_TYPE' AND cdsysd_no=x.pm_type) param         		");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y										");
        query.append("ON x.wkor_id = y.wkor_id														");
        query.append(" AND x.comp_no = y.comp_no													");
        query.append("		INNER JOIN TAEQUIPMENT z    											");
        query.append("		ON y.comp_no = z.comp_no          										");
        query.append("		 AND y.equip_id = z.equip_id       										");
        query.append("WHERE 1=1                                                                  	");
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