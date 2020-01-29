package dream.rcm.pmtask.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.rcm.pmtask.dao.RcmPmtaskListDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;


/**
 * dao
 * @author  kim21017
 * @version $Id: RcmPmtaskListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmPmtaskListDAOTarget"
 * @spring.txbn id="rcmPmtaskListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmPmtaskListDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmPmtaskListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmPmtaskListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @return List
     */
    public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                 	");
        query.append("       '' AS seqNo,                 		");
        query.append("       x.rcmlist_no rcmlistNo         	");
        query.append("       ,x.description rcmListDesc        	");
        query.append("       ,d.item_no equipNo,             	");
        query.append("       d.description equipDesc,      		");
        query.append("       y.rcmfunc_no rcmfuncNo,          	");
        query.append("       y.description funcDesc,        	");
        query.append("       z.rcmffail_no rcmffailNo,        	");
        query.append("       z.description failDesc,        	");
        query.append("       e.description asmbDesc,        	");
        query.append("       f.mofail_desc mofailDesc,        	");
        query.append("       f.cafail_desc cafailDesc,        	");
        query.append("       f.effail_desc effailDesc,         	");
        query.append("       h.description pmDesc,            	");
        query.append("       SFACODE_TO_DESC(h.rcm_task_type,'RCM_TASK_TYPE','SYS','',?) rcmTaskTypeDesc,    		");
        query.append("       h.rcm_task_type rcmTaskType,    	");
        query.append("       cycle||' '|| SFACODE_TO_DESC(h.period_type,'PERIOD_TYPE','SYS','',?) cycleDesc,    	");
        query.append("       h.remark,                        	");
        query.append("       h.cycle,                        	");
        query.append("       h.period_type poriodType,        	");
        query.append("       x.rcmlist_id rcmlistId,        	");
        query.append("       y.rcmfunc_id rcmfuncId,        	");
        query.append("       z.rcmffail_id rcmffailId,        	");
        query.append("       b.rcmeq_id rcmeqId,            	");
        query.append("       c.rcmeqasmb_id rcmeqasmbId,    	");
        query.append("       f.rcmfmea_id rcmfmeaId,        	");
        query.append("       b.rcmffeq_id rcmffeqId,        	");
        query.append("       h.rcmpmtask_id rcmpmtaskId     	");
        query.append("FROM   TARCMLIST x                    	");
        query.append("       LEFT OUTER JOIN TARCMFUNC y ON x.rcmlist_id = y.rcmlist_id                            		");
        query.append("       LEFT OUTER JOIN TARCMFFAIL z ON y.rcmfunc_id = z.rcmfunc_id                        	");
        query.append("       INNER JOIN TARCMFFEQ b INNER JOIN TARCMEQ k INNER JOIN TAEQUIPMENT d ON k.equip_id = d.equip_id ON b.rcmeq_id = k.rcmeq_id ON z.rcmffail_id = b.rcmffail_id      	");
        query.append("       LEFT OUTER JOIN TARCMEQASMB c  INNER JOIN TAEQASMB e ON e.eqasmb_id = c.eqasmb_id ON  b.rcmeqasmb_id = c.rcmeqasmb_id                             	");
        query.append("       LEFT OUTER JOIN TARCMFMEA f ON z.rcmffail_id = f.rcmffail_id AND b.rcmffeq_id = f.rcmffeq_id and NVL(b.rcmeqasmb_id,0) = NVL(f.rcmeqasmb_id,0)     		");
        query.append("       LEFT OUTER JOIN TARCMPMTASK h ON f.rcmfmea_id = h.rcmfmea_id    	");
        query.append("WHERE  1=1                                                                       	");
        query.append("  AND  f.rcmfmea_id is not null                                                   ");
        query.append(this.getWhere(rcmPmtaskCommonDTO));
        query.getOrderByQuery("x.rcmlist_id","x.rcmlist_no desc, d.description, y.rcmfunc_no, y.description,z.rcmffail_no,z.description", rcmPmtaskCommonDTO.getOrderBy(), rcmPmtaskCommonDTO.getDirection());

        Object[] objects = new Object[] {
        	user.getLangId(),
        	user.getLangId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(rcmPmtaskCommonDTO.getIsLoadMaxCount(), rcmPmtaskCommonDTO.getFirstRow()), objects);
         }
    
    /**
     * Filter Á¶°Ç
     * @author  kim21017
     * @version $Id: RcmPmtaskListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(RcmPmtaskCommonDTO rcmPmtaskCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        if (!"".equals(rcmPmtaskCommonDTO.getRcmlistId()))
        {
        	query.getAndNumKeyQuery("x.rcmlist_id", rcmPmtaskCommonDTO.getRcmlistId());
        	query.getAndNumKeyQuery("y.rcmfunc_id", rcmPmtaskCommonDTO.getRcmfuncId());
        	query.getAndNumKeyQuery("z.rcmffail_id", rcmPmtaskCommonDTO.getRcmffailId());
        	query.getAndNumKeyQuery("b.rcmeq_id", rcmPmtaskCommonDTO.getRcmeqId());
        	query.getAndNumKeyQuery("c.rcmeqasmb_id", rcmPmtaskCommonDTO.getRcmeqasmbId());
        	
            query.getAndNumKeyQuery("f.rcmfmea_id", rcmPmtaskCommonDTO.getRcmfmeaId());
            return query.toString();
        }

        query.getCodeLikeQuery("x.rcmlist_id", "x.description", 
        		rcmPmtaskCommonDTO.getFilterRcmlistId(), rcmPmtaskCommonDTO.getFilterRcmlistDesc());
        
        
        return query.toString();
    }

	@Override
	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, User user) {
	  QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                 	");
        query.append("  COUNT(1)			               		");
        query.append("FROM   TARCMLIST x                    	");
        query.append("       LEFT OUTER JOIN TARCMFUNC y ON x.rcmlist_id = y.rcmlist_id                            		");
        query.append("       LEFT OUTER JOIN TARCMFFAIL z ON y.rcmfunc_id = z.rcmfunc_id                        	");
        query.append("       INNER JOIN TARCMFFEQ b INNER JOIN TARCMEQ k INNER JOIN TAEQUIPMENT d ON k.equip_id = d.equip_id ON b.rcmeq_id = k.rcmeq_id ON z.rcmffail_id = b.rcmffail_id      	");
        query.append("       LEFT OUTER JOIN TARCMEQASMB c  INNER JOIN TAEQASMB e ON e.eqasmb_id = c.eqasmb_id ON  b.rcmeqasmb_id = c.rcmeqasmb_id                             	");
        query.append("       LEFT OUTER JOIN TARCMFMEA f ON z.rcmffail_id = f.rcmffail_id AND b.rcmffeq_id = f.rcmffeq_id and NVL(b.rcmeqasmb_id,0) = NVL(f.rcmeqasmb_id,0)     		");
        query.append("       LEFT OUTER JOIN TARCMPMTASK h ON f.rcmfmea_id = h.rcmfmea_id    	");
        query.append("WHERE  1=1                                                                       	");
        query.append("  AND  f.rcmfmea_id is not null                                                   ");
        query.append(this.getWhere(rcmPmtaskCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	 
	}
  
}