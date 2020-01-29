package dream.mgr.rpt.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.rpt.dao.MgrRptScrnLogListDAO;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;
import dream.mgr.rpt.dto.MgrRptScrnLogCommonDTO;

/**
 * ȭ�����ӷα� Page - List DAO implements
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrRptScrnLogListDAOTarget"
 * @spring.txbn id="mgrRptScrnLogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrRptScrnLogListDAOOraImpl  extends BaseJdbcDaoSupportOra implements MgrRptScrnLogListDAO
{

    /**
     * grid find
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrRptScrnLogCommonDTO
     * @return List
     */
    public List findList(MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT         																																			");
        query.append("''                 																							AS isDelCheck                               ");
        query.append(",''                																		  					AS SeqNo                                	");
        query.append(", a.comp_no 																						 			AS compNo                                	");
        query.append(", c.login_date																						 		AS LoginDate                                ");
        query.append(", a.user_id 																									AS UserId                        			");
        query.append(", c.user_no 																									AS UserNo                                	");
        query.append(", c.user_name 																								AS UserName                                 ");
        query.append(", (SELECT c.key_name FROM TALANG c WHERE c.key_type = b.key_type AND c.key_no = b.key_no AND c.lang = 'ko')	AS MenuDesc        							");
        query.append(", b.menu_id 																									AS MenuId         							");
        query.append(", b.page_id 																									AS PageId                         			");
        query.append(", SUBSTR(a.file_name, INSTR(a.file_name,'/',-1)+1, INSTR(a.file_name,'.')-INSTR(a.file_name,'/',-1)-1) 		AS fileName            					    ");
        query.append(", SUBSTR(a.access_time, 1, 4)||'-'||SUBSTR(a.access_time, 5, 2)||'-'||SUBSTR(a.access_time, 7, 2)||' '||SUBSTR(a.access_time, 9,2)||':'||SUBSTR(a.access_time, 11,2)||':'||SUBSTR(a.access_time, 13,2) AS LoginTime");
        query.append(" FROM TAACCESSCCLOG a INNER JOIN TAMENU b ON b.file_name = SUBSTR(a.file_name, INSTR(a.file_name,'/',-1)+1, INSTR(a.file_name,'.')-INSTR(a.file_name,'/',-1)-1)   ");
        query.append(" INNER JOIN TAUSER c ON c.user_id = a.user_id        																										");
        query.append(" WHERE 1=1 ");
        query.append(this.getWhere(mgrRptLoginLogCommonDTO, mgrRptScrnLogCommonDTO, user));
        query.getOrderByQuery("a.access_time desc", mgrRptScrnLogCommonDTO.getOrderBy(), mgrRptScrnLogCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrRptScrnLogCommonDTO.getIsLoadMaxCount(), mgrRptScrnLogCommonDTO.getFirstRow()));
    }

    /**
     * Filter ����
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrRptScrnLogCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // ** filter
        //����
        query.getAndDateQuery("c.login_date", mgrRptScrnLogCommonDTO.getFilterScrnFromDate(), mgrRptScrnLogCommonDTO.getFilterScrnToDate());
        //�����
        query.getCodeLikeQuery("a.user_id", "c.user_name", mgrRptScrnLogCommonDTO.getFilterUserId(), mgrRptScrnLogCommonDTO.getFilterUserDesc());
        //�޴�
        if(!"".equals(mgrRptScrnLogCommonDTO.getFilterMenuId())) {
        	query.getLikeQuery("b.menu_id", mgrRptScrnLogCommonDTO.getFilterMenuId());
        }
        else if(!"".equals(mgrRptScrnLogCommonDTO.getFilterMenuDesc())){
        	query.append("AND b.key_no IN (SELECT c.key_no FROM TALANG c 			");
        	query.append("                "
        			+ ""
        			+ "          WHERE c.key_type ='MENU' 		");
        	query.append("                          AND c.lang = 'ko' 				");
        	query.getLikeQuery("c.key_name", mgrRptScrnLogCommonDTO.getFilterMenuDesc());
            query.append(")															");
        }
        //ȭ��
        query.getLikeQuery("a.file_name", mgrRptScrnLogCommonDTO.getFilterScrnDesc());
        return query.toString();
    }

    /**
     * Filter ����
     * @author  euna0207
     * @version $Id: mgrRptScrnLogListDAO.java,v 1.0 2017/08/22 15:20:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrRptScrnLogCommonDTO
     * @return
     * @throws Exception
     */

    public String findTotalCount(
            MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                               						 ");
        query.append("       COUNT(1)                      						 ");
        query.append(" FROM TAACCESSCCLOG a INNER JOIN TAMENU b ON b.file_name = SUBSTR(a.file_name, INSTR(a.file_name,'/',-1)+1, INSTR(a.file_name,'.')-INSTR(a.file_name,'/',-1)-1)        		");
        query.append(" INNER JOIN TAUSER c ON c.user_id = a.user_id        		");
        query.append(" WHERE 1=1     											");
        query.append(this.getWhere(mgrRptLoginLogCommonDTO, mgrRptScrnLogCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    

}